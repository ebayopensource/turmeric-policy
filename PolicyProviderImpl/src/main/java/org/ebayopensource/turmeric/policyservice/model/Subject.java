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

import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class Subject.
 */
@Entity
public class Subject extends AuditablePersistent {
    private String subjectName;
    private String subjectType;
    private String description;
    private String ipMask;
    private long externalSubjectId;
    private String emailContact;

    /**
	 * Instantiates a new subject.
	 */
    public Subject() {}
    
    /**
	 * Instantiates a new subject.
	 * 
	 * @param subjectName
	 *            the subject name
	 * @param subjectType
	 *            the subject type
	 * @param description
	 *            the description
	 * @param ipMask
	 *            the ip mask
	 * @param externalSubjectId
	 *            the external subject id
	 * @param emailContact
	 *            the email contact
	 */
    public Subject(String subjectName,
                   String subjectType,
                   String description,
                   String ipMask,
                   long externalSubjectId,
                   String emailContact) {
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.description = description;
        this.ipMask = ipMask;
        this.externalSubjectId = externalSubjectId;
        this.emailContact = emailContact;
    }

    /**
	 * Gets the subject name.
	 * 
	 * @return the subject name
	 */
    public String getSubjectName() {
        return subjectName;
    }

    /**
	 * Sets the subject name.
	 * 
	 * @param subjectName
	 *            the new subject name
	 */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
	 * Gets the subject type.
	 * 
	 * @return the subject type
	 */
    public String getSubjectType() {
        return subjectType;
    }

    /**
	 * Sets the subject type.
	 * 
	 * @param subjectType
	 *            the new subject type
	 */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
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
    public void setDescription(String description) {
        this.description = description;
    }

    /**
	 * Gets the ip mask.
	 * 
	 * @return the ip mask
	 */
    public String getIpMask() {
        return ipMask;
    }

    /**
	 * Sets the ip mask.
	 * 
	 * @param ipMask
	 *            the new ip mask
	 */
    public void setIpMask(String ipMask) {
        this.ipMask = ipMask;
    }

    /**
	 * Gets the external subject id.
	 * 
	 * @return the external subject id
	 */
    public long getExternalSubjectId() {
        return externalSubjectId;
    }

    /**
	 * Sets the external subject id.
	 * 
	 * @param externalSubjectId
	 *            the new external subject id
	 */
    public void setExternalSubjectId(long externalSubjectId) {
        this.externalSubjectId = externalSubjectId;
    }

    /**
	 * Gets the email contact.
	 * 
	 * @return the email contact
	 */
    public String getEmailContact() {
        return emailContact;
    }

    /**
	 * Sets the email contact.
	 * 
	 * @param emailContact
	 *            the new email contact
	 */
    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }
}
