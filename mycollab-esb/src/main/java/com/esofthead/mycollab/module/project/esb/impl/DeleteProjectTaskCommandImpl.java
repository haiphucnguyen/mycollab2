package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.esb.DeleteProjectTaskCommand;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class DeleteProjectTaskCommandImpl implements DeleteProjectTaskCommand {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectTaskCommandImpl.class);

	@Override
	public void taskRemoved(String username, int accountId, int projectId,
			int taskId) {
		log.debug("Remove task id {} of project {} by user {}", new Object[] {
				taskId, projectId, username });

		removeRelatedFiles(accountId, projectId, taskId);
		removeRelatedComments(taskId);

	}

	private void removeRelatedFiles(int accountId, int projectId, int taskId) {
		log.debug("Delete files of task id {} in project {}", taskId, projectId);

		ResourceService resourceService = ApplicationContextUtil
				.getBean(ResourceService.class);
		String attachmentPath = AttachmentUtils.getProjectTaskAttachmentPath(
				accountId, projectId, taskId);
		resourceService.removeResource(attachmentPath, "");
	}

	private void removeRelatedComments(int taskId) {
		log.debug("Delete related comments of task id {}", taskId);
		CommentMapper commentMapper = ApplicationContextUtil
				.getBean(CommentMapper.class);

		CommentExample ex = new CommentExample();
		ex.createCriteria().andTypeEqualTo(CommentType.PRJ_TASK.toString())
				.andExtratypeidEqualTo(taskId);
		commentMapper.deleteByExample(ex);
	}

}
