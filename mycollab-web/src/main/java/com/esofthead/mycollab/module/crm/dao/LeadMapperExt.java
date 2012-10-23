package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;

public interface LeadMapperExt {
	public List findPagableList(LeadSearchCriteria criteria, RowBounds rowBounds);

	public int getTotalCount(LeadSearchCriteria criteria);
	
	SimpleLead findLeadById(int leadId);
}
