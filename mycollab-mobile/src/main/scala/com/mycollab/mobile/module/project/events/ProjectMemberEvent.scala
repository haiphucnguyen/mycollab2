package com.mycollab.mobile.module.project.events

import com.mycollab.events.ApplicationEvent

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object ProjectMemberEvent {

  @SerialVersionUID(1L)
  class InviteProjectMembers(@BeanProperty val inviteEmails: java.util.List[String], @BeanProperty val roleId: Integer,
                             @BeanProperty val inviteMessage: String) extends Serializable {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoInviteMembers(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
