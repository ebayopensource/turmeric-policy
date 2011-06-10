/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.presenter;

import java.util.HashMap;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.Container;
import org.ebayopensource.turmeric.policy.adminui.client.Controller;
import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.event.LogoutEvent;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyController;
import org.ebayopensource.turmeric.policy.adminui.client.shared.AppUser;
import org.ebayopensource.turmeric.policy.adminui.client.view.PolicyContainer;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * This presenter contains top level menu of the application.
 * (1) Monitoring Console
 * (2) Policy Admin
 * 
 * @author nuy
 *
 */
public class MenuController implements Presenter, Controller {
	
	/** The Constant PRESENTER_ID. */
	public final static String PRESENTER_ID = "Menu";

	/** The event bus. */
	protected HandlerManager eventBus;
	
	/** The view. */
	protected MenuControllerDisplay view;
	
	/** The root container. */
	protected HasWidgets rootContainer;
	
	/** The added. */
	protected boolean added;
	
	/** The presenters. */
	protected Map<String, Presenter> presenters = new HashMap<String, Presenter>();
	
	/** The action map. */
	protected Map<UserAction, Presenter> actionMap = new HashMap<UserAction, Presenter>();
	
	/** The service map. */
	protected Map<SupportedService, PolicyAdminUIService> serviceMap;
	
	
	/**
	 * The Interface MenuControllerDisplay.
	 */
	public interface MenuControllerDisplay extends Container {
	    HasClickHandlers getLogoutComponent();
	    void setUserName(String name);
	}
	
	/**
	 * Instantiates a new menu controller.
	 * 
	 * @param eventBus
	 *            the event bus
	 * @param rootContainer
	 *            the root container
	 * @param view
	 *            the view
	 * @param serviceMap
	 *            the service map
	 */
	public MenuController(HandlerManager eventBus, HasWidgets rootContainer, MenuControllerDisplay view, Map<SupportedService, PolicyAdminUIService> serviceMap) {
		this.eventBus = eventBus;
		this.view = view;
		this.serviceMap = serviceMap;
		this.rootContainer = rootContainer;
		
		initPresenters();
		bind();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets, org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken)
	 */
	public void go(HasWidgets container, HistoryToken token) {
	    //only add ourselves to the root window the first time we are activated
	    if (!added) {
	        added =true;
	        rootContainer.add(this.view.asWidget());
	        this.view.setUserName((AppUser.getUser()==null?"":AppUser.getUser().getUsername()));
	    }
	    
	    
	    //try my sub presenters
	    String id = token.getPresenterId();
	    //Window.alert("MenuController: presenter id = "+id);
	    if (id != null && !PRESENTER_ID.equals(id)){
	        selectPresenter(token);
	    }else{
	    	HistoryToken tok = HistoryToken.newHistoryToken(PolicyController.PRESENTER_ID, null);
            History.newItem(tok.toString());
            selectPresenter(tok);
	    }

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter#getId()
	 */
	public String getId() {
		return PRESENTER_ID;
	}

	/**
	 * Bind.
	 */
	public void bind() {
		
		//listen for logout
		this.view.getLogoutComponent().addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {    
                eventBus.fireEvent(new LogoutEvent());
            }
		});
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Controller#addPresenter(java.lang.String, org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter)
	 */
	public void addPresenter(String id, Presenter p) {
		presenters.put(id, p);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Controller#getPresenter(java.lang.String)
	 */
	public Presenter getPresenter(String id) {
		return presenters.get(id);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Controller#selectPresenter(org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken)
	 */
	public void selectPresenter(HistoryToken token) {
		String presenterId = token != null ? token.getPresenterId() : null;
		
		Presenter presenter = presenters.get(presenterId);
        if (presenter != null) {
           //Pass in this view so that all presenter/view pairs are children
           //of the MenuController's view (get constant header/footer wrapping)
            presenter.go(view, token);
        }
	}
	
	/**
	 * Inits the presenters.
	 */
	public void initPresenters() {
		Presenter pp = new PolicyController(eventBus, new PolicyContainer(), this.serviceMap);
		addPresenter(pp.getId(), pp);
		actionMap.put(UserAction.POLICY_MAIN, pp);
	}

}
