/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreatePolicyResponse;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * CreatePolicyResponseJS.
 */
public class CreatePolicyResponseJS extends JavaScriptObject implements
        CreatePolicyResponse {
    
    /** The Constant NAME. */
    public static final String NAME = "ns1.createPolicyResponse";

    /**
	 * Instantiates a new creates the policy response js.
	 */
    protected CreatePolicyResponseJS () {}

    
    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the creates the policy response
	 */
    public static final native CreatePolicyResponse fromJSON (String json) /*-{
        try {
            return eval('(' + json + ')');
        } catch (err) {
            return null;
        }
    }-*/;
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreatePolicyResponse#getPolicyId()
     */
    @Override
    public final Long getPolicyId () {
        return Long.valueOf(getPolicyIdAsString());
    }
    
    /**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreatePolicyResponse#getErrorMessage()
	 */
    @Override
    public native final String getErrorMessage() /*-{
       return this["ns1.createPolicyResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Gets the policy id as string.
	 * 
	 * @return the policy id as string
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreatePolicyResponse#getPolicyId()
	 */
    public native final String getPolicyIdAsString() /*-{
        return this["ns1.createPolicyResponse"]["ns1.policyId"];
    }-*/;

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreatePolicyResponse#isErrored()
	 */
    @Override
    public native final boolean isErrored() /*-{
        if (this["ns1.createPolicyResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;

}
