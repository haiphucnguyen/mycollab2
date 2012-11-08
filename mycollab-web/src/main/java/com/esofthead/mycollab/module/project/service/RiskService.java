package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;

public interface RiskService extends
		IDefaultService<Integer, Risk, RiskSearchCriteria> {
}
