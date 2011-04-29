package org.ebayopensource.turmeric.policyservice.model;


/**
 * The Enum EffectType.
 */
public enum EffectType {
	
	/** The ALLOW. */
	ALLOW("Allow"),
    
    /** The FLAG. */
    FLAG("Flag"),
    
    /** The CHALLENGE. */
    CHALLENGE("Challenge"),
    
    /** The BLOCK. */
    BLOCK("Block"),
    
    /** The SOFTLIMIT. */
    SOFTLIMIT("Softlimit");
    private final String value;

    /**
	 * Instantiates a new effect type.
	 * 
	 * @param v
	 *            the v
	 */
    EffectType(String v) {
        value = v;
    }

    /**
	 * Value.
	 * 
	 * @return the string
	 */
    public String value() {
        return value;
    }

    /**
	 * From value.
	 * 
	 * @param v
	 *            the v
	 * @return the effect type
	 */
    public static EffectType fromValue(String v) {
        for (EffectType c: EffectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
