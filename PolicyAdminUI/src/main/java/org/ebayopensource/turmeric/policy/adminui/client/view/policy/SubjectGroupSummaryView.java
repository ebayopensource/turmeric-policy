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
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.view.ErrorDialog;
import org.ebayopensource.turmeric.policy.adminui.client.view.InfoDialog;
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
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
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

/**
 * The Class SubjectGroupSummaryView.
 */
public class SubjectGroupSummaryView extends AbstractGenericView implements
		SubjectGroupSummaryDisplay {
	private ScrollPanel scrollPanel;
	private FlowPanel mainPanel;
	private Display contentView;

	private static interface GetValue<C> {
		C getValue(SubjectGroup sg);
	}

	/**
	 * SubjectSearchWidget
	 * 
	 */
	private class SubjectSearchWidget extends Composite {
		private FlowPanel mainPanel;
		private FlowPanel radioPanel;
		private RadioButton subjectCriteriaButton;
		private RadioButton policyCriteriaButton;
		private Button searchButton;
		private TextBox searchBox;
		private Label typeLabel;
		private ListBox typeBox;
		private Label nameLabel;

		public SubjectSearchWidget() {
			mainPanel = new FlowPanel();

			Grid grid = new Grid(2, 5);
			// Search for a SubjectGroup by: SubjectType + Name or PolicyType +
			// Name
			radioPanel = new FlowPanel();
			subjectCriteriaButton = new RadioButton("Criteria",
					PolicyAdminUIUtil.policyAdminConstants.subjectCriteria());
			subjectCriteriaButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					typeLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.subjectType());
					nameLabel.setText(PolicyAdminUIUtil.policyAdminConstants
							.subjectGroupName());
					searchButton.setText(PolicyAdminUIUtil.policyAdminConstants
							.search());
				}
			});
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
				}
			});
			radioPanel.add(subjectCriteriaButton);
			radioPanel.add(policyCriteriaButton);

			typeLabel = new Label(
					PolicyAdminUIUtil.policyAdminConstants.subjectType());
			nameLabel = new Label(
					PolicyAdminUIUtil.policyAdminConstants.subjectGroupName());
			typeBox = new ListBox(false);
			searchButton = new Button(
					PolicyAdminUIUtil.policyAdminConstants.search());
			searchBox = new TextBox();
			grid.setWidget(0, 0, radioPanel);
			grid.setWidget(1, 0, typeLabel);
			grid.setWidget(1, 1, typeBox);
			grid.setWidget(1, 2, nameLabel);
			grid.setWidget(1, 3, searchBox);
			grid.setWidget(1, 4, searchButton);

			mainPanel.add(grid);
			initWidget(mainPanel);
		}

		public void clear() {
			// select nothing
			subjectCriteriaButton.setValue(false);
			policyCriteriaButton.setValue(false);
			searchBox.setText("");
		}

		public HasClickHandlers getSubjectCriteriaButton() {
			return subjectCriteriaButton;
		}

		public HasClickHandlers getPolicyCriteriaButton() {
			return policyCriteriaButton;
		}

		public HasClickHandlers getSearchButton() {
			return searchButton;
		}

		public boolean isSubjectCriteriaEnabled() {
			return subjectCriteriaButton.getValue() == true;
		}

		public void setSubjectCriteriaEnabled(boolean enabled) {
			subjectCriteriaButton.setValue(enabled);
		}

		public boolean isPolicyCriteriaEnabled() {
			return policyCriteriaButton.getValue() == true;
		}

		public void setPolicyCriteriaEnabled(boolean enabled) {
			policyCriteriaButton.setValue(enabled);
		}

		public String getSearchTerm() {
			return searchBox.getValue();
		}

		public String getSelectedType() {
			if (typeBox.getSelectedIndex() >= 0)
				return typeBox.getItemText(typeBox.getSelectedIndex());
			else
				return null;
		}

		public void setAvailableTypes(List<String> types) {
			// enable the selection of a subject type
			typeBox.clear();
			if (types != null) {
				for (String s : types)
					typeBox.addItem(s);
			}
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

		public void setSelectedSearchTerm(String term) {
			searchBox.setValue((term == null ? "" : term));
		}

	}

	private class ContentView extends AbstractGenericView implements Display {
		private FlowPanel mainPanel;
		private CellTable<SubjectGroup> cellTable;
		ProvidesKey<SubjectGroup> keyProvider;
		DisclosurePanel searchPanel;
		SubjectSearchWidget searchWidget;
		ListDataProvider<SubjectGroup> dataProvider;
		PushButton actionButton = new PushButton(
				PolicyAdminUIUtil.constants.apply());
		final Map<SubjectGroup, UserAction> pendingActions = new HashMap<SubjectGroup, UserAction>();
		Map<SubjectGroup, List<UserAction>> permittedActions = new HashMap<SubjectGroup, List<UserAction>>();
		ListBox actionCombo = new ListBox();

		/*
		 * Columns
		 */
		Column<SubjectGroup, String> sgNameCol = null;
		TextColumn<SubjectGroup> groupTypeCol = null;
		TextColumn<SubjectGroup> groupCreatedByCol = null;
		TextColumn<SubjectGroup> groupModifiedByCol = null;
		Column<SubjectGroup, Date> groupModifiedDateCol = null;

		public ContentView() {
			mainPanel = new FlowPanel();
			initWidget(mainPanel);
			initialize();
		}

		public void setGroups(List<SubjectGroup> groups) {
			cellTable.setRowCount(0);
			int i = 0;
			while (cellTable.getColumnCount() != 0) {
				cellTable.removeColumn(i);
			}
			;

			final List<SubjectGroup> list;
			if (groups == null) {
				list = Collections.emptyList();
			} else {
				list = groups;

				// date modified sorting
				Comparator timeAscComp = new Comparator<SubjectGroup>() {
					public int compare(SubjectGroup o1, SubjectGroup o2) {
						if (o1 == o2) {
							return 0;
						}
						// Compare the time columns.
						if (o1 != null) {
							return (o2 != null) ? o1
									.getLastModifiedTime()
									.compareTo(o2.getLastModifiedTime()) : 1;
						}
						return -1;
					}
				};

				Collections.sort(list, timeAscComp);
			
			
			}

			ListHandler<SubjectGroup> sortHandler = new ListHandler<SubjectGroup>(
					list) {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void onColumnSort(ColumnSortEvent event) {

					if (sgNameCol.hashCode() == event.getColumn().hashCode()) {
						// name sorting
						Comparator nameAscComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
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
						Comparator nameDescComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
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
					} else if (groupTypeCol.hashCode() == event.getColumn()
							.hashCode()) {
						// type sorting
						Comparator typeAscComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the name columns.
								if (o1 != null) {
									return (o2 != null) ? o1.getType()
											.compareToIgnoreCase(o2.getType())
											: 1;
								}
								return -1;
							}
						};
						Comparator typeDescComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the name columns.
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

					} else if (groupCreatedByCol.hashCode() == event
							.getColumn().hashCode()) {
						// creator sorting
						Comparator creatorAscComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the name columns.
								if (o1 != null) {
									return (o2 != null) ? o1.getCreatedBy()
											.compareToIgnoreCase(
													o2.getCreatedBy()) : 1;
								}
								return -1;
							}
						};
						Comparator creatorDescComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the name columns.
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

					} else if (groupModifiedByCol.hashCode() == event
							.getColumn().hashCode()) {
						// creator sorting
						Comparator modifierAscComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the name columns.
								if (o1 != null && o1.getLastModifiedBy() != null ) {
									return (o2 != null && o2.getLastModifiedBy() != null ) ? o1
											.getLastModifiedBy()
											.compareToIgnoreCase(
													o2.getLastModifiedBy()) : 1;
								}
								return -1;
							}
						};
						Comparator modifierDescComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the name columns.
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

					} else if (groupModifiedDateCol.hashCode() == event
							.getColumn().hashCode()) {
						
						
						// creator sorting
						Comparator timeAscComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the time columns.
								if (o1 != null) {
									return (o2 != null) ? o1
											.getLastModifiedTime()
											.compareTo(o2.getLastModifiedTime()) : 1;
								}
								return -1;
							}
						};
						Comparator timeDescComp = new Comparator<SubjectGroup>() {
							public int compare(SubjectGroup o1, SubjectGroup o2) {
								if (o1 == o2) {
									return 0;
								}
								// Compare the time columns.
								if (o2 != null) {
									return (o1 != null) ? o2
											.getLastModifiedTime()
											.compareTo(
													o1.getLastModifiedTime()) : 1;
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
			final SelectionModel<SubjectGroup> selectionModel = new MultiSelectionModel<SubjectGroup>(
					keyProvider);

			initTableColumns(selectionModel, sortHandler);

			dataProvider.refresh();
		}

		HasClickHandlers getActionButton() {
			return actionButton;
		}

		public void setUserActions(SubjectGroup group,
				List<UserAction> enabledActions) {
			if (group == null)
				return;
			if (enabledActions == null)
				permittedActions.remove(group);
			else
				permittedActions.put(group, new ArrayList<UserAction>(
						enabledActions));
			if (cellTable != null)
				cellTable.redraw();
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
			searchWidget = new SubjectSearchWidget();
			searchPanel = new DisclosurePanel(
					PolicyAdminUIUtil.policyAdminConstants.search());
			searchPanel.setContent(searchWidget);

			// bottom part of panel is a table with search results
			Grid summaryGrid = new Grid(3, 1);
			summaryGrid.setStyleName("sggrid");

			/*
			 * Table section
			 */
			keyProvider = new ProvidesKey<SubjectGroup>() {
				public Object getKey(SubjectGroup group) {
					return group == null ? null : group.getName();
				}
			};
			cellTable = new CellTable<SubjectGroup>(keyProvider);
			dataProvider = new ListDataProvider<SubjectGroup>();
			dataProvider.addDataDisplay(cellTable);

			/*
			 * ends table section
			 */

			actionCombo.addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent paramChangeEvent) {
					pendingActions.clear();
					setGroups(dataProvider.getList());
					actionButton.setEnabled(pendingActions.size() > 0);
				}
			});

			TurmericPager pager = new TurmericPager();
			pager.setDisplay(cellTable);

			FlexTable actionTable = new FlexTable();
			actionTable.setWidget(0, 0, actionCombo);
			actionTable.setWidget(0, 1, actionButton);
			actionTable.setWidget(0, 2, pager);
			actionTable.getCellFormatter().setWidth(0, 2, "800px");
			actionTable.getCellFormatter().setHorizontalAlignment(0, 2,
					HasAlignment.ALIGN_RIGHT);

			summaryGrid.setWidget(0, 0, actionTable);
			summaryGrid.setWidget(1, 0, cellTable);

			mainPanel.addStyleName("sg-summary");
			mainPanel.add(searchPanel);
			searchPanel.addStyleName("sg-content");
			summaryGrid.addStyleName("sg-content");
			mainPanel.add(summaryGrid);
		}

		private void updateCombo() {
			actionCombo.clear();
			actionCombo.addItem(
					PolicyAdminUIUtil.policyAdminConstants.delete(),
					UserAction.SUBJECT_GROUP_DELETE.toString());
			actionCombo.addItem(
					PolicyAdminUIUtil.policyAdminConstants.export(),
					UserAction.SUBJECT_GROUP_EXPORT.toString());

		}

		private <C> Column<SubjectGroup, C> createCell(Cell<C> cell,
				final GetValue<C> getter,
				FieldUpdater<SubjectGroup, C> fieldUpdater) {
			Column<SubjectGroup, C> column = new Column<SubjectGroup, C>(cell) {

				@Override
				public C getValue(SubjectGroup object) {
					return getter.getValue(object);
				}

				@Override
				public void render(Cell.Context context, SubjectGroup object,
						SafeHtmlBuilder sb) {
					sb.appendHtmlConstant("<a href='javascript:void(0);'>");
					super.render(context, object, sb);
					sb.appendHtmlConstant("</a>");
				}
			};
			column.setFieldUpdater(fieldUpdater);
			return column;
		}

		private void initTableColumns(
				final SelectionModel<SubjectGroup> selectionModel,
				ListHandler<SubjectGroup> sortHandler) {

			// checkbox column
			Column<SubjectGroup, SubjectGroup> checkColumn = new Column<SubjectGroup, SubjectGroup>(
					new CustomPermissionCheckboxCell(
							UserAction.valueOf(this.actionCombo
									.getValue(this.actionCombo
											.getSelectedIndex())),
							pendingActions, permittedActions)) {
				public SubjectGroup getValue(SubjectGroup group) {
					return group;
				}
			};
			checkColumn
					.setFieldUpdater(new FieldUpdater<SubjectGroup, SubjectGroup>() {
						public void update(int arg0, SubjectGroup arg1,
								SubjectGroup arg2) {
							if (pendingActions.keySet().contains(arg1)) {
								pendingActions.remove(arg1);
							} else {
								// Called when the user clicks on a checkbox.
								pendingActions.put(arg1, UserAction
										.valueOf(actionCombo
												.getValue(actionCombo
														.getSelectedIndex())));
							}

							actionButton.setEnabled(pendingActions.size() > 0);

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
					for (SubjectGroup visibleItem : cellTable.getVisibleItems()) {
						if (!selected && pendingActions.keySet().contains(visibleItem)) {
	                        pendingActions.remove(visibleItem);
	                    }else if (permittedActions.get(visibleItem).contains(UserAction.valueOf(action)) && 
	                    		selected && !pendingActions.keySet().contains(visibleItem)) {
	                    	pendingActions.put(visibleItem, UserAction.valueOf(
	                        		actionCombo.getValue(actionCombo.getSelectedIndex())));
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

			// subject Group name.
			ClickableTextCell sgNameCellClickable = new ClickableTextCell();
			sgNameCol = createCell(sgNameCellClickable, new GetValue<String>() {
				public String getValue(SubjectGroup sg) {
					return sg.getName();
				}
			}, new FieldUpdater<SubjectGroup, String>() {
					public void update(int index, SubjectGroup sg, String value) {
						pendingActions.clear();
						if(permittedActions.containsKey(sg) && permittedActions.get(sg).contains(UserAction.SUBJECT_GROUP_EDIT)){
							pendingActions.put(sg, UserAction.SUBJECT_GROUP_EDIT);	
						}else{
							pendingActions.put(sg, UserAction.SUBJECT_GROUP_VIEW);
						}
						actionButton.fireEvent(new ClickEvent() {
						});
						pendingActions.clear();
					}
				});
				
			sgNameCol.setSortable(true);
			cellTable.addColumn(sgNameCol,
					PolicyAdminUIUtil.policyAdminConstants.subjectGroupName());
			cellTable.getColumnSortList().push(sgNameCol);

			// subject type
			groupTypeCol = new TextColumn<SubjectGroup>() {
				public String getValue(SubjectGroup group) {
					return (group == null ? null : group.getType().toString());
				}
			};
			groupTypeCol.setSortable(true);

			cellTable.addColumn(groupTypeCol,
					PolicyAdminUIUtil.policyAdminConstants.subjectType());

			// assigned subjects
			Column<SubjectGroup, List<String>> groupSubjectsCol = new Column<SubjectGroup, List<String>>(
					new CustomListCell()) {
				public List<String> getValue(SubjectGroup group) {

					if (group == null || group.getSubjects() == null)
						return null;
					return group.getSubjects();
				}
			};

			cellTable.addColumn(groupSubjectsCol,
					PolicyAdminUIUtil.policyAdminConstants.subjectsAssigned());

			// policies assigned
			Column<SubjectGroup, List<String>> groupPoliciesCol = new Column<SubjectGroup, List<String>>(
					new CustomListCell()) {
				public List<String> getValue(SubjectGroup group) {

					if (group == null || group.getPolicies() == null)
						return null;
					return group.getPolicies();
				}
			};
			cellTable.addColumn(groupPoliciesCol,
					PolicyAdminUIUtil.policyAdminConstants.policiesAssigned());

			// created by
			groupCreatedByCol = new TextColumn<SubjectGroup>() {
				public String getValue(SubjectGroup group) {
					return (group == null ? null : group.getCreatedBy());
				}
			};
			groupCreatedByCol.setSortable(true);
			cellTable.addColumn(groupCreatedByCol,
					PolicyAdminUIUtil.policyAdminConstants.createdBy());

			// Last modified by
			groupModifiedByCol = new TextColumn<SubjectGroup>() {
				public String getValue(SubjectGroup group) {
					return (group == null ? null : group.getLastModifiedBy());
				}
			};
			groupModifiedByCol.setSortable(true);
			cellTable.addColumn(groupModifiedByCol,
					PolicyAdminUIUtil.policyAdminConstants.lastModifiedBy());

			// Last modified date
			groupModifiedDateCol = new Column<SubjectGroup, Date>(new DateCell(
					PolicyAdminUIUtil.tzTimeFormat)) {
				public Date getValue(SubjectGroup group) {
					return (group == null ? null : group.getLastModifiedTime());
				}
			};
			groupModifiedDateCol.setSortable(true);
			cellTable.addColumn(groupModifiedDateCol,
					PolicyAdminUIUtil.policyAdminConstants.lastModifiedTime());

			cellTable.addColumnSortHandler(sortHandler);
		}

		public Map<SubjectGroup, UserAction> getPendingActions() {
			return new HashMap<SubjectGroup, UserAction>(pendingActions);
		}
	}

	/**
	 * Instantiates a new subject group summary view.
	 */
	public SubjectGroupSummaryView() {
		scrollPanel = new ScrollPanel();
		mainPanel = new FlowPanel();
		scrollPanel.add(mainPanel);
		initWidget(scrollPanel);

		initialize();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView#initialize()
	 */
	@Override
	public void initialize() {
		mainPanel.clear();

		mainPanel.setWidth("100%");
		mainPanel.add(initContentView());
	}

	/**
	 * Inits the content view.
	 * 
	 * @return the widget
	 */
	protected Widget initContentView() {
		ScrollPanel actionPanel = new ScrollPanel();
		contentView = new ContentView();
		actionPanel.add(contentView.asWidget());
		return actionPanel;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Display#activate()
	 */
	public void activate() {
		contentView.activate();
		this.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay#getContentView()
	 */
	public Display getContentView() {
		return contentView;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groups
	 *            the new groups
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#setGroups(java.util.List)
	 */
	public void setGroups(List<SubjectGroup> groups) {
		((ContentView) contentView).setGroups(groups);
	}

	/**
	 * Gets the search button.
	 * 
	 * @return the search button
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#getSearchButton()
	 */
	public HasClickHandlers getSearchButton() {
		return ((ContentView) contentView).searchWidget.getSearchButton();
	}

	/**
	 * Gets the search term.
	 * 
	 * @return the search term
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#getSearchTerm()
	 */
	public String getSearchTerm() {
		return ((ContentView) contentView).searchWidget.getSearchTerm();
	}

	/**
	 * Gets the selected type.
	 * 
	 * @return the selected type
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#getType()
	 */
	public String getSelectedType() {
		return ((ContentView) contentView).searchWidget.getSelectedType();
	}

	/**
	 * Checks if is policy criteria enabled.
	 * 
	 * @return true, if is policy criteria enabled
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#isPolicyCriteriaEnabled()
	 */
	public boolean isPolicyCriteriaEnabled() {
		return ((ContentView) contentView).searchWidget
				.isPolicyCriteriaEnabled();
	}

	/**
	 * Checks if is subject criteria enabled.
	 * 
	 * @return true, if is subject criteria enabled
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#isSearchCriteriaEnabled()
	 */
	public boolean isSubjectCriteriaEnabled() {
		return ((ContentView) contentView).searchWidget
				.isSubjectCriteriaEnabled();
	}

	/**
	 * Sets the available types.
	 * 
	 * @param types
	 *            the new available types
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#setAvailableTypes(java.util.List)
	 */
	public void setAvailableTypes(List<String> types) {
		((ContentView) contentView).searchWidget.setAvailableTypes(types);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#getSubjectCriteriaButton()
	 */
	public HasClickHandlers getSubjectCriteriaButton() {
		return ((ContentView) contentView).searchWidget
				.getSubjectCriteriaButton();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#getPolicyCriteriaButton()
	 */
	public HasClickHandlers getPolicyCriteriaButton() {
		return ((ContentView) contentView).searchWidget
				.getPolicyCriteriaButton();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#setPermittedActions(org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup, java.util.List)
	 */
	public void setPermittedActions(SubjectGroup group,
			List<UserAction> permittedActions) {
		((ContentView) contentView).setUserActions(group, permittedActions);
	}

	/**
	 * Gets the action button.
	 * 
	 * @return the action button
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#getActionButton()
	 */
	public HasClickHandlers getActionButton() {
		return ((ContentView) contentView).getActionButton();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#getPendingActions()
	 */
	public Map<SubjectGroup, UserAction> getPendingActions() {
		return ((ContentView) contentView).getPendingActions();
	}

	/**
	 * Sets the selected search term.
	 * 
	 * @param name
	 *            the new selected search term
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#setSelectedSearchTerm(java.lang.String)
	 */
	public void setSelectedSearchTerm(String name) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget.setSelectedSearchTerm(name);
	}

	/**
	 * Sets the selected type.
	 * 
	 * @param type
	 *            the new selected type
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#setSelectedType(java.lang.String)
	 */
	public void setSelectedType(String type) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget.setSelectedType(type);
	}

	/**
	 * Sets the policy criteria enabled.
	 * 
	 * @param enabled
	 *            the new policy criteria enabled
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#setPolicyCriteriaEnabled(boolean)
	 */
	public void setPolicyCriteriaEnabled(boolean enabled) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget
				.setPolicyCriteriaEnabled(enabled);

	}

	/**
	 * Sets the search criteria enabled.
	 * 
	 * @param enabled
	 *            the new search criteria enabled
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#setSearchCriteriaEnabled(boolean)
	 */
	public void setSearchCriteriaEnabled(boolean enabled) {
		((ContentView) contentView).searchPanel.setOpen(true);
		((ContentView) contentView).searchWidget
				.setSubjectCriteriaEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#error(java.lang.String)
	 */
	public void error(String msg) {
		ErrorDialog dialog = new ErrorDialog(true);
		dialog.setMessage(msg);
		dialog.getDialog().center();
		dialog.show();
	}

	/**
	 * Info alerts messages.
	 *
	 * @param msg the msg
	 */
	public void info(String msg) {
		InfoDialog dialog = new InfoDialog(true);
		dialog.setMessage(msg);
		dialog.getDialog().center();
		dialog.show();
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupSummaryPresenter.SubjectGroupSummaryDisplay#clearDataContent()
	 */
	public void clearDataContent() {
		this.setGroups(null);
	}

}
