package com.esofthead.mycollab.module.user.accountsettings.view.parameters

import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
object BillingScreenData {

  class CancelAccount(params:Object) extends ScreenData[Object](params){
    def this() = this(null)
  }

  class BillingSummary(params:Object) extends ScreenData[Object](params){
    def this() = this(null)
  }
}
