/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * The Class OperationImpl.
 */
public class OperationImpl implements Operation {

	private String description;
	private String operationName;
	private String operationId;
	private long creationDate;
	private String creationBy;
	private long lastModifiedTime;
	private String lastModifiedBy;

	/**
	 * Sets the ceation by.
	 * 
	 * @param creationBy
	 *            the new ceation by
	 */
	public void setCeationBy(String creationBy) {
		this.creationBy = creationBy;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the operation name.
	 * 
	 * @param operationName
	 *            the new operation name
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	/**
	 * Sets the operation id.
	 * 
	 * @param operationId
	 *            the new operation id
	 */
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	/**
	 * Sets the creation date.
	 * 
	 * @param creationDate
	 *            the new creation date
	 */
	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Sets the last modified time.
	 * 
	 * @param lastModifiedTime
	 *            the new last modified time
	 */
	public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 * Sets the last modified by.
	 * 
	 * @param lastModifiedBy
	 *            the new last modified by
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getOperationName()
	 */
	@Override
	public String getOperationName() {
		return operationName;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getOperationId()
	 */
	@Override
	public String getOperationId() {
		return operationId;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getCreationDate()
	 */
	@Override
	public long getCreationDate() {

		return creationDate;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getCreationBy()
	 */
	@Override
	public String getCreationBy() {
		return creationBy;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getLastModifiedTime()
	 */
	@Override
	public long getLastModifiedTime() {
		return lastModifiedTime;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getLastModifiedBy()
	 */
	@Override
	public String getLastModifiedBy() {

		return lastModifiedBy;
	}

}
