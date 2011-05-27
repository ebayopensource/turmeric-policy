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
 * PolicySubjectAssignment.
 */
public class PolicySubjectAssignment {
    
    private String subjectType;
    private List<Subject> subjects;
    private List<Subject> exclusionSubjects;
    private List<SubjectGroup> subjectGroups;
    private List<SubjectGroup> exclusionSubjectGroups;
    

	/**
	 * Gets the exclusion subjects.
	 * 
	 * @return the exclusion subjects
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
	 * Gets the subjects.
	 * 
	 * @return the subjects
	 */
    public List<Subject> getSubjects() {
        return subjects;
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
    
    /**
	 * Gets the subject groups.
	 * 
	 * @return the subject groups
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
    
    /**
	 * Gets the exclusion subject groups.
	 * 
	 * @return the exclusion subject groups
	 */
    public List<SubjectGroup> getExclusionSubjectGroups() {
		return exclusionSubjectGroups;
	}
	
	/**
	 * Sets the exclusion subject groups.
	 * 
	 * @param exclusionSubjectGroups
	 *            the new exclusion subject groups
	 */
	public void setExclusionSubjectGroups(List<SubjectGroup> exclusionSubjectGroups) {
		this.exclusionSubjectGroups = exclusionSubjectGroups;
	}
	
	
}
