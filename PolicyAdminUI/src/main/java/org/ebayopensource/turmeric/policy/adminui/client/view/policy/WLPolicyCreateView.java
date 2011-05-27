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
 * The Class WLPolicyCreateView.
 */
public class WLPolicyCreateView extends PolicyCreateView {
	
	/** The Constant SELECTED_ACTION. */
	protected static final UserAction SELECTED_ACTION = UserAction.WL_POLICY_CREATE;
	private static final String TITLE_FORM= PolicyAdminUIUtil.policyAdminConstants.policyInformationWLCreate();
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#getSelectedAction()
	 */
	@Override
	public UserAction getSelectedAction() {
		return UserAction.RL_POLICY_CREATE;
	} 
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#getTitleForm()
	 */
	@Override
	public String getTitleForm(){
		return TITLE_FORM;
	}

	

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Display#getAssociatedId()
     */
    @Override
    public String getAssociatedId() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Display#setAssociatedId(java.lang.String)
     */
    @Override
    public void setAssociatedId(String id) {
        // TODO Auto-generated method stub
        
    }

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#initializeExtraFields()
	 */
	@Override
	protected void initializeExtraFields() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setPolicyDesc(java.lang.String)
	 */
	@Override
	public void setPolicyDesc(String policyDesc) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setPolicyName(java.lang.String)
	 */
	@Override
	public void setPolicyName(String policyName) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setExtraFieldList(java.util.List)
	 */
	@Override
	public void setExtraFieldList(List<ExtraField> extraFieldList) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setStatusListboxEnabled(boolean)
	 */
	@Override
	public void setStatusListboxEnabled(boolean enable) {
		// TODO Auto-generated method stub
		
	}


	
}
