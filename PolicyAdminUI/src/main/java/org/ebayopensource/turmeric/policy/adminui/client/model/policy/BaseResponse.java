/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * BaseResponse.
 */
public interface BaseResponse {
    
    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 */
    boolean isErrored();
    
    /**
	 * Gets the error message.
	 * 
	 * @return the error message
	 */
    String getErrorMessage();
}
