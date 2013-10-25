package com.esofthead.mycollab.schedule.email.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.schedule.email.GenericLinkGenerator;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class ProjectMailLinkGenerator extends GenericLinkGenerator {
	private static Logger log = LoggerFactory
			.getLogger(ProjectMailLinkGenerator.class);

	private int projectId;
	private String siteUrl;

	public ProjectMailLinkGenerator(int projectId) {
		this.projectId = projectId;

		ProjectService projectService = ApplicationContextUtil
				.getSpringBean(ProjectService.class);
		String subdomain = projectService.getSubdomainOfProject(projectId);
		if (subdomain != null) {
			siteUrl = SiteConfiguration.getSiteUrl(subdomain);
		} else {
			log.error("Can not find subdomain for projectid {}", projectId);
			siteUrl = "";
		}
	}

	public String generateProjectFullLink() {
		return siteUrl + ProjectLinkUtils.URL_PREFIX_PARAM
				+ "project/dashboard/" + UrlEncodeDecoder.encode(projectId);
	}

	public String generateMessagePreviewFullLink(Integer messageId) {
		return siteUrl
				+ ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateMessagePreviewLink(projectId,
						messageId);
	}

	public String generateBugPreviewFullLink(Integer bugId) {
		return siteUrl + ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateBugPreviewLink(projectId, bugId);
	}

	public String generateMilestonePreviewFullLink(Integer milestoneId) {
		if (milestoneId == null) {
			return "";
		}
		return siteUrl
				+ ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateMilestonePreviewLink(projectId,
						milestoneId);
	}

	public String generateTaskPreviewFullLink(Integer taskId) {
		return siteUrl + ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateTaskPreviewLink(projectId, taskId);
	}

	public String generateTaskGroupPreviewFullLink(Integer taskgroupId) {
		return siteUrl
				+ ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateTaskGroupPreviewLink(projectId,
						taskgroupId);
	}

	public String generateRiskPreviewFullLink(Integer riskId) {
		return siteUrl + ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateRiskPreview(projectId, riskId);
	}

	public String generateProblemPreviewFullLink(Integer problemId) {
		return siteUrl
				+ ProjectLinkUtils.URL_PREFIX_PARAM
				+ ProjectLinkUtils.generateProblemPreviewLink(projectId,
						problemId);
	}

	@Override
	public String getSiteUrl() {
		return siteUrl;
	}
}
