package org.ebayopensource.turmeric.policy.adminui.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ebayopensource.turmeric.policy.adminui.client.model.AbstractPolicyAdminUIService.RequestFormat;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;
import org.eclipse.jetty.http.HttpHeaders;
import org.eclipse.jetty.http.HttpSchemes;
import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;


public class PlcImportServlet extends HttpServlet {

	private String impPolicyPrefix;
	private String impSGPrefix;
	private HttpClient client;
	private String policyServiceURL;
	private HttpURI uri;
	private String incomingJson;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		if (config.getInitParameter("PolicyServiceURL") != null) {
			policyServiceURL = config.getInitParameter("PolicyServiceURL");
		}

		if (config.getInitParameter("impPolicyPrefix") != null) {
			impPolicyPrefix = config.getInitParameter("impPolicyPrefix");
		}
		if (config.getInitParameter("impSGPrefix") != null) {
			impSGPrefix = config.getInitParameter("impSGPrefix");
		}

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
			continuation.setTimeout(500000);
			continuation.suspend();
			String tmp = request.getRequestURI();

			if (request.getQueryString() == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Invalid or empty url");
			} else {
				tmp += "?";
			}
			// eg: admin&admin
			String[] params = request.getQueryString().split("&");
			if (params.length < 2) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Invalid url: " + request.getQueryString());
			}

			ByteArrayOutputStream importData = parseInputStream(request,
					response);

			if (tmp.startsWith(impPolicyPrefix)) {
				String partialURL = getPartialUrl("createPolicy",
						RequestFormat.JSON, params);

				incomingJson = parseFile(importData, "/importpolicy.xsl");

				try {
					uri = new HttpURI(new URI(policyServiceURL
							+ tmp.substring(impPolicyPrefix.length()))
							.normalize().toString() + partialURL.toString());
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			} else if (tmp.startsWith(impSGPrefix)) {
				String partialURL = getPartialUrl("createSubjectGroups",
						RequestFormat.JSON, params);

				incomingJson = parseFile(importData, "/importsg.xsl");

				try {
					uri = new HttpURI(new URI(policyServiceURL
							+ tmp.substring(impSGPrefix.length()))
							.normalize().toString() + partialURL.toString());
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Invalid url for download: " + tmp);
				return;
			}

			send(request, response, continuation, uri, incomingJson);

			return;
		}

		if (error != null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private String parseFile(final ByteArrayOutputStream importData,
			final String xsl) {
		System.setProperty("javax.xml.transform.TransformerFactory",
				"net.sf.saxon.TransformerFactoryImpl");
		String incomingJson = null;
		// read data
		final ServletContext context = getServletContext();
		final InputStream importJSONTemplateAsStream = context
				.getResourceAsStream(xsl);
		// Create a transform factory instance.
		TransformerFactory tfactory = TransformerFactory.newInstance();
		// Create a transformer for the stylesheet.
		Transformer transformer;

		try {
			transformer = tfactory.newTransformer(new StreamSource(
					importJSONTemplateAsStream));

			StringWriter importDataJson = new StringWriter();
			StreamResult streamResult = new StreamResult(importDataJson);
			InputStream inputStream;
			inputStream = new ByteArrayInputStream(importData.toString()
					.getBytes("UTF-8"));

			StreamSource streamSource = new StreamSource(inputStream);
			// importData.toString());

			transformer.transform(streamSource, streamResult);
			incomingJson = importDataJson.toString().substring(1);
			if (incomingJson == null) {
				throw new TransformerException("Invalid importing file!");
			}
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return incomingJson;
	}

	public String getPartialUrl(String operation, RequestFormat format,
			String[] params) {
		StringBuffer url = new StringBuffer();

		url.append("X-TURMERIC-SERVICE-NAME=" + "PolicyService");
		url.append("&X-TURMERIC-OPERATION-NAME=" + operation);
		url.append("&X-TURMERIC-USECASE-NAME=" + "TMC");
		url.append("&X-TURMERIC-SECURITY-USERID=" + params[params.length - 2]);
		url.append("&X-TURMERIC-SECURITY-PASSWORD=" + params[params.length - 1]);
		switch (format) {
		case JSON:
			url.append("&X-TURMERIC-REQUEST-DATA-FORMAT=" + "JSON");
			break;
		case NV:
			url.append("&X-TURMERIC-REQUEST-DATA-FORMAT=" + "NV");
			break;
		}

		url.append("&X-TURMERIC-RESPONSE-DATA-FORMAT=" + "XML");

		return url.toString();
	}

	public ByteArrayOutputStream parseInputStream(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletFileUpload upload = new ServletFileUpload();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				InputStream stream = item.openStream();
				// Process the input stream
				int len;
				byte[] buffer = new byte[8192];
				while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
					out.write(buffer, 0, len);
				}

				int maxFileSize = 10 * (1024 * 1024); // 10 megs max
				if (out.size() > maxFileSize) {
					System.out.println("File is > than " + maxFileSize);
					response.sendError(
							HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE,
							"Max allowed file size: 10 Mb");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return out;
	}

	public void send(final HttpServletRequest request,
			final HttpServletResponse response,
			final Continuation continuation, final HttpURI uri,
			String contentRequest) throws IOException {

		ContentExchange exchange = new ContentExchange() {
			protected void onRequestCommitted() throws IOException {
				super.onRequestCommitted();
			}

			protected void onRequestComplete() throws IOException {
				super.onRequestComplete();
				// System.out.println("onRequestComplete");
			}

			protected void onResponseComplete() throws IOException {
				String content = getResponseContent();
				if (content == null) {
					request.setAttribute("ERROR", Boolean.TRUE);
				} else {
					// System.out.println("onResponseComplete");
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
		exchange.setMethod(request.getMethod()); // <-- POST
		StringBuffer content = new StringBuffer();
		content.append("{\"jsonns.ns2\":\"urn:oasis:names:tc:xacml:2.0:policy:schema:os\",\"jsonns.ns1\":\"htt"
				+ "p://www.ebayopensource.org/turmeric/security/v1/services\",\"jsonns.ms\":\"htt"
				+ "p://www.ebayopensource.org/turmeric/common/v1/types\",\"jsonns.xs\":\"http://www.w3.org/2001/XMLSchema\",\"jsonns.xsi\":\"htt"
				+ "p://www.w3.org/2001/XMLSchema-instance\", \"ns1.createPolicyRequest\":{ ");

				
		content.append(contentRequest);
		content.append("}");

		exchange.setURL(uri.toString());

		exchange.setVersion(request.getProtocol());
		long contentLength = content.length();

		exchange.setRequestHeader(HttpHeaders.CONTENT_LENGTH,
				Long.toString(contentLength));

		exchange.setRequestContentSource(new ByteArrayInputStream(content
				.toString().getBytes("UTF-8")));
		exchange.setRequestContentType("text/json; charset=utf-8");
		exchange.setRequestContent(new ByteArrayBuffer(content.toString()
				.getBytes("UTF-8")));

		client.send(exchange);
	}

}