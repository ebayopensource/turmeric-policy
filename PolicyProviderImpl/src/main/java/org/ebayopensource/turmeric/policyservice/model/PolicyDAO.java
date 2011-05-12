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
import java.util.Set;

import org.ebayopensource.turmeric.security.v1.services.PolicyKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;

/**
 * The Interface PolicyDAO.
 */
public interface PolicyDAO {

    /**
	 * Persist policy.
	 * 
	 * @param jpaPolicy
	 *            the jpa policy
	 */
    public void persistPolicy(Policy jpaPolicy);

    /**
	 * Find policy by id.
	 * 
	 * @param policyId
	 *            the policy id
	 * @return the policy
	 */
    public Policy findPolicyById(Long policyId);

    /**
	 * Find policy by name.
	 * 
	 * @param policyName
	 *            the policy name
	 * @return the policy
	 */
    public Policy findPolicyByName(String policyName);

    /**
	 * Find operation by id.
	 * 
	 * @param operationId
	 *            the operation id
	 * @return the operation
	 */
    public Operation findOperationById(Long operationId);

    /**
	 * Find resource by id.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the resource
	 */
    public Resource findResourceById(Long resourceId);

    /**
	 * Find subject by id.
	 * 
	 * @param subjectId
	 *            the subject id
	 * @return the subject
	 */
    public Subject findSubjectById(Long subjectId);

    /**
	 * Find subject type by id.
	 * 
	 * @param subjecttype Id
	 *            the subject type  id
	 * @return the subjectType
	 */
    public SubjectType findSubjectTypeById(Long subjectTypeId);

    
    /**
	 * Find subject group by id.
	 * 
	 * @param subjectGroupId
	 *            the subject group id
	 * @return the subject group
	 */
    public SubjectGroup findSubjectGroupById(Long subjectGroupId);

    /**
	 * Find policy by subject id.
	 * 
	 * @param subjectId
	 *            the subject id
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findPolicyBySubjectId(Long subjectId, String policyType);

    /**
	 * Find policy by subjectType id.
	 * 
	 * @param subjectTypeId
	 *            the subjectType id
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findPolicyBySubjectTypeId(Long subjectTypeId, String policyType);

    
    /**
	 * Find policy by subject group id.
	 * 
	 * @param subjGrpId
	 *            the subj grp id
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findPolicyBySubjectGroupId(Long subjGrpId, String policyType);

    /**
	 * Find policy by operation id.
	 * 
	 * @param opId
	 *            the op id
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findPolicyByOperationId(Long opId, String policyType);

    /**
	 * Find policy by resource id.
	 * 
	 * @param resId
	 *            the res id
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findPolicyByResourceId(Long resId, String policyType);

    /**
	 * Removes the policy.
	 * 
	 * @param id
	 *            the id
	 */
    void removePolicy(long id);

    /**
	 * Find all by name.
	 * 
	 * @param name
	 *            the name
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findAllByName(String name, String policyType);

    /**
	 * Find all by type.
	 * 
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findAllByType(String policyType);

    /**
	 * Audit.
	 * 
	 * @param policyKey
	 *            the policy key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 */
    public void audit(PolicyKey policyKey, String operationType, SubjectKey loginSubject);

    /**
	 * Gets the policy history.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the policy history
	 */
    public List<AuditHistory> getPolicyHistory(long policyId, Date start, Date end);

    /**
	 * Gets the policy history.
	 * 
	 * @param policyType
	 *            the policy type
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the policy history
	 */
    public List<AuditHistory> getPolicyHistory(String policyType, Date start, Date end);

    /**
	 * Find policy by exclusion subject id.
	 * 
	 * @param subjectId
	 *            the subject id
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findPolicyByExclusionSubjectId(Long subjectId, String policyType);

    /**
	 * Find policy by exclusion subject group id.
	 * 
	 * @param subjGrpId
	 *            the subj grp id
	 * @param policyType
	 *            the policy type
	 * @return the list
	 */
    public List<Policy> findPolicyByExclusionSubjectGroupId(Long subjGrpId, String policyType);
}
