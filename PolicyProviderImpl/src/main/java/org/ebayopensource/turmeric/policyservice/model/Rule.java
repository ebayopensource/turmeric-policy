
/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 ******************************************************************************/
package org.ebayopensource.turmeric.policyservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class Rule.
 * 
 * @author gbaal
 */
@Entity
public class Rule extends AuditablePersistent {

	
	/**
	 * Instantiates a new rule.
	 */
	public Rule() { }
	
	/**
	 * Instantiates a new rule.
	 * 
	 * @param ruleName
	 *            the rule name
	 * @param description
	 *            the description
	 * @param effectDuration
	 *            the effect duration
	 * @param rolloverPeriod
	 *            the rollover period
	 * @param priority
	 *            the priority
	 * @param effect
	 *            the effect
	 * @param condition
	 *            the condition
	 * @param notifyEmails
	 *            the notify emails
	 * @param notifyActive
	 *            the notify active
	 */
	public Rule(String ruleName, String description, Long effectDuration,
			Long rolloverPeriod, Integer priority, EffectType effect,
			Condition condition,String notifyEmails, boolean notifyActive ) {
		super();
		this.ruleName = ruleName;
		this.description = description;
		this.effectDuration = effectDuration;
		this.rolloverPeriod = rolloverPeriod;
		this.priority = priority;
		this.effect = effect;
		this.condition = condition;
		this.notifyEmails = notifyEmails;
		this.notifyActive = notifyActive;
	}
	private String notifyEmails;
	private boolean notifyActive;
	
	private String ruleName;
	private String description;
	private Long effectDuration;
	private Long rolloverPeriod;
	private Integer priority;
	@Enumerated(EnumType.ORDINAL)
	private EffectType effect;

//	@ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="policy_id" )
//    private Policy policy;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Condition condition;

	/**
	 * Gets the rule name.
	 * 
	 * @return the rule name
	 */
	public String getRuleName() {
		return ruleName;
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
	 * Gets the effect duration.
	 * 
	 * @return the effect duration
	 */
	public Long getEffectDuration() {
		return effectDuration;
	}

	/**
	 * Gets the rollover period.
	 * 
	 * @return the rollover period
	 */
	public Long getRolloverPeriod() {
		return rolloverPeriod;
	}

	/**
	 * Gets the priority.
	 * 
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Gets the condition.
	 * 
	 * @return the condition
	 */
	public Condition getCondition() {
		return condition;
	}
	
	/**
	 * Gets the effect.
	 * 
	 * @return the effect
	 */
	public EffectType getEffect() {
		return effect;
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
	 * Sets the effect duration.
	 * 
	 * @param effectDuration
	 *            the new effect duration
	 */
	public void setEffectDuration(Long effectDuration) {
		this.effectDuration = effectDuration;
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

	/**
	 * Sets the priority.
	 * 
	 * @param priority
	 *            the new priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * Sets the effect.
	 * 
	 * @param effect
	 *            the new effect
	 */
	public void setEffect(EffectType effect) {
		this.effect = effect;
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
	
	/**
	 * Gets the notify emails.
	 * 
	 * @return the notify emails
	 */
	public String getNotifyEmails() {
		return notifyEmails;
	}

	/**
	 * Sets the notify emails.
	 * 
	 * @param notifyEmails
	 *            the new notify emails
	 */
	public void setNotifyEmails(String notifyEmails) {
		this.notifyEmails = notifyEmails;
	}

	/**
	 * Checks if is notify active.
	 * 
	 * @return true, if is notify active
	 */
	public boolean isNotifyActive() {
		return notifyActive;
	}

	/**
	 * Sets the notify active.
	 * 
	 * @param notifyActive
	 *            the new notify active
	 */
	public void setNotifyActive(boolean notifyActive) {
		this.notifyActive = notifyActive;
	}
}
