package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.UrlEncodeDecoder;

public class ProjectLinkGenerator {
	public static String generateBugPreviewLink(int projectId, int bugId) {
		return "project/bug/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + bugId);
	}

	public static String generateTaskPreviewFullLink(int projectId, int taskId) {
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ "?url=" + generateTaskPreviewLink(projectId, taskId);
	}

	public static String generateTaskPreviewLink(int projectId, int taskId) {
		return "project/task/task/preview/"
				+ UrlEncodeDecoder.encode(projectId + "/" + taskId);
	}
}
