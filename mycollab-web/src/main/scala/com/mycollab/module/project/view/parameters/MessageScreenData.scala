package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.criteria.MessageSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object MessageScreenData {

  class Read(params:Integer) extends ScreenData[Integer](params){}

  class Search(params:MessageSearchCriteria) extends ScreenData[MessageSearchCriteria](params){}
}
