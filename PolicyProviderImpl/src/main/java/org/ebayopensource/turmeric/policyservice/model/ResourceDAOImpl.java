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
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.ebayopensource.turmeric.policyservice.exceptions.PolicyFinderException;
import org.ebayopensource.turmeric.policyservice.exceptions.PolicyProviderException.Category;
import org.ebayopensource.turmeric.security.v1.services.OperationKey;
import org.ebayopensource.turmeric.security.v1.services.ResourceKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;
import org.ebayopensource.turmeric.utils.jpa.AbstractDAO;

/**
 * The Class ResourceDAOImpl.
 */
public class ResourceDAOImpl extends AbstractDAO implements ResourceDAO {

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#persistResource(org.ebayopensource.turmeric.policyservice.model.Resource)
	 */
	@Override
	public void persistResource(final Resource resource) {
		persistEntity(resource);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#persistOperation(org.ebayopensource.turmeric.policyservice.model.Operation)
	 */
	@Override
	public void persistOperation(final Operation operation) {
		persistEntity(operation);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#findResourceById(long)
	 */
	@Override
	public Resource findResourceById(final long id) {
		return findEntity(Resource.class, id);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#findResourceByName(java.lang.String)
	 */
	@Override
	public Resource findResourceByName(final String name) {
		return getSingleResultOrNull(Resource.class, "resourceName", name);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#findResourceByType(java.lang.String)
	 */
	@Override
	public List<Resource> findResourceByType(final String type) {
		return getResultList(Resource.class, "resourceType", type);
	}

	/*
	 * It removes a Resource entity an all its assigned operations
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policyservice.model.ResourceDAO#removeResource
	 * (long)
	 */
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#removeResource(long)
	 */
	@Override
	public void removeResource(final long resourceId) {
		removeEntity(Resource.class, resourceId);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#removeOperation(java.lang.Long, java.lang.String)
	 */
	@Override
	public void removeOperation(final Long resourceId,
			final String operationName) {

		final Resource resource = findResourceById(resourceId);
		final Set<Operation> operations = resource.getOperations();

		for (Operation operation : operations) {
			if (operationName.equals(operation.getOperationName())) {
				removeOperation(operation.getId());
			}
		}
	}

	private void removeOperation(final long operationId) {
		removeEntity(Operation.class, operationId);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#findOperationById(long)
	 */
	@Override
	public Operation findOperationById(final long operationId) {
		return findEntity(Operation.class, operationId);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#findOperationByName(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Operation findOperationByName(final String resourceName,
			final String operationName, final String resourceType) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" from ").append(Operation.class.getName())
				.append(" as op ");
		jpql.append(" where op.resource.resourceName = :resourceName ");
		jpql.append(" and op.operationName = :operationName ");
		jpql.append(" and op.resource.resourceType = :resourceType ");

		final EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery(jpql.toString());
		query.setParameter("resourceName", resourceName);
		query.setParameter("operationName", operationName);
		query.setParameter("resourceType", resourceType);

		return getSingleResultOrNull(query);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#findOperationByResourceId(long)
	 */
	@Override
	public Set<Operation> findOperationByResourceId(final long resourceId) {
		return findEntity(Resource.class, resourceId).getOperations();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#findResourceByOperationId(long)
	 */
	@Override
	public Resource findResourceByOperationId(final long operationId) {

		final Operation op = findEntity(Operation.class, operationId);

		final StringBuilder jpql = new StringBuilder();
		jpql.append("from ").append(Resource.class.getName()).append(" as rs ");
		jpql.append(" where id = :id");

		final EntityManager entityManager = getEntityManager();
		final Query query = entityManager.createQuery(jpql.toString());
		query.setParameter("id", op.getResource().getId());

		return getSingleResultOrNull(query);

	}
    
	/**
	 * Convert.
	 * 
	 * @param resource
	 *            the resource
	 * @return the resource
	 */
	public static Resource convert(final org.ebayopensource.turmeric.security.v1.services.Resource resource) {

        final List<org.ebayopensource.turmeric.policyservice.model.Operation> operationList = 
            new ArrayList<org.ebayopensource.turmeric.policyservice.model.Operation>();
        final List<org.ebayopensource.turmeric.security.v1.services.Operation> operations = 
            resource.getOperation();
        if (operations != null && !operations.isEmpty()) {
            for (org.ebayopensource.turmeric.security.v1.services.Operation operation : operations) {
                operationList.add(convert(operation));
            }
        }

        return new org.ebayopensource.turmeric.policyservice.model.Resource(
                resource.getResourceType(), resource.getResourceName(),
                resource.getDescription());
    }

	/**
	 * Convert.
	 * 
	 * @param jpaResource
	 *            the jpa resource
	 * @return the org.ebayopensource.turmeric.security.v1.services. resource
	 * @throws PolicyFinderException
	 *             the policy finder exception
	 */
	public static org.ebayopensource.turmeric.security.v1.services.Resource convert(final Resource jpaResource)
            throws PolicyFinderException {
        final org.ebayopensource.turmeric.security.v1.services.Resource result = 
            new org.ebayopensource.turmeric.security.v1.services.Resource();
        result.setResourceName(jpaResource.getResourceName());
        result.setResourceType(jpaResource.getResourceType());
        result.setDescription(jpaResource.getDescription());
        result.setResourceId(jpaResource.getId());
        return result;
    }

	/**
	 * Convert.
	 * 
	 * @param operation
	 *            the operation
	 * @return the operation
	 */
	public static Operation convert(final org.ebayopensource.turmeric.security.v1.services.Operation operation) {
        return new org.ebayopensource.turmeric.policyservice.model.Operation(
                operation.getOperationName(), operation.getDescription());
    }

	/**
	 * Convert.
	 * 
	 * @param jpaOperation
	 *            the jpa operation
	 * @return the org.ebayopensource.turmeric.security.v1.services. operation
	 * @throws PolicyFinderException
	 *             the policy finder exception
	 */
	public static org.ebayopensource.turmeric.security.v1.services.Operation convert(final Operation jpaOperation)
            throws PolicyFinderException {
        final org.ebayopensource.turmeric.security.v1.services.Operation result = 
            new org.ebayopensource.turmeric.security.v1.services.Operation();
        result.setOperationName(jpaOperation.getOperationName());
        result.setDescription(jpaOperation.getDescription());
        result.setOperationId(jpaOperation.getId());
        if(jpaOperation.getResource()!=null){
          result.setResourceId(jpaOperation.getResource().getId());
        }
        return result;
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#getResourceHistory(long, java.util.Date, java.util.Date)
     */
    @Override
    public List<AuditHistory> getResourceHistory(long resourceId, Date start, Date end) {
        return getResultList(AuditHistory.class, "category", Category.RESOURCE.name(), 
                        "entityId", resourceId, "auditInfo.createdOn", start, end);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#getResourceHistory(java.lang.String, java.util.Date, java.util.Date)
     */
    @Override
    public List<AuditHistory> getResourceHistory(String resourceType, Date start, Date end) {
        return getResultList(AuditHistory.class, "category", Category.RESOURCE.name(), 
                        "entityType", resourceType, "auditInfo.createdOn", start, end);
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#getOperationHistory(long, java.util.Date, java.util.Date)
     */
    @Override
    public List<AuditHistory> getOperationHistory(long operationId, Date start, Date end) {
        return getResultList(AuditHistory.class, "category", Category.OPERATION.name(), 
                        "entityId", operationId, "auditInfo.createdOn", start, end);
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#getOperationHistory(java.lang.String, java.util.Date, java.util.Date)
     */
    @Override
    public List<AuditHistory> getOperationHistory(String resourceType, Date start, Date end) {
        return getResultList(AuditHistory.class, "category", Category.OPERATION.name(), 
                        "entityType", resourceType, "auditInfo.createdOn", start, end);
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#audit(org.ebayopensource.turmeric.security.v1.services.ResourceKey, java.lang.String, org.ebayopensource.turmeric.security.v1.services.SubjectKey)
     */
    @Override
    public void audit(ResourceKey resourceKey, String operationType, SubjectKey loginSubject) {
        persistEntity(AuditHistory.newRecord(resourceKey, operationType, loginSubject));
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.ResourceDAO#audit(org.ebayopensource.turmeric.security.v1.services.OperationKey, java.lang.String, org.ebayopensource.turmeric.security.v1.services.SubjectKey)
     */
    @Override
    public void audit(OperationKey operationKey, String operationType, SubjectKey loginSubject) {
        persistEntity(AuditHistory.newRecord(operationKey, operationType, loginSubject));
    }
}
