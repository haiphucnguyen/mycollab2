package com.mycollab.module.project.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.event.ClientEvent
import com.mycollab.module.project.event.ClientEvent.{GotoAdd, GotoEdit, GotoRead}

/**
  * @author MyCollab Ltd
  * @since 5.2.9
  */
class ClientUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null))
    }
  }

  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val clientId = token.getInt
      EventBusFactory.getInstance().post(new GotoRead(this, clientId))
    }
  }

  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new GotoAdd(this, null))
    }
  }

  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val clientId = token.getInt
      EventBusFactory.getInstance().post(new GotoEdit(this, clientId))
    }
  }

  protected override def handlePage(params: String*) {
    EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null))
  }
}
