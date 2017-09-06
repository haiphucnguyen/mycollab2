package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.ProjectRole
import com.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object ProjectRoleScreenData {

  class Search(params: ProjectRoleSearchCriteria) extends ScreenData[ProjectRoleSearchCriteria](params) {}

  class Add(params: ProjectRole) extends ScreenData[ProjectRole](params) {}

  class Read(params: Integer) extends ScreenData[Integer](params) {}
}
