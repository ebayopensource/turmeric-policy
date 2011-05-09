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
import org.ebayopensource.turmeric.policy.adminui.client.event.LogoutEvent;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter;
import org.ebayopensource.turmeric.policy.adminui.client.shared.AppUser;
import org.ebayopensource.turmeric.policy.adminui.client.shared.AppUserRole;
import org.ebayopensource.turmeric.policy.adminui.client.util.PolicyMenuUtil;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasWidgets;

public class PolicyImportPresenter extends AbstractGenericPresenter {
	
	public final static String PRESENTER_ID = "PolicyImport";
	
	protected HandlerManager eventBus;
	protected PolicyImportDisplay view;
	protected Map<SupportedService, PolicyAdminUIService> serviceMap;
	
	public PolicyImportPresenter(HandlerManager eventBus, PolicyImportDisplay view, Map<SupportedService, PolicyAdminUIService> serviceMap) {
		this.eventBus = eventBus;
		this.view = view;
		this.view.setAssociatedId(getId());
		this.serviceMap = serviceMap;
		
		bind();
	}

	public interface PolicyImportDisplay extends PolicyPageTemplateDisplay {
		FormPanel getForm() ;
	}

	public String getId() {
		return PRESENTER_ID;
	}

	@Override
	protected PolicyImportDisplay getView() {
		return view;
	}
	
	public void bind() {
		
	}
	
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
