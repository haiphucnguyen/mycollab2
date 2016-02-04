package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.Project
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object ProjectScreenData {

  class Goto(params: Integer) extends ScreenData[Integer](params) {}

  class Edit(params: Project) extends ScreenData[Project](params) {}

  class GotoTagList(params: Object) extends ScreenData[Object](params) {}

  class SearchItem(params: String) extends ScreenData[String](params) {}

  class GotoGanttChart extends ScreenData {}

  class GotoCalendarView extends ScreenData {}

}
