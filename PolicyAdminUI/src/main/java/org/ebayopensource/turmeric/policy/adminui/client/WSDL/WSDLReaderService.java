/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.WSDL;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class WSDLReaderService implements WSDLReaderInterfaceAsync {
	
	WSDLReaderInterfaceAsync service = (WSDLReaderInterfaceAsync) GWT.create(WSDLReaderInterface.class);
	ServiceDefTarget endpoint = (ServiceDefTarget) service;

	public WSDLReaderService()
	{
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "wsdlReaderService");
	}
	
	@Override
	public void getEffectTypes(AsyncCallback<List<String>> callback) {
		service.getEffectTypes(callback);
	}
}
