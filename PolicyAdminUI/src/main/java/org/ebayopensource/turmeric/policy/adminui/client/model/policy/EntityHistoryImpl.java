/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.Date;

/**
 * The Class EntityHistoryImpl.
 */
public class EntityHistoryImpl implements EntityHistory {

	private String comments;
	private String loginSubject;
	private String auditType;
	private Date lastModifiedTime;



	/**
	 * Sets the last modified time.
	 * 
	 * @param lastModifiedTime
	 *            the new last modified time
	 */
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 * Sets the comments.
	 * 
	 * @param comments
	 *            the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Sets the login subject.
	 * 
	 * @param loginSubject
	 *            the new login subject
	 */
	public void setLoginSubject(String loginSubject) {
		this.loginSubject = loginSubject;
	}

	/**
	 * Sets the audit type.
	 * 
	 * @param auditType
	 *            the new audit type
	 */
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory#getComments()
	 */
	@Override
	public String getComments() {
		return comments;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory#getLoginSubject()
	 */
	@Override
	public String getLoginSubject() {
		return loginSubject;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory#getAuditType()
	 */
	@Override
	public String getAuditType() {
		return auditType;
	}

	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory#getLastModifiedTime()
	 */
	@Override
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}


	

}
