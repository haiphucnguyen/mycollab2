package com.mycollab.module.project.view.parameters

import com.mycollab.module.crm.domain.Account
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.9
  */
object ClientScreenData {

  class Read(params: Integer) extends ScreenData[Integer](params) {}

  class Add(param: Account) extends ScreenData[Account](param) {}

  class Search(param: AccountSearchCriteria) extends ScreenData[AccountSearchCriteria](param) {}

}
