/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.session;

import com.google.gwt.user.client.rpc.RemoteService;

public interface SessionInterface extends RemoteService {

    Integer getUserSessionTimeout();
    Boolean isSessionAlive();
    void ping();
    
}
