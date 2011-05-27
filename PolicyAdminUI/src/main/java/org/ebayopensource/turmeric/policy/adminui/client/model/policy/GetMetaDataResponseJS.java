/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.HashMap;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetMetaDataResponse;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * GetMetaDataResponseJS.
 */
public class GetMetaDataResponseJS extends JavaScriptObject implements
        GetMetaDataResponse {
    
    /** The Constant NAME. */
    public static final String NAME = "ns1.getMetaDataResponse";
    
    
    /**
	 * The Class KeyValuePairJS.
	 */
    public static class KeyValuePairJS extends JavaScriptObject {
        
        /**
		 * Instantiates a new key value pair js.
		 */
        protected KeyValuePairJS() {}
        
        /**
		 * Gets the key.
		 * 
		 * @return the key
		 */
        public final native String getKey() /*-{
            return this["ns1.key"];
        }-*/;
        
        /**
		 * Gets the value.
		 * 
		 * @return the value
		 */
        public final native String getValue() /*-{
            return this["ns1.value"];
        }-*/;
    }

    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the gets the meta data response
	 */
    public static final native GetMetaDataResponse fromJSON (String json) /*-{
        try {
            return eval('(' + json + ')');
        } catch (err) {
        return null;
        }    
    }-*/;

    
    /**
	 * Instantiates a new gets the meta data response js.
	 */
    protected GetMetaDataResponseJS () {}
    
    
    /**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetMetaDataResponse#getErrorMessage()
	 */
    @Override
    public final native String getErrorMessage() /*-{
        return this["ns1.getMetaDataResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Gets the values.
	 * 
	 * @return the values
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetMetaDataResponse#getValues()
	 */
    @Override
    public final Map<String, String> getValues() {
        Map<String,String> map = new HashMap<String,String>();
        JsArray<KeyValuePairJS> pairs = getKeyValuePairs();
        if (pairs != null) {
            for (int i=0;i<pairs.length();i++) {
                map.put(pairs.get(i).getKey(), pairs.get(i).getValue());
            }
        }
        return map;
    }
    
    
    /**
	 * Gets the key value pairs.
	 * 
	 * @return the key value pairs
	 */
    public native final JsArray<KeyValuePairJS> getKeyValuePairs () /*-{
        return this["ns1.getMetaDataResponse"]["ns1.metadataValue"];
    }-*/;

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetMetaDataResponse#isErrored()
	 */
    @Override
    public final native boolean isErrored() /*-{
           if (this["ns1.getMetaDataResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;

}
