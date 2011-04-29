package org.ebayopensource.turmeric.groupmembershipservice.provider;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ebayopensource.turmeric.runtime.common.impl.utils.LogManager;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroupType;
import org.ebayopensource.turmeric.utils.DomParseUtils;
import org.ebayopensource.turmeric.utils.config.exceptions.PolicyProviderException;
import org.ebayopensource.turmeric.utils.config.impl.BaseFilePolicyProvider;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The Class CalculatedGroupMembershipProviderImpl.
 */
public class CalculatedGroupMembershipProviderImpl extends
		BaseFilePolicyProvider {
	
	/** The Constant CALCULATED_SUBJECT_GROUP_CONFIG_FILENAME. */
	protected static final String CALCULATED_SUBJECT_GROUP_CONFIG_FILENAME = "CalculatedSubjectGroupConfig.xml";

	/** The Constant CALCULATED_SUBJECT_GROUP_CONFIG_SCHEMA. */
	protected static final String CALCULATED_SUBJECT_GROUP_CONFIG_SCHEMA = "CalculatedSubjectGroupConfig.xsd";

	/** The Constant CALCULATED_SUBJECT_GROUP_CONFIG_ROOT_ELEMENT. */
	protected static final String CALCULATED_SUBJECT_GROUP_CONFIG_ROOT_ELEMENT = "subject-group-config";

	/** The m_subject group to subject group class info cache. */
	protected Map<String, SubjectGroupType> m_subjectGroupToSubjectGroupClassInfoCache = new HashMap<String, SubjectGroupType>();
	
	private static Logger s_logger = LogManager.getInstance(CalculatedGroupMembershipProviderImpl.class);
	
	private static CalculatedGroupMembershipProviderImpl s_instance = new CalculatedGroupMembershipProviderImpl();

	private CalculatedGroupMembershipProviderImpl() {	
	}
	
	/**
	 * Gets the single instance of CalculatedGroupMembershipProviderImpl.
	 * 
	 * @return single instance of CalculatedGroupMembershipProviderImpl
	 */
	public static CalculatedGroupMembershipProviderImpl getInstance() {
		return s_instance;
	}
	
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.utils.config.impl.BaseFilePolicyProvider#getPolicyFileName()
	 */
	@Override
	protected String getPolicyFileName() {
		return m_policyPath  + CALCULATED_SUBJECT_GROUP_CONFIG_FILENAME;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.utils.config.impl.BaseFilePolicyProvider#getPolicyRootElement()
	 */
	@Override
	protected String getPolicyRootElement() {
		return CALCULATED_SUBJECT_GROUP_CONFIG_ROOT_ELEMENT;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.utils.config.impl.BaseFilePolicyProvider#getPolicySchemaName()
	 */
	@Override
	protected String getPolicySchemaName() {
		return m_schemaPath + CALCULATED_SUBJECT_GROUP_CONFIG_SCHEMA;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.utils.config.impl.BaseFilePolicyProvider#mapPolicyData(org.w3c.dom.Element)
	 */
	@Override
	protected void mapPolicyData(Element policyData)
			throws PolicyProviderException {
		if (policyData == null)
			return;

		m_subjectGroupToSubjectGroupClassInfoCache.clear();
		
		try {
			NodeList subjectGroupList = DomParseUtils
					.getImmediateChildrenByTagName(
							policyData, "subject-group");
			if (subjectGroupList == null || subjectGroupList.getLength() <= 0)
				return;

			// iterate through the subject group list
			for (int i = 0; i < subjectGroupList.getLength(); i++) {
				Element subjectGroupElement = (Element) subjectGroupList
						.item(i);
				String subjectGroupName = DomParseUtils.getElementText(
						getPolicyFileName(), subjectGroupElement, "name");
				String subjectGroupType = DomParseUtils.getElementText(
						getPolicyFileName(), subjectGroupElement, "subjectType");
				String subjectGroupClassName = DomParseUtils.getElementText(
						getPolicyFileName(), subjectGroupElement, "className");

				SubjectGroupType sg = new SubjectGroupType();
				sg.setName(subjectGroupName);
				sg.setDomain(subjectGroupType);
				sg.setCalculator(subjectGroupClassName);
				
				m_subjectGroupToSubjectGroupClassInfoCache.put(subjectGroupName,
						sg);
					
			}
		} catch (Exception e) {
			s_logger.log(Level.SEVERE, "error", e);
			throw new PolicyProviderException(
					"Error in mapping calculated subject group policy: "
							+ e.getMessage(), e);
		}
		
	}
	
	/**
	 * Gets the calculated subject group config file name.
	 * 
	 * @return the calculated subject group config file name
	 */
	public String getCalculatedSubjectGroupConfigFileName() {
		return m_policyPath + CALCULATED_SUBJECT_GROUP_CONFIG_FILENAME;
	}

	/**
	 * Gets the calculated subject group config schema name.
	 * 
	 * @return the calculated subject group config schema name
	 */
	public String getCalculatedSubjectGroupConfigSchemaName() {
		return m_schemaPath + CALCULATED_SUBJECT_GROUP_CONFIG_SCHEMA;
	}

	/**
	 * Gets the calculated subject group config root element.
	 * 
	 * @return the calculated subject group config root element
	 */
	public String getCalculatedSubjectGroupConfigRootElement() {
		return CALCULATED_SUBJECT_GROUP_CONFIG_ROOT_ELEMENT;
	}

	/**
	 * Gets the calculated sg.
	 * 
	 * @param subjectGroup
	 *            the subject group
	 * @return the calculated sg
	 */
	public SubjectGroupType getCalculatedSG(SubjectGroupType subjectGroup) {

		return m_subjectGroupToSubjectGroupClassInfoCache.get(subjectGroup.getCalculator());
		
	}
	
	/**
	 * Gets the all calculated s gs.
	 * 
	 * @return the all calculated s gs
	 */
	public Collection<SubjectGroupType> getAllCalculatedSGs() {
		return m_subjectGroupToSubjectGroupClassInfoCache.values();
	}

}
