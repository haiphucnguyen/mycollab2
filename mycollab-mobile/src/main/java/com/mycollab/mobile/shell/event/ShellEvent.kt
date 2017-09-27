package com.mycollab.mobile.shell.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ShellEvent {
    class GotoLoginView(source: Any) : ApplicationEvent(source, null) 

    class GotoMainPage(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoCrmModule(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoProjectModule(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoUserAccountModule(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class PushView(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class NavigateBack(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class LogOut(source: Any) : ApplicationEvent(source, null) 
}