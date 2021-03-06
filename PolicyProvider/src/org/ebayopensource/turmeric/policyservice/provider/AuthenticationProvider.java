/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.policyservice.provider;

import org.ebayopensource.turmeric.policyservice.exceptions.PolicyProviderException;
import org.ebayopensource.turmeric.security.v1.services.GetAuthenticationPolicyRequest;
import org.ebayopensource.turmeric.security.v1.services.GetAuthenticationPolicyResponse;

/**
 * The interface for authentication provider.
 * 
 */
public interface AuthenticationProvider {
	
	/**
	 * Retrieve the authentication policy based on given request.
	 * 
	 * @param request
	 *            The given request
	 * @return The response object which contains the authentication policy
	 * @throws PolicyProviderException policy related exception
	 */
	GetAuthenticationPolicyResponse getAuthenticationPolicy(
			GetAuthenticationPolicyRequest request)
			throws PolicyProviderException;
}
