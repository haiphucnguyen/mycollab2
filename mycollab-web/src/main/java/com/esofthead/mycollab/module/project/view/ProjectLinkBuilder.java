package com.esofthead.mycollab.module.project.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.web.AppContext;

public class ProjectLinkBuilder {

	private static Logger log = LoggerFactory
			.getLogger(ProjectLinkBuilder.class);

	public static String URL_PREFIX_PARAM = "?url=";

	public static String DEFAULT_PREFIX_PARAM = "#";

	public static class WebLinkGenerator {
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
			return AppContext.getSiteUrl() + DEFAULT_PREFIX_PARAM
					+ "project/user/preview/"
					+ UrlEncodeDecoder.encode(projectId + "/" + memberName);
		}

		public static String generateProjectLink(int projectId) {
			return "project/dashboard/" + UrlEncodeDecoder.encode(projectId);
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
			return AppContext.getSiteUrl() + URL_PREFIX_PARAM
					+ generateBugPreviewLink(projectId, bugId);
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

		public static String generateMessagePreviewLink(int projectId,
				int messageId) {
			return "project/message/preview/"
					+ UrlEncodeDecoder.encode(projectId + "/" + messageId);
		}

		public static String generateMessagePreviewFullLink(Integer projectId,
				Integer messageId, String prefixParam) {
			if (projectId == null || messageId == null) {
				return "";
			}
			return AppContext.getSiteUrl() + prefixParam
					+ generateMessagePreviewLink(projectId, messageId);
		}

		public static String generateRiskPreview(Integer projectId,
				Integer riskId) {
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
			return AppContext.getSiteUrl() + URL_PREFIX_PARAM
					+ "project/risk/preview/"
					+ UrlEncodeDecoder.encode(projectId + "/" + riskId);
		}

		public static String generateTaskPreviewFullLink(Integer projectId,
				Integer taskId) {
			if (projectId == null || taskId == null) {
				return "";
			}
			return AppContext.getSiteUrl() + URL_PREFIX_PARAM
					+ generateTaskPreviewLink(projectId, taskId);
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

		public static String generateTaskGroupPreviewFullLink(
				Integer projectId, Integer taskgroupId) {
			if (projectId == null || taskgroupId == null) {
				return "";
			}
			return AppContext.getSiteUrl() + URL_PREFIX_PARAM
					+ generateTaskGroupPreviewLink(projectId, taskgroupId);
		}

		public static String generateMilestonePreviewLink(int projectId,
				int milestoneId) {
			return "project/milestone/preview/"
					+ UrlEncodeDecoder.encode(projectId + "/" + milestoneId);
		}

		public static String generateMilestonePreviewFullLink(
				Integer projectId, Integer milestoneId) {
			if (projectId == null || milestoneId == null) {
				return "";
			}
			return AppContext.getSiteUrl() + URL_PREFIX_PARAM
					+ generateMilestonePreviewLink(projectId, milestoneId);
		}

		public static String generateProjectItemLink(int projectId,
				String type, int typeid) {
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

			return "#" + result;
		}
	}

	public static class MailLinkGenerator {
		private int projectId;
		private String siteUrl;

		public MailLinkGenerator(int projectId) {
			this.projectId = projectId;

			if (!ApplicationProperties.productionMode) {
				siteUrl = ApplicationProperties
						.getProperty(ApplicationProperties.APP_URL);
			} else {
				ProjectService projectService = ApplicationContextUtil
						.getApplicationContext().getBean(ProjectService.class);
				String subdomain = projectService
						.getSubdomainOfProject(projectId);
				if (subdomain != null) {
					siteUrl = String.format(ApplicationProperties
							.getProperty(ApplicationProperties.APP_URL),
							subdomain);
				} else {
					log.error("Can not find subdomain for projectid {}",
							projectId);
					siteUrl = "";
				}
			}
		}

		public String generateProjectFullLink() {
			return siteUrl + URL_PREFIX_PARAM + "project/dashboard/"
					+ UrlEncodeDecoder.encode(projectId);
		}

		public String generateMessagePreviewFullLink(Integer messageId) {
			return siteUrl
					+ URL_PREFIX_PARAM
					+ WebLinkGenerator.generateMessagePreviewLink(projectId,
							messageId);
		}

		public String generateBugPreviewFullLink(Integer bugId) {
			return siteUrl + URL_PREFIX_PARAM
					+ WebLinkGenerator.generateBugPreviewLink(projectId, bugId);
		}

		public String generateMilestonePreviewFullLink(Integer milestoneId) {
			return siteUrl
					+ URL_PREFIX_PARAM
					+ WebLinkGenerator.generateMilestonePreviewLink(projectId,
							milestoneId);
		}

		public String generateTaskPreviewFullLink(Integer taskId) {
			return siteUrl
					+ URL_PREFIX_PARAM
					+ WebLinkGenerator.generateTaskPreviewLink(projectId,
							taskId);
		}

		public String generateTaskGroupPreviewFullLink(Integer taskgroupId) {
			return siteUrl
					+ URL_PREFIX_PARAM
					+ WebLinkGenerator.generateTaskGroupPreviewLink(projectId,
							taskgroupId);
		}

		public String getSiteUrl() {
			return siteUrl;
		}
	}
}
