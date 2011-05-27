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
 * SubjectMatchTypeJS.
 */
public class SubjectMatchTypeJS extends JavaScriptObject implements SubjectMatchType {

    /**
	 * Instantiates a new subject match type js.
	 */
    protected SubjectMatchTypeJS() {}
    
    /**
	 * Gets the match id.
	 * 
	 * @return the match id
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectMatchType#getMatchId()
	 */
    @Override
    public native final String getMatchId() /*-{ 
        return this["@MatchId"];
    }-*/;

	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectMatchType#getAttributeValue()
	 */
	@Override
    public final AttributeValue getAttributeValue() {

		return getAttributeValueAsObject();
    }
    
	
    /**
	 * Gets the attribute value as object.
	 * 
	 * @return the attribute value as object
	 */
    public final native AttributeValueJS getAttributeValueAsObject() /*-{
        return this["ns2.AttributeValue"];
	}-*/;

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectMatchType#getSubjectAttributeDesignator()
     */
    @Override
    public final SubjectAttributeDesignator getSubjectAttributeDesignator() {
        return getSubjectAttributeDesignatorAsObject();
    }
    
    /**
	 * Gets the subject attribute designator as object.
	 * 
	 * @return the subject attribute designator as object
	 */
    public final native SubjectAttributeDesignatorJS getSubjectAttributeDesignatorAsObject() /*-{
        return this["ns2.SubjectAttributeDesignator"];
	}-*/;

   
}
