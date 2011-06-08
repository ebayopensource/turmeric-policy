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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.SupportedService;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.AttributeValueImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ExtraField;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicyImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.OperationImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.CreateSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindExternalSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetResourcesResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.ResourceLevel;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicySubjectAssignment;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceType;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectAttributeDesignatorImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupQuery;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectMatchType;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectMatchTypeImpl;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectQuery;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectType;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.AbstractGenericPresenter;
import org.ebayopensource.turmeric.policy.adminui.client.util.PolicyKeysUtil;
import org.ebayopensource.turmeric.policy.adminui.client.util.SubjectUtil;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.SelectBoxesWidget;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.MultiSelectionModel;

/**
 * The Class PolicyCreatePresenter.
 */
public abstract class PolicyCreatePresenter extends AbstractGenericPresenter {

	/** The event bus. */
	protected HandlerManager eventBus;

	/** The view. */
	protected PolicyCreateDisplay view;

	/** The service map. */
	protected Map<SupportedService, PolicyAdminUIService> serviceMap;

	/** The permitted actions. */
	protected List<UserAction> permittedActions = new ArrayList<UserAction>();

	/** The available resources by type. */
	protected final List<Resource> availableResourcesByType = new ArrayList<Resource>();

	/** The all subjects. */
	protected List<Subject> allSubjects;

	/** The all subject groups. */
	protected List<SubjectGroup> allSubjectGroups;

	/** The all resources. */
	protected final List<Resource> allResources = new ArrayList<Resource>();

	/** The internal subjects. */
	protected List<Subject> internalSubjects;

	/** The assigned unique resources. */
	protected HashSet<String> assignedUniqueResources = new HashSet<String>();

	/** The subject types. */
	protected List<String> subjectTypes;

	/** The subject assignments. */
	protected List<PolicySubjectAssignment> subjectAssignments;

	/** The resource assignments. */
	protected List<Resource> resourceAssignments;

	/** The rules. */
	protected List<Rule> rules = new ArrayList<Rule>();;

	/** The edit resource assignment. */
	protected Resource editResourceAssignment;

	/** The edit subject assignment. */
	protected PolicySubjectAssignment editSubjectAssignment;

	/** The service. */
	protected PolicyQueryService service;

	/**
	 * Instantiates a new policy create presenter.
	 * 
	 * @param eventBus
	 *            the event bus
	 * @param view
	 *            the view
	 * @param serviceMap
	 *            the service map
	 */
	public PolicyCreatePresenter(final HandlerManager eventBus,
			final PolicyCreateDisplay view,
			final Map<SupportedService, PolicyAdminUIService> serviceMap) {
		this.eventBus = eventBus;
		this.view = view;
		this.view.setAssociatedId(getId());
		this.serviceMap = serviceMap;
		bindResourceSection();
		bindSubjectSection();
		bindSaveButton();
		bind();
	}

	/**
	 * Gets the resource levels.
	 * 
	 * @return the resource levels
	 */
	protected abstract List<String> getResourceLevels();

	/**
	 * Bind save button.
	 */
	protected abstract void bindSaveButton();

	private void bindSubjectSection() {

		// search for matching SubjectGroup
		this.view.addSbGroupSearchButtonClickHandler(new ClickHandler() {
					public void onClick(final ClickEvent event) {
						// do a lookup of all the matching SubjectGroups
						SubjectGroupKey key = new SubjectGroupKey();
						key.setType(view.getSubjectContentView()
								.getSubjectType());
						String name = view.getSubjectContentView()
								.getGroupSearchTerm();
						if (name != null && !name.trim().equals("")) {
							key.setName(name);
						}
						SubjectGroupQuery query = new SubjectGroupQuery();
						query.setGroupKeys(Collections.singletonList(key));

						service.findSubjectGroups(query,
								new AsyncCallback<FindSubjectGroupsResponse>() {

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
											final FindSubjectGroupsResponse response) {
										List<SubjectGroup> subjects = response
												.getGroups();
										List<String> names = new ArrayList<String>();
										if (subjects != null) {
											for (SubjectGroup s : subjects) {
												names.add(s.getName());
											}
										}
										view.getSubjectContentView()
												.setAvailableSubjectGroups(
														names);
										view.getSubjectContentView()
												.setAvailableExclusionSG(names);
									}

								});
					}
				});

		// search for matching Subject
		this.view.addSbSubjectSearchButtonClickHandler(new ClickHandler() {
					public void onClick(final ClickEvent event) {
						// do a lookup of all the matching Subjects
						SubjectKey key = new SubjectKey();
						String name = view.getSubjectContentView()
								.getSubjectSearchTerm();
						if (name != null && !name.trim().equals("")) {
							key.setName(name);
						}
						key.setType(view.getSubjectContentView()
								.getSubjectType());

						final SubjectQuery query = new SubjectQuery();
						query.setSubjectKeys(Collections.singletonList(key));

						if ("USER".equals(key.getType())) {
							service.findExternalSubjects(
									query,
									new AsyncCallback<FindExternalSubjectsResponse>() {

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

										public void onSuccess(
												final FindExternalSubjectsResponse response) {
											List<Subject> subjects = response
													.getSubjects();
											List<String> names = new ArrayList<String>();
											if (subjects != null) {
												for (Subject s : subjects) {
													names.add(s.getName());
												}
											}

											view.getSubjectContentView()
													.setAvailableSubjects(
															getSubjectNames(subjects));
											view.getSubjectContentView()
													.setAvailableExclusionSubjects(
															getSubjectNames(subjects));
										}

									});

						} else {
							service.findSubjects(query,
									new AsyncCallback<FindSubjectsResponse>() {

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

										public void onSuccess(
												final FindSubjectsResponse response) {
											List<Subject> subjects = response
													.getSubjects();
											List<String> names = new ArrayList<String>();

											if (subjects != null) {
												if (subjects.size() > 0) {
													for (Subject s : subjects) {
														names.add(s.getName());
													}
												} else {
													final String newSubjectName = query
															.getSubjectKeys()
															.get(0).getName();
													final String newSubjectType = query
															.getSubjectKeys()
															.get(0).getType();
													if (!newSubjectName
															.endsWith("%")) {
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
											view.getSubjectContentView()
													.setAvailableSubjects(names);

											view.getSubjectContentView()
													.setAvailableExclusionSubjects(
															names);

										}

									});

						}

					}
				});

		// edit an assignment
		this.view.addSbEditButtonClickHandler(new ClickHandler() {
					public void onClick(final ClickEvent event) {
						if (view.getSubjectContentView()
								.getSelectedSubjectAssignments().size() != 1) {
							return;
						}
						/**
						 * edit the existing assignment - set the available
						 * SubjectTypes as only the one in the assignment, and
						 * set the availables vs selected Subjects and
						 * SubjectGroups based on the assignment
						 */
						editSubjectAssignment = view.getSubjectContentView()
								.getSelectedSubjectAssignments().get(0);

						// remove the assignment from the list of assignments
						// while it is being edited

						subjectAssignments.remove(editSubjectAssignment);
						// update the display
						view.getSubjectContentView().setAssignments(
								subjectAssignments);
						// set the available subject types to just the type
						// being edited
						List<String> typeList = new ArrayList<String>();
						typeList.add(editSubjectAssignment.getSubjectType());
						view.getSubjectContentView().setAvailableSubjectTypes(
								typeList);
						// subject type attr values are: [0-9]+ when comes from
						// server, null when it has not been saved yet
						final boolean selectAllSubjects = (!editSubjectAssignment
								.getSubjects().isEmpty() && (editSubjectAssignment
								.getSubjects().get(0).getSubjectMatchTypes()
								.get(0).getAttributeValue().getValue() == null || editSubjectAssignment
								.getSubjects().get(0).getSubjectMatchTypes()
								.get(0).getAttributeValue().getValue()
								.endsWith("+")));

						view.getSubjectContentView().setSelectAllSubjects(
								selectAllSubjects);

						// get all the subjects of this type
						SubjectKey skey = new SubjectKey();
						skey.setType(editSubjectAssignment.getSubjectType());

						SubjectQuery query = new SubjectQuery();
						query.setSubjectKeys(Collections.singletonList(skey));

						service.findSubjects(query,
								new AsyncCallback<FindSubjectsResponse>() {

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
											final FindSubjectsResponse response) {
										List<Subject> subjects = response
												.getSubjects();
										if (!selectAllSubjects) {
											view.getSubjectContentView()
													.setSelectedSubjects(
															getSubjectNames(editSubjectAssignment
																	.getSubjects()));
										}
										subjects.removeAll(editSubjectAssignment
												.getSubjects());
										view.getSubjectContentView()
												.setAvailableSubjects(
														getSubjectNames(subjects));

										List<Subject> exclusionSubjects = response
												.getSubjects();
										view.getSubjectContentView()
												.setSelectedExclusionSubjects(
														getSubjectNames(editSubjectAssignment
																.getExclusionSubjects()));
										exclusionSubjects
												.removeAll(editSubjectAssignment
														.getExclusionSubjects());
										view.getSubjectContentView()
												.setAvailableExclusionSubjects(
														getSubjectNames(exclusionSubjects));

									}
								});

						// get the available SubjectGroups of the same type as
						// we've elected to edit
						SubjectGroupKey gkey = new SubjectGroupKey();
						gkey.setType(editSubjectAssignment.getSubjectType());
						SubjectGroupQuery gquery = new SubjectGroupQuery();
						gquery.setGroupKeys(Collections.singletonList(gkey));

						service.findSubjectGroups(gquery,
								new AsyncCallback<FindSubjectGroupsResponse>() {

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
											final FindSubjectGroupsResponse response) {
										List<SubjectGroup> subjectGroups = response
												.getGroups();

										view.getSubjectContentView()
												.setSelectedSubjectGroups(
														getGroupNames(editSubjectAssignment
																.getSubjectGroups()));
										// if there are subject groups already
										// assigned, take them out of the list
										// of available ones
										if (editSubjectAssignment
												.getSubjectGroups() != null) {

											for (SubjectGroup existing : editSubjectAssignment
													.getSubjectGroups()) {
												ListIterator<SubjectGroup> itor = subjectGroups
														.listIterator();
												while (itor.hasNext()) {
													SubjectGroup g = itor
															.next();
													if (existing.getId() != null
															&& existing
																	.getId()
																	.equals(g
																			.getId())) {
														itor.remove();
													} else if (existing
															.getName() != null
															&& existing
																	.getName()
																	.equals(g
																			.getName())) {
														itor.remove();
													}
												}
											}

										}
										view.getSubjectContentView()
												.setAvailableSubjectGroups(
														getGroupNames(subjectGroups));

										// ExclusionSG
										List<SubjectGroup> exclSG = response
												.getGroups();

										view.getSubjectContentView()
												.setSelectedExclusionSG(
														getGroupNames(editSubjectAssignment
																.getExclusionSubjectGroups()));
										// if there are exclusion subject groups
										// already
										// assigned, take them out of the list
										// of available ones
										if (editSubjectAssignment
												.getExclusionSubjectGroups() != null) {

											for (SubjectGroup existing : editSubjectAssignment
													.getExclusionSubjectGroups()) {
												ListIterator<SubjectGroup> itor = exclSG
														.listIterator();
												while (itor.hasNext()) {
													SubjectGroup g = itor
															.next();
													if (existing.getId() != null
															&& existing
																	.getId()
																	.equals(g
																			.getId())) {
														itor.remove();
													} else if (existing
															.getName() != null
															&& existing
																	.getName()
																	.equals(g
																			.getName())) {
														itor.remove();
													}
												}
											}

										}
										view.getSubjectContentView()
												.setAvailableExclusionSG(
														getGroupNames(exclSG));

										view.getSubjectContentView().show();
									}

								});
					}
				});

		// delete an assignment
		this.view.addSbDelButtonClickHandler(new ClickHandler() {
					public void onClick(final ClickEvent event) {

						if (view.getSubjectContentView()
								.getSelectedSubjectAssignments().size() == 0) {

							return;
						} else {
							if (Window
									.confirm(PolicyAdminUIUtil.policyAdminConstants
											.deleteSelected())) {
								for (PolicySubjectAssignment assignment : view
										.getSubjectContentView()
										.getSelectedSubjectAssignments()) {
									subjectAssignments.remove(assignment);
									subjectTypes.add(assignment
											.getSubjectType());
								}
								view.getSubjectContentView()
										.getSelectedSubjectAssignments()
										.clear();
								view.getSubjectContentView()
										.getSelectionModel().clear();
								view.getSubjectContentView().setAssignments(
										subjectAssignments);
								// add back in the subject type as being
								// available
								view.getSubjectContentView()
										.setAvailableSubjectTypes(subjectTypes);
							}
						}
					}
				});

		// assign a new group of Subject/SubjectGroup to the policy
		this.view.addSbAddButtonClickHandler(new ClickHandler() {
					public void onClick(final ClickEvent event) {

						if (completePopulatedForm()) {
							// Add a new set of subjects and groups for a given
							// SubjectType
							if (subjectAssignments == null) {
								subjectAssignments = new ArrayList<PolicySubjectAssignment>();
							}
							PolicySubjectAssignment assignment = null;
							if (editSubjectAssignment != null) {
								// we were editing an existing assignment
								assignment = editSubjectAssignment;
								editSubjectAssignment = null;
							} else {
								assignment = new PolicySubjectAssignment();
							}
							assignment.setSubjectType(view
									.getSubjectContentView().getSubjectType());

							// subject
							List<Subject> subjects = new ArrayList<Subject>();

							if (view.getSubjectContentView()
									.getSelectAllSubjects()) {
								Subject assignedSubject = getSubjectType(
										PolicyAdminUIUtil.policyAdminConstants
												.all(), assignment
												.getSubjectType(), false);

								subjects.add(assignedSubject);

							} else {
								for (String s : view.getSubjectContentView()
										.getSelectedSubjects()) {
									Subject assignedSubject = getSubject(s,
											assignment.getSubjectType(), false);
									// let's do a second loading, external
									// subjects
									// should stored in local now
									if (assignedSubject.getSubjectMatchTypes() == null) {
										assignedSubject = getSubject(s,
												assignment.getSubjectType(),
												true);
									}
									subjects.add(assignedSubject);
								}

							}
							// exclusionSubjects
							List<Subject> exclusionSubjects = new ArrayList<Subject>();
							for (String s : view.getSubjectContentView()
									.getSelectedExclusionSubjects()) {
								Subject assignedSubject = getSubject(s,
										assignment.getSubjectType(), true);

								// let's do a second loading, external subjects
								// should stored in local now
								if (assignedSubject.getSubjectMatchTypes() == null) {
									assignedSubject = getSubject(s,
											assignment.getSubjectType(), true);
								}
								exclusionSubjects.add(assignedSubject);
							}
							// groups
							List<SubjectGroup> groups = new ArrayList<SubjectGroup>();
							for (String s : view.getSubjectContentView()
									.getSelectedSubjectGroups()) {
								groups.add(getGroup(s,
										assignment.getSubjectType(), false));
							}
							// exclusion Subjectgroups
							List<SubjectGroup> exclSg = new ArrayList<SubjectGroup>();
							for (String s : view.getSubjectContentView()
									.getSelectedExclusionSG()) {
								exclSg.add(getGroup(s,
										assignment.getSubjectType(), true));
							}

							assignment.setSubjects(subjects);
							assignment.setExclusionSubjects(exclusionSubjects);
							assignment.setSubjectGroups(groups);
							assignment.setExclusionSubjectGroups(exclSg);
							subjectAssignments.add(assignment);

							view.getSubjectContentView().setAssignments(
									subjectAssignments);
							// take the SubjectType of the assignment out of the
							// list of available types
							subjectTypes.remove(assignment.getSubjectType());
							view.getSubjectContentView()
									.setAvailableSubjectTypes(subjectTypes);
							view.getSubjectContentView()
									.clearAssignmentWidget();
						}
					}
				});

		view.addSbCancelButtonClickHandler(new ClickHandler() {
					public void onClick(final ClickEvent event) {
						if (editSubjectAssignment != null) {
							subjectAssignments.add(editSubjectAssignment);
							editSubjectAssignment = null;
							view.getSubjectContentView().setAssignments(
									subjectAssignments);
							view.getSubjectContentView().hide();
							// fix the subjecttypes
							view.getSubjectContentView()
									.setAvailableSubjectTypes(subjectTypes);
							return;
						}

						view.getSubjectContentView().clearAssignmentWidget();
						view.getSubjectContentView().hide();
					}
				});

	}

	private boolean completePopulatedForm() {
		SubjectContentDisplay scView = view.getSubjectContentView();
		return (scView.getSelectAllSubjects()
				|| !scView.getSelectedSubjects().isEmpty()
				|| !scView.getSelectedExclusionSubjects().isEmpty()
				|| !scView.getSelectedSubjectGroups().isEmpty() || !scView
				.getSelectedExclusionSG().isEmpty());
	}

	private void display() {
		if (ResourceLevel.GLOBAL.name().equals(
				view.getResourceContentView().getResourceLevel())) {

			view.getResourceContentView().getResourceTypeLabel()
					.setVisible(false);
			view.getResourceContentView().getResourceTypeBox()
					.setVisible(false);
			view.getResourceContentView().getResourceNameLabel()
					.setVisible(false);
			view.getResourceContentView().getResourceNameBox()
					.setVisible(false);

			view.getResourceContentView().getSelectBoxesWidget()
					.setVisible(false);

		} else {

			if (ResourceLevel.RESOURCE.name().equals(
					view.getResourceContentView().getResourceLevel())) {
				view.getResourceContentView().getResourceTypeLabel()
						.setVisible(true);
				view.getResourceContentView().getResourceTypeBox()
						.setVisible(true);
				view.getResourceContentView().getResourceNameLabel()
						.setVisible(true);
				view.getResourceContentView().getResourceNameBox()
						.setVisible(true);
				view.getResourceContentView().getSelectBoxesWidget()
						.setVisible(false);
			} else if (ResourceLevel.OPERATION.name().equals(
					view.getResourceContentView().getResourceLevel())) {
				view.getResourceContentView().getResourceTypeLabel()
						.setVisible(true);
				view.getResourceContentView().getResourceTypeBox()
						.setVisible(true);
				view.getResourceContentView().getResourceNameLabel()
						.setVisible(true);
				view.getResourceContentView().getResourceNameBox()
						.setVisible(true);
				view.getResourceContentView().getSelectBoxesWidget()
						.setVisible(true);
			}

			view.getResourceContentView().setResourceTypes(getResourceTypes());
			view.getResourceContentView().getResourceTypeBox()
					.setSelectedIndex(-1);
		}

	}

	private void bindResourceSection() {

		this.view.addRsResourceLevelBoxChange(new ChangeHandler() {

					public void onChange(final ChangeEvent event) {
						display();

					}

				});

		// edit an assignment
		this.view.addRsEditButtonClick(new ClickHandler() {

					public void onClick(final ClickEvent event) {
						if (view.getResourceContentView().getSelections()
								.size() != 1) {
							return;
						}
						view.getResourceContentView().getResourceLevelLabel()
								.setVisible(false);
						view.getResourceContentView().getResourceLevelBox()
								.setVisible(false);

						view.getResourceContentView().getResourceTypeLabel()
								.setVisible(true);
						view.getResourceContentView().getResourceTypeBox()
								.setVisible(true);

						view.getResourceContentView().getResourceNameLabel()
								.setVisible(true);
						view.getResourceContentView().getResourceNameBox()
								.setVisible(true);

						view.getResourceContentView().getSelectBoxesWidget()
								.setVisible(true);

						// edit the existing assignment - set the available
						// SubjectTypes as only the one
						// in the assignment, and set the availables vs selected
						// Subjects and SubjectGroups
						// based on the assignment
						editResourceAssignment = view.getResourceContentView()
								.getSelections().get(0);
						// remove the assignment from the list of assignments
						// while it is being edited
						resourceAssignments.remove(editResourceAssignment);
						// update the display
						view.getResourceContentView().setAssignments(
								resourceAssignments);
						// set the available resource types to just the type
						// being edited
						List<String> typeList = new ArrayList<String>();
						typeList.add(editResourceAssignment.getResourceType());
						view.getResourceContentView()
								.setResourceTypes(typeList);

						// get all the resources of this type
						ResourceKey rsKey = new ResourceKey();
						rsKey.setType(editResourceAssignment.getResourceType());

						List<ResourceKey> rsKeys = new ArrayList<ResourceKey>();
						rsKeys.add(rsKey);
						service.getResources(rsKeys,
								new AsyncCallback<GetResourcesResponse>() {

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
											final GetResourcesResponse response) {
										List<Resource> resources = new ArrayList<Resource>(
												response.getResources());
										view.getResourceContentView()
												.setResourceNames(
														getResourceNames(resources));

										int index = getResourceIndex(resources,
												editResourceAssignment
														.getResourceName());
										view.getResourceContentView()
												.getResourceNameBox()
												.setSelectedIndex(index);

										view.getResourceContentView()
												.setSelectedOperations(
														getOperationNames(editResourceAssignment
																.getOpList()));

										List<Operation> operations = new ArrayList<Operation>(
												resources.get(index)
														.getOpList());
										operations
												.removeAll(editResourceAssignment
														.getOpList());
										view.getResourceContentView()
												.setAvailableOperations(
														getOperationNames(operations));
									}
								});
						view.getResourceContentView().show();

					}

				});

		view.addRsCancelResourceButtonClick(new ClickHandler() {
					public void onClick(final ClickEvent event) {
						if (editResourceAssignment != null) {
							resourceAssignments.add(editResourceAssignment);
							editResourceAssignment = null;
							view.getResourceContentView().setAssignments(
									resourceAssignments);
							view.getResourceContentView().hide();
							// // fix the resource types + name key
							// .setasiigmentUnique resource
							// AvailableSubjectTypes(subjectTypes);
							return;
						}

						view.getResourceContentView().clearAssignmentWidget();
						view.getResourceContentView().hide();
					}
				});

		// retrieve resource names based on selected type
		this.view.addRsResourceTypeBoxChange(new ChangeHandler() {

					public void onChange(final ChangeEvent event) {

						getRemainingResourceNames();
					}

				});

		// retrieve available operations based on selected name
		this.view.addRsResourceNameBoxClick(new ClickHandler() {

					public void onClick(final ClickEvent event) {
						getAvailableOperations();
					}

				});

		// assign a new rs to the policy
		this.view.addRsResourceButtonClick(new ClickHandler() {
					public void onClick(final ClickEvent event) {

						/*
						 * Resources could be assigned at 3 levels GLOBAL: means
						 * all resources and its operations apply RESOURCE: All
						 * operations of the selected resource are included
						 * OPERATION: The policy just apply to the specific
						 * operation of the selected resources
						 */
						if (resourceAssignments == null) {
							resourceAssignments = new ArrayList<Resource>();
						}
						final ResourceImpl assignment = new ResourceImpl();

						if (ResourceLevel.RESOURCE.name().equals(
								view.getResourceContentView()
										.getResourceLevel())) {
							assignment
									.setResourceName(view
											.getResourceContentView()
											.getResourceName());
							assignment
									.setResourceType(view
											.getResourceContentView()
											.getResourceType());

							List<Operation> operations = new ArrayList<Operation>();
							for (String s : view.getResourceContentView()
									.getAvailableOperations()) {
								OperationImpl op = new OperationImpl();
								op.setOperationName(s);
								operations.add(op);
							}
							assignment.setOpList(operations);
							assignedUniqueResources.add(assignment
									.getResourceType()
									+ assignment.getResourceName());
							resourceAssignments.add(assignment);

						} else if (ResourceLevel.GLOBAL.name().equals(
								view.getResourceContentView()
										.getResourceLevel())) {
							getAllResources();
							resourceAssignments.clear();
							resourceAssignments.addAll(allResources);
							assignedUniqueResources.clear();
							for (Resource rs : allResources) {
								assignedUniqueResources.add(rs
										.getResourceType()
										+ rs.getResourceName());
							}

						} else {

							assignment
									.setResourceName(view
											.getResourceContentView()
											.getResourceName());
							assignment
									.setResourceType(view
											.getResourceContentView()
											.getResourceType());

							List<Operation> operations = new ArrayList<Operation>();
							for (String s : view.getResourceContentView()
									.getSelectedOperations()) {
								OperationImpl op = new OperationImpl();
								op.setOperationName(s);
								operations.add(op);
							}
							assignment.setOpList(operations);

							assignedUniqueResources.add(assignment
									.getResourceType()
									+ assignment.getResourceName());

							resourceAssignments.add(assignment);
						}

						view.getResourceContentView().setAssignments(
								resourceAssignments);

						view.getResourceContentView().clearAssignmentWidget();
					}

				});

		// delete an assignment
		this.view.addRsDelButtonClick(new ClickHandler() {
					public void onClick(final ClickEvent event) {
						if (view.getResourceContentView().getSelections()
								.size() == 0) {
							return;
						} else {
							if (Window
									.confirm(PolicyAdminUIUtil.policyAdminConstants
											.deleteSelected())) {
								for (Resource selectedAssignment : view
										.getResourceContentView()
										.getSelections()) {
									resourceAssignments
											.remove(selectedAssignment);

									assignedUniqueResources
											.remove(selectedAssignment
													.getResourceType()
													+ selectedAssignment
															.getResourceName());

								}
								view.getResourceContentView().getSelections()
										.clear();
								view.getResourceContentView().setAssignments(
										resourceAssignments);
								view.getResourceContentView()
										.getSelectionModel().clear();
							}
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
	 * Interface definitions
	 */
	/**
	 * The Interface PolicyCreateDisplay.
	 */
	public interface PolicyCreateDisplay extends PolicyPageTemplateDisplay {
		
		/**
		 * Gets the policy name.
		 * 
		 * @return the policy name
		 */
		HasValue<String> getPolicyName();

		
		/**
		 * Adds the sb cancel button click handler.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addSbCancelButtonClickHandler(ClickHandler clickHandler);


		/**
		 * Adds the sb add button click handler.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addSbAddButtonClickHandler(ClickHandler clickHandler);


		/**
		 * Adds the sb del button click handler.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addSbDelButtonClickHandler(ClickHandler clickHandler);


		/**
		 * Adds the sb edit button click handler.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addSbEditButtonClickHandler(ClickHandler clickHandler);


		/**
		 * Adds the sb subject search button click handler.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addSbSubjectSearchButtonClickHandler(ClickHandler clickHandler);


		/**
		 * Adds the sb group search button click handler.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addSbGroupSearchButtonClickHandler(ClickHandler clickHandler);


		/**
		 * Gets the policy desc.
		 * 
		 * @return the policy desc
		 */
		HasValue<String> getPolicyDesc();

		/**
		 * Gets the policy enabled.
		 * 
		 * @return the policy enabled
		 */
		boolean getPolicyEnabled();

		/**
		 * Gets the save button.
		 * 
		 * @return the save button
		 */
		Button getSaveButton();

		/**
		 * Gets the cancel button.
		 * 
		 * @return the cancel button
		 */
		Button getCancelButton();

		/**
		 * Gets the resource content view.
		 * 
		 * @return the resource content view
		 */
		ResourcesContentDisplay getResourceContentView();

		/**
		 * Gets the subject content view.
		 * 
		 * @return the subject content view
		 */
		SubjectContentDisplay getSubjectContentView();

		/**
		 * Gets the extra field value.
		 * 
		 * @param order
		 *            the order
		 * @return the extra field value
		 */
		String getExtraFieldValue(int order);

		/**
		 * Sets the status listbox enabled.
		 * 
		 * @param enable
		 *            the new status listbox enabled
		 */
		void setStatusListboxEnabled(boolean enable);

		/**
		 * Sets the extra field value.
		 * 
		 * @param order
		 *            the order
		 * @param value
		 *            the value
		 * @param append
		 *            the append
		 */
		void setExtraFieldValue(int order, String value, boolean append);

		/**
		 * Sets the user actions.
		 * 
		 * @param permittedActions
		 *            the new user actions
		 */
		void setUserActions(List<UserAction> permittedActions);

		/**
		 * Clear.
		 */
		void clear();

		/**
		 * Error.
		 * 
		 * @param msg
		 *            the msg
		 */
		void error(String msg);

		/**
		 * Sets the policy desc.
		 * 
		 * @param policyDesc
		 *            the new policy desc
		 */
		void setPolicyDesc(String policyDesc);

		/**
		 * Sets the policy name.
		 * 
		 * @param policyName
		 *            the new policy name
		 */
		void setPolicyName(String policyName);

		/**
		 * Sets the policy status.
		 * 
		 * @param enabled
		 *            the new policy status
		 */
		void setPolicyStatus(boolean enabled);

		/**
		 * Sets the policy type.
		 * 
		 * @param policyType
		 *            the new policy type
		 */
		void setPolicyType(String policyType);

		/**
		 * Sets the extra field list.
		 * 
		 * @param extraFieldList
		 *            the new extra field list
		 */
		void setExtraFieldList(List<ExtraField> extraFieldList);

		/*
		 * Condition Builder methods
		 */
		/**
		 * Sets the condition builder visible.
		 * 
		 * @param visible
		 *            the new condition builder visible
		 */
		void setConditionBuilderVisible(boolean visible);

		/**
		 * Sets the exclusion lists visible.
		 * 
		 * @param visible
		 *            the new exclusion lists visible
		 */
		void setExclusionListsVisible(boolean visible);

		/**
		 * Gets the adds the condition button.
		 * 
		 * @return the adds the condition button
		 */
		HasClickHandlers getAddConditionButton();

		/**
		 * Gets the rs list box.
		 * 
		 * @return the rs list box
		 */
		HasChangeHandlers getRsListBox();

		/**
		 * Gets the rs name selected.
		 * 
		 * @return the rs name selected
		 */
		String getRsNameSelected();

		/**
		 * Gets the aritm sign selected.
		 * 
		 * @return the aritm sign selected
		 */
		String getAritmSignSelected();

		/**
		 * Gets the condition selected.
		 * 
		 * @return the condition selected
		 */
		String getConditionSelected();

		/**
		 * Gets the quantity box.
		 * 
		 * @return the quantity box
		 */
		String getQuantityBox();

		/**
		 * Gets the op name selected.
		 * 
		 * @return the op name selected
		 */
		String getOpNameSelected();

		/**
		 * Gets the logic op selected.
		 * 
		 * @return the logic op selected
		 */
		String getLogicOpSelected();

		/**
		 * Sets the rs names.
		 * 
		 * @param names
		 *            the new rs names
		 */
		void setRsNames(List<String> names);

		/**
		 * Sets the op names.
		 * 
		 * @param names
		 *            the new op names
		 */
		void setOpNames(List<String> names);

		/**
		 * Sets the condition names.
		 * 
		 * @param conditions
		 *            the new condition names
		 */
		void setConditionNames(List<String> conditions);

		/**
		 * Clear condition builder.
		 */
		void clearConditionBuilder();

		/**
		 * Valid all condition fields.
		 * 
		 * @return true, if successful
		 */
		boolean validAllConditionFields();


		/**
		 * Adds the rs del button click.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addRsDelButtonClick(ClickHandler clickHandler);


		/**
		 * Adds the rs resource button click.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addRsResourceButtonClick(ClickHandler clickHandler);


		/**
		 * Adds the rs resource name box click.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addRsResourceNameBoxClick(ClickHandler clickHandler);


		/**
		 * Adds the rs resource type box change.
		 * 
		 * @param changeHandler
		 *            the change handler
		 */
		void addRsResourceTypeBoxChange(ChangeHandler changeHandler);


		/**
		 * Adds the rs cancel resource button click.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addRsCancelResourceButtonClick(ClickHandler clickHandler);


		/**
		 * Adds the rs edit button click.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addRsEditButtonClick(ClickHandler clickHandler);


		/**
		 * Adds the rs resource level box change.
		 * 
		 * @param changeHandler
		 *            the change handler
		 */
		void addRsResourceLevelBoxChange(ChangeHandler changeHandler);


		/**
		 * Adds the save button click handler.
		 * 
		 * @param clickHandler
		 *            the click handler
		 */
		void addSaveButtonClickHandler(ClickHandler clickHandler);


		void addCancelButtonClickHandler(ClickHandler clickHandler);
	}

	/**
	 * The Interface ResourcesContentDisplay.
	 */
	public interface ResourcesContentDisplay extends Display {
		

		/**
		 * Sets the user actions.
		 * 
		 * @param permittedActions
		 *            the new user actions
		 */
		void setUserActions(List<UserAction> permittedActions);

		/**
		 * Sets the assignments.
		 * 
		 * @param assignments
		 *            the new assignments
		 */
		void setAssignments(List<Resource> assignments);

		/**
		 * Sets the available operations.
		 * 
		 * @param operations
		 *            the new available operations
		 */
		void setAvailableOperations(List<String> operations);

		/**
		 * Sets the selected operations.
		 * 
		 * @param operations
		 *            the new selected operations
		 */
		void setSelectedOperations(List<String> operations);

		/**
		 * Sets the resource level.
		 * 
		 * @param resourceLevels
		 *            the new resource level
		 */
		void setResourceLevel(List<String> resourceLevels);

		/**
		 * Sets the resource types.
		 * 
		 * @param resourceTypes
		 *            the new resource types
		 */
		void setResourceTypes(List<String> resourceTypes);

		/**
		 * Sets the resource names.
		 * 
		 * @param resourceNames
		 *            the new resource names
		 */
		void setResourceNames(List<String> resourceNames);

		/**
		 * Gets the selections.
		 * 
		 * @return the selections
		 */
		List<Resource> getSelections();

		/**
		 * Gets the select boxes widget.
		 * 
		 * @return the select boxes widget
		 */
		SelectBoxesWidget getSelectBoxesWidget();

		/**
		 * Gets the selected operations.
		 * 
		 * @return the selected operations
		 */
		List<String> getSelectedOperations();

		/**
		 * Gets the available operations.
		 * 
		 * @return the available operations
		 */
		List<String> getAvailableOperations();

		/**
		 * Gets the resource name.
		 * 
		 * @return the resource name
		 */
		String getResourceName();

		/**
		 * Gets the resource level.
		 * 
		 * @return the resource level
		 */
		String getResourceLevel();

		/**
		 * Gets the resource level label.
		 * 
		 * @return the resource level label
		 */
		Label getResourceLevelLabel();

		/**
		 * Gets the resource name label.
		 * 
		 * @return the resource name label
		 */
		Label getResourceNameLabel();

		/**
		 * Gets the resource type label.
		 * 
		 * @return the resource type label
		 */
		Label getResourceTypeLabel();

		/**
		 * Gets the resource type.
		 * 
		 * @return the resource type
		 */
		String getResourceType();

		/**
		 * Gets the resource type box.
		 * 
		 * @return the resource type box
		 */
		ListBox getResourceTypeBox();

		/**
		 * Gets the resource level box.
		 * 
		 * @return the resource level box
		 */
		ListBox getResourceLevelBox();

		/**
		 * Gets the resource name box.
		 * 
		 * @return the resource name box
		 */
		ListBox getResourceNameBox();

		/**
		 * Gets the selection model.
		 * 
		 * @return the selection model
		 */
		MultiSelectionModel<Resource> getSelectionModel();

		/**
		 * Gets the adds the resource button.
		 * 
		 * @return the adds the resource button
		 */
		HasClickHandlers getAddResourceButton();

		/**
		 * Gets the cancel resource button.
		 * 
		 * @return the cancel resource button
		 */
		HasClickHandlers getCancelResourceButton();

		/**
		 * Error.
		 * 
		 * @param msg
		 *            the msg
		 */
		void error(String msg);

		/**
		 * Clear assignment widget.
		 */
		void clearAssignmentWidget();

		/**
		 * Gets the edits the button.
		 * 
		 * @return the edits the button
		 */
		HasClickHandlers getEditButton();

		/**
		 * Gets the del button.
		 * 
		 * @return the del button
		 */
		HasClickHandlers getDelButton();

		/**
		 * Show.
		 */
		void show();

		/**
		 * Hide.
		 */
		void hide();
	}

	/**
	 * The Interface SubjectContentDisplay.
	 */
	public interface SubjectContentDisplay extends Display {
		
		/**
		 * Gets the subject type.
		 * 
		 * @return the subject type
		 */
		String getSubjectType();

		/**
		 * Gets the select all subjects.
		 * 
		 * @return the select all subjects
		 */
		boolean getSelectAllSubjects();

		/**
		 * Sets the select all subjects.
		 * 
		 * @param selected
		 *            the new select all subjects
		 */
		void setSelectAllSubjects(boolean selected);

		/**
		 * Gets the group search button.
		 * 
		 * @return the group search button
		 */
		HasClickHandlers getGroupSearchButton();

		/**
		 * Gets the subject search button.
		 * 
		 * @return the subject search button
		 */
		HasClickHandlers getSubjectSearchButton();

		/**
		 * Gets the adds the button.
		 * 
		 * @return the adds the button
		 */
		HasClickHandlers getAddButton();

		/**
		 * Gets the cancel button.
		 * 
		 * @return the cancel button
		 */
		HasClickHandlers getCancelButton();

		/**
		 * Gets the edits the button.
		 * 
		 * @return the edits the button
		 */
		HasClickHandlers getEditButton();

		/**
		 * Gets the del button.
		 * 
		 * @return the del button
		 */
		HasClickHandlers getDelButton();

		/**
		 * Gets the subject search term.
		 * 
		 * @return the subject search term
		 */
		String getSubjectSearchTerm();

		/**
		 * Gets the group search term.
		 * 
		 * @return the group search term
		 */
		String getGroupSearchTerm();

		/**
		 * Gets the selection model.
		 * 
		 * @return the selection model
		 */
		MultiSelectionModel<PolicySubjectAssignment> getSelectionModel();

		/**
		 * Gets the selected subject groups.
		 * 
		 * @return the selected subject groups
		 */
		List<String> getSelectedSubjectGroups();

		/**
		 * Gets the selected exclusion sg.
		 * 
		 * @return the selected exclusion sg
		 */
		List<String> getSelectedExclusionSG();

		/**
		 * Gets the selected subjects.
		 * 
		 * @return the selected subjects
		 */
		List<String> getSelectedSubjects();

		/**
		 * Gets the selected exclusion subjects.
		 * 
		 * @return the selected exclusion subjects
		 */
		List<String> getSelectedExclusionSubjects();

		/**
		 * Gets the selected subject assignments.
		 * 
		 * @return the selected subject assignments
		 */
		List<PolicySubjectAssignment> getSelectedSubjectAssignments();

		/**
		 * Gets the assignments.
		 * 
		 * @return the assignments
		 */
		List<PolicySubjectAssignment> getAssignments();

		/**
		 * Sets the available subject groups.
		 * 
		 * @param list
		 *            the new available subject groups
		 */
		void setAvailableSubjectGroups(List<String> list);

		/**
		 * Sets the available exclusion sg.
		 * 
		 * @param list
		 *            the new available exclusion sg
		 */
		void setAvailableExclusionSG(List<String> list);

		/**
		 * Sets the available subjects.
		 * 
		 * @param list
		 *            the new available subjects
		 */
		void setAvailableSubjects(List<String> list);

		/**
		 * Sets the available exclusion subjects.
		 * 
		 * @param list
		 *            the new available exclusion subjects
		 */
		void setAvailableExclusionSubjects(List<String> list);

		/**
		 * Sets the available subject types.
		 * 
		 * @param list
		 *            the new available subject types
		 */
		void setAvailableSubjectTypes(List<String> list);

		/**
		 * Sets the assignments.
		 * 
		 * @param assignments
		 *            the new assignments
		 */
		void setAssignments(List<PolicySubjectAssignment> assignments);

		/**
		 * Sets the selected subjects.
		 * 
		 * @param list
		 *            the new selected subjects
		 */
		void setSelectedSubjects(List<String> list);

		/**
		 * Sets the selected exclusion subjects.
		 * 
		 * @param list
		 *            the new selected exclusion subjects
		 */
		void setSelectedExclusionSubjects(List<String> list);

		/**
		 * Sets the selected subject groups.
		 * 
		 * @param list
		 *            the new selected subject groups
		 */
		void setSelectedSubjectGroups(List<String> list);

		/**
		 * Sets the selected exclusion sg.
		 * 
		 * @param list
		 *            the new selected exclusion sg
		 */
		void setSelectedExclusionSG(List<String> list);

		/**
		 * Sets the user actions.
		 * 
		 * @param permissions
		 *            the new user actions
		 */
		void setUserActions(List<UserAction> permissions);

		/**
		 * Clear assignment widget.
		 */
		void clearAssignmentWidget();

		/**
		 * Show.
		 */
		void show();

		/**
		 * Hide.
		 */
		void hide();
	}

	/**
	 * Clear lists.
	 */
	protected final void clearLists() {

		if (assignedUniqueResources != null) {
			assignedUniqueResources.clear();
		}
		if (availableResourcesByType != null) {
			availableResourcesByType.clear();
		}
		if (allSubjects != null) {
			allSubjects.clear();
		}
		if (allSubjectGroups != null) {
			allSubjectGroups.clear();
		}
		if (subjectAssignments != null) {
			subjectAssignments.clear();
		}
		if (rules != null) {
			rules.clear();
		}
		if (internalSubjects != null) {
			internalSubjects.clear();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.
	 * AbstractGenericPresenter#getView()
	 */
	@Override
	protected final PolicyCreateDisplay getView() {
		return view;
	}

	/**
	 * Bind.
	 */
	public void bind() {

		this.view.addCancelButtonClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				PolicyCreatePresenter.this.view.clear();

				if (subjectAssignments != null)
					subjectAssignments.clear();

				if (resourceAssignments != null) {
					resourceAssignments.clear();
				}

				if (rules != null) {
					rules.clear();
				}

				if (assignedUniqueResources != null) {
					assignedUniqueResources.clear();
				}

				if (availableResourcesByType != null) {
					availableResourcesByType.clear();
				}

				if (allResources != null) {
					allResources.clear();
				}

				if (allSubjects != null) {
					allSubjects.clear();
				}

				if (allSubjectGroups != null) {
					allSubjectGroups.clear();
				}

				if (allSubjectGroups != null) {
					editResourceAssignment = null;
				}

				HistoryToken token = makeToken(PolicyController.PRESENTER_ID,
						PolicySummaryPresenter.PRESENTER_ID, null);
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
	@Override
	public void go(final HasWidgets container, final HistoryToken token) {
		container.clear();
		// this.view.clear();

		this.view.activate();
		container.add(this.view.asWidget());

		service = (PolicyQueryService) serviceMap
				.get(SupportedService.POLICY_QUERY_SERVICE);

		permittedActions = Arrays.asList(UserAction.values());
		this.view.setUserActions(permittedActions);

		subjectTypes = new ArrayList(SubjectType.getValues());

		fetchResources();
		fetchSubjects();
		fetchSubjectGroups();
		getAllResources();

		this.view.getResourceContentView()
				.setResourceLevel(getResourceLevels());

		this.view.getResourceContentView().getResourceLevelBox()
				.setSelectedIndex(-1);

		this.view.getSubjectContentView()
				.setAvailableSubjectTypes(subjectTypes);

		this.view.setPolicyStatus(false);
	}

	private List<String> getResourceTypes() {
		return ResourceType.getValues();
	}

	private void getAvailableOperations() {
		PolicyCreatePresenter.this.view.getResourceContentView()
				.setAvailableOperations(Collections.EMPTY_LIST);
		String rsName = PolicyCreatePresenter.this.view
				.getResourceContentView().getResourceName();

		List<String> opNames = new ArrayList<String>();
		for (Resource rs : availableResourcesByType) {
			if (rsName.equals(rs.getResourceName())) {
				if (!rs.getOpList().isEmpty()) {
					for (Operation operation : rs.getOpList()) {
						opNames.add(operation.getOperationName());
					}

				}
				break;
			}
		}
		PolicyCreatePresenter.this.view.getResourceContentView()
				.setAvailableOperations(opNames);

	}

	private void getAllResources() {

		service.getResources(PolicyKeysUtil.getAllResourceKeyList(),
				new AsyncCallback<GetResourcesResponse>() {
					public void onSuccess(final GetResourcesResponse response) {
						allResources.clear();
						allResources.addAll(response.getResources());
					}

					public void onFailure(final Throwable arg) {
						if (arg.getLocalizedMessage().contains("500")) {
							view.getResourceContentView()
									.error(PolicyAdminUIUtil.messages
											.serverError(PolicyAdminUIUtil.policyAdminConstants
													.genericErrorMessage()));
						} else {
							view.getResourceContentView().error(
									PolicyAdminUIUtil.messages.serverError(arg
											.getLocalizedMessage()));
						}
					}
				});

	}

	private void getRemainingResourceNames() {
		ResourceKey key = new ResourceKey();
		key.setType(PolicyCreatePresenter.this.view.getResourceContentView()
				.getResourceType());

		service.getResources(Collections.singletonList(key),
				new AsyncCallback<GetResourcesResponse>() {
					public void onSuccess(final GetResourcesResponse response) {
						availableResourcesByType.clear();
						availableResourcesByType.addAll(response.getResources());
						List<String> resourceNames = new ArrayList<String>();

						for (Resource rs : availableResourcesByType) {
							if (!assignedUniqueResources.contains(rs
									.getResourceType() + rs.getResourceName())) {
								resourceNames.add(rs.getResourceName());
							}
						}
						PolicyCreatePresenter.this.view
								.getResourceContentView().setResourceNames(
										resourceNames);
						PolicyCreatePresenter.this.view
								.getResourceContentView().getResourceNameBox()
								.setSelectedIndex(-1);
					}

					public void onFailure(final Throwable arg) {
						if (arg.getLocalizedMessage().contains("500")) {
							view.getResourceContentView()
									.error(PolicyAdminUIUtil.messages
											.serverError(PolicyAdminUIUtil.policyAdminConstants
													.genericErrorMessage()));
						} else {
							view.getResourceContentView().error(
									PolicyAdminUIUtil.messages.serverError(arg
											.getLocalizedMessage()));
						}
					}
				});
	}

	private List<String> getResourceNames(final List<Resource> resources) {

		List<String> resourceNames = new ArrayList<String>();

		for (Resource rs : resources) {
			resourceNames.add(rs.getResourceName());
		}
		return resourceNames;
	}

	/**
	 * Fetch a subject entity from DB based on the subject key parameter.
	 * 
	 * @see getSubject() method
	 */
	protected final void fetchSubjects() {

		SubjectQuery query = new SubjectQuery();

		query.setSubjectKeys(PolicyKeysUtil.getAllSubjectKeyList());
		service.findSubjects(query,
				new AsyncCallback<PolicyQueryService.FindSubjectsResponse>() {

					public void onSuccess(final FindSubjectsResponse result) {
						allSubjects = new ArrayList<Subject>(result
								.getSubjects());
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

	/**
	 * Fetch a Subject Group entity from DB based on the subject Group key
	 * parameter.
	 */
	protected final void fetchSubjectGroups() {

		SubjectGroupQuery query = new SubjectGroupQuery();

		query.setGroupKeys(PolicyKeysUtil.getAllSubjectGroupKeyList());
		service.findSubjectGroups(
				query,
				new AsyncCallback<PolicyQueryService.FindSubjectGroupsResponse>() {

					public void onSuccess(final FindSubjectGroupsResponse result) {
						allSubjectGroups = new ArrayList<SubjectGroup>(result
								.getGroups());
					}

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
				});
	}

	/**
	 * Creates a non persisted subject type bean based on these parameters.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param isSubjectType
	 *            the is subject type
	 * @return the subject type
	 */
	private Subject getSubjectType(final String name, final String type,
			final boolean isSubjectType) {
		final SubjectImpl s = new SubjectImpl();
		s.setType(type);

		SubjectMatchTypeImpl smt = new SubjectMatchTypeImpl();
		AttributeValueImpl attr = new AttributeValueImpl();

		SubjectAttributeDesignatorImpl designator = new SubjectAttributeDesignatorImpl();
		smt.setMatchId("urn:oasis:names:tc:xacml:1.0:function:string-regexp-match");
		attr.setDataType("http://www.w3.org/2001/XMLSchema#string");

		designator
				.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
		designator.setDataType("http://www.w3.org/2001/XMLSchema#string");
		smt.setAttributeValue(attr);
		smt.setSubjectAttributeDesignator(designator);

		s.setSubjectMatch(new ArrayList<SubjectMatchType>(Collections
				.singletonList(smt)));

		return s;

	}

	/**
	 * Creates a non persisted subject bean based on these parameters. Subjects
	 * could be from inclusion lists or from exclusion list. Also if the subject
	 * is a USER type, it could not exist, that is an external subject, so it is
	 * firstly created in DB
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param exclusionList
	 *            the exclusion list
	 * @return the subject
	 * @see createExternalAsInternalSubject() method
	 */
	public Subject getSubject(final String name, final String type,
			final boolean exclusionList) {
		final SubjectImpl s = new SubjectImpl();
		for (Subject subject : allSubjects) {
			if (subject.getType().equals(type)
					&& subject.getName().equals(name)) {
				s.setName(subject.getName());
				s.setType(subject.getType());
				SubjectMatchTypeImpl smt = new SubjectMatchTypeImpl();

				AttributeValueImpl attr = new AttributeValueImpl();
				SubjectAttributeDesignatorImpl designator = new SubjectAttributeDesignatorImpl();
				if (!exclusionList) {
					smt.setMatchId("urn:oasis:names:tc:xacml:1.0:function:integer-equal");
					attr.setDataType("http://www.w3.org/2001/XMLSchema#integer");
					attr.setValue(SubjectUtil.getSubjectId(subject).toString());
					designator
							.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
					designator
							.setDataType("http://www.w3.org/2001/XMLSchema#integer");

				} else {
					smt.setMatchId("urn:oasis:names:tc:xacml:1.0:function:string-regexp-match");
					attr.setDataType("http://www.w3.org/2001/XMLSchema#string");
					attr.setValue("(?!"
							+ SubjectUtil.getSubjectId(subject).toString()
							+ ")");
					designator
							.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
					designator
							.setDataType("http://www.w3.org/2001/XMLSchema#string");

				}
				smt.setAttributeValue(attr);
				smt.setSubjectAttributeDesignator(designator);

				s.setSubjectMatch(new ArrayList<SubjectMatchType>(Collections
						.singletonList(smt)));
				break;
			}

		}

		if (s.getSubjectMatchTypes() == null) {
			s.setType(type);
			s.setName(name);
			if ("USER".equals(type)) {
				createExternalAsInternalSubject(new ArrayList<Subject>(
						Collections.singletonList(s)));
			}
			fetchSubjects();
			fetchSubjectGroups();
		}

		return s;
	}

	/**
	 * Gets the group.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param exclusionList
	 *            the exclusion list
	 * @return the group
	 */
	public SubjectGroup getGroup(final String name, final String type,
			final boolean exclusionList) {
		SubjectGroupImpl group = new SubjectGroupImpl();
		for (SubjectGroup subjectGroup : allSubjectGroups) {
			if (subjectGroup.getType().equals(type)
					&& subjectGroup.getName().equals(name)) {
				group.setType(type);
				group.setName(name);
				SubjectMatchTypeImpl smt = new SubjectMatchTypeImpl();
				AttributeValueImpl attr = new AttributeValueImpl();
				SubjectAttributeDesignatorImpl designator = new SubjectAttributeDesignatorImpl();

				if (!exclusionList) {
					smt.setMatchId("urn:oasis:names:tc:xacml:1.0:function:integer-equal");
					attr.setDataType("http://www.w3.org/2001/XMLSchema#integer");
					attr.setValue(SubjectUtil.getSubjectGroupId(subjectGroup)
							.toString());
					designator
							.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
					designator
							.setDataType("http://www.w3.org/2001/XMLSchema#integer");

				} else {
					smt.setMatchId("urn:oasis:names:tc:xacml:1.0:function:string-regexp-match");
					attr.setDataType("http://www.w3.org/2001/XMLSchema#string");
					attr.setValue("(?!"
							+ SubjectUtil.getSubjectGroupId(subjectGroup)
									.toString() + ")");
					designator
							.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
					designator
							.setDataType("http://www.w3.org/2001/XMLSchema#string");

				}
				smt.setAttributeValue(attr);
				smt.setSubjectAttributeDesignator(designator);

				group.setSubjectMatch(new ArrayList<SubjectMatchType>(
						Collections.singletonList(smt)));
				break;
			}
		}
		return group;
	}

	/**
	 * Gets the policy.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param description
	 *            the description
	 * @param resources
	 *            the resources
	 * @param subjectAssignments
	 *            the subject assignments
	 * @param rules
	 *            the rules
	 * @return the policy
	 */
	protected final GenericPolicy getPolicy(final String name,
			final String type, final String description,
			final List<Resource> resources,
			final List<PolicySubjectAssignment> subjectAssignments,
			final List<Rule> rules) {
		GenericPolicyImpl p = new GenericPolicyImpl();
		p.setName(name);
		p.setType(type);
		p.setDescription(description);

		if (rules != null) {
			p.setRules(rules);
		}

		if (resources != null) {
			p.setResources(new ArrayList<Resource>(resources));

			for (Resource rs : resources) {
				assignedUniqueResources.add(rs.getResourceType()
						+ rs.getResourceName());
			}
		}

		if (subjectAssignments != null) {
			List<Subject> subjects = new ArrayList<Subject>();
			List<Subject> exclusionSubjects = new ArrayList<Subject>();
			List<SubjectGroup> groups = new ArrayList<SubjectGroup>();
			List<SubjectGroup> exclSGroups = new ArrayList<SubjectGroup>();

			for (PolicySubjectAssignment a : subjectAssignments) {
				if (a.getSubjects() != null) {
					subjects.addAll(a.getSubjects());
				}

				if (a.getExclusionSubjects() != null) {
					exclusionSubjects.addAll(a.getExclusionSubjects());
				}

				if (a.getSubjectGroups() != null) {
					groups.addAll(a.getSubjectGroups());
				}

				if (a.getExclusionSubjectGroups() != null) {
					exclSGroups.addAll(a.getExclusionSubjectGroups());
				}
			}
			p.setSubjects(subjects);
			p.setExclusionSubjects(exclusionSubjects);
			p.setSubjectGroups(groups);
			p.setExclusionSG(exclSGroups);
		}
		return p;
	}

	/**
	 * Creates the external as internal subject.
	 * 
	 * @param subjects
	 *            the subjects
	 */
	protected final void createExternalAsInternalSubject(
			final List<Subject> subjects) {
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

	/**
	 * Fetch resources.
	 */
	protected final void fetchResources() {
		service.getResources(null, new AsyncCallback<GetResourcesResponse>() {
			public void onSuccess(GetResourcesResponse response) {
				availableResourcesByType.addAll(response.getResources());

			}

			public void onFailure(final Throwable arg) {
				if (arg.getLocalizedMessage().contains("500")) {
					view.getResourceContentView()
							.error(PolicyAdminUIUtil.messages
									.serverError(PolicyAdminUIUtil.policyAdminConstants
											.genericErrorMessage()));
				} else {
					view.getResourceContentView().error(
							PolicyAdminUIUtil.messages.serverError(arg
									.getLocalizedMessage()));
				}
			}
		});

	}

	/**
	 * Obtain Subject Names from subject objects.
	 */
	private List<String> getSubjectNames(final List<Subject> subjects) {
		List<String> names = new ArrayList<String>();
		if (subjects != null) {
			for (Subject s : subjects) {
				names.add(s.getName());
			}
		}
		return names;
	}

	/**
	 * Obtain Subject Group Names from SG objects.
	 */
	private List<String> getGroupNames(final List<SubjectGroup> groups) {
		List<String> names = new ArrayList<String>();
		if (groups != null) {
			for (SubjectGroup s : groups) {
				names.add(s.getName());
			}
		}
		return names;
	}

	/**
	 * Obtain Operation Names from Operations objects.
	 */
	private List<String> getOperationNames(final List<Operation> operations) {
		List<String> names = new ArrayList<String>();
		if (operations != null) {
			for (Operation op : operations) {
				names.add(op.getOperationName());
			}
		}
		return names;
	}

	/**
	 * Obtain index of getResource(name).
	 */
	private int getResourceIndex(final List<Resource> resources,
			final String rsName) {
		int index = -1;
		for (int i = 0; i < resources.size(); i++) {
			if (resources.get(i).getResourceName().equals(rsName)) {
				index = i;
				break;
			}
		}
		return index;
	}

}
