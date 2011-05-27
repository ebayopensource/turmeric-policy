/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * The Class Pagination.
 */
public class Pagination {
	private boolean enabled;
	private int currentPage;
	private int itemsPerPage;
	private int totalItems;
	
	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Sets the enabled.
	 * 
	 * @param enabled
	 *            the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * Gets the current page.
	 * 
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * Sets the current page.
	 * 
	 * @param currentPage
	 *            the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * Gets the items per page.
	 * 
	 * @return the items per page
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	
	/**
	 * Sets the items per page.
	 * 
	 * @param itemsPerPage
	 *            the new items per page
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	
	/**
	 * Gets the total items.
	 * 
	 * @return the total items
	 */
	public int getTotalItems() {
		return totalItems;
	}
	
	/**
	 * Sets the total items.
	 * 
	 * @param totalItems
	 *            the new total items
	 */
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
}
