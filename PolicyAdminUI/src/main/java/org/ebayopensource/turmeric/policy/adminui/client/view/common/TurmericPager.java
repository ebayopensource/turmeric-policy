/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.common;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.Range;

/*
 * from http://gwtnews.blogspot.com/2011/04/re-gwt-21-celltable-row.html
 */
public class TurmericPager extends SimplePager {
	protected String createText() {
		NumberFormat formatter = NumberFormat.getFormat("#,###");
		HasRows display = getDisplay();
		Range range = display.getVisibleRange();
		int pageStart = range.getStart() + 1;
		int pageSize = range.getLength();
		int dataSize = display.getRowCount();
		int endIndex = Math.min(dataSize, pageStart + pageSize - 1);
		endIndex = Math.max(pageStart, endIndex);
		boolean exact = display.isRowCountExact();
		if (dataSize == 0) {
			return "0 of 0";
		} else if (pageStart == endIndex) {
			return formatter.format(pageStart) + " of "
					+ formatter.format(dataSize);
		}
		return formatter.format(pageStart) + "-" + formatter.format(endIndex)
				+ (exact ? " of " : " of over ") + formatter.format(dataSize);
	}

}
