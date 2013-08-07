package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleTarget;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetSearchCriteria;

public interface TargetMapperExt extends ISearchableDAO<TargetSearchCriteria> {

	SimpleTarget findTargetById(int targetId);
}
