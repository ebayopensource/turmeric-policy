/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.presenter;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.event.LoginEvent;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The Class SplashPresenter.
 */
public class SplashPresenter implements Presenter {
	
	/** The Constant SPLASH_ID. */
	public final static String SPLASH_ID = "Splash";
	
	/** The view. */
	protected Display view;
	
	/** The event bus. */
	protected HandlerManager eventBus;
	

	/**
	 * The Interface Display.
	 */
	public interface Display extends org.ebayopensource.turmeric.policy.adminui.client.Display{
		HasClickHandlers getSubmitButton();
		HasValue<String> getLogin();
		HasValue<String> getPassword();
		HasValue<String> getDomain();
		void promptMessage(String message);
	}

	/**
	 * Instantiates a new splash presenter.
	 * 
	 * @param eventBus
	 *            the event bus
	 * @param view
	 *            the view
	 */
	public SplashPresenter (HandlerManager eventBus, Display view) {
		this.view = view;
		this.eventBus = eventBus;
	    bind();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets, org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken)
	 */
	public void go(final HasWidgets container, HistoryToken token) {
		container.clear();
		this.view.activate();
		container.add(this.view.asWidget());
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter#getId()
	 */
	public String getId()
	{
		return SPLASH_ID;
	}
	
	/**
	 * Bind.
	 */
	public void bind() {
		this.view.getSubmitButton().addClickHandler(new ClickHandler() {   
			public void onClick(ClickEvent event) {
				SplashPresenter.this.eventBus.fireEvent(new LoginEvent(view.getLogin().getValue(), 
				                                                       view.getPassword().getValue(),
				                                                       view.getDomain().getValue()));
			}
		});
	}
	
	// TODO - need to handle this properly
	/**
	 * Handle login error view.
	 */
	public void handleLoginErrorView() {
		view.getPassword().setValue("");
		view.promptMessage(PolicyAdminUIUtil.messages.loginFailed());
	}
	
	/**
	 * Handle logout success view.
	 */
	public void handleLogoutSuccessView() {
		view.promptMessage(PolicyAdminUIUtil.messages.logoutSuccessful());
	}
}
