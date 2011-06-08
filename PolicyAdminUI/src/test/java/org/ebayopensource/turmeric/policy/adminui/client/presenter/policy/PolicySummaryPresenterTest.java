/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.presenter.policy;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.DummyPolicyQueryServiceImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy;
import org.ebayopensource.turmeric.policy.adminui.client.view.policy.PolicySummaryView;
import org.ebayopensource.turmeric.policy.adminui.test.PolicyAdminUIGWTTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.core.java.util.Collections;
import com.octo.gwt.test.utils.GwtReflectionUtils;

public class PolicySummaryPresenterTest extends PolicyAdminUIGWTTestBase {

	PolicySummaryPresenter summaryPresenter = null;
	HandlerManager eventBus = null;
	Map<SupportedService, PolicyAdminUIService> serviceMap = null;
	PolicySummaryView view = null;

	@Before
	public void setUp() {
		eventBus = new HandlerManager(null);
		serviceMap = null;
		view = createMock(PolicySummaryView.class);
	}

	@After
	public void tearDown() {
		serviceMap = null;
		eventBus = null;
		summaryPresenter = null;
	}

	@Test
	public void testFetchResourcesByTypes() {
		summaryPresenter = new PolicySummaryPresenter(this.eventBus, view,
				serviceMap);
		summaryPresenter.service = new DummyPolicyQueryServiceImpl();
		view.setRsNames(new ArrayList<String>(Arrays.asList("ResourceSERVICE4",
				"ResourceSERVICE5")));
		view.setResourceNames();
		EasyMock.replay(view);

		GwtReflectionUtils.callPrivateMethod(summaryPresenter,
				"fetchResourcesByType", "SERVICE");
		assertEquals(2, summaryPresenter.resources.size());
	}


//	@Test
//	public void testFetchPoliciesByName() {
//		summaryPresenter = new PolicySummaryPresenter(this.eventBus, view,
//				serviceMap);
//		summaryPresenter.service = new DummyPolicyQueryServiceImpl();
//		List<GenericPolicy> policiesList = new ArrayList<GenericPolicy>(DummyPolicyQueryServiceImpl.tmpPolicies);
//		List<GenericPolicy> policyList = new ArrayList<GenericPolicy>();
//		policyList.add(policiesList.get(0));
//		view.setPolicies(policyList);
//		EasyMock.replay(view);
//		
//		GwtReflectionUtils.callPrivateMethod(summaryPresenter,
//				"fetchPoliciesByName", "Policy_1", "BlackList", "");
//		assertEquals(1, summaryPresenter.policies.size());
//		assertEquals("Policy_1", summaryPresenter.policies.get(0).getName());
//	}

}