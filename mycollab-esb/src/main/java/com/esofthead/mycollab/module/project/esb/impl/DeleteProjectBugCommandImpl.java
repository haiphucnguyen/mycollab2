package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.esb.DeleteProjectBugCommand;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class DeleteProjectBugCommandImpl implements DeleteProjectBugCommand {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectBugCommandImpl.class);

	@Override
	public void bugRemoved(String username, int accountId, int projectId,
			int bugId) {
		log.debug("Remove bug {} of project {} by user {}", new Object[] {
				bugId, projectId, username });

		removeRelatedFiles(accountId, projectId, bugId);
		removeRelatedComments(bugId);
	}

	private void removeRelatedFiles(int accountId, int projectId, int bugId) {
		log.debug("Delete files of bug {} in project {}", bugId, projectId);

		ResourceService resourceService = ApplicationContextUtil
				.getBean(ResourceService.class);
		String attachmentPath = AttachmentUtils.getProjectBugAttachmentPath(
				accountId, projectId, bugId);
		resourceService.removeResource(attachmentPath, "");
	}

	private void removeRelatedComments(int bugId) {
		log.debug("Delete related comments of bug {}", bugId);
		CommentMapper commentMapper = ApplicationContextUtil
				.getBean(CommentMapper.class);

		CommentExample ex = new CommentExample();
		ex.createCriteria().andTypeEqualTo(CommentType.PRJ_BUG.toString())
				.andExtratypeidEqualTo(bugId);
		commentMapper.deleteByExample(ex);
	}

}
