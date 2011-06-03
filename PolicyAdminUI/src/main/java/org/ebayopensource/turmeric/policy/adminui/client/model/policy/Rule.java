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
    Long getId();
    
    /**
	 * Gets the rule name.
	 * 
	 * @return the rule name
	 */
    String getRuleName();
    
    /**
	 * Gets the description.
	 * 
	 * @return the description
	 */
    String getDescription();
    
    /**
	 * Gets the effect.
	 * 
	 * @return the effect
	 */
    RuleEffectType getEffect();
    
    /**
	 * Gets the priority.
	 * 
	 * @return the priority
	 */
    Integer getPriority();
    
    /**
	 * Gets the version.
	 * 
	 * @return the version
	 */
    Integer getVersion();
    
    /**
	 * Gets the rollover period.
	 * 
	 * @return the rollover period
	 */
    Long getRolloverPeriod();
    
    /**
	 * Gets the effect duration.
	 * 
	 * @return the effect duration
	 */
    Long getEffectDuration();
    
    /**
	 * Gets the condition duration.
	 * 
	 * @return the condition duration
	 */
    Long getConditionDuration();
    
    /**
	 * Gets the condition.
	 * 
	 * @return the condition
	 */
    Condition getCondition();
    
    /**
	 * Gets the attribute list.
	 * 
	 * @return the attribute list
	 */
    List<RuleAttribute> getAttributeList();

}
