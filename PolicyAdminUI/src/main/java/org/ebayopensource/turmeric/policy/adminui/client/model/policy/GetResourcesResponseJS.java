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
import java.util.Collection;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetResourcesResponse;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * GetResourcesResponseJS.
 */
public class GetResourcesResponseJS extends JavaScriptObject implements
        GetResourcesResponse {

    /** The Constant NAME. */
    public static final String NAME = "ns1.getResourcesResponse";
    
    /**
	 * ResourceJS.
	 */
    public static class ResourceJS extends JavaScriptObject implements Resource {

        /**
		 * Instantiates a new resource js.
		 */
        protected ResourceJS() {}
        
        /**
		 * Gets the description.
		 * 
		 * @return the description
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getDescription()
		 */
        @Override
        public final native String getDescription() /*-{
            return this["@Description"];
        }-*/;

        /**
		 * Gets the id as string.
		 * 
		 * @return the id as string
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getId()
		 */
       
        public final native String getIdAsString() /*-{
            return this["@ResourceId"];
        }-*/;
        
        /* (non-Javadoc)
         * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getId()
         */
        @Override
        public final Long getId() {
            return Long.valueOf(getIdAsString());
        }
        
    
        /**
		 * Gets the op list.
		 * 
		 * @return the op list
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getOpList()
		 */
        @Override
		public final List<Operation> getOpList() {
			List<Operation> operations = new ArrayList<Operation>();
			JsArray<OperationJS> jsOperations = getOperationsArray();
			if (jsOperations != null) {
			    for (int i=0;i<jsOperations.length();i++)
			        operations.add(jsOperations.get(i));
			}
			return operations;
		};
		
		/**
		 * Gets the operations array.
		 * 
		 * @return the operations array
		 */
		public final native JsArray<OperationJS> getOperationsArray () /*-{
	    	return this["ns1.Operation"];
	    }-*/;
	    
		/**
		 * Gets the resource name.
		 * 
		 * @return the resource name
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getResourceName()
		 */
        @Override
        public final native String getResourceName() /*-{
            return this["@ResourceName"];
        }-*/;

        /**
		 * Gets the resource type.
		 * 
		 * @return the resource type
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getResourceType()
		 */
        @Override
        public final native String getResourceType() /*-{
            return this["@ResourceType"];
        }-*/;
    }
    
    
    /**
	 * Instantiates a new gets the resources response js.
	 */
    protected GetResourcesResponseJS () {}
    
    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the gets the resources response
	 */
    public static final native GetResourcesResponse fromJSON (String json) /*-{
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetResourcesResponse#getErrorMessage()
	 */
    public native final String getErrorMessage() /*-{
        return this["ns1.getResourcesResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Gets the resources.
	 * 
	 * @return the resources
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetResourcesResponse#getResources()
	 */
    public final Collection<Resource> getResources() {
        List<Resource> resources = new ArrayList<Resource>();
        JsArray<ResourceJS> jsResources = getResourcesArray();
        if (jsResources != null) {
            for (int i=0; i<jsResources.length();i++)
                resources.add(jsResources.get(i));
        }
        return resources;
    }
    
    
    /**
	 * Gets the resources array.
	 * 
	 * @return the resources array
	 */
    public native final JsArray<ResourceJS> getResourcesArray () /*-{
        return this["ns1.getResourcesResponse"]["ns1.resources"];
    }-*/; 

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetResourcesResponse#isErrored()
	 */
    public native final boolean isErrored() /*-{
        if (this["ns1.getResourcesResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;

}
