package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.IMassUpdateDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.crm.domain.Lead;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import org.apache.ibatis.annotations.Param;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface LeadMapperExt extends ISearchableDAO<LeadSearchCriteria>, IMassUpdateDAO<Lead, LeadSearchCriteria> {

    SimpleLead findById(Integer leadId);

    SimpleLead findConvertedLeadOfAccount(@Param("accountId") Integer accountId);

    SimpleLead findConvertedLeadOfContact(@Param("contactId") Integer contactId);

    SimpleLead findConvertedLeadOfOpportunity(@Param("opportunityId") Integer opportunity);
}
