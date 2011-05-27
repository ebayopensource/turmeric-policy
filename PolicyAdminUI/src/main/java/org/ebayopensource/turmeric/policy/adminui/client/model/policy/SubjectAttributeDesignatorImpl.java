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
 * SubjectAttributeDesignatorImpl.
 */
public class SubjectAttributeDesignatorImpl implements SubjectAttributeDesignator {
 
	private String attributeId;
    private String dataType;

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectAttributeDesignator#getAttributeId()
	 */
	public String getAttributeId() {
		return attributeId;
	}

	/**
	 * Sets the attribute id.
	 * 
	 * @param attributeId
	 *            the new attribute id
	 */
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectAttributeDesignator#getDataType()
	 */
	@Override
	public String getDataType() {
		return dataType;
	}
		   
	/**
	 * Sets the data type.
	 * 
	 * @param dataType
	 *            the new data type
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
