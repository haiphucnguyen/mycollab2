package com.mycollab.mobile.module.project.view.parameters

import com.mycollab.module.project.domain.SimpleProjectMember
import com.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object ProjectMemberScreenData {
    class Search(param: ProjectMemberSearchCriteria) : ScreenData<ProjectMemberSearchCriteria>(param)

    class InviteProjectMembers() : ScreenData<*>(null)

    class Read(param: Any?) : ScreenData<*>(param)

    class Edit(param: SimpleProjectMember) : ScreenData<SimpleProjectMember>(param)
}