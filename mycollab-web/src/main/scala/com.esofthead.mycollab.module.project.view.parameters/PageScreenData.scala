package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.page.domain.Page
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object PageScreenData {

  class Read(params:Page) extends ScreenData[Page](params){}

  class Add(params:Page) extends ScreenData[Page](params){}

  class Edit(params:Page) extends ScreenData[Page](params){}

  class Search(params:String) extends ScreenData[String](params){}
}
