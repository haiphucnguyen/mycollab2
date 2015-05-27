package com.esofthead.mycollab.module.user.accountsettings.view.parameters

import com.esofthead.mycollab.vaadin.mvp.ScreenData

import scala.beans.BeanProperty

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
object CustomizeScreenData {
  class ThemeCustomize(params:Object) extends ScreenData[Object](params){
    def this() = this(null)
  }

  class LogoUpload(params:Object, @BeanProperty var extraParam: Object) extends ScreenData[Object](params) {

  }
}
