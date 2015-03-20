package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.Task
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object TaskScreenData {

  class Search(param: TaskFilterParameter) extends ScreenData[TaskFilterParameter](param) {}

  class Read(param: Integer) extends ScreenData[Integer](param) {}

  class Edit(param: Task) extends ScreenData[Task](param) {}

  class Add(param: Task) extends ScreenData[Task](param) {}

  class GanttChart extends ScreenData {}

}
