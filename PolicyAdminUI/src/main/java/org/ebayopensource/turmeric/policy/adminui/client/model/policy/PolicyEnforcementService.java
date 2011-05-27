/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * PolicyEnforcementService.
 */
public interface PolicyEnforcementService {
    
    /** The Constant SUBJECT_GROUP_DELETE_RESOURCE. */
    public static final String SUBJECT_GROUP_DELETE_RESOURCE = "SERVICE.PolicyService.deleteSubjectGroups";
    
    /** The Constant SUBJECT_GROUP_EDIT_RESOURCE. */
    public static final String SUBJECT_GROUP_EDIT_RESOURCE = "SERVICE.PolicyService.updateSubjectGroups";
    
    /** The Constant SUBJECT_GROUP_DELETE_OPERATION_NAME. */
    public static final String SUBJECT_GROUP_DELETE_OPERATION_NAME = "deleteSubjectGroups";
    
    /** The Constant SUBJECT_GROUP_OPERATION_NAME. */
    public static final String SUBJECT_GROUP_OPERATION_NAME = "updateSubjectGroups";
    
    /** The Constant POLICY_DELETE_RESOURCE. */
    public static final String POLICY_DELETE_RESOURCE = "SERVICE.PolicyService.deletePolicy";
    
    /** The Constant POLICY_EDIT_RESOURCE. */
    public static final String POLICY_EDIT_RESOURCE = "SERVICE.PolicyService.updatePolicy";
    
    /** The Constant POLICY_DELETE_OPERATION_NAME. */
    public static final String POLICY_DELETE_OPERATION_NAME = "deletePolicy";
    
    /** The Constant POLICY_EDIT_OPERATION_NAME. */
    public static final String POLICY_EDIT_OPERATION_NAME = "updatePolicy";
    
    /** The Constant POLICY_SERVICE_NAME. */
    public static final String POLICY_SERVICE_NAME = "PolicyService";
    
    /**
	 * Verify.
	 * 
	 * @param opKey
	 *            the op key
	 * @param policyTypes
	 *            the policy types
	 * @param credentials
	 *            the credentials
	 * @param subjectTypes
	 *            the subject types
	 * @param extendedInfo
	 *            the extended info
	 * @param accessControlObjects
	 *            the access control objects
	 * @param resourceType
	 *            the resource type
	 * @param callback
	 *            the callback
	 */
    public void verify (OperationKey opKey,
                        List<String> policyTypes,
                        Map<String,String> credentials,
                        List<String[]> subjectTypes,
                        Map<String,String> extendedInfo,
                        List<String> accessControlObjects,
                        String resourceType,
                        AsyncCallback<VerifyAccessResponse> callback);
    
    
    /**
	 * The Interface VerifyAccessResponse.
	 */
    public interface VerifyAccessResponse {
        //TODO add other data access methods if necessary
        /**
		 * Checks if is errored.
		 * 
		 * @return true, if is errored
		 */
        public boolean isErrored();
    }

}
