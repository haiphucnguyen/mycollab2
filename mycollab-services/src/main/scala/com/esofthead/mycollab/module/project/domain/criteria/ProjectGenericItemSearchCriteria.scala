package com.esofthead.mycollab.module.project.domain.criteria

import com.esofthead.mycollab.core.arguments.{SearchCriteria, SetSearchField, StringSearchField}

import scala.beans.BeanProperty

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
class ProjectGenericItemSearchCriteria extends SearchCriteria {
  @BeanProperty var prjKeys: SetSearchField[Integer] = _
  @BeanProperty var txtValue: StringSearchField = _
}
