package com.esofthead.mycollab.mobile.module.project.view.settings

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.{StringSearchField, NumberSearchField}
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.project.ProjectUrlResolver
import com.esofthead.mycollab.mobile.module.project.events.ProjectEvent
import com.esofthead.mycollab.mobile.module.project.view.parameters.{ProjectMemberScreenData, ProjectScreenData}
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria
import com.esofthead.mycollab.module.project.service.ProjectMemberService
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class UserUrlResolver extends ProjectUrlResolver {
    this.addSubResolver("list", new ListUrlResolver)
    this.addSubResolver("preview", new PreviewUrlResolver)
    this.addSubResolver("edit", new EditUrlResolver)
    this.addSubResolver("invite", new InviteUrlResolver)

    private class ListUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Int = new UrlTokenizer(params(0)).getInt
            val memberSearchCriteria: ProjectMemberSearchCriteria = new ProjectMemberSearchCriteria
            memberSearchCriteria.setProjectId(new NumberSearchField(projectId))
            memberSearchCriteria.setStatus(new StringSearchField(ProjectMemberStatusConstants.ACTIVE))
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectMemberScreenData.Search(memberSearchCriteria))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class PreviewUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val token: UrlTokenizer = new UrlTokenizer(params(0))
            val projectId: Int = token.getInt
            val memberName: String = token.getString
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectMemberScreenData.Read(memberName))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class EditUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val token: UrlTokenizer = new UrlTokenizer(params(0))
            val projectId: Int = token.getInt
            val memberId: Int = token.getInt
            val memberService: ProjectMemberService = ApplicationContextUtil.getSpringBean(classOf[ProjectMemberService])
            val member: SimpleProjectMember = memberService.findById(memberId, AppContext.getAccountId)
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectMemberScreenData.Edit(member))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class InviteUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val token: UrlTokenizer = new UrlTokenizer(params(0))
            val projectId: Int = token.getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectMemberScreenData.InviteProjectMembers)
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

}
