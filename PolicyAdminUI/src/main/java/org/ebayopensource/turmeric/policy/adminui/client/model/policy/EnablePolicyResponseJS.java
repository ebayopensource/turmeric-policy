/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.EnablePolicyResponse;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * EnablePolicyResponseJS.
 */
public class EnablePolicyResponseJS extends JavaScriptObject implements
        EnablePolicyResponse {

    /** The Constant NAME. */
    public static final String NAME = "ns1.enablePolicyResponse";
    
    /**
	 * Instantiates a new enable policy response js.
	 */
    protected EnablePolicyResponseJS () {}
    
    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the enable policy response
	 */
    public static final native EnablePolicyResponse fromJSON (String json) /*-{
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.BaseResponse#getErrorMessage()
	 */
    public final native String getErrorMessage() /*-{
        return this["ns1.enablePolicyResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.BaseResponse#isErrored()
	 */
    public final native boolean isErrored() /*-{
        if (this["ns1.enablePolicyResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;

}
