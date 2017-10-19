package com.mycollab.pro.vaadin.web.ui

import com.mycollab.common.ModuleNameConstants
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
import com.vaadin.ui.UI
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class NotificationComponent : AbstractNotificationComponent() {
    init {
        AsyncInvoker.access(UI.getCurrent(), object: AsyncInvoker.PageCommand() {
            override fun run() {
                val notificationItemService = AppContextUtil.getSpringBean(NotificationItemService::class.java)
                val notifications = notificationItemService.findNotificationItemsByUser(UserUIContext.getUsername(), AppUI.accountId)
                notifications.forEach {
                    when {
                        it.module == ModuleNameConstants.PRJ -> {
                            val projectNotification = ProjectEntryUpdateNotification(it.type, it.typeid, it.message)
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

    class ProjectNotificationComponent(notification: ProjectEntryUpdateNotification) : CssLayout() {
        init {
            val noLabel = ELabel.html(notification.message)
            addComponent(noLabel)
            addLayoutClickListener { _ -> println(notification.message) }
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(NotificationComponent::class.java)
    }
}