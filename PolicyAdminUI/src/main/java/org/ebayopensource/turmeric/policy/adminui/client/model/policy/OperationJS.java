/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The Class OperationJS.
 */
public class OperationJS extends JavaScriptObject implements Operation {

	  /**
	 * Instantiates a new operation js.
	 */
  	protected OperationJS() {}
	   
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getDescription()
	 */
	@Override
	public  native final String getDescription() /*-{
		return this["@Description"];
	}-*/;

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getOperationName()
	 */
	@Override
	public native final String getOperationName() /*-{
		return this["@OperationName"];
	}-*/;
	

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getOperationId()
	 */
	@Override
	public  native final String getOperationId() /*-{
		return this["@OperationId"];
	}-*/;

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getCreationDate()
	 */
	@Override
	public  final long getCreationDate() {
		return 0;
	};

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getCreationBy()
	 */
	@Override
	public native final  String getCreationBy() /*-{
		return this["@CreationBy"];
	}-*/;


	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getLastModifiedTime()
	 */
	@Override
	public final  long getLastModifiedTime()  {
		return 0;
	};


	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation#getLastModifiedBy()
	 */
	@Override
	public native final  String getLastModifiedBy()  /*-{
		return this["@LastModifiedBy"];
	}-*/;


}
