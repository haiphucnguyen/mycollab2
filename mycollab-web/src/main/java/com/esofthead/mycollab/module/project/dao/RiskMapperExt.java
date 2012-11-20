package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;

public interface RiskMapperExt extends ISearchableDAO<RiskSearchCriteria>{
	void insertAndReturnKey(Risk risk);
}
