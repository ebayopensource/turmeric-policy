/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * SubjectMatchTypeImpl.
 */
public class SubjectMatchTypeImpl implements SubjectMatchType {
    private String matchId;
    private AttributeValue attributeValue;
    private SubjectAttributeDesignator subjectAttributeDesignator;
    
	/**
	 * Sets the match id.
	 * 
	 * @param matchId
	 *            the new match id
	 */
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectMatchType#getMatchId()
	 */
	@Override
	public String getMatchId() {
		return matchId;
	}

	/**
	 * Sets the attribute value.
	 * 
	 * @param attributeValue
	 *            the new attribute value
	 */
	public void setAttributeValue(AttributeValue attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectMatchType#getAttributeValue()
	 */
	@Override
	public AttributeValue getAttributeValue() {
		return attributeValue;
	}
	
	/**
	 * Sets the subject attribute designator.
	 * 
	 * @param subjectAttributeDesignator
	 *            the new subject attribute designator
	 */
	public void setSubjectAttributeDesignator(SubjectAttributeDesignator subjectAttributeDesignator) {
		this.subjectAttributeDesignator = subjectAttributeDesignator;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectMatchType#getSubjectAttributeDesignator()
	 */
	@Override
	public SubjectAttributeDesignator getSubjectAttributeDesignator() {
		return subjectAttributeDesignator;
	}
}
