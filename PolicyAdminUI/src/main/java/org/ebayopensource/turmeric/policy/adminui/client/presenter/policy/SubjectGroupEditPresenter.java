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

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindExternalSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetMetaDataResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateMode;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdateSubjectGroupsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.QueryCondition;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.QueryCondition.Query;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupQuery;
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
 * SubjectGroupEditPresenter.
 */
public class SubjectGroupEditPresenter extends AbstractGenericPresenter {

	/** The Constant PRESENTER_ID. */
	public static final String PRESENTER_ID = "SubjectGroupEdit";

	/** The event bus. */
	protected HandlerManager eventBus;

	/** The view. */
	protected SubjectGroupEditDisplay view;

	/** The original group. */
	protected SubjectGroup originalGroup;

	/** The service map. */
	protected Map<SupportedService, PolicyAdminUIService> serviceMap;
	private PolicyQueryService service;

	/**
	 * The Interface SubjectGroupEditDisplay.
	 */
	public interface SubjectGroupEditDisplay extends PolicyPageTemplateDisplay {

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
		 * Gets the search button.
		 * 
		 * @return the search button
		 */
		HasClickHandlers getSearchButton();

		/**
		 * Gets the cancel button.
		 * 
		 * @return the cancel button
		 */
		HasClickHandlers getCancelButton();

		/**
		 * Gets the apply button.
		 * 
		 * @return the apply button
		 */
		Button getApplyButton();

		/**
		 * Gets the selected subjects.
		 * 
		 * @return the selected subjects
		 */
		List<String> getSelectedSubjects();

		/**
		 * Sets the selected subjects.
		 * 
		 * @param subjects
		 *            the new selected subjects
		 */
		void setSelectedSubjects(List<String> subjects);

		/**
		 * Sets the available subjects.
		 * 
		 * @param subjects
		 *            the new available subjects
		 */
		void setAvailableSubjects(List<String> subjects);

		/**
		 * Gets the search term.
		 * 
		 * @return the search term
		 */
		String getSearchTerm();

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

		/**
		 * Sets the subject group calculator.
		 * 
		 * @param groupCalculator
		 *            the new subject group calculator
		 */
		void setSubjectGroupCalculator(String groupCalculator);

		/**
		 * Sets the sg calculator map.
		 * 
		 * @param values
		 *            the values
		 */
		void setSgCalculatorMap(Map<String, String> values);

		/**
		 * Sets the selected type.
		 * 
		 * @param type
		 *            the new selected type
		 */
		void setSelectedType(String type);

		/**
		 * Gets the group calculator.
		 * 
		 * @return the group calculator
		 */
		String getGroupCalculator();
	}

	/**
	 * Instantiates a new subject group edit presenter.
	 * 
	 * @param eventBus
	 *            the event bus
	 * @param view
	 *            the view
	 * @param serviceMap
	 *            the service map
	 */
	public SubjectGroupEditPresenter(HandlerManager eventBus,
			SubjectGroupEditDisplay view,
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
	protected Display getView() {
		return view;
	}

	/**
	 * Bind.
	 */
	public void bind() {
		this.view.getSearchButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// do a lookup of all the matching Subjects
				// Get the available subjects of that type
				final SubjectQuery subjectQuery = new SubjectQuery();
				SubjectKey key = new SubjectKey();
				String subName = view.getSearchTerm();
				if (subName != null && !subName.trim().equals(""))
					key.setName(subName);
				key.setType(originalGroup.getType());
				subjectQuery.setSubjectKeys(Collections.singletonList(key));

				if ("USER".equals(key.getType())) {
					service.findExternalSubjects(subjectQuery,
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
									if (subjects != null) {
										for (Subject s : subjects)
											names.add(s.getName());
									}

									if (originalGroup.getSubjects() != null) {
										names.removeAll(originalGroup
												.getSubjects());
									}
									view.setAvailableSubjects(names);

								}

							});

				} else {
					service.findSubjects(subjectQuery,
							new AsyncCallback<FindSubjectsResponse>() {

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
										FindSubjectsResponse response) {
									List<Subject> subjects = response
											.getSubjects();
									List<String> names = new ArrayList<String>();
									if (subjects != null) {
										if (subjects.size() > 0) {
											for (Subject s : subjects) {
												names.add(s.getName());
											}
										} else {
											final String newSubjectName = subjectQuery
													.getSubjectKeys().get(0)
													.getName();
											final String newSubjectType = subjectQuery
													.getSubjectKeys().get(0)
													.getType();
											if (!newSubjectName.endsWith("%")) {
												if (Window
														.confirm(PolicyAdminUIUtil.policyAdminConstants
																.createInternalSubjects())) {
													createInternalSubject(
															newSubjectName,
															newSubjectType);
													names.add(newSubjectName);
												}
											}
										}
									}
									if (originalGroup.getSubjects() != null) {
										names.removeAll(originalGroup
												.getSubjects());
									}
									view.setAvailableSubjects(names);
								}

							});
				}
			}
		});

		this.view.getApplyButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				List<String> subjects = SubjectGroupEditPresenter.this.view
						.getSelectedSubjects();
				if (subjects == null || subjects.isEmpty()) {
					SubjectGroupEditPresenter.this.view
							.error(PolicyAdminUIUtil.policyAdminMessages
									.minimumSubjectsMessage());
					return;
				}

				final SubjectGroupImpl editedGroup = new SubjectGroupImpl(
						originalGroup);
				editedGroup.setName(view.getName());
				editedGroup.setDescription(view.getDescription());
				editedGroup.setSubjects(view.getSelectedSubjects());
				editedGroup.setGroupCalculator(view.getGroupCalculator());

				if ("USER".equals(originalGroup.getType())) {
					// external subjects todays are only USER types
					List<Subject> externalSubjects = new ArrayList<Subject>();
					for (String sb : view.getSelectedSubjects()) {
						SubjectImpl subject = new SubjectImpl();
						subject.setType("USER");
						subject.setName(sb);
						externalSubjects.add(subject);
					}

					createInternalSubject(externalSubjects);
				}
				/**
				 * This timer is needed due to GWT has only one thread, so
				 * Thread.sleep is not a valid option The purpose of sleeping
				 * time is wait until new external subject been created into
				 * turmeric db, in order to assign them as internal subjects
				 */
				Timer timer = new Timer() {
					public void run() {

						service.updateSubjectGroups(
								Collections
										.singletonList((SubjectGroup) editedGroup),
								UpdateMode.REPLACE,
								new AsyncCallback<UpdateSubjectGroupsResponse>() {

									public void onFailure(final Throwable arg) {
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

									public void onSuccess(
											final UpdateSubjectGroupsResponse response) {
										// copy changes from the editedGroup
										// back to the group
										((SubjectGroupImpl) originalGroup)
												.setName(view.getName());
										((SubjectGroupImpl) originalGroup)
												.setDescription(view
														.getDescription());
										((SubjectGroupImpl) originalGroup)
												.setSubjects(view
														.getSelectedSubjects());
										view.clear();
										HistoryToken token = makeToken(
												PolicyController.PRESENTER_ID,
												SubjectGroupSummaryPresenter.PRESENTER_ID,
												null);
										token.addValue(
												HistoryToken.SRCH_SUBJECT_GROUP_TYPE,
												originalGroup.getType());
										token.addValue(
												HistoryToken.SRCH_SUBJECT_GROUP_NAME,
												originalGroup.getName());
										History.newItem(token.toString(), true);
									}
								});

						view.getApplyButton().setEnabled(true);

					}
				};
				if ("USER".equals(originalGroup.getType())) {
					view.getApplyButton().setEnabled(false);
					timer.schedule(3000);
				} else {
					timer.schedule(1);
				}
			}
		});

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

	private void createInternalSubject(final List<Subject> subjects) {

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
		service.findSubjects(query,
				new AsyncCallback<PolicyQueryService.FindSubjectsResponse>() {

					public void onSuccess(final FindSubjectsResponse result) {
						subjects.removeAll(result.getSubjects());
						if (subjects.size() > 0) {

							service.createSubjects(
									subjects,
									new AsyncCallback<PolicyQueryService.CreateSubjectsResponse>() {

										public void onSuccess(
												final CreateSubjectsResponse result) {
											// do nothing, subjects has been
											// stored, we can continue...
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

				});

	}

	private void createInternalSubject(final String subjectName,
			final String subjectType) {

		List<Subject> subjects = new ArrayList<Subject>();
		SubjectImpl subject = new SubjectImpl();
		subject.setName(subjectName);
		subject.setType(subjectType);
		subjects.add(subject);

		service.createSubjects(subjects,
				new AsyncCallback<PolicyQueryService.CreateSubjectsResponse>() {

					public void onSuccess(final CreateSubjectsResponse result) {
						// do nothing, subjects has been
						// stored,
						// we can continue...
					}

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

		// do a lookup of all the matching Subjects
		service = (PolicyQueryService) serviceMap
				.get(SupportedService.POLICY_QUERY_SERVICE);

		if (name != null) {
			container.clear();
			view.activate();
			container.add(view.asWidget());

			// Get the SubjectGroup being edited
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

						public void onFailure(Throwable arg) {
							if (arg.getLocalizedMessage().contains("500")) {
								view.error(PolicyAdminUIUtil.messages
										.serverError(PolicyAdminUIUtil.policyAdminConstants
												.genericErrorMessage()));
							} else {
								view.error(PolicyAdminUIUtil.messages
										.serverError(arg.getLocalizedMessage()));
							}
						}

						public void onSuccess(FindSubjectGroupsResponse response) {
							if (response.getGroups() != null
									&& response.getGroups().size() > 0) {
								// copy the SubjectGroup to make it editable
								originalGroup = new SubjectGroupImpl(response
										.getGroups().get(0));
								view.setName(originalGroup.getName());
								view.setDescription(originalGroup
										.getDescription());
								view.setSelectedSubjects(originalGroup
										.getSubjects());
								view.setSelectedType(originalGroup.getType());

								fetchSubjectGroupCalculators();

								// Get the available subjects of that type
								SubjectKey key = new SubjectKey();
								String subName = view.getSearchTerm();
								if (subName != null
										&& !subName.trim().equals("")) {
									key.setName(subName);
								}
								key.setType(type);
								SubjectQuery query = new SubjectQuery();
								query.setSubjectKeys(Collections
										.singletonList(key));

								service.findSubjects(
										query,
										new AsyncCallback<FindSubjectsResponse>() {

											public void onFailure(Throwable arg) {
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

											public void onSuccess(
													FindSubjectsResponse response) {
												List<Subject> subjects = response
														.getSubjects();
												List<String> names = new ArrayList<String>();
												if (subjects != null) {
													for (Subject s : subjects)
														names.add(s.getName());
												}
												// remove all the subjects that
												// are already selected
												if (originalGroup.getSubjects() != null) {
													names.removeAll(originalGroup
															.getSubjects());
												}
												view.setAvailableSubjects(names);
											}
										});

							}
						}
					});
		}
	}

	private void fetchSubjectGroupCalculators() {
		QueryCondition queryCondition = new QueryCondition();
		queryCondition.setResolution(null);
		QueryCondition.Query query = new Query("SUBJECT_TYPE",
				"SubjectGroupCalculator");
		queryCondition.getQueries().add(query);

		service = (PolicyQueryService) serviceMap
				.get(SupportedService.POLICY_QUERY_SERVICE);

		service.getMetaData(queryCondition,
				new AsyncCallback<PolicyQueryService.GetMetaDataResponse>() {

					@Override
					public void onFailure(Throwable arg0) {
						if (arg0.getLocalizedMessage().contains("500")) {
							view.error(PolicyAdminUIUtil.messages
									.serverError(PolicyAdminUIUtil.policyAdminConstants
											.genericErrorMessage()));
						} else {
							view.error(PolicyAdminUIUtil.messages
									.serverError(arg0.getLocalizedMessage()));
						}
					}

					@Override
					public void onSuccess(GetMetaDataResponse arg0) {
						view.setSgCalculatorMap(arg0.getValues());
						view.setSubjectGroupCalculator(originalGroup
								.getGroupCalculator());
					}
				});
	}
}
