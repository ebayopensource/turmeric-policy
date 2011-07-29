/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.util;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ExtraField;

import com.google.gwt.user.client.ui.ListBox;

/**
 * The Class PolicyExtraFieldsUtil.
 */
public class PolicyExtraFieldsUtil {
	// TODO move it to specific RL policy Presenter extends from PolicyCreate
	// Presenter
	private static List<ExtraField> rlExtraFields;


	/**
	 * Gets the rL extra fields.
	 * 
	 * @return the rL extra fields
	 */
	public static List<ExtraField> getRLExtraFields() {

		rlExtraFields = new ArrayList<ExtraField>();
		// Policy Based Email Address:
		ExtraField field_1 = new ExtraField();
		field_1.setFieldType("TextBox");
		field_1.setLabelName("Policy Based Email Address:");
		field_1.setOrder(1);
		field_1.setLenghtBox("300px");
		rlExtraFields.add(field_1);

		// Subject Based Email Address:
		ExtraField field_2 = new ExtraField();
		field_2.setFieldType("CheckBox");
		field_2.setLabelName("Subject Based Email Address:");
		field_2.setOrder(2);
		rlExtraFields.add(field_2);

		// Effect Duration
		ExtraField field_3 = new ExtraField();
		field_3.setFieldType("TextBox");
		field_3.setLabelName("Effect Duration: (secs)");
		field_3.setOrder(3);
		field_3.setLenghtBox("80px");
		rlExtraFields.add(field_3);

		// Rollover period
		ExtraField field_4 = new ExtraField();
		field_4.setFieldType("ListBox");
		ListBox rollover = new ListBox();
		rollover.addItem("3600");
		rollover.addItem("86400");
		field_4.setListBox(rollover);
		field_4.setLabelName("Rollover Period:");
		field_4.setOrder(4);
		field_4.setLenghtBox("80px");
		rlExtraFields.add(field_4);

		// priority
		ExtraField field_5 = new ExtraField();
		field_5.setFieldType("ListBox");
		// TODO JOSE I18N the extra fields
		ListBox priority = new ListBox();
		for (int i = 0; i < 9; i++) {
			priority.addItem(Integer.toString(i + 1));
		}
		field_5.setListBox(priority);
		field_5.setLabelName("Priority:");
		field_5.setOrder(5);
		field_5.setLenghtBox("80px");
		rlExtraFields.add(field_5);

		// move to the 1st cause it is async call
		// Effect
		final ExtraField field_6 = new ExtraField();
		field_6.setFieldType("ListBox");
		field_6.setLabelName("Effect:");
		field_6.setOrder(6);
		field_6.setLenghtBox("80px");
		rlExtraFields.add(field_6);

		// condition
		ExtraField field_7 = new ExtraField();
		field_7.setFieldType("TextArea");
		field_7.setLabelName("Condition:");
		field_7.setOrder(7);
		field_7.setLenghtBox("580px");
		rlExtraFields.add(field_7);

		return rlExtraFields;
	}

}
