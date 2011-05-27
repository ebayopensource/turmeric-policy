/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.List;

/**
 * PolicyResourceAssignment.
 */
public class PolicyResourceAssignment {
    
    private String resourceType;
    private String resourceName;
    private List<Operation> opList;
	
	/**
	 * Gets the resource type.
	 * 
	 * @return the resource type
	 */
	public String getResourceType() {
		return resourceType;
	}
	
	/**
	 * Sets the resource type.
	 * 
	 * @param resourceType
	 *            the new resource type
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	/**
	 * Gets the resource name.
	 * 
	 * @return the resource name
	 */
	public String getResourceName() {
		return resourceName;
	}
	
	/**
	 * Sets the resource name.
	 * 
	 * @param resourceName
	 *            the new resource name
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	/**
	 * Gets the op list.
	 * 
	 * @return the op list
	 */
	public List<Operation> getOpList() {
		return opList;
	}
	
	/**
	 * Sets the op list.
	 * 
	 * @param opList
	 *            the new op list
	 */
	public void setOpList(List<Operation> opList) {
		this.opList = opList;
	}

}
