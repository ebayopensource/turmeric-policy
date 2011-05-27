/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.presenter.policy;

import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter;
import org.ebayopensource.turmeric.policy.adminui.client.shared.AppUser;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The Class PolicyImportPresenter.
 */
public class PolicyImportPresenter extends AbstractGenericPresenter {
	
	/** The Constant PRESENTER_ID. */
	public final static String PRESENTER_ID = "PolicyImport";
	
	/** The event bus. */
	protected HandlerManager eventBus;
	
	/** The view. */
	protected PolicyImportDisplay view;
	
	/** The service map. */
	protected Map<SupportedService, PolicyAdminUIService> serviceMap;
	
	/**
	 * Instantiates a new policy import presenter.
	 * 
	 * @param eventBus
	 *            the event bus
	 * @param view
	 *            the view
	 * @param serviceMap
	 *            the service map
	 */
	public PolicyImportPresenter(HandlerManager eventBus, PolicyImportDisplay view, Map<SupportedService, PolicyAdminUIService> serviceMap) {
		this.eventBus = eventBus;
		this.view = view;
		this.view.setAssociatedId(getId());
		this.serviceMap = serviceMap;
		
		bind();
	}

	/**
	 * The Interface PolicyImportDisplay.
	 */
	public interface PolicyImportDisplay extends PolicyPageTemplateDisplay {
		FormPanel getForm() ;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter#getId()
	 */
	public String getId() {
		return PRESENTER_ID;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter#getView()
	 */
	@Override
	protected PolicyImportDisplay getView() {
		return view;
	}
	
	/**
	 * Bind.
	 */
	public void bind() {
		
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter#go(com.google.gwt.user.client.ui.HasWidgets, org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken)
	 */
	@Override
	public void go(HasWidgets container, final HistoryToken token) {
		container.clear();
		this.view.activate();
		container.add(this.view.asWidget());
		
		StringBuffer importUrl = new StringBuffer();
		importUrl.append("/importPlc/policy?");
		
		AppUser user = AppUser.getUser();
		importUrl.append(user.getUsername() + "&");
		importUrl.append(user.getPassword());
		
		this.view.getForm().setAction(importUrl.toString());
		
	}
}
