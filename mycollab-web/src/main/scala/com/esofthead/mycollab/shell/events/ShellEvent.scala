package com.esofthead.mycollab.shell.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.5
 */
object ShellEvent {

  class NotifyErrorEvent(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoMainPage(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class LogOut(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoProjectModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoCrmModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoFileModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoUserAccountModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoConsolePage(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoForgotPasswordPage(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class NewNotification(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
