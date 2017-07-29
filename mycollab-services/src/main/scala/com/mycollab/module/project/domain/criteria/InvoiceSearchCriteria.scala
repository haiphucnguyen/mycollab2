package com.mycollab.module.project.domain.criteria

import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.db.query.{CacheParamMapper, PropertyListParam}
import com.mycollab.module.project.ProjectTypeConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.10
  */
class InvoiceSearchCriteria extends SearchCriteria {

}

object InvoiceSearchCriteria {
  val p_status = CacheParamMapper.register(ProjectTypeConstants.INVOICE, null,
    new PropertyListParam[String]("status", "m_prj_invoice", "status"))
  val p_projectIds = CacheParamMapper.register(ProjectTypeConstants.INVOICE, null,
    new PropertyListParam[Integer]("projectid", "m_prj_invoice", "projectId"))
}
