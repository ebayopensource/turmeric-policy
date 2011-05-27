/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * The Interface RuleAttribute.
 */
public interface RuleAttribute {
	
	/**
	 * The Enum NotifyKeys.
	 */
	public static enum NotifyKeys {NotifyEmails,  NotifyActive};
	
	/**
	 * The Enum NotifyActiveValue.
	 */
	public static enum NotifyActiveValue {TRUE, FALSE};
	    
	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey();

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue();

}
