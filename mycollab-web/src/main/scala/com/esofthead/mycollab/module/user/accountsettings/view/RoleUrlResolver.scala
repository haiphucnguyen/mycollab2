package com.esofthead.mycollab.module.user.accountsettings.view

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.user.domain.SimpleRole
import com.esofthead.mycollab.module.user.events.RoleEvent
import com.esofthead.mycollab.module.user.service.RoleService
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class RoleUrlResolver extends AccountUrlResolver {
    this.addSubResolver("list", new ListUrlResolver)
    this.addSubResolver("add", new AddUrlResolver)
    this.addSubResolver("edit", new EditUrlResolver)
    this.addSubResolver("preview", new PreviewUrlResolver)

    private class ListUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new RoleEvent.GotoList(ListUrlResolver.this, null))
        }
    }

    private class AddUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new RoleEvent.GotoAdd(AddUrlResolver.this, null))
        }
    }

    private class EditUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            val roleId: Integer = new UrlTokenizer(params(0)).getInt
            val roleService: RoleService = ApplicationContextUtil.getSpringBean(classOf[RoleService])
            val role: SimpleRole = roleService.findById(roleId, AppContext.getAccountId)
            EventBusFactory.getInstance.post(new RoleEvent.GotoEdit(EditUrlResolver.this, role))
        }
    }

    private class PreviewUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            val roleId: Integer = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new RoleEvent.GotoRead(PreviewUrlResolver.this, roleId))
        }
    }

}
