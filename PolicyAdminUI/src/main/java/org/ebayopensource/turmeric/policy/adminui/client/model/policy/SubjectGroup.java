/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.Date;
import java.util.List;



/**
 * SubjectGroup.
 */
public interface SubjectGroup {
    
    /**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    Long getId();
    
    /**
	 * Gets the description.
	 * 
	 * @return the description
	 */
    String getDescription();
    
    /**
	 * Gets the type.
	 * 
	 * @return the type
	 */
    String getType();
    
    /**
	 * Gets the name.
	 * 
	 * @return the name
	 */
    String getName();
    
    /**
	 * Gets the last modified by.
	 * 
	 * @return the last modified by
	 */
    String getLastModifiedBy();
    
    /**
	 * Gets the subject match types.
	 * 
	 * @return the subject match types
	 */
    List<SubjectMatchType> getSubjectMatchTypes();

    /**
	 * Gets the last modified time.
	 * 
	 * @return the last modified time
	 */
    Date getLastModifiedTime();
    
    /**
	 * Gets the created by.
	 * 
	 * @return the created by
	 */
    String getCreatedBy();
    
    /**
	 * Gets the subjects.
	 * 
	 * @return the subjects
	 */
    List<String> getSubjects();
    
    /**
	 * Gets the policies.
	 * 
	 * @return the policies
	 */
    List<String> getPolicies();
    
    /**
	 * Gets the group calculator.
	 * 
	 * @return the group calculator
	 */
    String getGroupCalculator();
}
