package com.esofthead.mycollab.module.user.accountsettings.view

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
class UserUrlResolver extends AccountUrlResolver {
    this.addSubResolver("list", new ListUrlResolver)
    this.addSubResolver("add", new AddUrlResolver)
    this.addSubResolver("edit", new EditUrlResolver)
    this.addSubResolver("preview", new PreviewUrlResolver)

    private class ListUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new UserEvent.GotoList(ListUrlResolver.this, null))
        }
    }

    private class AddUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new UserEvent.GotoAdd(AddUrlResolver.this, null))
        }
    }

    private class EditUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            val username: String = new UrlTokenizer(params(0)).getString
            val userService: UserService = ApplicationContextUtil.getSpringBean(classOf[UserService])
            val user: SimpleUser = userService.findUserByUserNameInAccount(username, AppContext.getAccountId)
            EventBusFactory.getInstance.post(new UserEvent.GotoEdit(EditUrlResolver.this, user))
        }
    }

    private class PreviewUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            val username: String = new UrlTokenizer(params(0)).getString
            EventBusFactory.getInstance.post(new UserEvent.GotoRead(PreviewUrlResolver.this, username))
        }
    }

}
