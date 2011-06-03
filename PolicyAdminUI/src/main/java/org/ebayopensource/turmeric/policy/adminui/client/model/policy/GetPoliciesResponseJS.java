/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.GetResourcesResponseJS.ResourceJS;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetPoliciesResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.RuleEffectType;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * GetPoliciesResponseJS.
 */
public class GetPoliciesResponseJS extends JavaScriptObject implements
		GetPoliciesResponse {

	/** The Constant NAME. */
	public static final String NAME = "ns1.findPoliciesResponse";

	/**
	 * PolicyJS.
	 */
	public static class PolicyJS extends JavaScriptObject implements
			GenericPolicy {

		/**
		 * Instantiates a new policy js.
		 */
		protected PolicyJS() {
		}

		/**
		 * Gets the description.
		 * 
		 * @return the description
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getDescription()
		 */
		@Override
		public final native String getDescription() /*-{
			return this["ns2.Description"];
		}-*/;

		/**
		 * Gets the id as string.
		 * 
		 * @return the id as string
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getId()
		 */
		public final native String getIdAsString() /*-{
			return this["@PolicyId"];
		}-*/;

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getId()
		 */
		@Override
		public final Long getId() {
			return Long.valueOf(getIdAsString());
		}

		/**
		 * Gets the name.
		 * 
		 * @return the name
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getName()
		 */
		@Override
		public final native String getName() /*-{
			return this["@PolicyName"];
		}-*/;

		/**
		 * Gets the type.
		 * 
		 * @return the type
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getType()
		 */
		@Override
		public final native String getType() /*-{
			return this["@PolicyType"];
		}-*/;

		/**
		 * Gets the rules.
		 * 
		 * @return the rules
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getRules()
		 */
		@Override
		public final List<Rule> getRules() {
			List<Rule> rules = new ArrayList<Rule>();
			JsArray<RuleJS> array = getRuleArray();
			if (array != null) {
				for (int i = 0; i < array.length(); i++)
					rules.add(array.get(i));
			}
			return rules;
		}

		/**
		 * Gets the rule array.
		 * 
		 * @return the rule array
		 */
		public final native JsArray<RuleJS> getRuleArray() /*-{
			if (this["ns1.Rule"])
			return this["ns1.Rule"];
			else
			return null;
		}-*/;

		/**
		 * Gets the resources.
		 * 
		 * @return the resources
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getResources()
		 */
		@Override
		public final List<Resource> getResources() {
			List<Resource> resources = new ArrayList<Resource>();
			JsArray<ResourceJS> array = getResourceArray();
			if (array != null) {
				for (int i = 0; i < array.length(); i++)
					resources.add(array.get(i));
			}
			return resources;
		}

		/**
		 * Gets the resource array.
		 * 
		 * @return the resource array
		 */
		public final native JsArray<ResourceJS> getResourceArray() /*-{
			if (this["ns1.Target"])
			if (this["ns1.Target"]["ns1.Resources"])
			return this["ns1.Target"]["ns1.Resources"]["ns1.Resource"];
			else
			return null;
			else
			return null;
		}-*/;

		/**
		 * both Inclusion List & exclusion list are store at same list. the way
		 * to distinguish include subject as following example: assume a subject
		 * Id is 705033744 then at request schema: include subject looks like:
		 * <ns1:AttributeValue
		 * DataType="http://www.w3.org/2001/XMLSchema#string"
		 * >(705033744)</ns1:AttributeValue> exclusion subject looks like:
		 * <ns1:AttributeValue
		 * DataType="http://www.w3.org/2001/XMLSchema#string"
		 * >(?!705033744)</ns1:AttributeValue>
		 * 
		 * @return the subject groups
		 */
		@Override
		public final List<SubjectGroup> getSubjectGroups() {
			List<SubjectGroup> subjectGroups = new ArrayList<SubjectGroup>();
			JsArray<SubjectGroupJS> array = getSubjectGroupsArray();
			if (array != null) {
				for (int i = 0; i < array.length(); i++){
					if(array.get(i).getSubjectMatchTypes() != null){
						for (SubjectMatchType matchType :array.get(i).getSubjectMatchTypes()){
							if("urn:oasis:names:tc:xacml:1.0:function:integer-equal".equals(matchType.getMatchId())){
								subjectGroups.add(array.get(i));	
							}
							break;
						}	
					}
				}
//				for (int i = 0; i < array.length(); i++){
//					if(array.get(i).getSubjectMatchType() != null){
//						if("urn:oasis:names:tc:xacml:1.0:function:integer-equal".equals(array.get(i).getSubjectMatchType().getMatchId())){
//							subjectGroups.add(array.get(i));	
//						}
//					}
//				}
			}
			return subjectGroups;
		}

		/**
		 * both Inclusion List & exclusion list are store at same list. the way
		 * to distinguish include subject as following example: assume a subject
		 * Id is 705033744 then at request schema: include subject looks like:
		 * <ns1:AttributeValue
		 * DataType="http://www.w3.org/2001/XMLSchema#string"
		 * >(705033744)</ns1:AttributeValue> exclusion subject looks like:
		 * <ns1:AttributeValue
		 * DataType="http://www.w3.org/2001/XMLSchema#string"
		 * >(?!705033744)</ns1:AttributeValue>
		 * 
		 * @return the subjects
		 */
		@Override
		public final List<Subject> getSubjects() {
			List<Subject> subjects = new ArrayList<Subject>();
			JsArray<SubjectJS> array = getSubjectsArray();
			if (array != null) {
				for (int i = 0; i < array.length(); i++){
					if(array.get(i).getSubjectMatchTypes() != null){
						for (SubjectMatchType matchType :array.get(i).getSubjectMatchTypes()){
							//ordinary subjects
							if("urn:oasis:names:tc:xacml:1.0:function:integer-equal".equals(matchType.getMatchId())){
								subjects.add(array.get(i));	
							}
							//subject type
							if("urn:oasis:names:tc:xacml:1.0:function:string-regexp-match".equals(matchType.getMatchId())
									&& array.get(i).getIdFromSubjectMatchAsString().endsWith("+") ){
								subjects.add(array.get(i));	
							}
							break;
						}	
					}
				}
			}
			
			return subjects;
		}

		

		
		/**
		 * Gets the subjects array.
		 * 
		 * @return the subjects array
		 */
		public final native JsArray<SubjectJS> getSubjectsArray() /*-{
			if (this["ns1.Target"])
			if (this["ns1.Target"]["ns1.Subjects"])
			return this["ns1.Target"]["ns1.Subjects"]["ns1.Subject"];
			else
			return null;
			else
			return null;
		}-*/;

		/**
		 * Gets the subject groups array.
		 * 
		 * @return the subject groups array
		 */
		public final native JsArray<SubjectGroupJS> getSubjectGroupsArray() /*-{
			if (this["ns1.Target"])
			if (this["ns1.Target"]["ns1.Subjects"])
			return this["ns1.Target"]["ns1.Subjects"]["ns1.SubjectGroup"];
			else
			return null;
			else 
			return null;
		}-*/;

		/**
		 * Gets the subject match type array.
		 * 
		 * @return the subject match type array
		 */
		public final native JsArray<SubjectMatchTypeJS> getSubjectMatchTypeArray() /*-{
		if (this["ns1.Target"])
			if (this["ns1.Target"]["ns1.Subjects"])
				if (this["ns1.Target"]["ns1.Subjects"]["ns1.SubjectGroup"])
					return this["ns1.Target"]["ns1.Subjects"]["ns1.SubjectGroup"]["ns2.SubjectMatchType"];
			else
				return null;
		else 
			return null;
		}-*/;
		
		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getLastModified()
		 */
		public final Date getLastModified() {
			String tmp = getLastModifiedAsString();
			try {
				return PolicyAdminUIUtil.xsDateTimeFormat.parse(tmp);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}

		/**
		 * Gets the last modified as string.
		 * 
		 * @return the last modified as string
		 */
		public final native String getLastModifiedAsString() /*-{
			return this["@LastModified"];
		}-*/;

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getCreatedBy()
		 */
		@Override
		public final native String getCreatedBy() /*-{
			return this["@CreatedBy"];
		}-*/;

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getLastModifiedBy()
		 */
		@Override
		public final native String getLastModifiedBy() /*-{
			return this["@LastModifiedBy"];
		}-*/;

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getCreationDate()
		 */
		@Override
		public final Date getCreationDate() {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.GenericPolicy#getEnabled()
		 */
		@Override
		public final boolean getEnabled() {
			return Boolean.valueOf(getEnabledAsString());
		}

		/**
		 * Gets the enabled as string.
		 * 
		 * @return the enabled as string
		 */
		public native final String getEnabledAsString() /*-{
			return this["@Active"];
		}-*/;

		
		/**
		 * both Inclusion List & exclusion list are store at same list. the way
		 * to distinguish include subject as following example: assume a subject
		 * Id is 705033744 then at request schema: include subject looks like:
		 * <ns1:AttributeValue
		 * DataType="http://www.w3.org/2001/XMLSchema#string"
		 * >(705033744)</ns1:AttributeValue> exclusion subject looks like:
		 * <ns1:AttributeValue
		 * DataType="http://www.w3.org/2001/XMLSchema#string"
		 * >(?!705033744)</ns1:AttributeValue>
		 * 
		 * @return the exclusion subjects
		 */
        @Override
		public final List<Subject> getExclusionSubjects() {
	        List<Subject> exclusionSubjects = new ArrayList<Subject>();
			JsArray<SubjectJS> array = getSubjectsArray();
			if (array != null) {
				for (int i = 0; i < array.length(); i++){
					if(array.get(i).getSubjectMatchTypes() != null){
						for (SubjectMatchType matchType :array.get(i).getSubjectMatchTypes()){
							if("urn:oasis:names:tc:xacml:1.0:function:string-regexp-match".equals(matchType.getMatchId())
									&& !array.get(i).getIdFromSubjectMatchAsString().endsWith("+")){
								exclusionSubjects.add(array.get(i));	
							}
							break;
						}	
					}
				}
			}
			return exclusionSubjects;
        }
        
        /**
		 * both Inclusion List & exclusion list are store at same list. the way
		 * to distinguish include subject as following example: assume a subject
		 * group Id is 705033744 then at request schema: include subject looks
		 * like: <ns1:AttributeValue
		 * DataType="http://www.w3.org/2001/XMLSchema#string"
		 * >(705033744)</ns1:AttributeValue> exclusion subject looks like:
		 * <ns1:AttributeValue
		 * DataType="http://www.w3.org/2001/XMLSchema#string"
		 * >(?!705033744)</ns1:AttributeValue>
		 * 
		 * @return the exclusion sg
		 */
        	
		public final List<SubjectGroup> getExclusionSG() { 
	        List<SubjectGroup> exclusionSG = new ArrayList<SubjectGroup>();
			JsArray<SubjectGroupJS> array = getSubjectGroupsArray();
//			if (array != null) {
//				for (int i = 0; i < array.length(); i++){
//					if(array.get(i).getSubjectMatchType() != null){
//						if("urn:oasis:names:tc:xacml:1.0:function:string-regexp-match".equals(array.get(i).getSubjectMatchType().getMatchId())){
//							exclusionSG.add(array.get(i));	
//						}
//					}
//				}
//			}
			if (array != null) {
				for (int i = 0; i < array.length(); i++){
					if(array.get(i).getSubjectMatchTypes() != null){
						for (SubjectMatchType matchType :array.get(i).getSubjectMatchTypes()){
							if("urn:oasis:names:tc:xacml:1.0:function:string-regexp-match".equals(matchType.getMatchId())){
								exclusionSG.add(array.get(i));	
							}
							break;
						}	
					}
				}
			}
			return exclusionSG;
		}

	}

	/**
	 * RuleJS.
	 */
	public static class RuleJS extends JavaScriptObject implements Rule {

		/**
		 * Instantiates a new rule js.
		 */
		protected RuleJS() {
		}

		/**
		 * Gets the id.
		 * 
		 * @return the id
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.Rule#getId()
		 */
		@Override
		public final Long getId() {
			return Long.valueOf(getIdAsString());
		}

		private final native String getIdAsString() /*-{
			return this["@RuleId"];
		}-*/;

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getRuleName()
		 */
		@Override
		public native final String getRuleName() /*-{
			return this["@RuleName"];
		}-*/;

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getDescription()
		 */
		@Override
		public final String getDescription() {
			return null;
		}

		/**
		 * Gets the effect.
		 * 
		 * @return the effect
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.Rule#getEffect()
		 */
		@Override
		public final RuleEffectType getEffect() {
			return RuleEffectType.valueOf(getEffectAsString());
		}

		private final native String getEffectAsString() /*-{
			return this["@Effect"];
		}-*/;

		/**
		 * Gets the priority.
		 * 
		 * @return the priority
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.Rule#getPriority()
		 */
		@Override
		public final Integer getPriority() {
			return Integer.valueOf(getPriorityAsString());
		}

		private final native String getPriorityAsString() /*-{
			return this["@Priority"];
		}-*/;

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getVersion()
		 */
		@Override
		public final Integer getVersion() {
			return null;
		}

		/**
		 * Gets the rollover period.
		 * 
		 * @return the rollover period
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.Rule#getRolloverPeriod()
		 */
		@Override
		public final Long getRolloverPeriod() {
			return Long.valueOf(getRolloverPeriodAsString());
		}

		private final native String getRolloverPeriodAsString() /*-{
			return this["@RolloverPeriod"];
		}-*/;

		/**
		 * Gets the effect duration.
		 * 
		 * @return the effect duration
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.Rule#getEffectDuration()
		 */
		@Override
		public final Long getEffectDuration() {
			return Long.valueOf(getEffectDurationAsString());
		}

		private final native String getEffectDurationAsString() /*-{
			return this["@EffectDuration"];
		}-*/;
 
		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getConditionDuration()
		 */
		@Override
		public final Long getConditionDuration() {
			return null;
		}


		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getCondition()
		 */
		public final Condition getCondition() {
			return getConditionsAsObject();
		}

		private final native ConditionJS getConditionsAsObject() /*-{
			return this["ns1.Condition"];
		}-*/;

		/**
		 * ConditionJS.
		 */
		public static class ConditionJS extends JavaScriptObject implements
				Condition {

			/**
			 * Instantiates a new condition js.
			 */
			protected ConditionJS() {
			}

			/* (non-Javadoc)
			 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Condition#getExpression()
			 */
			@Override
			public final Expression getExpression() {
				return getExpressionAsObject();

			}

			/**
			 * Gets the expression as object.
			 * 
			 * @return the expression as object
			 */
			public final native ExpressionJS getExpressionAsObject() /*-{
				return (this["ns1.Expression"]);
			}-*/;

			/**
			 * ExpressionJS.
			 */
			public static class ExpressionJS extends JavaScriptObject implements
					Expression {

				/**
				 * Instantiates a new expression js.
				 */
				protected ExpressionJS() {
				}

				/* (non-Javadoc)
				 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Expression#getId()
				 */
				@Override
				public final Long getId() {
					return null;
				}

				/* (non-Javadoc)
				 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Expression#getName()
				 */
				public native final String getName() /*-{
					return this["@Name"];
				}-*/;

				/* (non-Javadoc)
				 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Expression#getComment()
				 */
				@Override
				public final native String getComment() /*-{
					return this["ns1.Comment"];
				}-*/;

				/* (non-Javadoc)
				 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Expression#getPrimitiveValue()
				 */
				@Override
				public final PrimitiveValue getPrimitiveValue() {
					return getPrimitiveValueAsObject();
				}

				/**
				 * Gets the primitive value as object.
				 * 
				 * @return the primitive value as object
				 */
				public final native PrimitiveValueJS getPrimitiveValueAsObject() /*-{
					return this["ns1.PrimitiveValue"];
				}-*/;

				/**
				 * PrimitiveValueJS.
				 */
				public static class PrimitiveValueJS extends JavaScriptObject
						implements PrimitiveValue {

					/**
					 * Instantiates a new primitive value js.
					 */
					protected PrimitiveValueJS() {
					}

					/* (non-Javadoc)
					 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PrimitiveValue#getId()
					 */
					@Override
					public final Long getId() {
						return null;
					}

					/* (non-Javadoc)
					 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PrimitiveValue#getType()
					 */
					@Override
					public final SupportedPrimitive getType() {
						return SupportedPrimitive.fromValue(getTypeAsString());
					}

					private final native String getTypeAsString() /*-{
						return this["@type"];
					}-*/;

					/* (non-Javadoc)
					 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PrimitiveValue#getValue()
					 */
					@Override
					public final native String getValue() /*-{
						return this["@value"];
					}-*/;

				}
			}
		}

		/* (non-Javadoc)
		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.Rule#getAttributeList()
		 */
		@Override
		public final List<RuleAttribute> getAttributeList() {
			
			List<RuleAttribute> attibutes = new ArrayList<RuleAttribute>();
			JsArray<RuleAttributeJS> jsAttributes= getRuleAttributeAsArray();
			if (jsAttributes != null) {
				for (int i = 0; i < jsAttributes.length(); i++)
					attibutes.add(jsAttributes.get(i));
			}
			return attibutes;
		}

		private final native JsArray<RuleAttributeJS> getRuleAttributeAsArray() /*-{
			return this["ns1.Attribute"];
		}-*/;

		/**
		 * RuleAttributeJS.
		 */
		public static class RuleAttributeJS extends JavaScriptObject
				implements RuleAttribute {

			/**
			 * Instantiates a new rule attribute js.
			 */
			protected RuleAttributeJS() {
			}

			
		    /* (non-Javadoc)
    		 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.RuleAttribute#getKey()
    		 */
    		@Override
			public native final String getKey() /*-{
				return this["ns1.key"];
			}-*/;

			/* (non-Javadoc)
			 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.RuleAttribute#getValue()
			 */
			@Override
			public final native String getValue() /*-{
				return this["ns1.value"];
			}-*/;
		}

		
	}

	/**
	 * PolicySetJS.
	 */
	public static class PolicySetJS extends JavaScriptObject {
		
		/**
		 * Instantiates a new policy set js.
		 */
		protected PolicySetJS() {
		}

		/**
		 * Gets the policies.
		 * 
		 * @return the policies
		 */
		public final native JsArray<PolicyJS> getPolicies() /*-{
			return this["ns1.policy"];
		}-*/;
	}

	/**
	 * Instantiates a new gets the policies response js.
	 */
	protected GetPoliciesResponseJS() {
	}

	/**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the gets the policies response js
	 */
	public static final native GetPoliciesResponseJS fromJSON(String json) /*-{
		try {
		return eval('(' + json + ')');
		} catch (err) {
		return null;
		}
	}-*/;

	/**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetPoliciesResponse#getErrorMessage()
	 */
	public native final String getErrorMessage() /*-{
		return this["ns1.findPoliciesResponse"]["ms.errorMessage"];
	}-*/;

	/**
	 * Gets the policies.
	 * 
	 * @return the policies
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetPoliciesResponse#getPolicies()
	 */
	public final Collection<GenericPolicy> getPolicies() {
		List<GenericPolicy> policies = new ArrayList<GenericPolicy>();
		JsArray<PolicyJS> jsPolicies = getPolicyArray();
		if (jsPolicies != null) {
			for (int i = 0; i < jsPolicies.length(); i++)
				policies.add(jsPolicies.get(i));
		}
		return policies;
	}

	/**
	 * Gets the policy array.
	 * 
	 * @return the policy array
	 */
	public native final JsArray<PolicyJS> getPolicyArray() /*-{
															if (this["ns1.findPoliciesResponse"]["ns1.policySet"])
															return this["ns1.findPoliciesResponse"]["ns1.policySet"]["ns1.Policy"];
															else
															return null;
															}-*/;

	/**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.GetPoliciesResponse#isErrored()
	 */
	public native final boolean isErrored() /*-{
											if (this["ns1.findPoliciesResponse"]["ms.ack"] === "Success")
											return false;
											else
											return true;
											}-*/;
}
