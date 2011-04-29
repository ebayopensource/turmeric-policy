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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.ebayopensource.turmeric.policyservice.exceptions.PolicyFinderException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyProviderException.Category;
import org.ebayopensource.turmeric.policyservice.provider.utils.RuleHelper;
import org.ebayopensource.turmeric.security.v1.services.PolicyKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;
import org.ebayopensource.turmeric.utils.jpa.AbstractDAO;
import org.ebayopensource.turmeric.utils.jpa.model.AuditInfo;

/**
 * The Class PolicyDAOImpl.
 */
public class PolicyDAOImpl extends AbstractDAO implements PolicyDAO {
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#persistPolicy(org.ebayopensource.turmeric.policyservice.model.Policy)
     */
    @Override
    public void persistPolicy(Policy policy) {
        persistEntity(policy);
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#removePolicy(long)
     */
    @Override
    public void removePolicy(long id) {
        removeEntity(Policy.class, id);
    }

   /* (non-Javadoc)
    * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findPolicyById(java.lang.Long)
    */
   @Override
    public Policy findPolicyById(Long id) {
        return findEntity(Policy.class, id);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findPolicyByName(java.lang.String)
     */
    @Override
    public Policy findPolicyByName(String name) {
        return getSingleResultOrNull(Policy.class, "policyName", name);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findOperationById(java.lang.Long)
     */
    @Override
    public Operation findOperationById(Long id) {
        return findEntity(Operation.class, id);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findResourceById(java.lang.Long)
     */
    @Override
    public Resource findResourceById(Long id) {
        return findEntity(Resource.class, id);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findSubjectById(java.lang.Long)
     */
    @Override
    public Subject findSubjectById(Long id) {
        return findEntity(Subject.class, id);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findSubjectGroupById(java.lang.Long)
     */
    @Override
    public SubjectGroup findSubjectGroupById(Long id) {
        return findEntity(SubjectGroup.class, id);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findPolicyBySubjectId(java.lang.Long, java.lang.String)
     */
    @Override
    public List<Policy> findPolicyBySubjectId(Long subjectId, String policyType) {
        return findEntityByMemberId(Policy.class, "policyType", policyType, "subjects", subjectId);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findPolicyBySubjectGroupId(java.lang.Long, java.lang.String)
     */
    @Override
    public List<Policy> findPolicyBySubjectGroupId(Long subjGrpId, String policyType) {
        return findEntityByMemberId(Policy.class, "policyType", policyType, "subjectGroups", subjGrpId);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findPolicyByOperationId(java.lang.Long, java.lang.String)
     */
    @Override
    public List<Policy> findPolicyByOperationId(Long opId, String policyType) {
        return findEntityByMemberId(Policy.class, "policyType", policyType, "operations", opId);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findPolicyByResourceId(java.lang.Long, java.lang.String)
     */
    @Override
    public List<Policy> findPolicyByResourceId(Long resId, String policyType) {
        return findEntityByMemberId(Policy.class, "policyType", policyType, "resources", resId);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findAllByName(java.lang.String, java.lang.String)
     */
    @Override
    public List<Policy> findAllByName(String name, String policyType) {
        return getWildcardResultList(Policy.class, "policyType", policyType, "policyName", name);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findAllByType(java.lang.String)
     */
    @Override
    public List<Policy> findAllByType(String policyType) {
        return getResultList(Policy.class, "policyType", policyType);
    }

    /**
	 * Convert.
	 * 
	 * @param policy
	 *            the policy
	 * @return the policy
	 */
    public static Policy convert(org.ebayopensource.turmeric.security.v1.services.Policy policy){
        Boolean value;
        if(policy == null){
        	return null;
        }
        
        final List<org.ebayopensource.turmeric.policyservice.model.Rule> ruleList = 
            new ArrayList<org.ebayopensource.turmeric.policyservice.model.Rule>();
        final List<org.ebayopensource.turmeric.security.v1.services.Rule> rules= 
            policy.getRule();
        if (rules != null && !rules.isEmpty()) {
            for (org.ebayopensource.turmeric.security.v1.services.Rule rule: rules) {
            	Rule converted = RuleHelper.convert(rule);
            	if(converted != null){
            		ruleList.add(converted);	
            	}
            }
        }
        
        Policy jpaPolicy = new Policy();
        jpaPolicy.setPolicyName(policy.getPolicyName());
        jpaPolicy.setPolicyType(policy.getPolicyType());
        jpaPolicy.setDescription(policy.getDescription());
        jpaPolicy.setActive((value = policy.isActive())==null ? false:value);
        jpaPolicy.getRules().addAll(ruleList);
        return jpaPolicy;
    }

    /**
	 * Convert.
	 * 
	 * @param jpaPolicy
	 *            the jpa policy
	 * @return the org.ebayopensource.turmeric.security.v1.services. policy
	 * @throws PolicyFinderException
	 *             the policy finder exception
	 */
    public static org.ebayopensource.turmeric.security.v1.services.Policy convert(Policy jpaPolicy)
            throws PolicyFinderException {
        if( jpaPolicy==null) {
        	return null;
        }
        org.ebayopensource.turmeric.security.v1.services.Policy policy = 
            new org.ebayopensource.turmeric.security.v1.services.Policy();

        policy.setPolicyId(jpaPolicy.getId());
        policy.setPolicyName(jpaPolicy.getPolicyName());
        policy.setPolicyType(jpaPolicy.getPolicyType());
        policy.setActive(jpaPolicy.isActive());
        policy.setDescription(jpaPolicy.getDescription());

        if(jpaPolicy.getRules() != null && jpaPolicy.getRules().size() > 0){
            policy.getRule().addAll(RuleHelper.convert(jpaPolicy.getRules()));
         }    

        AuditInfo auditInfo = jpaPolicy.getAuditInfo();
        if (auditInfo != null) {
            policy.setCreatedBy(auditInfo.getCreatedBy());
            policy.setLastModifiedBy(auditInfo.getUpdatedBy());
    
            try {
                GregorianCalendar updatedOn = new GregorianCalendar();
                Date updateDate = auditInfo.getUpdatedOn();
                updatedOn.setTime(updateDate == null ? auditInfo.getCreatedOn() : updateDate);
                policy.setLastModified(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                                updatedOn));
            }
            catch (DatatypeConfigurationException ex) {
                throw new PolicyFinderException(Category.POLICY, jpaPolicy.getPolicyType(),
                                null, jpaPolicy.getPolicyName(), "Failed to convert policy", ex);
            }
        }
        return policy;
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#getPolicyHistory(long, java.util.Date, java.util.Date)
     */
    @Override
    public List<AuditHistory> getPolicyHistory(long policyId, Date start, Date end) {
        return getResultList(AuditHistory.class, "category", Category.POLICY.name(), 
                        "entityId", policyId, "auditInfo.createdOn", start, end);
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#getPolicyHistory(java.lang.String, java.util.Date, java.util.Date)
     */
    @Override
    public List<AuditHistory> getPolicyHistory(String policyType, Date start, Date end) {
        return getResultList(AuditHistory.class, "category", Category.POLICY.name(), 
                        "entityType", policyType, "auditInfo.createdOn", start, end);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#audit(org.ebayopensource.turmeric.security.v1.services.PolicyKey, java.lang.String, org.ebayopensource.turmeric.security.v1.services.SubjectKey)
     */
    @Override
    public void audit(PolicyKey policyKey, String operationType, SubjectKey loginSubject) {
        persistEntity(AuditHistory.newRecord(policyKey, operationType, loginSubject));
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findPolicyByExclusionSubjectId(java.lang.Long, java.lang.String)
     */
    @Override
    public List<Policy> findPolicyByExclusionSubjectId(Long subjectId, String policyType) {
        return findEntityByMemberId(Policy.class, "policyType", policyType, "exclusionSubjects", subjectId);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.PolicyDAO#findPolicyByExclusionSubjectGroupId(java.lang.Long, java.lang.String)
     */
    @Override
    public List<Policy> findPolicyByExclusionSubjectGroupId(Long subjGrpId, String policyType) {
        return findEntityByMemberId(Policy.class, "policyType", policyType, "exclusionSubjectGroups", subjGrpId);
    }
}
