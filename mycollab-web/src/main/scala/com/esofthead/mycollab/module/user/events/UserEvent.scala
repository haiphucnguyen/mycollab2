package com.esofthead.mycollab.module.user.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

import scala.beans.BeanProperty

/**
 * @author MyCollab Ltd
 * @version 5.1.0
 */
object UserEvent {

    class PlainLogin(@BeanProperty val username: String, @BeanProperty val password: String,
                     @BeanProperty val isRememberMe: Boolean) extends Serializable {}

    class Search(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
