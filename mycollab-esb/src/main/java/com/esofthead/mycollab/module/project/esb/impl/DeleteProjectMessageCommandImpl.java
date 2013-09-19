package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.esb.DeleteProjectMessageCommand;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class DeleteProjectMessageCommandImpl implements
		DeleteProjectMessageCommand {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectMessageCommandImpl.class);

	@Override
	public void messageRemoved(String username, int accountId, int projectId,
			int messageId) {
		log.debug("Remove message id {} of project {} by user {}",
				new Object[] { messageId, projectId, username });

		removeRelatedFiles(accountId, projectId, messageId);
		removeRelatedComments(messageId);
	}

	private void removeRelatedFiles(int accountId, int projectId, int messageId) {
		log.debug("Delete files of bug {} in project {}", messageId, projectId);

		ResourceService resourceService = ApplicationContextUtil
				.getBean(ResourceService.class);
		String attachmentPath = AttachmentUtils
				.getProjectMessageAttachmentPath(accountId, projectId,
						messageId);
		resourceService.removeResource(attachmentPath, "");
	}

	private void removeRelatedComments(int messageId) {
		log.debug("Delete related comments of message id {}", messageId);
		CommentMapper commentMapper = ApplicationContextUtil
				.getBean(CommentMapper.class);

		CommentExample ex = new CommentExample();
		ex.createCriteria().andTypeEqualTo(CommentType.PRJ_MESSAGE.toString())
				.andExtratypeidEqualTo(messageId);
		commentMapper.deleteByExample(ex);
	}

}
