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
 * SubjectQuery.
 */
public class SubjectQuery {

    private List<SubjectTypeKey> typeKeys;
    private List<SubjectKey> subjectKeys;
    private String query;
    
    /**
	 * The Class SubjectTypeKey.
	 */
    public static class SubjectTypeKey {
        private Long typeId;
        private String typeName;
        
        /**
		 * Gets the type id.
		 * 
		 * @return the type id
		 */
        public Long getTypeId() {
            return typeId;
        }
        
        /**
		 * Sets the type id.
		 * 
		 * @param typeId
		 *            the new type id
		 */
        public void setTypeId(Long typeId) {
            this.typeId = typeId;
        }
        
        /**
		 * Gets the type name.
		 * 
		 * @return the type name
		 */
        public String getTypeName() {
            return typeName;
        }
        
        /**
		 * Sets the type name.
		 * 
		 * @param typeName
		 *            the new type name
		 */
        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }

    /**
	 * Gets the type keys.
	 * 
	 * @return the type keys
	 */
    public List<SubjectTypeKey> getTypeKeys() {
        return typeKeys;
    }

    /**
	 * Sets the type keys.
	 * 
	 * @param typeKeys
	 *            the new type keys
	 */
    public void setTypeKeys(List<SubjectTypeKey> typeKeys) {
        this.typeKeys = typeKeys;
    }

    /**
	 * Gets the subject keys.
	 * 
	 * @return the subject keys
	 */
    public List<SubjectKey> getSubjectKeys() {
        return subjectKeys;
    }

    /**
	 * Sets the subject keys.
	 * 
	 * @param subjectKeys
	 *            the new subject keys
	 */
    public void setSubjectKeys(List<SubjectKey> subjectKeys) {
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
}
