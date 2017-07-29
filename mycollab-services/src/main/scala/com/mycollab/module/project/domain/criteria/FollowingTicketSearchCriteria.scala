package com.mycollab.module.project.domain.criteria

import com.mycollab.common.domain.criteria.MonitorSearchCriteria
import com.mycollab.db.arguments.StringSearchField

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.2.10
  */
class FollowingTicketSearchCriteria extends MonitorSearchCriteria {
  @BeanProperty var name: StringSearchField = _
}
