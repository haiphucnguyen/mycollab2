package com.mycollab.module.project.domain.criteria

import com.mycollab.db.arguments.{SearchCriteria, SetSearchField, StringSearchField}

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
class ProjectGenericItemSearchCriteria extends SearchCriteria {
  @BeanProperty var prjKeys: SetSearchField[Integer] = _
  @BeanProperty var txtValue: StringSearchField = _
  @BeanProperty var createdUsers: SetSearchField[String] = _
  @BeanProperty var types: SetSearchField[String] = _
  @BeanProperty var monitorProjectIds: SetSearchField[Integer] = _
  @BeanProperty var tagNames: SetSearchField[String] = null
}
