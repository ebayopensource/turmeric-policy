/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.util;

import java.util.Collection;

import org.ebayopensource.turmeric.policy.adminui.client.shared.AppUserRole;

/**
 * The Class UserRoleUtil.
 */
public class UserRoleUtil {
	
	/**
	 * Evaluate highest role from collection.
	 * 
	 * @param userRoles
	 *            the user roles
	 * @return the app user role
	 */
	public static AppUserRole evaluateHighestRoleFromCollection(Collection<AppUserRole> userRoles) {
		if (userRoles.contains(AppUserRole.ADMIN)) {
			return AppUserRole.ADMIN;
		} else {
			return AppUserRole.GUEST;
		}
	}
}
