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

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectsResponse;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * CreateSubjectsResponseJS.
 * 
 * @author jose
 */
public class CreateSubjectsResponseJS extends JavaScriptObject implements
		CreateSubjectsResponse {

	/** The Constant NAME. */
	public static final String NAME = "ns1.createSubjectsResponse";

	/**
	 * Instantiates a new creates the subjects response js.
	 */
	protected CreateSubjectsResponseJS() {
	}

	/**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the creates the subjects response
	 */
	public static final native CreateSubjectsResponse fromJSON(String json) /*-{
																					try {
																					return eval('(' + json + ')');
																					} catch (err) {
																					return null;
																					}
																					}-*/;

	/**
	 * Gets the subject ids.
	 * 
	 * @return the subject ids
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectsResponse#getSubjectIds()
	 */
	public final List<Long> getSubjectIds() {
		JsArrayString idsAsStrings = getSubjectIdsAsStrings();
		List<Long> results = new ArrayList<Long>();
		if (idsAsStrings != null) {
			for (int i = 0; i < idsAsStrings.length(); i++) {
				String s = idsAsStrings.get(i);
				results.add(Long.valueOf(s));
			}
		}
		return results;
	};

	/**
	 * Gets the subject ids as strings.
	 * 
	 * @return the subject ids as strings
	 */
	public final native JsArrayString getSubjectIdsAsStrings() /*-{
																	return this["ns1.createSubjectsResponse"]["ns1.subjectIds"];
																	}-*/;

	/**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.BaseResponse#getErrorMessage()
	 */
	public final native String getErrorMessage() /*-{
													return this["ns1.createSubjectsResponse"]["ms.errorMessage"];
													}-*/;

	/**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.BaseResponse#isErrored()
	 */
	public final native boolean isErrored() /*-{
											if (this["ns1.createSubjectsResponse"]["ms.ack"] === "Success")
											return false;
											else
											return true;
											}-*/;

}
