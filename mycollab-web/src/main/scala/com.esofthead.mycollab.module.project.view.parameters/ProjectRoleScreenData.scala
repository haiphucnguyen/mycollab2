package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.ProjectRole
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object ProjectRoleScreenData {

  class Search(params: ProjectRoleSearchCriteria) extends ScreenData[ProjectRoleSearchCriteria](params) {}

  class Add(params: ProjectRole) extends ScreenData[ProjectRole](params) {}

  class Read(params: Integer) extends ScreenData[Integer](params) {}
}
