/**
 * 
 */
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * The Class PrimitiveValueImpl.
 * 
 * @author jose
 */
public class PrimitiveValueImpl implements PrimitiveValue {
	private Long id;
	private  SupportedPrimitive type;
	private String value;
	
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
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(SupportedPrimitive type) {
		this.type = type;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}


	
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PrimitiveValue#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PrimitiveValue#getType()
	 */
	@Override
	public SupportedPrimitive getType() {

		return type;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PrimitiveValue#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

}
