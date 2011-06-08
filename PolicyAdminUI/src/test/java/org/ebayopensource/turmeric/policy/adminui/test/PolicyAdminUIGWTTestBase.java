/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.test;

import org.junit.Test;

import com.octo.gwt.test.GwtTest;

public class PolicyAdminUIGWTTestBase extends GwtTest {

    
	 /**
     * Instantiates a new policy gwt test case.
     */
//    public PolicyAdminUIGWTTestBase() {
//    	super();
//    }
   
	@Override
	public String getModuleName() {
		System.out.println("Getting module..");
		return "org.ebayopensource.turmeric.policy.adminui.PolicyAdminUI";
	}
	
	@Test
	public void testDoNothing(){
		System.out.println("Starting Unit Tests with GWT-Test-Utils...");
	}
}
