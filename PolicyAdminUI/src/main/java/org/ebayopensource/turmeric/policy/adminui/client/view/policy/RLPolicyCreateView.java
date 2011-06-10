/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ExtraField;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.RLPolicyCreatePresenter.RLPolicyCreateDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.util.PolicyExtraFieldsUtil;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Class RLPolicyCreateView.
 */
public class RLPolicyCreateView extends PolicyCreateView implements
		RLPolicyCreateDisplay {

	/** The Constant SELECTED_ACTION. */
	protected static final UserAction SELECTED_ACTION = UserAction.RL_POLICY_CREATE;
	private static final String TITLE_FORM = PolicyAdminUIUtil.policyAdminConstants
			.policyInformationRLCreate();

	List<ExtraField> rlExtraFields = new ArrayList<ExtraField>();

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#getSelectedAction()
	 */
	@Override
	public UserAction getSelectedAction() {
		return SELECTED_ACTION;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#getTitleForm()
	 */
	@Override
	public String getTitleForm() {
		return TITLE_FORM;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#clear()
	 */
	@Override
	public void clear() {
		super.clear();
		for (int i = 0; i < extraFieldsGrid.getRowCount(); i++) {
			Widget widget = extraFieldsGrid.getWidget(i, 1);
			if (widget != null && widget instanceof CheckBox) {
				((CheckBox) widget).setValue(false);
			} else if (widget != null && widget instanceof TextBox) {
				((TextBox) widget).setText("");
			} else if (widget != null && widget instanceof TextArea) {
				((TextArea) widget).setText("");
			} else if (widget != null && widget instanceof ListBox) {
				((ListBox) widget).setSelectedIndex(-1);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policy.adminui.client.Display#getAssociatedId()
	 */
	@Override
	public String getAssociatedId() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policy.adminui.client.Display#setAssociatedId
	 * (java.lang.String)
	 */
	@Override
	public void setAssociatedId(String id) {

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicyCreateView#initializeExtraFields()
	 */
	protected void initializeExtraFields() {
		rlExtraFields = PolicyExtraFieldsUtil.getRLExtraFields();

		extraFieldsGrid = new Grid(rlExtraFields.size() + 1, 3);

		for (ExtraField extraField : rlExtraFields) {

			extraFieldsGrid.setWidget(extraField.getOrder(), 0, new Label(
					extraField.getLabelName()));

			if (extraField.getFieldType() != null
					&& "CheckBox".equalsIgnoreCase(extraField.getFieldType())) {
				CheckBox chBox = new CheckBox();
				chBox.setEnabled(true);
				extraField.setCheckBox(chBox);
				extraFieldsGrid.setWidget(extraField.getOrder(), 1,
						extraField.getCheckBox());

			} else if (extraField.getFieldType() != null
					&& "TextBox".equalsIgnoreCase(extraField.getFieldType())) {
				TextBox txBox = new TextBox();
				txBox.setText("");
				txBox.setWidth(extraField.getLenghtBox());
				extraField.setTextBox(txBox);
				extraFieldsGrid.setWidget(extraField.getOrder(), 1,
						extraField.getTextBox());

			} else if (extraField.getFieldType() != null
					&& "TextArea".equalsIgnoreCase(extraField.getFieldType())) {
				TextArea txArea = new TextArea();
				txArea.setText("");
				txArea.setWidth(extraField.getLenghtBox());
//				txArea.setEnabled(false);
				extraField.setTextArea(txArea);
				extraFieldsGrid.setWidget(extraField.getOrder(), 1,
						extraField.getTextArea());

			} else if (extraField.getFieldType() != null
					&& "ListBox".equalsIgnoreCase(extraField.getFieldType())) {
				ListBox lsBox = new ListBox();
				for (int i = 0; i < extraField.getListBox().getItemCount(); i++) {
					lsBox.addItem(extraField.getListBox().getItemText(i));
				}
				extraField.setListBox(lsBox);
				extraFieldsGrid.setWidget(extraField.getOrder(), 1,
						extraField.getListBox());

			}
		}
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setPolicyDesc(java.lang.String)
	 */
	@Override
	public void setPolicyDesc(String policyDesc) {

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setPolicyName(java.lang.String)
	 */
	@Override
	public void setPolicyName(String policyName) {

	}


	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setExtraFieldList(java.util.List)
	 */
	@Override
	public void setExtraFieldList(List<ExtraField> extraFieldList) {

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyCreatePresenter.PolicyCreateDisplay#setStatusListboxEnabled(boolean)
	 */
	@Override
	public void setStatusListboxEnabled(boolean enable) {
		
	}

	@Override
	public void addAddConditionButtonClickHandler(ClickHandler clickHandler) {
		getAddConditionButton().addClickHandler(clickHandler);
	}

	@Override
	public void addRsListBoxChangeHandler(ChangeHandler changeHandler) {
		getRsListBox().addChangeHandler(changeHandler);		
	}
}
