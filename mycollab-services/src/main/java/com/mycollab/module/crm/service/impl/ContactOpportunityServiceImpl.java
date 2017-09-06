package com.mycollab.module.crm.service.impl;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultSearchService;
import com.mycollab.module.crm.dao.ContactOpportunityMapperExt;
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.mycollab.module.crm.service.ContactOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@Service
public class ContactOpportunityServiceImpl extends DefaultSearchService<ContactSearchCriteria> implements
        ContactOpportunityService {

    @Autowired
    private ContactOpportunityMapperExt contactOpportunityMapperExt;

    @Override
    public ISearchableDAO<ContactSearchCriteria> getSearchMapper() {
        return contactOpportunityMapperExt;
    }

}
