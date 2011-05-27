/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * The Class LoginEvent.
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {
	
	/** The TYPE. */
	public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
	private String login;
	private String password;
	private String domain;
	
	
	
	/**
	 * Instantiates a new login event.
	 * 
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @param domain
	 *            the domain
	 */
	public LoginEvent (String login, String password, String domain) {
		this.login = login;
		this.password = password;
		this.domain = domain;
	}
	
	
	/**
	 * Gets the tYPE.
	 * 
	 * @return the tYPE
	 */
	public static Type<LoginEventHandler> getTYPE() {
		return TYPE;
	}


	/**
	 * Gets the login.
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}



	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the domain.
	 * 
	 * @return the domain
	 */
	public String getDomain() {
	    return domain;
	}


	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<LoginEventHandler> getAssociatedType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(LoginEventHandler handler) {
		handler.onLogin(this);
	}
}