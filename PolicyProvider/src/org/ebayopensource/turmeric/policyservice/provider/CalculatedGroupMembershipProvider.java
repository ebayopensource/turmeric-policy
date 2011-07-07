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

import java.util.Collection;

import org.ebayopensource.turmeric.policyservice.exceptions.PolicyProviderException;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroupType;

/**
 * The interface for CalculatedGroupMembership provider.
 * 
 */
public interface CalculatedGroupMembershipProvider {
	
	
	/**
	 * Gets the calculated sg.
	 *
	 * @param subjectGroup the subject group
	 * @return the calculated sg
	 * @throws PolicyProviderException the policy provider exception
	 */
	SubjectGroupType getCalculatedSG(SubjectGroupType subjectGroup) 
			throws PolicyProviderException;
	
	

	 /**
 	 * Gets the all calculated s gs.
 	 *
 	 * @return the all calculated s gs
 	 * @throws PolicyProviderException the policy provider exception
 	 */
 	Collection<SubjectGroupType> getAllCalculatedSGs()
		throws PolicyProviderException;

}
