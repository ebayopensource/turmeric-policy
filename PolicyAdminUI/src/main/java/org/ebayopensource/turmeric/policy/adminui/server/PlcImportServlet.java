package org.ebayopensource.turmeric.policy.adminui.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.ebayopensource.turmeric.security.v1.services.CreatePolicyRequest;
import org.ebayopensource.turmeric.security.v1.services.CreatePolicyResponse;
import org.ebayopensource.turmeric.security.v1.services.CreateSubjectGroupsRequest;
import org.ebayopensource.turmeric.security.v1.services.CreateSubjectGroupsResponse;
import org.ebayopensource.turmeric.security.v1.services.FindPoliciesResponse;
import org.ebayopensource.turmeric.security.v1.services.FindSubjectGroupsResponse;
import org.ebayopensource.turmeric.security.v1.services.Policy;
import org.ebayopensource.turmeric.security.v1.services.PolicySet;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroup;
import org.ebayopensource.turmeric.services.policyservice.intf.gen.BasePolicyServiceConsumer;


public class PlcImportServlet extends HttpServlet {

	private String impPolicyPrefix;
	private String impSGPrefix;

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

			final BasePolicyServiceConsumer consumer = new BasePolicyServiceConsumer();

			if (importData != null) {
				if (tmp.startsWith(impPolicyPrefix)) {
					try {
						PolicySet unmarshalXmlData = unmarshalXmlPolicyData(importData);

						if (unmarshalXmlData.getPolicy().isEmpty()) {
							throw new IOException("No policies to import");
						}

						// Service service;
						// try {
						// service = ServiceFactory.create("PolicyService");
						//
						// Object responseNew =
						// service.createDispatch("createPolicy")
						// .invoke(request);
						//
						// } catch (ServiceException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }

						CreatePolicyResponse createPolicyResponse;

						for (Policy policy : unmarshalXmlData.getPolicy()) {
							CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
							createPolicyRequest.setPolicy(policy);
							createPolicyResponse = consumer
									.createPolicy(createPolicyRequest);

							if (createPolicyResponse.getErrorMessage() != null) {
								response.sendError(
										HttpServletResponse.SC_CONFLICT,
										"Policy " + policy.getPolicyName()
												+ " can not be imported!");
								break;
							}

						}
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else if (tmp.startsWith(impSGPrefix)) {
				try {
					List<SubjectGroup> unmarshalXmlData = unmarshalXmlSGData(importData);

					if (unmarshalXmlData.isEmpty()) {
						throw new IOException("No Subject Group to import");
					}

					CreateSubjectGroupsResponse createSGResponse;

					for (SubjectGroup subjectGroup : unmarshalXmlData) {
						CreateSubjectGroupsRequest createSGRequest = new CreateSubjectGroupsRequest();
						createSGRequest.getSubjectGroups().add(subjectGroup);
						createSGResponse = consumer
								.createSubjectGroups(createSGRequest);

						if (createSGResponse.getErrorMessage() != null) {
							response.sendError(
									HttpServletResponse.SC_CONFLICT,
									"Subject Group "
											+ subjectGroup
													.getSubjectGroupName()
											+ " can not be imported!");
							break;
						}

					}
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Invalid url for download: " + tmp);
				return;
			}

			return;
		}

		if (error != null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
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

}