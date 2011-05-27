/*********************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;


/**
 * The Class ConditionImpl.
 */
public class ConditionImpl  implements Condition{

	private Expression expression;
	
	/**
	 * Instantiates a new condition impl.
	 * 
	 * @param expression
	 *            the expression
	 */
	public ConditionImpl(Expression expression) {
		this.expression = expression;
	}
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Condition#getExpression()
	 */
	@Override
	public Expression getExpression() {
		return expression;
	}

	

	/**
	 * Sets the expression list.
	 * 
	 * @param expression
	 *            the new expression list
	 */
	public void setExpressionList(Expression expression) {
		this.expression = expression;
	}

}
