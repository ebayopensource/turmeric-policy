/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 ******************************************************************************/
package org.ebayopensource.turmeric.policyservice.model;

import javax.persistence.Entity;

import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class BasicAuth.
 * 
 * @author jose
 */
@Entity
public class BasicAuth extends AuditablePersistent{
    private String subjectName;
    private String password;
    
    /**
	 * Instantiates a new basic auth.
	 */
    public BasicAuth() {}

    /**
	 * Instantiates a new basic auth.
	 * 
	 * @param subjectName
	 *            the subject name
	 * @param password
	 *            the password
	 */
    public BasicAuth(String subjectName, String password) {
        this.subjectName = subjectName;
        this.password = password;
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
	 * Gets the password.
	 * 
	 * @return the password
	 */
    public String getPassword() {
        return password;
    }
    
    /**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
    public void setPassword(String password) {
        this.password = password;
    }
}
