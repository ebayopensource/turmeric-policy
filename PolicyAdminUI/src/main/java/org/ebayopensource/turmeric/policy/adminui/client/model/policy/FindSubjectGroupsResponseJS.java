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

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * FindSubjectGroupsReponseJS.
 */
public class FindSubjectGroupsResponseJS extends JavaScriptObject implements
        FindSubjectGroupsResponse {
    
    /** The Constant NAME. */
    public final static String NAME = "ns1.findSubjectGroupsResponse";
    
    /**
	 * Instantiates a new find subject groups response js.
	 */
    protected FindSubjectGroupsResponseJS() {}
    
    
    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the find subject groups response
	 */
    public static final native FindSubjectGroupsResponse fromJSON(String json) /*-{
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse#getErrorMessage()
	 */
    public final native String getErrorMessage() /*-{
        return this["ns1.findSubjectGroupsResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Gets the groups as array.
	 * 
	 * @return the groups as array
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse#getGroups()
	 */
    public final native JsArray<SubjectGroupJS> getGroupsAsArray() /*-{
        return this["ns1.findSubjectGroupsResponse"]["ns1.subjectGroups"];
    }-*/;

    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse#getGroups()
     */
    public final List<SubjectGroup> getGroups() {
        List<SubjectGroup> results = new ArrayList<SubjectGroup>();
        JsArray<SubjectGroupJS> groups = getGroupsAsArray();
        if (groups != null) {
            for (int i=0;i<groups.length();i++)
                results.add(groups.get(i));
        }
        return results;
    }
    
    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse#isErrored()
	 */
    public final native boolean isErrored() /*-{
        if (this["ns1.findSubjectGroupsResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;

}
