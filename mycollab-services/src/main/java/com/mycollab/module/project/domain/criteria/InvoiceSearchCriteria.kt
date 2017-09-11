package com.mycollab.module.project.domain.criteria

import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.db.query.CacheParamMapper
import com.mycollab.db.query.PropertyListParam
import com.mycollab.module.project.ProjectTypeConstants

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class InvoiceSearchCriteria: SearchCriteria {
    constructor()

    companion object {
        val p_status = CacheParamMapper.register(ProjectTypeConstants.INVOICE, null,
                 PropertyListParam<String>("status", "m_prj_invoice", "status"))
        val p_projectIds = CacheParamMapper.register(ProjectTypeConstants.INVOICE, null,
                 PropertyListParam<Integer>("projectid", "m_prj_invoice", "projectId"))
    }
}