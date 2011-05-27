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
 * The Class LoginFailureEvent.
 */
public class LoginFailureEvent extends GwtEvent<LoginFailureEventHandler> {

	/** The TYPE. */
	public static Type<LoginFailureEventHandler> TYPE = new Type<LoginFailureEventHandler>();
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(LoginFailureEventHandler handler) {
		handler.onFailure();
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoginFailureEventHandler> getAssociatedType() {
		return TYPE;
	}

}
