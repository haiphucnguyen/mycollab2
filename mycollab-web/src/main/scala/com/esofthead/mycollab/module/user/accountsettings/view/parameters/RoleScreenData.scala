package com.esofthead.mycollab.module.user.accountsettings.view.parameters

import com.esofthead.mycollab.module.user.domain.Role
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
object RoleScreenData {

  class Read(params:Integer) extends ScreenData[Integer](params) {}

  class Add(params:Role) extends ScreenData[Role](params) {}

  class Edit(params:Role) extends ScreenData[Role](params) {}

  class Search(params:RoleSearchCriteria) extends ScreenData[RoleSearchCriteria](params) {}
}
