/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectGroupsResponse;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * CreateSubjectGroupsResponseJS.
 */
public class CreateSubjectGroupsResponseJS  extends JavaScriptObject implements
        CreateSubjectGroupsResponse {

    /** The Constant NAME. */
    public static final String NAME = "ns1.createSubjectGroupsResponse";
    
    
    /**
	 * Instantiates a new creates the subject groups response js.
	 */
    protected CreateSubjectGroupsResponseJS () {
    }

    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the creates the subject groups response
	 */
    public static final native CreateSubjectGroupsResponse fromJSON (String json) /*-{
        try {
            return eval('(' + json + ')');
        } catch (err) {
            return null;
        }
    }-*/;
    

    /**
	 * Gets the subject group ids.
	 * 
	 * @return the subject group ids
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectGroupsResponse#getSubjectGroupIds()
	 */
    public final List<Long> getSubjectGroupIds() {
        JsArrayString idsAsStrings = getSubjectGroupIdsAsStrings();
        List<Long> results = new ArrayList<Long>();
        if (idsAsStrings != null) {
            for (int i=0;i<idsAsStrings.length();i++) {
                String s = idsAsStrings.get(i);
                results.add(Long.valueOf(s));
            }
        }
        return results;
    };

    
    
    /**
	 * Gets the subject group ids as strings.
	 * 
	 * @return the subject group ids as strings
	 */
    public final native JsArrayString getSubjectGroupIdsAsStrings () /*-{
        return this["ns1.createSubjectGroupsResponse"]["ns1.subjectGroupIds"];
    }-*/;
    
    /**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.BaseResponse#getErrorMessage()
	 */
    public final native String getErrorMessage() /*-{
        return this["ns1.createSubjectGroupsResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.BaseResponse#isErrored()
	 */
    public final native boolean isErrored() /*-{
        if (this["ns1.createSubjectGroupsResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;

    
    
}
