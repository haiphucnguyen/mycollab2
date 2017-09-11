package com.mycollab.module.project;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.file.service.AbstractStorageService;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.ui.UIConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mycollab.common.GenericLinkUtils.URL_PREFIX_PARAM;
import static com.mycollab.vaadin.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectLinkBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectLinkBuilder.class);

    public static String generateProjectFullLink(Integer projectId) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateProjectLink(projectId);
    }

    public static String generateComponentPreviewFullLink(Integer projectId, Integer componentId) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateBugComponentPreviewLink(projectId, componentId);
    }

    public static String generateBugVersionPreviewFullLink(Integer projectId, Integer versionId) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateBugVersionPreviewLink(projectId, versionId);
    }

    public static String generateRolePreviewFullLink(Integer projectId, Integer roleId) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateRolePreviewLink(projectId, roleId);
    }

    public static String generateProjectMemberFullLink(Integer projectId, String memberName) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateProjectMemberLink(projectId, memberName);
    }

    public static String generateProjectMemberHtmlLink(Integer projectId, String username, String displayName, String avatarId,
                                                       Boolean isDisplayTooltip) {
        Img userAvatar = new Img("", AppContextUtil.getSpringBean(AbstractStorageService.class)
                .getAvatarPath(avatarId, 16)).setCSSClass(UIConstants.CIRCLE_BOX);
        A link = new A().setId("tag" + TOOLTIP_ID).setHref(generateProjectMemberFullLink(projectId,
                username)).appendText(StringUtils.trim(displayName, 30, true));
        if (isDisplayTooltip) {
            link.setAttribute("onmouseover", TooltipHelper.userHoverJsFunction(username));
            link.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
            return new DivLessFormatter().appendChild(userAvatar, DivLessFormatter.EMPTY_SPACE(), link).write();
        } else {
            return new DivLessFormatter().appendChild(userAvatar, DivLessFormatter.EMPTY_SPACE(), link).write();
        }
    }

    public static String generateProjectMemberHtmlLink(Integer projectId, String username, Boolean isDisplayTooltip) {
        ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
        SimpleProjectMember member = projectMemberService.findMemberByUsername(username, projectId, AppUI.getAccountId());
        if (member != null) {
            return generateProjectMemberHtmlLink(projectId, member.getUsername(), member.getDisplayName(), member
                    .getMemberAvatarId(), isDisplayTooltip);
        } else {
            return null;
        }
    }

    public static String generateBugPreviewFullLink(Integer bugKey, String prjShortName) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateBugPreviewLink(bugKey, prjShortName);
    }

    public static String generateMessagePreviewFullLink(Integer projectId, Integer messageId) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateMessagePreviewLink(projectId, messageId);
    }

    public static String generateRiskPreviewFullLink(Integer projectId, Integer riskId) {
        return INSTANCE.getURL_PREFIX_PARAM() + "project/risk/preview/" + UrlEncodeDecoder.encode(projectId + "/" + riskId);
    }

    public static String generateTaskPreviewFullLink(Integer taskKey, String prjShortName) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateTaskPreviewLink(taskKey, prjShortName);
    }

    public static String generateMilestonePreviewFullLink(Integer projectId, Integer milestoneId) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateMilestonePreviewLink(projectId, milestoneId);
    }

    public static String generateClientPreviewFullLink(Integer clientId) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateClientPreviewLink(clientId);
    }

    public static String generatePageFolderFullLink(Integer projectId, String folderPath) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generatePagesLink(projectId, folderPath);
    }

    public static String generatePageFullLink(Integer projectId, String pagePath) {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generatePageRead(projectId, pagePath);
    }

    public static String generateStandupDashboardLink() {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateStandupDashboardLink();
    }

    public static String generateHoursWeeklyReportLink() {
        return AppUI.getSiteUrl() + INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE
                .generateHoursWeeklyReportLink();
    }

    public static String generateTimesheetReportLink() {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateTimesheetReportLink();
    }

    public static String generateUsersWorkloadReportLink() {
        return INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateUsersWorkloadReportLink();
    }

    public static String generateProjectItemHtmlLinkAndTooltip(String prjShortName, Integer projectId, String summary, String type, String typeId) {
        Text image = new Text(ProjectAssetsManager.getAsset(type).getHtml());
        A link = new A().setId("tag" + TOOLTIP_ID);
        link.setHref(INSTANCE.getURL_PREFIX_PARAM() + ProjectLinkGenerator.INSTANCE.generateProjectItemLink(prjShortName, projectId, type, typeId))
                .appendChild(new Text(summary));
        link.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(type, typeId));
        link.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
        Div div = new DivLessFormatter().appendChild(image, DivLessFormatter.EMPTY_SPACE(), link);
        return div.write();
    }
}
