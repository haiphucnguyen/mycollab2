package com.esofthead.mycollab.module.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;

public interface ContactMapperExt extends ISearchableDAO<ContactSearchCriteria> {

    SimpleContact findContactById(int contactId);
    public abstract void upateContactBySearchCriteria(@Param("record") Contact record, @Param("searchCriteria")SearchCriteria searchCriteria);
}
