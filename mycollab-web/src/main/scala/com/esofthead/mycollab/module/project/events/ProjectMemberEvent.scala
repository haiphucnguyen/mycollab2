package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

import scala.beans.BeanProperty

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object ProjectMemberEvent {

  class InviteProjectMembers(@BeanProperty var emails: java.util.List[String], @BeanProperty var roleId: Integer,
                             @BeanProperty var inviteMessage: String) extends Serializable {}

  class Search(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoInviteMembers(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
