/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client;

import com.google.gwt.i18n.client.Messages;

/**
 * The Interface PolicyAdminMessageConstants.
 */
public interface PolicyAdminMessageConstants extends Messages {
    
    /**
	 * Operation unsuccessful.
	 * 
	 * @return the string
	 */
    @DefaultMessage("The operation was not successful")
    String operationUnsuccessful();
    
	/**
	 * Failed operation message.
	 * 
	 * @return the string
	 */
	@DefaultMessage("Request failed. Please try again later.")
	String failedOperationMessage();
	
	/**
	 * Successful operation message.
	 * 
	 * @return the string
	 */
	@DefaultMessage("Request has been successfully sent.")
	String successfulOperationMessage();
	
	/**
	 * Name field message.
	 * 
	 * @return the string
	 */
	@DefaultMessage("Name field is required")
	String nameFieldMessage();
	
	/**
	 * Minimum subjects message.
	 * 
	 * @return the string
	 */
	@DefaultMessage("At least one Subject is required")
	String minimumSubjectsMessage();
	
	/**
	 * Enable policy confirm message.
	 * 
	 * @param policyName
	 *            the policy name
	 * @return the string
	 */
	@DefaultMessage("Do you wish to enable this policy : \"{0}\" ?")
	String enablePolicyConfirmMessage(String policyName);
	
	/**
	 * Enable policies confirm message.
	 * 
	 * @return the string
	 */
	@DefaultMessage("Do you wish to enable all selected policies ?")
	String enablePoliciesConfirmMessage();
	
	/**
	 * Disable policies confirm message.
	 * 
	 * @return the string
	 */
	@DefaultMessage("Do you wish to disable all selected policies ?")
	String disablePoliciesConfirmMessage();
	
	/**
	 * Delete policies confirm message.
	 * 
	 * @return the string
	 */
	@DefaultMessage("Do you wish to delete all selected policies ?")
	String deletePoliciesConfirmMessage();
	
	/**
	 * No effect types loaded.
	 *
	 * @return the string
	 */
	@DefaultMessage("No Effect Types found! Please contact your server administrator")
	String noEffectTypesLoaded();
	
	/**
	 * Export policies confirm message.
	 * 
	 * @return the string
	 */
	@DefaultMessage("Do you wish to export all selected policies ?")
	String exportPoliciesConfirmMessage();
	
	/**
	 * Disable policy confirm message.
	 * 
	 * @param policyName
	 *            the policy name
	 * @return the string
	 */
	@DefaultMessage("Do you wish to disable this policy : \"{0}\" ?")
	String disablePolicyConfirmMessage(String policyName);
		
	/**
	 * Delete policy confirm message.
	 * 
	 * @param policyName
	 *            the policy name
	 * @return the string
	 */
	@DefaultMessage("Do you wish to delete this policy : \"{0}\" ?")
	String deletePolicyConfirmMessage(String policyName);
	
	/**
	 * Select an application.
	 * 
	 * @return the string
	 */
	@DefaultMessage("Select an application")
	String selectAnApplication ();
}
