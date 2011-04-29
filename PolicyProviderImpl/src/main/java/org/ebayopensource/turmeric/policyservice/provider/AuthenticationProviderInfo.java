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

import java.util.List;

/**
 * AuthenticationProviderInfo
 * 
 * Container for authentication methods with its corresponding resource and
 * operation
 * 
 * Code derived from EBay reference codes.
 */
class AuthenticationProviderInfo {
	private String resourceName;
	private String resourceType;
	private String operationName;
	private List<String> authenticationMethods;

	/**
	 * Gets the resource name.
	 * 
	 * @return the resource name
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Sets the resource name.
	 * 
	 * @param resourceName
	 *            the new resource name
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * Gets the resource type.
	 * 
	 * @return the resource type
	 */
	public String getResourceType() {
		return resourceType;
	}
	
	/**
	 * Sets the resource type.
	 * 
	 * @param resourceType
	 *            the new resource type
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * Gets the operation name.
	 * 
	 * @return the operation name
	 */
	public String getOperationName() {
		return operationName;
	}
	
	/**
	 * Sets the operation name.
	 * 
	 * @param operationName
	 *            the new operation name
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	/**
	 * Gets the authentication methods.
	 * 
	 * @return the authentication methods
	 */
	public List<String> getAuthenticationMethods() {
		return authenticationMethods;
	}
	
	/**
	 * Sets the authentication methods.
	 * 
	 * @param authenticationMethods
	 *            the new authentication methods
	 */
	public void setAuthenticationMethods(List<String> authenticationMethods) {
		this.authenticationMethods = authenticationMethods;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("resource name {").append(resourceName).append("}");
		buffer.append(" resource type {").append(resourceType).append("}");
		buffer.append(" operation name {").append(operationName).append("}");
		if (authenticationMethods != null && !authenticationMethods.isEmpty()) {
			buffer.append(" authentication methods\n");
			for (String authMethod : authenticationMethods) {
				buffer.append("  method {").append(authMethod).append("}");
			}
		}
		return buffer.toString();
	}
}
