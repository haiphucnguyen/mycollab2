package com.mycollab.module.project

import com.hp.gagawa.java.elements.A
import com.hp.gagawa.java.elements.Img
import com.hp.gagawa.java.elements.Text
import com.mycollab.common.GenericLinkUtils.URL_PREFIX_PARAM
import com.mycollab.common.UrlEncodeDecoder
import com.mycollab.core.utils.StringUtils
import com.mycollab.html.DivLessFormatter
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.module.project.service.ProjectMemberService
import com.mycollab.module.project.ui.ProjectAssetsManager
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.AppUI
import com.mycollab.vaadin.TooltipHelper
import com.mycollab.vaadin.TooltipHelper.TOOLTIP_ID
import com.mycollab.vaadin.ui.UIConstants

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object ProjectLinkBuilder {

    fun generateProjectFullLink(projectId: Int?): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateProjectLink(projectId)
    }

    fun generateComponentPreviewFullLink(projectId: Int?, componentId: Int?): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateBugComponentPreviewLink(projectId!!, componentId!!)
    }

    fun generateBugVersionPreviewFullLink(projectId: Int?, versionId: Int?): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateBugVersionPreviewLink(projectId!!, versionId!!)
    }

    fun generateRolePreviewFullLink(projectId: Int?, roleId: Int?): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateRolePreviewLink(projectId!!, roleId!!)
    }

    fun generateProjectMemberFullLink(projectId: Int?, memberName: String): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateProjectMemberLink(projectId!!, memberName)
    }

    fun generateProjectMemberHtmlLink(projectId: Int?, username: String, displayName: String, avatarId: String,
                                      isDisplayTooltip: Boolean?): String {
        val userAvatar = Img("", AppContextUtil.getSpringBean(AbstractStorageService::class.java)
                .getAvatarPath(avatarId, 16)).setCSSClass(UIConstants.CIRCLE_BOX)
        val link = A().setId("tag" + TOOLTIP_ID).setHref(generateProjectMemberFullLink(projectId,
                username)).appendText(StringUtils.trim(displayName, 30, true))
        if (isDisplayTooltip!!) {
            link.setAttribute("onmouseover", TooltipHelper.userHoverJsFunction(username))
            link.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction())
            return DivLessFormatter().appendChild(userAvatar, DivLessFormatter.EMPTY_SPACE, link).write()
        } else {
            return DivLessFormatter().appendChild(userAvatar, DivLessFormatter.EMPTY_SPACE, link).write()
        }
    }

    fun generateProjectMemberHtmlLink(projectId: Int, username: String, isDisplayTooltip: Boolean?): String? {
        val projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService::class.java)
        val member = projectMemberService.findMemberByUsername(username, projectId, AppUI.getAccountId())
        return if (member != null) {
            generateProjectMemberHtmlLink(projectId, member.username, member.displayName, member
                    .memberAvatarId, isDisplayTooltip)
        } else null
    }

    fun generateBugPreviewFullLink(bugKey: Int?, prjShortName: String): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateBugPreviewLink(bugKey, prjShortName)
    }

    fun generateMessagePreviewFullLink(projectId: Int?, messageId: Int?): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateMessagePreviewLink(projectId!!, messageId!!)
    }

    fun generateRiskPreviewFullLink(projectId: Int?, riskId: Int?): String {
        return URL_PREFIX_PARAM + "project/risk/preview/" + UrlEncodeDecoder.encode(projectId.toString() + "/" + riskId)
    }

    fun generateTaskPreviewFullLink(taskKey: Int?, prjShortName: String): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateTaskPreviewLink(taskKey, prjShortName)
    }

    fun generateMilestonePreviewFullLink(projectId: Int?, milestoneId: Int?): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateMilestonePreviewLink(projectId!!, milestoneId!!)
    }

    fun generateClientPreviewFullLink(clientId: Int?): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateClientPreviewLink(clientId)
    }

    fun generatePageFolderFullLink(projectId: Int?, folderPath: String): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generatePagesLink(projectId!!, folderPath)
    }

    fun generatePageFullLink(projectId: Int?, pagePath: String): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generatePageRead(projectId!!, pagePath)
    }

    fun generateStandupDashboardLink(): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateStandupDashboardLink()
    }

    fun generateHoursWeeklyReportLink(): String {
        return AppUI.getSiteUrl() + URL_PREFIX_PARAM + ProjectLinkGenerator
                .generateHoursWeeklyReportLink()
    }

    fun generateTimesheetReportLink(): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateTimesheetReportLink()
    }

    fun generateUsersWorkloadReportLink(): String {
        return URL_PREFIX_PARAM + ProjectLinkGenerator.generateUsersWorkloadReportLink()
    }

    fun generateProjectItemHtmlLinkAndTooltip(prjShortName: String, projectId: Int?, summary: String, type: String, typeId: String): String {
        val image = Text(ProjectAssetsManager.getAsset(type).html)
        val link = A().setId("tag" + TOOLTIP_ID)
        link.setHref(URL_PREFIX_PARAM + ProjectLinkGenerator.generateProjectItemLink(prjShortName, projectId!!, type, typeId))
                .appendChild(Text(summary))
        link.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(type, typeId))
        link.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction())
        val div = DivLessFormatter().appendChild(image, DivLessFormatter.EMPTY_SPACE, link)
        return div.write()
    }
}
