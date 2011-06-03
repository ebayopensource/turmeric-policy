/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.List;

/**
 * Subject.
 */
public interface Subject {
    
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
	 * Gets the external subject id.
	 * 
	 * @return the external subject id
	 */
    long getExternalSubjectId();
    
    /**
	 * Gets the subject match types.
	 * 
	 * @return the subject match types
	 */
    List<SubjectMatchType> getSubjectMatchTypes();
    
    /**
	 * Gets the created by.
	 * 
	 * @return the created by
	 */
    String getCreatedBy();
    
    /**
	 * Gets the last modified time.
	 * 
	 * @return the last modified time
	 */
    long getLastModifiedTime();
    
    /**
	 * Gets the last modified by.
	 * 
	 * @return the last modified by
	 */
    String getLastModifiedBy();
}
