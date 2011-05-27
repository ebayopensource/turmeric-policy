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
 * The Class LogoutEvent.
 */
public class LogoutEvent extends GwtEvent<LogoutEventHandler> {
	
	/** The TYPE. */
	public static Type<LogoutEventHandler> TYPE = new Type<LogoutEventHandler>();

	
	/**
	 * Instantiates a new logout event.
	 */
	public LogoutEvent () {
	}
	
	
	/**
	 * Gets the tYPE.
	 * 
	 * @return the tYPE
	 */
	public static Type<LogoutEventHandler> getTYPE() {
		return TYPE;
	}


	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<LogoutEventHandler> getAssociatedType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(LogoutEventHandler handler) {
		handler.onLogout(this);
	}
}
