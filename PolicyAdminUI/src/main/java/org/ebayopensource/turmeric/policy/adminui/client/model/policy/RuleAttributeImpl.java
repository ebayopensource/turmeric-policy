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
 * RuleAttributeImpl.
 */
public class RuleAttributeImpl implements RuleAttribute {   
    
	/** The key. */
	protected String key;
	
	/** The value. */
	protected String value;

	/**
	 * Instantiates a new rule attribute impl.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public RuleAttributeImpl(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Instantiates a new rule attribute impl.
	 * 
	 * @param value
	 *            the value
	 */
	public RuleAttributeImpl(String value) {
		this.key = RuleAttributeImpl.NotifyKeys.NotifyEmails.toString();
		this.value = value.toString();
	}

	/**
	 * Instantiates a new rule attribute impl.
	 * 
	 * @param value
	 *            the value
	 */
	public RuleAttributeImpl(NotifyActiveValue value) {
		this.key = RuleAttributeImpl.NotifyKeys.NotifyActive.toString();
		this.value = value.toString();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.RuleAttribute#getKey()
	 */
	public String getKey() {
		return this.key;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.RuleAttribute#getValue()
	 */
	public String getValue() {
		return value;
	}
}
