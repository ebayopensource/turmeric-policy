/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.presenter.policy;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupQuery;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * SubjectGroupViewPresenter.
 */
public class SubjectGroupViewPresenter extends AbstractGenericPresenter {

	/** The Constant PRESENTER_ID. */
	public static final String PRESENTER_ID = "SubjectGroupView";

	/** The event bus. */
	protected HandlerManager eventBus;

	/** The view. */
	protected SubjectGroupViewDisplay view;

	/** The group. */
	protected SubjectGroup group;

	/** The service map. */
	protected Map<SupportedService, PolicyAdminUIService> serviceMap;

	/**
	 * The Interface SubjectGroupViewDisplay.
	 */
	public interface SubjectGroupViewDisplay extends PolicyPageTemplateDisplay {

		/**
		 * Sets the name.
		 * 
		 * @param name
		 *            the new name
		 */
		void setName(String name);

		/**
		 * Gets the name.
		 * 
		 * @return the name
		 */
		String getName();

		/**
		 * Sets the description.
		 * 
		 * @param desc
		 *            the new description
		 */
		void setDescription(String desc);

		/**
		 * Gets the description.
		 * 
		 * @return the description
		 */
		String getDescription();

		/**
		 * Sets the type.
		 * 
		 * @param type
		 *            the new type
		 */
		void setType(String type);

		/**
		 * Gets the type.
		 * 
		 * @return the type
		 */
		String getType();

		/**
		 * Gets the cancel button.
		 * 
		 * @return the cancel button
		 */
		HasClickHandlers getCancelButton();

		/**
		 * Sets the subjects.
		 * 
		 * @param subjects
		 *            the new subjects
		 */
		void setSubjects(List<String> subjects);

		/**
		 * Error.
		 * 
		 * @param msg
		 *            the msg
		 */
		void error(String msg);

		/**
		 * Clear.
		 */
		void clear();
	}

	/**
	 * Instantiates a new subject group view presenter.
	 * 
	 * @param eventBus
	 *            the event bus
	 * @param view
	 *            the view
	 * @param serviceMap
	 *            the service map
	 */
	public SubjectGroupViewPresenter(final HandlerManager eventBus,
			final SubjectGroupViewDisplay view,
			Map<SupportedService, PolicyAdminUIService> serviceMap) {
		this.eventBus = eventBus;
		this.view = view;
		this.view.setAssociatedId(getId());
		this.serviceMap = serviceMap;
		bind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.
	 * AbstractGenericPresenter#getView()
	 */
	@Override
	protected final Display getView() {
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter
	 * #getId()
	 */
	@Override
	public final String getId() {
		return PRESENTER_ID;
	}

	/**
	 * Bind.
	 */
	public final void bind() {
		this.view.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				view.clear();
				// Just go back to the summary
				HistoryToken token = makeToken(PolicyController.PRESENTER_ID,
						SubjectGroupSummaryPresenter.PRESENTER_ID, null);
				History.newItem(token.toString(), true);
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
	public void go(final HasWidgets container, final HistoryToken token) {
		// Get the id from the token
		final String name = token
				.getValue(HistoryToken.SELECTED_SUBJECT_GROUP_TOKEN);
		final String type = token
				.getValue(HistoryToken.SELECTED_SUBJECT_GROUP_TYPE_TOKEN);
		if (name != null) {
			container.clear();
			view.activate();
			container.add(view.asWidget());

			// Get the SubjectGroup being viewed
			final PolicyQueryService service = (PolicyQueryService) serviceMap
					.get(SupportedService.POLICY_QUERY_SERVICE);
			SubjectGroupQuery query = new SubjectGroupQuery();
			query.setIncludeSubjects(true);
			SubjectGroupKey key = new SubjectGroupKey();
			key.setName(name);
			key.setType(type);
			query.setGroupKeys(Collections.singletonList(key));

			service.findSubjectGroups(query,
					new AsyncCallback<FindSubjectGroupsResponse>() {

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

						public void onSuccess(
								final FindSubjectGroupsResponse response) {
							if (response.getGroups() != null
									&& response.getGroups().size() > 0) {
								group = response.getGroups().get(0);
								view.setName(group.getName());
								view.setType(group.getType());
								view.setDescription(group.getDescription());
								view.setSubjects(group.getSubjects());
							}
						}
					});
		}
	}
}
