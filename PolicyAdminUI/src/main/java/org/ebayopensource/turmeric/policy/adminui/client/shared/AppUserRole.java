/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.shared;

/**
 * The Enum AppUserRole.
 */
public enum AppUserRole {
	GUEST,
	ADMIN;
	
	private AppUserRole() {}
	
	/**
	 * Checks if is admin.
	 * 
	 * @return true, if is admin
	 */
	public boolean isAdmin() {
		return this.equals(AppUserRole.ADMIN);
	}
	
	/**
	 * Checks if is guest.
	 * 
	 * @return true, if is guest
	 */
	public boolean isGuest() {
		return this.equals(AppUserRole.GUEST);
	}
}
