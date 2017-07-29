package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.ProjectMember
import com.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object ProjectMemberScreenData {

  class Search(params:ProjectMemberSearchCriteria) extends ScreenData[ProjectMemberSearchCriteria](params) {}

  class Add(params:ProjectMember) extends ScreenData[ProjectMember](params){}

  class InviteProjectMembers extends ScreenData {}

  class Read(params:Any) extends ScreenData[Any](params) {}
}
