package org.ebayopensource.turmeric.policy.adminui.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;
import org.eclipse.jetty.http.HttpHeaders;
import org.eclipse.jetty.http.HttpSchemes;
import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.io.Buffer;

public class PlcExportServlet extends HttpServlet {

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd_HH_mm_ss_SSS");
	private SimpleDateFormat gmtFormat;
	private TimeZone gmt;
	private String myPrefix;
	private HttpClient client;
	private String policyServiceURL;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String[] ids = TimeZone.getAvailableIDs(0);
		gmt = TimeZone.getTimeZone(ids[0]); // GMT
		gmtFormat = new SimpleDateFormat("yyyy MM dd hh:00");
		gmtFormat.setTimeZone(gmt);

		if (config.getInitParameter("PolicyServiceURL") != null) {
			policyServiceURL = config.getInitParameter("PolicyServiceURL");
		}

		if (config.getInitParameter("myPrefix") != null) {
			myPrefix = config.getInitParameter("myPrefix");
		}

		System.err.println("myPrefix =" + myPrefix);
		client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		try {
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String json = (String) request.getAttribute("RESULT");
		Boolean error = (Boolean) request.getAttribute("ERROR");

		if (json == null && error == null) {

			final Continuation continuation = ContinuationSupport
					.getContinuation(request);
			continuation.setTimeout(10000);
			continuation.suspend();
			String tmp = request.getRequestURI();

			if (request.getQueryString() != null) {
				tmp += "?" + parseQuery(request.getQueryString(), response);
			}
			HttpURI uri = null;
			if (tmp.startsWith(myPrefix)) {
				try {
					uri = new HttpURI(new URI(policyServiceURL
							+ tmp.substring(myPrefix.length())).normalize()
							.toString());
				} catch (URISyntaxException e) {
					throw new MalformedURLException(e.getMessage());
				}
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Invalid url for download: " + tmp);
				return;
			}

			send(request, response, continuation, uri);
			return;
		}

		if (error != null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else {
			final String filename = getFileName();
			// (request);
			String csv = json; // convertToCSV(request, json);
			response.setContentType(MimeTypes.TEXT_HTML);
			response.setHeader("Content-disposition", "attachment; filename="
					+ filename);
			response.getOutputStream().write(csv.getBytes());
		}
	}

	private String parseQuery(String queryString, HttpServletResponse response)
			throws IOException {
		// eg: 1&71&100&BLACKLIST&admin&admin
		String[] params = queryString.split("&");

		if (params.length <= 3) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Invalid url for download: " + queryString);

		}

		StringBuffer sb = new StringBuffer();

		sb.append("X-TURMERIC-SECURITY-USERID=" + params[params.length - 2]);
		sb.append("&X-TURMERIC-SECURITY-PASSWORD=" + params[params.length - 1]);
		sb.append("&X-TURMERIC-SERVICE-NAME=PolicyService");
		sb.append("&X-TURMERIC-OPERATION-NAME=findPolicies");
		sb.append("&X-TURMERIC-USECASE-NAME=TMC");
		sb.append("&X-TURMERIC-REQUEST-DATA-FORMAT=NV");
		sb.append("&X-TURMERIC-RESPONSE-DATA-FORMAT=JSON");
		sb.append("&nvns:ns1=http://www.ebayopensource.org/turmeric/security/v1/services&nvns:ns2=urn:oasis:names:tc:xacml:2.0:policy:schema:os");

		for (int i = 0; i < params.length - 3; i++) {
			sb.append("&ns1:policyKey(" + i + ").ns1:policyId=" + params[i]
					+ "&ns1:policyKey(" + i + ").ns1:policyType="
					+ params[params.length - 3]);
		}

		sb.append("&ns1:queryCondition.ns1:query(0).ns1:QueryType=ActivePoliciesOnly");
		sb.append("&ns1:queryCondition.ns1:query(0).ns1:QueryValue=FALSE");

		return sb.toString();
	}

	private String getFileName() {
		StringBuffer name = new StringBuffer();
		name.append("policy_bulk_");
		name.append(dateFormat.format(new Date()));
		name.append("_");
		name.append("json");

		return name.toString();
	}

	// private void getPoliciesById(String ids, String type) {
	// PolicyKey key = new PolicyKey();
	// key.setName(ids);
	// key.setType(type);
	//
	// QueryCondition condition = new QueryCondition();
	//
	// condition.addQuery(new QueryCondition.Query(
	// QueryCondition.ActivePoliciesOnlyValue.FALSE));
	// PolicyQueryService service = (PolicyQueryService) serviceMap
	// .get(SupportedService.POLICY_QUERY_SERVICE);
	//
	//
	// service.findPolicies(null, Collections.singletonList(key), null, null,
	// null, null, null, condition,
	// new AsyncCallback<GetPoliciesResponse>() {
	//
	// public void onFailure(Throwable arg) {
	//
	// // if (arg.getLocalizedMessage().contains("500")) {
	// // view.error(PolicyAdminUIUtil.messages
	// // .serverError(PolicyAdminUIUtil.policyAdminConstants
	// // .genericErrorMessage()));
	// // } else {
	// // view.error(PolicyAdminUIUtil.messages
	// // .serverError(arg.getLocalizedMessage()));
	// // }
	// }
	//
	// public void onSuccess(GetPoliciesResponse result) {
	// PolicySummaryPresenter.this.policies = copyToWriteable(result
	// .getPolicies());
	// PolicySummaryPresenter.this.view.setPolicies(policies);
	// for (GenericPolicy p : policies)
	// fetchAccess(p);
	// }
	// });
	//
	// }

	public void send(final HttpServletRequest request,
			final HttpServletResponse response,
			final Continuation continuation, final HttpURI uri)
			throws IOException {

		ContentExchange exchange = new ContentExchange() {
			protected void onRequestCommitted() throws IOException {
				super.onRequestCommitted();
			}

			protected void onRequestComplete() throws IOException {
				super.onRequestComplete();
			}

			protected void onResponseComplete() throws IOException {
				String content = getResponseContent();
				if (content == null) {
					request.setAttribute("ERROR", Boolean.TRUE);
				} else {
					// convert json content to csv
					request.setAttribute("RESULT", content);
				}

				continuation.resume();
			}

			protected void onResponseHeader(Buffer name, Buffer value)
					throws IOException {
				String s = name.toString().toLowerCase();
				if (s.startsWith("X-TURMERIC-ERROR") && !response.isCommitted()) {
					request.setAttribute("ERROR", Boolean.TRUE);
				}
				super.onResponseHeader(name, value);
			}

			protected void onConnectionFailed(Throwable ex) {
				onException(ex);
			}

			protected void onException(Throwable ex) {
				request.setAttribute("ERROR", Boolean.TRUE);
				continuation.resume();
			}

			protected void onExpire() {
				request.setAttribute("ERROR", Boolean.TRUE);
				continuation.resume();
			}

		};

		exchange.setScheme(HttpSchemes.HTTPS.equals(request.getScheme()) ? HttpSchemes.HTTPS_BUFFER
				: HttpSchemes.HTTP_BUFFER);
		exchange.setMethod(request.getMethod());
		exchange.setURL(uri.toString());
		exchange.setVersion(request.getProtocol());

		// copy headers
		boolean xForwardedFor = false;
		boolean hasContent = false;
		long contentLength = -1;
		Enumeration<?> enm = request.getHeaderNames();
		while (enm.hasMoreElements()) {
			String hdr = (String) enm.nextElement();
			String lhdr = hdr.toLowerCase();

			if ("content-length".equals(lhdr)) {
				contentLength = request.getContentLength();
				exchange.setRequestHeader(HttpHeaders.CONTENT_LENGTH,
						Long.toString(contentLength));
				if (contentLength > 0)
					hasContent = true;
			} else if ("x-forwarded-for".equals(lhdr))
				xForwardedFor = true;

			Enumeration<?> vals = request.getHeaders(hdr);
			while (vals.hasMoreElements()) {
				String val = (String) vals.nextElement();
				if (val != null) {
					exchange.setRequestHeader(hdr, val);
				}
			}
		}
		if (hasContent) {
			exchange.setRequestContentSource(request.getInputStream());
		}
		client.send(exchange);

	}

	// @Override
	// public void doGet(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// String json = request.getParameter("json");
	// response.setContentType("application/x-download");
	// response.setHeader("Content-Disposition", "attachment; filename="
	// + "export_date" + ".txt");
	// // Send the file.
	// PrintWriter out = response.getWriter();
	// out.print(json);
	// OutputStream stream = response.getOutputStream();
	//
	// stream.flush();
	//
	// }
}