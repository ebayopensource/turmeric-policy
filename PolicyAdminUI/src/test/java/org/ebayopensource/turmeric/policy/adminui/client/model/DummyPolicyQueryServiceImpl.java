/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicyImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.OperationImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.OperationKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.QueryCondition;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupQuery;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectQuery;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreatePolicyResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectGroupsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeletePolicyResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeleteResourceResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DeleteSubjectGroupResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.DisablePolicyResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.EnablePolicyResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindExternalSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetEntityHistoryResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetMetaDataResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetPoliciesResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetResourcesResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.PolicyOutputSelector;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateMode;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdatePolicyResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateSubjectGroupsResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The Class DummyPolicyQueryServiceImpl.
 */
public class DummyPolicyQueryServiceImpl implements PolicyQueryService {
    private static long subjectGroupIdCounter = 0;
    
	public static Collection<GenericPolicyImpl> tmpPolicies;
	static {
		tmpPolicies = new ArrayList<GenericPolicyImpl>();
		GenericPolicyImpl policy = null;
		for (int i = 0; i < 12; i++) { 
			policy = new GenericPolicyImpl();
			policy.setId(new Long(i));
			policy.setName("Policy_" + i);
			policy.setDescription("A description");
			policy.setCreatedBy("jose");
			policy.setType("BlackList");
			policy.setLastModifiedBy("jose");
			policy.setCreationDate(new Date(System.currentTimeMillis()));
			policy.setLastModified(new Date(System.currentTimeMillis()));
			policy.setEnabled(true);

			if (i == 0 || i == 3 || i == 4 || i == 10 || i == 11) {
				List<SubjectGroup> subjectGroups = new ArrayList<SubjectGroup>();
				SubjectGroupImpl sg = null;

				for (int j = 0; j < 2; j++) {
					sg = new SubjectGroupImpl();
					sg.setName("SG_" + i);

					subjectGroups.add(sg);
				}
				policy.setSubjectGroups(subjectGroups);
			}

			if (i == 2 || i == 5 || i == 6 || i == 8 || i == 10) {
				policy.setEnabled(false);
			}

			tmpPolicies.add(policy);
		}

	}

	
	/**
	 * The Class CreateSubjectGroupsResponseImpl.
	 */
	public class CreateSubjectGroupsResponseImpl implements CreateSubjectGroupsResponse {

	    private List<Long> ids;
	    
	    /**
		 * Sets the subject group ids.
		 * 
		 * @param ids
		 *            the new subject group ids
		 */
    	public void setSubjectGroupIds(List<Long> ids) {
	        this.ids = ids;
	    }
        
        /* (non-Javadoc)
         * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectGroupsResponse#getSubjectGroupIds()
         */
        @Override
        public List<Long> getSubjectGroupIds() {
            return this.ids;
        }

        /* (non-Javadoc)
         * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectGroupsResponse#getErrorMessage()
         */
        @Override
        public String getErrorMessage() {
            return null;
        }

        /* (non-Javadoc)
         * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectGroupsResponse#isErrored()
         */
        @Override
        public boolean isErrored() {
            return false;
        }
	    
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#getResources(java.util.List, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	public void getResources(List<ResourceKey> keys,
	                         AsyncCallback<GetResourcesResponse> callback) {

	    final Collection<Resource> result = new ArrayList<Resource>();
	    if (keys == null) {
	        for (int iter = 1; iter < 15; iter++) {
	            ResourceImpl resource = new ResourceImpl();
	            resource.setId((long) iter);
	            resource.setResourceName("Resource" + iter);
	            resource.setDescription("Description" + iter);
	            resource.setResourceType("Resource Type" + iter);

	            List<Operation> opList = new ArrayList<Operation>();
	            OperationImpl op = new OperationImpl();
	            op.setOperationName("Operation_1");
	            opList.add(op);
	            OperationImpl op2 = new OperationImpl();
	            op2.setOperationName("Operation_2");
	            opList.add(op2);
	            OperationImpl op3 = new OperationImpl();
	            op3.setOperationName("Operation_3");
	            opList.add(op3);
	            
	            resource.setOpList(opList);

	            result.add(resource);
	        }
	    } else {
	        for (ResourceKey key:keys) {
	            if ("GENERIC".equals(key.getType())) {
	                for (int iter = 1; iter < 4; iter++) {
	                    ResourceImpl resource = new ResourceImpl();
	                    resource.setId((long) iter);
	                    resource.setResourceName("Resource"
	                                             + "GENERIC"
	                                             + iter);
	                    resource.setDescription("Description" + iter);
	                    resource.setResourceType("GENERIC");

	                    List<Operation> opList = new ArrayList<Operation>();
	    	            OperationImpl op = new OperationImpl();
	    	            op.setOperationName("Operation_1");
	    	            opList.add(op);
	    	            OperationImpl op2 = new OperationImpl();
	    	            op2.setOperationName("Operation_2");
	    	            opList.add(op2);
	    	            OperationImpl op3 = new OperationImpl();
	    	            op3.setOperationName("Operation_3");
	    	            opList.add(op3);
	    	            
	    	            resource.setOpList(opList);

	                    result.add(resource);
	                }

	            } else if ("SERVICE".equals(key.getType())) {
	                for (int iter = 4; iter < 6; iter++) {
	                    ResourceImpl resource = new ResourceImpl();
	                    resource.setId((long) iter);
	                    resource.setResourceName("Resource"
	                                             + "SERVICE"
	                                             + iter);
	                    resource.setDescription("Description" + iter);
	                    resource.setResourceType("SERVICE");

	                    List<Operation> opList = new ArrayList<Operation>();
	    	            OperationImpl op = new OperationImpl();
	    	            op.setOperationName("Operation_1");
	    	            opList.add(op);
	    	            OperationImpl op2 = new OperationImpl();
	    	            op2.setOperationName("Operation_2");
	    	            opList.add(op2);
	    	            OperationImpl op3 = new OperationImpl();
	    	            op3.setOperationName("Operation_3");
	    	            opList.add(op3);
	    	            
	    	            resource.setOpList(opList);

	                    result.add(resource);
	                }

	            } else if ("URL".equals(key.getType())) {
	                for (int iter = 6; iter < 11; iter++) {
	                    ResourceImpl resource = new ResourceImpl();
	                    resource.setId((long) iter);
	                    resource.setResourceName("Resource"
	                                             + "URL" + iter);
	                    resource.setDescription("Description" + iter);
	                    resource.setResourceType("URL");

	                    List<Operation> opList = new ArrayList<Operation>();
	    	            OperationImpl op = new OperationImpl();
	    	            op.setOperationName("Operation_1");
	    	            opList.add(op);
	    	            OperationImpl op2 = new OperationImpl();
	    	            op2.setOperationName("Operation_2");
	    	            opList.add(op2);
	    	            OperationImpl op3 = new OperationImpl();
	    	            op3.setOperationName("Operation_3");
	    	            opList.add(op3);
	    	            
	    	            resource.setOpList(opList);

	                    result.add(resource);
	                }
	            } else if ("OBJECT".equals(key.getType())) {
	                for (int iter = 11; iter < 16; iter++) {
	                    ResourceImpl resource = new ResourceImpl();
	                    resource.setId((long) iter);
	                    resource.setResourceName("Resource"
	                                             + "OBJECT"
	                                             + iter);
	                    resource.setDescription("Description" + iter);
	                    resource.setResourceType("OBJECT");

	                    List<Operation> opList = new ArrayList<Operation>();
	    	            OperationImpl op = new OperationImpl();
	    	            op.setOperationName("Operation_1");
	    	            opList.add(op);
	    	            OperationImpl op2 = new OperationImpl();
	    	            op2.setOperationName("Operation_2");
	    	            opList.add(op2);
	    	            OperationImpl op3 = new OperationImpl();
	    	            op3.setOperationName("Operation_3");
	    	            opList.add(op3);
	    	            
	    	            resource.setOpList(opList);


	                    result.add(resource);
	                }
	            }
	        }
	    }
		
		
		GetResourcesResponse response = new GetResourcesResponse() {
            public Collection<Resource> getResources() {
                return result;
            }

            public String getErrorMessage() {
                return null;
            }

            public boolean isErrored() {
                return false;
            }
		};
		
		callback.onSuccess(response);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#deleteResources(java.util.List, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	public void deleteResources(List<ResourceKey> keys,
	                           AsyncCallback<DeleteResourceResponse> callback) {
	    DeleteResourceResponse response = new DeleteResourceResponse() {
            @Override
            public String getErrorMessage() {
                return null;
            }

            @Override
            public boolean isErrored() {
                return false;
            }  
	    };
	    callback.onSuccess(response);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#enablePolicy(org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyKey, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	public void enablePolicy(PolicyKey key, AsyncCallback<EnablePolicyResponse> callback) {
	    EnablePolicyResponse response = new EnablePolicyResponse() {
	        @Override
	        public String getErrorMessage() {
	            return null;
	        }

	        @Override
	        public boolean isErrored() {
	            return false;
	        }
	    };
	    callback.onSuccess(response);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#disablePolicy(org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyKey, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	public void disablePolicy(PolicyKey key, AsyncCallback<DisablePolicyResponse> callback) {
	    DisablePolicyResponse response = new DisablePolicyResponse() {
	        @Override
	        public String getErrorMessage() {
	            return null;
	        }

	        @Override
	        public boolean isErrored() {
	            return false;
	        }
	    };
	    callback.onSuccess(response);
	}

	/*
	public void submitTicketTraceForPolicy(
			SubmitTicketTraceForPolicyRequest request,
			AsyncCallback<SubmitTicketTraceForPolicyResponse> callback) {
		SubmitTicketTraceForPolicyResponse response = new SubmitTicketTraceForPolicyResponse();
		response.setResult(Boolean.TRUE);
		callback.onSuccess(response);
	}
	*/

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#deletePolicy(org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyKey, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	public void deletePolicy(PolicyKey key, AsyncCallback<DeletePolicyResponse> callback) {
		DeletePolicyResponse response = new DeletePolicyResponse() 
		{
            @Override
            public Boolean isSuccess() {
                return Boolean.TRUE;
            }

            @Override
            public String getErrorMessage() {
                return null;
            }

            @Override
            public boolean isErrored() {
                return false;
            }
		    
		};
		callback.onSuccess(response);
	}

	/*
	public void deployPolicy(SubmitTicketTraceForPolicyRequest request,
			AsyncCallback<SubmitTicketTraceForPolicyResponse> callback) {
		SubmitTicketTraceForPolicyResponse response = new SubmitTicketTraceForPolicyResponse();
		response.setResult(Boolean.TRUE);
		callback.onSuccess(response);
	}
	 */
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#findPolicies(java.lang.Long, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.PolicyOutputSelector, org.ebayopensource.turmeric.policy.adminui.client.model.policy.QueryCondition, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void findPolicies(Long sinceLastModifiedTime,
	                  List<PolicyKey> keys, 
	                  List<ResourceKey> resKeys,
	                  List<OperationKey> opKeys,
	                  List<SubjectKey> subjectKeys,
	                  List<SubjectGroupKey> subjectGroupKeys,
	                  PolicyOutputSelector outputSelector, 
	                  QueryCondition condition, 
	                  AsyncCallback<GetPoliciesResponse> callback) {
	    
	    if (keys == null)
	        return;

	    //Only honour a single key for each list

	    //get by matching subjectgroup
	    if (subjectGroupKeys != null) {
	        final Collection<GenericPolicy> policies = new ArrayList<GenericPolicy>();

	        //search by name & type
	        if (subjectGroupKeys.get(0).getName() != null) {
                //crappy pattern match - assume wildcards at head and tail
                String name = subjectGroupKeys.get(0).getName().replace('%', ' ').trim();
                for (GenericPolicyImpl genericPolicy : tmpPolicies) {
                    List<SubjectGroup> subjectGroups = genericPolicy.getSubjectGroups();
                    if (subjectGroups != null) {

                        for (SubjectGroup subjectGroup : subjectGroups) {
                            if (subjectGroup.getName().toLowerCase().contains(name.toLowerCase())
                                    && subjectGroup.getType().equalsIgnoreCase(subjectGroupKeys.get(0).getType())) {
                                policies.add(genericPolicy);
                                break;
                            }
                        }
                    }
                }
            } else {
                //search by exact id
                for (GenericPolicyImpl genericPolicy : tmpPolicies) {
                    if (genericPolicy.getId().longValue()==keys.get(0).getId())
                        policies.add(genericPolicy);
                }
            }

            GetPoliciesResponse response = new GetPoliciesResponse() {
                @Override
                public String getErrorMessage() {
                    return null;
                }

                @Override
                public boolean isErrored() {
                    return false;
                }

                @Override
                public Collection<GenericPolicy> getPolicies() {
                    return policies;
                }
            };

            callback.onSuccess(response);
            return;
        }

        //get by matching policy info
        final Collection<GenericPolicy> policies = new ArrayList<GenericPolicy>();
        if (keys.get(0).getName() != null) {
            //really rubbish pattern match - just get rid of leading/trailing wildcard
            String name = keys.get(0).getName().replace('%', ' ').trim();

            for (GenericPolicyImpl genericPolicy : tmpPolicies) {
                if (genericPolicy.getName().toLowerCase().equals(name.toLowerCase())){
                    policies.add(genericPolicy);
                }
            }
        }   else if (keys.get(0).getId() !=null ) {
            for (GenericPolicyImpl genericPolicy : tmpPolicies) {
                if (genericPolicy.getId().longValue() == keys.get(0).getId())
                    policies.add(genericPolicy);
            }
        }

        GetPoliciesResponse response = new GetPoliciesResponse() {   
            @Override
            public String getErrorMessage() {
                return null;
            }

            @Override
            public boolean isErrored() {
                return false;
            }

            @Override
            public Collection<GenericPolicy> getPolicies() {
                return policies;
            }
        };

        callback.onSuccess(response);
        return;
	}

	


	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#deleteSubjectGroups(java.util.List, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void deleteSubjectGroups(List<SubjectGroupKey> keys,
			AsyncCallback<DeleteSubjectGroupResponse> callback) {
		
		DeleteSubjectGroupResponse response = new DeleteSubjectGroupResponse() {
            @Override
            public String getErrorMessage() {
                return null;
            }
            @Override
            public boolean isErrored() {
                return false;
            }
		};
		
		callback.onSuccess(response);
	}

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#createSubjectGroups(java.util.List, com.google.gwt.user.client.rpc.AsyncCallback)
     */
	@Override
	public void createSubjectGroups(List<SubjectGroup> groups,
	                                AsyncCallback<CreateSubjectGroupsResponse> callback) {
	    List<Long> ids = new ArrayList<Long>();
	    CreateSubjectGroupsResponseImpl impl = new CreateSubjectGroupsResponseImpl();
	    if (groups != null) {
	    	for (SubjectGroup g:groups) {
	            ids.add(new Long(subjectGroupIdCounter++));
	        }
	    }
	    impl.setSubjectGroupIds(ids);
	    callback.onSuccess(impl);
	}

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#createPolicy(org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy, com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void createPolicy(GenericPolicy policy,
                             AsyncCallback<CreatePolicyResponse> callback) {
        
        
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#findSubjectGroups(org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupQuery, com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void findSubjectGroups(
                                  SubjectGroupQuery query,
                                  AsyncCallback<FindSubjectGroupsResponse> callback) {
        
        
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#findSubjects(org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectQuery, com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void findSubjects(SubjectQuery query,
                             AsyncCallback<FindSubjectsResponse> callback) {
        
        
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#getMetaData(org.ebayopensource.turmeric.policy.adminui.client.model.policy.QueryCondition, com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void getMetaData(QueryCondition condition,
                            AsyncCallback<GetMetaDataResponse> callback) {
        
        
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#updateSubjectGroups(java.util.List, org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateMode, com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void updateSubjectGroups(
                                    List<SubjectGroup> groups,
                                    UpdateMode mode,
                                    AsyncCallback<UpdateSubjectGroupsResponse> callback) {
        
        
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#updatePolicy(org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateMode, org.ebayopensource.turmeric.policy.adminui.client.model.GenericPolicy, com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void updatePolicy(UpdateMode mode, GenericPolicy policy,
                             AsyncCallback<UpdatePolicyResponse> callback) {
        
        
    }

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#getEntityHistory(java.lang.Long, java.lang.Long, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void getEntityHistory(Long startDate, Long endDate,
			List<PolicyKey> polKeys, List<ResourceKey> resKeys,
			List<OperationKey> opKeys, List<SubjectKey> subjectKeys,
			List<SubjectGroupKey> subjectGroupKeys,
			AsyncCallback<GetEntityHistoryResponse> callback) {
		
		
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#findExternalSubjects(org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectQuery, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	public void findExternalSubjects(SubjectQuery query,
			AsyncCallback<FindExternalSubjectsResponse> callback) {
		
		
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService#createSubjects(java.util.List, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void createSubjects(List<Subject> subject,
			AsyncCallback<CreateSubjectsResponse> callback) {
		
		
	}
}
