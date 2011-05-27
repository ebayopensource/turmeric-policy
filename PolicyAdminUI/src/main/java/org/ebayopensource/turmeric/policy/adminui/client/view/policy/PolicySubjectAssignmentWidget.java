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

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.SelectBoxesWidget;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;


/**
 * PolicySubjectAssignmentWidget.
 */
public class PolicySubjectAssignmentWidget extends SubjectGroupAssignmentWidget {

    /** The subject group caption. */
    protected CaptionPanel subjectGroupCaption;
    
    /** The subject group name label. */
    protected Label subjectGroupNameLabel;

    /** The select groups. */
    protected SelectBoxesWidget selectGroups;
    
    /** The search group box. */
    protected TextBox searchGroupBox;
    
    /** The search group button. */
    protected Button searchGroupButton;
    
    /** The select exclusion subjects. */
    protected SelectBoxesWidget selectExclusionSubjects;
    
    /** The select exclusion sg. */
    protected SelectBoxesWidget selectExclusionSG;
    
    /** The select all subjects xb. */
    protected CheckBox selectAllSubjectsXB;
    
    /**
	 * Instantiates a new policy subject assignment widget.
	 */
    public PolicySubjectAssignmentWidget() {
        super();
    }
    
    /**
	 * Sets the exclusion list visible.
	 * 
	 * @param visible
	 *            the new exclusion list visible
	 */
    public void setExclusionListVisible(boolean visible){
    	selectExclusionSubjects.setVisible(visible);
    	selectExclusionSG.setVisible(visible);
    	selectAllSubjectsXB.setVisible(visible);
    }
    
    /**
	 * Gets the selected groups.
	 * 
	 * @return the selected groups
	 */
    public List<String> getSelectedGroups () {
        return selectGroups.getSelections();
    }
    
    /**
	 * Gets the group search button.
	 * 
	 * @return the group search button
	 */
    public HasClickHandlers getGroupSearchButton () {
        return searchGroupButton;
    }
    
    /**
	 * Gets the group search term.
	 * 
	 * @return the group search term
	 */
    public String getGroupSearchTerm () {
        return searchGroupBox.getText();
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.SubjectGroupAssignmentWidget#setAvailableSubjectTypes(java.util.List)
     */
    public void setAvailableSubjectTypes (List<String> availableSubjectTypes) {
        super.setAvailableSubjectTypes(availableSubjectTypes);
        List<String> emptyList = Collections.emptyList();
        setSelectedGroups(emptyList);
        setAvailableGroups(emptyList);
        subjectGroupCaption.setVisible(true);

    }
    
    /**
	 * Sets the available groups.
	 * 
	 * @param availableGroups
	 *            the new available groups
	 */
    public void setAvailableGroups(List<String> availableGroups) {
        selectGroups.setAvailables(availableGroups);
        
    }
    
    
    
    /**
	 * Sets the selected groups.
	 * 
	 * @param selectedGroups
	 *            the new selected groups
	 */
    public void setSelectedGroups(List<String> selectedGroups) {
        selectGroups.setSelections(selectedGroups);
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.SubjectGroupAssignmentWidget#clear()
     */
    public void clear () {
        super.clear();
        List<String> emptyList = Collections.emptyList();
        setAvailableGroups(emptyList);
        setSelectedGroups(emptyList);
        setAvailableExclusionSG(emptyList);
        setSelectedExclusionSG(emptyList);
        
        setAvailableSubjects(emptyList);
        setSelectedSubjects(emptyList);
        setAvailableExclusionSubjects(emptyList);
        setSelectedExclusionSubjects(emptyList);
        searchGroupBox.setText("");
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.SubjectGroupAssignmentWidget#createFields()
     */
    protected void createFields() {
        super.createFields();
        createExclusionSubjectFields();

        createSubjectGroupCaption();
    }
    
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.policy.SubjectGroupAssignmentWidget#positionFields()
     */
    protected void positionFields () {
        super.positionFields();
        positionSubjectTypeXB();
        positionExclusionSubjectFields();
        positionSubjectGroupCaption();
        table.setWidget(1, 1, subjectGroupCaption);
        
    }
    
    /**
	 * Position subject type xb.
	 */
    protected void positionSubjectTypeXB(){
    	FlexTable table  = ((FlexTable)this.panel.getWidget(0));
    	Grid grid =((Grid) table.getWidget(0, 0));
    	grid.setWidget(0,2, selectAllSubjectsXB);
    }
    
    /**
	 * Position subject group caption.
	 */
    protected void positionSubjectGroupCaption(){
    	positionGroupSearchFields();
        positionGroupsFields();
        positionExclusionSGFields();
    }
    
    /**
	 * Position group search fields.
	 */
    protected void positionGroupSearchFields() {
        subjectGroupNameLabel = new Label(PolicyAdminUIUtil.policyAdminConstants.subjectGroupName()+":");
    	subjectGroupTable.setWidget(0,0, subjectGroupNameLabel);
    	subjectGroupTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
    	subjectGroupTable.setWidget(0,1,searchGroupBox);
    	subjectGroupTable.setWidget(0,2, searchGroupButton);
    
    }
    
    /**
	 * Creates the subject group caption.
	 */
    protected void createSubjectGroupCaption(){
    	createGroupSearchFields();
        createGroupsFields();
        createExclusionSGFields();
        createSubjectTypeXB();
       	subjectGroupCaption = new CaptionPanel("Subject Groups");
        subjectGroupCaption.setContentWidget(subjectGroupTable);	
    
    }

    /**
	 * Creates the group search fields.
	 */
    protected void createGroupSearchFields () {
        searchGroupBox = new TextBox();
        searchGroupButton = new Button(PolicyAdminUIUtil.policyAdminConstants.search());
    }
    
    
    /**
	 * Position groups fields.
	 */
    protected void positionGroupsFields() {
        subjectGroupTable.setWidget(1, 0, selectGroups);
        subjectGroupTable.getFlexCellFormatter().setColSpan(1, 0, 3);   
        subjectGroupTable.getCellFormatter().setHorizontalAlignment(1,0,HasHorizontalAlignment.ALIGN_CENTER);

    }
    
    /**
	 * Position exclusion subject fields.
	 */
    protected void positionExclusionSubjectFields() {
    	 subjectTable.setWidget(2, 0, selectExclusionSubjects);
    	 subjectTable.getFlexCellFormatter().setColSpan(2, 0, 3);
    	 subjectTable.getCellFormatter().setHorizontalAlignment(2,0,HasHorizontalAlignment.ALIGN_CENTER);
    }
   
    /**
	 * Position exclusion sg fields.
	 */
    protected void positionExclusionSGFields() {
    	subjectGroupTable.setWidget(2, 0, selectExclusionSG);
    	subjectGroupTable.getFlexCellFormatter().setColSpan(2, 0, 3);         
    	subjectGroupTable.getCellFormatter().setHorizontalAlignment(2,0,HasHorizontalAlignment.ALIGN_CENTER);
   }
    
    /**
	 * Creates the groups fields.
	 */
    protected void createGroupsFields() {
        selectGroups = new SelectBoxesWidget(PolicyAdminUIUtil.policyAdminConstants.availableSubjectGroups(), true, PolicyAdminUIUtil.policyAdminConstants.selectedSubjectGroups(), true);
    }

    /**
	 * Creates the exclusion subject fields.
	 */
    protected void createExclusionSubjectFields() {
        selectExclusionSubjects = new SelectBoxesWidget(PolicyAdminUIUtil.policyAdminConstants.availableSubjects(), true, PolicyAdminUIUtil.policyAdminConstants.selectedExclusionSubject(), true);
    }

    /**
	 * Creates the exclusion sg fields.
	 */
    protected void createExclusionSGFields() {
        selectExclusionSG = new SelectBoxesWidget(PolicyAdminUIUtil.policyAdminConstants.availableSubjectGroups(), true, PolicyAdminUIUtil.policyAdminConstants.selectedExclusionSG(), true);
    }

    /**
	 * Creates the subject type xb.
	 */
    protected void createSubjectTypeXB() {
        selectAllSubjectsXB = new CheckBox(PolicyAdminUIUtil.policyAdminConstants.selectAllSubjects() + " " + PolicyAdminUIUtil.policyAdminConstants.selectAllsubjectsAlert());
    }
    
    /**
	 * Gets the selected exclusion subjects.
	 * 
	 * @return the selected exclusion subjects
	 */
    public List<String> getSelectedExclusionSubjects () {
        return selectExclusionSubjects.getSelections();
    }
    
    /**
	 * Gets the select all subjects xb.
	 * 
	 * @return the select all subjects xb
	 */
    public boolean getSelectAllSubjectsXB() {
        return selectAllSubjectsXB.getValue().booleanValue();
    }
    
    /**
	 * Sets the select all subjects xb.
	 * 
	 * @param selected
	 *            the new select all subjects xb
	 */
    public void setSelectAllSubjectsXB(boolean selected) {
        selectAllSubjectsXB.setValue(new Boolean(selected));
    }
   
    /**
	 * Gets the selected exclusion sg.
	 * 
	 * @return the selected exclusion sg
	 */
    public List<String> getSelectedExclusionSG() {
        return selectExclusionSG.getSelections();
    }
    
    /**
	 * Sets the available exclusion subjects.
	 * 
	 * @param availableExclusionSubjects
	 *            the new available exclusion subjects
	 */
    public void setAvailableExclusionSubjects(final List<String> availableExclusionSubjects) {
    	selectExclusionSubjects.setAvailables(availableExclusionSubjects);
    }
 
    /**
	 * Sets the available exclusion sg.
	 * 
	 * @param availableExclusionSG
	 *            the new available exclusion sg
	 */
    public void setAvailableExclusionSG(final List<String> availableExclusionSG) {
    	selectExclusionSG.setAvailables(availableExclusionSG);
    }
    
    /**
	 * Sets the selected exclusion subjects.
	 * 
	 * @param availableExclusionSubjects
	 *            the new selected exclusion subjects
	 */
    public void setSelectedExclusionSubjects(final List<String> availableExclusionSubjects) {
    	selectExclusionSubjects.setSelections(availableExclusionSubjects);
    }
    
    /**
	 * Sets the selected exclusion sg.
	 * 
	 * @param availableExclusionSG
	 *            the new selected exclusion sg
	 */
    public void setSelectedExclusionSG(final List<String> availableExclusionSG) {
    	selectExclusionSG.setSelections(availableExclusionSG);
    }
}
