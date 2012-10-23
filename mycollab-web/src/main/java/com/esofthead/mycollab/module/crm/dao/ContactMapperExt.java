package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;

public interface ContactMapperExt {
	public List findPagableList(ContactSearchCriteria criteria,
			RowBounds rowBounds);

	public int getTotalCount(ContactSearchCriteria criteria);
	
	SimpleContact findContactById(int contactId);
}
