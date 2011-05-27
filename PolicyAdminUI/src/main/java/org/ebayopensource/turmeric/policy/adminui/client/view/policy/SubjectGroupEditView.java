/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.view.ErrorDialog;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * SubjectGroupEditView.
 */
public class SubjectGroupEditView extends AbstractGenericView implements SubjectGroupEditDisplay {
    private ScrollPanel scrollPanel;
    private FlowPanel mainPanel;
    private Display contentView;
    
    
    
    /**
     * ContentView
     *
     */
    private class ContentView extends AbstractGenericView implements  Display {
        private FlowPanel mainPanel;
        private FlexTable table;
        private TextBox nameBox;
        private TextArea descBox;
        private CheckBox isCalculatedSg;
        private ListBox sgCalculators;
        private SubjectGroupAssignmentWidget assignmentWidget;
        private Button saveButton;
        private Button cancelButton;
        private Map<String, String> sgCalculatorMap;
        private String subjectType;
        
        public ContentView() {
            mainPanel = new FlowPanel();
            table = new FlexTable();
            nameBox = new TextBox();
            descBox = new TextArea();
            sgCalculators = new ListBox();
            isCalculatedSg = new CheckBox();
            saveButton = new Button(PolicyAdminUIUtil.constants.apply());
            cancelButton = new Button(PolicyAdminUIUtil.constants.cancel());
            initWidget(mainPanel);

            initialize();
        }

        public void activate() {  
            
        }
        
        @Override
        public void initialize() {
            mainPanel.clear();
            table.setWidget(0, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.subjectGroupName()+":"));
            table.setWidget(0, 1, nameBox);
            table.setWidget(1, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.subjectGroupDescription()+":"));
            table.setWidget(1, 1, descBox);
            table.setWidget(2, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.calculated()+":"));
            table.setWidget(2, 1, this.isCalculatedSg);
            
            this.isCalculatedSg.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    boolean checked = ((CheckBox) event.getSource()).getValue();
                    if(checked){
                        sgCalculators.setEnabled(true);
                        fillCalculatorList(sgCalculators);
                    }else{
                        emptyCalculatorList(sgCalculators);
                        sgCalculators.setEnabled(false);
                    }
                  }
                });
            
            assignmentWidget = new SubjectGroupAssignmentWidget();
            table.setWidget(3, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.sgCalculator()+":"));
            table.setWidget(3, 1, sgCalculators);
            table.setWidget(4, 0, new Label(PolicyAdminUIUtil.policyAdminConstants.subjects()+":"));
            table.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
            table.setWidget(4, 1, assignmentWidget);
            
            
            mainPanel.add(table);
            mainPanel.add(saveButton);
            mainPanel.add(cancelButton);
        }
        
        public void setDescription (String desc) {
            descBox.setText(desc);
        }
        
        protected void emptyCalculatorList(ListBox sgCalculators2) {
            this.sgCalculators.clear();
        }
        
        /**
         * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupCreatePresenter.SubjectGroupCreateDisplay#getSubjectType()
         */
        public String getSubjectType() {
            return this.subjectType;
        }

        public void fillCalculatorList(ListBox sgCalculators) {
            String subjectType = this.getSubjectType();
            if(this.sgCalculatorMap != null && subjectType != null){
                this.sgCalculators.clear();
                for (String key : sgCalculatorMap.keySet()) {
                    if(subjectType.equals(sgCalculatorMap.get(key))){
                        this.sgCalculators.addItem(key);
                    }
                }
                
            }
        }
        
        public String getDescription () {
            return descBox.getText();
        }
        
        public void setName (String name) {
            nameBox.setText(name);
        }
        
        public String getName () {
            return nameBox.getText();
        }
        
        public Button getSaveButton() {
            return saveButton;
        }
        
        public HasClickHandlers getCancelButton() {
            return cancelButton;
        }
        
        public HasClickHandlers getSearchButton() {
            return assignmentWidget.getSearchButton();
        }
        
        public String getSearchTerm() {
            return assignmentWidget.getSearchTerm();
        }

        public List<String> getSelectedSubjects() {
            return assignmentWidget.getSelectedSubjects();
        }
        
        public void setSelectedSubjects(List<String> selectedSubjects) {
            assignmentWidget.setSelectedSubjects(selectedSubjects);
        } 
        
        public void setAvailableSubjects(List<String> selectedSubjects) {
            assignmentWidget.setAvailableSubjects(selectedSubjects);
        }
        
        public Boolean isSgCalculated() {
            return this.isCalculatedSg.getValue();
        }

        public void setSgCalculatorMap(Map<String, String> sgCalculatorMap2) {
            this.sgCalculatorMap = sgCalculatorMap2;
        }

        public String getSelectedSubjectGroupCalculatorName() {
            if(sgCalculators.getSelectedIndex()>=0){
                return this.sgCalculators.getItemText(sgCalculators.getSelectedIndex());
            }else{
                return null;
            }
            
        }

        public void setSelectedType(String type) {
            this.subjectType = type;
        }
    }

    
    /**
	 * Instantiates a new subject group edit view.
	 */
    public SubjectGroupEditView() {
        scrollPanel = new ScrollPanel();
        mainPanel = new FlowPanel();
        scrollPanel.add(mainPanel);
        initWidget(scrollPanel);
        
        initialize();
    }

    
    /**
	 * Initialize.
	 * 
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
    

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay#getContentView()
     */
    public Display getContentView() {
        return contentView;
    }
   



    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Display#activate()
     */
    public void activate() {
        contentView.activate();
        this.setVisible(true);
    }


    /**
	 * Gets the description.
	 * 
	 * @return the description
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#getDescription()
	 */
    @Override
    public String getDescription() {
        return ((ContentView)contentView).getDescription();
    }


    /**
	 * Gets the name.
	 * 
	 * @return the name
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#getName()
	 */
    @Override
    public String getName() {
        return ((ContentView)contentView).getName();
    }


    /**
	 * Gets the selected subjects.
	 * 
	 * @return the selected subjects
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#getSelectedSubjects()
	 */
    @Override
    public List<String> getSelectedSubjects() {
        return ((ContentView)contentView).getSelectedSubjects();
    }


    /**
	 * Sets the available subjects.
	 * 
	 * @param subjects
	 *            the new available subjects
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#setAvailableSubjects(java.util.List)
	 */
    @Override
    public void setAvailableSubjects(List<String> subjects) {
         ((ContentView)contentView).setAvailableSubjects(subjects);
    }


    /**
	 * Sets the description.
	 * 
	 * @param desc
	 *            the new description
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#setDescription(java.lang.String)
	 */
    @Override
    public void setDescription(String desc) {
        ((ContentView)contentView).setDescription(desc);
    }


    /**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#setName(java.lang.String)
	 */
    @Override
    public void setName(String name) {
        ((ContentView)contentView).setName(name);
    }
    
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#error(java.lang.String)
     */
    public void error (String msg) {
        ErrorDialog dialog = new ErrorDialog(true);
        dialog.setMessage(msg);
        dialog.getDialog().center();
        dialog.show();
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#clear()
     */
    public void clear () {
        setName("");
        setDescription("");
        List<String> empty = Collections.emptyList();
        setSelectedSubjects(empty);
        setAvailableSubjects(empty); 
    }

    /**
	 * Sets the selected subjects.
	 * 
	 * @param subjects
	 *            the new selected subjects
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#setSelectedSubjects()
	 */
    @Override
    public void setSelectedSubjects(List<String> subjects) {
        ((ContentView)contentView).setSelectedSubjects(subjects);
    }


    /**
	 * Gets the search term.
	 * 
	 * @return the search term
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#getSearchTerm()
	 */
    @Override
    public String getSearchTerm() {
       return ((ContentView)contentView).getSearchTerm();
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#getSearchButton()
     */
    public HasClickHandlers getSearchButton() {
        return ((ContentView)contentView).getSearchButton();
    }


    /**
	 * Gets the apply button.
	 * 
	 * @return the apply button
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#getApplyButton()
	 */
    @Override
    public Button getApplyButton() {
        return ((ContentView)contentView).getSaveButton();
    }


    /**
	 * Gets the cancel button.
	 * 
	 * @return the cancel button
	 * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#getCancelButton()
	 */
    @Override
    public HasClickHandlers getCancelButton() {
        return ((ContentView)contentView).getCancelButton();
    }


    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#setSubjectGroupCalculator(java.lang.String)
     */
    @Override
    public void setSubjectGroupCalculator(String groupCalculator) {
        if(groupCalculator != null && !groupCalculator.isEmpty()){
            ((ContentView)contentView).isCalculatedSg.setValue(true);
            ((ContentView)contentView).fillCalculatorList(((ContentView)contentView).sgCalculators);
        }
    }


    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#setSgCalculatorMap(java.util.Map)
     */
    @Override
    public void setSgCalculatorMap(Map<String, String> values) {
        ((ContentView)contentView).sgCalculatorMap = values;
    }


    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#setSelectedType(java.lang.String)
     */
    @Override
    public void setSelectedType(String type) {
        ((ContentView)contentView).setSelectedType(type);
    }


    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.SubjectGroupEditPresenter.SubjectGroupEditDisplay#getGroupCalculator()
     */
    @Override
    public String getGroupCalculator() {
        return ((ContentView)contentView).getSelectedSubjectGroupCalculatorName();
    }
    
}
