package com.esofthead.mycollab.module.project.view.client

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.ClientEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.shell.events.ShellEvent

/**
  * @author MyCollab Ltd
  * @since 5.2.9
  */
class ClientUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new ClientEvent.GotoList(this, null))
    }
  }

  protected override def handlePage(params: String*) {
    EventBusFactory.getInstance.post(new ClientEvent.GotoList(this, null))
  }
}
