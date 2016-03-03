package com.esofthead.mycollab.module.project.view.standup

import java.text.ParseException
import java.util.{Date, GregorianCalendar}

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.{DateSearchField, NumberSearchField}
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.service.StandupReportService
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{ProjectScreenData, StandupScreenData}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain
import org.apache.commons.lang3.time.FastDateFormat

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class StandupUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new PreviewUrlResolver)

  private val simpleDateFormat: FastDateFormat = FastDateFormat.getInstance("MM/dd/yyyy")

  /**
    * @param dateVal
    * @return
    */
  private def parseDate(dateVal: String): Date = {
    try {
      return simpleDateFormat.parse(dateVal)
    }
    catch {
      case e: ParseException => new GregorianCalendar().getTime
    }
  }

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val standupSearchCriteria = new StandupReportSearchCriteria
      standupSearchCriteria.setProjectId(new NumberSearchField(projectId))
      if (token.hasMoreTokens) {
        val date = parseDate(token.getString)
        standupSearchCriteria.setOnDate(new DateSearchField(date))
      }
      else {
        standupSearchCriteria.setOnDate(new DateSearchField(new GregorianCalendar().getTime))
      }
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new StandupScreenData.Search(standupSearchCriteria))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val onDate = parseDate(token.getString)
      val reportService = ApplicationContextUtil.getSpringBean(classOf[StandupReportService])
      var report = reportService.findStandupReportByDateUser(projectId, AppContext.getUsername,
        onDate, AppContext.getAccountId)
      if (report == null) {
        report = new SimpleStandupReport
      }
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new StandupScreenData.Add(report))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

}
