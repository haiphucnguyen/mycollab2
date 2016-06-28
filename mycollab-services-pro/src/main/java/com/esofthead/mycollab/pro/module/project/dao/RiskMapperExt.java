package com.esofthead.mycollab.pro.module.project.dao;

import com.esofthead.mycollab.core.persistence.IMassUpdateDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface RiskMapperExt extends ISearchableDAO<RiskSearchCriteria>, IMassUpdateDAO<Risk, RiskSearchCriteria> {

    SimpleRisk findRiskById(int riskId);
}
