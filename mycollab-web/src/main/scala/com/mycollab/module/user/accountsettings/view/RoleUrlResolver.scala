package com.mycollab.premium.module.user.accountsettings.view

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.user.events.RoleEvent
import com.mycollab.module.user.service.RoleService
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.MyCollabUI

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class RoleUrlResolver extends AccountSettingUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  
  private class ListUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new RoleEvent.GotoList(ListUrlResolver.this, null))
    }
  }
  
  private class AddUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new RoleEvent.GotoAdd(AddUrlResolver.this, null))
    }
  }
  
  private class EditUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      val roleId = UrlTokenizer(params(0)).getInt
      val roleService = AppContextUtil.getSpringBean(classOf[RoleService])
      val role = roleService.findById(roleId, MyCollabUI.getAccountId)
      EventBusFactory.getInstance().post(new RoleEvent.GotoEdit(EditUrlResolver.this, role))
    }
  }
  
  private class PreviewUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      val roleId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new RoleEvent.GotoRead(PreviewUrlResolver.this, roleId))
    }
  }
  
}