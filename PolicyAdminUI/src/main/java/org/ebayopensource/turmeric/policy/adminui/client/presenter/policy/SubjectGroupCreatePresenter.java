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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectGroupsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindExternalSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetMetaDataResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.QueryCondition;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.QueryCondition.Query;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectQuery;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The Class SubjectGroupCreatePresenter.
 */
public class SubjectGroupCreatePresenter extends AbstractGenericPresenter {

	/** The Constant PRESENTER_ID. */
	public final static String PRESENTER_ID = "SubjectGroupCreate";

	/** The event bus. */
	protected HandlerManager eventBus;
	
	/** The view. */
	protected SubjectGroupCreateDisplay view;
	
	/** The service map. */
	protected Map<SupportedService, PolicyAdminUIService> serviceMap;
	
	/** The subject types. */
	protected List<String> subjectTypes;
	private List<Long> createdSubjectIds;

	/** The service. */
	protected PolicyQueryService service;

	/**
	 * The Interface SubjectGroupCreateDisplay.
	 */
	public interface SubjectGroupCreateDisplay extends
			PolicyPageTemplateDisplay {

		/**
		 * Gets the creates the button.
		 * 
		 * @return the creates the button
		 */
		Button getCreateButton();

		/**
		 * Gets the cancel button.
		 * 
		 * @return the cancel button
		 */
		HasClickHandlers getCancelButton();

		/**
		 * Gets the search button.
		 * 
		 * @return the search button
		 */
		HasClickHandlers getSearchButton();

		/**
		 * Gets the selected subjects.
		 * 
		 * @return the selected subjects
		 */
		List<String> getSelectedSubjects();

		/**
		 * Gets the subject type.
		 * 
		 * @return the subject type
		 */
		String getSubjectType();

		/**
		 * Gets the search term.
		 * 
		 * @return the search term
		 */
		String getSearchTerm();

		/**
		 * Gets the description.
		 * 
		 * @return the description
		 */
		String getDescription();

		/**
		 * Gets the name.
		 * 
		 * @return the name
		 */
		String getName();

		/**
		 * Sets the available subjects.
		 * 
		 * @param subjects
		 *            the new available subjects
		 */
		void setAvailableSubjects(List<String> subjects);

		/**
		 * Sets the subject types.
		 * 
		 * @param subjectTypes
		 *            the new subject types
		 */
		void setSubjectTypes(List<String> subjectTypes);

		/**
		 * Error.
		 * 
		 * @param msg
		 *            the msg
		 */
		void error(String msg);
		
		/**
		 * Info.
		 *
		 * @param msg the msg
		 */
		void info(String msg);
		
		/**
		 * Sets the sg calculator map.
		 * 
		 * @param sgCalculatorMap
		 *            the sg calculator map
		 */
		void setSgCalculatorMap(Map<String, String> sgCalculatorMap);
		
		/**
		 * Checks if is sg calculated.
		 * 
		 * @return the boolean
		 */
		Boolean isSgCalculated();
		
		/**
		 * Gets the selected subject group calculator name.
		 * 
		 * @return the selected subject group calculator name
		 */
		String getSelectedSubjectGroupCalculatorName();

	}

	/**
	 * Instantiates a new subject group create presenter.
	 * 
	 * @param eventBus
	 *            the event bus
	 * @param view
	 *            the view
	 * @param serviceMap
	 *            the service map
	 */
	public SubjectGroupCreatePresenter(HandlerManager eventBus,
			SubjectGroupCreateDisplay view,
			Map<SupportedService, PolicyAdminUIService> serviceMap) {
		this.eventBus = eventBus;
		this.view = view;
		this.view.setAssociatedId(getId());
		this.serviceMap = serviceMap;

		bind();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter#getId()
	 */
	public String getId() {
		return PRESENTER_ID;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter#getView()
	 */
	@Override
	protected Display getView() {
		return view;
	}

	/**
	 * Bind.
	 */
	public void bind() {
		this.view.getSearchButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// a search term has been entered
				SubjectGroupCreatePresenter.this.view.getSearchTerm();
				// get the Subject Type
				SubjectGroupCreatePresenter.this.view.getSubjectType();

				// do a lookup of all the matching Subjects
				service = (PolicyQueryService) serviceMap
						.get(SupportedService.POLICY_QUERY_SERVICE);
				SubjectKey key = new SubjectKey();
				final String searchTerm = SubjectGroupCreatePresenter.this.view
						.getSearchTerm();
				if (searchTerm != null && !searchTerm.trim().equals("")){
					key.setName(searchTerm);
				}
				key.setType(SubjectGroupCreatePresenter.this.view
						.getSubjectType());

				final SubjectQuery query = new SubjectQuery();
				query.setSubjectKeys(Collections.singletonList(key));

				if ("USER".equals(key.getType())) {
					service.findExternalSubjects(query,
							new AsyncCallback<FindExternalSubjectsResponse>() {

								public void onFailure(Throwable arg) {
									if (arg.getLocalizedMessage().contains(
											"500")) {
										view.error(PolicyAdminUIUtil.messages
												.serverError(PolicyAdminUIUtil.policyAdminConstants
														.genericErrorMessage()));
									} else {
										view.error(PolicyAdminUIUtil.messages
												.serverError(arg
														.getLocalizedMessage()));
									}
								}

								public void onSuccess(
										FindExternalSubjectsResponse response) {
									List<Subject> subjects = response
											.getSubjects();
									List<String> names = new ArrayList<String>();
									if (subjects != null && subjects.size() > 0) {
										for (Subject s : subjects)
											names.add(s.getName());
									}else{
										view.info(PolicyAdminUIUtil.policyAdminConstants
							                    .noItemFoundMessage());
									}
									view.setAvailableSubjects(names);

								}

							});

				} else {
					service.findSubjects(query,
							new AsyncCallback<FindSubjectsResponse>() {

								@Override
								public void onFailure(Throwable arg) {
									if (arg.getLocalizedMessage().contains(
											"500")) {
										view.error(PolicyAdminUIUtil.messages
												.serverError(PolicyAdminUIUtil.policyAdminConstants
														.genericErrorMessage()));
									} else {
										view.error(PolicyAdminUIUtil.messages
												.serverError(arg
														.getLocalizedMessage()));
									}
								}

								@Override
								public void onSuccess(
										FindSubjectsResponse response) {
									List<Subject> subjects = response
											.getSubjects();
									List<String> names = new ArrayList<String>();
									if (subjects != null && subjects.size() > 0) {
										for (Subject s : subjects){
											names.add(s.getName());
										}
									}else {
										final String newSubjectName = query.getSubjectKeys().get(0).getName();
										final String newSubjectType  = query.getSubjectKeys().get(0).getType();
										if(newSubjectName != null && !newSubjectName.endsWith("%")){ //create a new one
											if(Window.confirm(PolicyAdminUIUtil.policyAdminConstants.createInternalSubjects())){
												createInternalSubject(newSubjectName, newSubjectType);
												names.add(newSubjectName);
											}
										}else{ // not found and do not create it
											view.info(PolicyAdminUIUtil.policyAdminConstants
								                    .noItemFoundMessage());
										}
									}
								
									view.setAvailableSubjects(names);
								}

							});
				}
			}
		});

		this.view.getCreateButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Verify that the group has a name, and at least one subject
				String name = SubjectGroupCreatePresenter.this.view.getName();
				if (name == null || name.length() == 0) {
					SubjectGroupCreatePresenter.this.view
							.error(PolicyAdminUIUtil.policyAdminMessages
									.nameFieldMessage());
					return;
				}

				String description = SubjectGroupCreatePresenter.this.view
						.getDescription();

				List<String> subjectNames = SubjectGroupCreatePresenter.this.view
						.getSelectedSubjects();
				if (subjectNames == null || subjectNames.isEmpty()) {
					SubjectGroupCreatePresenter.this.view
							.error(PolicyAdminUIUtil.policyAdminMessages
									.minimumSubjectsMessage());
					return;
				}

				if ("USER".equals(view.getSubjectType())) {
					// external subjects todays are only USER types
					List<Subject> subjects = new ArrayList<Subject>();
					for (String sbName : subjectNames) {
						SubjectImpl subject = new SubjectImpl();
						subject.setType("USER");
						subject.setName(sbName);
						subjects.add(subject);
					}
					createExternalAsInternalSubject(subjects);
				}

				// user wants to create the Subject Group
				// 1. send the new Subject Group to the server
				// 2. when server acknowledges creation, use the history
				// mechanism to move back to the Subject Group Summary
				service = (PolicyQueryService) serviceMap
						.get(SupportedService.POLICY_QUERY_SERVICE);
				final SubjectGroupImpl group = new SubjectGroupImpl();
				group.setName(name);
				group.setDescription(description);

				group.setType(SubjectGroupCreatePresenter.this.view
						.getSubjectType());
				group.setSubjects(subjectNames);
				group.setGroupCalculator(SubjectGroupCreatePresenter.this.view.getSelectedSubjectGroupCalculatorName());

				/**
				 * This timer is needed due to GWT has only one thread, so
				 * Thread.sleep is not a valid option The purpose of sleeping
				 * time is wait until new external subject been created into
				 * turmeric db, in order to assign them as internal subjects
				 */
				Timer timer = new Timer() {
					public void run() {

						service.createSubjectGroups(
								Collections.singletonList((SubjectGroup) group),
								new AsyncCallback<CreateSubjectGroupsResponse>() {

									@Override
									public void onFailure(Throwable arg) {
										if (arg.getLocalizedMessage().contains(
												"500")) {
											view.error(PolicyAdminUIUtil.messages
													.serverError(PolicyAdminUIUtil.policyAdminConstants
															.genericErrorMessage()));
										} else {
											view.error(PolicyAdminUIUtil.messages.serverError(arg
													.getLocalizedMessage()));
										}
									}

									@Override
									public void onSuccess(
											CreateSubjectGroupsResponse arg0) {
										Map<String, String> prefill = new HashMap<String, String>();
										prefill.put(
												HistoryToken.SRCH_SUBJECT_GROUP_TYPE,
												SubjectGroupCreatePresenter.this.view
														.getSubjectType());
										prefill.put(
												HistoryToken.SRCH_SUBJECT_GROUP_NAME,
												SubjectGroupCreatePresenter.this.view
														.getName());
										HistoryToken token = makeToken(
												PolicyController.PRESENTER_ID,
												SubjectGroupSummaryPresenter.PRESENTER_ID,
												prefill);

										History.newItem(token.toString(), true);
									}

								});

						view.getCreateButton().setEnabled(true);

					}

				};
				if ("USER".equals(view.getSubjectType())) {
					view.getCreateButton().setEnabled(false);
					timer.schedule(3000);
				} else {
					timer.schedule(1);
				}
			}
		});

		this.view.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Just go back to the summary
				HistoryToken token = makeToken(PolicyController.PRESENTER_ID,
						SubjectGroupSummaryPresenter.PRESENTER_ID, null);
				History.newItem(token.toString(), true);
			}
		});
	}

	private void createExternalAsInternalSubject(final List<Subject> subjects) {

		List<SubjectKey> keys = new ArrayList<SubjectKey>();
		for (Subject subj : subjects) {
			SubjectKey key = new SubjectKey();
			key.setName(subj.getName());
			// today external subject supported are USER types
			key.setType("USER");
			keys.add(key);
		}

		final SubjectQuery query = new SubjectQuery();
		query.setSubjectKeys(keys);
		// looking for already created subjects
		service.findSubjects(query,
				new AsyncCallback<PolicyQueryService.FindSubjectsResponse>() {

					public void onSuccess(FindSubjectsResponse result) {
						subjects.removeAll(result.getSubjects());
						if (subjects.size() > 0) {
							service.createSubjects(
									subjects,
									new AsyncCallback<PolicyQueryService.CreateSubjectsResponse>() {

										public void onSuccess(
												final CreateSubjectsResponse result) {
											// do nothing, subjects has been
											// stored,
											// we can continue...
										}

										public void onFailure(
												final Throwable arg) {
											if (arg.getLocalizedMessage()
													.contains("500")) {
												view.error(PolicyAdminUIUtil.messages
														.serverError(PolicyAdminUIUtil.policyAdminConstants
																.genericErrorMessage()));
											} else {
												view.error(PolicyAdminUIUtil.messages.serverError(arg
														.getLocalizedMessage()));
											}
										}
									});
						}
					}

					public void onFailure(Throwable arg) {
						if (arg.getLocalizedMessage().contains("500")) {
							view.error(PolicyAdminUIUtil.messages
									.serverError(PolicyAdminUIUtil.policyAdminConstants
											.genericErrorMessage()));
						} else {
							view.error(PolicyAdminUIUtil.messages.serverError(arg
									.getLocalizedMessage()));
						}
					}

				});

	}
	
	
	private void createInternalSubject(final String subjectName, final String subjectType) {

		List<Subject> subjects = new ArrayList<Subject>();
		SubjectImpl subject = new SubjectImpl();
		subject.setName(subjectName);
		subject.setType(subjectType);
		subjects.add(subject);
		
		service.createSubjects(
				subjects,
				new AsyncCallback<PolicyQueryService.CreateSubjectsResponse>() {

					public void onSuccess(
							final CreateSubjectsResponse result) {
						// do nothing, subjects has been
						// stored,
						// we can continue...
					}

					public void onFailure(
							final Throwable arg) {
						if (arg.getLocalizedMessage()
								.contains("500")) {
							view.error(PolicyAdminUIUtil.messages
									.serverError(PolicyAdminUIUtil.policyAdminConstants
											.genericErrorMessage()));
						} else {
							view.error(PolicyAdminUIUtil.messages.serverError(arg
									.getLocalizedMessage()));
						}
					}
				});
			
	}


	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter#go(com.google.gwt.user.client.ui.HasWidgets, org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken)
	 */
	@Override
	public void go(HasWidgets container, final HistoryToken token) {
		fetchSubjectTypes();
		fetchSubjectGroupCalculators();
		this.view.setSubjectTypes(subjectTypes);
		container.clear();
		this.view.activate();
		container.add(this.view.asWidget());
	}

	private void fetchSubjectGroupCalculators() {
	    QueryCondition queryCondition = new QueryCondition();
	    queryCondition.setResolution(null);
	    QueryCondition.Query query = new Query("SUBJECT_TYPE", "SubjectGroupCalculator");
	    queryCondition.getQueries().add(query);
	    
	    service = (PolicyQueryService) serviceMap.get(SupportedService.POLICY_QUERY_SERVICE);
	    
	    service.getMetaData(queryCondition, new AsyncCallback<PolicyQueryService.GetMetaDataResponse>() {

            @Override
            public void onFailure(Throwable arg0) {
                if (arg0.getLocalizedMessage()
                                .contains("500")) {
                            view.error(PolicyAdminUIUtil.messages
                                    .serverError(PolicyAdminUIUtil.policyAdminConstants
                                            .genericErrorMessage()));
                        } else {
                            view.error(PolicyAdminUIUtil.messages.serverError(arg0
                                    .getLocalizedMessage()));
                        }
            }

            @Override
            public void onSuccess(GetMetaDataResponse arg0) {
                view.setSgCalculatorMap(arg0.getValues());
            }
        });
    }

    private void fetchSubjectTypes() {
		subjectTypes = org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectType
				.getValues();
	}
}
