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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

/**
 * PolicyResourceAssignmentWidget
 *
 * Assign resources to a Policy.
 */
public class PolicyResourceAssignmentWidget extends Composite {
    
	/** The resource level label. */
	protected Label resourceLevelLabel;
    
    /** The resource level box. */
    protected ListBox resourceLevelBox;
    
    /** The resource type label. */
    protected Label resourceTypeLabel;
    
    /** The resource type box. */
    protected ListBox resourceTypeBox;
    
    /** The resource name label. */
    protected Label resourceNameLabel;
    
    /** The resource name box. */
    protected ListBox resourceNameBox;
    
    /** The panel. */
    protected FlowPanel panel;
    
    /** The table. */
    protected FlexTable table;
    
    /** The add operation button. */
    protected Button addOperationButton;
    
    /** The del operation button. */
    protected Button delOperationButton;
    
    /** The select boxes. */
    protected SelectBoxesWidget selectBoxes;


    /**
	 * Instantiates a new policy resource assignment widget.
	 */
    public PolicyResourceAssignmentWidget()
    {
        panel = new FlowPanel();
        panel.addStyleName("resource-assignment-panel");
        table = new FlexTable();  
        table.setWidth("100%");
        panel.add(table);   
        createFields();
        positionFields();
        initWidget(panel);
    }

 

    /**
	 * Gets the selected operations.
	 * 
	 * @return the selected operations
	 */
    public List<String> getSelectedOperations() {
        return selectBoxes.getSelections();
    }
  
    /**
	 * Gets the available operations.
	 * 
	 * @return the available operations
	 */
    public List<String> getAvailableOperations() {
        return selectBoxes.getAvailables();
    }
    
    /**
	 * Gets the resource name label.
	 * 
	 * @return the resource name label
	 */
    public Label getResourceNameLabel() {
        return resourceNameLabel;
    }
    
    /**
	 * Gets the resource type label.
	 * 
	 * @return the resource type label
	 */
    public Label getResourceTypeLabel() {
        return resourceTypeLabel;
    }
    
    /**
	 * Gets the resource level label.
	 * 
	 * @return the resource level label
	 */
    public Label getResourceLevelLabel() {
        return resourceLevelLabel;
    }
    
    /**
	 * Gets the select boxes widget.
	 * 
	 * @return the select boxes widget
	 */
    public SelectBoxesWidget getSelectBoxesWidget(){
    	return selectBoxes;
    }
    
    /**
	 * Clear.
	 */
    public void clear () {
    	resourceLevelBox.clear();
        resourceLevelBox.setVisible(true);
        resourceLevelLabel.setVisible(true);
        
        List<String> emptyList = Collections.emptyList();
        setResourceTypes(emptyList);
        setResourceNames(emptyList);
        setSelectedOperations(emptyList);
        setAvailableOperations(emptyList);
        
        resourceLevelBox.setSelectedIndex(-1);
        
        resourceTypeBox.setSelectedIndex(-1);
        resourceTypeBox.setVisible(false);
        resourceTypeLabel.setVisible(false);
        
        resourceNameBox.setSelectedIndex(-1);
        resourceNameBox.setVisible(false);
        resourceNameLabel.setVisible(false);
        
        selectBoxes.setVisible(false);
        
    }
    
    
    /**
	 * Gets the resource type box.
	 * 
	 * @return the resource type box
	 */
    public ListBox getResourceTypeBox(){
    	return resourceTypeBox;	
    }
    

    /**
	 * Gets the resource level box.
	 * 
	 * @return the resource level box
	 */
    public ListBox getResourceLevelBox(){
    	return resourceLevelBox;	
    }
    
    /**
	 * Gets the resource name box.
	 * 
	 * @return the resource name box
	 */
    public ListBox getResourceNameBox(){
    	return resourceNameBox;	
    }
    
    
    /**
	 * Gets the resource type.
	 * 
	 * @return the resource type
	 */
    public String getResourceType() {
        int index = resourceTypeBox.getSelectedIndex();
        if (index < 0)
            return null;
        
        return resourceTypeBox.getItemText(index);
    }
    
    /**
	 * Gets the resource name.
	 * 
	 * @return the resource name
	 */
    public String getResourceName() {
        int index = resourceNameBox.getSelectedIndex();
        if (index < 0)
            return null;
        
        return resourceNameBox.getItemText(index);
    }
    
    /**
	 * Gets the resource level.
	 * 
	 * @return the resource level
	 */
    public String getResourceLevel() {
        int index = resourceLevelBox.getSelectedIndex();
        if (index < 0)
            return null;
        
        return resourceLevelBox.getItemText(index);
    }
    
    /**
	 * Sets the available operations.
	 * 
	 * @param availableOperations
	 *            the new available operations
	 */
    public void setAvailableOperations (List<String> availableOperations) {
        selectBoxes.setAvailables(availableOperations);
    }
    
    /**
	 * Sets the selected operations.
	 * 
	 * @param selectedOperations
	 *            the new selected operations
	 */
    public void setSelectedOperations (List<String> selectedOperations) {
        selectBoxes.setSelections(selectedOperations);
    }
    
    /**
	 * Sets the resource levels.
	 * 
	 * @param resourceLevels
	 *            the new resource levels
	 */
    public void setResourceLevels (List<String> resourceLevels) {
        //enable the selection of a resource level
        resourceLevelBox.clear();
        resourceLevelBox.setVisible(true);
        resourceLevelLabel.setVisible(true);
        for (String s:resourceLevels)
        	resourceLevelBox.addItem(s);
        List<String> emptyList = Collections.emptyList();
        setResourceTypes(emptyList);
        setResourceNames(emptyList);
        setSelectedOperations(emptyList);
        setAvailableOperations(emptyList);
    }
    
    /**
	 * Sets the resource types.
	 * 
	 * @param availableResourceTypes
	 *            the new resource types
	 */
    public void setResourceTypes (List<String> availableResourceTypes) {
        //enable the selection of a resource type
        resourceTypeBox.clear();
//        resourceTypeBox.setVisible(true);
//        resourceTypeLabel.setVisible(true);
        for (String s:availableResourceTypes)
        	resourceTypeBox.addItem(s);
        List<String> emptyList = Collections.emptyList();
        setResourceNames(emptyList);
        setSelectedOperations(emptyList);
        setAvailableOperations(emptyList);
    }
    
    /**
	 * Sets the resource names.
	 * 
	 * @param availableResourceNames
	 *            the new resource names
	 */
    public void setResourceNames (List<String> availableResourceNames) {
        //enable the selection of a resource type
        resourceNameBox.clear();
//        resourceNameBox.setVisible(true);
//        resourceNameLabel.setVisible(true);
        for (String s:availableResourceNames)
        	resourceNameBox.addItem(s);
        List<String> emptyList = Collections.emptyList();
        setSelectedOperations(emptyList);
        setAvailableOperations(emptyList);
    }
    
    /**
	 * Creates the fields.
	 */
    protected void createFields () {
        createResourceFields();
        createOperationFields();
    }
    
    /**
	 * Position fields.
	 */
    protected void positionFields () {
    	 // resourceLevelBox add it first
        positionResourceLevelFields();
    	
    	// resourceTypeBox 
        positionResourceTypeFields();
        
        // resourceNameBox
        positionResourceNameFields();
        
        //position the operation boxes
        positionOperationFields();
    }
   
    
  
    
    private void createResourceFields () {
       	resourceLevelBox = new ListBox(false);
        resourceLevelLabel = new Label(PolicyAdminUIUtil.policyAdminConstants.resourceLevel()+":");
        resourceLevelBox.setVisible(false);
        resourceLevelLabel.setVisible(false);
    	
    	resourceTypeBox = new ListBox(false);
        resourceTypeLabel = new Label(PolicyAdminUIUtil.policyAdminConstants.resourceType()+":");
        resourceTypeBox.setVisible(false);
        resourceTypeLabel.setVisible(false);
        
        resourceNameBox = new ListBox(false);
        resourceNameLabel = new Label(PolicyAdminUIUtil.policyAdminConstants.resourceName()+":");
        resourceNameBox.setVisible(false);
        resourceNameLabel.setVisible(false);
    }
    
    private void createOperationFields () {
        selectBoxes = new SelectBoxesWidget(PolicyAdminUIUtil.policyAdminConstants.availableOperations(), true, PolicyAdminUIUtil.policyAdminConstants.selectedOperations(), true);
        selectBoxes.setVisible(false);
        selectBoxes.setWithForOperations();
    }
  
    private void positionResourceLevelFields () {      
        table.setWidget(0, 0, resourceLevelLabel);
        table.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        table.getCellFormatter().setWordWrap(0, 0, false);
        table.setWidget(0, 1, resourceLevelBox);
    }
    
    private void positionResourceTypeFields () {      
        table.setWidget(1, 0, resourceTypeLabel);
        table.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        table.getCellFormatter().setWordWrap(0, 0, false);
        table.setWidget(1, 1, resourceTypeBox);
    }
    
    private void positionResourceNameFields () {      
        table.setWidget(2, 0, resourceNameLabel);
        table.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        table.getCellFormatter().setWordWrap(0, 0, false);
        table.setWidget(2, 1, resourceNameBox);
    }
    
    private void positionOperationFields () {
        table.setWidget(4, 0, selectBoxes);
        table.getFlexCellFormatter().setColSpan(4, 0, 4);
    }
    
   
}
