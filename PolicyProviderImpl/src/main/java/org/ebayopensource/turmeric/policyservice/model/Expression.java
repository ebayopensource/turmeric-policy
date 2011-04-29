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
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class Expression.
 * 
 * @author gbaal
 */
@Entity
public class Expression  extends AuditablePersistent{
	
	

	/**
	 * Instantiates a new expression.
	 */
	public Expression() { }
	
	/**
	 * Instantiates a new expression.
	 * 
	 * @param primitiveValue
	 *            the primitive value
	 * @param comment
	 *            the comment
	 * @param name
	 *            the name
	 */
	public Expression(PrimitiveValue primitiveValue, String comment, String name) {
		super();
		this.primitiveValue = primitiveValue;
		this.comment = comment;
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PrimitiveValue primitiveValue;
	// TODO Not yet Implemented
	// private Variable variable;
	// private Function function;
	private String comment;
	
	/** The name. */
	protected String name;

	/**
	 * Gets the primitive value.
	 * 
	 * @return the primitive value
	 */
	public PrimitiveValue getPrimitiveValue() {
		return primitiveValue;
	}

	/**
	 * Gets the comment.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the primitive value.
	 * 
	 * @param primitiveValue
	 *            the new primitive value
	 */
	public void setPrimitiveValue(PrimitiveValue primitiveValue) {
		this.primitiveValue = primitiveValue;
	}
}
