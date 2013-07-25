package com.esofthead.mycollab.esb.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.dao.ActivityStreamMapper;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.ActivityStreamExample;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.esb.handler.ProjectDeleteListener;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class ProjectDeleteListenerImpl implements ProjectDeleteListener {

	private static Logger log = LoggerFactory
			.getLogger(ProjectDeleteListenerImpl.class);

	@Override
	public void projectRemoved(int projectId) {
		log.debug("Remove project {}", projectId);

		deleteProjectActivityStream(projectId);
		deleteProjectFiles(projectId);
		deleteRelatedComments(projectId);
	}

	private void deleteProjectActivityStream(int projectId) {
		log.debug("Delete activity stream of project {}", projectId);

		ActivityStreamMapper activityStreamMapper = ApplicationContextUtil
				.getBean(ActivityStreamMapper.class);
		ActivityStreamExample ex = new ActivityStreamExample();
		ex.createCriteria().andExtratypeidEqualTo(projectId)
				.andModuleEqualTo(ModuleNameConstants.PRJ);
		activityStreamMapper.deleteByExample(ex);

	}

	private void deleteRelatedComments(int projectId) {
		CommentMapper commentMapper = ApplicationContextUtil
				.getBean(CommentMapper.class);

		CommentExample ex = new CommentExample();
		ex.createCriteria().andExtratypeidEqualTo(projectId);
		commentMapper.deleteByExample(ex);
	}

	private void deleteProjectFiles(int projectId) {
		log.debug("Delete files of project {}", projectId);

		ProjectService projectService = ApplicationContextUtil
				.getBean(ProjectService.class);
		Project project = projectService.findByPrimaryKey(projectId);

		ResourceService resourceService = ApplicationContextUtil
				.getBean(ResourceService.class);

		String rootPath = String.format("%d/project/%d",
				project.getSaccountid(), projectId);
		resourceService.removeResource(rootPath);
	}

}
