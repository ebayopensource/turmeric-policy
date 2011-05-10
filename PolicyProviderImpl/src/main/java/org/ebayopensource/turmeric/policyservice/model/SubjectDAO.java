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

import java.util.Date;
import java.util.List;

import org.ebayopensource.turmeric.security.v1.services.SubjectGroupKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;

/**
 * The Interface SubjectDAO.
 */
public interface SubjectDAO {
    
    /**
	 * Persist subject.
	 * 
	 * @param subject
	 *            the subject
	 */
    void persistSubject(Subject subject);

    /**
	 * Persist subject group.
	 * 
	 * @param subjectGroup
	 *            the subject group
	 */
    void persistSubjectGroup(SubjectGroup subjectGroup);

    /**
	 * Removes the subject.
	 * 
	 * @param id
	 *            the id
	 */
    void removeSubject(long id);

    /**
	 * Removes the subject group.
	 * 
	 * @param id
	 *            the id
	 */
    void removeSubjectGroup(long id);

    /**
	 * Find subject by id.
	 * 
	 * @param id
	 *            the id
	 * @return the subject
	 */
    Subject findSubjectById(long id);

    /**
	 * Find subject by name.
	 * 
	 * @param name
	 *            the name
	 * @return the subject
	 */
    Subject findSubjectByName(String name);

	/**
	 * Find external subjects.
	 * 
	 * @return the list
	 */
	List<BasicAuth> findExternalSubjects();
    
	/**
	 * Find external subject by name.
	 * 
	 * @param name
	 *            the name
	 * @return the subject list
	 */
	List<BasicAuth> findExternalSubjectsByName(String name);

    
    /**
	 * Find subject by type.
	 * 
	 * @param type
	 *            the type
	 * @return the list
	 */
    List<Subject> findSubjectByType(String type);

    /**
	 * Find subject group by id.
	 * 
	 * @param id
	 *            the id
	 * @return the subject group
	 */
    SubjectGroup findSubjectGroupById(Long id);

    /**
	 * Find subject group by name.
	 * 
	 * @param name
	 *            the name
	 * @return the subject group
	 */
    SubjectGroup findSubjectGroupByName(String name);

    /**
	 * Find subject group by type.
	 * 
	 * @param type
	 *            the type
	 * @return the list
	 */
    List<SubjectGroup> findSubjectGroupByType(String type);

    /**
	 * Find subject group by subject name.
	 * 
	 * @param name
	 *            the name
	 * @param subjectType
	 *            the subject type
	 * @return the list
	 */
    List<SubjectGroup> findSubjectGroupBySubjectName(String name, String subjectType);

    /**
	 * Find subject type by name.
	 * 
	 * @param subjectType
	 *            the subject type
	 * @return the subject type
	 */
    SubjectType findSubjectTypeByName(String subjectType);

    /**
	 * Find all subject by name.
	 * 
	 * @param name
	 *            the name
	 * @param subjectType
	 *            the subject type
	 * @return the list
	 */
    List<Subject> findAllSubjectByName(String name, String subjectType);

    /**
	 * Find all subject group by name.
	 * 
	 * @param name
	 *            the name
	 * @param subjectType
	 *            the subject type
	 * @return the list
	 */
    List<SubjectGroup> findAllSubjectGroupByName(String name, String subjectType);

    /**
	 * Gets the subject history.
	 * 
	 * @param subjectId
	 *            the subject id
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the subject history
	 */
    List<AuditHistory> getSubjectHistory(long subjectId, Date begin, Date end);

    /**
	 * Gets the subject history.
	 * 
	 * @param subjectType
	 *            the subject type
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the subject history
	 */
    List<AuditHistory> getSubjectHistory(String subjectType, Date begin, Date end);

    /**
	 * Gets the subject group history.
	 * 
	 * @param subjectGroupId
	 *            the subject group id
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the subject group history
	 */
    List<AuditHistory> getSubjectGroupHistory(long subjectGroupId, Date begin, Date end);

    /**
	 * Gets the subject group history.
	 * 
	 * @param subjectType
	 *            the subject type
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the subject group history
	 */
    List<AuditHistory> getSubjectGroupHistory(String subjectType, Date begin, Date end);

    /**
	 * Audit.
	 * 
	 * @param subjectKey
	 *            the subject key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 */
    void audit(SubjectKey subjectKey, String operationType,
			SubjectKey loginSubject);

	/**
	 * Audit.
	 * 
	 * @param subjectGroupKey
	 *            the subject group key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 */
	void audit(SubjectGroupKey subjectGroupKey, String operationType,
			SubjectKey loginSubject);

}
