package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.Project
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object ProjectScreenData {

  class GotoList() extends ScreenData(null) {}

  class Goto(params: Integer) extends ScreenData[Integer](params) {}

  class Edit(params: Project) extends ScreenData[Project](params) {}

  class GotoTagList(params: Object) extends ScreenData[Object](params) {}

  class GotoFavorite() extends ScreenData[Object](null) {}

  class SearchItem(params: String) extends ScreenData[String](params) {}

  class GotoGanttChart extends ScreenData {}

  class GotoReportConsole extends ScreenData {}

  class GotoCalendarView extends ScreenData {}

}
