package com.mycollab.shell.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ShellEvent {
    class NotifyErrorEvent(source: Any, data: Any?) : ApplicationEvent(source, data)

    class RefreshPage(source: Any) : ApplicationEvent(source, null)

    class GotoMainPage(source: Any, data: Any?) : ApplicationEvent(source, data)

    class LogOut(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoSetupPage(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoProjectModule(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoCrmModule(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoFileModule(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoUserAccountModule(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoConsolePage(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoForgotPasswordPage(source: Any, data: Any?) : ApplicationEvent(source, data)

    class NewNotification(source: Any, data: Any?) : ApplicationEvent(source, data)

    class AddQueryParam(source: Any, data: Any?) : ApplicationEvent(source, data)
}