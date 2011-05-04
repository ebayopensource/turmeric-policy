/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.services.policyservice.impl;

import java.util.Date;
import java.util.List;

import org.ebayopensource.turmeric.errorlibrary.turmericpolicy.ErrorConstants;
import org.ebayopensource.turmeric.runtime.common.exceptions.ErrorUtils;
import org.ebayopensource.turmeric.runtime.common.exceptions.ServiceException;
import org.ebayopensource.turmeric.security.v1.services.FindPoliciesRequest;
import org.ebayopensource.turmeric.security.v1.services.OperationKey;
import org.ebayopensource.turmeric.security.v1.services.PolicyKey;
import org.ebayopensource.turmeric.security.v1.services.PolicyOutputSelector;
import org.ebayopensource.turmeric.security.v1.services.Query;
import org.ebayopensource.turmeric.security.v1.services.QueryCondition;
import org.ebayopensource.turmeric.security.v1.services.ResourceKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroupKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;


/**
 * The Class FindPolicyRequestHelper.
 */
class FindPolicyRequestHelper {
	private FindPoliciesRequest m_request;
	private Date m_lastModified;
	
	 /**
	 * Instantiates a new find policy request helper.
	 * 
	 * @param request
	 *            the request
	 */
 	FindPolicyRequestHelper(FindPoliciesRequest request) {
		m_request = request;
		if (request.getLastUpdatedDate() != null)
			m_lastModified = request.getLastUpdatedDate().toGregorianCalendar().getTime();
	}
	
	 /**
	 * Checks if is time based request.
	 * 
	 * @return true, if is time based request
	 */
 	boolean isTimeBasedRequest() {
		return m_lastModified != null;
	}

	 /**
	 * Gets the last modified.
	 * 
	 * @return the last modified
	 */
 	Date getLastModified() {
		return m_lastModified;
	}

	 /**
	 * Gets the policy key list.
	 * 
	 * @return the policy key list
	 */
 	List<PolicyKey> getPolicyKeyList() {
		return m_request.getPolicyKey();
	}

	 /**
	 * Validate.
	 * 
	 * @throws ServiceException
	 *             the service exception
	 */
 	void validate() throws ServiceException  {
		// check PolicyType list for policyType values
		if (m_request.getPolicyKey() == null || m_request.getPolicyKey().isEmpty())	
				throw new ServiceException(ErrorUtils.createErrorData(
						ErrorConstants.SVC_POLICYSERVICE_INVALID_INPUT_ERROR, 
						ErrorConstants.ERRORDOMAIN.toString(), new Object[]{"Policy key cannot be empty"}));
		
		for(PolicyKey key: m_request.getPolicyKey()) 
		{
			if (key.getPolicyType() == null)
				throw new ServiceException(ErrorUtils.createErrorData(
						ErrorConstants.SVC_POLICYSERVICE_INVALID_INPUT_ERROR, 
						ErrorConstants.ERRORDOMAIN.toString(), new Object[]{"Policy type cannot be empty"}));
		}
		
	}

	 /**
	 * Checks if is filtered.
	 * 
	 * @return true, if is filtered
	 */
 	boolean isFiltered() {
		 return
	        isSubjectFiltered() || isSubjectGroupFiltered() ||
	        isOperationFiltered() || isResourceFiltered() ||
	        isEffectSpecified() || isSearchScopeSpecified();

	}

	 /**
	 * Checks if is search scope specified.
	 * 
	 * @return true, if is search scope specified
	 */
 	boolean isSearchScopeSpecified() {
		return isQueryTypeSpecified( "SubjectSearchScope" );
	}

	 /**
	 * Checks if is effect specified.
	 * 
	 * @return true, if is effect specified
	 */
 	boolean isEffectSpecified() {
		return isQueryTypeSpecified( "Effect" );

	}
	
	  /**
	 * Gets the effect.
	 * 
	 * @return the effect
	 */
  	String getEffect() {
	        return getQueryValue( "Effect" );
	    }

	private boolean isQueryTypeSpecified(String queryType) {
		return getQueryValue( queryType ) != null;
    }


	private String getQueryValue(String queryType) {
		List<Query> queryList = getQueryList();
        if ( queryList == null || queryList.size() == 0 )
            return null;
        for ( Query query : queryList )
            if ( queryType.equals( query.getQueryType() ) )
                return query.getQueryValue();
        return null;
	}

	private List<Query> getQueryList() {
		return m_request.getQueryCondition() == null ? null : m_request.getQueryCondition().getQuery();
	}

	 /**
	 * Checks if is resource filtered.
	 * 
	 * @return true, if is resource filtered
	 */
 	boolean isResourceFiltered() {
		return getResourceList() != null && getResourceList().size() > 0;

	}

	 /**
	 * Gets the resource list.
	 * 
	 * @return the resource list
	 */
 	List<ResourceKey> getResourceList() {
		return m_request.getResourceKey();
	}

	 /**
	 * Checks if is operation filtered.
	 * 
	 * @return true, if is operation filtered
	 */
 	boolean isOperationFiltered() {
        return getOperationList() != null && getOperationList().size() > 0;
	}

	 /**
	 * Gets the operation list.
	 * 
	 * @return the operation list
	 */
 	List<OperationKey> getOperationList() {
		return m_request.getOperationKey();
	}

	 /**
	 * Checks if is subject group filtered.
	 * 
	 * @return true, if is subject group filtered
	 */
 	boolean isSubjectGroupFiltered() {
		return getSubjectGroupList() != null && getSubjectGroupList().size() > 0;
	}

	 /**
	 * Gets the subject group list.
	 * 
	 * @return the subject group list
	 */
 	List<SubjectGroupKey> getSubjectGroupList() {
		return m_request.getSubjectGroupKey();
	}

	 /**
	 * Checks if is subject filtered.
	 * 
	 * @return true, if is subject filtered
	 */
 	boolean isSubjectFiltered() {
		 return getSubjectList() != null && getSubjectList().size() > 0;

	}

	 /**
	 * Gets the subject list.
	 * 
	 * @return the subject list
	 */
 	List<SubjectKey> getSubjectList() {
		return m_request.getSubjectKey();
	}
	
	 /**
	 * Checks if is effect filtered only.
	 * 
	 * @return true, if is effect filtered only
	 */
 	boolean isEffectFilteredOnly() {
		return !isPolicyKeyLookupOnly() && isEffectSpecified() &&
		!isSubjectFiltered() && !isSubjectGroupFiltered() &&
		!isOperationFiltered() && !isResourceFiltered();
	}
	
	 /**
	 * Checks if is policy key lookup only.
	 * 
	 * @return true, if is policy key lookup only
	 */
 	boolean isPolicyKeyLookupOnly() {
		for ( PolicyKey policyKey : m_request.getPolicyKey() ) {
			if ( policyKey.getPolicyType() != null && ( policyKey.getPolicyName() != null || policyKey.getPolicyId() != null ) )
				return true;
		}
		return false;
	}

	 /**
	 * Output active policies only.
	 * 
	 * @return true, if successful
	 */
 	boolean outputActivePoliciesOnly() {
		String value = getQueryValue( "ActivePoliciesOnly" );
        return value == null ? true : value.equalsIgnoreCase( "TRUE" );

	}

	 /**
	 * Output all.
	 * 
	 * @return true, if successful
	 */
 	boolean outputAll() {
        return m_request.getOutputSelector() == null ? true : m_request.getOutputSelector() == PolicyOutputSelector.ALL;
    }
    
     /**
		 * Output rules.
		 * 
		 * @return true, if successful
		 */
     boolean outputRules() {
        return outputAll() || m_request.getOutputSelector() == PolicyOutputSelector.RULES;
    }
    

     /**
		 * Output resources.
		 * 
		 * @return true, if successful
		 */
     boolean outputResources() {
        return outputAll() || m_request.getOutputSelector() == PolicyOutputSelector.RESOURCES;
    }

     /**
		 * Output subjects.
		 * 
		 * @return true, if successful
		 */
     boolean outputSubjects() {
        return outputAll() || m_request.getOutputSelector() == PolicyOutputSelector.SUBJECTS;
    }
    
     /**
		 * Output subject groups.
		 * 
		 * @return true, if successful
		 */
     boolean outputSubjectGroups() {
        return outputAll() || m_request.getOutputSelector() == PolicyOutputSelector.SUBJECTS || 
        m_request.getOutputSelector() == PolicyOutputSelector.SUBJECTGROUPS;
    }

	  /**
	 * Checks if is target expand resources specified.
	 * 
	 * @return true, if is target expand resources specified
	 */
  	boolean isTargetExpandResourcesSpecified() {
	    return isQueryTypeSpecified( "ExpandResourceLevelPolicies" );
	 }
	 
	  /**
	 * Gets the search scope.
	 * 
	 * @return the search scope
	 */
  	String getSearchScope() {
	        String value = getQueryValue( "SubjectSearchScope" );
	        return value == null ? "TARGET" : value;
	 }


	 /**
	 * Find inclusions.
	 * 
	 * @return true, if successful
	 */
 	boolean findInclusions() {
			String searchScope = getSearchScope();
			return searchScope == null ? true : "TARGET".equalsIgnoreCase( searchScope.trim() ) || "BOTH".equalsIgnoreCase( searchScope.trim() );
	}
	
	 /**
	 * Find exclusions.
	 * 
	 * @return true, if successful
	 */
 	boolean findExclusions() {
		String searchScope = getSearchScope();
		return searchScope == null ? false : "EXCLUDED".equalsIgnoreCase( searchScope.trim() ) || "BOTH".equalsIgnoreCase( searchScope.trim() );
	}

	 /**
	 * Gets the query condition.
	 * 
	 * @return the query condition
	 */
 	QueryCondition getQueryCondition() {
		return m_request.getQueryCondition();
	}
}
