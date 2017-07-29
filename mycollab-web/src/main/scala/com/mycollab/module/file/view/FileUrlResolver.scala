package com.mycollab.module.file.view

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.httpmapping.CrmUrlResolver
import com.mycollab.shell.events.ShellEvent
import com.mycollab.vaadin.mvp.UrlResolver
import com.mycollab.vaadin.web.ui.ModuleHelper

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class FileUrlResolver extends UrlResolver {
  def build: UrlResolver = {
    this.addSubResolver("list", new FileListUrlResolver)
    this
  }

  override def handle(params: String*) {
    if (!ModuleHelper.isCurrentFileModule) {
      EventBusFactory.getInstance().post(new ShellEvent.GotoFileModule(this, params))
    }
    else {
      super.handle(params: _*)
    }
  }

  protected def defaultPageErrorHandler() {
  }

  class FileListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
    }
  }

}
