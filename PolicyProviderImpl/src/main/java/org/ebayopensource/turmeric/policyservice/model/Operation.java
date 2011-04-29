/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package org.ebayopensource.turmeric.policyservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class Operation.
 */
@Entity
public class Operation extends AuditablePersistent {
 
	private String operationName;
    private String description;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="resource_id" )
    private Resource resource;


	/**
	 * Instantiates a new operation.
	 */
	protected Operation(){
	}
	
	/**
	 * Instantiates a new operation.
	 * 
	 * @param operationName
	 *            the operation name
	 * @param description
	 *            the description
	 */
	public Operation( final String operationName, final String description) {
		this.operationName = operationName;
		this.description = description;
	}

    
	/**
	 * Gets the operation name.
	 * 
	 * @return the operation name
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * Sets the operation name.
	 * 
	 * @param operationName
	 *            the new operation name
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Sets the resource.
	 * 
	 * @param resource
	 *            the new resource
	 */
	public void setResource(final Resource resource) {
		this.resource = resource;
		if(!resource.getOperations().contains(this)){
			resource.getOperations().add(this);
		}
	}

	/**
	 * Gets the resource.
	 * 
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	

}
