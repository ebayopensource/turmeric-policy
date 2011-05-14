package org.ebayopensource.turmeric.groupmembershipservice.provider;

import org.ebayopensource.turmeric.groupmembershipservice.exceptions.GroupMembershipException;
import org.ebayopensource.turmeric.security.v1.services.SubjectType;

/**
 * The Class DoNothingCalculatedSubjectGroupImpl.
 */
public class DoNothingCalculatedSubjectGroupImpl implements BaseCalculatedSubjectGroup {

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.groupmembershipservice.provider.BaseCalculatedSubjectGroup#contains(org.ebayopensource.turmeric.security.v1.services.SubjectType)
     */
    @Override
    public boolean contains(SubjectType subject) throws GroupMembershipException {
       return true;
    }

}
