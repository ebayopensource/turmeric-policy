/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.common;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.StackPanel;

/**
 * The Class TurmericDecoratedStackPanel.
 */
public class TurmericDecoratedStackPanel extends StackPanel {
	
	/** The Constant DEFAULT_STYLENAME. */
	public static final String DEFAULT_STYLENAME = "gwt-DecoratedStackPanel";
	private static final String[] DEFAULT_ROW_STYLENAMES = { "stackItemTop",
			"stackItemMiddle" };

	/**
	 * Instantiates a new turmeric decorated stack panel.
	 */
	public TurmericDecoratedStackPanel() {
		setStylePrimaryName("gwt-DecoratedStackPanel");
	}

	Element createHeaderElem() {
		Element table = DOM.createTable();
		Element tbody = DOM.createTBody();
		DOM.appendChild(table, tbody);
		DOM.setStyleAttribute(table, "width", "100%");
		DOM.setElementPropertyInt(table, "cellSpacing", 0);
		DOM.setElementPropertyInt(table, "cellPadding", 0);

		for (int i = 0; i < DEFAULT_ROW_STYLENAMES.length; ++i) {
			DOM.appendChild(tbody,
					TurmericDecoratorPanel.createTR(DEFAULT_ROW_STYLENAMES[i]));
		}

		return table;
	}

	Element getHeaderTextElem(Element headerElem) {
		Element tbody = DOM.getFirstChild(headerElem);
		Element tr = DOM.getChild(tbody, 1);
		Element td = DOM.getChild(tr, 1);
		return DOM.getFirstChild(td);
	}
}
