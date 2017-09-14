package com.mycollab.module.project.ui

import com.mycollab.core.utils.StringUtils
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.i18n.OptionI18nEnum.MilestoneStatus
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority
import com.vaadin.server.FontAwesome

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
object ProjectAssetsManager {
    private val resources = mutableMapOf(ProjectTypeConstants.DASHBOARD to FontAwesome.DASHBOARD,
            ProjectTypeConstants.MESSAGE to FontAwesome.COMMENT,
            ProjectTypeConstants.MILESTONE to FontAwesome.FLAG_CHECKERED,
            ProjectTypeConstants.TASK to FontAwesome.TASKS,
            ProjectTypeConstants.TICKET to FontAwesome.TICKET,
            ProjectTypeConstants.PAGE to FontAwesome.FILE,
            ProjectTypeConstants.BUG to FontAwesome.BUG,
            ProjectTypeConstants.BUG_COMPONENT to FontAwesome.CUBE,
            ProjectTypeConstants.BUG_VERSION to FontAwesome.LEAF,
            ProjectTypeConstants.FILE to FontAwesome.BRIEFCASE,
            ProjectTypeConstants.RISK to FontAwesome.SHIELD,
            ProjectTypeConstants.FINANCE to FontAwesome.MONEY,
            ProjectTypeConstants.TIME to FontAwesome.CLOCK_O,
            ProjectTypeConstants.INVOICE to FontAwesome.CREDIT_CARD,
            ProjectTypeConstants.STANDUP to FontAwesome.CUBES,
            ProjectTypeConstants.MEMBER to FontAwesome.USERS,
            ProjectTypeConstants.PROJECT to FontAwesome.CALENDAR_O
    )

    fun getAsset(resId: String): FontAwesome = resources[resId] ?: FontAwesome.DASHBOARD

    fun toHexString(resId: String): String ="&#x" + Integer.toHexString(resources[resId]!!.getCodepoint())

    fun getPriority(priority: String?): FontAwesome {
        return if (Priority.Urgent.name == priority || Priority.High.name == priority || Priority.Medium.name == priority || priority == null) {
            FontAwesome.ARROW_UP
        } else {
            FontAwesome.ARROW_DOWN
        }
    }

    fun getMilestoneStatus(status: String): FontAwesome {
        return when (status) {
            MilestoneStatus.Closed.name -> FontAwesome.MINUS_CIRCLE
            MilestoneStatus.InProgress.name -> FontAwesome.CLOCK_O
            else -> FontAwesome.SPINNER
        }
    }

    fun getPriorityHtml(priority: String): String {
        var temp = priority
        if (StringUtils.isBlank(temp)) {
            temp = Priority.Medium.name
        }
        val fontAwesome = getPriority(temp)
        return String.format("<span class=\"priority-%s v-icon\" style=\"font-family: FontAwesome;\">&#x%s;</span>",
                temp.toLowerCase(), Integer.toHexString(fontAwesome.codepoint))
    }
}
