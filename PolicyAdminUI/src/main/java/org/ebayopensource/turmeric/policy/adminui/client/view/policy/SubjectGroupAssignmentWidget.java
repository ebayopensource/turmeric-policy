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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * SubjectGroupAssigmentWidget
 *
 * Assign Subjects to a SubjectGroup.
 * If the Subject Group already exists, the Subject Type will be set, and this
 * widget will allow Subjects of that type to be added or subtracted from
 * the Group. If the Subject Type is not set, this widget allows it to be selected,
 * and then Subjects of that Type to be be added or subtracted from the Group.
 */
public class SubjectGroupAssignmentWidget extends Composite {
    
    /** The subject type. */
    protected String subjectType;
    
    /** The subject type box. */
    protected ListBox subjectTypeBox;
    
    /** The subject type label. */
    protected Label subjectTypeLabel;
    
    /** The subject name label. */
    protected Label subjectNameLabel;
    
    /** The subject caption. */
    protected CaptionPanel subjectCaption;
    
    /** The search box. */
    protected TextBox searchBox;
    
    /** The search button. */
    protected Button searchButton;
    
    /** The panel. */
    protected FlowPanel panel;
    
    /** The table. */
    protected FlexTable table;
    
    /** The subject table. */
    protected FlexTable subjectTable;

    /** The subject group table. */
    protected FlexTable subjectGroupTable;
    
    /** The add subject button. */
    protected Button addSubjectButton;
    
    /** The del subject button. */
    protected Button delSubjectButton;
    
    /** The select boxes. */
    protected SelectBoxesWidget selectBoxes;


    /**
     * Constructor.
     * A SubjectType has not been selected at the time of the creation of the Widget.
     * The Widget will allow one to be selected, along with the Subjects of that SubjectType.
     * 
     */
    public SubjectGroupAssignmentWidget ()
    {
        panel = new FlowPanel();
        panel.addStyleName("subject-assignment-panel");
        table = new FlexTable();  
        table.setWidth("100%");
        
        subjectTable = new FlexTable();  
        subjectTable.setWidth("100%");
         
        subjectGroupTable = new FlexTable();  
        subjectGroupTable.setWidth("100%");

        panel.add(table);   
        createFields();
        positionFields();
        initWidget(panel);
    }

 

    /**
	 * Gets the selected subjects.
	 * 
	 * @return the selected subjects
	 */
    public List<String> getSelectedSubjects () {
        return selectBoxes.getSelections();
    }
    
    /**
	 * Clear.
	 */
    public void clear () {
        List<String> emptyList = Collections.emptyList();
        setAvailableSubjects(emptyList);
        setSelectedSubjects(emptyList);
        searchBox.setText("");
    }
    
   
	
    /**
	 * Gets the subject type.
	 * 
	 * @return the subject type
	 */
    public String getSubjectType () {
        int index = subjectTypeBox.getSelectedIndex();
        if (index < 0)
            return null;
        
        return subjectTypeBox.getItemText(index);
    }
    
    /**
	 * Gets the search term.
	 * 
	 * @return the search term
	 */
    public String getSearchTerm () {
        return searchBox.getText();
    }
     
    /**
	 * Gets the search box.
	 * 
	 * @return the search box
	 */
    public HasValue<String> getSearchBox () {
        return searchBox;
    }
    
    /**
	 * Gets the search button.
	 * 
	 * @return the search button
	 */
    public HasClickHandlers getSearchButton() {
        return searchButton;
    }
    
    /**
	 * Sets the available subjects.
	 * 
	 * @param availableSubjects
	 *            the new available subjects
	 */
    public void setAvailableSubjects (List<String> availableSubjects) {
        selectBoxes.setAvailables(availableSubjects);
    }
    
    /**
	 * Sets the selected subjects.
	 * 
	 * @param selectedSubjects
	 *            the new selected subjects
	 */
    public void setSelectedSubjects (List<String> selectedSubjects) {
        selectBoxes.setSelections(selectedSubjects);
    }
    
    
    /**
	 * Sets the available subject types.
	 * 
	 * @param availableSubjectTypes
	 *            the new available subject types
	 */
    public void setAvailableSubjectTypes (List<String> availableSubjectTypes) {
        //enable the selection of a subject type
        subjectTypeBox.clear();
        subjectTypeBox.setVisible(true);
        subjectTypeLabel.setVisible(true);
        subjectCaption.setVisible(true);

        subjectNameLabel.setVisible(true);
        if(availableSubjectTypes != null){
        	for (String s:availableSubjectTypes)
            subjectTypeBox.addItem(s);
        }
        List<String> emptyList = Collections.emptyList();
        setSelectedSubjects(emptyList);
        setAvailableSubjects(emptyList);
    }
    
    /**
	 * Creates the fields.
	 */
    protected void createFields () {
        createSubjectTypeFields();
        createSubjectCaption();
       
    }
    
    /**
	 * Position fields.
	 */
    protected void positionFields () {
        // subjectTypeBox add it first
        positionSubjectTypeFields();
        positionSubjectCaption();
        table.setWidget(1, 0, subjectCaption);

    }
   
    private void positionSubjectCaption(){
    	//position the search box
        positionSearchFields();
        
        //position the subject boxes
        positionSubjectsFields();
        
    }
    
    
    private void positionSubjectTypeFields () {  
    	Grid searchGrid = new Grid(1,3);
    	
    	searchGrid.setWidget(0, 0, subjectTypeLabel);
    	searchGrid.setWidget(0, 1, subjectTypeBox);   
    	table.setWidget(0, 0, searchGrid);
     
    }
    
    private void createSubjectTypeFields () {
        subjectTypeBox = new ListBox(false);
        subjectTypeLabel = new Label(PolicyAdminUIUtil.policyAdminConstants.subjectType()+":");
        subjectTypeBox.setVisible(false);
        subjectTypeLabel.setVisible(false);
    }
    
    private void createSubjectsFields () {
        selectBoxes = new SelectBoxesWidget(PolicyAdminUIUtil.policyAdminConstants.availableSubjects(), true, PolicyAdminUIUtil.policyAdminConstants.selectedSubjects(), true);
    }
    
    
    private void positionSubjectsFields () {
    	subjectTable.setWidget(1, 0, selectBoxes);
    	subjectTable.getFlexCellFormatter().setColSpan(1, 0, 3);

    }
    
    private void createSearchFields (){
        searchBox = new TextBox();
        searchButton = new Button(PolicyAdminUIUtil.policyAdminConstants.search());
    }
    
    private void createSubjectCaption(){
    	createSearchFields();
        createSubjectsFields();
        subjectCaption = new CaptionPanel("Subjects");
        subjectCaption.setContentWidget(subjectTable);	    
    }
    
   
    private void positionSearchFields() {
    	subjectNameLabel = new Label(PolicyAdminUIUtil.policyAdminConstants.subjectName()+":");
    	subjectTable.setWidget(0,0, subjectNameLabel);
    	subjectTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
    	subjectTable.getCellFormatter().setHorizontalAlignment(1,0,HasHorizontalAlignment.ALIGN_CENTER);
    	
    	subjectTable.setWidget(0,1,searchBox);
    	subjectTable.setWidget(0,2, searchButton);
     }
}
