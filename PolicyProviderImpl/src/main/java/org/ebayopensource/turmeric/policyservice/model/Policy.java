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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class Policy.
 */
@Entity
public class Policy extends AuditablePersistent{
    private String policyName;
    private String policyType;
    private String description;
    private boolean active;
    
    @ManyToMany
    private List<SubjectType> subjectTypes;

    @ManyToMany
    private List<Resource> resources;

    @ManyToMany
    private List<Operation> operations;
    
    @ManyToMany
    private List<Subject> subjects;

    @ManyToMany
    private List<SubjectGroup> subjectGroups;

    @ManyToMany
    @JoinTable(name="Policy_ExclusionSubjects")
    private List<Subject> exclusionSubjects;

    @ManyToMany
    @JoinTable(name="Policy_ExclusionSubjectGroups")
    private List<SubjectGroup> exclusionSubjectGroups;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Rule> rules = new ArrayList<Rule>();

	/**
	 * Instantiates a new policy.
	 */
	public Policy() {}
    
    /**
	 * Instantiates a new policy.
	 * 
	 * @param policyName
	 *            the policy name
	 * @param policyType
	 *            the policy type
	 * @param description
	 *            the description
	 */
    public Policy(String policyName, String policyType, String description) {
        this.policyName = policyName;
        this.policyType = policyType;
        this.description = description;
    }
    
    /**
	 * Gets the policy name.
	 * 
	 * @return the policy name
	 */
    public String getPolicyName() {
        return policyName;
    }
    
    /**
	 * Sets the policy name.
	 * 
	 * @param policyName
	 *            the new policy name
	 */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
    
    /**
	 * Gets the policy type.
	 * 
	 * @return the policy type
	 */
    public String getPolicyType() {
        return policyType;
    }
    
    /**
	 * Sets the policy type.
	 * 
	 * @param policyType
	 *            the new policy type
	 */
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
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
	 * Checks if is active.
	 * 
	 * @return true, if is active
	 */
    public boolean isActive() {
        return active;
    }
    
    /**
	 * Sets the active.
	 * 
	 * @param active
	 *            the new active
	 */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
	 * Gets the resources.
	 * 
	 * @return the resources
	 */
    public List<Resource> getResources() {
        if (resources == null) {
            resources = new ArrayList<Resource>();
        }
        return resources;
    }
    
    /**
	 * Gets the operations.
	 * 
	 * @return the operations
	 */
    public List<Operation> getOperations() {
        if (operations == null) {
            operations = new ArrayList<Operation>();
        }
        return operations;
    }
    
    /**
	 * Gets the subjects.
	 * 
	 * @return the subjects
	 */
    public List<Subject> getSubjects() {
        if (subjects == null) {
            subjects = new ArrayList<Subject>();
        }
        return subjects;
    }
    
    /**
	 * Gets the subject types.
	 * 
	 * @return the subject types
	 */
    public List<SubjectType> getSubjectTypes() {
        if (subjectTypes == null) {
            subjectTypes = new ArrayList<SubjectType>();
        }
        return subjectTypes;
    }
    
    /**
	 * Gets the subject groups.
	 * 
	 * @return the subject groups
	 */
    public List<SubjectGroup> getSubjectGroups() {
        if (subjectGroups == null) {
            subjectGroups = new ArrayList<SubjectGroup>();
        }
        return subjectGroups;
    }

    /**
	 * Gets the exclusion subjects.
	 * 
	 * @return the exclusion subjects
	 */
    public List<Subject> getExclusionSubjects() {
        if (exclusionSubjects == null) {
            exclusionSubjects = new ArrayList<Subject>();
        }
        return exclusionSubjects;
    }

    /**
	 * Gets the exclusion subject groups.
	 * 
	 * @return the exclusion subject groups
	 */
    public List<SubjectGroup> getExclusionSubjectGroups() {
        if (exclusionSubjectGroups == null) {
            exclusionSubjectGroups = new ArrayList<SubjectGroup>();
        }
        return exclusionSubjectGroups;
    }
    
    /**
	 * Gets the rules.
	 * 
	 * @return the rules
	 */
    public List<Rule> getRules() {
        if (rules == null) {
            rules = new ArrayList<Rule>();
        }
        return rules;
    }
}
