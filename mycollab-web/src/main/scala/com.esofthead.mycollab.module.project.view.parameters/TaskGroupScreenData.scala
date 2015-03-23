package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.TaskList
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object TaskGroupScreenData {

  class GotoDashboard extends ScreenData {}

  class Read(param: Integer) extends ScreenData[Integer](param) {}

  class ReorderTaskListRequest extends ScreenData {}

  class GotoGanttChartView extends ScreenData {}

  class Edit(param: TaskList) extends ScreenData[TaskList](param) {}

  class Add(param: TaskList) extends ScreenData[TaskList](param) {}

}
