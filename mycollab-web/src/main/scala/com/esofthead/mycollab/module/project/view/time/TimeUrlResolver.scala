package com.esofthead.mycollab.module.project.view.time

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.{SearchField, SetSearchField}
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{TimeTrackingScreenData, ProjectScreenData}
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class TimeUrlResolver extends ProjectUrlResolver {
    this.addSubResolver("list", new ListUrlResolver)

    private class ListUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Integer = new UrlTokenizer(params(0)).getInt
            val searchCriteria: ItemTimeLoggingSearchCriteria = new ItemTimeLoggingSearchCriteria
            searchCriteria.setProjectIds(new SetSearchField[Integer](projectId))
            searchCriteria.setRangeDate(ItemTimeLoggingSearchCriteria.getCurrentRangeDateOfWeekSearchField)
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new TimeTrackingScreenData.Search(searchCriteria))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

}
