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

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.ebayopensource.turmeric.policyservice.exceptions.PolicyCreationException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyDeleteException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyFinderException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyUpdateException;
import org.ebayopensource.turmeric.policyservice.model.AuditHistory;
import org.ebayopensource.turmeric.policyservice.model.ResourceDAO;
import org.ebayopensource.turmeric.policyservice.model.ResourceDAOImpl;
import org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider;
import org.ebayopensource.turmeric.policyservice.provider.common.OperationEditObject;
import org.ebayopensource.turmeric.security.v1.services.EntityHistory;
import org.ebayopensource.turmeric.security.v1.services.KeyValuePair;
import org.ebayopensource.turmeric.security.v1.services.Operation;
import org.ebayopensource.turmeric.security.v1.services.OperationKey;
import org.ebayopensource.turmeric.security.v1.services.Resource;
import org.ebayopensource.turmeric.security.v1.services.ResourceKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;
import org.ebayopensource.turmeric.utils.jpa.JPAAroundAdvice;
import org.ebayopensource.turmeric.utils.jpa.PersistenceContext;
import org.ebayopensource.turmeric.utils.jpa.model.AuditContext;


/**
 * The Class ResourceBase.
 */
public abstract class ResourceBase implements ResourceTypeProvider {
	
	/** The factory. */
	protected EntityManagerFactory factory;
	
	/** The impl. */
	protected ResourceTypeProvider impl;

	/**
	 * Gets the resource type.
	 * 
	 * @return the resource type
	 */
	public abstract String getResourceType();

	/**
	 * Instantiates a new resource base.
	 */
	public ResourceBase() {
		factory = PersistenceContext
				.createEntityManagerFactory("policyservice");

		final ClassLoader classLoader = ResourceTypeProvider.class
				.getClassLoader();
		final Class[] interfaces = { ResourceTypeProvider.class };
		final ResourceTypeProvider target = new ResourceBaseImpl(
				getResourceType());
		impl = (ResourceTypeProvider) Proxy.newProxyInstance(classLoader,
				interfaces, new JPAAroundAdvice(factory, target));
	}

	/**
	 * The Class ResourceBaseImpl.
	 */
	public static class ResourceBaseImpl implements ResourceTypeProvider {
		private final ResourceDAO resourceDAO;
		private final String resourceType;

		/**
		 * Instantiates a new resource base impl.
		 * 
		 * @param resourceType
		 *            the resource type
		 */
		public ResourceBaseImpl(final String resourceType) {
			this.resourceType = resourceType;
			resourceDAO = new ResourceDAOImpl();
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#createResource(org.ebayopensource.turmeric.security.v1.services.Resource, org.ebayopensource.turmeric.policyservice.provider.common.OperationEditObject, org.ebayopensource.turmeric.security.v1.services.SubjectKey)
		 */
		@Override
		public ResourceKey createResource(final Resource resource,
				final OperationEditObject operationEditObject,
				final SubjectKey createdBy) throws PolicyCreationException {
			if (createdBy != null) {
				AuditContext.setUser(createdBy.getSubjectName());
			}
			try {
				final org.ebayopensource.turmeric.policyservice.model.Resource jpaResource = 
				    ResourceDAOImpl.convert(resource);
				resourceDAO.persistResource(jpaResource);
				final ResourceKey resourceKey = new ResourceKey();
				resourceKey.setResourceId(jpaResource.getId());
				resourceKey.setResourceName(resource.getResourceName());
				resourceKey.setResourceType(resource.getResourceType());

				if (operationEditObject != null) {
					final List<String> addOpNameList = operationEditObject
							.getAddList();
					if (addOpNameList != null && !addOpNameList.isEmpty()) {
						createOperation(resourceKey.getResourceId(),
								addOpNameList, createdBy);
					}
				}
				return resourceKey;
			} finally {
				AuditContext.clear();
			}

		}

		private List<OperationKey> createOperation(final Long resourceId,
				final List<String> operationNameList, final SubjectKey createdBy)
				throws PolicyCreationException{

			final List<OperationKey> operationKeyList = new ArrayList<OperationKey>();

			if (operationNameList != null && !operationNameList.isEmpty()) {

				OperationKey operationKey = null;
				org.ebayopensource.turmeric.policyservice.model.Resource resourceParent = null;

				resourceParent = resourceDAO.findResourceById(resourceId);
                Set<org.ebayopensource.turmeric.policyservice.model.Operation> jpaOperationSet = 
                    resourceParent.getOperations();

				for (String operationName : operationNameList) {
					// description is not necessary
					final org.ebayopensource.turmeric.policyservice.model.Operation jpaOperation = 
					    new org.ebayopensource.turmeric.policyservice.model.Operation(operationName, null);

					jpaOperation.setResource(resourceParent);
					resourceDAO.persistOperation(jpaOperation);
					jpaOperationSet.add(jpaOperation);

					operationKey = new OperationKey();
					operationKey.setOperationId(jpaOperation.getId());
					operationKey.setOperationName(jpaOperation.getOperationName());
					operationKey.setResourceName(resourceParent.getResourceName());
					operationKey.setResourceType(resourceParent.getResourceType());

					operationKeyList.add(operationKey);
					try {
						audit(operationKey, resourceParent.getResourceType(), createdBy);
					} catch (PolicyFinderException e) {
						//	do nothing
					}
				}
			}

			return operationKeyList;
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#updateResource(org.ebayopensource.turmeric.security.v1.services.Resource, org.ebayopensource.turmeric.policyservice.provider.common.OperationEditObject, org.ebayopensource.turmeric.security.v1.services.SubjectKey)
		 */
		@Override
		public ResourceKey updateResource(final Resource resource,
				final OperationEditObject operationEditObject,
				final SubjectKey modifiedBy) throws PolicyUpdateException,
				PolicyCreationException, PolicyDeleteException {
			if (modifiedBy != null) {
				AuditContext.setUser(modifiedBy.getSubjectName());
			}
			try {
				org.ebayopensource.turmeric.policyservice.model.Resource jpaResource = resourceDAO
						.findResourceById(resource.getResourceId());
				// Resource Info
				if (resource.getResourceType() != null) {
				    jpaResource.setResourceType(resource.getResourceType());
				}
				if (resource.getDescription() != null) {
				    jpaResource.setDescription(resource.getDescription());
				}
				if (resource.getResourceName() != null) {
				    jpaResource.setResourceName(resource.getResourceName());
				}

				ResourceKey resourceKey = new ResourceKey();
				resourceKey.setResourceId(jpaResource.getId());
				resourceKey.setResourceName(jpaResource.getResourceName());
				resourceKey.setResourceType(jpaResource.getResourceType());

				Set<org.ebayopensource.turmeric.policyservice.model.Operation> jpaOperationSet = 
				        jpaResource.getOperations();

                // adding operations
				for (String operationName : operationEditObject.getAddList()) {
					final org.ebayopensource.turmeric.policyservice.model.Operation operation = 
					    new org.ebayopensource.turmeric.policyservice.model.Operation(operationName, null);
					
                    operation.setResource(jpaResource);
                    resourceDAO.persistOperation(operation);
					jpaOperationSet.add(operation);
				}

				// removing operations
				for (String operationName : operationEditObject.getRemoveList()) {
					org.ebayopensource.turmeric.policyservice.model.Operation operation = 
					    resourceDAO.findOperationByName(resource.getResourceName(),
					                                    operationName,
					                                    resource.getResourceType());
					if (operation != null) {
						resourceDAO.removeOperation(resourceKey.getResourceId(), operation.getOperationName());
                        jpaOperationSet.remove(operation);
					}
				}
				
//				resourceDAO.persistResource(jpaResource);
				return resourceKey;
			} finally {
				AuditContext.clear();
			}
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#deleteResource(java.lang.Long)
		 */
		@Override
		public void deleteResource(final Long resourceId)
				throws PolicyFinderException, PolicyDeleteException {

			List<Operation> operations = getOperationByResourceId(resourceId);

			List<String> removeList = new ArrayList<String>();

			for (Operation op : operations) {
				removeList.add(op.getOperationName());
			}

			removeOperation(resourceId, removeList);

			removeResourceInfo(resourceId);
		}

		private void removeResourceInfo(final Long resourceId)
				throws PolicyDeleteException {
			resourceDAO.removeResource(resourceId);
		}

		private void removeOperation(final Long resourceId,
				final List<String> operationNameList)
				throws PolicyDeleteException {
			for (String operationName : operationNameList) {
				resourceDAO.removeOperation(resourceId, operationName);
			}
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getResourceInfoById(java.lang.Long)
		 */
		@Override
		public Resource getResourceInfoById(final Long resourceId)
				throws PolicyFinderException {
			org.ebayopensource.turmeric.policyservice.model.Resource jpaResource = resourceDAO
					.findResourceById(resourceId);
			if (jpaResource != null) {
				return ResourceDAOImpl.convert(jpaResource);
			}
			
			return null;
			
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getResourceInfoByName(java.lang.String)
		 */
		@Override
		public Resource getResourceInfoByName(final String resourceName)
				throws PolicyFinderException {
			org.ebayopensource.turmeric.policyservice.model.Resource jpaResource = resourceDAO
					.findResourceByName(resourceName);
			if (jpaResource != null) {
				return ResourceDAOImpl.convert(jpaResource);
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getResourceInfoByType()
		 */
		@Override
		public Map<Long, Resource> getResourceInfoByType()
				throws PolicyFinderException {

			final Map<Long, Resource> result = new HashMap<Long, Resource>();
			final List<org.ebayopensource.turmeric.policyservice.model.Resource> jpaResourceList = resourceDAO
					.findResourceByType(resourceType);

			if (jpaResourceList != null && !jpaResourceList.isEmpty()) {
				for (org.ebayopensource.turmeric.policyservice.model.Resource jpaResource : jpaResourceList) {
					result.put(jpaResource.getId(), ResourceDAOImpl.convert(jpaResource));
				}
			}

			return result;
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getOperationById(java.lang.String, java.lang.Long)
		 */
		@Override
		public Operation getOperationById(final String resourceName,
				final Long operationId) throws PolicyFinderException {
			return getOperationById(operationId);
		}

		private Operation getOperationById(final Long operationId)
				throws PolicyFinderException {
			final org.ebayopensource.turmeric.policyservice.model.Operation jpaOperation = resourceDAO
					.findOperationById(operationId);
			if (jpaOperation != null) {
				return ResourceDAOImpl.convert(jpaOperation);
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getOperationByName(java.lang.String, java.lang.String)
		 */
		@Override
		public Operation getOperationByName(final String resourceName,
				final String operationName) throws PolicyFinderException {
			final org.ebayopensource.turmeric.policyservice.model.Operation jpaOperation = resourceDAO
					.findOperationByName(resourceName, operationName,
							resourceType);
			if (jpaOperation != null) {
				return ResourceDAOImpl.convert(jpaOperation);
			}
			return null;
		
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getOperationByResourceId(java.lang.Long)
		 */
		@Override
		public List<Operation> getOperationByResourceId(final Long resourceId)
				throws PolicyFinderException {
			final List<Operation> result = new ArrayList<Operation>();

			final Set<org.ebayopensource.turmeric.policyservice.model.Operation> jpaOperations = resourceDAO
					.findOperationByResourceId(resourceId);
			if (jpaOperations != null && !jpaOperations.isEmpty()) {
				for (org.ebayopensource.turmeric.policyservice.model.Operation jpaOperation : jpaOperations) {
					result.add(ResourceDAOImpl.convert(jpaOperation));
				}
			}
			return result;

		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getResourceInfoByOperationId(java.lang.Long)
		 */
		@Override
		public Resource getResourceInfoByOperationId(final Long operationId)
				throws PolicyFinderException {
			final org.ebayopensource.turmeric.policyservice.model.Resource jpaResource = resourceDAO
					.findResourceByOperationId(operationId);
			if (jpaResource != null) {
				return ResourceDAOImpl.convert(jpaResource);
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getAuditHistory(org.ebayopensource.turmeric.security.v1.services.ResourceKey, javax.xml.datatype.XMLGregorianCalendar, javax.xml.datatype.XMLGregorianCalendar)
		 */
		@Override
		public List<EntityHistory> getAuditHistory(
				final ResourceKey resourceKey,
				final XMLGregorianCalendar startDate,
				final XMLGregorianCalendar endDate)
				throws PolicyFinderException {
     
			String resourceType = resourceKey.getResourceType();
			if (resourceType != null) {
				return getAuditHistoryByResourceType(startDate, endDate,
						resourceType);
			}

			return getAuditHistoryByIdOrName(resourceKey, startDate, endDate);
        }
		
		private List<EntityHistory> getAuditHistoryByResourceType(
				XMLGregorianCalendar startDate, XMLGregorianCalendar endDate, String resourceType) {
			List<EntityHistory> entityHistory = new ArrayList<EntityHistory>();
			List<AuditHistory> auditHistory = resourceDAO.getResourceHistory(
					resourceType, 
		            startDate.toGregorianCalendar().getTime(), 
		            endDate.toGregorianCalendar().getTime());            		
		    
			for (AuditHistory entry : auditHistory) {
		        entityHistory.add(AuditHistory.convert(entry));
		    }
			
			return entityHistory;
		}
		
		private List<EntityHistory> getAuditHistoryByIdOrName(
				ResourceKey resourceKey, XMLGregorianCalendar startDate,
				XMLGregorianCalendar endDate) {
		       List<EntityHistory> entityHistory = new ArrayList<EntityHistory>();
	            
	            Long resourceId = resourceKey.getResourceId();
	            if (resourceId == null) {
	                org.ebayopensource.turmeric.policyservice.model.Resource resource = 
	                    resourceDAO.findResourceByName(resourceKey.getResourceName());
	                if (resource != null) {
	                    resourceId = resource.getId();
	                }
	            }
	            
	            if (resourceId != null) {
	                List<AuditHistory> auditHistory = resourceDAO.getResourceHistory(
	                                resourceId.longValue(), 
	                                startDate.toGregorianCalendar().getTime(), 
	                                endDate.toGregorianCalendar().getTime());
	                
	                for (AuditHistory entry : auditHistory) {
	                    entityHistory.add(AuditHistory.convert(entry));
	                }
	            }
	            
	            return entityHistory;			
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getAuditHistory(org.ebayopensource.turmeric.security.v1.services.OperationKey, javax.xml.datatype.XMLGregorianCalendar, javax.xml.datatype.XMLGregorianCalendar)
		 */
		@Override
		public List<EntityHistory> getAuditHistory(
				final OperationKey operationKey,
				final XMLGregorianCalendar startDate,
				final XMLGregorianCalendar endDate)
				throws PolicyFinderException {
           
			String resourceType = operationKey.getResourceType();
			if (resourceType != null) {
				return getAuditHistoryByOperationType(startDate, endDate,
						resourceType);
			}

			return getAuditHistoryByIdOrName(operationKey, startDate, endDate);
        }
		
		private List<EntityHistory> getAuditHistoryByOperationType(
				XMLGregorianCalendar startDate, XMLGregorianCalendar endDate, String operationType) {
			List<EntityHistory> entityHistory = new ArrayList<EntityHistory>();
//			List<org.ebayopensource.turmeric.policyservice.model.Resource> allResources= resourceDAO.findResourceByType(resourceType);
//			List<org.ebayopensource.turmeric.policyservice.model.Operation> allOperations = new ArrayList<org.ebayopensource.turmeric.policyservice.model.Operation>();
//			for (org.ebayopensource.turmeric.policyservice.model.Resource resource : allResources) {
//				List<org.ebayopensource.turmeric.policyservice.model.Operation> operations = new ArrayList<org.ebayopensource.turmeric.policyservice.model.Operation>(resourceDAO.findOperationByResourceId(resource.getId().longValue()));	
//				allOperations.addAll(operations);
//			}
//						
//			for (org.ebayopensource.turmeric.policyservice.model.Operation operation : allOperations) {
				
				List<AuditHistory> auditHistory = resourceDAO.getOperationHistory(
						resourceType, 
			            startDate.toGregorianCalendar().getTime(), 
			            endDate.toGregorianCalendar().getTime());            		
			    
				for (AuditHistory entry : auditHistory) {
			        entityHistory.add(AuditHistory.convert(entry));
			    }
//			}
			
			return entityHistory;
		}
		
		
		private List<EntityHistory> getAuditHistoryByIdOrName(
				OperationKey operationKey, XMLGregorianCalendar startDate,
				XMLGregorianCalendar endDate) {
			 List<EntityHistory> entityHistory = new ArrayList<EntityHistory>();
	            
	            Long operationId = operationKey.getOperationId();
	            if (operationId == null) {
	                org.ebayopensource.turmeric.policyservice.model.Operation operation = 
	                    resourceDAO.findOperationByName(operationKey.getResourceName(), operationKey.getOperationName(),operationKey.getResourceType());
	                if (operation != null) {
	                	operationId = operation.getId();
	                }
	            }
	            
	            if (operationId != null) {
	                List<AuditHistory> auditHistory = resourceDAO.getOperationHistory(
	                                operationId.longValue(), 
	                                startDate.toGregorianCalendar().getTime(), 
	                                endDate.toGregorianCalendar().getTime());
	                
	                for (AuditHistory entry : auditHistory) {
	                    entityHistory.add(AuditHistory.convert(entry));
	                }
	            }
	            
	            return entityHistory;
		}
		       
		       
		       

		/**
		 * {@inheritDoc}
		 * 
		 */
		public void audit(final ResourceKey resourceKey,
				final String operationType, final SubjectKey loginSubject)
				throws PolicyFinderException {
		    resourceDAO.audit(resourceKey, operationType, loginSubject);
		}

		/**
		 * {@inheritDoc}
		 */
		public void audit(final OperationKey operationKey,
				final String operationType, final SubjectKey loginSubject)
				throws PolicyFinderException {
		    resourceDAO.audit(operationKey, operationType, loginSubject);
		}
        
        /* (non-Javadoc)
         * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getMetaData(java.lang.String)
         */
        @Override
        public List<KeyValuePair> getMetaData(String queryValue) throws PolicyFinderException {
            if (queryValue.equals("Type"))
            {
                    List<KeyValuePair> ret = new ArrayList<KeyValuePair>();
                    KeyValuePair valuePair = new KeyValuePair();
                    valuePair.setKey(resourceType);
                    ret.add(valuePair);
                    return ret;
            }
           
            return null;
        }
	}

	// The following methods are needed to allow wrapping this class with JPA
	// transaction wrapper

	/**
	 * {@inheritDoc}
	 */
	public ResourceKey createResource(final Resource resource,
			final OperationEditObject operationEditObject,
			final SubjectKey createdBy) throws PolicyCreationException {
		return impl.createResource(resource, operationEditObject, createdBy);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResourceKey updateResource(final Resource resource,
			final OperationEditObject operationEditObject,
			final SubjectKey modifiedBy) throws PolicyUpdateException,
			PolicyCreationException, PolicyDeleteException {
		return impl.updateResource(resource, operationEditObject, modifiedBy);
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteResource(final Long resourceId)
			throws PolicyFinderException, PolicyDeleteException {
		impl.deleteResource(resourceId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Resource getResourceInfoById(final Long resourceId)
			throws PolicyFinderException {
		return impl.getResourceInfoById(resourceId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Resource getResourceInfoByName(final String resourceName)
			throws PolicyFinderException {
		return impl.getResourceInfoByName(resourceName);
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<Long, Resource> getResourceInfoByType()
			throws PolicyFinderException {
		return impl.getResourceInfoByType();
	}

	/**
	 * {@inheritDoc}
	 */
	public Operation getOperationById(final String resourceName,
			final Long operationId) throws PolicyFinderException {
		return impl.getOperationById(resourceName, operationId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Operation getOperationByName(final String resourceName,
			final String operationName) throws PolicyFinderException {
		return impl.getOperationByName(resourceName, operationName);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Operation> getOperationByResourceId(final Long resourceId)
			throws PolicyFinderException {
		return impl.getOperationByResourceId(resourceId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Resource getResourceInfoByOperationId(final Long operationId)
			throws PolicyFinderException {
		return impl.getResourceInfoByOperationId(operationId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EntityHistory> getAuditHistory(final ResourceKey resourceKey,
			final XMLGregorianCalendar startDate,
			final XMLGregorianCalendar endDate) throws PolicyFinderException {
		return impl.getAuditHistory(resourceKey, startDate, endDate);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EntityHistory> getAuditHistory(final OperationKey operationKey,
			final XMLGregorianCalendar startDate,
			final XMLGregorianCalendar endDate) throws PolicyFinderException {
		return impl.getAuditHistory(operationKey, startDate, endDate);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider#getMetaData(java.lang.String)
	 */
	@Override
	public List<KeyValuePair> getMetaData(String queryValue) throws PolicyFinderException {
	    return impl.getMetaData(queryValue);
	}

	/**
	 * {@inheritDoc}
	 */
	public void audit(final ResourceKey resourceKey,
			final String operationType, final SubjectKey loginSubject)
			throws PolicyFinderException {
		impl.audit(resourceKey, operationType, loginSubject);
	}

	/**
	 * {@inheritDoc}
	 */
	public void audit(final OperationKey operationKey,
			final String operationType, final SubjectKey loginSubject)
			throws PolicyFinderException {
		impl.audit(operationKey, operationType, loginSubject);
	}
}
