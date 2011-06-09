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
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.model.DummyPolicyEnforcementServiceImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.DummyPolicyQueryServiceImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyType;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceType;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectType;
import org.ebayopensource.turmeric.policy.adminui.client.view.policy.WLPolicyEditView;
import org.ebayopensource.turmeric.policy.adminui.test.PolicyAdminUIGWTTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.octo.gwt.test.utils.GwtReflectionUtils;

public class WLPolicyEditPresenterTest extends PolicyAdminUIGWTTestBase {

	WLPolicyEditPresenter wlPolicyEditPresenter = null;
	HandlerManager eventBus = null;
	Map<SupportedService, PolicyAdminUIService> serviceMap = null;
	WLPolicyEditView view = null;

	@Before
	public void setUp() {
		eventBus = new HandlerManager(null);
		serviceMap = new HashMap<SupportedService, PolicyAdminUIService>();
		serviceMap.put(SupportedService.POLICY_QUERY_SERVICE,
				new DummyPolicyQueryServiceImpl());
		serviceMap.put(SupportedService.POLICY_ENFORCEMENT_SERVICE,
				new DummyPolicyEnforcementServiceImpl());

		view = createMock(WLPolicyEditView.class);
	}

	@After
	public void tearDown() {
		serviceMap = null;
		eventBus = null;
		wlPolicyEditPresenter = null;
	}

	@Test
	public void testFetchSubjects() {
		wlPolicyEditPresenter = new WLPolicyEditPresenter(this.eventBus,
				view, serviceMap);
		wlPolicyEditPresenter.service = (PolicyQueryService) serviceMap
				.get(SupportedService.POLICY_QUERY_SERVICE);
		replay(view);

		PolicyType.init(wlPolicyEditPresenter.service,
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

		ResourceType.init(wlPolicyEditPresenter.service,
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

		SubjectType.init(wlPolicyEditPresenter.service,
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

		GwtReflectionUtils.callPrivateMethod(wlPolicyEditPresenter,
				"fetchSubjects");
		assertNotNull(wlPolicyEditPresenter.allSubjects);
		assertEquals(DummyPolicyQueryServiceImpl.tmpSubjects.size(),
				wlPolicyEditPresenter.allSubjects.size());

	}

	@Test
	public void testFetchResources() {
		wlPolicyEditPresenter = new WLPolicyEditPresenter(this.eventBus,
				view, serviceMap);
		wlPolicyEditPresenter.service = (PolicyQueryService) serviceMap
				.get(SupportedService.POLICY_QUERY_SERVICE);
		replay(view);

		PolicyType.init(wlPolicyEditPresenter.service,
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

		ResourceType.init(wlPolicyEditPresenter.service,
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

		SubjectType.init(wlPolicyEditPresenter.service,
				new AsyncCallback<List<String>>() {
					public void onFailure(final Throwable arg) {
						// do nothing
					}

					public void onSuccess(List<String> arg0) {
						// nothing to do, the PolicyTypes have been loaded
					}
				});

		GwtReflectionUtils.callPrivateMethod(wlPolicyEditPresenter,
				"fetchResources");
		assertNotNull(wlPolicyEditPresenter.availableResourcesByType);
		assertEquals(DummyPolicyQueryServiceImpl.tmpResources.size(),
				wlPolicyEditPresenter.availableResourcesByType.size());

	}

}