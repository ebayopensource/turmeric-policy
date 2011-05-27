/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.List;

/**
 * SubjectImpl.
 */
public class SubjectImpl implements Subject {
    private String type;
    private String name;
    private String createdBy;
    private long externalSubjectId;
    private List<SubjectMatchType> subjectMatchTypes;

    private long lastModifiedTime;
    private String lastModifiedBy;
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject#getType()
     */
    public String getType() {
        return type;
    }
    
    /**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
    public void setType(String type) {
        this.type = type;
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject#getName()
     */
    public String getName() {
        return name;
    }
    
    /**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
	 * Sets the created by.
	 * 
	 * @param createdBy
	 *            the new created by
	 */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject#getCreatedBy()
     */
    public String getCreatedBy() {
        return createdBy;
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
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject#getLastModifiedTime()
     */
    public long getLastModifiedTime() {
        return lastModifiedTime;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals (Object o) {
        if (!(o instanceof Subject))
            return false;
        
        if (name == null || type==null)
            return false;
       
        return (name.equals(((Subject)o).getName()) && type.equals(((Subject)o).getType()));
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
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject#getLastModifiedBy()
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
    
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject#getExternalSubjectId()
	 */
	@Override
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
	 * Sets the subject match.
	 * 
	 * @param subjectMatchType
	 *            the new subject match
	 */
	public void setSubjectMatch(List<SubjectMatchType> subjectMatchType) {
		this.subjectMatchTypes = subjectMatchType;

	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject#getSubjectMatchTypes()
	 */
	public List<SubjectMatchType> getSubjectMatchTypes() {
		return subjectMatchTypes;
	}
}
