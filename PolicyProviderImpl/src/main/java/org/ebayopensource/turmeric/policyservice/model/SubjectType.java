/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.policyservice.model;

import javax.persistence.Entity;

import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class SubjectType.
 */
@Entity
public class SubjectType extends AuditablePersistent {
    private String name;
    private String description;
    private boolean external;
    
    /**
	 * Instantiates a new subject type.
	 */
    public SubjectType() {}
    
    /**
	 * Instantiates a new subject type.
	 * 
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 * @param external
	 *            the external
	 */
    public SubjectType(String name, String description, boolean external)
    {
        this.name = name;
        this.description = description;
        this.external = external;
    }
    
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
	 * Gets the description.
	 * 
	 * @return the description
	 */
    public String getDescription() {
        return description;
    }
    
    /**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
	 * Checks if is external.
	 * 
	 * @return true, if is external
	 */
    public boolean isExternal() {
        return external;
    }
    
    /**
	 * Sets the external.
	 * 
	 * @param external
	 *            the new external
	 */
    public void setExternal(boolean external) {
        this.external = external;
    }
}
