package com.mycollab.module.project.httpmapping

import java.text.ParseException
import java.util.{Date, GregorianCalendar}

import com.mycollab.db.arguments.DateSearchField
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.domain.criteria.StandupReportSearchCriteria
import com.mycollab.module.project.event.StandUpEvent
import org.joda.time.format.DateTimeFormat

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class StandupUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)

  private val simpleDateFormat = DateTimeFormat.forPattern("MM-dd-yyyy");

  /**
    * @param dateVal
    * @return
    */
  private def parseDate(dateVal: String): Date = {
    try {
      return simpleDateFormat.parseDateTime(dateVal).toDate
    } catch {
      case e: ParseException => new GregorianCalendar().getTime
    }
  }

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val searchCriteria = new StandupReportSearchCriteria
      if (params.length == 0) {
        searchCriteria.setOnDate(new DateSearchField(new GregorianCalendar().getTime))
      } else {
        val date = parseDate(params(0))
        searchCriteria.setOnDate(new DateSearchField(date))
      }
      EventBusFactory.getInstance().post(new StandUpEvent.GotoList(this, searchCriteria))
    }
  }

}
