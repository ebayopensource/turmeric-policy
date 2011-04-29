/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.policyservice.provider;

import org.ebayopensource.turmeric.security.v1.services.EffectType;

/**
 * The Class WHITELISTPolicy.
 */
public class WHITELISTPolicy extends ListPolicyBase {

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.provider.ListPolicyBase#getPolicyType()
     */
    @Override
    protected String getPolicyType() {
        return "WHITELIST";
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.provider.ListPolicyBase#getAction()
     */
    @Override
    protected String getAction() {
       return EffectType.ALLOW.name();
    }
}
