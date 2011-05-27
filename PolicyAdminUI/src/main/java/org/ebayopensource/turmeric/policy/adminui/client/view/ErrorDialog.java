/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;

/**
 * ErrorDialog
 * 
 * A popup to show an error message.
 */
public class ErrorDialog extends AbstractDialog {
    
    /**
	 * Instantiates a new error dialog.
	 * 
	 * @param animationEnabled
	 *            the animation enabled
	 */
    public ErrorDialog (boolean animationEnabled) {
        super(animationEnabled);
       
        dialog.addStyleName("error");
        dialog.setText(PolicyAdminUIUtil.constants.error());
        label.addStyleName("error");
        ok.addStyleName("error");
    }
}
