/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.presenter.policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.OperationKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetEntityHistoryResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.QueryCondition;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectKey;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter;
import org.ebayopensource.turmeric.policy.adminui.client.util.PolicyKeysUtil;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The Class HistoryChangeSummaryPresenter.
 */
public class HistoryChangeSummaryPresenter extends AbstractGenericPresenter {

	/** The Constant PRESENTER_ID. */
	public static final String PRESENTER_ID = "HistoryChangeSummary";

	/** The event bus. */
	private HandlerManager eventBus;

	/** The view. */
	private HistoryChangeSummaryDisplay view;

	/** The service map. */
	private Map<SupportedService, PolicyAdminUIService> serviceMap;

	/** The entities. */
	protected List<EntityHistory> entities;

	/** The service. */
	protected PolicyQueryService service;

	/**
	 * The Interface HistoryChangeSummaryDisplay.
	 */
	public interface HistoryChangeSummaryDisplay extends
			PolicyPageTemplateDisplay {
		void setEntities(List<EntityHistory> entities);

		void setEntityTypes(List<String> entityTypes);

		HasClickHandlers getSearchButton();

		String getEntity();

		long getFrom();

		long getTo();

		void error(String error);
	}

	/**
	 * Instantiates a new history change summary presenter.
	 * 
	 * @param eventBus
	 *            the event bus
	 * @param view
	 *            the view
	 * @param serviceMap
	 *            the service map
	 */
	public HistoryChangeSummaryPresenter(final HandlerManager eventBus,
			final HistoryChangeSummaryDisplay view,
			final Map<SupportedService, PolicyAdminUIService> serviceMap) {
		this.eventBus = eventBus;
		this.view = view;
		this.view.setAssociatedId(getId());
		this.serviceMap = serviceMap;

		bind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter
	 * #getId()
	 */
	public String getId() {
		return PRESENTER_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.
	 * AbstractGenericPresenter#getView()
	 */
	@Override
	protected final PolicyPageTemplateDisplay getView() {
		return view;
	}

	/**
	 * Bind.
	 */
	public void bind() {

		this.view.getSearchButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				fetchEntities(view.getEntity(), view.getFrom(), view.getTo());
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.
	 * AbstractGenericPresenter#go(com.google.gwt.user.client.ui.HasWidgets,
	 * org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken)
	 */
	@Override
	public final void go(final HasWidgets container, final HistoryToken token) {
		container.clear();

		this.view.setEntities(null);

		setEntityTypes();

		service = (PolicyQueryService) serviceMap
				.get(SupportedService.POLICY_QUERY_SERVICE);

		this.view.activate();
		container.add(this.view.asWidget());
	}

	private void setEntityTypes() {

		List<String> entitiesList = new ArrayList<String>();
		entitiesList.add("All");
		entitiesList.add("Resource");
		entitiesList.add("Operation");
		entitiesList.add("Subject");
		entitiesList.add("Subject Group");
		entitiesList.add("Authorization Policy");
		entitiesList.add("Blacklist Policy");
		entitiesList.add("Rate Limiting Policy");
		entitiesList.add("Whitelist Policy");

		this.view.setEntityTypes(entitiesList);
	}

	private void fetchEntities(final String entityType, long from, long to) {

		if (from == 0) {
			from = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
		}
		if (to == 0) {
			to = System.currentTimeMillis();
		}

		List<PolicyKey> poKeys = null;
		List<SubjectKey> sKeys = null;
		List<SubjectGroupKey> sgKeys = null;
		List<ResourceKey> rsKeys = null;
		List<OperationKey> opKeys = null;

		PolicyKey pkey = null;
		SubjectKey sKey = null;
		SubjectGroupKey sgKey = null;
		ResourceKey rsKey = null;

		if ("Authorization Policy".equals(entityType)) {
			pkey = new PolicyKey();
			pkey.setType("AUTHZ");
			poKeys = new ArrayList<PolicyKey>(Collections.singletonList(pkey));
			QueryCondition condition = new QueryCondition();
			condition.addQuery(new QueryCondition.Query(
					QueryCondition.ActivePoliciesOnlyValue.FALSE));
		} else if ("Blacklist Policy".equals(entityType)) {
			pkey = new PolicyKey();
			pkey.setType("BLACKLIST");
			poKeys = new ArrayList<PolicyKey>(Collections.singletonList(pkey));
			QueryCondition condition = new QueryCondition();
			condition.addQuery(new QueryCondition.Query(
					QueryCondition.ActivePoliciesOnlyValue.FALSE));
		} else if ("Whitelist Policy".equals(entityType)) {
			pkey = new PolicyKey();
			pkey.setType("WHITELIST");
			poKeys = new ArrayList<PolicyKey>(Collections.singletonList(pkey));
			QueryCondition condition = new QueryCondition();
			condition.addQuery(new QueryCondition.Query(
					QueryCondition.ActivePoliciesOnlyValue.FALSE));
		} else if ("Rate Limiting Policy".equals(entityType)) {
			pkey = new PolicyKey();
			pkey.setType("RL");
			poKeys = new ArrayList<PolicyKey>(Collections.singletonList(pkey));
			QueryCondition condition = new QueryCondition();
			condition.addQuery(new QueryCondition.Query(
					QueryCondition.ActivePoliciesOnlyValue.FALSE));
		} else if ("Subject".equals(entityType)) {
			sKeys = new ArrayList<SubjectKey>(
					PolicyKeysUtil.getAllSubjectKeyList());
		} else if ("Subject Group".equals(entityType)) {
			sgKeys = new ArrayList<SubjectGroupKey>(
					PolicyKeysUtil.getAllSubjectGroupKeyList());
		} else if ("Resource".equals(entityType)) {
			rsKeys = new ArrayList<ResourceKey>(
					PolicyKeysUtil.getAllResourceKeyList());
		} else if ("Operation".equals(entityType)) {
			opKeys = new ArrayList<OperationKey>(
					PolicyKeysUtil.getAllOperationKeyList());
		} else if ("All".equals(entityType)) {
			poKeys = new ArrayList<PolicyKey>(
					PolicyKeysUtil.getAllPolicyKeyList());
			rsKeys = new ArrayList<ResourceKey>(
					PolicyKeysUtil.getAllResourceKeyList());
			opKeys = new ArrayList<OperationKey>(
					PolicyKeysUtil.getAllOperationKeyList());
			sKeys = new ArrayList<SubjectKey>(
					PolicyKeysUtil.getAllSubjectKeyList());
			sgKeys = new ArrayList<SubjectGroupKey>(
					PolicyKeysUtil.getAllSubjectGroupKeyList());
		}

		service.getEntityHistory(from, to, poKeys, rsKeys, opKeys, sKeys,
				sgKeys, new AsyncCallback<GetEntityHistoryResponse>() {
					public void onFailure(final Throwable arg) {
						if (arg.getLocalizedMessage().contains("500")) {
							view.error(PolicyAdminUIUtil.messages
									.serverError(PolicyAdminUIUtil.policyAdminConstants
											.genericErrorMessage()));
						} else {
							view.error(PolicyAdminUIUtil.messages
									.serverError(arg.getLocalizedMessage()));
						}

					}

					public void onSuccess(final GetEntityHistoryResponse result) {
						List<EntityHistory> entities = new ArrayList<EntityHistory>(
								result.getEntities());
						if (entities != null && entities.size() > 0) {
							view.setEntities(entities);
						} else
							view.setEntities(null);
					}
				});

	}

}
