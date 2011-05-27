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
 * SubjectGroupKey.
 */
public class SubjectGroupKey {
    private Long id;
    private String name;
    private String type;
    
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
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals (Object o) {
        if (!(o instanceof SubjectGroupKey))
            return false;
        SubjectGroupKey k = ((SubjectGroupKey)o);
        if (id != null && (k.getId() != null && k.getId().equals(id)))
            return true;
        
        if (name != null && (k.getName() != null && name.equals(k.getName()))) {
            if (type != null && (k.getType() != null && type.equals(k.getType())))
                return true;
        }
        
        return false;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode () {
        int hash = 1;
        if (id != null)
            hash = hash*31 +id.hashCode();
        if (name != null)
            hash = hash*31 +name.hashCode();
        if (type != null)
            hash = hash*31 +type.hashCode();
        return hash;
    }
}
