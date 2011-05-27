/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeletePolicyResponse;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * DeletePolicyResponseJS.
 */
public class DeletePolicyResponseJS extends JavaScriptObject implements
        DeletePolicyResponse {

    /** The Constant NAME. */
    public static final String NAME = "ns1.deletePolicyResponse";
    
    /**
	 * Instantiates a new delete policy response js.
	 */
    protected DeletePolicyResponseJS () {}
    
    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the delete policy response
	 */
    public static final native DeletePolicyResponse fromJSON (String json) /*-{
        try {
            return eval('(' + json + ')');
        } catch (err) {
        return null;
            }
    }-*/;
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeletePolicyResponse#isSuccess()
     */
    public final Boolean isSuccess () {
        return Boolean.valueOf(getResultAsString());
    }
    
    /**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeletePolicyResponse#getErrorMessage()
	 */
    @Override
    public native final String getErrorMessage() /*-{
       return this["ns1.deletePolicyResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Gets the result as string.
	 * 
	 * @return the result as string
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeletePolicyResponse#isSuccess()
	 */
    public native final String getResultAsString() /*-{
        return this["ns1.deletePolicyResponse"]["ns1.success"];
    }-*/;

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeletePolicyResponse#isErrored()
	 */
    @Override
    public native final boolean isErrored() /*-{
        if (this["ns1.deletePolicyResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;

}
