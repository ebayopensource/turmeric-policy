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
import java.util.Date;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.util.SubjectUtil;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;


/**
 * SubjectGroupJS.
 */
public class SubjectGroupJS extends JavaScriptObject implements SubjectGroup {

    /**
	 * Instantiates a new subject group js.
	 */
    protected SubjectGroupJS () {}
    
   
    /**
	 * Gets the created by.
	 * 
	 * @return the created by
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getCreatedBy()
	 */
    public native final String getCreatedBy() /*-{
        return this["@CreatedBy"];
    }-*/;

    /**
	 * Gets the description.
	 * 
	 * @return the description
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getDescription()
	 */
    public native final String getDescription() /*-{
        return this["@Description"];
    }-*/;

    /**
	 * Gets the last modified by.
	 * 
	 * @return the last modified by
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getLastModifiedBy()
	 */
    public native final String getLastModifiedBy() /*-{
        return this["@LastModifiedBy"];
    }-*/;
    

    /**
	 * Gets the last modified time.
	 * 
	 * @return the last modified time
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getLastModifiedTime()
	 */
    @Override
    public final Date getLastModifiedTime() {

        String tmp = getLastModifiedAsString();
        if (tmp == null)
            return null;
        try {
            return PolicyAdminUIUtil.xsDateTimeFormat.parse(tmp);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    
    /**
	 * Gets the last modified as string.
	 * 
	 * @return the last modified as string
	 */
    public native final String getLastModifiedAsString() /*-{
        return this["@LastUpdatedDate"];
    }-*/;
    
    /**
	 * Gets the name.
	 * 
	 * @return the name
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getName()
	 */
    public native final String getName() /*-{
        return this["@SubjectGroupName"];
    }-*/;

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getPolicies()
     */
    @Override
    public final List<String> getPolicies() {
        return null;
    }

    /**
	 * Gets the subjects.
	 * 
	 * @return the subjects
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getSubjects()
	 */
    public final List<String> getSubjects() {
        List<String> strings = new ArrayList<String>();
        JsArray<SubjectJS> subjects = getSubjectsAsObjects();
        if (subjects != null) {
            for (int i=0; i< subjects.length(); i++)
                strings.add(subjects.get(i).getName());
        }
        return strings;
    }
    
    private native final JsArray<SubjectJS> getSubjectsAsObjects () /*-{
        return this["ns1.Subject"];
    }-*/;

//    @Override
//	public final SubjectMatchType getSubjectMatchType(){
//		return getSubjectMatchAsObject();
//	}; 
//    
//	private final native SubjectMatchTypeJS getSubjectMatchAsObject() /*-{
//		return this["ns2.SubjectMatch"];
//	}-*/;

    /* (non-Javadoc)
 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getSubjectMatchTypes()
 */
@Override
    public final List<SubjectMatchType> getSubjectMatchTypes() {
        List<SubjectMatchType> results = new ArrayList<SubjectMatchType>();
        JsArray<SubjectMatchTypeJS> subjectMatchs = getSubjectMatchAsArray();
        if (subjectMatchs != null) {
            for (int i=0;i<subjectMatchs.length();i++)
                results.add(subjectMatchs.get(i));
        }
        return results;
    }
	
	private final native JsArray<SubjectMatchTypeJS> getSubjectMatchAsArray() /*-{
		return this["ns2.SubjectMatch"]
	}-*/;
	
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getType()
     */
    public native final String getType() /*-{
        return this["@SubjectType"];
    }-*/;

    
	/**
	 * Gets the id from subject match as string.
	 * 
	 * @return the id from subject match as string
	 */
	public final String getIdFromSubjectMatchAsString () {
		    
	        JsArray<SubjectMatchTypeJS> array =  getSubjectMatchAsArray();
	        if (array == null)
	            return null;
	        
	        if (array.length() == 0)
	            return null;
	        
	        SubjectMatchTypeJS element = array.get(0);
	        SubjectAttributeDesignatorJS des = element.getSubjectAttributeDesignatorAsObject();
	        String attId = des.getAttributeId();
	        if ("urn:oasis:names:tc:xacml:1.0:subject:subject-id".equals(attId)) {
	            AttributeValueJS attVal = element.getAttributeValueAsObject();
	            return attVal.getValue();
	        } else
	            return null;
	}
//        public final String getIdFromSubjectMatchAsString () {
//    
//        SubjectMatchTypeJS subjectMatch =  getSubjectMatchAsArray();
//        if (subjectMatch == null){
//            return null;
//        }
//       
//        SubjectAttributeDesignatorJS des = subjectMatch.getSubjectAttributeDesignatorAsObject();
//        String attId = des.getAttributeId();
//        if ("urn:oasis:names:tc:xacml:1.0:subject:subject-id".equals(attId)) {
//            AttributeValueJS attVal = subjectMatch.getAttributeValueAsObject();
//            return attVal.getValue();
//        } else
//            return null;
//    }
//    
    /* (non-Javadoc)
 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getId()
 */
@Override
    public final Long getId() {
        return SubjectUtil.getSubjectGroupId(this);
    }


    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getGroupCalculator()
     */
    @Override
    public native final String getGroupCalculator()  /*-{
        return this["@SubjectGroupCalculator"];
    }-*/;

    
}
