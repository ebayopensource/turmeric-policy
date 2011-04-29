package org.ebayopensource.turmeric.policyservice.model;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.ebayopensource.turmeric.policyservice.exceptions.PolicyProviderException.Category;
import org.ebayopensource.turmeric.security.v1.services.EntityHistory;
import org.ebayopensource.turmeric.security.v1.services.OperationKey;
import org.ebayopensource.turmeric.security.v1.services.PolicyKey;
import org.ebayopensource.turmeric.security.v1.services.ResourceKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectGroupKey;
import org.ebayopensource.turmeric.security.v1.services.SubjectKey;
import org.ebayopensource.turmeric.utils.jpa.model.AuditInfo;
import org.ebayopensource.turmeric.utils.jpa.model.AuditablePersistent;

/**
 * The Class AuditHistory.
 */
@Entity
public class AuditHistory extends AuditablePersistent {
    private static final String UNKNOWN = "unknown";

	private static final String AUDIT_TEMPLATE = "%1$s[%4$s:%3$s:%2$d] %5$s @[%8$s:%7$s:%6$d]";
    
	private String category;
	private long entityId;
	private String entityName;
	private String entityType;
	private String operationType;
	private long subjectId;
	private String subjectName;
	private String subjectType;
	private String comment;
	
	/**
	 * Instantiates a new audit history.
	 */
	public AuditHistory() {}
		
	/**
	 * Instantiates a new audit history.
	 * 
	 * @param category
	 *            the category
	 * @param entityId
	 *            the entity id
	 * @param entityName
	 *            the entity name
	 * @param entityType
	 *            the entity type
	 * @param operationType
	 *            the operation type
	 * @param subjectId
	 *            the subject id
	 * @param subjectName
	 *            the subject name
	 * @param subjectType
	 *            the subject type
	 */
	public AuditHistory(String category, long entityId, String entityName, String entityType,
						String operationType, long subjectId, String subjectName, String subjectType)
	{
		this.category = category;
		this.entityId = entityId;
		this.entityName = entityName;
		this.entityType = entityType;
		this.operationType = operationType;
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectType = subjectType;
		this.comment = String.format(AUDIT_TEMPLATE, category, entityId, entityName, entityType,
		                operationType, subjectId, subjectName, subjectType);
	}
	
	/**
	 * Gets the category.
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Sets the category.
	 * 
	 * @param category
	 *            the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Gets the entity id.
	 * 
	 * @return the entity id
	 */
	public long getEntityId() {
		return entityId;
	}
	
	/**
	 * Sets the entity id.
	 * 
	 * @param entityId
	 *            the new entity id
	 */
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	
	/**
	 * Gets the entity name.
	 * 
	 * @return the entity name
	 */
	public String getEntityName() {
		return entityName;
	}
	
	/**
	 * Sets the entity name.
	 * 
	 * @param entityName
	 *            the new entity name
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	/**
	 * Gets the entity type.
	 * 
	 * @return the entity type
	 */
	public String getEntityType() {
		return entityType;
	}
	
	/**
	 * Sets the entity type.
	 * 
	 * @param entityType
	 *            the new entity type
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	/**
	 * Gets the operation type.
	 * 
	 * @return the operation type
	 */
	public String getOperationType() {
		return operationType;
	}
	
	/**
	 * Sets the operation type.
	 * 
	 * @param operationType
	 *            the new operation type
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	/**
	 * Gets the subject id.
	 * 
	 * @return the subject id
	 */
	public long getSubjectId() {
		return subjectId;
	}
	
	/**
	 * Sets the subject id.
	 * 
	 * @param subjectId
	 *            the new subject id
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	
	/**
	 * Gets the subject name.
	 * 
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}
	
	/**
	 * Sets the subject name.
	 * 
	 * @param subjectName
	 *            the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * Gets the subject type.
	 * 
	 * @return the subject type
	 */
	public String getSubjectType() {
		return subjectType;
	}
	
	/**
	 * Sets the subject type.
	 * 
	 * @param subjectType
	 *            the new subject type
	 */
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

    /**
	 * Gets the comment.
	 * 
	 * @return the comment
	 */
    public String getComment() {
        return comment;
    }

    /**
	 * Sets the comment.
	 * 
	 * @param comment
	 *            the new comment
	 */
    public void setComment(String comment) {
        this.comment = comment;
    }   
    
	/**
	 * New record.
	 * 
	 * @param subjectKey
	 *            the subject key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 * @return the audit history
	 */
	public static AuditHistory newRecord(SubjectKey subjectKey, String operationType, SubjectKey loginSubject) {
		String subjectName = UNKNOWN;
		long subjectId = 0;
		String subjectType = UNKNOWN;
		if (loginSubject != null) {
			subjectName = loginSubject.getSubjectName();
			subjectId = loginSubject.getSubjectId();
			subjectType = loginSubject.getSubjectType();
		}
		return new AuditHistory( Category.SUBJECT.name(), subjectKey.getSubjectId(), 
			subjectKey.getSubjectName(), subjectKey.getSubjectType(),
			operationType, subjectId, subjectName, subjectType);
	}

	/**
	 * New record.
	 * 
	 * @param subjectGroupKey
	 *            the subject group key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 * @return the audit history
	 */
	public static AuditHistory newRecord(SubjectGroupKey subjectGroupKey, String operationType, SubjectKey loginSubject) {
		String subjectName = UNKNOWN;
		long subjectId = 0;
		String subjectType = UNKNOWN;
		if (loginSubject != null) {
			subjectName = loginSubject.getSubjectName();
			subjectId = loginSubject.getSubjectId();
			subjectType = loginSubject.getSubjectType();
		}
		return new AuditHistory(Category.SUBJECTGROUP.name(), subjectGroupKey.getSubjectGroupId(),
			subjectGroupKey.getSubjectGroupName(), subjectGroupKey.getSubjectType(),
			operationType, subjectId, 
			subjectName, subjectType);
	}

	/**
	 * New record.
	 * 
	 * @param resourceKey
	 *            the resource key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 * @return the audit history
	 */
	public static AuditHistory newRecord(ResourceKey resourceKey, String operationType, SubjectKey loginSubject) {
		String subjectName = UNKNOWN;
		long subjectId = 0;
		String subjectType = UNKNOWN;
		if (loginSubject != null) {
			subjectName = loginSubject.getSubjectName();
			subjectId = loginSubject.getSubjectId();
			subjectType = loginSubject.getSubjectType();
		}
		return new AuditHistory(Category.RESOURCE.name(), resourceKey.getResourceId(),
			resourceKey.getResourceName(), resourceKey.getResourceType(),
			operationType, subjectId, subjectName, subjectType);
	}
	
	/**
	 * New record.
	 * 
	 * @param operationKey
	 *            the operation key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 * @return the audit history
	 */
	public static AuditHistory newRecord(OperationKey operationKey, String operationType, SubjectKey loginSubject) {
		String subjectName = UNKNOWN;
		long subjectId = 0;
		String subjectType = UNKNOWN;
		if (loginSubject != null) {
			subjectName = loginSubject.getSubjectName();
			subjectId = loginSubject.getSubjectId();
			subjectType = loginSubject.getSubjectType();
		}
		return new AuditHistory(Category.OPERATION.name(), operationKey.getOperationId(),
			operationKey.getOperationName(), operationKey.getResourceType(),
			operationType, subjectId, subjectName, subjectType);
	}
	
	/**
	 * New record.
	 * 
	 * @param policyKey
	 *            the policy key
	 * @param operationType
	 *            the operation type
	 * @param loginSubject
	 *            the login subject
	 * @return the audit history
	 */
	public static AuditHistory newRecord(PolicyKey policyKey, String operationType, SubjectKey loginSubject) {
		String subjectName = UNKNOWN;
		long subjectId = 0;
		String subjectType = UNKNOWN;
		if (loginSubject != null) {
			subjectName = loginSubject.getSubjectName();
			subjectId = loginSubject.getSubjectId();
			subjectType = loginSubject.getSubjectType();
		}
		return new AuditHistory(Category.POLICY.name(), policyKey.getPolicyId(),
			policyKey.getPolicyName(), policyKey.getPolicyType(),
			operationType, subjectId, subjectName, subjectType);
	}

    /**
	 * Convert.
	 * 
	 * @param auditEntry
	 *            the audit entry
	 * @return the entity history
	 */
    public static EntityHistory convert(AuditHistory auditEntry) {
        
        XMLGregorianCalendar xgcDate = null;
        try {
            GregorianCalendar gcDate = new GregorianCalendar();
            AuditInfo auditInfo = auditEntry.getAuditInfo();
            Date auditDate = auditInfo.getUpdatedOn();
            gcDate.setTime(auditDate == null ? auditInfo.getCreatedOn() : auditDate);
            xgcDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcDate);
        }
        catch (DatatypeConfigurationException ex) {}
        
        EntityHistory entityEntry = new EntityHistory();
        entityEntry.setAuditDate(xgcDate);
        entityEntry.setLoginSubject(auditEntry.getSubjectName());
        entityEntry.setAuditType(auditEntry.getOperationType());
        entityEntry.setComments(auditEntry.getComment());
        return entityEntry;
    }
}
