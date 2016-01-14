package com.esofthead.mycollab.mobile.module.user.events

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object UserEvent {
  class PlainLogin(@BeanProperty val username: String, @BeanProperty val password: String,
                   @BeanProperty val isRememberMe: Boolean) extends Serializable {}
}
