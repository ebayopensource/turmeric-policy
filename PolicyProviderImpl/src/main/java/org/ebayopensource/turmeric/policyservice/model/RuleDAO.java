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


/**
 * The Interface RuleDAO.
 * 
 * @author muguerza
 */
public interface RuleDAO {

	/**
	 * Persist rule.
	 * 
	 * @param rule
	 *            the rule
	 */
	void persistRule(Rule rule);
	
	/**
	 * Persist condition.
	 * 
	 * @param condition
	 *            the condition
	 */
	void persistCondition(Condition condition);

	/**
	 * Persist expression.
	 * 
	 * @param expression
	 *            the expression
	 */
	void persistExpression(Expression expression);
	
	/**
	 * Persist primitive value.
	 * 
	 * @param primitiveValue
	 *            the primitive value
	 */
	void persistPrimitiveValue(PrimitiveValue primitiveValue);

	
	/**
	 * Find rule by id.
	 * 
	 * @param id
	 *            the id
	 * @return the rule
	 */
	Rule findRuleById(long id);
	
	/**
	 * Find rule by name.
	 * 
	 * @param name
	 *            the name
	 * @return the rule
	 */
	Rule findRuleByName(String name);

	/**
	 * Find condition by id.
	 * 
	 * @param conditionId
	 *            the condition id
	 * @return the condition
	 */
	Condition findConditionById(long conditionId);
	
	/**
	 * Find expression by id.
	 * 
	 * @param expressionId
	 *            the expression id
	 * @return the expression
	 */
	Expression findExpressionById(long expressionId);

	/**
	 * Find primitive value by id.
	 * 
	 * @param primitiveValueId
	 *            the primitive value id
	 * @return the primitive value
	 */
	PrimitiveValue findPrimitiveValueById(long primitiveValueId);
	
	/**
	 * Find condition by rule id.
	 * 
	 * @param ruleId
	 *            the rule id
	 * @return the condition
	 */
	Condition findConditionByRuleId(long ruleId);

	
	/**
	 * Find expression by condition id.
	 * 
	 * @param conditionId
	 *            the condition id
	 * @return the expression
	 */
	Expression findExpressionByConditionId(long conditionId);

	/**
	 * Find primitive value by expression id.
	 * 
	 * @param expressionId
	 *            the expression id
	 * @return the primitive value
	 */
	PrimitiveValue findPrimitiveValueByExpressionId(long expressionId);

	
	/**
	 * Find expression by name.
	 * 
	 * @param expressionName
	 *            the expression name
	 * @return the expression
	 */
	Expression findExpressionByName(String expressionName);
  
    
	/**
	 * Removes the rule.
	 * 
	 * @param ruleId
	 *            the rule id
	 */
	void removeRule(long ruleId);

	/**
	 * Removes the primitive value.
	 * 
	 * @param primitiveValueId
	 *            the primitive value id
	 */
	void removePrimitiveValue(long primitiveValueId);
 
	/**
	 * Checks if is rule name used.
	 * 
	 * @param ruleName
	 *            the rule name
	 * @return true, if is rule name used
	 */
	boolean isRuleNameUsed(String ruleName);
	
	/**
	 * Checks if is rule valid.
	 * 
	 * @param rule
	 *            the rule
	 * @param allowNull
	 *            the allow null
	 * @return true, if is rule valid
	 */
	boolean isRuleValid(Rule rule, boolean allowNull);

	//	void removeCondition(Long ruleId, String conditionId);

//    void audit(RuleKey ruleKey,  SubjectKey loginSubject);

}
