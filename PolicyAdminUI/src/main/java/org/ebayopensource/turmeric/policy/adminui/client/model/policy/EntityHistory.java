/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.Date;

/**
 * EntityHistory.
 */
public interface EntityHistory {
    
    /**
	 * Gets the comments.
	 * 
	 * @return the comments
	 */
    String getComments();
    
    /**
	 * Gets the login subject.
	 * 
	 * @return the login subject
	 */
    String getLoginSubject();
    
    /**
	 * Gets the audit type.
	 * 
	 * @return the audit type
	 */
    String getAuditType();
    
    /**
	 * Gets the last modified time.
	 * 
	 * @return the last modified time
	 */
    Date getLastModifiedTime();
}
