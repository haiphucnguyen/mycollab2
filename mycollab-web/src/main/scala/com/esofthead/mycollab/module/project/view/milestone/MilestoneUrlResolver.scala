package com.esofthead.mycollab.module.project.view.milestone

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.NumberSearchField
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria
import com.esofthead.mycollab.module.project.domain.{Milestone, SimpleMilestone}
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.service.MilestoneService
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{MilestoneScreenData, ProjectScreenData}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class MilestoneUrlResolver extends ProjectUrlResolver {
    this.addSubResolver("list", new ListUrlResolver)
    this.addSubResolver("add", new AddUrlResolver)
    this.addSubResolver("edit", new EditUrlResolver)
    this.addSubResolver("preview", new PreviewUrlResolver)

    private class ListUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Integer = new UrlTokenizer(params(0)).getInt
            val milestoneSearchCriteria: MilestoneSearchCriteria = new MilestoneSearchCriteria
            milestoneSearchCriteria.setProjectId(new NumberSearchField(projectId))
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new MilestoneScreenData.Search(milestoneSearchCriteria))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class AddUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Integer = new UrlTokenizer(params(0)).getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new MilestoneScreenData.Add(new Milestone))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class EditUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val token: UrlTokenizer = new UrlTokenizer(params(0))
            val projectId: Integer = token.getInt
            val milestoneid: Integer = token.getInt
            val milestoneService: MilestoneService = ApplicationContextUtil.getSpringBean(classOf[MilestoneService])
            val milestone: SimpleMilestone = milestoneService.findById(milestoneid, AppContext.getAccountId)
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new MilestoneScreenData.Edit(milestone))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class PreviewUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val token: UrlTokenizer = new UrlTokenizer(params(0))
            val projectId: Integer = token.getInt
            val milestoneid: Integer = token.getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new MilestoneScreenData.Read(milestoneid))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

}
