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


/**
 * The Interface PrimitiveValue.
 */
public interface PrimitiveValue {
    
    /**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    public Long getId();
    
    /**
	 * Gets the type.
	 * 
	 * @return the type
	 */
    public SupportedPrimitive getType();
    
    /**
	 * Gets the value.
	 * 
	 * @return the value
	 */
    public String getValue();
}