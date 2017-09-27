package com.mycollab.mobile.module.user.event

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class UserEvent {
    class PlainLogin(val username: String, val password: String,
                     val isRememberMe: Boolean)
}