/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * ExtraField.
 */
public class ExtraField extends FocusWidget {

	private String labelName;
	private int order;
	private String fieldType;
	private String lenghtBox;
	private String value;

	private Label labelBox;
	
	private TextBox textBox;
	private ListBox listBox;
	private CheckBox checkBox;
	private TextArea textArea;

	/**
	 * Gets the lenght box.
	 * 
	 * @return the lenght box
	 */
	public String getLenghtBox() {
		return lenghtBox;
	}

	/**
	 * Sets the lenght box.
	 * 
	 * @param lenghtBox
	 *            the new lenght box
	 */
	public void setLenghtBox(String lenghtBox) {
		this.lenghtBox = lenghtBox;
	}

	/**
	 * Gets the field type.
	 * 
	 * @return the field type
	 */
	public String getFieldType() {
		return fieldType;
	}

	/**
	 * Sets the field type.
	 * 
	 * @param fieldType
	 *            the new field type
	 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * Gets the order.
	 * 
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 * 
	 * @param order
	 *            the new order
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * Gets the label name.
	 * 
	 * @return the label name
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * Sets the label name.
	 * 
	 * @param labelName
	 *            the new label name
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * Gets the text box.
	 * 
	 * @return the text box
	 */
	public TextBox getTextBox() {
		return textBox;
	}

	/**
	 * Sets the text box.
	 * 
	 * @param textBox
	 *            the new text box
	 */
	public void setTextBox(TextBox textBox) {
		this.textBox = textBox;
	}

	/**
	 * Gets the list box.
	 * 
	 * @return the list box
	 */
	public ListBox getListBox() {
		return listBox;
	}

	/**
	 * Sets the list box.
	 * 
	 * @param listBox
	 *            the new list box
	 */
	public void setListBox(ListBox listBox) {
		this.listBox = listBox;
	}

	/**
	 * Gets the check box.
	 * 
	 * @return the check box
	 */
	public CheckBox getCheckBox() {
		return checkBox;
	}

	/**
	 * Sets the check box.
	 * 
	 * @param checkBox
	 *            the new check box
	 */
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the label box.
	 * 
	 * @param labelBox
	 *            the new label box
	 */
	public void setLabelBox(Label labelBox) {
		this.labelBox = labelBox;
	}

	/**
	 * Gets the label box.
	 * 
	 * @return the label box
	 */
	public Label getLabelBox() {
		return labelBox;
	}

	/**
	 * Sets the text area.
	 * 
	 * @param textArea
	 *            the new text area
	 */
	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * Gets the text area.
	 * 
	 * @return the text area
	 */
	public TextArea getTextArea() {
		return textArea;
	}

}
