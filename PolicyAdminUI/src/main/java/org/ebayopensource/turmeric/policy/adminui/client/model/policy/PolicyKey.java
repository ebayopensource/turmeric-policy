/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;



/**
 * PolicyKey.
 */
public class PolicyKey {
    private String name;
    private Long id;
    private String type;
    
    /**
	 * Gets the name.
	 * 
	 * @return the name
	 */
    public String getName() {
        return name;
    }
    
    /**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    public Long getId() {
        return id;
    }
    
    /**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
	 * Gets the type.
	 * 
	 * @return the type
	 */
    public String getType() {
        return type;
    }
    
    /**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
    public void setType(String type) {
        this.type = type;
    }
}
