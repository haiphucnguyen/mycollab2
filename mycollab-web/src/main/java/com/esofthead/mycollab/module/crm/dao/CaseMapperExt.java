package com.esofthead.mycollab.module.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;

public interface CaseMapperExt extends ISearchableDAO<CaseSearchCriteria> {
	SimpleCase findCaseById(int caseId);
	
	public abstract void upateCaseBySearchCriteria(@Param("record") CaseWithBLOBs record, @Param("searchCriteria")SearchCriteria searchCriteria);
}
