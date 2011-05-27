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

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.RuleEffectType;

/**
 * Rule.
 */
public interface Rule {

    /**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    public Long getId();
    
    /**
	 * Gets the rule name.
	 * 
	 * @return the rule name
	 */
    public String getRuleName();
    
    /**
	 * Gets the description.
	 * 
	 * @return the description
	 */
    public String getDescription();
    
    /**
	 * Gets the effect.
	 * 
	 * @return the effect
	 */
    public RuleEffectType getEffect();
    
    /**
	 * Gets the priority.
	 * 
	 * @return the priority
	 */
    public Integer getPriority();
    
    /**
	 * Gets the version.
	 * 
	 * @return the version
	 */
    public Integer getVersion();
    
    /**
	 * Gets the rollover period.
	 * 
	 * @return the rollover period
	 */
    public Long getRolloverPeriod();
    
    /**
	 * Gets the effect duration.
	 * 
	 * @return the effect duration
	 */
    public Long getEffectDuration();
    
    /**
	 * Gets the condition duration.
	 * 
	 * @return the condition duration
	 */
    public Long getConditionDuration();
    
    /**
	 * Gets the condition.
	 * 
	 * @return the condition
	 */
    public Condition getCondition();
    
    /**
	 * Gets the attribute list.
	 * 
	 * @return the attribute list
	 */
    public List<RuleAttribute> getAttributeList();

}
