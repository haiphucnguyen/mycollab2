package com.esofthead.mycollab.module.project.view.settings

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.{StringSearchField, NumberSearchField}
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{ProjectMemberScreenData, ProjectScreenData}
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class UserUrlResolver extends ProjectUrlResolver {
    this.addSubResolver("list", new ListUrlResolver)
    this.addSubResolver("preview", new PreviewUrlResolver)
    this.addSubResolver("add", new AddUrlResolver)

    private class ListUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Integer = new UrlTokenizer(params(0)).getInt
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
            val projectId: Integer = token.getInt
            val memberName: String = token.getString
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new ProjectMemberScreenData.Read(memberName))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class AddUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val token: UrlTokenizer = new UrlTokenizer(params(0))
            val projectId: Integer = token.getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new ProjectMemberScreenData.InviteProjectMembers)
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }
}
