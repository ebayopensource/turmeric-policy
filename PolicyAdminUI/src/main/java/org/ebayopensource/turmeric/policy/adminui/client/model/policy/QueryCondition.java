/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.ArrayList;
import java.util.List;

/**
 * QueryCondition.
 */
public class QueryCondition {
    
    /**
	 * The Enum ResolutionType.
	 */
    public static enum ResolutionType {AND, OR};
    
    /**
	 * The Enum PolicyQueryType.
	 */
    public static enum PolicyQueryType {Effect, ActivePoliciesOnly, SubjectSearchScope, ExpandResourceLevelPolicies};
    
    /**
	 * The Enum MetaDataQueryType.
	 */
    public static enum MetaDataQueryType {RESOURCE_TYPE, POLICY_TYPE, SUBJECT_TYPE};
    
    /**
	 * The Enum MetaDataQueryValue.
	 */
    public static enum MetaDataQueryValue {Type};
    
    /**
	 * The Enum EffectValue.
	 */
    public static enum EffectValue {BLOCK, FLAG, CHALLENGE, ALLOW};
    
    /**
	 * The Enum ActivePoliciesOnlyValue.
	 */
    public static enum ActivePoliciesOnlyValue {TRUE, FALSE};
    
    /**
	 * The Enum SubjectSearchScopeValue.
	 */
    public static enum SubjectSearchScopeValue {TARGET, EXCLUDED, BOTH};
    
    /**
	 * The Enum ExpandResourceLevelPoliciesValue.
	 */
    public static enum ExpandResourceLevelPoliciesValue {TRUE, FALSE};
 
    
    private ResolutionType resolution;
    private List<Query> queries = new ArrayList<Query>();
    
    
    
    /**
	 * The Class Query.
	 */
    public static class Query { 
        
        /** The type. */
        protected String type;
        
        /** The value. */
        protected String value;
 
        /**
		 * Instantiates a new query.
		 * 
		 * @param key
		 *            the key
		 * @param value
		 *            the value
		 */
        public Query (String key, String value) {
            this.type = key;
            this.value = value;
        }
    
        /**
		 * Instantiates a new query.
		 * 
		 * @param type
		 *            the type
		 * @param value
		 *            the value
		 */
        public Query (MetaDataQueryType type, MetaDataQueryValue value) {
            this.type = type.toString();
            this.value = value.toString();
        }
        
        /**
		 * Instantiates a new query.
		 * 
		 * @param value
		 *            the value
		 */
        public Query (EffectValue value)
        {
            this.type=PolicyQueryType.Effect.toString();
            this.value=value.toString();
        }
        
        /**
		 * Instantiates a new query.
		 * 
		 * @param value
		 *            the value
		 */
        public Query (ActivePoliciesOnlyValue value)
        {
            this.type=PolicyQueryType.ActivePoliciesOnly.toString();
            this.value=value.toString();
        }
        
        /**
		 * Instantiates a new query.
		 * 
		 * @param value
		 *            the value
		 */
        public Query (SubjectSearchScopeValue value) {
            this.type = PolicyQueryType.SubjectSearchScope.toString();
            this.value = value.toString();
        }
        
        /**
		 * Instantiates a new query.
		 * 
		 * @param value
		 *            the value
		 */
        public Query (ExpandResourceLevelPoliciesValue value) {
            this.type = PolicyQueryType.ExpandResourceLevelPolicies.toString();
            this.value = value.toString();
        }
        
        /**
		 * Gets the type.
		 * 
		 * @return the type
		 */
        public String getType () {
            return this.type;
        }
        
        /**
		 * Gets the value.
		 * 
		 * @return the value
		 */
        public String  getValue()
        {
            return value;
        }
    };
    

    /**
	 * Gets the resolution.
	 * 
	 * @return the resolution
	 */
    public ResolutionType getResolution() {
        return resolution;
    }

    /**
	 * Sets the resolution.
	 * 
	 * @param resolution
	 *            the new resolution
	 */
    public void setResolution(ResolutionType resolution) {
        this.resolution = resolution;
    }
    
    /**
	 * Adds the query.
	 * 
	 * @param q
	 *            the q
	 */
    public void addQuery (Query q) {
        queries.add(q);
    }
 
    /**
	 * Gets the queries.
	 * 
	 * @return the queries
	 */
    public List<Query> getQueries () {
        return queries;
    }
}
