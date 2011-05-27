/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyEditPresenter.PolicyEditDisplay;

/**
 * The Class PolicyEditView.
 */
public abstract class PolicyEditView extends PolicyCreateView implements
		PolicyEditDisplay {
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setPolicyDesc(java.lang.String)
	 */
	@Override
	public void setPolicyDesc(final String policyDesc) {
		this.policyDesc.setText(policyDesc);
	} 
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setStatusListboxEnabled(boolean)
	 */
	public void setStatusListboxEnabled(boolean enabled){
		this.getPolicyStatusList().setEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setPolicyName(java.lang.String)
	 */
	@Override
	public void setPolicyName(final String policyName) {
		this.policyName.setText(policyName);
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#setPolicyStatus(boolean)
	 */
	@Override
	public void setPolicyStatus(final boolean enabled) {
		if(enabled){
			this.policyStatus.setSelectedIndex(0); //enable;
		}else{
			this.policyStatus.setSelectedIndex(1);//disable;
		}
	}


}
