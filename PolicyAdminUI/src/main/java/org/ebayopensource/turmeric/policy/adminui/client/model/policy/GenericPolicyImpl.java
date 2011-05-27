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
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;

/**
 * The Class GenericPolicyImpl.
 */
public class GenericPolicyImpl implements GenericPolicy {
	private Long id;
	private String name;
	private String description;
	private String type;
	private Date creationDate;
	private String createdBy;
	private Date lastModified;
	private String lastModifiedBy;
	
	private boolean enabled;
	private List<SubjectGroup> subjectGroups;
	private List<SubjectGroup> exclusionSG;

	
	private List<Subject> subjects;
	private List<Subject> exclusionSubjects;
	private List<Resource> resources;
	private List<Rule> rules;

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getId()
	 */
	@SuppressWarnings("unchecked")
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getName()
	 */
	@Override
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getDescription()
	 */
	@Override
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getType()
	 */
	@Override
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getCreatedBy()
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getLastModified()
	 */
	public Date getLastModified() {
		return lastModified;
	}
	
	/**
	 * Sets the last modified.
	 * 
	 * @param lastModified
	 *            the new last modified
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getLastModifiedBy()
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
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getEnabled()
	 */
	public boolean getEnabled() {
		return enabled;
	}
	
	/**
	 * Sets the enabled.
	 * 
	 * @param enabled
	 *            the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getCreationDate()
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Sets the creation date.
	 * 
	 * @param creationDate
	 *            the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getSubjectGroups()
	 */
	public List<SubjectGroup> getSubjectGroups() {
		return subjectGroups;
	}
	
	/**
	 * Sets the subject groups.
	 * 
	 * @param subjectGroups
	 *            the new subject groups
	 */
	public void setSubjectGroups(List<SubjectGroup> subjectGroups) {
		this.subjectGroups = subjectGroups;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getResources()
	 */
	public List<Resource> getResources() {
		return resources;
	}
	
	/**
	 * Sets the resources.
	 * 
	 * @param resources
	 *            the new resources
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
    
    /**
	 * Sets the subjects.
	 * 
	 * @param subjects
	 *            the new subjects
	 */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getSubjects()
     */
    public List<Subject> getSubjects() {
        return subjects;
    }
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getRules()
	 */
	@Override
	public List<Rule> getRules() {
		return rules;
	}
    
    /**
	 * Sets the rules.
	 * 
	 * @param rules
	 *            the new rules
	 */
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getExclusionSubjects()
     */
    public List<Subject> getExclusionSubjects() {
		return exclusionSubjects;
	}
	
	/**
	 * Sets the exclusion subjects.
	 * 
	 * @param exclusionSubjects
	 *            the new exclusion subjects
	 */
	public void setExclusionSubjects(List<Subject> exclusionSubjects) {
		this.exclusionSubjects = exclusionSubjects;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getExclusionSG()
	 */
	public List<SubjectGroup> getExclusionSG() {
		return exclusionSG;
	}
	
	/**
	 * Sets the exclusion sg.
	 * 
	 * @param exclusionSG
	 *            the new exclusion sg
	 */
	public void setExclusionSG(List<SubjectGroup> exclusionSG) {
		this.exclusionSG = exclusionSG;
	}
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals (Object o) {
        if (!(o instanceof GenericPolicy))
            return false;
        
        GenericPolicy p = (GenericPolicy)o;
        
        if (this.id != null && p.getId() != null && this.id.equals(p.getId()))
            return true;
        if (this.type != null && this.type.equals(p.getType())) {
            if (this.name != null && this.name.equals(p.getName()))
                return true;
        }
        
        return false;
    }

}
