/**
 * 
 */
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * The Class ExpressionImpl.
 * 
 * @author jose
 */
public class ExpressionImpl implements Expression {

	private Long id;
	private String name;
	private String comment;
	private PrimitiveValue primitiveValue;
	
	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param comment
	 *            the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policy.adminui.client.model.policy.Expression
	 * #getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policy.adminui.client.model.policy.Expression
	 * #getName()
	 */
	@Override
	public String getName() {

		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policy.adminui.client.model.policy.Expression
	 * #getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.policy.adminui.client.model.policy.Expression
	 * #getPrimitiveValue()
	 */
	@Override
	public PrimitiveValue getPrimitiveValue() {
		return primitiveValue;
	}

}
