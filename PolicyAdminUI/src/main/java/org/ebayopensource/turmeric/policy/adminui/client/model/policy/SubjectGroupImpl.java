/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * SubjectGroupImpl.
 */
public class SubjectGroupImpl implements SubjectGroup {
	private String name;
    private String type;
    private String groupCalculator;
    private String description;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private String createdBy;
    private List<String> subjects;
    private List<String> policies;
//    private SubjectMatchType subjectMatch;
    private List<SubjectMatchType> subjectMatchTypes;
    private Long id;

    
    /**
	 * Instantiates a new subject group impl.
	 */
    public SubjectGroupImpl () {}
    
    /**
	 * Instantiates a new subject group impl.
	 * 
	 * @param g
	 *            the g
	 */
    public SubjectGroupImpl (SubjectGroup g) {
        if (g == null)
            return;
        this.id = g.getId();
        this.name = g.getName();
        this.type = g.getType();
        this.description = g.getDescription();
        this.lastModifiedBy = g.getLastModifiedBy();
        this.lastModifiedTime = g.getLastModifiedTime();
        this.createdBy = g.getCreatedBy();
        this.subjects = (g.getSubjects()==null?null:new ArrayList(g.getSubjects()));
        this.policies = (g.getPolicies()==null?null:new ArrayList(g.getPolicies()));
        this.subjectMatchTypes = g.getSubjectMatchTypes();
        this.groupCalculator = g.getGroupCalculator();
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getLastModifiedBy()
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
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
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getLastModifiedTime()
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }
    
    /**
	 * Sets the last modified time.
	 * 
	 * @param lastModifiedTime
	 *            the new last modified time
	 */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getCreatedBy()
     */
    public String getCreatedBy() {
        return createdBy;
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
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getSubjects()
     */
    public List<String> getSubjects() {
        return subjects;
    } 
    
    /**
	 * Sets the subjects.
	 * 
	 * @param subjects
	 *            the new subjects
	 */
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getPolicies()
     */
    public List<String> getPolicies() {
        return policies;
    }
    
    /**
	 * Sets the policies.
	 * 
	 * @param policies
	 *            the new policies
	 */
    public void setPolicies(List<String> policies) {
        this.policies = policies;
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getName()
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
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getType()
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
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getGroupCalculator()
     */
    public String getGroupCalculator() {
        return groupCalculator;
    }
    
    /**
	 * Sets the group calculator.
	 * 
	 * @param groupCalculator
	 *            the new group calculator
	 */
    public void setGroupCalculator(String groupCalculator) {
        this.groupCalculator = groupCalculator;
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getDescription()
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
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getId()
     */
    public Long getId() {
        return id;
    }
  
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals (Object o) {
        if (!(o instanceof SubjectGroup))
            return false;
        
      
        
        if (name == null || type==null)
            return false;
       
        return (name.equals(((SubjectGroup)o).getName()) && type.equals(((SubjectGroup)o).getType()));
    }

	

//	public void setSubjectMatchType(SubjectMatchType subjectMatch) {
//		this.subjectMatch = subjectMatch;
//	} 
//
//	@Override
//	public SubjectMatchType getSubjectMatchType() {
//		return subjectMatch;
//	}

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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup#getSubjectMatchTypes()
	 */
	public List<SubjectMatchType> getSubjectMatchTypes() {
		return subjectMatchTypes;
	}
}
