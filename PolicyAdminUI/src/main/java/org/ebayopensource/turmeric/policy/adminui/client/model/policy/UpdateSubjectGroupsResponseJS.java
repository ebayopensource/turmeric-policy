/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateSubjectGroupsResponse;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * UpdateSubjectGroupsResponseJS.
 */
public class UpdateSubjectGroupsResponseJS extends JavaScriptObject implements
        UpdateSubjectGroupsResponse {
    
    /** The Constant NAME. */
    public static final String NAME = "ns1.updateSubjectGroupsResponse";

    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the update subject groups response js
	 */
    public static final native UpdateSubjectGroupsResponseJS fromJSON (String json) /*-{
        try {
            return eval('(' + json + ')');
        } catch (err) {
            return null;
        }
    }-*/;

    
    
    /**
	 * Instantiates a new update subject groups response js.
	 */
    protected UpdateSubjectGroupsResponseJS() {}
    
    
    /**
	 * Checks if is success.
	 * 
	 * @return the boolean
	 */
    public final Boolean isSuccess () {
        return Boolean.valueOf(getResultAsString());
    }
    
    /**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateSubjectGroupsResponse#getErrorMessage()
	 */
    public final native String getErrorMessage() /*-{
        return this["ns1.updateSubjectGroupsResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Gets the result as string.
	 * 
	 * @return the result as string
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateSubjectGroupsResponse#isSuccess()
	 */
    public final native String getResultAsString() /*-{
        return this["ns1.updateSubjectGroupsResponse"]["ns1.success"];
    }-*/;

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateSubjectGroupsResponse#isErrored()
	 */
    public final native boolean isErrored() /*-{
        if (this["ns1.updateSubjectGroupsResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;

}
