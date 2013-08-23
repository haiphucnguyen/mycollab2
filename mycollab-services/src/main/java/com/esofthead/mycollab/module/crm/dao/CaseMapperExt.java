package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.IMassUpdateDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;

public interface CaseMapperExt extends ISearchableDAO<CaseSearchCriteria> ,
	IMassUpdateDAO<CaseWithBLOBs, CaseSearchCriteria>{
	
	SimpleCase findById(int caseId);
}
