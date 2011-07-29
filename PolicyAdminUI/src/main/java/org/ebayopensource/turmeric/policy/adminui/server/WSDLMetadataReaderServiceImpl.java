/*********************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.server;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.WSDL.WSDLMetadataReaderInterface;
import org.ebayopensource.turmeric.security.v1.services.EffectType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class WSDLMetadataReaderServiceImpl extends RemoteServiceServlet implements
		WSDLMetadataReaderInterface {

	private static final long serialVersionUID = 1L;

	public List<String> getEffectTypes() {
		List<String> effectTypes = new ArrayList<String>();
		for (EffectType effectType : EffectType.values()) {
			effectTypes.add(effectType.value());
		}
		return effectTypes;
	}
}
