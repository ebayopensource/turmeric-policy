/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ExtraField;

/**
 * The Class AUTHZPolicyEditView.
 */
public class AUTHZPolicyEditView extends PolicyEditView {

	/** The Constant SELECTED_ACTION. */
    protected static  final UserAction SELECTED_ACTION = UserAction.AUTHZ_POLICY_EDIT;
	
    private static final String TITLE_FORM= PolicyAdminUIUtil.policyAdminConstants.policyInformationAuthzEdit();
	
	/* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#getSelectedAction()
	 */
	@Override
	public final UserAction getSelectedAction(){
		return SELECTED_ACTION;
	}

	/* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#getTitleForm()
	 */
	@Override
	public final String getTitleForm() {
		return TITLE_FORM;
	}




    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Display#getAssociatedId()
     */
    @Override
	public final String getAssociatedId() {
        return null;
    }


    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Display#setAssociatedId(java.lang.String)
     */
    @Override
    public void setAssociatedId(final String id) {
    }

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#initializeExtraFields()
	 */
	@Override
	protected void initializeExtraFields() {
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setExtraFieldList(java.util.List)
	 */
	@Override
	public void setExtraFieldList(final List<ExtraField> extraFieldList) {

	}

}
