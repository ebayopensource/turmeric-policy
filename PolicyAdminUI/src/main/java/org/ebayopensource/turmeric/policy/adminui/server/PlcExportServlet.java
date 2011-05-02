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

import com.google.gwt.core.client.GWT;

public class PlcExportServlet extends HttpServlet {

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd_HH_mm_ss_SSS");
	private SimpleDateFormat gmtFormat;
	private TimeZone gmt;
	private String expPolicyPrefix;
	private String expSGPrefix;
	private HttpClient client;
	private String policyServiceURL;
	private String exportedEntity;

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

		if (config.getInitParameter("expPolicyPrefix") != null) {
			expPolicyPrefix = config.getInitParameter("expPolicyPrefix");
		}
		if (config.getInitParameter("expSGPrefix") != null) {
			expSGPrefix = config.getInitParameter("expSGPrefix");
		}

		GWT.log("expPolicyPrefix =" + expPolicyPrefix);
		GWT.log("expSGPrefix =" + expSGPrefix);

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
		String result = (String) request.getAttribute("RESULT");
		Boolean error = (Boolean) request.getAttribute("ERROR");

		if (result == null && error == null) {

			final Continuation continuation = ContinuationSupport
					.getContinuation(request);
			continuation.setTimeout(10000);
			continuation.suspend();
			String tmp = request.getRequestURI();

			if (request.getQueryString() != null) {
				tmp += "?";
			}
			HttpURI uri = null;
			if (tmp.startsWith(expPolicyPrefix)) {
				try {
					uri = new HttpURI(new URI(policyServiceURL
							+ tmp.substring(expPolicyPrefix.length()))
							.normalize().toString()
							+ parseExportPolicyQuery(request.getQueryString(),
									response));
					exportedEntity = "Policy";
				} catch (URISyntaxException e) {
					throw new MalformedURLException(e.getMessage());
				}
			} else if (tmp.startsWith(expSGPrefix)) {
				try {
					uri = new HttpURI(new URI(policyServiceURL
							+ tmp.substring(expSGPrefix.length())).normalize()
							.toString()
							+ parseExportSGQuery(request.getQueryString(),
									response));
					exportedEntity = "SG";
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
			final String filename = getFileName(exportedEntity);
			response.setContentType(MimeTypes.TEXT_HTML);
			response.setHeader("Content-disposition", "attachment; filename="
					+ filename);
			response.getOutputStream().write(result.getBytes());
		}
	}

	private String parseExportPolicyQuery(String queryString,
			HttpServletResponse response) throws IOException {
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
		sb.append("&X-TURMERIC-RESPONSE-DATA-FORMAT=XML");
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

	private String parseExportSGQuery(String queryString,
			HttpServletResponse response) throws IOException {
		// eg: 23&50&IP&admin&admin
		String[] params = queryString.split("&");
		if (params.length <= 3) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Invalid url for download: " + queryString);

		}

		StringBuffer sb = new StringBuffer();

		sb.append("X-TURMERIC-SECURITY-USERID=" + params[params.length - 2]);
		sb.append("&X-TURMERIC-SECURITY-PASSWORD=" + params[params.length - 1]);
		sb.append("&X-TURMERIC-SERVICE-NAME=PolicyService");
		sb.append("&X-TURMERIC-OPERATION-NAME=findSubjectGroups");
		sb.append("&X-TURMERIC-USECASE-NAME=TMC");
		sb.append("&X-TURMERIC-REQUEST-DATA-FORMAT=NV");
		sb.append("&X-TURMERIC-RESPONSE-DATA-FORMAT=XML");
		sb.append("&nvns:ns1=http://www.ebayopensource.org/turmeric/security/v1/services&nvns:ns2=urn:oasis:names:tc:xacml:2.0:policy:schema:os");

		for (int i = 0; i < params.length - 3; i++) {
			sb.append("&ns1:subjectGroupQuery.ns1:subjectGroupKey(" + i
					+ ").ns1:subjectGroupId=" + params[i]);
			sb.append("&ns1:subjectGroupQuery.ns1:subjectGroupKey(" + i
					+ ").ns1:subjectType=" + params[params.length - 3]);
		}

		sb.append("&ns1:subjectGroupQuery.ns1:includeSubjects=true");
		return sb.toString();
	}

	private String getFileName(final String entity) {
		StringBuffer name = new StringBuffer();
		name.append(entity);
		name.append("_bulk_");
		name.append(dateFormat.format(new Date()));
		name.append(".xml");

		return name.toString();
	}

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

					//TODO validate it against XSD
					
//					try {
//
//						final ServletContext context = getServletContext();
//
//						String schemaLang = "http://www.w3.org/2001/XMLSchema";
//						// get validation driver:
//						SchemaFactory factory = XMLSchemaFactory
//								.newInstance(schemaLang);
//						// create schema by reading it from an XSD file:
//						final InputStream importexportAsStream = context
//								.getResourceAsStream("/importexport.xsd");
//
//						final StreamSource importExportSource = new StreamSource(
//								importexportAsStream);
//						final Schema schema = factory
//								.newSchema(importExportSource);
//						Validator validator = schema.newValidator();
//						validator.validate(new StreamSource(content));
//						request.setAttribute("RESULT", content);
//					} catch (Exception e) {
//						System.out.println(e);
//						request.setAttribute("ERROR", Boolean.TRUE);
//					}
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

}