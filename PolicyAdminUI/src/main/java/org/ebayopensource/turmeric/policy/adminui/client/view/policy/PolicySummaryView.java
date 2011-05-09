/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicySummaryPresenter.PolicySummaryDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.view.ErrorDialog;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.TurmericPager;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;

public class PolicySummaryView extends AbstractGenericView implements
		PolicySummaryDisplay {

	private final static UserAction SELECTED_ACTION = UserAction.POLICY_SUMMARY;

	private FlowPanel mainPanel;
	private ScrollPanel scrollPanel;
	private Display contentView;

	private static interface GetValue<C> {
	    C getValue(GenericPolicy policy);
	}
	
	/**
	 * PolicySearchWidget
	 * 
	 */
	private class PolicySearchWidget extends Composite {
		private FlowPanel mainPanel;
		private FlowPanel radioPanel;
		private RadioButton policyCriteriaButton;
		private RadioButton resourceCriteriaButton;
		private RadioButton subjectCriteriaButton;
		private RadioButton subjectGroupCriteriaButton;
		private Button searchButton;
		TextBox searchBox;
		private Label typeLabel;
		private Label nameLabel;
		private Label opLabel;
		private Label rlEffectLabel;

		private ListBox typeBox;
		private ListBox rsNameBox;
		private ListBox rlEffectBox;

		private ListBox opBox;
		private List<String> policyTypes = new ArrayList<String>();
		private List<String> rsNames = new ArrayList<String>();
		private List<String> opNames = new ArrayList<String>();

		
		public PolicySearchWidget() {
			mainPanel = new FlowPanel();

			final FlexTable flex = new FlexTable();
			// Search for a Policy by: SubjectType + Name or PolicyType + Name
			// TODO complete filter options
			radioPanel = new FlowPanel();

			policyCriteriaButton = new RadioButton("Criteria",
					PolicyAdminUIUtil.policyAdminConstants.policyCriteria());
			policyCriteriaButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					typeLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.policyType());
					nameLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.policyName());
					searchButton.setText(PolicyAdminUIUtil.policyAdminConstants
							.search());

					rlEffectLabel.setVisible(false);
					rlEffectBox.setVisible(false);
					flex.clearCell(1, 5);
					flex.setWidget(1, 5, searchBox);
					flex.setWidget(1, 8, rlEffectLabel);
					flex.setWidget(1, 9, rlEffectBox);

				}
			});

			resourceCriteriaButton = new RadioButton("Criteria",
					PolicyAdminUIUtil.policyAdminConstants.resourceCriteria());
			resourceCriteriaButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					typeLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.resourceType());
					nameLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.resourceName());

					searchButton.setText(PolicyAdminUIUtil.policyAdminConstants
							.search());
					flex.clearCell(1, 5);
					flex.setWidget(1, 5, rsNameBox);
					flex.setWidget(1, 8, opLabel);
					flex.setWidget(1, 9, opBox);
				}
			});

			subjectCriteriaButton = new RadioButton("Criteria",
					PolicyAdminUIUtil.policyAdminConstants.subjectCriteria());
			subjectCriteriaButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					typeLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.subjectType());
					nameLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.subjectName());
					searchButton.setText(PolicyAdminUIUtil.policyAdminConstants
							.search());
					rlEffectLabel.setVisible(false);
					rlEffectBox.setVisible(false);
					flex.clearCell(1, 5);
					flex.setWidget(1, 5, searchBox);
					flex.clearCell(1, 8);
					flex.clearCell(1, 9);
				}
			});

			subjectGroupCriteriaButton = new RadioButton("Criteria",
					PolicyAdminUIUtil.policyAdminConstants
							.subjectGroupCriteria());
			subjectGroupCriteriaButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					typeLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.subjectType());
					nameLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.subjectName());
					searchButton.setText(PolicyAdminUIUtil.policyAdminConstants
							.search());
					rlEffectLabel.setVisible(false);
					rlEffectBox.setVisible(false);
					flex.clearCell(1, 5);
					flex.setWidget(1, 5, searchBox);
					flex.clearCell(1, 8);
					flex.clearCell(1, 9);
				}
			});

			radioPanel.add(policyCriteriaButton);
			radioPanel.add(resourceCriteriaButton);
			radioPanel.add(subjectCriteriaButton);
			radioPanel.add(subjectGroupCriteriaButton);

			typeLabel = new Label(
					PolicyAdminUIUtil.policyAdminConstants.policyType());
			nameLabel = new Label(
					PolicyAdminUIUtil.policyAdminConstants.policyName());

			opLabel = new Label(
					PolicyAdminUIUtil.policyAdminConstants.operationName());

			rlEffectLabel = new Label(
					PolicyAdminUIUtil.policyAdminConstants.effect());

			typeBox = new ListBox(false);
			rsNameBox = new ListBox(false);
			rlEffectBox = new ListBox(false);
			opBox = new ListBox(false);

			searchButton = new Button(
					PolicyAdminUIUtil.policyAdminConstants.search());
			searchBox = new TextBox();

			flex.setWidget(0, 1, radioPanel);
			flex.getFlexCellFormatter().setColSpan(0, 1, 8);
			flex.setWidget(1, 0, typeLabel);
			flex.getCellFormatter().setWidth(1, 0, "100px");
			flex.setWidget(1, 1, typeBox);
			flex.setWidget(1, 4, nameLabel);
			flex.setWidget(1, 5, searchBox);
			flex.setWidget(1, 12, searchButton);

			mainPanel.add(flex);
			initWidget(mainPanel);
		}

		public void clear() {
			// select nothing
			policyCriteriaButton.setValue(false);
			resourceCriteriaButton.setValue(false);
			subjectCriteriaButton.setValue(false);
			subjectGroupCriteriaButton.setValue(false);
			opBox.clear();
			opBox.setEnabled(false);
			searchBox.setText("");
		}

		public HasClickHandlers getSubjectCriteriaButton() {
			return subjectCriteriaButton;
		}

		public HasClickHandlers getPolicyCriteriaButton() {
			return policyCriteriaButton;
		}

		public HasClickHandlers getResourceCriteriaButton() {
			return resourceCriteriaButton;
		}

		public HasClickHandlers getSubjectGroupCriteriaButton() {
			return subjectGroupCriteriaButton;
		}

		public HasClickHandlers getSearchButton() {
			return searchButton;
		}

		public void setSubjectCriteriaEnabled(boolean enabled) {
			subjectCriteriaButton.setValue(enabled);
		}

		public boolean isPolicyCriteriaEnabled() {
			return policyCriteriaButton.getValue() == true;
		}

		public boolean isSubjectCriteriaEnabled() {
			return subjectCriteriaButton.getValue() == true;
		}

		public boolean isSubjectGroupCriteriaEnabled() {
			return subjectGroupCriteriaButton.getValue() == true;
		}

		public boolean isResourceCriteriaEnabled() {
			return resourceCriteriaButton.getValue() == true;
		}

		public void setPolicyCriteriaEnabled(boolean enabled) {
			policyCriteriaButton.setValue(enabled);
		}

		public void setSubjectGroupCriteriaEnabled(boolean enabled) {
			subjectGroupCriteriaButton.setValue(enabled);
		}

		public void setResourceCriteriaEnabled(boolean enabled) {
			resourceCriteriaButton.setValue(enabled);
		}

		public void setRsNames(List<String> names) {
			rsNames = names;
		}

		public void setRLEffect(List<String> effects) {
			rlEffectBox.clear();
			for (String effect : effects) {
				rlEffectBox.addItem(effect);
			}
		}

		public void setOpNames(List<String> names) {
			opNames = names;
		}

		public String getSearchTerm() {
			return searchBox.getValue();
		}

		public List<String> getPolicyTypes() {
			return policyTypes;
		}

		public void setPolicyTypes(List<String> types) {
			policyTypes = types;
		}

		public String getSelectedType() {
			if (typeBox.getSelectedIndex() >= 0)
				return typeBox.getItemText(typeBox.getSelectedIndex());
			else
				return null;
		}

		public String getSelectedEffect() {
			if (rlEffectBox.getSelectedIndex() >= 0)
				return rlEffectBox.getItemText(rlEffectBox.getSelectedIndex());
			else
				return null;
		}

		public void setAvailableTypes(List<String> types) {
			// enable the selection of a subject type
			typeBox.clear();
			for (String s : types) {
				typeBox.addItem(s);
			}
			searchBox.setText("");
		}

		public void setSelectedType(String type) {
			if (type == null)
				return;

			int idx = -1;
			for (int i = 0; i < typeBox.getItemCount() && idx < 0; i++) {
				if (typeBox.getItemText(i).equals(type))
					idx = i;
			}
			if (idx >= 0)
				typeBox.setSelectedIndex(idx);
		}

		public String getSelectedRsName() {
			if (rsNameBox.getSelectedIndex() >= 0)
				return rsNameBox.getItemText(rsNameBox.getSelectedIndex());
			else
				return null;
		}

		public void setRLEffectBoxVisible(boolean b) {
			rlEffectBox.setVisible(b);
		}

		public void setRLEffectLabelVisible(boolean b) {
			rlEffectLabel.setVisible(b);
		}

		public ListBox getRsNameBox() {
			return rsNameBox;
		}

		public ListBox getAvailableTypeBox() {
			return typeBox;
		}

		public ListBox getOperationNameBox() {
			return opBox;
		}

		public void setAvailableRsNames() {
			rsNameBox.clear();
			for (String name : rsNames) {
				rsNameBox.addItem(name);
			}

		}

		public void setSelectedRsName(String name) {
			if (name == null)
				return;

			int idx = -1;
			for (int i = 0; i < rsNameBox.getItemCount() && idx < 0; i++) {
				if (rsNameBox.getItemText(i).equals(name))
					idx = i;
			}
			if (idx >= 0)
				rsNameBox.setSelectedIndex(idx);
		}

		public String getSelectedOp() {
			if (opBox.getSelectedIndex() >= 0)
				return opBox.getItemText(opBox.getSelectedIndex());
			else
				return null;
		}

		public void setAvailableOps() {
			opBox.clear();
			for (String name : opNames) {
				opBox.addItem(name);
			}
		}

		public void setSelectedOp(String op) {
			if (op == null)
				return;

			int idx = -1;
			for (int i = 0; i < opBox.getItemCount() && idx < 0; i++) {
				if (opBox.getItemText(i).equals(op))
					idx = i;
			}
			if (idx >= 0)
				opBox.setSelectedIndex(idx);
		}

		public void setSelectedSearchTerm(String term) {
			searchBox.setValue((term == null ? "" : term));
		}

	}

	private class ContentView extends AbstractGenericView implements Display {
		private FlowPanel mainPanel;

		private CellTable<GenericPolicy> cellTable;
		ProvidesKey<GenericPolicy> keyProvider;
		DisclosurePanel searchPanel;
		PolicySearchWidget searchWidget;
		
		/*
		 * GWT clone not supported yet. So just one action row implemented now
		 * http://code.google.com/p/google-web-toolkit/issues/detail?id=5068#c1
		 */
		ListBox actionCombo = new ListBox();
		
		PushButton actionButton = new PushButton(
				PolicyAdminUIUtil.constants.apply());
		
		final Map<GenericPolicy, UserAction> pendingActions = new HashMap<GenericPolicy, UserAction>();
		final Map<GenericPolicy, List<UserAction>> permittedActions = new HashMap<GenericPolicy, List<UserAction>>();
		ListDataProvider<GenericPolicy> dataProvider;

		/*
		 * columns
		 */
		Column<GenericPolicy, String> policyNameCol;
		TextColumn<GenericPolicy> policyTypeCol;
		TextColumn<GenericPolicy> policyStatusCol;
		TextColumn<GenericPolicy> policyCreatedByCol;
		TextColumn<GenericPolicy> policyModifiedByCol;
		Column<GenericPolicy, Date> policyModifiedDateCol;

		/**
		 * ActionPermissionCheckboxCell
		 * 
		 */
		public class ActionPermissionCheckboxCell extends
				CustomPermissionCheckboxCell<GenericPolicy, Boolean> {

			/**
			 * @param action
			 * @param pendingActions
			 * @param permittedActions
			 */
			public ActionPermissionCheckboxCell(UserAction action,
					Map<GenericPolicy, UserAction> pendingActions,
					Map<GenericPolicy, List<UserAction>> permittedActions) {
				super(action, pendingActions, permittedActions);
			}

			public void render(GenericPolicy value, Object key,
					SafeHtmlBuilder sb) {
				if (value == null)
					return;

				// if the user has permission for the action, then render
				// according to
				// the boolean value else render as disabled
				List<UserAction> permitted = permittedActions.get(value);
				UserAction pending = pendingActions.get(value);

				boolean isChecked = false;
				boolean isDisabled = false;

				if (permitted != null && permitted.contains(this.action)) {
					// The user is permitted to perform the enable/disable
					// action
					// Check what the enable/disable state of the policy is and
					// render appropriately
					boolean isEnabled = value.getEnabled();
					switch (action) {
						case POLICY_ENABLE: {
							// this is the enable checkbox, and the policy is
							// already enabled, render as disabled
							if (isEnabled) {
								isChecked = false;
								isDisabled = true;
	
							} else {
								// policy is not already enabled, decide whether to
								// render as checked or not
								// based on if there is a pending action to enable
								// it
								isDisabled = false;
								isChecked = (pending != null
										&& pending.equals(action) ? true : false);
							}
							break;
						}
						case POLICY_DISABLE: {
							// if this is the disable checkbox, and the policy is
							// already disabled, render as disabled
							if (!isEnabled) {
								isChecked = false;
								isDisabled = true;
							} else {
								// the policy is not already disabled, decide
								// whether to render it as checked or not
								// based on if there is a pending action to disable
								// it
								isDisabled = false;
								isChecked = (pending != null
										&& pending.equals(action) ? true : false);
							}
							break;
						}
						case POLICY_DELETE: {
							// this is the delete checkbox, and the policy is
							// enabled, it cannot be deleted
								isDisabled = false;
								isChecked = (pending != null
										&& pending.equals(action) ? true : false);
							break;
						}
						case POLICY_EXPORT: {
								
								isDisabled = false;
								isChecked = (pending != null
										&& pending.equals(action) ? true : false);
								break;
						}
					}
				} else {
					// the user is not permitted to perform the enable or
					// disable action, so render it always as
					// disabled.
					isDisabled = true;
					isChecked = false;
				}

				sb.appendHtmlConstant("<input type='checkbox' "
						+ (isDisabled ? "disabled=disabled" : "")
						+ (isChecked ? " checked " : "") + "></input>");
			}
		}

		public ContentView() {
			mainPanel = new FlowPanel();
			initWidget(mainPanel);
			initialize();
		}

		@SuppressWarnings("unchecked")
		public void setPolicies(List<GenericPolicy> policies) {
			cellTable.setRowCount(0);
			int i = 0;
			while (cellTable.getColumnCount() != 0) {
				cellTable.removeColumn(i);	
			};
			
			
			final List<GenericPolicy> list;
			if (policies == null) {
				list = Collections.emptyList();
			} else {
				list = policies;

				// date modified sorting
				Comparator timeAscComp = new Comparator<GenericPolicy>() {
					public int compare(GenericPolicy o1, GenericPolicy o2) {
						if (o1 == o2) {
							return 0;
						}
						// Compare the time columns.
						if (o1 != null) {
							return (o2 != null) ? o1
									.getLastModified()
									.compareTo(o2.getLastModified()) : 1;
						}
						return -1;
					}
				};

				Collections.sort(list, timeAscComp);
			}
			
			// Attach a column sort handler to the ListDataProvider to sort the
			// list.
			ListHandler<GenericPolicy> sortHandler = new ListHandler<GenericPolicy>( 
					list){
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void onColumnSort(ColumnSortEvent event) {

					if (policyNameCol.hashCode() == event.getColumn().hashCode()) {
						// name sorting
						Comparator nameAscComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the name columns.
								if (o1 != null) {
									return (o2 != null) ? o1.getName()
											.compareToIgnoreCase(o2.getName())
											: 1;
								}
								return -1;
							}
						};
						Comparator nameDescComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the name columns.
								if (o2 != null) {
									return (o1 != null) ? o2.getName()
											.compareToIgnoreCase(o1.getName())
											: 1;
								}
								return -1;
							}
						};

						if (event.isSortAscending()) {
							Collections.sort(list, nameDescComp);
						} else {
							Collections.sort(list, nameAscComp);
						}
						
					} else if (policyTypeCol.hashCode() == event.getColumn()
							.hashCode()) {
						// type sorting
						Comparator typeAscComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the type columns.
								if (o1 != null) {
									return (o2 != null) ? o1.getType()
											.compareToIgnoreCase(o2.getType())
											: 1;
								}
								return -1;
							}
						};
						Comparator typeDescComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the type columns.
								if (o2 != null) {
									return (o1 != null) ? o2.getType()
											.compareToIgnoreCase(o1.getType())
											: 1;
								}
								return -1;
							}
						};

						if (event.isSortAscending()) {
							Collections.sort(list, typeDescComp);
						} else {
							Collections.sort(list, typeAscComp);
						}
						
					} else if (policyStatusCol.hashCode() == event.getColumn()
							.hashCode()) {
						// status sorting
						Comparator statusAscComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the status columns.
								if (o1 != null) {
									return (o2 != null) ? String.valueOf(o1.getEnabled())
											.compareToIgnoreCase(String.valueOf(o2.getEnabled()))
											: 1;
								}
								return -1;
							}
						};
						Comparator statusDescComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the status columns.
								if (o2 != null) {
									return (o1 != null) ? String.valueOf(o2.getEnabled())
											.compareToIgnoreCase(String.valueOf(o1.getEnabled()))
											: 1;
								}
								return -1;
							}
						};

						if (event.isSortAscending()) {
							Collections.sort(list, statusDescComp);
						} else {
							Collections.sort(list, statusAscComp);
						}

					} else if (policyCreatedByCol.hashCode() == event
							.getColumn().hashCode()) {
						// creator sorting
						Comparator creatorAscComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the creator columns.
								if (o1 != null) {
									return (o2 != null) ? o1.getCreatedBy()
											.compareToIgnoreCase(
													o2.getCreatedBy()) : 1;
								}
								return -1;
							}
						};
						Comparator creatorDescComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the creator columns.
								if (o2 != null) {
									return (o1 != null) ? o2.getCreatedBy()
											.compareToIgnoreCase(
													o1.getCreatedBy()) : 1;
								}
								return -1;
							}
						};

						if (event.isSortAscending()) {
							Collections.sort(list, creatorDescComp);
						} else {
							Collections.sort(list, creatorAscComp);
						}

					} else if (policyModifiedByCol.hashCode() == event
							.getColumn().hashCode()) {
						// modifier sorting
						Comparator modifierAscComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the updater columns.
								if (o1 != null && o1.getLastModifiedBy() != null ) {
									return (o2 != null && o2.getLastModifiedBy() != null ) ? o1
											.getLastModifiedBy()
											.compareToIgnoreCase(
													o2.getLastModifiedBy()) : 1;
								}
								return -1;
							}
						};
						Comparator modifierDescComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the updater columns.
								if (o2 != null && o2.getLastModifiedBy() != null) {
									return (o1 != null && o1.getLastModifiedBy() != null ) ? o2
											.getLastModifiedBy()
											.compareToIgnoreCase(
													o1.getLastModifiedBy()) : 1;
								}
								return -1;
							}
						};

						if (event.isSortAscending()) {
							Collections.sort(list, modifierDescComp);
						} else {
							Collections.sort(list, modifierAscComp);
						}

					} else if (policyModifiedDateCol.hashCode() == event
							.getColumn().hashCode()) {
						
						
						// date modified sorting
						Comparator timeAscComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the time columns.
								if (o1 != null) {
									return (o2 != null) ? o1
											.getLastModified()
											.compareTo(o2.getLastModified()) : 1;
								}
								return -1;
							}
						};
						Comparator timeDescComp = new Comparator<GenericPolicy>() {
							public int compare(GenericPolicy o1, GenericPolicy o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the time columns.
								if (o2 != null) {
									return (o1 != null) ? o2
											.getLastModified()
											.compareTo(
													o1.getLastModified()) : 1;
								}
								return -1;
							}
						};

						if (event.isSortAscending()) {
							Collections.sort(list, timeDescComp);
						} else {
							Collections.sort(list, timeAscComp);
						}

					}
					
					
					
					
					
					
					
					
					dataProvider.setList(list);
					dataProvider.refresh();
					cellTable.redraw();
				}
			};
		
			dataProvider.setList(list);
			final SelectionModel<GenericPolicy> selectionModel = new MultiSelectionModel<GenericPolicy>(
					keyProvider);
			
			initTableColumns(selectionModel, sortHandler);	

		    // We know that the data is sorted alphabetically by default.
			cellTable.getColumnSortList().push(policyNameCol);
		    
			dataProvider.refresh();
			
			
		}

		public void setUserActions(GenericPolicy policy,
				List<UserAction> enabledActions) {
			if (policy == null)
				return;
			if (enabledActions == null)
				permittedActions.remove(policy);
			else
				permittedActions.put(policy, new ArrayList<UserAction>(
						enabledActions));
			if (cellTable != null)
				cellTable.redraw();
		}

		public Map<GenericPolicy, UserAction> getPendingActions() {
			return pendingActions;
		}

		public void activate() {
			// do nothing for now
		}

		@Override
		public void initialize() {
			mainPanel.clear();
			updateCombo();

			actionButton.setEnabled(false);
			// top part of contentPanel is a disclosure panel with a search
			// feature
			searchWidget = new PolicySearchWidget();
			searchPanel = new DisclosurePanel(
					PolicyAdminUIUtil.policyAdminConstants.search());
			searchPanel.setContent(searchWidget);

	
			// bottom part of panel is a table with search results
			Grid summaryGrid = new Grid(3, 1);
			summaryGrid.setStyleName("sggrid");

			/*
			 * Table section	    
			 */
			keyProvider = new ProvidesKey<GenericPolicy>() {
				public Object getKey(GenericPolicy policy) {
					return policy == null ? null : policy.getId();
				}
			};

			cellTable = new CellTable<GenericPolicy>(keyProvider);
			
			dataProvider = new ListDataProvider<GenericPolicy>();
			dataProvider.addDataDisplay(cellTable);

			
			/*
			 * ends table section
			 */

			actionCombo.addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent paramChangeEvent) {
					pendingActions.clear();
					setPolicies(dataProvider.getList());
					actionButton.setEnabled(pendingActions.size()>0);
				}
			});
			
			
			TurmericPager pagerAbove = new TurmericPager();
			pagerAbove.setDisplay(cellTable);
			TurmericPager pagerBelow = new TurmericPager();
			pagerBelow.setDisplay(cellTable);
			
						
			FlexTable actionTableAbove = new FlexTable();
			actionTableAbove.setWidget(0, 0, actionCombo);

			actionTableAbove.setWidget(0, 1, actionButton);
			actionTableAbove.setWidget(0, 2, pagerAbove);
			actionTableAbove.getCellFormatter().setWidth(0,2,"600px");
			actionTableAbove.getCellFormatter().setHorizontalAlignment(0,2,HasAlignment.ALIGN_RIGHT);

			summaryGrid.setWidget(0, 0, actionTableAbove);
			summaryGrid.setWidget(1, 0, cellTable);

			
			mainPanel.addStyleName("policy-summary");
			mainPanel.add(searchPanel);
			searchPanel.addStyleName("policy-content");
			summaryGrid.addStyleName("policy-content");
			mainPanel.add(summaryGrid);
		}
		
		

		private <C> Column<GenericPolicy, C> createCell(Cell<C> cell,final GetValue<C> getter,
				FieldUpdater<GenericPolicy, C> fieldUpdater) {
				        Column<GenericPolicy, C> column = new Column<GenericPolicy, C>(cell) {

				        @Override
				        public C getValue(GenericPolicy object) {
				            return getter.getValue(object);
				        }
				       
				        @Override
		                public void render(Cell.Context context, GenericPolicy object, SafeHtmlBuilder sb) 
		                {
		                    sb.appendHtmlConstant("<a href='javascript:void(0);'>");
		                    super.render(context, object, sb);
		                    sb.appendHtmlConstant("</a>");
		                } 
				    };
				    column.setFieldUpdater(fieldUpdater);
				    return column;
		}
		
		private void initTableColumns(final SelectionModel<GenericPolicy> selectionModel, 
				ListHandler<GenericPolicy> sortHandler ) {

           //checkbox column 
			final Column<GenericPolicy, GenericPolicy> checkColumn = new Column<GenericPolicy, GenericPolicy>(
                    new ActionPermissionCheckboxCell(UserAction.valueOf(
                    		this.actionCombo.getValue(this.actionCombo.getSelectedIndex())),
                    		pendingActions, permittedActions)) {
                public GenericPolicy getValue(GenericPolicy policy) {
                   return policy;
                }
            };
            checkColumn.setFieldUpdater(new FieldUpdater<GenericPolicy, GenericPolicy>() {
                public void update(int arg0, GenericPolicy arg1,GenericPolicy arg2) {
                	if (pendingActions.keySet().contains(arg1)) {
                        pendingActions.remove(arg1);
                    }else {
                        // Called when the user clicks on a checkbox.
                    	pendingActions.put(arg1, UserAction.valueOf(
                        		actionCombo.getValue(actionCombo.getSelectedIndex())));
                    }
                    
                    actionButton.setEnabled(pendingActions.size()>0);
                    cellTable.redraw();
                }
            });
            
			final CheckboxCell cb = new CheckboxCell();
			Header<Boolean> hdr = new Header<Boolean>(cb) {
				boolean selected = false;
				@Override
				public void onBrowserEvent(Context context, Element elem,
						NativeEvent event) {
					super.onBrowserEvent(context, elem, event);
					selected=!selected;
					final String action = actionCombo.getValue(actionCombo.getSelectedIndex());
					
					for (GenericPolicy visibleItem : cellTable.getVisibleItems()) {
						if (!selected && pendingActions.keySet().contains(visibleItem)) {
	                        pendingActions.remove(visibleItem);
	                        
	                        //to draw as enable user should has privileges, checkAll must be selected, 
	                        //and do not duplicate an entry en the pending actions set

	                    }else if (permittedActions.get(visibleItem).contains(UserAction.valueOf(action)) && 
	                    		selected && !pendingActions.keySet().contains(visibleItem) ) {
	                        //also, the action should be a valid action, ie: enabling just for disabled and viceversa
	                    	if(! (visibleItem.getEnabled() && UserAction.valueOf(action).compareTo(UserAction.POLICY_ENABLE)==0
	                    			|| !visibleItem.getEnabled() && UserAction.valueOf(action).compareTo(UserAction.POLICY_DISABLE)==0)){
	                    		pendingActions.put(visibleItem, UserAction.valueOf(
		                        		actionCombo.getValue(actionCombo.getSelectedIndex())));	
	                    	}
	                    	
	                    }
					}
					 actionButton.setEnabled(pendingActions.size()>0);
					 
					 cellTable.redraw();
				}
				
				@Override
				public Boolean getValue() {
					return false;
				}
			};
			
			
			cellTable.addColumn(checkColumn, hdr);

			
			// policyName.
			ClickableTextCell policyNameCellClickable = new ClickableTextCell();
			policyNameCol =  createCell(policyNameCellClickable, new GetValue<String>(){
				public String getValue(GenericPolicy policy) {
	                return policy.getName() ; 
	            }
	        }, new FieldUpdater<GenericPolicy, String>() {
	            public void update(int index, GenericPolicy policy, String value) {
	                pendingActions.clear();
					if(permittedActions.containsKey(policy) && permittedActions.get(policy).contains(UserAction.POLICY_EDIT)){
						pendingActions.put(policy, UserAction.POLICY_EDIT);	
					}else{
						pendingActions.put(policy, UserAction.POLICY_VIEW);
					}
						

	                actionButton.fireEvent(new ClickEvent(){});
	                pendingActions.clear();

	            }
	        });
			policyNameCol.setSortable(true);
			cellTable.addColumn(policyNameCol, PolicyAdminUIUtil.policyAdminConstants.policyName());
			cellTable.getColumnSortList().push(policyNameCol);
			
			
			// policy type
			policyTypeCol = new TextColumn<GenericPolicy>() {
				public String getValue(GenericPolicy policy) {
					return (policy == null ? null : policy.getType());
				}
			};
			policyTypeCol.setSortable(true);
			cellTable.addColumn(policyTypeCol,
					PolicyAdminUIUtil.policyAdminConstants.policyType());
			
			
			// policy status
			policyStatusCol = new TextColumn<GenericPolicy>() {
				public String getValue(GenericPolicy policy) {
					return (policy == null ? null
							: policy.getEnabled() ? "ENABLED" : "DISABLED");
				}
			};
			policyStatusCol.setSortable(true);
			cellTable.addColumn(policyStatusCol,
					PolicyAdminUIUtil.policyAdminConstants.status());
			
			
			// created by
			policyCreatedByCol = new TextColumn<GenericPolicy>() {
				public String getValue(GenericPolicy policy) {
					return (policy == null ? null : policy.getCreatedBy());
				}
			};
			policyCreatedByCol.setSortable(true);
			cellTable.addColumn(policyCreatedByCol,
					PolicyAdminUIUtil.policyAdminConstants.createdBy());

			
			
			// Last modified by
			policyModifiedByCol = new TextColumn<GenericPolicy>() {
				public String getValue(GenericPolicy policy) {
					return (policy == null ? null : policy.getLastModifiedBy());
				}
			};
			
			policyModifiedByCol.setSortable(true);
			cellTable.addColumn(policyModifiedByCol,
					PolicyAdminUIUtil.policyAdminConstants.lastModifiedBy());
			
			
			// Last modified date
			policyModifiedDateCol = new Column<GenericPolicy, Date>(
					new DateCell(PolicyAdminUIUtil.tzTimeFormat)) {
				public Date getValue(GenericPolicy policy) {
					return (policy == null ? null : policy.getLastModified());
				}
			};
			policyModifiedDateCol.setSortable(true);
			cellTable.addColumn(policyModifiedDateCol,
					PolicyAdminUIUtil.policyAdminConstants.lastModifiedTime());
			
			cellTable.addColumnSortHandler(sortHandler);
		}

		public HasClickHandlers getActionButtonAbove() {
			return actionButton;
		}

		
		private void updateCombo() {
			actionCombo.clear();
			actionCombo
					.addItem(PolicyAdminUIUtil.policyAdminConstants.enable(), UserAction.POLICY_ENABLE.toString());
			actionCombo.addItem(PolicyAdminUIUtil.policyAdminConstants
					.disable(), UserAction.POLICY_DISABLE.toString());
			actionCombo
					.addItem(PolicyAdminUIUtil.policyAdminConstants.delete(), UserAction.POLICY_DELETE.toString());
			actionCombo
					.addItem(PolicyAdminUIUtil.policyAdminConstants.export(), UserAction.POLICY_EXPORT.toString());
		}

	}

	public PolicySummaryView() {
		scrollPanel = new ScrollPanel();
		mainPanel = new FlowPanel();
		scrollPanel.add(mainPanel);
		initWidget(scrollPanel);

		initialize();
	}

	public void error(String msg) {
		ErrorDialog dialog = new ErrorDialog(true);
		dialog.setMessage(msg);
		dialog.getDialog().center();
		dialog.show();
	}

	@Override
	public void initialize() {
		mainPanel.clear();

		mainPanel.setWidth("100%");
		mainPanel.add(initContentView());
	}

	protected Widget initContentView() {
		ScrollPanel actionPanel = new ScrollPanel();
		contentView = new ContentView();
		actionPanel.add(contentView.asWidget());
		return actionPanel;
	}

	public void activate() {
		contentView.activate();
		this.setVisible(true);

	}

	public Display getContentView() {
		return contentView;
	}

	public void setPermittedActions(GenericPolicy policy,
			List<UserAction> permittedActions) {
		((ContentView) contentView).setUserActions(policy, permittedActions);
	}

	public void setPolicies(List<GenericPolicy> policies) {
		((ContentView) contentView).setPolicies(policies);
	}

	public HasClickHandlers getSearchButton() {
		return ((ContentView) contentView).searchWidget.getSearchButton();
	}

	public String getSearchTerm() {
		return ((ContentView) contentView).searchWidget.getSearchTerm();
	}

	public String getSelectedType() {
		return ((ContentView) contentView).searchWidget.getSelectedType();
	}

	public String getSelectedEffect() {
		return ((ContentView) contentView).searchWidget.getSelectedEffect();
	}

	public boolean isPolicyCriteriaEnabled() {
		return ((ContentView) contentView).searchWidget
				.isPolicyCriteriaEnabled();
	}

	public boolean isResourceCriteriaEnabled() {
		return ((ContentView) contentView).searchWidget
				.isResourceCriteriaEnabled();
	}

	public boolean isSubjectCriteriaEnabled() {
		return ((ContentView) contentView).searchWidget
				.isSubjectCriteriaEnabled();
	}

	public boolean isSubjectGroupCriteriaEnabled() {
		return ((ContentView) contentView).searchWidget
				.isSubjectGroupCriteriaEnabled();
	}

	public boolean isSearchCriteriaEnabled() {
		return ((ContentView) contentView).searchWidget
				.isSubjectCriteriaEnabled();
	}

	public void setAvailableTypes(List<String> types) {
		((ContentView) contentView).searchWidget.setAvailableTypes(types);
		((ContentView) contentView).searchWidget.getAvailableTypeBox()
				.setSelectedIndex(-1);
		if (!((ContentView) contentView).searchWidget
				.isResourceCriteriaEnabled()) {
			((ContentView) contentView).searchWidget.getRsNameBox()
					.setSelectedIndex(-1);
			((ContentView) contentView).searchWidget.getOperationNameBox()
					.setSelectedIndex(-1);
		}
	}

	public void setResourceNames() {
		((ContentView) contentView).searchWidget.setAvailableRsNames();
		((ContentView) contentView).searchWidget.getRsNameBox()
				.setSelectedIndex(-1);
		((ContentView) contentView).searchWidget.getOperationNameBox().clear();
		((ContentView) contentView).searchWidget.getOperationNameBox()
				.setSelectedIndex(-1);
		((ContentView) contentView).searchWidget.getOperationNameBox()
				.setEnabled(true);
	}

	public void setOperationNames() {
		((ContentView) contentView).searchWidget.setAvailableOps();
		((ContentView) contentView).searchWidget.getOperationNameBox()
				.setSelectedIndex(-1);
	}

	public HasClickHandlers getSubjectCriteriaButton() {
		return ((ContentView) contentView).searchWidget
				.getSubjectCriteriaButton();
	}

	public HasClickHandlers getPolicyCriteriaButton() {
		return ((ContentView) contentView).searchWidget
				.getPolicyCriteriaButton();
	}

	public HasClickHandlers getResourceCriteriaButton() {
		return ((ContentView) contentView).searchWidget
				.getResourceCriteriaButton();
	}

	public HasClickHandlers getSubjectGroupCriteriaButton() {
		return ((ContentView) contentView).searchWidget
				.getSubjectGroupCriteriaButton();
	}

	public void setSelectedSearchTerm(String name) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget.setSelectedSearchTerm(name);
	}

	public void setSelectedType(String type) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget.setSelectedType(type);
	}

	public void setPolicyCriteriaEnabled(boolean enabled) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget
				.setPolicyCriteriaEnabled(enabled);

	}

	public void setResourceCriteriaEnabled(boolean enabled) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget
				.setResourceCriteriaEnabled(enabled);

	}

	public void setSubjectCriteriaEnabled(boolean enabled) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget
				.setSubjectCriteriaEnabled(enabled);

	}

	public void setSubjectGroupCriteriaEnabled(boolean enabled) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget
				.setSubjectGroupCriteriaEnabled(enabled);

	}

	public void setSearchCriteriaEnabled(boolean enabled) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget
				.setSubjectCriteriaEnabled(enabled);
	}

	public List<String> getPolicyTypes() {
		return ((ContentView) contentView).searchWidget.getPolicyTypes();
	}

	public void setPolicyTypes(List<String> types) {
		((ContentView) contentView).searchWidget.setPolicyTypes(types);
	}

	public String getSelectedResource() {
		return ((ContentView) contentView).searchWidget.getSelectedRsName();
	}

	public String getSelectedOperation() {
		return ((ContentView) contentView).searchWidget.getSelectedOp();
	}

	public HasChangeHandlers getResourceNameBox() {
		return ((ContentView) contentView).searchWidget.getRsNameBox();
	}

	public HasChangeHandlers getAvailableTypesBox() {
		return ((ContentView) contentView).searchWidget.getAvailableTypeBox();
	}

	public void setRLEffectBoxVisible(boolean b) {
		((ContentView) contentView).searchWidget.setRLEffectBoxVisible(b);
	}

	public void setRLEffectLabelVisible(boolean b) {
		((ContentView) contentView).searchWidget.setRLEffectLabelVisible(b);
	}

	public void setRsNames(List<String> names) {
		((ContentView) contentView).searchWidget.setRsNames(names);
	}

	public void setOpNames(List<String> names) {
		((ContentView) contentView).searchWidget.setOpNames(names);
	}

	public void setEffect(List<String> effects) {
		((ContentView) contentView).searchWidget.setRLEffect(effects);

	}

	public HasClickHandlers getActionButtonAbove() {
		return ((ContentView) contentView).getActionButtonAbove();
	}

	
	public Map<GenericPolicy, UserAction> getPendingActions() {
		return ((ContentView) contentView).getPendingActions();
	}
}