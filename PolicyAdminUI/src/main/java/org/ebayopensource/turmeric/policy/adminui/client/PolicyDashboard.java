/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client;


import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * PolicyDashboard.
 */
public interface PolicyDashboard  extends Container {
    
    /**
	 * Activate.
	 * 
	 * @param view
	 *            the view
	 */
    public void activate (Display view);
    
    /**
	 * Gets the selector.
	 * 
	 * @return the selector
	 */
    public HasSelectionHandlers<TreeItem> getSelector();
    
    /**
	 * Sets the actions.
	 * 
	 * @param actions
	 *            the new actions
	 */
    public void setActions(List<UserAction> actions);
    
    /**
	 * Sets the selected.
	 * 
	 * @param action
	 *            the new selected
	 */
    public void setSelected(UserAction action);
    
    /**
	 * Error.
	 * 
	 * @param err
	 *            the err
	 */
    public void error(String err);
}
