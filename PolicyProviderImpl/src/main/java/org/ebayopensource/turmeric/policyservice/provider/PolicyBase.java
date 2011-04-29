/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.policyservice.provider;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.ebayopensource.turmeric.policyservice.exceptions.PolicyCreationException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyDeleteException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyFinderException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyUpdateException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyProviderException.Category;
import org.ebayopensource.turmeric.policyservice.provider.PolicyTypeProvider;
import org.ebayopensource.turmeric.policyservice.provider.common.PolicyBuilderObject;
import org.ebayopensource.turmeric.policyservice.provider.common.PolicyEditObject;
import org.ebayopensource.turmeric.policyservice.provider.common.ResourcesEditObject;
import org.ebayopensource.turmeric.policyservice.provider.common.SubjectsEditObject;
import org.ebayopensource.turmeric.policyservice.provider.utils.QueryConditionHelper;
import org.ebayopensource.turmeric.security.v1.services.EntityHistory;
import org.ebayopensource.turmeric.security.v1.services.KeyValuePair;
import org.ebayopensource.turmeric.security.v1.services.Operation;
import org.ebayopensource.turmeric.security.v1.services.Policy;
import org.ebayopensource.turmeric.security.v1.services.PolicyKey;
import org.ebayopensource.turmeric.security.v1.services.Query;
import org.ebayopensource.turmeric.security.v1.services.QueryCondition;
import org.ebayopensource.turmeric.security.v1.services.Resource;
import org.ebayopensource.turmeric.security.v1.services.Rule;
import org.ebayopensource.turmeric.security.v1.services.Subject;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroup;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectTypeInfo;


/**
 * The Class PolicyBase.
 */
public abstract class PolicyBase implements PolicyTypeProvider {
	
	/**
	 * Gets the policy type.
	 * 
	 * @return the policy type
	 */
	protected abstract String getPolicyType();
	
	/**
	 * Gets the query value.
	 * 
	 * @param queryCondition
	 *            the query condition
	 * @param queryType
	 *            the query type
	 * @return the query value
	 */
	protected String getQueryValue(QueryCondition queryCondition,
            String queryType) {
        if (queryCondition != null) {
            for (Query query : queryCondition.getQuery()) {
                if (query.getQueryType().equalsIgnoreCase(queryType))
                    return query.getQueryValue();
            }
        }
        return null;
    }

	/**
	 * {@inheritDoc}
	 */
    public PolicyKey createPolicy(
            Policy inputPolicy,
            PolicyEditObject policyEditObject,
            SubjectKey createdBy) throws PolicyUpdateException,
            PolicyCreationException {

        PolicyKey policyKey = createPolicyInfo(inputPolicy, createdBy);
        policyEditObject.setPolicyId(policyKey.getPolicyId());
        inputPolicy.setActive(Boolean.FALSE);
        try {
            return updatePolicy(inputPolicy, policyEditObject, createdBy);
        } catch (PolicyDeleteException e) {
            //Should not happen.
            throw new PolicyUpdateException(Category.POLICY, 
                    getPolicyType(), "Failed to create policy", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public PolicyKey updatePolicy(
            Policy inputPolicy,
            PolicyEditObject policyEditObject,
            SubjectKey modifiedBy) throws PolicyUpdateException,
            PolicyCreationException, PolicyDeleteException {
        Boolean isModified = false;

        if (policyEditObject != null) {
	        Long policyId = policyEditObject.getPolicyId();
	        if (policyEditObject.getRuleEditObject() != null)
	            isModified = updateRuleOfPolicy(policyId, policyEditObject
	                    .getRuleEditObject().getRemoveList(), policyEditObject
	                    .getRuleEditObject().getAddList())
	                    || isModified;
	
	        if (policyEditObject.getResourcesEditObject() != null)
	            isModified = updateResourcesOfPolicy(policyId, policyEditObject
	                    .getResourcesEditObject())
	                    || isModified;
	
	        if (policyEditObject.getSubjectsEditObject() != null)
	            isModified = updateSubjectsOfPolicy(policyId, policyEditObject
	                    .getSubjectsEditObject())
	                    || isModified;
	
	        inputPolicy.setPolicyId(policyId);
        }

        return updatePolicyInfo(inputPolicy, modifiedBy);

    }

	/**
	 * Update resources of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param resourcesEditObject
	 *            the resources edit object
	 * @return true, if successful
	 * @throws PolicyCreationException
	 *             the policy creation exception
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 * @throws PolicyDeleteException
	 *             the policy delete exception
	 */
	protected boolean updateResourcesOfPolicy(
            Long policyId,
            ResourcesEditObject resourcesEditObject)
            throws PolicyCreationException, PolicyUpdateException,
            PolicyDeleteException {

        boolean isModified = false;

        if (resourcesEditObject == null)
            return isModified;

        List<Long> removeResourceList = resourcesEditObject
                .getRemoveResourceList();
        if (removeResourceList != null && !removeResourceList.isEmpty()) {
            removeResourceAssignmentOfPolicy(policyId, resourcesEditObject
                    .getRemoveResourceList());
            isModified = true;
        }

        List<Long> addResourceList = resourcesEditObject.getAddResourceList();
        if (addResourceList != null && !addResourceList.isEmpty()) {
            addResourceAssignmentOfPolicy(policyId, addResourceList);
            isModified = true;
        }

        List<Long> removeOperationList = resourcesEditObject
                .getRemoveOperationList();
        if (removeOperationList != null && !removeOperationList.isEmpty()) {
            removeOperationAssignmentOfPolicy(policyId, removeOperationList);
            isModified = true;
        }

        List<Long> addOperationList = resourcesEditObject.getAddOperationList();
        if (addOperationList != null && !addOperationList.isEmpty()) {
            addOperationAssignmentOfPolicy(policyId, addOperationList);
            isModified = true;
        }

        return isModified;
    }

	/**
	 * Update subjects of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param policyEditObject
	 *            the policy edit object
	 * @return true, if successful
	 * @throws PolicyCreationException
	 *             the policy creation exception
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 * @throws PolicyDeleteException
	 *             the policy delete exception
	 */
	protected boolean updateSubjectsOfPolicy(
            Long policyId,
            SubjectsEditObject policyEditObject)
            throws PolicyCreationException, PolicyUpdateException,
            PolicyDeleteException {
        boolean isModified = false;

        List<Long> removeSubjectTypeList = policyEditObject
                .getRemoveSubjectTypeList();
        if (!removeSubjectTypeList.isEmpty()) {
            removeSubjectTypeAssignmentOfPolicy(policyId, removeSubjectTypeList);
            isModified = true;
        }

        List<Long> addSubjectTypeList = policyEditObject
                .getAddSubjectTypeList();
        if (!addSubjectTypeList.isEmpty()) {
            addSubjectTypeAssignmentOfPolicy(policyId, addSubjectTypeList);
            isModified = true;
        }

        List<Long> removeSubjectList = policyEditObject.getRemoveSubjectList();
        if (!removeSubjectList.isEmpty()) {
            removeSubjectAssignmentOfPolicy(policyId, removeSubjectList);
            isModified = true;
        }

        List<Long> addSubjectList = policyEditObject.getAddSubjectList();
        if (addSubjectList != null && !addSubjectList.isEmpty()) {
            addSubjectAssignmentOfPolicy(policyId, addSubjectList);
            isModified = true;
        }

        List<Long> removeExclusionSubjectList = policyEditObject
                .getRemoveExclusionSubjectList();
        if (!removeExclusionSubjectList.isEmpty()) {
            removeExclusionSubjectAssignmentOfPolicy(policyId,
                    removeExclusionSubjectList);
            isModified = true;
        }

        List<Long> addExclusionSubjectList = policyEditObject
                .getAddExclusionSubjectList();
        if (!addExclusionSubjectList.isEmpty()) {
            addExclusionSubjectAssignmentOfPolicy(policyId,
                    addExclusionSubjectList);
            isModified = true;
        }

        List<Long> removeSubjectGroupList = policyEditObject
                .getRemoveSubjectGroupList();
        if (!removeSubjectGroupList.isEmpty()) {
            removeSubjectGroupAssignmentOfPolicy(policyId,
                    removeSubjectGroupList);
            isModified = true;
        }

        List<Long> addSubjectGroupList = policyEditObject
                .getAddSubjectGroupList();
        if (!addSubjectGroupList.isEmpty()) {
            addSubjectGroupAssignmentOfPolicy(policyId, addSubjectGroupList);
            isModified = true;
        }

        List<Long> removeExclusionSubjectGroupList = policyEditObject
                .getRemoveExclusionSubjectGroupList();
        if (!removeExclusionSubjectGroupList.isEmpty()) {
            removeExclusionSubjectGroupAssignmentOfPolicy(policyId,
                    removeExclusionSubjectGroupList);
            isModified = true;
        }

        List<Long> addExclusionSubjectGroupList = policyEditObject
                .getAddExclusionSubjectGroupList();
        if (!addExclusionSubjectGroupList.isEmpty()) {
            addExclusionSubjectGroupAssignmentOfPolicy(policyId,
                    addExclusionSubjectGroupList);
            isModified = true;
        }

        return isModified;
    }

	/**
	 * {@inheritDoc}
	 */
    public PolicyBuilderObject applyQueryCondition(
            PolicyBuilderObject builderObject,
            QueryCondition queryCondition) {

        QueryConditionHelper queryConditionHelper = new QueryConditionHelper(
                queryCondition);
        if (queryConditionHelper.isIdMasked()) {

            Map<Long, Resource> resources = builderObject.getResources();
            Map<Long, Resource> maskedResources = new HashMap<Long, Resource>();
            for (Long id : resources.keySet()) {
                Long maskedId = maskHighOrderBits(id);
                Resource resource = resources.get(id);
                resource.setResourceId(maskedId);
                List<Operation> operations = resource.getOperation();
                for (Operation operation : operations) {
                    operation.setOperationId(maskHighOrderBits(operation
                            .getOperationId()));
                }
                maskedResources.put(maskedId, resource);
            }
            builderObject.setResources(maskedResources);

            Map<Long, Rule> rules = builderObject.getRules();
            Map<Long, Rule> maskedRules = new HashMap<Long, Rule>();
            for (Long id : rules.keySet()) {
                Long maskedId = maskHighOrderBits(id);
                Rule rule = rules.get(id);
                rule.setRuleId(maskedId);
                maskedRules.put(maskedId, rule);
            }
            builderObject.setRules(maskedRules);

            builderObject.setInclusionSubjectGrps(maskIds(builderObject
                    .getInclusionSubjectGrps()));
            builderObject.setInclusionSubjects(maskIds(builderObject
                    .getInclusionSubjects()));
            builderObject.setExclusionSubjectGrps(maskIds(builderObject
                    .getExclusionSubjectGrps()));
            builderObject.setExclusionSubjects(maskIds(builderObject
                    .getExclusionSubjects()));
        }
        return builderObject;
    }

    /**
	 * Mask ids.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param objs
	 *            the objs
	 * @return the map
	 */
    protected <T> Map<Long, T> maskIds(Map<Long, T> objs) {
        Map<Long, T> maskedObjs = new HashMap<Long, T>();
        for (Long id : objs.keySet()) {
            Long maskedId = maskHighOrderBits(id);
            maskedObjs.put(maskedId, objs.get(id));
        }
        return maskedObjs;
    }

    /**
	 * Mask high order bits.
	 * 
	 * @param l
	 *            the l
	 * @return the long
	 */
    protected Long maskHighOrderBits(Long l) {
        return Long.valueOf(l.intValue() & 0x7fffffff);
    }

    /**
     * {@inheritDoc}
     */
    public abstract List<EntityHistory> getAuditHistory(PolicyKey policyKey,
                    XMLGregorianCalendar startDate, XMLGregorianCalendar endDate)
                    throws PolicyFinderException;

    /**
     * {@inheritDoc}
     */
    public abstract void audit(PolicyKey policyKey, String operationType, SubjectKey loginSubject)
                    throws PolicyFinderException;

    /**
	 * Creates the policy info.
	 * 
	 * @param inputPolicy
	 *            the input policy
	 * @param createdBy
	 *            the created by
	 * @return the policy key
	 * @throws PolicyCreationException
	 *             the policy creation exception
	 */
    protected abstract PolicyKey createPolicyInfo(Policy inputPolicy, SubjectKey createdBy)
                    throws PolicyCreationException;

    /**
	 * Update policy info.
	 * 
	 * @param inputPolicy
	 *            the input policy
	 * @param modifiedBy
	 *            the modified by
	 * @return the policy key
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract PolicyKey updatePolicyInfo(Policy inputPolicy, SubjectKey modifiedBy)
                    throws PolicyUpdateException;

    /**
	 * Update rule of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param removeList
	 *            the remove list
	 * @param addList
	 *            the add list
	 * @return true, if successful
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract boolean updateRuleOfPolicy(Long policyId, List<Rule> removeList,
                    List<Rule> addList) throws PolicyUpdateException;

    /**
	 * Adds the operation assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param addOperationList
	 *            the add operation list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void addOperationAssignmentOfPolicy(Long policyId,
                    List<Long> addOperationList) throws PolicyUpdateException;

    /**
	 * Removes the operation assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param removeOperationList
	 *            the remove operation list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void removeOperationAssignmentOfPolicy(Long policyId,
                    List<Long> removeOperationList) throws PolicyUpdateException;

    /**
	 * Adds the resource assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param addResourceList
	 *            the add resource list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void addResourceAssignmentOfPolicy(Long policyId, List<Long> addResourceList)
                    throws PolicyUpdateException;

    /**
	 * Removes the resource assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param removeResourceList
	 *            the remove resource list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void removeResourceAssignmentOfPolicy(Long policyId,
                    List<Long> removeResourceList) throws PolicyUpdateException;

    /**
	 * Adds the exclusion subject group assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param addExclusionSubjectGroupList
	 *            the add exclusion subject group list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void addExclusionSubjectGroupAssignmentOfPolicy(Long policyId,
                    List<Long> addExclusionSubjectGroupList) throws PolicyUpdateException;

    /**
	 * Removes the exclusion subject group assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param removeExclusionSubjectGroupList
	 *            the remove exclusion subject group list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void removeExclusionSubjectGroupAssignmentOfPolicy(Long policyId,
                    List<Long> removeExclusionSubjectGroupList) throws PolicyUpdateException;

    /**
	 * Adds the subject group assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param addSubjectGroupList
	 *            the add subject group list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void addSubjectGroupAssignmentOfPolicy(Long policyId,
                    List<Long> addSubjectGroupList) throws PolicyUpdateException;

    /**
	 * Removes the subject group assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param removeSubjectGroupList
	 *            the remove subject group list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void removeSubjectGroupAssignmentOfPolicy(Long policyId,
                    List<Long> removeSubjectGroupList) throws PolicyUpdateException;

    /**
	 * Adds the exclusion subject assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param addExclusionSubjectList
	 *            the add exclusion subject list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void addExclusionSubjectAssignmentOfPolicy(Long policyId,
                    List<Long> addExclusionSubjectList) throws PolicyUpdateException;

    /**
	 * Removes the exclusion subject assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param removeExclusionSubjectList
	 *            the remove exclusion subject list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void removeExclusionSubjectAssignmentOfPolicy(Long policyId,
                    List<Long> removeExclusionSubjectList) throws PolicyUpdateException;

    /**
	 * Adds the subject assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param addSubjectList
	 *            the add subject list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void addSubjectAssignmentOfPolicy(Long policyId, List<Long> addSubjectList)
                    throws PolicyUpdateException;

    /**
	 * Removes the subject assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param removeSubjectList
	 *            the remove subject list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void removeSubjectAssignmentOfPolicy(Long policyId,
                    List<Long> removeSubjectList) throws PolicyUpdateException;

    /**
	 * Adds the subject type assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param addSubjectTypeList
	 *            the add subject type list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void addSubjectTypeAssignmentOfPolicy(Long policyId,
                    List<Long> addSubjectTypeList) throws PolicyUpdateException;

    /**
	 * Removes the subject type assignment of policy.
	 * 
	 * @param policyId
	 *            the policy id
	 * @param removeSubjectTypeList
	 *            the remove subject type list
	 * @throws PolicyUpdateException
	 *             the policy update exception
	 */
    protected abstract void removeSubjectTypeAssignmentOfPolicy(Long policyId,
                    List<Long> removeSubjectTypeList) throws PolicyUpdateException;
}
