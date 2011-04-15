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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;


/**
 * PolicySubjectAssignmentWidget
 *
 */
public class PolicySubjectAssignmentWidget extends SubjectGroupAssignmentWidget {

    protected CaptionPanel subjectGroupCaption;
    protected Label subjectGroupNameLabel;

    protected SelectBoxesWidget selectGroups;
    protected TextBox searchGroupBox;
    protected Button searchGroupButton;
    protected SelectBoxesWidget selectExclusionSubjects;
    protected SelectBoxesWidget selectExclusionSG;
    
    public PolicySubjectAssignmentWidget() {
        super();
    }
    
    public void setExclusionListVisible(boolean visible){
    	selectExclusionSubjects.setVisible(visible);
    	selectExclusionSG.setVisible(visible);
    }
    
    public List<String> getSelectedGroups () {
        return selectGroups.getSelections();
    }
    
    public HasClickHandlers getGroupSearchButton () {
        return searchGroupButton;
    }
    
    public String getGroupSearchTerm () {
        return searchGroupBox.getText();
    }
    
    public void setAvailableSubjectTypes (List<String> availableSubjectTypes) {
        super.setAvailableSubjectTypes(availableSubjectTypes);
        List<String> emptyList = Collections.emptyList();
        setSelectedGroups(emptyList);
        setAvailableGroups(emptyList);
        subjectGroupCaption.setVisible(true);

    }
    
    public void setAvailableGroups(List<String> availableGroups) {
        selectGroups.setAvailables(availableGroups);
        
    }
    
    
    
    public void setSelectedGroups(List<String> selectedGroups) {
        selectGroups.setSelections(selectedGroups);
    }
    
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
    
    protected void createFields() {
        super.createFields();
        createExclusionSubjectFields();

        createSubjectGroupCaption();
    }
    
    protected void positionFields () {
        super.positionFields();
        positionExclusionSubjectFields();
        positionSubjectGroupCaption();
        table.setWidget(1, 1, subjectGroupCaption);
        
    }
    
    protected void positionSubjectGroupCaption(){
    	positionGroupSearchFields();
        positionGroupsFields();
        positionExclusionSGFields();
    }
    
    protected void positionGroupSearchFields() {
        subjectGroupNameLabel = new Label(PolicyAdminUIUtil.policyAdminConstants.subjectGroupName()+":");
    	subjectGroupTable.setWidget(0,0, subjectGroupNameLabel);
    	subjectGroupTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
    	subjectGroupTable.setWidget(0,1,searchGroupBox);
    	subjectGroupTable.setWidget(0,2, searchGroupButton);
    
    }
    
    protected void createSubjectGroupCaption(){
    	createGroupSearchFields();
        createGroupsFields();
        createExclusionSGFields();
       	subjectGroupCaption = new CaptionPanel("Subject Groups");
        subjectGroupCaption.setContentWidget(subjectGroupTable);	
    
    }

    protected void createGroupSearchFields () {
        searchGroupBox = new TextBox();
        searchGroupButton = new Button(PolicyAdminUIUtil.policyAdminConstants.search());
    }
    
    
    protected void positionGroupsFields() {
        subjectGroupTable.setWidget(1, 0, selectGroups);
        subjectGroupTable.getFlexCellFormatter().setColSpan(1, 0, 3);                
    }
    
    protected void positionExclusionSubjectFields() {
    	 subjectTable.setWidget(2, 0, selectExclusionSubjects);
    	 subjectTable.getFlexCellFormatter().setColSpan(2, 0, 3);         
    }
   
    protected void positionExclusionSGFields() {
    	subjectGroupTable.setWidget(2, 0, selectExclusionSG);
    	subjectGroupTable.getFlexCellFormatter().setColSpan(2, 0, 3);         
   }
    
    /**
     * 
     */
    protected void createGroupsFields() {
        selectGroups = new SelectBoxesWidget(PolicyAdminUIUtil.policyAdminConstants.availableSubjectGroups(), true, PolicyAdminUIUtil.policyAdminConstants.selectedSubjectGroups(), true);
    }

    protected void createExclusionSubjectFields() {
        selectExclusionSubjects = new SelectBoxesWidget(PolicyAdminUIUtil.policyAdminConstants.availableSubjects(), true, PolicyAdminUIUtil.policyAdminConstants.selectedExclusionSubject(), true);
    }

    protected void createExclusionSGFields() {
        selectExclusionSG = new SelectBoxesWidget(PolicyAdminUIUtil.policyAdminConstants.availableSubjectGroups(), true, PolicyAdminUIUtil.policyAdminConstants.selectedExclusionSG(), true);
    }

    
    public List<String> getSelectedExclusionSubjects () {
        return selectExclusionSubjects.getSelections();
    }
    
   
    public List<String> getSelectedExclusionSG() {
        return selectExclusionSG.getSelections();
    }
    
    public void setAvailableExclusionSubjects(final List<String> availableExclusionSubjects) {
    	selectExclusionSubjects.setAvailables(availableExclusionSubjects);
    }
 
    public void setAvailableExclusionSG(final List<String> availableExclusionSG) {
    	selectExclusionSG.setAvailables(availableExclusionSG);
    }
    
    public void setSelectedExclusionSubjects(final List<String> availableExclusionSubjects) {
    	selectExclusionSubjects.setSelections(availableExclusionSubjects);
    }
    
    public void setSelectedExclusionSG(final List<String> availableExclusionSG) {
    	selectExclusionSG.setSelections(availableExclusionSG);
    }
}
