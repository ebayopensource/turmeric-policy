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

import org.ebayopensource.turmeric.security.v1.services.OperationKey;
import org.ebayopensource.turmeric.security.v1.services.ResourceKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;

/**
 * The Interface ResourceDAO.
 */
public interface ResourceDAO {

	/**
	 * Persist resource.
	 * 
	 * @param resource
	 *            the resource
	 */
	void persistResource(Resource resource);
	
	/**
	 * Persist operation.
	 * 
	 * @param operation
	 *            the operation
	 */
	void persistOperation(Operation operation);

	/**
	 * Find resource by id.
	 * 
	 * @param id
	 *            the id
	 * @return the resource
	 */
	Resource findResourceById(long id);

	/**
	 * Find resource by name.
	 * 
	 * @param name
	 *            the name
	 * @return the resource
	 */
	Resource findResourceByName(String name);

	/**
	 * Find resource by type.
	 * 
	 * @param type
	 *            the type
	 * @return the list
	 */
	List<Resource> findResourceByType(String type);

	/**
	 * Find operation by id.
	 * 
	 * @param operationId
	 *            the operation id
	 * @return the operation
	 */
	Operation findOperationById(long operationId);

	/**
	 * Find operation by resource id.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the sets the
	 */
	Set<Operation> findOperationByResourceId(long resourceId);

	/**
	 * Find resource by operation id.
	 * 
	 * @param operationId
	 *            the operation id
	 * @return the resource
	 */
	Resource findResourceByOperationId(long operationId);

	/**
	 * Find operation by name.
	 * 
	 * @param resourceName
	 *            the resource name
	 * @param operationName
	 *            the operation name
	 * @param resourceType
	 *            the resource type
	 * @return the operation
	 */
	Operation findOperationByName(String resourceName, String operationName, String resourceType);

	/**
	 * Removes the resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 */
	void removeResource(long resourceId);

	/**
	 * Removes the operation.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @param operationName
	 *            the operation name
	 */
	void removeOperation(Long resourceId, String operationName);

    /**
	 * Audit.
	 * 
	 * @param resourceKey
	 *            the resource key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 */
    void audit(ResourceKey resourceKey, String operationType, SubjectKey loginSubject);

    /**
	 * Audit.
	 * 
	 * @param operationKey
	 *            the operation key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 */
    void audit(OperationKey operationKey, String operationType, SubjectKey loginSubject);

    /**
	 * Gets the resource history.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the resource history
	 */
    List<AuditHistory> getResourceHistory(long resourceId, Date start, Date end);

    /**
	 * Gets the resource history.
	 * 
	 * @param resourceType
	 *            the resource type
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the resource history
	 */
    List<AuditHistory> getResourceHistory(String resourceType, Date start, Date end);

    /**
	 * Gets the operation history.
	 * 
	 * @param operationId
	 *            the operation id
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the operation history
	 */
    List<AuditHistory> getOperationHistory(long operationId, Date start, Date end);

    /**
	 * Gets the operation history.
	 * 
	 * @param resourceType
	 *            the resource type
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the operation history
	 */
    List<AuditHistory> getOperationHistory(String resourceType, Date start, Date end);
}
