package com.esofthead.mycollab.module.project;

import com.esofthead.mycollab.common.UrlEncodeDecoder;

public class ProjectLinkUtils {
	public static String URL_PREFIX_PARAM = "?url=";

	public static String DEFAULT_PREFIX_PARAM = "#";

	public static String generateTaskGroupPreviewLink(Integer projectId,
			Integer taskgroupId) {
		return "project/task/taskgroup/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + taskgroupId);
	}
	
	public static String generateTaskPreviewLink(Integer projectId,
			Integer taskId) {
		return "project/task/task/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + taskId);
	}
	
	public static String generateMilestonePreviewLink(int projectId,
			int milestoneId) {
		return "project/milestone/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + milestoneId);
	}
	
	public static String generateProblemPreviewLink(Integer projectId,
			Integer problemId) {
		return "project/problem/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + problemId);
	}
	
	public static String generateRiskPreview(Integer projectId, Integer riskId) {
		return "project/risk/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + riskId);
	}
	
	public static String generateMessagePreviewLink(Integer projectId,
			Integer messageId) {
		if (messageId == null) {
			return "";
		}
		return "project/message/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + messageId);
	}
	
	public static String generateBugComponentPreviewLink(Integer projectId,
			Integer componentId) {
		if (componentId == null) {
			return "";
		}
		return "project/bug/component/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + componentId);
	}
	
	public static String generateBugVersionPreviewLink(Integer projectId,
			Integer versionId) {
		if (versionId == null) {
			return "";
		}
		return "project/bug/version/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + versionId);
	}
	
	public static String generateProjectLink(int projectId) {
		return "project/dashboard/" + UrlEncodeDecoder.encode(projectId);
	}

	public static String generateBugPreviewLink(int projectId, int bugId) {
		return "project/bug/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + bugId);
	}
}
