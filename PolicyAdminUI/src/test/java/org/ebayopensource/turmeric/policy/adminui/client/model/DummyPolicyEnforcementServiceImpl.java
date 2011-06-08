/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model;

import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.OperationKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyEnforcementService;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The Class DummyPolicyQueryServiceImpl.
 */
public class DummyPolicyEnforcementServiceImpl extends AbstractPolicyAdminUIService implements PolicyEnforcementService {

	@Override
	public void verify(OperationKey opKey, List<String> policyTypes,
			Map<String, String> credentials, List<String[]> subjectTypes,
			Map<String, String> extendedInfo,
			List<String> accessControlObjects, String resourceType,
			AsyncCallback<VerifyAccessResponse> callback) {
		// Do Nothing
		
	}
 
}
