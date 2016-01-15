package com.esofthead.mycollab.mobile.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object ProjectMemberScreenData {

  class Search(param: ProjectMemberSearchCriteria) extends ScreenData[ProjectMemberSearchCriteria](param) {}

  class InviteProjectMembers() extends ScreenData[Any](null) {}

  class Read(param: Any) extends ScreenData[Any](param) {}

  class Edit(param: SimpleProjectMember) extends ScreenData[SimpleProjectMember](param) {}

}
