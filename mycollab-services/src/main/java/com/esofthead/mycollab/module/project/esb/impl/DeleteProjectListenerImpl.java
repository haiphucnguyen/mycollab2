package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.dao.ActivityStreamMapper;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.ActivityStreamExample;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.project.esb.DeleteProjectListener;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class DeleteProjectListenerImpl implements DeleteProjectListener {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectListenerImpl.class);

	@Override
	public void projectRemoved(int accountId, int projectId) {
		log.debug("Remove project {}", projectId);

		deleteProjectActivityStream(projectId);
		deleteRelatedComments(projectId);
		deleteProjectFiles(accountId, projectId);
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
		log.debug("Delete related comments");
		CommentMapper commentMapper = ApplicationContextUtil
				.getBean(CommentMapper.class);

		CommentExample ex = new CommentExample();
		ex.createCriteria().andExtratypeidEqualTo(projectId);
		commentMapper.deleteByExample(ex);
	}

	private void deleteProjectFiles(int accountid, int projectId) {
		log.debug("Delete files of project {}", projectId);

		ResourceService resourceService = ApplicationContextUtil
				.getBean(ResourceService.class);

		String rootPath = String.format("%d/project/%d", accountid, projectId);
		resourceService.removeResource(rootPath, "");
	}

}
