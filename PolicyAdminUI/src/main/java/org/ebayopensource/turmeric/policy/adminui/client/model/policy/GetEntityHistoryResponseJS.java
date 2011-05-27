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
import java.util.Date;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetEntityHistoryResponse;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * GetEntityHistoryResponseJS.
 */
public class GetEntityHistoryResponseJS extends JavaScriptObject implements
        GetEntityHistoryResponse{

	/** The NAME. */
	protected static String NAME= "ns1.getEntityHistoryResponse";
 
	/**
	 * Instantiates a new gets the entity history response js.
	 */
	protected GetEntityHistoryResponseJS() {}

    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the gets the entity history response js
	 */
    public static final native GetEntityHistoryResponseJS fromJSON (String json) /*-{
        try {
            return eval('(' + json + ')');
        } catch (err) {
        return null;
        }    
    }-*/;
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetEntityHistoryResponse#getEntities()
     */
    @Override
	public final Collection<EntityHistory> getEntities() {
        List<EntityHistory> entities= new ArrayList<EntityHistory>();
        JsArray<EntityHistoryJS> jsEntities= getEntityArray();
        if (jsEntities != null) {
            for (int i=0; i<jsEntities.length();i++)
                entities.add(jsEntities.get(i));
        }
        return entities;
    }
    
    /**
	 * Gets the entity array.
	 * 
	 * @return the entity array
	 */
    public native final JsArray<EntityHistoryJS> getEntityArray () /*-{
        if (this["ns1.getEntityHistoryResponse"]["ns1.entityHistories"])
            return this["ns1.getEntityHistoryResponse"]["ns1.entityHistories"];
        else
            return null;
    }-*/; 

    
	
    /**
	 * The Class EntityHistorySetJS.
	 */
    public static class EntityHistorySetJS  extends JavaScriptObject {
        
        /**
		 * Instantiates a new entity history set js.
		 */
        protected EntityHistorySetJS () {}
        
        /**
		 * Gets the entities.
		 * 
		 * @return the entities
		 */
        public final native JsArray<EntityHistoryJS> getEntities() /*-{
           	return this["ns1.entityHistories"]; 
        }-*/;
    }

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetEntityHistoryResponse#isErrored()
	 */
	@Override
	 public native final boolean isErrored() /*-{
    if (this["ns1.getEntityHistoryResponse"]["ms.ack"] === "Success")
        return false;
    else
        return true;
	}-*/;

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetEntityHistoryResponse#getErrorMessage()
	 */
	@Override
	public native final String getErrorMessage() /*-{
    return this["ns1.getEntityHistoryResponse"]["ms.errorMessage"];
	}-*/;


	/**
	 * EntityHistoryJS.
	 */
    public static class EntityHistoryJS extends JavaScriptObject implements EntityHistory {

        /**
		 * Instantiates a new entity history js.
		 */
        protected EntityHistoryJS() {}
       
        /* (non-Javadoc)
         * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory#getComments()
         */
        @Override
        public final native String getComments() /*-{
            return this["ns1.comments"];
        }-*/;
        
        /* (non-Javadoc)
         * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory#getLoginSubject()
         */
        @Override
        public final native String getLoginSubject() /*-{
            return this["ns1.loginSubject"];
        }-*/;

        /**
		 * Gets the last modified time as string.
		 * 
		 * @return the last modified time as string
		 */
        public final native String getLastModifiedTimeAsString() /*-{
        return this["ns1.auditDate"];
	    }-*/;
	    
	    /* (non-Javadoc)
    	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory#getLastModifiedTime()
    	 */
    	@Override
	    public final Date getLastModifiedTime() {
		    String tmp = getLastModifiedTimeAsString();
		        try {
			        return PolicyAdminUIUtil.xsDateTimeFormat.parse(tmp);
			    } catch (Exception e) {
			        return null;
			}
		    
		}

		
	    
		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory#getAuditType()
		 */
		@Override
		public final native String getAuditType() /*-{
        	return this["ns1.auditType"];
    	}-*/;
		
    
    }

	
	
}
