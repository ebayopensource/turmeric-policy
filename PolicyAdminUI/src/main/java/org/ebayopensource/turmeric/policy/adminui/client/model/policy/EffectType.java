/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.Collections;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.WSDL.WSDLMetadataReaderService;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * EffectType.
 */
public class EffectType {
	private static List<String> values;
	private static boolean init;

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	public static List<String> getValues() {
		return Collections.unmodifiableList(values);
	}

	/**
	 * Inits the.
	 * 
	 * @param service
	 *            the service
	 * @param callback
	 *            the callback
	 */
	public static void init(WSDLMetadataReaderService service, 
			final AsyncCallback<List<String>> callback) {
		if (init) {
			return;
		}

		service.getEffectTypes(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> effectTypes) {
				values = effectTypes;
				init = true;
				callback.onSuccess(values);

			}

			public void onFailure(Throwable arg0) {
				init = false;
				callback.onFailure(arg0);
			}
		});
	}

}
