package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.ProjectContants;

public class ProjectLinkGenerator {

	private static String PARAM_PREFIX = "?url=";

	public static String generateProjectLink(int projectId) {
		return "project/dashboard/" + UrlEncodeDecoder.encode(projectId);
	}

	public static String generateProjectFullLink(Integer projectId) {
		if (projectId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ PARAM_PREFIX + "project/dashboard/"
				+ UrlEncodeDecoder.encode(projectId);
	}

	public static String generateBugPreviewLink(int projectId, int bugId) {
		return "project/bug/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + bugId);
	}

	public static String generateBugPreviewFullLink(Integer projectId,
			Integer bugId) {
		if (projectId == null || bugId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ PARAM_PREFIX + generateBugPreviewLink(projectId, bugId);
	}

	public static String generateBugVersionPreviewLink(Integer projectId,
			Integer versionId) {
		return "project/bug/version/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + versionId);
	}

	public static String generateBugComponentPreviewLink(Integer projectId,
			Integer componentId) {
		return "project/bug/component/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + componentId);
	}

	public static String generateMessagePreviewLink(int projectId, int messageId) {
		return "project/message/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + messageId);
	}

	public static String generateMessagePreviewFullLink(Integer projectId,
			Integer messageId) {
		if (projectId == null || messageId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ PARAM_PREFIX
				+ generateMessagePreviewLink(projectId, messageId);
	}

	public static String generateRiskPreview(Integer projectId, Integer riskId) {
		return "project/risk/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + riskId);
	}

	public static String generateProblemPreviewLink(Integer projectId,
			Integer problemId) {
		return "project/problem/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + problemId);
	}

	public static String generateRiskPreviewFullLink(Integer projectId,
			Integer riskId) {
		if (projectId == null || riskId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ PARAM_PREFIX + "project/risk/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + riskId);
	}

	public static String generateTaskPreviewFullLink(Integer projectId,
			Integer taskId) {
		if (projectId == null || taskId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ PARAM_PREFIX + generateTaskPreviewLink(projectId, taskId);
	}

	public static String generateTaskPreviewLink(int projectId, int taskId) {
		return "project/task/task/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + taskId);
	}

	public static String generateTaskGroupPreviewLink(Integer projectId,
			Integer taskgroupId) {
		return "project/task/taskgroup/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + taskgroupId);
	}

	public static String generateTaskGroupPreviewFullLink(Integer projectId,
			Integer taskgroupId) {
		if (projectId == null || taskgroupId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ PARAM_PREFIX
				+ generateTaskGroupPreviewLink(projectId, taskgroupId);
	}

	public static String generateMilestonePreviewLink(int projectId,
			int milestoneId) {
		return "project/milestone/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + milestoneId);
	}

	public static String generateMilestonePreviewFullLink(Integer projectId,
			Integer milestoneId) {
		if (projectId == null || milestoneId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ PARAM_PREFIX
				+ generateMilestonePreviewLink(projectId, milestoneId);
	}

	public static String generateProjectItemLink(int projectId, String type,
			int typeid) {
		String result = "";

		if (ProjectContants.PROJECT.equals(type)) {
		} else if (ProjectContants.MESSAGE.equals(type)) {
			result = generateMessagePreviewLink(projectId, typeid);
		} else if (ProjectContants.MILESTONE.equals(type)) {
			result = generateMilestonePreviewLink(projectId, typeid);
		} else if (ProjectContants.PROBLEM.equals(type)) {
			result = generateProblemPreviewLink(projectId, typeid);
		} else if (ProjectContants.RISK.equals(type)) {
			result = generateRiskPreview(projectId, typeid);
		} else if (ProjectContants.TASK.equals(type)) {
			result = generateTaskPreviewLink(projectId, typeid);
		} else if (ProjectContants.TASK_LIST.equals(type)) {
			result = generateTaskGroupPreviewLink(projectId, typeid);
		} else if (ProjectContants.BUG.equals(type)) {
			result = generateBugPreviewLink(projectId, typeid);
		} else if (ProjectContants.BUG_COMPONENT.equals(type)) {
			result = generateBugComponentPreviewLink(projectId, typeid);
		} else if (ProjectContants.BUG_VERSION.equals(type)) {
			result = generateBugVersionPreviewLink(projectId, typeid);
		} else if (ProjectContants.STANDUP.equals(type)) {

		}

		return "?url=" + result;
	}
}
