/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.fail;
import static org.powermock.api.easymock.PowerMock.mockStatic;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gwt.http.client.Response;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ErrorResponse.class })
public class AbstractPolicyAdminUIServiceTest {
	private Response response = null;
	private String errorJSONMsg = null;
	Object errorMsgEval = null;
	AbstractPolicyAdminUIService abstractPolicyAdminUIService = null;
	ErrorResponse errorResponse = null;
	
	@Before
	public void setUp() {
		errorJSONMsg = "{\"jsonns.ns2\":\"urn:oasis:names:tc:xacml:2.0:policy:schema:os\",\"jsonns.ns1\":\"http://www.ebayopensource.org/turmeric/security/v1/services\",\"jsonns.ms\":\"http://www.ebayopensource.org/turmeric/common/v1/types\",\"jsonns.xs\":\"http://www.w3.org/2001/XMLSchema\",\"jsonns.xsi\":\"http://www.w3.org/2001/XMLSchema-instance\",\"ns1.createPolicyResponse\":{\"ms.ack\":\"Success\",\"ns1.policyId\":\"2\"}}";// {\"jsonns.ns2\":\"urn:oasis:names:tc:xacml:2.0:policy:schema:os\",\"jsonns.ns1\":\"http://www.ebayopensource.org/turmeric/security/v1/services\",\"jsonns.ms\":\"http://www.ebayopensource.org/turmeric/common/v1/types\",\"jsonns.xs\":\"http://www.w3.org/2001/XMLSchema\",\"jsonns.xsi\":\"http://www.w3.org/2001/XMLSchema-instance\",\"ns1.createPolicyResponse\":{\"ms.ack\":\"Failure\",\"ms.errorMessage\":{\"ms.error\":[{\"ms.errorId\":\"20004\",\"ms.domain\":\"TurmericPolicy\",\"ms.subdomain\":\"POLICY\",\"ms.severity\":\"Error\",\"ms.category\":\"System\",\"ms.message\":\"Invalid input : Please input value for policy name or Id.\",\"ms.parameter\":[{\"@name\":\"Param1\",\"__value__\":\"Please input value for policy name or Id.\"}],\"ms.errorName\":\"svc_policyservice_invalid_input_error\",\"ms.resolution\":\"\",\"ms.organization\":\"eBay\"}]},\"ns1.policyId\":\"0\"}}";
		response = org.easymock.EasyMock.createMock(Response.class);
		mockStatic(ErrorResponse.class);

		errorResponse = org.powermock.api.easymock.PowerMock
				.createMock(ErrorResponse.class);

		
		abstractPolicyAdminUIService = new AbstractPolicyAdminUIService();

	}

	@Test
	public void testNoError() {
		response = null;
		try {
			abstractPolicyAdminUIService.getErrorAsThrowable(response);
		} catch (Throwable t) {
			fail("an exception was thrown");
		}

	}

	@Test (expected=Throwable.class)
	public void testErrorResponseNull() {

		String errorJSONMsg_1 = "{\"jsonns.ns2\":\"urn:oasis:names:tc:xacml:2.0:policy:schema:os\",\"jsonns.ns1\":\"http://www.ebayopensource.org/turmeric/security/v1/services\",\"jsonns.ms\":\"http://www.ebayopensource.org/turmeric/common/v1/types\",\"jsonns.xs\":\"http://www.w3.org/2001/XMLSchema\",\"jsonns.xsi\":\"http://www.w3.org/2001/XMLSchema-instance\",\"ns1.createPolicyResponse\":{\"ns1.policyId\":\"0\"}}";
		expect(response.getText()).andReturn(errorJSONMsg_1);
		expect(ErrorResponse.fromJSON(errorJSONMsg_1)).andReturn(errorResponse);
		
		replay(response);
		PowerMock.replay(ErrorResponse.class);

		AbstractPolicyAdminUIService policyAdminUIService = new AbstractPolicyAdminUIService();

		policyAdminUIService.getErrorAsThrowable(response);
	
		EasyMock.verify(response);

	}
	
	
	
}
