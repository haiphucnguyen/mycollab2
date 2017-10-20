package com.mycollab.pro.vaadin.web.ui

import com.mycollab.common.ModuleNameConstants
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.common.service.NotificationItemService
import com.mycollab.core.AbstractNotification
import com.mycollab.module.project.ProjectEntryUpdateNotification
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.AppUI
import com.mycollab.vaadin.AsyncInvoker
import com.mycollab.vaadin.UserUIContext
import com.mycollab.vaadin.ui.ELabel
import com.mycollab.vaadin.web.ui.AbstractNotificationComponent
import com.vaadin.ui.Component
import com.vaadin.ui.CssLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.UI
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class NotificationComponent : AbstractNotificationComponent() {
    init {
        AsyncInvoker.access(UI.getCurrent(), object : AsyncInvoker.PageCommand() {
            override fun run() {
                val notificationItemService = AppContextUtil.getSpringBean(NotificationItemService::class.java)
                val notifications = notificationItemService.findUnreadNotificationItemsByUser(UserUIContext.getUsername(), AppUI.accountId)
                notifications.forEach {
                    when {
                        it.module == ModuleNameConstants.PRJ -> {
                            val projectNotification = ProjectEntryUpdateNotification(it.notificationuser, it.type, it.typeid, it.message)
                            addNotification(projectNotification)
                        }
                    }
                }
            }
        })
    }

    override fun buildComponentFromNotificationExclusive(item: AbstractNotification): Component? {
        return when (item) {
            is ProjectEntryUpdateNotification -> ProjectNotificationComponent(item)
            else -> {
                LOG.error("Do not support notification type $item")
                null
            }
        }
    }

    override fun displayTrayNotificationExclusive(item: AbstractNotification) {
        if (item is ProjectEntryUpdateNotification) {
            val no = Notification(UserUIContext.getMessage(GenericI18Enum.WINDOW_INFORMATION_TITLE), item.message, Notification.Type.TRAY_NOTIFICATION)
            no.isHtmlContentAllowed = true
            no.delayMsec = 3000

            AsyncInvoker.access(ui, object : AsyncInvoker.PageCommand() {
                override fun run() {
                    no.show(ui.page)
                }
            })
        }
    }

    class ProjectNotificationComponent(notification: ProjectEntryUpdateNotification) : CssLayout() {
        init {
            val noLabel = ELabel.html(notification.message)
            addComponent(noLabel)
            addLayoutClickListener { _ ->
                val notificationItemService = AppContextUtil.getSpringBean(NotificationItemService::class.java)
                notificationItemService.markNotificationRead(notification.targetUser, ModuleNameConstants.PRJ, notification.type, notification.typeId)
            }
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(NotificationComponent::class.java)
    }
}