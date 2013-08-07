package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.web.AppContext;

public class ProjectLinkBuilder {

	public static String generateProjectFullLink(Integer projectId,
			String prefixParam) {
		if (projectId == null) {
			return "";
		}
		return AppContext.getSiteUrl() + prefixParam + "project/dashboard/"
				+ UrlEncodeDecoder.encode(projectId);
	}

	public static String generateProjectMemberFullLink(int projectId,
			String memberName) {
		return AppContext.getSiteUrl() + ProjectLinkUtils.DEFAULT_PREFIX_PARAM
				+ "project/user/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + memberName);
	}

	public static String generateBugPreviewFullLink(Integer projectId,
			Integer bugId) {
		if (projectId == null || bugId == null) {
			return "";
		}
		return AppContext.getSiteUrl() + ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateBugPreviewLink(projectId, bugId);
	}

	public static String generateMessagePreviewFullLink(Integer projectId,
			Integer messageId, String prefixParam) {
		if (projectId == null || messageId == null) {
			return "";
		}
		return AppContext.getSiteUrl()
				+ prefixParam
				+ ProjectLinkUtils.generateMessagePreviewLink(projectId,
						messageId);
	}

	public static String generateRiskPreviewFullLink(Integer projectId,
			Integer riskId) {
		if (projectId == null || riskId == null) {
			return "";
		}
		return AppContext.getSiteUrl() + ProjectLinkUtils.URL_PREFIX_PARAM
				+ "project/risk/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + riskId);
	}

	public static String generateTaskPreviewFullLink(Integer projectId,
			Integer taskId) {
		if (projectId == null || taskId == null) {
			return "";
		}
		return AppContext.getSiteUrl() + ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateTaskPreviewLink(projectId, taskId);
	}

	public static String generateTaskGroupPreviewFullLink(Integer projectId,
			Integer taskgroupId) {
		if (projectId == null || taskgroupId == null) {
			return "";
		}
		return AppContext.getSiteUrl()
				+ ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateTaskGroupPreviewLink(projectId,
						taskgroupId);
	}

	public static String generateMilestonePreviewFullLink(Integer projectId,
			Integer milestoneId) {
		if (projectId == null || milestoneId == null) {
			return "";
		}
		return AppContext.getSiteUrl()
				+ ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateMilestonePreviewLink(projectId,
						milestoneId);
	}

	public static String generateProjectItemLink(int projectId, String type,
			int typeid) {
		String result = "";

		if (ProjectContants.PROJECT.equals(type)) {
		} else if (ProjectContants.MESSAGE.equals(type)) {
			result = ProjectLinkUtils.generateMessagePreviewLink(projectId,
					typeid);
		} else if (ProjectContants.MILESTONE.equals(type)) {
			result = ProjectLinkUtils.generateMilestonePreviewLink(projectId,
					typeid);
		} else if (ProjectContants.PROBLEM.equals(type)) {
			result = ProjectLinkUtils.generateProblemPreviewLink(projectId,
					typeid);
		} else if (ProjectContants.RISK.equals(type)) {
			result = ProjectLinkUtils.generateRiskPreview(projectId, typeid);
		} else if (ProjectContants.TASK.equals(type)) {
			result = ProjectLinkUtils
					.generateTaskPreviewLink(projectId, typeid);
		} else if (ProjectContants.TASK_LIST.equals(type)) {
			result = ProjectLinkUtils.generateTaskGroupPreviewLink(projectId,
					typeid);
		} else if (ProjectContants.BUG.equals(type)) {
			result = ProjectLinkUtils.generateBugPreviewLink(projectId, typeid);
		} else if (ProjectContants.BUG_COMPONENT.equals(type)) {
			result = ProjectLinkUtils.generateBugComponentPreviewLink(
					projectId, typeid);
		} else if (ProjectContants.BUG_VERSION.equals(type)) {
			result = ProjectLinkUtils.generateBugVersionPreviewLink(projectId,
					typeid);
		} else if (ProjectContants.STANDUP.equals(type)) {

		}

		return "#" + result;
	}
}
