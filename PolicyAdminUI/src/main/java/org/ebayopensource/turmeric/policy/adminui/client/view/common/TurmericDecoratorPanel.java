/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.common;

import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;

public class TurmericDecoratorPanel extends SimplePanel {
	private static final String DEFAULT_STYLENAME = "gwt-DecoratorPanel";
	private static final String[] DEFAULT_ROW_STYLENAMES = { "top", "middle",
			"bottom" };
	private Element containerElem;
	private Element tbody;

	static Element createTR(String styleName) {
		Element trElem = DOM.createTR();
		setStyleName(trElem, styleName);
		if (LocaleInfo.getCurrentLocale().isRTL()) {
			DOM.appendChild(trElem, createTD(styleName + "Right"));
			DOM.appendChild(trElem, createTD(styleName + "Center"));
			DOM.appendChild(trElem, createTD(styleName + "Left"));
		} else {
			DOM.appendChild(trElem, createTD(styleName + "Left"));
			DOM.appendChild(trElem, createTD(styleName + "Center"));
			DOM.appendChild(trElem, createTD(styleName + "Right"));
		}
		return trElem;
	}

	private static Element createTD(String styleName) {
		Element tdElem = DOM.createTD();
		Element inner = DOM.createDiv();
		DOM.appendChild(tdElem, inner);
		setStyleName(tdElem, styleName);
		setStyleName(inner, styleName + "Inner");
		return tdElem;
	}

	public TurmericDecoratorPanel() {
		this(DEFAULT_ROW_STYLENAMES, 1);
	}

	TurmericDecoratorPanel(String[] rowStyles, int containerIndex) {
		super(DOM.createTable());

		Element table = getElement();
		this.tbody = DOM.createTBody();
		DOM.appendChild(table, this.tbody);
		DOM.setElementPropertyInt(table, "cellSpacing", 0);
		DOM.setElementPropertyInt(table, "cellPadding", 0);

		for (int i = 0; i < rowStyles.length; ++i) {
			Element row = createTR(rowStyles[i]);
			DOM.appendChild(this.tbody, row);
			if (i == containerIndex) {
				this.containerElem = DOM.getFirstChild(DOM.getChild(row, 1));
			}

		}

		setStyleName("gwt-DecoratorPanel");
	}

	protected Element getCellElement(int row, int cell) {
		Element tr = DOM.getChild(this.tbody, row);
		Element td = DOM.getChild(tr, cell);
		return DOM.getFirstChild(td);
	}

	protected Element getContainerElement() {
		return this.containerElem;
	}
}
