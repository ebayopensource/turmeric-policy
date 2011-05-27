/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * DeleteSubjectGroupResponse.
 */
public class DeleteSubjectGroupResponseJS extends JavaScriptObject
        implements
        org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeleteSubjectGroupResponse {
    
    /** The Constant NAME. */
    public static final String NAME = "ns1.deleteSubjectGroupsResponse";

    /**
	 * Instantiates a new delete subject group response js.
	 */
    protected DeleteSubjectGroupResponseJS() {}
    
    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the delete subject group response js
	 */
    public static final native DeleteSubjectGroupResponseJS fromJSON (String json) /*-{
        try {
            return eval('(' + json + ')');
        } catch (err) {
        return null;
            }
    }-*/;
    
    
    /**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeleteSubjectGroupResponse#getErrorMessage()
	 */
    public final native String getErrorMessage() /*-{
        return this["ns1.deleteSubjectGroupsResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeleteSubjectGroupResponse#isErrored()
	 */
    public final native boolean isErrored() /*-{
        if (this["ns1.deleteSubjectGroupsResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
        return false;
    }-*/;

}
