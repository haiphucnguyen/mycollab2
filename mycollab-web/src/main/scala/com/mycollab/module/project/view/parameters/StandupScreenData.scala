package com.mycollab.module.project.view.parameters

import java.util.Date

import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object StandupScreenData {

  class Search(param: Date) extends ScreenData[Date](param) {}

}
