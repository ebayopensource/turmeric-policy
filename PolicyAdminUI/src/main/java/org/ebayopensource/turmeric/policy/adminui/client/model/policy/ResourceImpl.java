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
 * ResourceImpl.
 */
public class ResourceImpl implements Resource {

	private Long id;
	private String resourceType;
	private String resourceName;
	private String description;


	private List<Operation> operations;

	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getResourceType()
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
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getOpList()
	 */
	@Override
	public List<Operation> getOpList() {
		return operations;
	}

	/**
	 * Sets the op list.
	 * 
	 * @param operations
	 *            the new op list
	 */
	public void setOpList(List<Operation> operations) {
		this.operations = operations;
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

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getResourceName()
	 */
	public String getResourceName() {
		return resourceName;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getId()
	 */
	@Override
	public Long getId() {

		return id;
	}
	
	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param desc
	 *            the new description
	 */
	public void setDescription(String desc) {
		this.description = desc;
	}
	
}
