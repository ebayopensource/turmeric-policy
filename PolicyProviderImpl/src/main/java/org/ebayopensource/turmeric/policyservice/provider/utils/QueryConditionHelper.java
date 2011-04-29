/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policyservice.provider.utils;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.security.v1.services.Query;
import org.ebayopensource.turmeric.security.v1.services.QueryCondition;

/**
 * The Class QueryConditionHelper.
 */
public class QueryConditionHelper {

    private QueryCondition queryCondition = null;

    /**
	 * Instantiates a new query condition helper.
	 * 
	 * @param queryCondition
	 *            the query condition
	 */
    public QueryConditionHelper(QueryCondition queryCondition) {
        this.queryCondition = queryCondition;
    }

    private String getQueryValue(String queryType) {
        List<Query> queryList = getQueryList();
        if (queryList == null || queryList.size() == 0)
            return null;
        for (Query query : queryList)
            if (queryType.equals(query.getQueryType()))
                return query.getQueryValue();
        return null;
    }

    private List<Query> getQueryList() {
        if (queryCondition == null) {
            return new ArrayList<Query>();
        }
        return queryCondition.getQuery();
    }

    private boolean isQueryTypeSpecified(String queryType) {
        return getQueryValue(queryType) != null;
    }

    /**
	 * Gets the search scope.
	 * 
	 * @return the search scope
	 */
    public String getSearchScope() {
        String value = getQueryValue("SubjectSearchScope");
        return value == null ? "TARGET" : value;
    }

    /**
	 * Checks if is search scope specified.
	 * 
	 * @return true, if is search scope specified
	 */
    public boolean isSearchScopeSpecified() {
        return isQueryTypeSpecified("SubjectSearchScope");
    }

    /**
	 * Gets the effect.
	 * 
	 * @return the effect
	 */
    public String getEffect() {
        return getQueryValue("Effect");
    }

    /**
	 * Checks if is effect specified.
	 * 
	 * @return true, if is effect specified
	 */
    public boolean isEffectSpecified() {
        return isQueryTypeSpecified("Effect");
    }

    /**
	 * Checks if is rule condition format specified.
	 * 
	 * @return true, if is rule condition format specified
	 */
    public boolean isRuleConditionFormatSpecified() {
        return isQueryTypeSpecified("RuleConditionFormat");
    }

    /**
	 * Checks if is rule condition expanded.
	 * 
	 * @return true, if is rule condition expanded
	 */
    public boolean isRuleConditionExpanded() {
        String value = getQueryValue("RuleConditionFormat");
        return value == null ? false : "OPERATIONID".equalsIgnoreCase(value);
    }

    /**
	 * Checks if is target expand resources specified.
	 * 
	 * @return true, if is target expand resources specified
	 */
    public boolean isTargetExpandResourcesSpecified() {
        return isQueryTypeSpecified("ExpandResourceLevelPolicies");
    }

    /**
	 * Checks if is target resources level expanded.
	 * 
	 * @return true, if is target resources level expanded
	 */
    public boolean isTargetResourcesLevelExpanded() {
        String value = getQueryValue("ExpandResourceLevelPolicies");
        return value == null ? false : "TRUE".equalsIgnoreCase(value);
    }

    /**
	 * Checks if is include operation level policies specified.
	 * 
	 * @return true, if is include operation level policies specified
	 */
    public boolean isIncludeOperationLevelPoliciesSpecified() {
        return isQueryTypeSpecified("IncludeOperationLevelPolicies");
    }

    /**
	 * Checks if is to include operation level policies.
	 * 
	 * @return true, if is to include operation level policies
	 */
    public boolean isToIncludeOperationLevelPolicies() {
        String value = getQueryValue("IncludeOperationLevelPolicies");
        return value == null ? true : "TRUE".equalsIgnoreCase(value);
    }

    /**
	 * Checks if is id masked.
	 * 
	 * @return true, if is id masked
	 */
    public boolean isIdMasked() {
        String value = getQueryValue("MaskedIds");
        return value == null ? false : value.equalsIgnoreCase("TRUE");
    }

    /**
	 * Checks if is active policies requested only.
	 * 
	 * @return true, if is active policies requested only
	 */
    public boolean isActivePoliciesRequestedOnly() {
        String value = getQueryValue("ActivePoliciesOnly");
        return value == null ? true : value.equalsIgnoreCase("TRUE");
    }
}

