/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model;

/**
 * The Interface PolicyAdminUIService.
 */
public interface PolicyAdminUIService {
    
    /** The Constant ERROR_HEADER. */
    String ERROR_HEADER = "X-TURMERIC-ERROR-RESPONSE";
    
    /** The Constant SERVICE_NAME_HEADER. */
    String SERVICE_NAME_HEADER = "X-TURMERIC-SERVICE-NAME";
    
    /** The Constant OPERATION_NAME_HEADER. */
    String OPERATION_NAME_HEADER = "X-TURMERIC-OPERATION-NAME";
    
    /** The Constant USECASE_NAME_HEADER. */
    String USECASE_NAME_HEADER = "X-TURMERIC-USECASE-NAME";
    
    /** The Constant DATA_FORMAT_HEADER. */
    String DATA_FORMAT_HEADER = "X-TURMERIC-REQUEST-DATA-FORMAT";
    
    /** The Constant RESPONSE_FORMAT_HEADER. */
    String RESPONSE_FORMAT_HEADER = "X-TURMERIC-RESPONSE-DATA-FORMAT";
    
    /** The Constant TMC. */
    String TMC = "TMC";
    
    /** The Constant USECASE_HEADER_VALUE. */
    String USECASE_HEADER_VALUE=USECASE_NAME_HEADER+"="+TMC;
    
    /** The Constant NV_DATA_FORMAT_HEADER_VALUE. */
    String NV_DATA_FORMAT_HEADER_VALUE=DATA_FORMAT_HEADER+"=NV";
    
    /** The Constant JSON_DATA_FORMAT_HEADER_VALUE. */
    String JSON_DATA_FORMAT_HEADER_VALUE=DATA_FORMAT_HEADER+"=JSON";
    
    /** The Constant JSON_RESPONSE_FORMAT_HEADER_VALUE. */
    String JSON_RESPONSE_FORMAT_HEADER_VALUE=RESPONSE_FORMAT_HEADER+"=JSON";
}
