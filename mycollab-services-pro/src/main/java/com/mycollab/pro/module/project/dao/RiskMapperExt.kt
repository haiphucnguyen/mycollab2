package com.mycollab.pro.module.project.dao

import com.mycollab.db.persistence.IMassUpdateDAO
import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.module.project.domain.Risk
import com.mycollab.module.project.domain.SimpleRisk
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
interface RiskMapperExt : ISearchableDAO<RiskSearchCriteria>, IMassUpdateDAO<Risk, RiskSearchCriteria> {

    fun findRiskById(riskId: Int): SimpleRisk?
}
