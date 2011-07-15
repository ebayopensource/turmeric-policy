/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.event;

import org.ebayopensource.turmeric.policy.adminui.client.shared.AppUser;

import com.google.gwt.event.shared.GwtEvent;

/**
 * The Class LoginSuccessEvent.
 */
public class LoginSuccessEvent extends GwtEvent<LoginSuccessEventHandler> {
	
	/** The TYPE. */
	public static Type<LoginSuccessEventHandler> TYPE = new Type<LoginSuccessEventHandler>();
	
	private AppUser appUser;
	
	/**
	 * Instantiates a new login success event.
	 * 
	 * @param appUser
	 *            the app user
	 */
	public LoginSuccessEvent(AppUser appUser) {
		this.appUser = appUser;
		
	}
	
	/**
	 * Gets the tYPE.
	 * 
	 * @return the tYPE
	 */
	public static Type<LoginSuccessEventHandler> getTYPE() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(LoginSuccessEventHandler handler) {
		handler.onSuccess(appUser);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoginSuccessEventHandler> getAssociatedType() {
		return TYPE;
	}

}
