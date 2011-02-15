/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/
package org.ebayopensource.turmeric.policyservice.provider.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import oasis.names.tc.xacml._2_0.policy.schema.os.AttributeValueType;
import oasis.names.tc.xacml._2_0.policy.schema.os.SubjectMatchType;

import org.ebayopensource.turmeric.runtime.common.impl.utils.LogManager;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroup;


public class PolicyServiceUtils
{
	private static Logger s_Logger = LogManager.getInstance(PolicyServiceUtils.class);
	

	public static Long getSubjectGroupId(SubjectGroup sg )
	{	
		SubjectMatchType matchType = sg.getSubjectMatch();
		
		return getIdFromSubjectMatch(matchType);
	}
	
	public static Long getIdFromSubjectMatch(SubjectMatchType matchType)
	{
		Long subjectId = null;
		
		if (matchType != null && matchType.getSubjectAttributeDesignator().
					getAttributeId().equals("urn:oasis:names:tc:xacml:1.0:subject:subject-id"))
		{
			AttributeValueType attributeValue = matchType.getAttributeValue();
			 
			String idString = attributeValue.getContent().get(0).toString();
            if ("urn:oasis:names:tc:xacml:1.0:function:integer-equal".equals(matchType.getMatchId())) {
                try {
                    subjectId = Long.parseLong(idString);
                } catch (Exception e) {
                	s_Logger.log(Level.WARNING, 
                			"org.ebayopensource.turmeric.services.policyservice.impl.Utils invalid subject Id " + 
                			idString);
                }
            }

            if ("urn:oasis:names:tc:xacml:1.0:function:string-regexp-match".equals(matchType.getMatchId())) {
                try {
                    subjectId = Long.parseLong(idString.substring(3, idString.length() -1));
                } catch (Exception e) {
                	s_Logger.log(Level.WARNING, 
                			"org.ebayopensource.turmeric.services.policyservice.impl.Utils invalid external subject Id " + 
                			idString);
                }
            }
		}
			
		return subjectId;
	}
	

}
