package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object MessageScreenData {

  class Read(params:Integer) extends ScreenData[Integer](params){}

  class Search(params:MessageSearchCriteria) extends ScreenData[MessageSearchCriteria](params){}
}
