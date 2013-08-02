package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.IMassUpdateDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;

public interface ContactMapperExt extends ISearchableDAO<ContactSearchCriteria>,
	IMassUpdateDAO<Contact, ContactSearchCriteria>{

    SimpleContact findContactById(int contactId);
}
