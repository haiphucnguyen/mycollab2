package com.mycollab.mobile.shell.events

import com.mycollab.events.ApplicationEvent

/**
 * @author myCollab Ltd
 * @since 5.2.1
 */
object ShellEvent {

    class GotoLoginView(source: AnyRef) extends ApplicationEvent(source, null) {}

    class GotoMainPage(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoCrmModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoProjectModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoUserAccountModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class PushView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class NavigateBack(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class LogOut(source: AnyRef) extends ApplicationEvent(source, null) {}
}
