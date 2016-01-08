package com.esofthead.mycollab.mobile.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.SimpleTask
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object TaskScreenData {

  class Search(param: TaskSearchCriteria) extends ScreenData(param) {}

  class Read(param: Integer) extends ScreenData(param) {}

  class Edit(param: SimpleTask) extends ScreenData(param) {}

  class Add(param: SimpleTask) extends ScreenData(param) {}

}
