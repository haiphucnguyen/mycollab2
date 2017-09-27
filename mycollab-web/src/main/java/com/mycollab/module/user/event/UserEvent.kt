package com.mycollab.module.user.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object UserEvent {
    class PlainLogin(val username: String, val password: String, val isRememberMe: Boolean)

    class Search(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data)
}