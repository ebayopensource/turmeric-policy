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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import oasis.names.tc.xacml._2_0.policy.schema.os.SubjectMatchType;

import org.ebayopensource.turmeric.policyservice.exceptions.PolicyFinderException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyProviderException.Category;
import org.ebayopensource.turmeric.policyservice.provider.USERSubject;
import org.ebayopensource.turmeric.policyservice.provider.utils.PolicyServiceUtils;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroupKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;
import org.ebayopensource.turmeric.utils.jpa.AbstractDAO;
import org.ebayopensource.turmeric.utils.jpa.model.AuditInfo;

/**
 * The Class SubjectDAOImpl.
 */
public class SubjectDAOImpl extends AbstractDAO implements SubjectDAO {
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#persistSubject(org.ebayopensource.turmeric.policyservice.model.Subject)
	 */
	@Override
	public void persistSubject(Subject subject) {
		persistEntity(subject);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#persistSubjectGroup(org.ebayopensource.turmeric.policyservice.model.SubjectGroup)
	 */
	@Override
	public void persistSubjectGroup(SubjectGroup subjectGroup) {
		persistEntity(subjectGroup);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#removeSubject(long)
	 */
	@Override
	public void removeSubject(long id) {
		removeEntity(Subject.class, id);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#removeSubjectGroup(long)
	 */
	@Override
	public void removeSubjectGroup(long id) {
		removeEntity(SubjectGroup.class, id);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findSubjectById(long)
	 */
	@Override
	public Subject findSubjectById(long id) {
		return findEntity(Subject.class, id);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findSubjectByName(java.lang.String)
	 */
	@Override
	public Subject findSubjectByName(String name) {
		return getSingleResultOrNull(Subject.class, "subjectName", name);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findAllSubjectByName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Subject> findAllSubjectByName(String name, String subjectType) {
		return getWildcardResultList(Subject.class, "subjectType", subjectType,
				"subjectName", name);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findSubjectByType(java.lang.String)
	 */
	@Override
	public List<Subject> findSubjectByType(String type) {
		return getResultList(Subject.class, "subjectType", type);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findSubjectGroupById(java.lang.Long)
	 */
	@Override
	public SubjectGroup findSubjectGroupById(Long id) {
		return findEntity(SubjectGroup.class, id);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findSubjectGroupByName(java.lang.String)
	 */
	@Override
	public SubjectGroup findSubjectGroupByName(String name) {
		return getSingleResultOrNull(SubjectGroup.class, "subjectGroupName",
				name);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findAllSubjectGroupByName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<SubjectGroup> findAllSubjectGroupByName(String name,
			String subjectType) {
		return getWildcardResultList(SubjectGroup.class, "subjectType",
				subjectType, "subjectGroupName", name);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findSubjectGroupByType(java.lang.String)
	 */
	@Override
	public List<SubjectGroup> findSubjectGroupByType(String type) {
		return getResultList(SubjectGroup.class, "subjectType", type);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findSubjectGroupBySubjectName(java.lang.String, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectGroup> findSubjectGroupBySubjectName(String name,
			String subjectType) {
		return findEntityByMemberValue(SubjectGroup.class, "subjectType",
				subjectType, "subjects", "subjectName", name);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findSubjectTypeByName(java.lang.String)
	 */
	@Override
	public SubjectType findSubjectTypeByName(String name) {
		return getSingleResultOrNull(SubjectType.class, "name", name);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#getSubjectHistory(long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<AuditHistory> getSubjectHistory(long subjectId, Date start,
			Date end) {
		return getResultList(AuditHistory.class, "category",
				Category.SUBJECT.name(), "entityId", subjectId,
				"auditInfo.createdOn", start, end);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#getSubjectHistory(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<AuditHistory> getSubjectHistory(String subjectType, Date start,
			Date end) {
		return getResultList(AuditHistory.class, "category",
				Category.SUBJECT.name(), "entityType", subjectType,
				"auditInfo.createdOn", start, end);
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#getSubjectGroupHistory(long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<AuditHistory> getSubjectGroupHistory(long subjectId,
			Date start, Date end) {
		return getResultList(AuditHistory.class, "category",
				Category.SUBJECTGROUP.name(), "entityId", subjectId,
				"auditInfo.createdOn", start, end);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#getSubjectGroupHistory(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<AuditHistory> getSubjectGroupHistory(String subjectType,
			Date start, Date end) {
		return getResultList(AuditHistory.class, "category",
				Category.SUBJECTGROUP.name(), "entityType", subjectType,
				"auditInfo.createdOn", start, end);
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#audit(org.ebayopensource.turmeric.security.v1.services.SubjectKey, java.lang.String, org.ebayopensource.turmeric.security.v1.services.SubjectKey)
	 */
	@Override
	public void audit(SubjectKey subjectKey, String operationType,
			SubjectKey loginSubject) {
		persistEntity(AuditHistory.newRecord(subjectKey, operationType,
				loginSubject));
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#audit(org.ebayopensource.turmeric.security.v1.services.SubjectGroupKey, java.lang.String, org.ebayopensource.turmeric.security.v1.services.SubjectKey)
	 */
	@Override
	public void audit(SubjectGroupKey subjectGroupKey, String operationType,
			SubjectKey loginSubject) {
		persistEntity(AuditHistory.newRecord(subjectGroupKey, operationType,
				loginSubject));
	}

	/**
	 * Convert.
	 * 
	 * @param jpaSubject
	 *            the jpa subject
	 * @return the org.ebayopensource.turmeric.security.v1.services. subject
	 * @throws PolicyFinderException
	 *             the policy finder exception
	 */
	public static org.ebayopensource.turmeric.security.v1.services.Subject convert(
			Subject jpaSubject) throws PolicyFinderException {
		org.ebayopensource.turmeric.security.v1.services.Subject result = new org.ebayopensource.turmeric.security.v1.services.Subject();

		result.setSubjectName(jpaSubject.getSubjectName());
		result.setSubjectType(jpaSubject.getSubjectType());
		result.setDescription(jpaSubject.getDescription());
		result.setIpMask(jpaSubject.getIpMask());
		result.setExternalSubjectId(jpaSubject.getExternalSubjectId());
		result.setEmailContact(jpaSubject.getEmailContact());

		AuditInfo auditInfo = jpaSubject.getAuditInfo();
		if (auditInfo != null) {
			result.setCreatedBy(auditInfo.getCreatedBy());

			try {
				GregorianCalendar updatedOn = new GregorianCalendar();
				Date updateDate = auditInfo.getUpdatedOn();
				updatedOn.setTime(updateDate == null ? auditInfo.getCreatedOn()
						: updateDate);
				result.setLastUpdatedDate(DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(updatedOn));
			} catch (DatatypeConfigurationException ex) {
				throw new PolicyFinderException(Category.SUBJECT,
						jpaSubject.getSubjectType(), null,
						jpaSubject.getSubjectName(),
						"Failed to convert subject", ex);
			}
		}

		return result;
	}

	/**
	 * Convert.
	 * 
	 * @param subject
	 *            the subject
	 * @return the subject
	 */
	public static Subject convert(
			org.ebayopensource.turmeric.security.v1.services.Subject subject) {
		Long extId = subject.getExternalSubjectId();
		return new Subject(subject.getSubjectName(), subject.getSubjectType(),
				subject.getDescription(), subject.getIpMask(),
				(extId == null ? 0 : extId.longValue()),
				subject.getEmailContact());
	}

	/**
	 * Convert.
	 * 
	 * @param jpaSubjectGroup
	 *            the jpa subject group
	 * @return the org.ebayopensource.turmeric.security.v1.services. subject
	 *         group
	 * @throws PolicyFinderException
	 *             the policy finder exception
	 */
	public static org.ebayopensource.turmeric.security.v1.services.SubjectGroup convert(
			SubjectGroup jpaSubjectGroup) throws PolicyFinderException {
		org.ebayopensource.turmeric.security.v1.services.SubjectGroup result = new org.ebayopensource.turmeric.security.v1.services.SubjectGroup();
		result.setSubjectGroupName(jpaSubjectGroup.getSubjectGroupName());
		result.setSubjectType(jpaSubjectGroup.getSubjectType());
		result.setSubjectGroupCalculator(jpaSubjectGroup
				.getSubjectGroupCalculator());
		result.setApplyToEach(jpaSubjectGroup.getApplyToEach());
		result.setApplyToAll(jpaSubjectGroup.getApplyToAll());
		result.setDescription(jpaSubjectGroup.getDescription());

		AuditInfo auditInfo = jpaSubjectGroup.getAuditInfo();
		result.setCreatedBy(auditInfo.getCreatedBy());
		result.setLastModifiedBy(auditInfo.getUpdatedBy());

		try {
			GregorianCalendar updatedOn = new GregorianCalendar();
			Date updateDate = auditInfo.getUpdatedOn();
			updatedOn.setTime(updateDate == null ? auditInfo.getCreatedOn()
					: updateDate);
			result.setLastUpdatedDate(DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(updatedOn));
		} catch (DatatypeConfigurationException ex) {
			throw new PolicyFinderException(Category.SUBJECTGROUP,
					jpaSubjectGroup.getSubjectType(), null,
					jpaSubjectGroup.getSubjectGroupName(),
					"Failed to convert subject group", ex);
		}

		return result;
	}

	/**
	 * Convert.
	 * 
	 * @param subjectGroup
	 *            the subject group
	 * @return the subject group
	 */
	public static SubjectGroup convert(
			org.ebayopensource.turmeric.security.v1.services.SubjectGroup subjectGroup) {
		return new SubjectGroup(subjectGroup.getSubjectGroupName(),
				subjectGroup.getSubjectType(),
				subjectGroup.getSubjectGroupCalculator(),
				subjectGroup.isApplyToEach(), subjectGroup.isApplyToAll(),
				subjectGroup.getDescription());
	}

	//
	// public static
	// org.ebayopensource.turmeric.security.v1.services.GroupCalculatorInfo
	// convert(GroupCalculator jpaGroupCalculator) {
	// org.ebayopensource.turmeric.security.v1.services.GroupCalculatorInfo
	// result =
	// new
	// org.ebayopensource.turmeric.security.v1.services.GroupCalculatorInfo();
	// result.setName(jpaGroupCalculator.getName());
	// result.setSubjectTypeName(jpaGroupCalculator.getSubjectTypeName());
	// result.setDescription(jpaGroupCalculator.getDescription());
	//
	// return result;
	// }

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.SubjectDAO#findExternalSubjects()
	 */
	@Override
	public List<BasicAuth> findExternalSubjects() {
		return getWildcardResultList(BasicAuth.class, "subjectName", null);
	}

	/*
	 * Converts an external subject type into a internal Subject type
	 */
	/**
	 * Convert.
	 * 
	 * @param externalSubject
	 *            the external subject
	 * @return the org.ebayopensource.turmeric.security.v1.services. subject
	 * @throws PolicyFinderException
	 *             the policy finder exception
	 */
	public static org.ebayopensource.turmeric.security.v1.services.Subject convert(
			BasicAuth externalSubject) throws PolicyFinderException {
		org.ebayopensource.turmeric.security.v1.services.Subject subject = new org.ebayopensource.turmeric.security.v1.services.Subject();

		subject.setSubjectName(externalSubject.getSubjectName());
		subject.setExternalSubjectId(externalSubject.getId());

		AuditInfo auditInfo = externalSubject.getAuditInfo();
		if (auditInfo != null) {
			subject.setCreatedBy(auditInfo.getCreatedBy());

			try {
				GregorianCalendar updatedOn = new GregorianCalendar();
				Date updateDate = auditInfo.getUpdatedOn();
				updatedOn.setTime(updateDate == null ? auditInfo.getCreatedOn()
						: updateDate);
				subject.setLastUpdatedDate(DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(updatedOn));
			} catch (DatatypeConfigurationException ex) {
				throw new PolicyFinderException(Category.SUBJECT, "EXTERNAL",
						null, externalSubject.getSubjectName(),
						"Failed to convert subject", ex);
			}
		}
		return subject;

	}
}
