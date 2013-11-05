package com.esofthead.mycollab.module.project;

import com.esofthead.mycollab.common.GenericLinkUtils;

public class ProjectLinkUtils {

	public static String generateTaskGroupPreviewLink(Integer projectId,
			Integer taskgroupId) {
		return "project/task/taskgroup/preview/"
				+ GenericLinkUtils.encodeParam(new Object[] { projectId,
						taskgroupId });
	}

	public static String generateTaskPreviewLink(Integer projectId,
			Integer taskId) {
		return "project/task/task/preview/"
				+ GenericLinkUtils
						.encodeParam(new Object[] { projectId, taskId });
	}

	public static String generateMilestonePreviewLink(int projectId,
			int milestoneId) {
		return "project/milestone/preview/"
				+ GenericLinkUtils.encodeParam(new Object[] { projectId,
						milestoneId });
	}

	public static String generateProblemPreviewLink(Integer projectId,
			Integer problemId) {
		return "project/problem/preview/"
				+ GenericLinkUtils.encodeParam(new Object[] { projectId,
						problemId });
	}

	public static String generateRiskPreview(Integer projectId, Integer riskId) {
		return "project/risk/preview/"
				+ GenericLinkUtils
						.encodeParam(new Object[] { projectId, riskId });
	}

	public static String generateMessagePreviewLink(Integer projectId,
			Integer messageId) {
		if (messageId == null) {
			return "";
		}
		return "project/message/preview/"
				+ GenericLinkUtils.encodeParam(new Object[] { projectId,
						messageId });
	}

	public static String generateBugComponentPreviewLink(Integer projectId,
			Integer componentId) {
		if (componentId == null) {
			return "";
		}
		return "project/bug/component/preview/"
				+ GenericLinkUtils.encodeParam(new Object[] { projectId,
						componentId });
	}

	public static String generateBugVersionPreviewLink(Integer projectId,
			Integer versionId) {
		if (versionId == null) {
			return "";
		}
		return "project/bug/version/preview/"
				+ GenericLinkUtils.encodeParam(new Object[] { projectId,
						versionId });
	}

	public static String generateProjectLink(int projectId) {
		return "project/dashboard/"
				+ GenericLinkUtils.encodeParam(new Object[] { projectId });
	}

	public static String generateBugPreviewLink(int projectId, int bugId) {
		return "project/bug/preview/"
				+ GenericLinkUtils
						.encodeParam(new Object[] { projectId, bugId });
	}

	public static String generateRolePreviewLink(int projectId, int roleId) {
		return "project/role/preview/"
				+ GenericLinkUtils
						.encodeParam(new Object[] { projectId, roleId });
	}

	public static String generateStandUpPreviewLink() {
		return "project/standup/list/Mg";
	}
}
