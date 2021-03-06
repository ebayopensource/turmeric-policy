/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.List;


/**
 * The Interface Resource.
 */
public interface Resource {
    
    /**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    Long getId();
    
    /**
	 * Gets the resource type.
	 * 
	 * @return the resource type
	 */
    String getResourceType();
    
    /**
	 * Gets the resource name.
	 * 
	 * @return the resource name
	 */
    String getResourceName();
    
    /**
	 * Gets the description.
	 * 
	 * @return the description
	 */
    String getDescription();
    
    /**
	 * Gets the op list.
	 * 
	 * @return the op list
	 */
    List<Operation> getOpList();
    
    
}