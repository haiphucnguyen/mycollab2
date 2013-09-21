package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.esb.DeleteProjectTaskListCommand;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class DeleteProjectTaskListCommandImpl implements
		DeleteProjectTaskListCommand {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectTaskListCommandImpl.class);

	@Override
	public void taskListRemoved(String username, int accountId, int projectId,
			int taskListId) {
		log.debug("Remove task list id {} of project {} by user {}",
				new Object[] { taskListId, projectId, username });

		removeRelatedFiles(accountId, projectId, taskListId);
		removeRelatedComments(taskListId);

	}

	private void removeRelatedFiles(int accountId, int projectId, int taskListId) {
		log.debug("Delete files of task list id {} in project {}", taskListId,
				projectId);

		ResourceService resourceService = ApplicationContextUtil
				.getSpringBean(ResourceService.class);
		String attachmentPath = AttachmentUtils.getProjectTaskAttachmentPath(
				accountId, projectId, taskListId);
		resourceService.removeResource(attachmentPath, "", accountId);
	}

	private void removeRelatedComments(int taskListId) {
		log.debug("Delete related comments of task list id {}", taskListId);
		CommentMapper commentMapper = ApplicationContextUtil
				.getSpringBean(CommentMapper.class);

		CommentExample ex = new CommentExample();
		ex.createCriteria()
				.andTypeEqualTo(CommentType.PRJ_TASK_LIST.toString())
				.andExtratypeidEqualTo(taskListId);
		commentMapper.deleteByExample(ex);
	}

}
