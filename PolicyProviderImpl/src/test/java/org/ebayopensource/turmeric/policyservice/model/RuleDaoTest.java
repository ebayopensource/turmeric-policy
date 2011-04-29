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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.lang.reflect.Proxy;

import org.ebayopensource.turmeric.utils.jpa.JPAAroundAdvice;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class RuleDaoTest.
 * 
 * @author gbaal
 */
public class RuleDaoTest extends AbstractJPATest {
	
	/** The rule dao. */
	RuleDAO ruleDAO;

	/**
	 * Inits the dao.
	 */
	@Before
	public void initDAO() {
		ClassLoader classLoader = RuleDAO.class.getClassLoader();
		Class[] interfaces = { RuleDAO.class };
		RuleDAO target = new RuleDAOImpl();
		ruleDAO = (RuleDAO) Proxy.newProxyInstance(classLoader, interfaces,
				new JPAAroundAdvice(factory, target));
	}

	private PrimitiveValue createPrimitiveValue() {
		PrimitiveValue value = new PrimitiveValue();
		value.setType(SupportedPrimitive.STRING);
		value.setValue("PaymentService:commit.count>5");
		return value;
	}

	private Rule createRule() {
		Rule rule = new Rule();
		rule.setCondition(createCondition());
		rule.setEffect(EffectType.BLOCK);
		rule.setEffectDuration(3600L);
		rule.setPriority(2);
		rule.setRolloverPeriod(1L);
		rule.setRuleName("RLRule");

		return rule;
	}

	private Condition createCondition() {
		Condition condition = new Condition();

		Expression expression = createExpression();
		condition.setExpression(expression);
		return condition;
	}

	private Expression createExpression() {
		PrimitiveValue value = createPrimitiveValue();
		Expression expression = new Expression(value, "Service count", "HITS");
		expression.setPrimitiveValue(value);
		return expression;
	}

	/**
	 * Find condition by id test.
	 */
	@Test
	public void findConditionByIdTest() {
		Condition condition = createCondition();
		ruleDAO.persistCondition(condition);
		assertNotNull(condition.getId());
		assertNotNull(ruleDAO.findConditionById(condition.getId()));
	}

	/**
	 * Find condition by rule id test.
	 */
	@Test
	public void findConditionByRuleIdTest() {
		Rule rule = createRule();
		ruleDAO.persistRule(rule);
		assertNotNull(rule.getId());
		assertNotNull(ruleDAO.findConditionByRuleId(rule.getId()));
	}

	/**
	 * Find expression by condition id test.
	 */
	@Test
	public void findExpressionByConditionIdTest() {
		Condition condition = createCondition();
		ruleDAO.persistCondition(condition);
		assertNotNull(condition.getId());
		assertNotNull(ruleDAO.findExpressionByConditionId(condition.getId()));
	}

	/**
	 * Find expression by id test.
	 */
	@Test
	public void findExpressionByIdTest() {
		Expression expression = createExpression();
		ruleDAO.persistExpression(expression);
		assertNotNull(expression.getId());
		assertNotNull(ruleDAO.findExpressionById(expression.getId()));
	}

	/**
	 * Find expression by name test.
	 */
	@Test
	public void findExpressionByNameTest() {
		Expression expression = createExpression();
		ruleDAO.persistExpression(expression);
		assertNotNull(expression.getId());
		assertNotNull(ruleDAO.findExpressionByName(expression.getName()));
	}

	/**
	 * Find primitive value by expression id test.
	 */
	@Test
	public void findPrimitiveValueByExpressionIdTest() {
		Expression expression = createExpression();
		ruleDAO.persistExpression(expression);
		assertNotNull(expression.getId());
		assertNotNull(ruleDAO.findPrimitiveValueByExpressionId(expression
				.getId()));
	}

	/**
	 * Find primitive value by id test.
	 */
	@Test
	public void findPrimitiveValueByIdTest() {
		PrimitiveValue primitiveValue = createPrimitiveValue();
		ruleDAO.persistPrimitiveValue(primitiveValue);
		assertNotNull(primitiveValue.getId());
		assertNotNull(ruleDAO.findPrimitiveValueById(primitiveValue.getId()));

	}

	/**
	 * Find rule by id test.
	 */
	@Test
	public void findRuleByIdTest() {
		Rule rule = createRule();
		ruleDAO.persistRule(rule);
		assertNotNull(rule.getId());
		assertNotNull(ruleDAO.findRuleById(rule.getId()));
	}

	/**
	 * Find rule by name test.
	 */
	@Test
	public void findRuleByNameTest() {
		Rule rule = createRule();
		ruleDAO.persistRule(rule);
		assertNotNull(rule.getId());
		assertNotNull(ruleDAO.findRuleByName(rule.getRuleName()));
	}

	/**
	 * Persist condition test.
	 */
	@Test
	public void persistConditionTest() {
		Condition condition = createCondition();
		ruleDAO.persistCondition(condition);
		assertNotNull(condition.getId());

	}

	/**
	 * Persist expression test.
	 */
	@Test
	public void persistExpressionTest() {
		Expression expression = createExpression();
		ruleDAO.persistExpression(expression);
		assertNotNull(expression.getId());

	}

	/**
	 * Persist primitive value test.
	 */
	@Test
	public void persistPrimitiveValueTest() {
		PrimitiveValue primitiveValue = createPrimitiveValue();
		ruleDAO.persistPrimitiveValue(primitiveValue);
		assertNotNull(primitiveValue.getId());

	}

	/**
	 * Persist rule test.
	 */
	@Test
	public void persistRuleTest() {
		Rule rule = createRule();
		ruleDAO.persistRule(rule);
		assertNotNull(rule.getId());

	}

	/**
	 * Removes the primitive value.
	 */
	@Test
	public void removePrimitiveValue() {
		PrimitiveValue primitiveValue = createPrimitiveValue();
		ruleDAO.persistPrimitiveValue(primitiveValue);
		Long id = primitiveValue.getId();
		assertNotNull(id);
		ruleDAO.removePrimitiveValue(id);
		assertNull(ruleDAO.findPrimitiveValueById(id));

	}

	/**
	 * Removes the rule.
	 */
	@Test
	public void removeRule() {
		Rule rule = createRule();
		ruleDAO.persistRule(rule);
		Long id = rule.getId();
		String name = rule.getRuleName();
		assertNotNull(id);
		ruleDAO.removeRule(id);
		assertNull(ruleDAO.findRuleById(id));
		assertNull(ruleDAO.findRuleByName(name));
	}

	/**
	 * Checks if is rule name used test.
	 */
	@Test
	public void isRuleNameUsedTest() {
		Rule rule = createRule();
		assertFalse(ruleDAO.isRuleNameUsed(rule.getRuleName()));
		ruleDAO.persistRule(rule);
		assertTrue(ruleDAO.isRuleNameUsed(rule.getRuleName()));

	}



	/**
	 * Checks if is rule valid test.
	 */
	@Test
	public void isRuleValidTest() {
		Rule rule = createRule();
		assertTrue(ruleDAO.isRuleValid(rule,false));
		rule.getCondition().getExpression().setPrimitiveValue(null);
		assertFalse(ruleDAO.isRuleValid(rule,false));
		assertFalse(ruleDAO.isRuleValid(null, true));
		
	}

}
