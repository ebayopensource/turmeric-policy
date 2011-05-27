/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view;

import java.util.Iterator;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyDashboard;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyMenuWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * PolicyContainer.
 */
public class PolicyContainer extends ResizeComposite implements HasWidgets, PolicyDashboard {

    /** The content panel. */
    protected SplitLayoutPanel contentPanel;
    Widget policyContent;
    PolicyMenuWidget menuWidget;

    /**
	 * Instantiates a new policy container.
	 */
    public PolicyContainer() {
        configureContent();
        initWidget(contentPanel);
    }
    
    /**
	 * Configure content.
	 * 
	 * @return the split layout panel
	 */
    protected SplitLayoutPanel configureContent() {
        contentPanel = new SplitLayoutPanel();
        contentPanel.addWest(initMenuView(), 150);
        return contentPanel;
    }

    /**
	 * Inits the menu view.
	 * 
	 * @return the widget
	 */
    protected Widget initMenuView() {
        ScrollPanel scroller = new ScrollPanel();
        menuWidget = new PolicyMenuWidget();
        scroller.add(menuWidget);
        return scroller;
    }


    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
     */
    public void add(Widget arg0) {
        if (policyContent != null) {
            contentPanel.remove(policyContent);
        }

        GWT.log(arg0.getClass().getName() + "as policy content");
        policyContent = arg0;
        contentPanel.add(policyContent);
    }
    
    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.HasWidgets#clear()
     */
    public void clear() {
        if (policyContent != null) {
            contentPanel.remove(policyContent);
            policyContent = null;
        }
        this.onResize();
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
     */
    public Iterator<Widget> iterator() {
        return this.contentPanel.iterator();
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
     */
    public boolean remove(Widget arg0) {
        boolean result = contentPanel.remove(arg0);
        if (arg0 == policyContent)
            policyContent = null;
            
        return result;
        
    }


    /**
	 * Activate.
	 * 
	 * @param view
	 *            the view
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.PolicyDashboard#activate(org.ebayopensource.turmeric.policy.adminui.client.Display)
	 */
    @Override
    public void activate(Display view) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.PolicyDashboard#setActions(java.util.List)
     */
    public void setActions(List<UserAction> actions) {
        this.menuWidget.setActions(actions);
    }


    /**
	 * Gets the selector.
	 * 
	 * @return the selector
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.PolicyDashboard#getSelector()
	 */
    @Override
    public HasSelectionHandlers<TreeItem> getSelector() {
        return menuWidget.getSelector();
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.PolicyDashboard#setSelected(org.ebayopensource.turmeric.policy.adminui.client.model.UserAction)
     */
    public void setSelected (UserAction action) {
        GWT.log("UserAction selected:"+action);
        this.menuWidget.setSelected(action);
    }

    /**
	 * Error.
	 * 
	 * @param err
	 *            the err
	 * @see org.ebayopensource.turmeric.policy.adminui.client.PolicyDashboard#error(java.lang.String)
	 */
    @Override
    public void error(String err) {
        ErrorDialog dialog = new ErrorDialog(true);
        dialog.setMessage(err);
        dialog.getDialog().center();
        dialog.show(); 
    }

}
