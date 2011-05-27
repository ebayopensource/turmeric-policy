/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.List;

/**
 * SubjectGroupQuery.
 */
public class SubjectGroupQuery {
    private List<SubjectGroupKey> subjectKeys;
    private String query;
    private boolean includeSubjects;
    
    /**
	 * Gets the group keys.
	 * 
	 * @return the group keys
	 */
    public List<SubjectGroupKey> getGroupKeys() {
        return subjectKeys;
    }
    
    /**
	 * Sets the group keys.
	 * 
	 * @param subjectKeys
	 *            the new group keys
	 */
    public void setGroupKeys(List<SubjectGroupKey> subjectKeys) {
        this.subjectKeys = subjectKeys;
    }
    
    /**
	 * Gets the query.
	 * 
	 * @return the query
	 */
    public String getQuery() {
        return query;
    }
    
    /**
	 * Sets the query.
	 * 
	 * @param query
	 *            the new query
	 */
    public void setQuery(String query) {
        this.query = query;
    }
    
    /**
	 * Sets the include subjects.
	 * 
	 * @param includeSubjects
	 *            the new include subjects
	 */
    public void setIncludeSubjects(boolean includeSubjects) {
        this.includeSubjects = includeSubjects;
    }
    
    /**
	 * Checks if is include subjects.
	 * 
	 * @return true, if is include subjects
	 */
    public boolean isIncludeSubjects() {
        return includeSubjects;
    }
}
