package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.ProjectMember
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object ProjectMemberScreenData {

  class Search(params:ProjectMemberSearchCriteria) extends ScreenData[ProjectMemberSearchCriteria](params) {}

  class Add(params:ProjectMember) extends ScreenData[ProjectMember](params){}

  class InviteProjectMembers extends ScreenData {}

  class Read(params:Object) extends ScreenData[Object](params) {}
}
