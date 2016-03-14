package com.esofthead.mycollab.premium.module.user.accountsettings.view

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.module.user.events.UserEvent
import com.esofthead.mycollab.module.user.service.UserService
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class UserUrlResolver extends AccountSettingUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)

  private class ListUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new UserEvent.GotoList(ListUrlResolver.this, null))
    }
  }

  private class AddUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new UserEvent.GotoAdd(AddUrlResolver.this, null))
    }
  }

  private class EditUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      val username = new UrlTokenizer(params(0)).getString
      val userService = ApplicationContextUtil.getSpringBean(classOf[UserService])
      val user = userService.findUserByUserNameInAccount(username, AppContext.getAccountId)
      EventBusFactory.getInstance.post(new UserEvent.GotoEdit(EditUrlResolver.this, user))
    }
  }

  private class PreviewUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      val username = new UrlTokenizer(params(0)).getString
      EventBusFactory.getInstance.post(new UserEvent.GotoRead(PreviewUrlResolver.this, username))
    }
  }

}
