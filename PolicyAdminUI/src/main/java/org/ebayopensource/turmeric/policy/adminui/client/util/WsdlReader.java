/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.util;

import java.util.Map;
import javax.wsdl.Definition;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;

import com.google.gwt.core.client.GWT;

/**
 * The Class WsdlReader.
 */
public class WsdlReader {

	/** The wsdl instance. */
	private static Definition wsdlInstance;
	
	static {
		// get hold the WSDLFactory
		WSDLFactory factory;
		try {
			factory = WSDLFactory.newInstance();

			// create an object to read the WSDL file
			WSDLReader reader = factory.newWSDLReader();

			// pass the URL to the reader for parsing and get back a WSDL
			// definiton

			String policyServiceWSDLURL = GWT.getModuleBaseURL()
					+ "/PolicyService.wsdl";

			wsdlInstance = reader.readWSDL(policyServiceWSDLURL);
		} catch (WSDLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	/**
	 * Gets the messages.
	 * 
	 * @return the messages
	 */
	public static Map getMessages() {
		if (wsdlInstance != null) {
			return wsdlInstance.getMessages();
		} else {
			return null;
		}
	}

	/**
	 * Gets the port types.
	 * 
	 * @return the port types
	 */
	public static Map getPortTypes() {
		if (wsdlInstance != null) {
			return wsdlInstance.getPortTypes();
		} else {
			return null;
		}
	}

	/**
	 * Gets the bindings.
	 * 
	 * @return the bindings
	 */
	public static Map getBindings() {
		if (wsdlInstance != null) {
			return wsdlInstance.getBindings();
		} else {
			return null;
		}
	}

	/**
	 * Gets the types.
	 * 
	 * @return the types
	 */
	public static Types getTypes() {
		if (wsdlInstance != null) {
			return wsdlInstance.getTypes();
		} else {
			return null;
		}
	}

	/**
	 * Gets the services.
	 * 
	 * @return the services
	 */
	public static Map getServices() {
		if (wsdlInstance != null) {
			return wsdlInstance.getServices();
		} else {
			return null;
		}
	}

}
