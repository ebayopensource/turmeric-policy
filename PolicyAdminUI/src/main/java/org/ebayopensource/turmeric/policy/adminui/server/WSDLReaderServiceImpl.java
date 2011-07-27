/*********************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.server;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.wsdl.Definition;
import javax.wsdl.Service;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.ebayopensource.turmeric.policy.adminui.client.WSDL.WSDLReaderInterface;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class WSDLReaderServiceImpl extends RemoteServiceServlet implements
		WSDLReaderInterface {

	private static final long serialVersionUID = 1L;

	private  Definition wsdlInstance;
	private String wsdlPolicyServiceURL;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		if (config.getInitParameter("PolicyServiceURL") != null) {
			wsdlPolicyServiceURL = config.getInitParameter("PolicyServiceURL")
					+ "?wsdl";
		}

		// get hold the WSDLFactory
		WSDLFactory factory;
		try {
			factory = WSDLFactory.newInstance();

			// create an object to read the WSDL file
			WSDLReader reader = factory.newWSDLReader();

			// pass the URL to the reader for parsing and get back a WSDL 
			// definiton
			wsdlInstance = reader.readWSDL(wsdlPolicyServiceURL);
			System.out.println(wsdlInstance.getTypes());

		} catch (WSDLException e) {
			// TODO Jose Send the error to Browser
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the messages.
	 * 
	 * @return the messages
	 */
	public Map getMessages() {
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
	public  Map getPortTypes() {
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
	public  Map getBindings() {
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
	public  Types getTypes() {
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
	public  Map getServices() {
		if (wsdlInstance != null) {
			return wsdlInstance.getServices();
		} else {
			return null;
		}
	}


	public List<String> getEffectTypes() {
		System.out.println(wsdlInstance.getTypes());
		return null;
	}

}
