package com.esofthead.mycollab.mobile.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object MessageScreenData {

  class Add extends ScreenData[Any](null) {}

  class Read(param: Integer) extends ScreenData[Integer](param) {}

  class Search(param: MessageSearchCriteria) extends ScreenData[MessageSearchCriteria](param) {}
}
