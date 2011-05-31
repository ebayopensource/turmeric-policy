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
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ExtraField;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Operation;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicySubjectAssignment;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Resource;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.Subject;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.ResourcesContentDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.SubjectContentDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.util.PolicyExtraFieldsUtil;
import org.ebayopensource.turmeric.policy.adminui.client.view.ErrorDialog;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.TurmericPager;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.TurmericStackPanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;

/**
 * The Class PolicyViewView.
 */
public class PolicyViewView extends ResizeComposite implements
		PolicyViewDisplay {

	private final DockLayoutPanel mainPanel;
	private static final String TITLE_FORM = PolicyAdminUIUtil.policyAdminConstants
			.policyInformationView();
	
	/** The Constant SELECTED_ACTION. */
	protected static final UserAction SELECTED_ACTION = UserAction.POLICY_VIEW;

	private Display contentView;
	private ResourcesContentDisplay resourceContentView;
	private SubjectContentDisplay subjectContentView;

	/** The policy name. */
	protected TextBox policyName;
	
	/** The policy desc. */
	protected TextArea policyDesc;
	
	/** The policy type. */
	protected Label policyType;
	
	/** The policy status. */
	protected ListBox policyStatus;

	/** The extra grid available. */
	protected boolean extraGridAvailable;

	private Button cancelButton;

	/** The extra fields grid. */
	protected Grid extraFieldsGrid = new Grid(1, 1);
	
	/** The extra field list. */
	protected List<ExtraField> extraFieldList;

	/**
	 * Instantiates a new policy view view.
	 */
	public PolicyViewView() {
		mainPanel = new DockLayoutPanel(Unit.EM);
		initWidget(mainPanel);
		mainPanel.setWidth("100%");
		initialize();

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#getSelectedAction()
	 */
	@Override
	public UserAction getSelectedAction() {
		return SELECTED_ACTION;
	}

	/**
	 * Initialize.
	 */
	public void initialize() {

		policyName = new TextBox();
		policyName.setEnabled(false);
		
		policyDesc = new TextArea();
		policyDesc.setEnabled(false);
		
		policyType = new Label();
		
		policyStatus = new ListBox();
		policyStatus.setEnabled(false);
		policyStatus.addItem(PolicyAdminUIUtil.policyAdminConstants.enable(), PolicyAdminUIUtil.policyAdminConstants.enable());
		policyStatus.addItem(PolicyAdminUIUtil.policyAdminConstants.disable(), PolicyAdminUIUtil.policyAdminConstants.disable());
		
		
		extraFieldList = new ArrayList<ExtraField>();

		// CONTENT
		TurmericStackPanel policyContentPanel = new TurmericStackPanel();
		policyContentPanel.add(initContentView(), TITLE_FORM);
		policyContentPanel.add(initResourceContentView(),
				PolicyAdminUIUtil.policyAdminConstants.resources());
		policyContentPanel.add(initSubjectContentView(),
				PolicyAdminUIUtil.policyAdminConstants.subjectsAndSubjectGroups());
		policyContentPanel.setWidth("100%");

		cancelButton = new Button(PolicyAdminUIUtil.constants.cancel());
		final HorizontalPanel buttonsPannel = new HorizontalPanel();
		buttonsPannel.add(cancelButton);

		mainPanel.addSouth(buttonsPannel, 2);
		
		ScrollPanel scroll =  new ScrollPanel( policyContentPanel);
		scroll.setHeight("95%");
		mainPanel.add(scroll);
		
	}

	/**
	 * Inits the content view.
	 * 
	 * @return the widget
	 */
	protected Widget initContentView() {
		final ScrollPanel actionPanel = new ScrollPanel();
		contentView = new ContentView();
		actionPanel.add(contentView.asWidget());
		return actionPanel;
	}

	/**
	 * Inits the resource content view.
	 * 
	 * @return the widget
	 */
	protected Widget initResourceContentView() {
		final ScrollPanel actionPanel = new ScrollPanel();
		resourceContentView = new ResourceContentView();
		actionPanel.add(resourceContentView.asWidget());

		return actionPanel;
	}

	/**
	 * Inits the subject content view.
	 * 
	 * @return the widget
	 */
	protected Widget initSubjectContentView() {
		final ScrollPanel actionPanel = new ScrollPanel();
		subjectContentView = new SubjectContentView();
		actionPanel.add(subjectContentView.asWidget());

		return actionPanel;
	}

	private class ContentView extends AbstractGenericView implements Display {
		final private VerticalPanel mainPanel;
		private Grid policyInfoGrid;

		public ContentView() {
			mainPanel = new VerticalPanel();
			initWidget(mainPanel);
			initialize();
		}

		public void activate() {
			// do nothing for now
		}

		@Override
		public void initialize() {
			mainPanel.clear();

			policyName.setWidth("300px");
			policyDesc.setWidth("250px");
			policyType.setWidth("300px");
			policyStatus.setWidth("100px");

			policyInfoGrid = new Grid(4, 2);

			policyInfoGrid.setWidget(0, 0, new Label(
					PolicyAdminUIUtil.policyAdminConstants.policyType() + ":"));
			policyInfoGrid.setWidget(0, 1, policyType);
			
			policyInfoGrid.setWidget(1, 0, new Label(
					PolicyAdminUIUtil.policyAdminConstants.policyName() + ":"));
			policyInfoGrid.setWidget(1, 1, policyName);
			policyInfoGrid
					.setWidget(
							2,
							0,
							new Label(PolicyAdminUIUtil.policyAdminConstants
									.policyDescription() + ":"));
			policyInfoGrid.setWidget(2, 1, policyDesc);

			policyInfoGrid.setWidget(3, 0, new Label(
					PolicyAdminUIUtil.policyAdminConstants.status() + ":"));
			policyInfoGrid.setWidget(3, 1, policyStatus);

			mainPanel.add(policyInfoGrid);
			setExtraFields();
			mainPanel.add(extraFieldsGrid);
			extraFieldsGrid.setVisible(extraGridAvailable);

		}

		protected void setExtraFields() {
			// TODO move this setting to a particular RL class
			extraFieldList = PolicyExtraFieldsUtil.getRLExtraFields();
			extraFieldsGrid = new Grid(extraFieldList.size() + 1, 3);
			for (ExtraField extraField : extraFieldList) {

				extraFieldsGrid.setWidget(extraField.getOrder() - 1, 0,
						new Label(extraField.getLabelName()));

				if (extraField.getFieldType() != null
						&& "CheckBox".equalsIgnoreCase(extraField
								.getFieldType())) {
					final CheckBox chBox = new CheckBox();
					// TODO set value
					chBox.setEnabled(false);
					extraField.setCheckBox(chBox);
					extraFieldsGrid.setWidget(extraField.getOrder() - 1, 1,
							extraField.getCheckBox());
				} else if (extraField.getFieldType() != null
						&& ("TextBox".equalsIgnoreCase(extraField
								.getFieldType())
								|| "TextArea".equalsIgnoreCase(extraField
										.getFieldType()) || "ListBox"
								.equalsIgnoreCase(extraField.getFieldType()))) {
					final Label label = new Label(" ");
					extraFieldsGrid.setWidget(extraField.getOrder() - 1, 1,
							label);
				}
			}

		}
	}

	private class ResourceContentView extends AbstractGenericView implements
			ResourcesContentDisplay {
		final private FlowPanel mainPanel;

		private CellTable<Resource> cellTable;
		private ProvidesKey<Resource> keyProvider;
		private ListDataProvider<Resource> dataProvider;

		public ResourceContentView() {
			mainPanel = new FlowPanel();
			initWidget(mainPanel);
			initialize();
		}

		public void activate() {
			// do nothing for now
		}

		@Override
		public void initialize() {
			mainPanel.clear();

			// bottom part of panel is a table with search results
			final Grid summaryGrid = new Grid(2, 1);
			summaryGrid.setStyleName("sggrid");

			keyProvider = new ProvidesKey<Resource>() {
				public Object getKey(final Resource assignment) {
					return assignment == null ? null : assignment
							.getResourceType() + assignment.getResourceName();
				}
			};

			cellTable = new CellTable<Resource>(keyProvider);

			dataProvider = new ListDataProvider<Resource>();
			dataProvider.addDataDisplay(cellTable);
			final TurmericPager pager = new TurmericPager();
			pager.setDisplay(cellTable);
			// resource type
			final TextColumn<Resource> resourceTypeCol = new TextColumn<Resource>() {
				public String getValue(final Resource assignment) {
					if (assignment == null
							|| assignment.getResourceType() == null) {
						return null;
					}
					return assignment.getResourceType();
				}
			};
			cellTable.addColumn(resourceTypeCol,
					PolicyAdminUIUtil.policyAdminConstants.resourceType());

			// resource Name
			final TextColumn<Resource> resourceNameCol = new TextColumn<Resource>() {
				public String getValue(final Resource assignment) {
					if (assignment == null
							|| assignment.getResourceName() == null) {
						return null;
					}
					return assignment.getResourceName();
				}
			};
			cellTable.addColumn(resourceNameCol,
					PolicyAdminUIUtil.policyAdminConstants.resourceName());

			// operations
			// TODO add operations name into table
			Column<Resource, List<String>> resourceOpsCol = new Column<Resource, List<String>>(
					new CustomListCell(MIN_SCROLLBAR_SIZE)) {
				public List<String> getValue(Resource resource) {

					if (resource == null || resource.getOpList() == null) {
						return null;
					}
					ArrayList<String> opsNamesList = new ArrayList<String>();
					for (Operation op : resource.getOpList()) {
						opsNamesList.add(op.getOperationName());
					}
					return opsNamesList;
				}
			};

			cellTable.addColumn(resourceOpsCol,
					PolicyAdminUIUtil.policyAdminConstants.operations());

			summaryGrid.setWidget(0, 0, cellTable);
			summaryGrid.setWidget(1, 0, pager);

			mainPanel.addStyleName("resource-summary");
			summaryGrid.addStyleName("resource-content");
			mainPanel.add(summaryGrid);
		}

		public void error(final String msg) {
			final ErrorDialog dialog = new ErrorDialog(true);
			dialog.setMessage(msg);
			dialog.getDialog().center();
			dialog.show();

		}

		public void setAssignments(final List<Resource> assignments) {
			final List<Resource> data = dataProvider.getList();
			data.clear();
			if (assignments != null) {
				data.addAll(assignments);
			}
			cellTable.redraw();
		}

	}

	private class SubjectContentView extends AbstractGenericView implements
			SubjectContentDisplay {
		private final SimplePanel mainPanel;
		private final Grid mainGrid;
		private CellTable<PolicySubjectAssignment> cellTable;
		private ProvidesKey<PolicySubjectAssignment> keyProvider;
		private ListDataProvider<PolicySubjectAssignment> dataProvider;
		private Grid subjectGrid;
		private TurmericPager pager;

		public SubjectContentView() {
			mainPanel = new SimplePanel();
			mainGrid = new Grid(2, 1);
			mainPanel.add(mainGrid);
			initWidget(mainPanel);

			initialize();
		}

		public void activate() {
			// do nothing for now
		}

		@Override
		public void initialize() {
			subjectGrid = new Grid(3, 1);
			createSubjectTableFields();
			subjectGrid.setWidget(0, 0, cellTable);
			subjectGrid.setWidget(1, 0, pager);
			mainGrid.setWidget(1, 0, subjectGrid);
			mainGrid.setWidth("60%");
		}

		protected void createSubjectTableFields() {

			keyProvider = new ProvidesKey<PolicySubjectAssignment>() {
				public Object getKey(PolicySubjectAssignment assignment) {
					return assignment == null ? null : assignment
							.getSubjectType();
				}
			};

			cellTable = new CellTable<PolicySubjectAssignment>(keyProvider);

			dataProvider = new ListDataProvider<PolicySubjectAssignment>();
			dataProvider.addDataDisplay(cellTable);

			pager = new TurmericPager();
			pager.setDisplay(cellTable);
			// text column for type
			final TextColumn<PolicySubjectAssignment> typeCol = new TextColumn<PolicySubjectAssignment>() {
				public String getValue(final PolicySubjectAssignment assignment) {
					if (assignment == null ) {
						return null;
					}
					return assignment.getSubjectType();
				}
			};
			cellTable.addColumn(typeCol,
					PolicyAdminUIUtil.policyAdminConstants.subjectType());

			// text column for Subject names
			Column<PolicySubjectAssignment, List<String>> subjectNamesCol = new Column<PolicySubjectAssignment, List<String>>(
					new CustomListCell(MIN_SCROLLBAR_SIZE)) {
				public List<String> getValue(PolicySubjectAssignment assignment) {

					if (assignment == null || assignment.getSubjects() == null) {
						return null;
					}
					ArrayList<String> namesList = new ArrayList<String>();
					for (Subject subject : assignment.getSubjects()) {
						//name = null means apply all subject for that type 
						namesList.add((subject.getName()==null ? PolicyAdminUIUtil.policyAdminConstants.all() : subject.getName()));
					}

					return namesList;
				}
			};
			
			cellTable.addColumn(subjectNamesCol,
					PolicyAdminUIUtil.policyAdminConstants.subjects());

			

			// text column for SubjectGroup names
			Column<PolicySubjectAssignment, List<String>> sgNamesCol = new Column<PolicySubjectAssignment, List<String>>(
					new CustomListCell(MIN_SCROLLBAR_SIZE)) {
				public List<String> getValue(PolicySubjectAssignment assignment) {

					if (assignment == null || assignment.getSubjectGroups()  == null) {
						return null;
					}
					ArrayList<String> namesList = new ArrayList<String>();
					for (SubjectGroup subjectGroup : assignment.getSubjectGroups()) {
						namesList.add(subjectGroup.getName());
					}

					return namesList;
				}
			};
			
			cellTable.addColumn(sgNamesCol,
					PolicyAdminUIUtil.policyAdminConstants.subjectGroups());

			
		}

		public void setAssignments(
				final List<PolicySubjectAssignment> assignments) {
			final List<PolicySubjectAssignment> data = dataProvider.getList();
			data.clear();

			if (assignments != null) {
				data.addAll(assignments);
			}
			cellTable.redraw();
		}
		
		public CellTable<PolicySubjectAssignment> getCellTable(){
			return cellTable;
		}

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Display#activate()
	 */
	public void activate() {
		contentView.activate();
		this.setVisible(true);
	}

	/**
	 * Gets the action selected.
	 * 
	 * @return the action selected
	 */
	public UserAction getActionSelected() {
		return UserAction.BL_POLICY_CREATE;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay#getContentView()
	 */
	public Display getContentView() {
		return contentView;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#getResourceContentView()
	 */
	public ResourcesContentDisplay getResourceContentView() {
		return resourceContentView;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#getCancelButton()
	 */
	public Button getCancelButton() {
		return cancelButton;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#getSubjectContentView()
	 */
	@Override
	public SubjectContentDisplay getSubjectContentView() {
		return subjectContentView;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#setPolicyDesc(java.lang.String)
	 */
	public void setPolicyDesc(final String policyDesc) {
		this.policyDesc.setText(policyDesc);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#setPolicyType(java.lang.String)
	 */
	public void setPolicyType(final String policyType) {
		if("RL".equalsIgnoreCase(policyType)){
			CellTable<PolicySubjectAssignment> cellTable = ((SubjectContentView)subjectContentView).getCellTable();

			// text column for Exclusion Subject names
			Column<PolicySubjectAssignment, List<String>> excluionSubjectNamesCol = new Column<PolicySubjectAssignment, List<String>>(
					new CustomListCell(MIN_SCROLLBAR_SIZE)) {
				public List<String> getValue(PolicySubjectAssignment assignment) {

					if (assignment == null || assignment.getExclusionSubjects() == null) {
						return null;
					}
					ArrayList<String> namesList = new ArrayList<String>();
					for (Subject subject : assignment.getExclusionSubjects()) {
						namesList.add(subject.getName());
					}

					return namesList;
				}
			};
			
			cellTable.addColumn(excluionSubjectNamesCol,
					PolicyAdminUIUtil.policyAdminConstants.exclusionSubjects());
			
			
			// text column for Exclusion Subject Group names
			Column<PolicySubjectAssignment, List<String>> exclusionSGNamesCol = new Column<PolicySubjectAssignment, List<String>>(
					new CustomListCell(MIN_SCROLLBAR_SIZE)) {
				public List<String> getValue(PolicySubjectAssignment assignment) {

					if (assignment == null || assignment.getExclusionSubjectGroups() == null) {
						return null;
					}
					ArrayList<String> namesList = new ArrayList<String>();
					for (SubjectGroup subjectGroup : assignment.getExclusionSubjectGroups()) {
						namesList.add(subjectGroup.getName());
					}

					return namesList;
				}
			};
			
			cellTable.addColumn(exclusionSGNamesCol,
					PolicyAdminUIUtil.policyAdminConstants.exclusionSubjectGroups());
		
		}
		
		this.policyType.setText(policyType);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#setPolicyStatus(boolean)
	 */
	public void setPolicyStatus(final boolean enabled) {
		if (enabled) {
			this.policyStatus.setSelectedIndex(0);
		} else {
			this.policyStatus.setSelectedIndex(1);
		}
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#setPolicyName(java.lang.String)
	 */
	public void setPolicyName(final String policyName) {
		this.policyName.setText(policyName);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#setExtraFieldAvailable(boolean)
	 */
	public void setExtraFieldAvailable(final boolean available) {
		this.extraGridAvailable = available;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#setExtraFieldList(java.util.List)
	 */
	public void setExtraFieldList(List<ExtraField> extraFieldList) {

		for (ExtraField extraField : extraFieldList) {

			if (extraField.getFieldType() != null
					&& "CheckBox".equalsIgnoreCase(extraField.getFieldType())) {
				final CheckBox ch = (CheckBox) extraFieldsGrid.getWidget(
						extraField.getOrder() - 1, 1);
				ch.setValue(Boolean.parseBoolean(extraField.getValue()));

			} else if (extraField.getFieldType() != null
					&& ("Label".equalsIgnoreCase(extraField.getFieldType()))) {
				final Label lbl = (Label) extraFieldsGrid.getWidget(
						extraField.getOrder() - 1, 1);
				lbl.setText(extraField.getValue());

			}
		}
		extraFieldsGrid.setVisible(extraGridAvailable);
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#clear()
	 */
	public void clear() {
		policyName.setText("");
		policyDesc.setText("");
		policyType.setText("");
		policyStatus.setSelectedIndex(-1);
		extraGridAvailable = false;

		if (this.extraFieldList != null && this.extraFieldList.size() > 0) {

			for (int i = 0; i < extraFieldList.size(); i++) {
				extraFieldList.get(i).setValue("");
			}

			setExtraFieldList(extraFieldList);
		}

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.PolicyViewPresenter.PolicyViewDisplay#error(java.lang.String)
	 */
	public void error(final String msg) {
		final ErrorDialog dialog = new ErrorDialog(true);
		dialog.setMessage(msg);
		dialog.getDialog().center();
		dialog.show();
	}

	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Display#setAssociatedId(java.lang.String)
	 */
	public void setAssociatedId(final String id) {

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Display#getAssociatedId()
	 */
	public String getAssociatedId() {
		return null;
	}

}
