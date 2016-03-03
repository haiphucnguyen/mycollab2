package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.crm.domain.Account
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.9
  */
object ClientScreenData {

  class Read(params: Integer) extends ScreenData[Integer](params) {}

  class Add(param: Account) extends ScreenData[Account](param) {}

  class Edit(param: Account) extends ScreenData[Account](param) {}

  class Search(param: AccountSearchCriteria) extends ScreenData[AccountSearchCriteria](param) {}

}
