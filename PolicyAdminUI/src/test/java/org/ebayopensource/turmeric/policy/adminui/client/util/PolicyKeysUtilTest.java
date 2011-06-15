/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.model.DummyPolicyQueryServiceImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyType;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceType;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectType;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class PolicyKeysUtilTest {

	@Before
	public void setUp() {
		PolicyType.init(new DummyPolicyQueryServiceImpl(),
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

		ResourceType.init(new DummyPolicyQueryServiceImpl(),
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

		SubjectType.init(new DummyPolicyQueryServiceImpl(),
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

	}

	@Test
	public void testGetAllPolicyKeyList() {
		assertNotNull(PolicyKeysUtil.getAllPolicyKeyList());
		assertEquals(4, PolicyKeysUtil.getAllPolicyKeyList().size());
	}

	@Test
	public void testGetAllResourcesKeyList() {
		assertNotNull(PolicyKeysUtil.getAllResourceKeyList());
		assertEquals(3, PolicyKeysUtil.getAllResourceKeyList().size());
	}

	@Test
	public void testGetAllSubjectKeyList() {
		assertNotNull(PolicyKeysUtil.getAllSubjectKeyList());
		assertEquals(4, PolicyKeysUtil.getAllSubjectKeyList().size());
	}

	@Test
	public void testGetAllOperationKeyList() {
		assertNotNull(PolicyKeysUtil.getAllOperationKeyList());
		assertEquals(3, PolicyKeysUtil.getAllOperationKeyList().size());
	}
}
