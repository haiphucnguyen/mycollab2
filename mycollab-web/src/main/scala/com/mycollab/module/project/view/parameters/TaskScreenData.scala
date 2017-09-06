package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.Task
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object TaskScreenData {

  class Read(param: Integer) extends ScreenData[Integer](param) {}

  class Edit(param: Task) extends ScreenData[Task](param) {}

  class Add(param: Task) extends ScreenData[Task](param) {}

  class GotoKanbanView extends ScreenData {}

}
