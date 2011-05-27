/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.common;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Class TurmericStackPanel.
 */
public class TurmericStackPanel extends ComplexPanel implements
		InsertPanel.ForIsWidget {
	private static final String DEFAULT_STYLENAME = "gwt-StackPanel";
	private static final String DEFAULT_ITEM_STYLENAME = "gwt-StackPanelItem";
	private Element body;
	private int visibleStack = -1;

	/**
	 * Instantiates a new turmeric stack panel.
	 */
	public TurmericStackPanel() {
		Element table = DOM.createTable();
		setElement(table);

		this.body = DOM.createTBody();
		DOM.appendChild(table, this.body);
		DOM.setElementPropertyInt(table, "cellSpacing", 0);
		DOM.setElementPropertyInt(table, "cellPadding", 0);

		DOM.sinkEvents(table, 1);
		setStyleName("gwt-StackPanel");
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Panel#add(com.google.gwt.user.client.ui.Widget)
	 */
	public void add(Widget w) {
		insert(w, getWidgetCount());
	}

	/**
	 * Adds the.
	 * 
	 * @param w
	 *            the w
	 * @param stackText
	 *            the stack text
	 */
	public void add(Widget w, String stackText) {
		add(w, stackText, false);
	}

	/**
	 * Adds the.
	 * 
	 * @param w
	 *            the w
	 * @param stackHtml
	 *            the stack html
	 */
	public void add(Widget w, SafeHtml stackHtml) {
		add(w, stackHtml.asString(), true);
	}

	/**
	 * Adds the.
	 * 
	 * @param w
	 *            the w
	 * @param stackText
	 *            the stack text
	 * @param asHTML
	 *            the as html
	 */
	public void add(Widget w, String stackText, boolean asHTML) {
		add(w);
		setStackText(getWidgetCount() - 1, stackText, asHTML);
	}

	/**
	 * Gets the selected index.
	 * 
	 * @return the selected index
	 */
	public int getSelectedIndex() {
		return this.visibleStack;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.InsertPanel.ForIsWidget#insert(com.google.gwt.user.client.ui.IsWidget, int)
	 */
	public void insert(IsWidget w, int beforeIndex) {
		insert(asWidgetOrNull(w), beforeIndex);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.InsertPanel#insert(com.google.gwt.user.client.ui.Widget, int)
	 */
	public void insert(Widget w, int beforeIndex) {
		Element trh = DOM.createTR();
		Element tdh = DOM.createTD();
		DOM.appendChild(trh, tdh);
		DOM.appendChild(tdh, createHeaderElem());

		Element trb = DOM.createTR();
		Element tdb = DOM.createTD();
		DOM.appendChild(trb, tdb);

		beforeIndex = adjustIndex(w, beforeIndex);
		int effectiveIndex = beforeIndex * 2;

		DOM.insertChild(this.body, trb, effectiveIndex);
		DOM.insertChild(this.body, trh, effectiveIndex);

		setStyleName(tdh, "gwt-StackPanelItem", true);
		DOM.setElementPropertyInt(tdh, "__owner", super.hashCode());
		DOM.setElementProperty(tdh, "height", "1px");

		setStyleName(tdb, "gwt-StackPanelContent", true);
		DOM.setElementProperty(tdb, "height", "100%");
		DOM.setElementProperty(tdb, "vAlign", "top");

		insert(w, tdb, beforeIndex, false);

		updateIndicesFrom(beforeIndex);

		if (this.visibleStack == -1) {
			showStack(0);
		} else {
			setStackVisible(beforeIndex, false);
			if (this.visibleStack >= beforeIndex) {
				this.visibleStack += 1;
			}

			setStackVisible(this.visibleStack, true);
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onBrowserEvent(com.google.gwt.user.client.Event)
	 */
	public void onBrowserEvent(Event event) {
		if (DOM.eventGetType(event) == 1) {
			Element target = DOM.eventGetTarget(event);
			int index = findDividerIndex(target);
			if (index != -1) {
				showStack(index);
			}
		}
		super.onBrowserEvent(event);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ComplexPanel#remove(int)
	 */
	public boolean remove(int index) {
		return remove(getWidget(index), index);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ComplexPanel#remove(com.google.gwt.user.client.ui.Widget)
	 */
	public boolean remove(Widget child) {
		return remove(child, getWidgetIndex(child));
	}

	/**
	 * Sets the stack text.
	 * 
	 * @param index
	 *            the index
	 * @param text
	 *            the text
	 */
	public void setStackText(int index, String text) {
		setStackText(index, text, false);
	}

	/**
	 * Sets the stack text.
	 * 
	 * @param index
	 *            the index
	 * @param html
	 *            the html
	 */
	public void setStackText(int index, SafeHtml html) {
		setStackText(index, html.asString(), true);
	}

	/**
	 * Sets the stack text.
	 * 
	 * @param index
	 *            the index
	 * @param text
	 *            the text
	 * @param asHTML
	 *            the as html
	 */
	public void setStackText(int index, String text, boolean asHTML) {
		if (index >= getWidgetCount()) {
			return;
		}

		Element tdWrapper = DOM.getChild(DOM.getChild(this.body, index * 2), 0);
		Element headerElem = DOM.getFirstChild(tdWrapper);
		if (asHTML)
			DOM.setInnerHTML(getHeaderTextElem(headerElem), text);
		else
			DOM.setInnerText(getHeaderTextElem(headerElem), text);
	}

	/**
	 * Show stack.
	 * 
	 * @param index
	 *            the index
	 */
	public void showStack(int index) {
		if ((index >= getWidgetCount()) || index < 1) {
			return;
		}

		visibleStack = index;
		setStackVisible(visibleStack, !getWidget(visibleStack).isVisible());

		// replaced code in order to mantain all panels open

		// if ((index >= getwidgetcount()) || (index < 0)
		// || (index == this.visiblestack)) {
		// return;
		// }
		//
		// if (this.visiblestack >= 0) {
		// setstackvisible(this.visiblestack, false);
		// }
		//
		// this.visiblestack = index;
		// setstackvisible(this.visiblestack, true);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.UIObject#onEnsureDebugId(java.lang.String)
	 */
	protected void onEnsureDebugId(String baseID) {
		super.onEnsureDebugId(baseID);

		int numHeaders = DOM.getChildCount(this.body) >> 1;
		for (int i = 0; i < numHeaders; ++i) {
			Element tdWrapper = DOM.getFirstChild(DOM
					.getChild(this.body, 2 * i));
			Element headerElem = DOM.getFirstChild(tdWrapper);
			Element bodyElem = DOM.getFirstChild(DOM.getChild(this.body,
					2 * i + 1));
			ensureDebugId(tdWrapper, baseID, "text-wrapper" + i);
			ensureDebugId(bodyElem, baseID, "content" + i);
			ensureDebugId(getHeaderTextElem(headerElem), baseID, "text" + i);
		}
	}

	Element createHeaderElem() {
		return DOM.createDiv();
	}

	Element getHeaderTextElem(Element headerElem) {
		return headerElem;
	}

	private int findDividerIndex(Element elem) {
		while ((elem != null) && (elem != getElement())) {
			String expando = DOM.getElementProperty(elem, "__index");
			if (expando != null) {
				int ownerHash = DOM.getElementPropertyInt(elem, "__owner");
				if (ownerHash == super.hashCode()) {
					return Integer.parseInt(expando);
				}

				return -1;
			}

			elem = DOM.getParent(elem);
		}
		return -1;
	}

	private boolean remove(Widget child, int index) {
		boolean removed = super.remove(child);
		if (removed) {
			int rowIndex = 2 * index;
			Element tr = DOM.getChild(this.body, rowIndex);
			DOM.removeChild(this.body, tr);
			tr = DOM.getChild(this.body, rowIndex);
			DOM.removeChild(this.body, tr);

			if (this.visibleStack == index)
				this.visibleStack = -1;
			else if (this.visibleStack > index) {
				this.visibleStack -= 1;
			}

			updateIndicesFrom(index);
		}
		return removed;
	}

	private void setStackContentVisible(int index, boolean visible) {
		Element tr = DOM.getChild(this.body, index * 2 + 1);
		UIObject.setVisible(tr, visible);
		getWidget(index).setVisible(visible);
	}

	private void setStackVisible(int index, boolean visible) {
		Element tr = DOM.getChild(this.body, index * 2);
		if (tr == null) {
			return;
		}

		Element td = DOM.getFirstChild(tr);
		setStyleName(td, "gwt-StackPanelItem-selected", visible);

		setStackContentVisible(index, visible);

		Element trNext = DOM.getChild(this.body, (index + 1) * 2);
		if (trNext != null) {
			Element tdNext = DOM.getFirstChild(trNext);
			setStyleName(tdNext, "gwt-StackPanelItem-below-selected", visible);
		}
	}

	private void updateIndicesFrom(int beforeIndex) {
		int i = beforeIndex;
		for (int c = getWidgetCount(); i < c; ++i) {
			Element childTR = DOM.getChild(this.body, i * 2);
			Element childTD = DOM.getFirstChild(childTR);
			DOM.setElementPropertyInt(childTD, "__index", i);

			if (beforeIndex == 0)
				setStyleName(childTD, "gwt-StackPanelItem-first", true);
			else
				setStyleName(childTD, "gwt-StackPanelItem-first", false);
		}
	}
}
