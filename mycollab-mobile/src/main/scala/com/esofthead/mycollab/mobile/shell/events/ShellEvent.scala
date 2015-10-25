package com.esofthead.mycollab.mobile.shell.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author myCollab Ltd
 * @since 5.2.1
 */
object ShellEvent {

    @SerialVersionUID(-551175801973985055L)
    class GotoLoginView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoMainPage(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoCrmModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoProjectModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoUserAccountModule(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class PushView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class NavigateBack(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
