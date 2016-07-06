package com.mycollab.pro.module.project.dao;

import com.mycollab.db.persistence.IMassUpdateDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface RiskMapperExt extends ISearchableDAO<RiskSearchCriteria>, IMassUpdateDAO<Risk, RiskSearchCriteria> {

    SimpleRisk findRiskById(int riskId);
}
