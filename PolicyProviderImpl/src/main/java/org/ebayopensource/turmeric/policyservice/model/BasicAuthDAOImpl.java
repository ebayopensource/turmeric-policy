package org.ebayopensource.turmeric.policyservice.model;

import org.ebayopensource.turmeric.utils.jpa.AbstractDAO;

/**
 * The Class BasicAuthDAOImpl.
 */
public class BasicAuthDAOImpl extends AbstractDAO implements BasicAuthDAO {

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policyservice.model.BasicAuthDAO#getBasicAuth(java.lang.String)
     */
    @Override
    public BasicAuth getBasicAuth(String subjectName) {
        return getSingleResultOrNull(BasicAuth.class, "subjectName", subjectName);
    }
}
