package com.mycollab.vaadin.mvp

import com.mycollab.db.arguments.SearchCriteria

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
class ScreenData[P](@BeanProperty var params: P) {}

object ScreenData {

  class Add[P](params: P) extends ScreenData[P](params) {}

  class Edit[P](params: P) extends ScreenData[P](params) {}

  class Preview[P](params: P) extends ScreenData[P](params) {}

  class Search[P <: SearchCriteria](params: P) extends ScreenData(params) {}

}