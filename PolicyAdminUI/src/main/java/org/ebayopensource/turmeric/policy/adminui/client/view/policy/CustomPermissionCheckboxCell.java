/********************************************************************
 * Copyright (c) 2011 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * CustomPermissionCheckboxCell.
 * 
 * @param <C>
 *            the generic type
 * @param <V>
 *            the value type
 */
public class CustomPermissionCheckboxCell<C, V> extends
		AbstractEditableCell<C, V> {

	UserAction action;
	Map<C, UserAction> pendingActions;
	Map<C, List<UserAction>> permittedActions;

	/**
	 * Instantiates a new custom permission checkbox cell.
	 * 
	 * @param action
	 *            the action
	 * @param pendingActions
	 *            the pending actions
	 * @param permittedActions
	 *            the permitted actions
	 */
	public CustomPermissionCheckboxCell(UserAction action,
			Map<C, UserAction> pendingActions,
			Map<C, List<UserAction>> permittedActions) {
		super("change", "keydown");
		this.action = action;
		this.pendingActions = pendingActions;
		this.permittedActions = permittedActions;
	}

	/**
	 * Render.
	 * 
	 * @param value
	 *            the value
	 * @param key
	 *            the key
	 * @param sb
	 *            the sb
	 */
	public void render(C value, Object key, SafeHtmlBuilder sb) {
		if (value == null) {
			return;
		}

		// if the user has permission for the action, then render according to
		// the boolean value else render as disabled
		List<UserAction> permitted = permittedActions.get(value);
		UserAction pending = pendingActions.get(value);

		if (permitted != null && permitted.contains(this.action)) {
			if (pending != null && pending.equals(this.action)) {
				sb.appendHtmlConstant("<input type='checkbox' checked></input>");
			} else {
				sb.appendHtmlConstant("<input type='checkbox'></input>");
			}
		} else {
			// render as disabled
			if (pending != null && pending.equals(this.action)) {
				sb.appendHtmlConstant("<input type='checkbox' disabled=disabled checked></input>");
			} else {
				sb.appendHtmlConstant("<input type='checkbox' disabled=disabled></input>");
			}
		}
	}

	/**
	 * Checks if is editing.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 * @param arg2
	 *            the arg2
	 * @return true, if is editing
	 * @see com.google.gwt.cell.client.AbstractEditableCell#isEditing(com.google.gwt.dom.client.Element,
	 *      java.lang.Object, java.lang.Object)
	 */
	public boolean isEditing(final Element arg0, final C arg1, final Object arg2) {
		return false;
	}

	/**
	 * On browser event.
	 * 
	 * @param parent
	 *            the parent
	 * @param value
	 *            the value
	 * @param key
	 *            the key
	 * @param event
	 *            the event
	 * @param valueUpdater
	 *            the value updater
	 */
	public final void onBrowserEvent(final Element parent, final C value,
			final Object key, final NativeEvent event,
			final ValueUpdater<C> valueUpdater) {
		String type = event.getType();

		boolean enterPressed = "keydown".equals(type)
				&& event.getKeyCode() == KeyCodes.KEY_ENTER;
		if ("change".equals(type) || enterPressed) {
			if (valueUpdater != null) {
				valueUpdater.update(value);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.cell.client.AbstractEditableCell#isEditing(com.google.
	 * gwt.cell.client.Cell.Context, com.google.gwt.dom.client.Element,
	 * java.lang.Object)
	 */
	@Override
	public final boolean isEditing(
			com.google.gwt.cell.client.Cell.Context arg0, final Element arg1,
			final C arg2) {
		return isEditing(arg1, arg2, arg0.getKey());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.cell.client.AbstractCell#onBrowserEvent(com.google.gwt
	 * .cell.client.Cell.Context, com.google.gwt.dom.client.Element,
	 * java.lang.Object, com.google.gwt.dom.client.NativeEvent,
	 * com.google.gwt.cell.client.ValueUpdater)
	 */
	@Override
	public final void onBrowserEvent(final Cell.Context context,
			final Element parent, final C value, final NativeEvent event,
			final ValueUpdater<C> valueUpdater) {
		onBrowserEvent(parent, value, context.getKey(), event, valueUpdater);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.cell.client.AbstractCell#render(com.google.gwt.cell.client
	 * .Cell.Context, java.lang.Object,
	 * com.google.gwt.safehtml.shared.SafeHtmlBuilder)
	 */
	@Override
	public final void render(
			final com.google.gwt.cell.client.Cell.Context arg0, C arg1,
			final SafeHtmlBuilder arg2) {
		render(arg1, arg0.getKey(), arg2);
	}

}
