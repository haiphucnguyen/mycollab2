package com.esofthead.mycollab.module.user.accountsettings.view.parameters

import com.esofthead.mycollab.module.user.domain.{User, SimpleUser}
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
object UserScreenData {

  class Read(params:String) extends ScreenData[String](params) {}

  class Add(params:SimpleUser) extends ScreenData[SimpleUser](params) {}

  class Edit(params:User) extends ScreenData[User](params) {}

  class Search(params:UserSearchCriteria) extends ScreenData[UserSearchCriteria](params) {}
}
