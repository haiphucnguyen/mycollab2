package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.UrlEncodeDecoder;

public class ProjectLinkGenerator {

	public static String generateProjectLink(int projectId) {
		return "project/dashboard/" + UrlEncodeDecoder.encode(projectId);
	}

	public static String generateProjectFullLink(Integer projectId) {
		if (projectId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ "?url=" + "project/dashboard/"
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
				+ "?url=" + generateBugPreviewLink(projectId, bugId);
	}

	public static String generateTaskPreviewFullLink(Integer projectId,
			Integer taskId) {
		if (projectId == null || taskId == null) {
			return "";
		}
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ "?url=" + generateTaskPreviewLink(projectId, taskId);
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
				+ "?url="
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
				+ "?url="
				+ generateMilestonePreviewLink(projectId, milestoneId);
	}
}
