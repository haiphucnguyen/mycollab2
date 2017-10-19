package com.mycollab.community.vaadin.web.ui

import com.mycollab.core.AbstractNotification
import com.mycollab.module.project.ProjectEntryUpdateNotification
import com.mycollab.vaadin.ui.ELabel
import com.mycollab.vaadin.web.ui.AbstractNotificationComponent
import com.vaadin.ui.Component
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class NotificationComponent : AbstractNotificationComponent() {
    override fun buildComponentFromNotificationExclusive(item: AbstractNotification): Component? {
        return when (item) {
            is ProjectEntryUpdateNotification -> ELabel.html(item.message)
            else -> {
                LOG.error("Do not support notification type $item")
                null;
            }
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(NotificationComponent::class.java)
    }
}