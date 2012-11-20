package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;

public interface ContactMapperExt extends ISearchableDAO<ContactSearchCriteria> {

	public abstract SimpleContact findContactById(int contactId);
}
