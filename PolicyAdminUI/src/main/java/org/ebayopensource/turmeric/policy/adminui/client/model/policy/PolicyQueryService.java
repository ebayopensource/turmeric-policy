/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The Interface PolicyQueryService.
 */
public interface PolicyQueryService extends PolicyAdminUIService {

    /**
	 * The Enum ResourceLevel.
	 */
    public enum ResourceLevel {
        OPERATION, RESOURCE, GLOBAL
    };
    
	/**
	 * The Enum PolicyOutputSelector.
	 */
	public enum PolicyOutputSelector {
	    ALL, RESOURCES, SUBJECTS, RULES, SUBJECTGROUPS
	}
	
	/**
	 * The Enum UpdateMode.
	 */
	public enum UpdateMode {
	    REPLACE, UPDATE, DELETE
	}
	
	/**
	 * The Enum RuleEffectType.
	 */
	public enum RuleEffectType {
	    Allow, Flag, Challenge, Block, Softlimit
	}
	
	void createPolicy(GenericPolicy policy, AsyncCallback<CreatePolicyResponse> callback);
	
	void createSubjects(List<Subject> subject, AsyncCallback<CreateSubjectsResponse> callback);
	
	void createSubjectGroups (List<SubjectGroup> groups, AsyncCallback<CreateSubjectGroupsResponse> callback);

	void enablePolicy(PolicyKey key, AsyncCallback<EnablePolicyResponse> callback);

	void disablePolicy(PolicyKey key, AsyncCallback<DisablePolicyResponse> callback);

	void deletePolicy(PolicyKey key, AsyncCallback<DeletePolicyResponse> callback);

	void getResources(List<ResourceKey> keys, AsyncCallback<GetResourcesResponse> callback);

	void getEntityHistory(Long startDate, Long endDate,
            List<PolicyKey> polKeys, 
            List<ResourceKey> resKeys,
            List<OperationKey> opKeys,
            List<SubjectKey> subjectKeys,
            List<SubjectGroupKey> subjectGroupKeys, 
            AsyncCallback<GetEntityHistoryResponse> callback);
	
	void deleteSubjectGroups(List<SubjectGroupKey> keys, AsyncCallback<DeleteSubjectGroupResponse> callback);

	void findPolicies(Long sinceLastModifiedTime,
	                  List<PolicyKey> keys, 
	                  List<ResourceKey> resKeys,
	                  List<OperationKey> opKeys,
	                  List<SubjectKey> subjectKeys,
	                  List<SubjectGroupKey> subjectGroupKeys,
	                  PolicyOutputSelector outputSelector, 
	                  QueryCondition condition, 
	                  AsyncCallback<GetPoliciesResponse> callback);


	void deleteResources (List<ResourceKey> keys, AsyncCallback<DeleteResourceResponse> callback);
	
	void findSubjectGroups (SubjectGroupQuery query, AsyncCallback<FindSubjectGroupsResponse> callback);
	
	void findSubjects (SubjectQuery query, AsyncCallback<FindSubjectsResponse> callback);
	
	void findExternalSubjects(SubjectQuery query, AsyncCallback<FindExternalSubjectsResponse> callback);

	void getMetaData (QueryCondition condition, AsyncCallback<GetMetaDataResponse> callback);
	
	void updateSubjectGroups (List<SubjectGroup> groups, UpdateMode mode, AsyncCallback<UpdateSubjectGroupsResponse> callback);
	
	void updatePolicy (UpdateMode mode, GenericPolicy policy, AsyncCallback<UpdatePolicyResponse> callback);

	/**
	 * The Interface GetResourcesResponse.
	 */
	public interface GetResourcesResponse {
	    
    	/**
		 * Gets the resources.
		 * 
		 * @return the resources
		 */
    	Collection<Resource> getResources();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface GetEntityHistoryResponse.
	 */
	public interface GetEntityHistoryResponse {
	    
    	/**
		 * Gets the entities.
		 * 
		 * @return the entities
		 */
    	Collection<EntityHistory> getEntities();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface GetPoliciesResponse.
	 */
	public interface GetPoliciesResponse {
	    
    	/**
		 * Gets the policies.
		 * 
		 * @return the policies
		 */
    	Collection<GenericPolicy> getPolicies();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface EnablePolicyResponse.
	 */
	public interface EnablePolicyResponse  {
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}  

	/**
	 * The Interface DisablePolicyResponse.
	 */
	public interface DisablePolicyResponse  {
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface DeletePolicyResponse.
	 */
	public interface DeletePolicyResponse {
		
		/**
		 * Checks if is success.
		 * 
		 * @return the boolean
		 */
		Boolean isSuccess();       
		
		/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
		boolean isErrored();
        
        /**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
        String getErrorMessage();
	}

	/**
	 * The Interface DeleteSubjectGroupResponse.
	 */
	public interface DeleteSubjectGroupResponse {
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface DeleteResourceResponse.
	 */
	public interface DeleteResourceResponse {
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface CreateSubjectGroupsResponse.
	 */
	public interface CreateSubjectGroupsResponse {
	    
    	/**
		 * Gets the subject group ids.
		 * 
		 * @return the subject group ids
		 */
    	List<Long> getSubjectGroupIds();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
        
        /**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
        String getErrorMessage();
	}

	/**
	 * The Interface CreatePolicyResponse.
	 */
	public interface CreatePolicyResponse {
	    
    	/**
		 * Gets the policy id.
		 * 
		 * @return the policy id
		 */
    	Long getPolicyId();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface CreateSubjectsResponse.
	 */
	public interface CreateSubjectsResponse {
	    
    	/**
		 * Gets the subject ids.
		 * 
		 * @return the subject ids
		 */
    	List<Long> getSubjectIds();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface FindSubjectGroupsResponse.
	 */
	public interface FindSubjectGroupsResponse {
	    
    	/**
		 * Gets the groups.
		 * 
		 * @return the groups
		 */
    	List<SubjectGroup> getGroups();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface FindSubjectsResponse.
	 */
	public interface FindSubjectsResponse {
	    
    	/**
		 * Gets the subjects.
		 * 
		 * @return the subjects
		 */
    	List<Subject> getSubjects();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}
	
	/**
	 * The Interface FindExternalSubjectsResponse.
	 */
	public interface FindExternalSubjectsResponse {
	    
    	/**
		 * Gets the subjects.
		 * 
		 * @return the subjects
		 */
    	List<Subject> getSubjects();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface GetMetaDataResponse.
	 */
	public interface GetMetaDataResponse {
	    
    	/**
		 * Gets the values.
		 * 
		 * @return the values
		 */
    	Map<String,String> getValues();
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface UpdateSubjectGroupsResponse.
	 */
	public interface UpdateSubjectGroupsResponse {
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}

	/**
	 * The Interface UpdatePolicyResponse.
	 */
	public interface UpdatePolicyResponse { 
	    
    	/**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
    	boolean isErrored();
	    
    	/**
		 * Gets the error message.
		 * 
		 * @return the error message
		 */
    	String getErrorMessage();
	}
}
