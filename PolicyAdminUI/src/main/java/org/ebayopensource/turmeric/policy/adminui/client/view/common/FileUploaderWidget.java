/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.common;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FileUploaderWidget {
	public static Widget getFileUploaderWidget(final FormPanel form,
			final String entity) {

		VerticalPanel holder = new VerticalPanel();
		final FileUpload fu = new FileUpload();

		fu.setName("upload");
		holder.add(fu);
		holder.add(new Button(PolicyAdminUIUtil.policyAdminConstants
				.importAction(), new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (!fu.getFilename().isEmpty()
						&& Window.confirm(PolicyAdminUIUtil.policyAdminConstants
								.importAction()
								+ " "
								+ entity
								+ PolicyAdminUIUtil.policyAdminConstants.from()
								+ " " + fu.getFilename() + "?")) {
					form.submit();
				}
			}
		}));

		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			public void onSubmit(SubmitEvent event) {

			}
		});

		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				
				int indexFrom = event.getResults().indexOf("<pre>") + 5;
				int indexTo = event.getResults().indexOf("</pre>");
				if(indexTo - indexFrom > 1){
					Window.alert(event.getResults().substring(indexFrom, indexTo));
				}else{
					Window.alert(PolicyAdminUIUtil.policyAdminMessages.successfulOperationMessage());
				}
				
			}
		});

		form.add(holder);

		return form;
	}
}
