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

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class SubjectGroup.
 */
@Entity
public class SubjectGroup extends AuditablePersistent {
    @ManyToMany
    private List<Subject> subjects;
    private String subjectGroupName;
    private String subjectType;
    private String subjectGroupCalculator;
    private boolean applyToEach;
    private boolean applyToAll;
    private String description;

    /**
	 * Instantiates a new subject group.
	 */
    protected SubjectGroup() { }

    /**
	 * Instantiates a new subject group.
	 * 
	 * @param subjectGroupName
	 *            the subject group name
	 * @param subjectType
	 *            the subject type
	 * @param subjectGroupCalculator
	 *            the subject group calculator
	 * @param applyToEach
	 *            the apply to each
	 * @param applyToAll
	 *            the apply to all
	 * @param description
	 *            the description
	 */
    public SubjectGroup(String subjectGroupName,
                        String subjectType,
                        String subjectGroupCalculator,
                        boolean applyToEach,
                        boolean applyToAll,
                        String description) {
        this.subjectGroupName = subjectGroupName;
        this.subjectType = subjectType;
        this.subjectGroupCalculator = subjectGroupCalculator;
        this.applyToEach = applyToEach;
        this.applyToAll = applyToAll;
        this.description = description;
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
	 * Sets the subjects.
	 * 
	 * @param subjects
	 *            the new subjects
	 */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    /**
	 * Gets the subject group name.
	 * 
	 * @return the subject group name
	 */
    public String getSubjectGroupName() {
        return subjectGroupName;
    }

    /**
	 * Sets the subject group name.
	 * 
	 * @param subjectGroupName
	 *            the new subject group name
	 */
    public void setSubjectGroupName(String subjectGroupName) {
        this.subjectGroupName = subjectGroupName;
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
	 * Gets the subject group calculator.
	 * 
	 * @return the subject group calculator
	 */
    public String getSubjectGroupCalculator() {
        return subjectGroupCalculator;
    }

    /**
	 * Sets the subject group calculator.
	 * 
	 * @param subjectGroupCalculator
	 *            the new subject group calculator
	 */
    public void setSubjectGroupCalculator(String subjectGroupCalculator) {
        this.subjectGroupCalculator = subjectGroupCalculator;
    }

    /**
	 * Gets the apply to each.
	 * 
	 * @return the apply to each
	 */
    public boolean getApplyToEach() {
        return applyToEach;
    }

    /**
	 * Sets the apply to each.
	 * 
	 * @param applyToEach
	 *            the new apply to each
	 */
    public void setApplyToEach(boolean applyToEach) {
        this.applyToEach = applyToEach;
    }

    /**
	 * Gets the apply to all.
	 * 
	 * @return the apply to all
	 */
    public boolean getApplyToAll() {
        return applyToAll;
    }

    /**
	 * Sets the apply to all.
	 * 
	 * @param applyToAll
	 *            the new apply to all
	 */
    public void setApplyToAll(boolean applyToAll) {
        this.applyToAll = applyToAll;
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

}
