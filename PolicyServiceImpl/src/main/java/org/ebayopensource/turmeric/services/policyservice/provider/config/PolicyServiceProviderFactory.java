/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.services.policyservice.provider.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ebayopensource.turmeric.common.v1.types.CommonErrorData;
import org.ebayopensource.turmeric.errorlibrary.turmericpolicy.ErrorConstants;
import org.ebayopensource.turmeric.policyservice.provider.AuthenticationProvider;
import org.ebayopensource.turmeric.policyservice.provider.CalculatedGroupMembershipProvider;
import org.ebayopensource.turmeric.policyservice.provider.PolicyTypeProvider;
import org.ebayopensource.turmeric.policyservice.provider.ResourceTypeProvider;
import org.ebayopensource.turmeric.policyservice.provider.SubjectTypeProvider;
import org.ebayopensource.turmeric.runtime.common.exceptions.ErrorUtils;
import org.ebayopensource.turmeric.runtime.common.exceptions.ServiceException;
import org.ebayopensource.turmeric.runtime.common.impl.utils.LogManager;
import org.ebayopensource.turmeric.utils.ReflectionUtils;


/**
 * A factory for creating PolicyServiceProvider objects.
 * 
 * @author rpallikonda
 */
public class PolicyServiceProviderFactory {
	
	private static Logger s_Logger = LogManager.getInstance(PolicyServiceProviderFactory.class);
	
	/**
	 * Initialize.
	 * 
	 * @param provider
	 *            the provider
	 * @throws ServiceException
	 *             the service exception
	 */
	public static synchronized void initialize(String provider) throws ServiceException {
	
		if (isInitialized) {
			throw new ServiceException("already initialized");
		}
		isInitialized = true;
		PolicyServiceProviderConfigManager configMngr = 
			PolicyServiceProviderConfigManager.getInstance();
		try {
			// initialization
			
			ClassLoader cl = Thread.currentThread().getContextClassLoader();

			PolicyServiceProviderConfig config = configMngr.getConfig();
			if (provider == null) {
				provider = config.getDefaultProvider();
				s_Logger.log(Level.WARNING,
						"changing provider suite to default: " + provider);
			}

			// populate policy type Map
			s_policyTypeProviderMap = getProviderInstances(config
					.getPolicyTypeProviders(provider),
					PolicyTypeProvider.class, cl);
			// populate subject type Map
			s_subjectTypeProviderMap = getProviderInstances(config
					.getSubjectTypeProviders(provider),
					SubjectTypeProvider.class, cl);
			// populate resource type map
			s_resourceTypeProviderMap = getProviderInstances(config
					.getResourceTypeProviders(provider),
					ResourceTypeProvider.class, cl);
			s_authnProvider = ReflectionUtils.createInstance(config.getAuthenticationProvider(provider), 
					AuthenticationProvider.class, cl);
			
			s_groupMembershipProvider = ReflectionUtils.createInstance(config.getGroupMembershipProvider(provider), 
					CalculatedGroupMembershipProvider.class, cl);
		} catch (Exception ce) {
			s_Logger.log(Level.SEVERE, "invalid configuration" , ce);
			s_errorData = getConfigError("PolicyService", configMngr);
		}
		
	}
	
	private static boolean isInitialized;
	private static CommonErrorData s_errorData;
	private static Map<String, PolicyTypeProvider> s_policyTypeProviderMap;
	private static Map<String, SubjectTypeProvider> s_subjectTypeProviderMap;
	private static Map<String, ResourceTypeProvider> s_resourceTypeProviderMap;
	private static AuthenticationProvider s_authnProvider;
	private static CalculatedGroupMembershipProvider s_groupMembershipProvider;

	
	// disable creating instances
	private PolicyServiceProviderFactory() {
	}
	
	private static <T> Map<String, T> getProviderInstances(
			Map<String, String> typeProviderMap, Class<T> clazz, ClassLoader cl) throws Exception {
		Map<String, T> providerInstanceMap = new HashMap<String, T>();
		for (Map.Entry<String, String> entry : typeProviderMap.entrySet()) {
			try {
				T instance = ReflectionUtils.createInstance(entry.getValue(), clazz, cl);
				providerInstanceMap.put(entry.getKey(), instance);
			} catch (Exception e) {
				s_Logger.log(Level.SEVERE, 
						"The class name: " 
							+ entry.getValue() + " for type: " + entry.getKey() + " is invalid",
						e);
				throw e;
			}
		}
		return providerInstanceMap;
	}
	
	static private <T> T getProviderInstance(String type, Map<String, T> providerMap) throws ServiceException {
		if (!isInitialized) {
			// Not initialized
			throw new ServiceException("Factory is not initialized");
		}
		if (s_errorData != null) {
			throw new ServiceException(s_errorData);
		}
		return providerMap.get(type);
	}
	
	private static CommonErrorData getConfigError(String type) {
		return getConfigError(type, PolicyServiceProviderConfigManager.getInstance());
	}
	
	private static CommonErrorData getConfigError(String type,
			PolicyServiceProviderConfigManager configMngr) {
		return ErrorUtils.createErrorData(
				ErrorConstants.SVC_POLICYSERVICE_INVALID_PROVIDER_CONFIGURATION, 
				ErrorConstants.ERRORDOMAIN.toString(),
				new Object[] {new String("PolicyService"), 
					configMngr.getConfigPath() + 
					configMngr.getConfigFileName()});
	}
	
	private static CommonErrorData getTypeError(String category, String type)	{
		PolicyServiceProviderConfigManager configMngr = PolicyServiceProviderConfigManager.getInstance();

		return ErrorUtils.createErrorData(
				ErrorConstants.SVC_POLICYSERVICE_INVALID_PROVIDER_TYPE, 
				ErrorConstants.ERRORDOMAIN.toString(),
				new Object[] {category, type == null ? "" : type,  
					configMngr.getConfigPath() + 
					configMngr.getConfigFileName()});
	}
	
	/**
	 * Gets the policy type provider.
	 * 
	 * @param type
	 *            the type
	 * @return the policy type provider
	 * @throws ServiceException
	 *             the service exception
	 */
	public static PolicyTypeProvider getPolicyTypeProvider(String type) throws ServiceException {
		if(type == null)
		{
			throw new ServiceException(ErrorUtils.createErrorData(
					ErrorConstants.SVC_POLICYSERVICE_INVALID_INPUT_ERROR, 
					ErrorConstants.ERRORDOMAIN.toString(), new Object[]{"Policy type cannot be empty"}));			
		}
		PolicyTypeProvider provider = getProviderInstance(type, s_policyTypeProviderMap);
		if (provider == null) 
			throw new ServiceException(getTypeError("policy", type));
		return provider;
		
	}
	
	/**
	 * Gets the subject type provider.
	 * 
	 * @param type
	 *            the type
	 * @return the subject type provider
	 * @throws ServiceException
	 *             the service exception
	 */
	public static SubjectTypeProvider getSubjectTypeProvider(String type) throws ServiceException {
		if(type == null)
		{
			throw new ServiceException(ErrorUtils.createErrorData(
					ErrorConstants.SVC_POLICYSERVICE_INVALID_INPUT_ERROR, 
					ErrorConstants.ERRORDOMAIN.toString(), new Object[]{"Subject type cannot be empty"}));			
		}
		SubjectTypeProvider provider = getProviderInstance(type, s_subjectTypeProviderMap);
		if (provider == null) 
			throw new ServiceException(getTypeError("subject", type));
		return provider;
	}
	
	/**
	 * Gets the resource type provider.
	 * 
	 * @param type
	 *            the type
	 * @return the resource type provider
	 * @throws ServiceException
	 *             the service exception
	 */
	public static ResourceTypeProvider getResourceTypeProvider(String type) throws ServiceException {
		if(type == null)
		{
			throw new ServiceException(ErrorUtils.createErrorData(
					ErrorConstants.SVC_POLICYSERVICE_INVALID_INPUT_ERROR, 
					ErrorConstants.ERRORDOMAIN.toString(), new Object[]{"Resource type cannot be empty"}));			
		}
		ResourceTypeProvider provider =  getProviderInstance(type, s_resourceTypeProviderMap);
		if (provider == null) 
			throw new ServiceException(getTypeError("resource", type));
		return provider;
	}
	
	/**
	 * Gets the authentication provider.
	 * 
	 * @return the authentication provider
	 * @throws ServiceException
	 *             the service exception
	 */
	public static AuthenticationProvider getAuthenticationProvider() throws ServiceException {
		if (!isInitialized) {
			// Not initialized
			throw new ServiceException("Factory is not initialized");
		}
		if (s_errorData != null)
			throw new ServiceException(s_errorData);
		return s_authnProvider;
	}
	
	public static CalculatedGroupMembershipProvider getCalculatedGroupMembershipProvider() throws ServiceException {
		if (!isInitialized) {
			throw new ServiceException("Factory is not initialized");
		}
		if (s_errorData != null){
			throw new ServiceException(s_errorData);
		}
		
		return s_groupMembershipProvider;
	}

	/**
	 * Gets the subject types.
	 * 
	 * @return the subject types
	 * @throws ServiceException
	 *             the service exception
	 */
	public static Set<String> getSubjectTypes() throws ServiceException {
		if (!isInitialized) {
			// Not initialized
			throw new ServiceException("Factory is not initialized");
		}
		if (s_errorData != null)
			throw new ServiceException(s_errorData);
		return s_subjectTypeProviderMap.keySet();
	}
	
	/**
	 * Gets the resource types.
	 * 
	 * @return the resource types
	 * @throws ServiceException
	 *             the service exception
	 */
	public static Set<String> getResourceTypes() throws ServiceException {
		if (!isInitialized) {
			// Not initialized
			throw new ServiceException("Factory is not initialized");
		}
		if (s_errorData != null)
			throw new ServiceException(s_errorData);
		return s_resourceTypeProviderMap.keySet();
	}
	
	/**
	 * Gets the policy types.
	 * 
	 * @return the policy types
	 * @throws ServiceException
	 *             the service exception
	 */
	public static Set<String> getPolicyTypes() throws ServiceException {
		if (!isInitialized) {
			// Not initialized
			throw new ServiceException("Factory is not initialized");
		}
		if (s_errorData != null)
			throw new ServiceException(s_errorData);
		return s_policyTypeProviderMap.keySet();
	}

}
