/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyImportPresenter.PolicyImportDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.FileUploaderWidget;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.MenuDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.TurmericStackPanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Class PolicyImportView.
 */
public class PolicyImportView extends AbstractGenericView implements PolicyImportDisplay {
	
	private final static UserAction SELECTED_ACTION = UserAction.POLICY_IMPORT;
	
	private DockLayoutPanel mainPanel;
	private MenuDisplay menuView;
	private Display contentView;
	private HasClickHandlers logoutComponent;
	
	
	

	/**
	 * Instantiates a new policy import view.
	 */
	public PolicyImportView() {
		mainPanel = new DockLayoutPanel(Unit.PX);
		initWidget(mainPanel);
		
		initialize();
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView#initialize()
	 */
	@Override
	public void initialize() {
		mainPanel.clear();
		mainPanel.setWidth("100%");
	    mainPanel.add(initContentView());
	}
	

	
	/**
	 * Inits the content view.
	 * 
	 * @return the widget
	 */
	protected Widget initContentView() {
		ScrollPanel actionPanel = new ScrollPanel();
	    contentView = new ContentView();
	    actionPanel.add(contentView.asWidget());
	    return actionPanel;
	}
	
	private class ContentView extends AbstractGenericView implements Display {
		private SimplePanel mainPanel;
		private FormPanel form;
				

		public ContentView() {
			mainPanel = new SimplePanel();
			mainPanel.setWidth("100%");
			initWidget(mainPanel);
			
			initialize();
		}
		
		public void activate() {
			// do nothing for now
		}

		@Override
		public void initialize() {
			mainPanel.clear();
			TurmericStackPanel panel = new TurmericStackPanel();
			panel.setWidth("100%");
			VerticalPanel vp = new VerticalPanel();
			
			form = new FormPanel();
			form.setEncoding(FormPanel.ENCODING_MULTIPART);
			form.setMethod(FormPanel.METHOD_POST);

			FileUploaderWidget.getFileUploaderWidget(form, PolicyAdminUIUtil.policyAdminConstants.policies());
			panel.add(form,PolicyAdminUIUtil.policyAdminConstants.importPolicyAction());
			vp.add(panel);
			vp.add(new Label(PolicyAdminUIUtil.policyAdminConstants.importConditionalFileMsg()));
			mainPanel.add(vp);

		}
		
		public FormPanel getForm() {
			return form;
		}

	}


	
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Display#activate()
	 */
	public void activate() {
		contentView.activate();
		this.setVisible(true);
	}

	/**
	 * Gets the menu view.
	 * 
	 * @return the menu view
	 */
	public MenuDisplay getMenuView() {
		return menuView;
	}

	
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay#getContentView()
	 */
	public Display getContentView() {
		return contentView;
	}
	
	/**
	 * Gets the logout component.
	 * 
	 * @return the logout component
	 */
	public HasClickHandlers getLogoutComponent() {
		return logoutComponent;
	}

	/**
	 * Gets the action selected.
	 * 
	 * @return the action selected
	 */
	public UserAction getActionSelected() {
		return UserAction.POLICY_IMPORT;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyImportPresenter.PolicyImportDisplay#getForm()
	 */
	public FormPanel getForm() {
		return ((ContentView)contentView).getForm();
	}
	
}
