package com.mycollab.module.project

import com.mycollab.common.GenericLinkUtils
import com.mycollab.common.GenericLinkUtils.URL_PREFIX_PARAM
import com.mycollab.common.UrlEncodeDecoder
import com.mycollab.core.DebugException
import com.mycollab.core.utils.StringUtils

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object ProjectLinkGenerator {

    fun generateProjectLink(projectId: Int?): String {
        return "project/dashboard/" + GenericLinkUtils.encodeParam(projectId!!)
    }

    fun generateProjectFullLink(siteUrl: String, projectId: Int?): String {
        return siteUrl + URL_PREFIX_PARAM + generateProjectLink(projectId)
    }

    fun generateTicketDashboardLink(projectId: Int?): String {
        return "project/ticket/dashboard/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateTaskPreviewLink(taskKey: Int?, prjShortName: String): String {
        return String.format("project/task/preview/%s-%d", prjShortName, taskKey)
    }

    fun generateTaskPreviewFullLink(siteUrl: String, taskKey: Int?, prjShortName: String): String {
        return siteUrl + URL_PREFIX_PARAM + generateTaskPreviewLink(taskKey, prjShortName)
    }

    fun generateTaskEditLink(taskKey: Int?, prjShortName: String): String {
        return String.format("project/task/edit/%s-%d", prjShortName, taskKey)
    }

    fun generateMilestonesLink(projectId: Int?): String {
        return "project/milestone/list/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateMilestonePreviewLink(projectId: Int, milestoneId: Int): String {
        return "project/milestone/preview/" + GenericLinkUtils.encodeParam(projectId, milestoneId)
    }

    fun generateMilestonePreviewFullLink(siteUrl: String, projectId: Int, milestoneId: Int): String {
        return siteUrl + URL_PREFIX_PARAM + generateMilestonePreviewLink(projectId, milestoneId)
    }

    fun generateClientPreviewLink(accountId: Int?): String {
        return "project/client/preview/" + UrlEncodeDecoder.encode(accountId)
    }

    fun generateClientPreviewFullLink(siteUrl: String, accountId: Int?): String {
        return siteUrl + URL_PREFIX_PARAM + "project/client/preview/" + UrlEncodeDecoder.encode(accountId)
    }

    fun generatePagesLink(projectId: Int, folderPath: String): String {
        return "project/page/list/" + GenericLinkUtils.encodeParam(projectId, folderPath)
    }

    fun generatePageAdd(projectId: Int, pagePath: String): String {
        return "project/page/add/" + GenericLinkUtils.encodeParam(projectId, pagePath)
    }

    fun generatePageRead(projectId: Int, pagePath: String): String {
        return "project/page/preview/" + GenericLinkUtils.encodeParam(projectId, pagePath)
    }

    fun generatePageEdit(projectId: Int, pagePath: String): String {
        return "project/page/edit/" + GenericLinkUtils.encodeParam(projectId, pagePath)
    }

    fun generateProjectMemberFullLink(siteUrl: String, projectId: Int, memberName: String?): String {
        return if (memberName == null) {
            ""
        } else siteUrl + URL_PREFIX_PARAM + "project/user/preview/" + GenericLinkUtils.encodeParam(projectId, memberName)
    }

    fun generateProjectMemberLink(projectId: Int, memberName: String): String {
        return "project/user/preview/" + GenericLinkUtils.encodeParam(projectId, memberName)
    }

    fun generateRisksLink(projectId: Int?): String {
        return "project/risk/list/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateRiskPreviewLink(projectId: Int, riskId: Int): String {
        return "project/risk/preview/" + GenericLinkUtils.encodeParam(projectId, riskId)
    }

    fun generateRiskPreviewFullLink(siteUrl: String, projectId: Int, riskId: Int): String {
        return siteUrl + URL_PREFIX_PARAM + generateRiskPreviewLink(projectId, riskId)
    }

    fun generateRiskEditLink(projectId: Int?, riskId: Int?): String {
        return "project/risk/edit/" + UrlEncodeDecoder.encode(projectId.toString() + "/" + riskId)
    }

    fun generateRiskAddLink(projectId: Int?): String {
        return "project/risk/add/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateMessageAddLink(projectId: Int?): String {
        return "project/message/add/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateMessagesLink(projectId: Int?): String {
        return "project/message/list/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateMessagePreviewLink(projectId: Int, messageId: Int): String {
        return "project/message/preview/" + GenericLinkUtils.encodeParam(projectId, messageId)
    }

    fun generateMessagePreviewFullLink(siteUrl: String, projectId: Int, messageId: Int): String {
        return siteUrl + URL_PREFIX_PARAM + generateMessagePreviewLink(projectId, messageId)
    }

    fun generateBugComponentPreviewLink(projectId: Int, componentId: Int): String {
        return "project/component/preview/" + GenericLinkUtils.encodeParam(projectId, componentId)
    }

    fun generateBugComponentPreviewFullLink(siteUrl: String, projectId: Int, componentId: Int): String {
        return siteUrl + URL_PREFIX_PARAM + generateBugComponentPreviewLink(projectId, componentId)
    }

    fun generateBugVersionPreviewLink(projectId: Int, versionId: Int): String {
        return "project/version/preview/" + GenericLinkUtils.encodeParam(projectId, versionId)
    }

    fun generateBugVersionPreviewFullLink(siteUrl: String, projectId: Int, versionId: Int): String {
        return siteUrl + URL_PREFIX_PARAM + generateBugVersionPreviewLink(projectId, versionId)
    }

    fun generateBugPreviewLink(bugKey: Int?, prjShortName: String): String {
        return String.format("project/bug/preview/%s-%d", prjShortName, bugKey)
    }

    fun generateBugEditLink(bugKey: Int?, prjShortName: String): String {
        return String.format("project/bug/edit/%s-%d", prjShortName, bugKey)
    }

    fun generateBugsLink(projectId: Int?): String {
        return "project/bug/list/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateBugPreviewFullLink(siteUrl: String, bugKey: Int?, prjShortName: String): String {
        return siteUrl + URL_PREFIX_PARAM + generateBugPreviewLink(bugKey, prjShortName)
    }

    fun generateFileDashboardLink(projectId: Int?): String {
        return "project/file/dashboard/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateTimeReportLink(projectId: Int?): String {
        return "project/time/list/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateInvoiceListLink(projectId: Int?): String {
        return "project/invoice/list/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateRolePreviewLink(projectId: Int, roleId: Int): String {
        return "project/role/preview/" + GenericLinkUtils.encodeParam(projectId, roleId)
    }

    fun generateRolePreviewFullLink(siteUrl: String, projectId: Int, roleId: Int): String {
        return siteUrl + URL_PREFIX_PARAM + generateRolePreviewLink(projectId, roleId)
    }

    fun generateStandupDashboardLink(): String {
        return "project/reports/standup/list/"
    }

    fun generateHoursWeeklyReportLink(): String {
        return "project/reports/weeklytiming/"
    }

    fun generateTimesheetReportLink(): String {
        return "project/reports/timesheet/"
    }

    fun generateUsersWorkloadReportLink(): String {
        return "project/reports/usersworkload/"
    }

    fun generateUsersLink(projectId: Int?): String {
        return "project/user/list/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateProjectSettingLink(projectId: Int?): String {
        return "project/setting/" + UrlEncodeDecoder.encode(projectId)
    }

    fun generateProjectSettingFullLink(siteUrl: String, projectId: Int?): String {
        return siteUrl + URL_PREFIX_PARAM + generateProjectSettingLink(projectId)
    }

    fun generateProjectItemLink(prjShortName: String, projectId: Int, type: String, typeId: String): String {
        var result = ""

        if (typeId == null || StringUtils.isBlank(typeId) || "null" == typeId) {
            return ""
        }

        try {
            if (ProjectTypeConstants.PROJECT == type) {
            } else if (ProjectTypeConstants.MESSAGE == type) {
                result = generateMessagePreviewLink(projectId, Integer.parseInt(typeId))
            } else if (ProjectTypeConstants.MILESTONE == type) {
                result = generateMilestonePreviewLink(projectId, Integer.parseInt(typeId))
            } else if (ProjectTypeConstants.RISK == type) {
                result = generateRiskPreviewLink(projectId, Integer.parseInt(typeId))
            } else if (ProjectTypeConstants.TASK == type) {
                result = generateTaskPreviewLink(Integer.parseInt(typeId), prjShortName)
            } else if (ProjectTypeConstants.BUG == type) {
                result = generateBugPreviewLink(Integer.parseInt(typeId), prjShortName)
            } else if (ProjectTypeConstants.BUG_COMPONENT == type) {
                result = generateBugComponentPreviewLink(projectId, Integer.parseInt(typeId))
            } else if (ProjectTypeConstants.BUG_VERSION == type) {
                result = generateBugVersionPreviewLink(projectId, Integer.parseInt(typeId))
            } else if (ProjectTypeConstants.PAGE == type) {
                result = generatePageRead(projectId, typeId)
            }
        } catch (e: Exception) {
            throw DebugException(String.format("Error generate tooltip%d---%s---%s", projectId, type, typeId), e)
        }

        return "#" + result
    }
}
