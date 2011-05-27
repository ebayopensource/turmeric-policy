/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * Operation.
 */
public interface Operation {
    
    /**
	 * Gets the description.
	 * 
	 * @return the description
	 */
    public String getDescription();
    
    /**
	 * Gets the operation name.
	 * 
	 * @return the operation name
	 */
    public String getOperationName();
    
    /**
	 * Gets the operation id.
	 * 
	 * @return the operation id
	 */
    public String getOperationId();
    
    /**
	 * Gets the creation date.
	 * 
	 * @return the creation date
	 */
    public long getCreationDate();
    
    /**
	 * Gets the creation by.
	 * 
	 * @return the creation by
	 */
    public String getCreationBy();
    
    /**
	 * Gets the last modified time.
	 * 
	 * @return the last modified time
	 */
    public long getLastModifiedTime();
    
    /**
	 * Gets the last modified by.
	 * 
	 * @return the last modified by
	 */
    public String getLastModifiedBy();
}
