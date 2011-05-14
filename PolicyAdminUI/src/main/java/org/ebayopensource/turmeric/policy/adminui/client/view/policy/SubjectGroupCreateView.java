/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.model.SubjectGroupCalculator;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.view.ErrorDialog;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.FooterWidget;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.HeaderWidget;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyMenuWidget;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.MenuDisplay;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Class SubjectGroupCreateView.
 */
public class SubjectGroupCreateView extends AbstractGenericView implements SubjectGroupCreateDisplay {

    /** The Constant SELECTED_ACTION. */
    private final static UserAction SELECTED_ACTION = UserAction.SUBJECT_GROUP_CREATE;

    /** The scroll panel. */
    private ScrollPanel scrollPanel;

    /** The main panel. */
    private FlowPanel mainPanel;

    /** The content view. */
    private Display contentView;

    /**
     * ContentView.
     */
    private class ContentView extends AbstractGenericView implements Display {

        /** The main panel. */
        private FlowPanel mainPanel;

        /** The table. */
        private FlexTable table;

        /** The name box. */
        private TextBox nameBox;

        /** The desc box. */
        private TextArea descBox;

        /** The is calculated sg. */
        private CheckBox isCalculatedSg;

        /** The sg calculators. */
        private ListBox sgCalculators;

        /** The assignment widget. */
        private SubjectGroupAssignmentWidget assignmentWidget;

        /** The create button. */
        private Button createButton;

        /** The cancel button. */
        private Button cancelButton;

        /** The sg calculator map. */
        private Map<String, String> sgCalculatorMap;

        /**
         * Instantiates a new content view.
         */
        public ContentView() {
            mainPanel = new FlowPanel();
            table = new FlexTable();
            nameBox = new TextBox();
            descBox = new TextArea();
            sgCalculators = new ListBox();
            isCalculatedSg = new CheckBox();
            createButton = new Button(PolicyAdminUIUtil.constants.create());
            cancelButton = new Button(PolicyAdminUIUtil.constants.cancel());

            initWidget(mainPanel);
            initialize();
        }

        /*
         * (non-Javadoc)
         * @see org.ebayopensource.turmeric.policy.adminui.client.Display#activate()
         */
        public void activate() {

        }

        /*
         * (non-Javadoc)
         * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView#initialize()
         */
        @Override
        public void initialize() {
            mainPanel.clear();
            table.setWidget(0, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.subjectGroupName() + ":"));
            table.setWidget(0, 1, nameBox);
            table.setWidget(1, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.subjectGroupDescription() + ":"));
            table.setWidget(1, 1, descBox);
            table.setWidget(2, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.calculated() + ":"));
            table.setWidget(2, 1, this.isCalculatedSg);

            this.isCalculatedSg.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    boolean checked = ((CheckBox) event.getSource()).getValue();
                    if (checked) {
                        sgCalculators.setEnabled(true);
                        fillCalculatorList(sgCalculators);
                    }
                    else {
                        emptyCalculatorList(sgCalculators);
                        sgCalculators.setEnabled(false);
                    }
                }
            });

            assignmentWidget = new SubjectGroupAssignmentWidget();
            table.setWidget(3, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.sgCalculator() + ":"));
            table.setWidget(3, 1, sgCalculators);
            table.setWidget(4, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.subjects() + ":"));
            table.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
            table.setWidget(4, 1, assignmentWidget);

            assignmentWidget.subjectTypeBox.addChangeHandler(new ChangeHandler() {

                @Override
                public void onChange(ChangeEvent arg0) {
                    isCalculatedSg.setChecked(false);
                    sgCalculators.clear();
                }
            });

            mainPanel.add(table);
            mainPanel.add(createButton);
            mainPanel.add(cancelButton);

        }

        /**
         * Empty calculator list.
         * 
         * @param sgCalculators2
         *            the sg calculators2
         */
        protected void emptyCalculatorList(ListBox sgCalculators2) {
            this.sgCalculators.clear();
        }

        /**
         * Fill calculator list.
         * 
         * @param sgCalculators
         *            the sg calculators
         */
        protected void fillCalculatorList(ListBox sgCalculators) {
            String subjectType = this.getSubjectType();
            if (this.sgCalculatorMap != null && subjectType != null) {
                for (String key : sgCalculatorMap.keySet()) {
                    if (subjectType.equals(sgCalculatorMap.get(key))) {
                        this.sgCalculators.addItem(key);
                    }
                }

            }
        }

        /**
         * Gets the creates the button.
         * 
         * @return the creates the button
         */
        public Button getCreateButton() {
            return createButton;
        }

        /**
         * Gets the cancel button.
         * 
         * @return the cancel button
         */
        public HasClickHandlers getCancelButton() {
            return cancelButton;
        }

        /**
         * Gets the search button.
         * 
         * @return the search button
         */
        public HasClickHandlers getSearchButton() {
            return assignmentWidget.getSearchButton();
        }

        /**
         * Gets the description.
         * 
         * @return the description
         * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getDescription()
         */
        public String getDescription() {
            return descBox.getText();
        }

        /**
         * Gets the name.
         * 
         * @return the name
         * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getName()
         */
        public String getName() {
            return nameBox.getText();
        }

        /**
         * Gets the search term.
         * 
         * @return the search term
         * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getSearchTerm()
         */
        public String getSearchTerm() {
            return assignmentWidget.getSearchTerm();
        }

        /**
         * Gets the selected subjects.
         * 
         * @return the selected subjects
         * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getSelectedSubjects()
         */
        public List<String> getSelectedSubjects() {
            return assignmentWidget.getSelectedSubjects();
        }

        /**
         * Gets the subject type.
         * 
         * @return the subject type
         * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getSubjectType()
         */
        public String getSubjectType() {
            return assignmentWidget.getSubjectType();
        }

        /**
         * Sets the available subjects.
         * 
         * @param subjects
         *            the new available subjects
         */
        public void setAvailableSubjects(List<String> subjects) {
            assignmentWidget.setAvailableSubjects(subjects);
        }

        /**
         * Sets the subject types.
         * 
         * @param subjectTypes
         *            the new subject types
         */
        public void setSubjectTypes(List<String> subjectTypes) {
            assignmentWidget.setAvailableSubjectTypes(subjectTypes);
        }

        /**
         * Checks if is sg calculated.
         * 
         * @return the boolean
         */
        public Boolean isSgCalculated() {
            return this.isCalculatedSg.getValue();
        }

        /**
         * Sets the sg calculator map.
         * 
         * @param sgCalculatorMap2
         *            the sg calculator map2
         */
        public void setSgCalculatorMap(Map<String, String> sgCalculatorMap2) {
            this.sgCalculatorMap = sgCalculatorMap2;
        }

        /**
         * Gets the selected subject group calculator name.
         * 
         * @return the selected subject group calculator name
         */
        public String getSelectedSubjectGroupCalculatorName() {
            if (sgCalculators.getSelectedIndex() >= 0) {
                return this.sgCalculators.getItemText(sgCalculators.getSelectedIndex());
            }
            else {
                return null;
            }
        }
    }

    /**
     * Instantiates a new subject group create view.
     */
    public SubjectGroupCreateView() {
        scrollPanel = new ScrollPanel();
        mainPanel = new FlowPanel();
        scrollPanel.add(mainPanel);
        initWidget(scrollPanel);
        initialize();
    }

    /*
     * (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView#initialize()
     */
    @Override
    public void initialize() {
        mainPanel.clear();
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

    /*
     * (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Display#activate()
     */
    public void activate() {
        contentView.activate();
        this.setVisible(true);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay
     * #getContentView()
     */
    public Display getContentView() {
        return contentView;
    }

    /**
     * Gets the cancel button.
     * 
     * @return the cancel button
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getCancelButton()
     */
    @Override
    public HasClickHandlers getCancelButton() {
        return ((ContentView) contentView).getCancelButton();
    }

    /**
     * Gets the creates the button.
     * 
     * @return the creates the button
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getCreateButton()
     */
    @Override
    public Button getCreateButton() {
        return ((ContentView) contentView).getCreateButton();
    }

    /**
     * Gets the description.
     * 
     * @return the description
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getDescription()
     */
    @Override
    public String getDescription() {
        return ((ContentView) contentView).getDescription();
    }

    /**
     * Gets the name.
     * 
     * @return the name
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getName()
     */
    @Override
    public String getName() {
        return ((ContentView) contentView).getName();
    }

    /**
     * Gets the search button.
     * 
     * @return the search button
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getSearchButton()
     */
    @Override
    public HasClickHandlers getSearchButton() {
        return ((ContentView) contentView).getSearchButton();
    }

    /**
     * Gets the search term.
     * 
     * @return the search term
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getSearchTerm()
     */
    @Override
    public String getSearchTerm() {
        return ((ContentView) contentView).getSearchTerm();
    }

    /**
     * Gets the selected subjects.
     * 
     * @return the selected subjects
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getSelectedSubjects()
     */
    @Override
    public List<String> getSelectedSubjects() {
        return ((ContentView) contentView).getSelectedSubjects();
    }

    /**
     * Gets the subject type.
     * 
     * @return the subject type
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getSubjectType()
     */
    @Override
    public String getSubjectType() {
        return ((ContentView) contentView).getSubjectType();
    }

    /*
     * (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.
     * SubjectGroupCreateDisplay#setAvailableSubjects(java.util.List)
     */
    @Override
    public void setAvailableSubjects(List<String> subjects) {
        ((ContentView) contentView).setAvailableSubjects(subjects);
    }

    /**
     * Sets the subject types.
     * 
     * @param subjectTypes
     *            the new subject types
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#setSubjectTypes(java.util.List)
     */
    @Override
    public void setSubjectTypes(List<String> subjectTypes) {
        ((ContentView) contentView).setSubjectTypes(subjectTypes);
    }

    /*
     * (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.
     * SubjectGroupCreateDisplay#error(java.lang.String)
     */
    public void error(String msg) {
        ErrorDialog dialog = new ErrorDialog(true);
        dialog.setMessage(msg);
        dialog.getDialog().center();
        dialog.show();
    }

    /*
     * (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.
     * SubjectGroupCreateDisplay#isSgCalculated()
     */
    @Override
    public Boolean isSgCalculated() {
        return ((ContentView) contentView).isSgCalculated();
    }

    /*
     * (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.
     * SubjectGroupCreateDisplay#getSelectedSubjectGroupCalculatorName()
     */
    @Override
    public String getSelectedSubjectGroupCalculatorName() {
        return ((ContentView) contentView).getSelectedSubjectGroupCalculatorName();
    }

    /*
     * (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.
     * SubjectGroupCreateDisplay#setSgCalculatorMap(java.util.Map)
     */
    @Override
    public void setSgCalculatorMap(Map<String, String> sgCalculatorMap) {
        ((ContentView) contentView).setSgCalculatorMap(sgCalculatorMap);
    }

}
