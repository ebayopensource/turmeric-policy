package org.ebayopensource.turmeric.policyservice.provider;

import org.ebayopensource.turmeric.security.v1.services.EffectType;

/**
 * The Class AUTHZPolicy.
 */
public class AUTHZPolicy extends ListPolicyBase {

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.provider.ListPolicyBase#getPolicyType()
	 */
	@Override
	protected String getPolicyType() {
		return "AUTHZ";
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policyservice.provider.ListPolicyBase#getAction()
	 */
	@Override
	protected String getAction() {
	       return EffectType.ALLOW.name();
	}

}
