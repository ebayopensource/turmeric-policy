/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client;

import com.google.gwt.i18n.client.Constants;

/**
 * The Interface PolicyAdminConstants.
 */
public interface PolicyAdminConstants extends Constants {
    
    /**
	 * Sg calculator.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("SubjectGroup Calculator")
    String sgCalculator();
    
    /**
	 * Calculated.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Calculated")
    String calculated();
    
    /**
	 * Subject criteria.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Subject")
    String subjectCriteria();

    /**
	 * Subject group criteria.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Subject Group")
    String subjectGroupCriteria();
    
    /**
	 * Policy criteria.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Policy")
    String policyCriteria();

    /**
	 * Resource criteria.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Resource")
    String resourceCriteria();
    
    /**
	 * Subjects and subject groups.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Subjects and Subject Groups")
    String subjectsAndSubjectGroups();
    
    /**
	 * Assign subjects and subject groups.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Assign Subjects and Subject Groups")
    String assignSubjectsAndSubjectGroups();
    
    /**
	 * Assign resources.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Assign Resources")
    String assignResources();
    
    /**
	 * Condition builder.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Condition Builder")
    String conditionBuilder();
     
    /**
	 * Search.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Search")
    String search();
    
    /**
	 * Search groups.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Search Groups")
    String searchGroups();
    
    /**
	 * Search subjects.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Search Subjects")
    String searchSubjects();

    /**
	 * Available operations.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Available Operations")
    String availableOperations();

    /**
	 * Selected operations.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Selected Operations")
    String selectedOperations();
    
    
    /**
	 * Available subjects.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Available Subjects")
    String availableSubjects();
    
    /**
	 * Available subject groups.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Available Subject Groups")
    String availableSubjectGroups();
    
	/**
	 * Subject group.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Subject Group")
	String subjectGroup();
	
	/**
	 * Subject groups.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Subject Groups")
	String subjectGroups();
	
	/**
	 * Selected subject groups.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Selected Subject Groups")
	String selectedSubjectGroups();
	
	/**
	 * Selected exclusion subject.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Selected Exclusion Subject")
	String selectedExclusionSubject();

	/**
	 * Selected exclusion sg.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Selected Exclusion Subject Groups")
	String selectedExclusionSG();
	
	/**
	 * Subject type.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Subject Type")
	String subjectType();

	/**
	 * Subject name.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Subject Name")
	String subjectName();
	
	/**
	 * Subject group description.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Subject Group Description")
	String subjectGroupDescription();
	
	/**
	 * Subject group name.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Subject Group Name")
	String subjectGroupName();
	
	/**
	 * Selected subjects.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Selected Subjects")
	String selectedSubjects();
	
	/**
	 * Select all subjects.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Select All Subjects")
	String selectAllSubjects();
	
	/**
	 * Subjects.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Subjects")
	String subjects();
	
	/**
	 * Exclusion subjects.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Exclusion Subjects")
	String exclusionSubjects();
	
	/**
	 * Exclusion subject groups.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Exclusion Subject Groups")
	String exclusionSubjectGroups();
	
	/**
	 * Subjects assigned.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Subjects Assigned")
	String subjectsAssigned();
	
	/**
	 * Policies assigned.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Policies Assigned")
	String policiesAssigned();
	
	/**
	 * Created by.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Created By")
	String createdBy();

	/**
	 * Last modified by.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Last Modified By")
	String lastModifiedBy();

	/**
	 * Last modified time.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Last Modified Time")
	String lastModifiedTime();
	
	/**
	 * Creates the time.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Create Time")
	String createTime();

	/**
	 * Actions.
	 * 
	 * @return the string
	 */
	@DefaultStringValue ("Actions")
	String actions();
	
	/**
	 * Creates the.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Create")
	String create();
	
	/**
	 * Import action.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Import")
	String importAction();
	
	/**
	 * Import policy action.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Import Policy")
	String importPolicyAction();
	
	/**
	 * Import sg action.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Import Subject Group")
	String importSGAction();
	
	/**
	 * From.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("from")
	String from();
	
	/**
	 * Import conditional file msg.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("<< Selected file must be in XML format >>")
	String importConditionalFileMsg();
	
	/**
	 * All policies.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("All Policies")
	String allPolicies();

	/**
	 * All.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("All")
	String all();

	
	/**
	 * Summary.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Summary")
	String summary();
	
	/**
	 * Authz policy.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Authorization Policy")
	String authzPolicy();
	
	/**
	 * Rate limiting policy.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Rate Limiting Policy")
	String rateLimitingPolicy();
	
	/**
	 * Bl policy.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Blacklist Policy")
	String blPolicy();
	
	/**
	 * Wl policy.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Whitelist Policy")
	String wlPolicy();
	
	/**
	 * Change history.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Change History")
	String changeHistory();

	/**
	 * Policy name.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Policy Name")
	String policyName();
	
	/**
	 * Policy description.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Policy Description")
	String policyDescription();
	
	/**
	 * Policy type.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Policy Type")
	String policyType();

	/**
	 * Policy based email address.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Policy Based Email Address")
	String policyBasedEmailAddress();

	/**
	 * Subject based email address.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Subject Based Email Address")
	String subjectBasedEmailAddress();

	/**
	 * Effect duration.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Effect Duration")
	String effectDuration();
	
	/**
	 * Rollovel period.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Rollover Period")
	String rollovelPeriod();

	/**
	 * Priority.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Priority")
	String priority();
	
	/**
	 * Effect.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Effect")
	String effect();
	
	/**
	 * Condition.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Condition")
	String condition();
	
	/**
	 * Field unit secs.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("(secs)")
	String fieldUnitSecs();
	
	/**
	 * Status.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Status")
	String status();
	
	/**
	 * View.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("View")
	String view();
	
	/**
	 * Enable.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Enable")
	String enable();
	
	/**
	 * Disable.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Disable")
	String disable();
	
	/**
	 * Submit trace ticket.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Submit a Trace Ticket")
	String submitTraceTicket();
	
	/**
	 * Delete.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Delete")
	String delete();
	
	/**
	 * Export.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Export")
	String export();
	
	/**
	 * Confirm.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Confirm")
	String confirm();
	
	/**
	 * Cancel.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Cancel")
	String cancel();
	
	/**
	 * Close.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Close")
	String close();

	/**
	 * Save.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Save")
	String save();

    /**
	 * Edits the.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Edit")
    String edit();
    
    /**
	 * Adds the.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Add")
    String add();
    
    /**
	 * Policy information authz edit.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Editing Authorization Policy")
    String policyInformationAuthzEdit();

    /**
	 * Policy information authz create.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("New Authorization Policy")
    String policyInformationAuthzCreate();

    /**
	 * Policy information bl edit.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Editing BlackList Policy")
    String policyInformationBLEdit();

    /**
	 * Policy information bl create.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("New Blacklist Policy")
    String policyInformationBLCreate();
    
    /**
	 * Policy information wl create.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("New Whitelist Policy")
    String policyInformationWLCreate();

    /**
	 * Policy information wl edit.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Editing WhiteList Policy")
    String policyInformationWLEdit();

    /**
	 * Policy information rl create.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("New Rate Limiting Policy")
    String policyInformationRLCreate();

    /**
	 * Policy information rl edit.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Editing Rate Limiting Policy")
    String policyInformationRLEdit();
    
    /**
	 * Policy information view.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("View Policy")
    String policyInformationView();
    
	/**
	 * Resources.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Resources")
    String resources();
 
	/**
	 * Service.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Service")
    String service();

	/**
	 * Resource name.
	 * 
	 * @return the string
	 */
	@DefaultStringValue("Resource Name")
    String resourceName();
    
    /**
	 * Operation name.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Operation Name")
    String operationName();
    
    /**
	 * Resource type.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Resource Type")
    String resourceType();
    
    /**
	 * Resource level.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Level")
    String resourceLevel();
    
    /**
	 * Resource assign resources.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Assign resources...")
    String resourceAssignResources();
    
    /**
	 * Subject assign subjects.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Assign subjects...")
    String subjectAssignSubjects();
    
    /**
	 * Operations.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Operations")
    String operations();
 
    /**
	 * Search criteria invalid.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Must select a searching criteria")
    String searchCriteriaInvalid();
    
    /**
	 * Delete subjet group invalid.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Cannot delete a subject group that is assigned to a policy")
    String deleteSubjetGroupInvalid();
 
    /**
	 * Populate all fiedls.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("All fields must be populated")
    String populateAllFiedls();
    
    /**
	 * Description.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Description")
    String description();
    
    /**
	 * Policies.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Policies")
    String policies();

    /**
	 * Policy administration.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Policy Administration")
    String policyAdministration();
 
    /*
     * Entity History View
     */
    /**
	 * Eh when column.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("When")
    String ehWhenColumn();

    /**
	 * Eh who column.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Who")
    String ehWhoColumn();

    /**
	 * Eh ip column.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Source IP")
    String ehIPColumn();

    /**
	 * Eh change type column.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Type of Change")
    String ehChangeTypeColumn();

    /**
	 * Eh comments column.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Comments")
    String ehCommentsColumn();
    
    /**
	 * Delete selected.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Delete selected items?")
    String deleteSelected();
    
    /**
	 * Creates the internal subjects.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("Subject not found. Do you want to create it?")
    String createInternalSubjects();

    /**
	 * Select allsubjects alert.
	 * 
	 * @return the string
	 */
    @DefaultStringValue("('Selected Subject' will not be saved)")
    String selectAllsubjectsAlert();
    
    /**
     * Generic Error message. This message should never be shown  unless 
     * that the error message received from server be improperly set 
     * @return the string
	 */
    @DefaultStringValue("Operation not valid. Please contact your site administrator.")
    String genericErrorMessage();
  
    /**
     * Generic message for no item found 
     * @return the string
	 */
    @DefaultStringValue("No items found")
    String noItemFoundMessage();
}
