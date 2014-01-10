package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultSearchService;
import com.esofthead.mycollab.module.crm.dao.ContactOpportunityMapperExt;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactOpportunityService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
@Service
public class ContactOpportunityServiceImpl extends
		DefaultSearchService<ContactSearchCriteria> implements
		ContactOpportunityService {

	@Autowired
	private ContactOpportunityMapperExt contactOpportunityMapperExt;

	@Override
	public ISearchableDAO<ContactSearchCriteria> getSearchMapper() {
		return contactOpportunityMapperExt;
	}

}
