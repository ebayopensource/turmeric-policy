package org.ebayopensource.turmeric.policyservice.model;

/**
 * The Interface BasicAuthDAO.
 */
public interface BasicAuthDAO {

    /**
	 * Gets the basic auth.
	 * 
	 * @param subjectName
	 *            the subject name
	 * @return the basic auth
	 */
    BasicAuth getBasicAuth(String subjectName);

}
