package com.mycollab.module.project.view.time

import com.mycollab.common.UrlTokenizer
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.db.query.{DateParam, VariableInjector}
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import com.mycollab.module.project.events.ProjectEvent
import com.mycollab.module.project.view.ProjectUrlResolver
import com.mycollab.module.project.view.parameters.{ProjectScreenData, TimeTrackingScreenData}
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class TimeUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  
  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = UrlTokenizer(params(0)).getInt
      val searchCriteria = new ItemTimeLoggingSearchCriteria
      searchCriteria.setProjectIds(new SetSearchField[Integer](projectId))
      searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates, VariableInjector.THIS_WEEK))
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new TimeTrackingScreenData.Search(searchCriteria))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
}

