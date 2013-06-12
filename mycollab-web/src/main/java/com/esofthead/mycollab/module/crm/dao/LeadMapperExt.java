package com.esofthead.mycollab.module.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;

public interface LeadMapperExt extends ISearchableDAO<LeadSearchCriteria> {

	SimpleLead findLeadById(int leadId);
	
	public abstract void upateLeadBySearchCriteria(@Param("record") Lead record, @Param("searchCriteria")SearchCriteria searchCriteria);
}
