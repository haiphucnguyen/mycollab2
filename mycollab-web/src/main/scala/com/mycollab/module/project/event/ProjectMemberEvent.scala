package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object ProjectMemberEvent {

  class InviteProjectMembers(@BeanProperty var emails: java.util.Collection[String], @BeanProperty var roleId: Integer,
                             @BeanProperty var roleName: String,
                             @BeanProperty var inviteMessage: String) extends Serializable {}

  class Search(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoInviteMembers(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
