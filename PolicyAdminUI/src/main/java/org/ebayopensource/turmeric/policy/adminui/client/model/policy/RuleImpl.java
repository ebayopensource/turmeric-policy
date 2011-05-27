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
 * RuleImpl.
 */
public class RuleImpl implements Rule {

	private Long id;
	private String ruleName;
	private String description;
	private RuleEffectType effect;
	private Integer priority;
	private Integer version;
	

	private Long rolloverPeriod;
	private Long effectDuration;
	private Long conditionDuration;
	private Condition condition;
    
    /** The attribute list. */
    public List<RuleAttribute> attributeList;
	
	
	/**
	 * Instantiates a new rule impl.
	 * 
	 * @param ruleName
	 *            the rule name
	 * @param description
	 *            the description
	 * @param effect
	 *            the effect
	 * @param priority
	 *            the priority
	 * @param rolloverPeriod
	 *            the rollover period
	 * @param effectDuration
	 *            the effect duration
	 * @param conditionDuration
	 *            the condition duration
	 * @param condition
	 *            the condition
	 * @param attributeList
	 *            the attribute list
	 */
	public RuleImpl(String ruleName, String description, RuleEffectType effect, Integer priority,
			Long rolloverPeriod, Long effectDuration, Long conditionDuration, Condition condition, 
			List<RuleAttribute> attributeList){
		this.ruleName=ruleName;
		this.description=description;
		this.effect = effect;
		this.priority = priority;
		this.rolloverPeriod =rolloverPeriod;
		this.effectDuration =effectDuration;
		this.conditionDuration = conditionDuration;
		this.condition = condition;
		this.attributeList = attributeList;
		
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getId()
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
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getRuleName()
	 */
	public String getRuleName() {
		return ruleName;
	}
	
	/**
	 * Sets the rule name.
	 * 
	 * @param ruleName
	 *            the new rule name
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getDescription()
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
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getEffect()
	 */
	public RuleEffectType getEffect() {
		return effect;
	}
	
	/**
	 * Sets the effect.
	 * 
	 * @param effect
	 *            the new effect
	 */
	public void setEffect(RuleEffectType effect) {
		this.effect = effect;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getPriority()
	 */
	public Integer getPriority() {
		return priority;
	}
	
	/**
	 * Sets the priority.
	 * 
	 * @param priority
	 *            the new priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getVersion()
	 */
	public Integer getVersion() {
		return version;
	}
//	public void setVersion(Integer version) {
//		this.version = version;
//	}
	/* (non-Javadoc)
 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getRolloverPeriod()
 */
public Long getRolloverPeriod() {
		return rolloverPeriod;
	}
	
	/**
	 * Sets the rollover period.
	 * 
	 * @param rolloverPeriod
	 *            the new rollover period
	 */
	public void setRolloverPeriod(Long rolloverPeriod) {
		this.rolloverPeriod = rolloverPeriod;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getEffectDuration()
	 */
	public Long getEffectDuration() {
		return effectDuration;
	}
	
	/**
	 * Sets the effect duration.
	 * 
	 * @param effectDuration
	 *            the new effect duration
	 */
	public void setEffectDuration(Long effectDuration) {
		this.effectDuration = effectDuration;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getConditionDuration()
	 */
	public Long getConditionDuration() {
		return conditionDuration;
	}
	
	/**
	 * Sets the condition duration.
	 * 
	 * @param conditionDuration
	 *            the new condition duration
	 */
	public void setConditionDuration(Long conditionDuration) {
		this.conditionDuration = conditionDuration;
	}

	/**
	 * Sets the condition.
	 * 
	 * @param condition
	 *            the new condition
	 */
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getCondition()
	 */
	public Condition getCondition() {
		return condition;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getAttributeList()
	 */
	public List<RuleAttribute> getAttributeList() {
		return attributeList;
	}

	/**
	 * Sets the attribute list.
	 * 
	 * @param attributeList
	 *            the new attribute list
	 */
	public void setAttributeList(List<RuleAttribute> attributeList) {
		this.attributeList = attributeList;
	}


	
}
