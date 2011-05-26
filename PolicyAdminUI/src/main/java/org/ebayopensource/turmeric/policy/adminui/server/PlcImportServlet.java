package org.ebayopensource.turmeric.policy.adminui.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ebayopensource.turmeric.common.v1.types.CommonErrorData;
import org.ebayopensource.turmeric.policy.adminui.client.model.AbstractPolicyAdminUIService.RequestFormat;

import org.ebayopensource.turmeric.runtime.common.exceptions.ServiceException;
import org.ebayopensource.turmeric.security.v1.services.CreatePolicyRequest;
import org.ebayopensource.turmeric.security.v1.services.CreatePolicyResponse;
import org.ebayopensource.turmeric.security.v1.services.CreateSubjectGroupsRequest;
import org.ebayopensource.turmeric.security.v1.services.CreateSubjectGroupsResponse;
import org.ebayopensource.turmeric.security.v1.services.FindPoliciesResponse;
import org.ebayopensource.turmeric.security.v1.services.FindSubjectGroupsResponse;
import org.ebayopensource.turmeric.security.v1.services.Policy;
import org.ebayopensource.turmeric.security.v1.services.PolicySet;
import org.ebayopensource.turmeric.security.v1.services.Rule;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroup;
import org.ebayopensource.turmeric.services.policyservice.intf.gen.BasePolicyServiceConsumer;

public class PlcImportServlet extends HttpServlet {

	private String impPolicyPrefix;
	private String impSGPrefix;
	private static String SECURITY_TOKEN_HEADER = "X-TURMERIC-SECURITY-TOKEN";
	private static String SECURITY_TOKEN = "security token value";
	private String policyServiceURL;

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

	}

	@SuppressWarnings("unchecked")
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String result = (String) request.getAttribute("RESULT");
		Boolean error = (Boolean) request.getAttribute("ERROR");

		if (result == null && error == null) {

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

			final ByteArrayOutputStream importData = parseInputStream(request,
					response);

			try {

				final BasePolicyServiceConsumer consumer = new BasePolicyServiceConsumer();
				consumer.getService().getInvokerOptions()
						.setTransportName("HTTP11");
				consumer.getService().setSessionTransportHeader(
						SECURITY_TOKEN_HEADER, SECURITY_TOKEN);

				if (importData != null) {

					if (tmp.startsWith(impPolicyPrefix)) {

						String partialURL = getPartialUrl("createPolicy",
								RequestFormat.JSON, params);

						URL url = new URL(policyServiceURL
								+ tmp.substring(impPolicyPrefix.length())
										.toString() + partialURL);

						consumer.getService().setServiceLocation(url);

						PolicySet policySet = unmarshalXmlPolicyData(importData);
						StringBuffer policiesNotStored = new StringBuffer();

						if (policySet.getPolicy().isEmpty()) {
							response.sendError(
									HttpServletResponse.SC_NOT_FOUND,
									"No policies to import");
						}

						CreatePolicyResponse createPolicyResponse;

						for (Policy policy : policySet.getPolicy()) {
							policy.setPolicyId(null);

							if ("RL".equalsIgnoreCase(policy.getPolicyType())) {
								List<Rule> rules = policy.getRule();
								for (Rule rule : rules) {
									rule.setRuleName(policy.getPolicyName());
								}
							}
							CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
							createPolicyRequest.setPolicy(policy);
							createPolicyResponse = (CreatePolicyResponse) consumer
									.getService()
									.createDispatch("createPolicy")
									.invoke(createPolicyRequest);

							if (createPolicyResponse.getErrorMessage() != null) {
								policiesNotStored.append(policy.getPolicyName()
										+ ": ");
								for (CommonErrorData error2 : createPolicyResponse
										.getErrorMessage().getError()) {
									policiesNotStored.append(error2
											.getMessage() + "\n");
								}
							}
						}

						if (policiesNotStored.length() > 0) {
							response.sendError(HttpServletResponse.SC_CONFLICT,
									"The following policies have not been stored: \n"
											+ policiesNotStored.toString());
						}

					} else if (tmp.startsWith(impSGPrefix)) {

						String partialURL = getPartialUrl(
								"createSubjectGroups", RequestFormat.JSON,
								params);

						URL url = new URL(policyServiceURL
								+ tmp.substring(impSGPrefix.length())
										.toString() + partialURL);

						consumer.getService().setServiceLocation(url);

						List<SubjectGroup> subjectGroups = unmarshalXmlSGData(importData);
						StringBuffer sgNotStored = new StringBuffer();

						if (subjectGroups.isEmpty()) {
							response.sendError(
									HttpServletResponse.SC_NOT_FOUND,
									"No Subject Group to import");
						}

						CreateSubjectGroupsResponse createSGResponse;
						for (SubjectGroup subjectGroup : subjectGroups) {
							subjectGroup.setSubjectMatch(null);
							
							CreateSubjectGroupsRequest createSGRequest = new CreateSubjectGroupsRequest();
							createSGRequest.getSubjectGroups()
									.add(subjectGroup);
							createSGResponse = consumer
									.createSubjectGroups(createSGRequest);
	
							if (createSGResponse.getErrorMessage() != null) {
								sgNotStored.append(subjectGroup.getSubjectGroupName()
										+ ": ");
								for (CommonErrorData error2 : createSGResponse
										.getErrorMessage().getError()) {
									sgNotStored.append(error2
											.getMessage() + "\n");
								}
							}
						}
						
						if (sgNotStored.length() > 0) {
							response.sendError(HttpServletResponse.SC_CONFLICT,
									"The following Subject Groups have not been stored: \n"
											+ sgNotStored.toString());
						}
						
					} else {
						response.sendError(HttpServletResponse.SC_BAD_REQUEST,
								"Invalid url for download: " + tmp);
						return;
					}

					return;
				}
			} catch (JAXBException e) {
				response.sendError(
						HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Unable to parse imported file");

			} catch (ServiceException e1) {
				response.sendError(
						HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Unable to invoke PolicyService");

			}
		}

		if (error != null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	public String getPartialUrl(String operation, RequestFormat format,
			String[] params) {
		StringBuffer url = new StringBuffer();
		url.append("X-TURMERIC-OPERATION-NAME=" + operation);
		url.append("&X-TURMERIC-USECASE-NAME=" + "TMC");
		url.append("&X-TURMERIC-SECURITY-USERID=" + params[params.length - 2]);
		url.append("&X-TURMERIC-SECURITY-PASSWORD=" + params[params.length - 1]);
		url.append("&X-TURMERIC-RESPONSE-DATA-FORMAT=" + "XML");

		return url.toString();
	}

	public PolicySet unmarshalXmlPolicyData(final ByteArrayOutputStream xmlInput)
			throws ServletException, JAXBException, IOException {
		JAXBContext jc = JAXBContext
				.newInstance("org.ebayopensource.turmeric.security.v1.services");

		Unmarshaller u = jc.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<FindPoliciesResponse> unmarshalledXML = (JAXBElement<FindPoliciesResponse>) u
				.unmarshal(new ByteArrayInputStream(xmlInput.toByteArray()));

		FindPoliciesResponse findPoliciesResponse = unmarshalledXML.getValue();

		return findPoliciesResponse.getPolicySet();

	}

	public List<SubjectGroup> unmarshalXmlSGData(
			final ByteArrayOutputStream xmlInput) throws ServletException,
			JAXBException, IOException {
		JAXBContext jc = JAXBContext
				.newInstance("org.ebayopensource.turmeric.security.v1.services");

		Unmarshaller u = jc.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<FindSubjectGroupsResponse> unmarshalledXML = (JAXBElement<FindSubjectGroupsResponse>) u
				.unmarshal(new ByteArrayInputStream(xmlInput.toByteArray()));

		FindSubjectGroupsResponse findSGResponse = unmarshalledXML.getValue();

		return findSGResponse.getSubjectGroups();

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

}