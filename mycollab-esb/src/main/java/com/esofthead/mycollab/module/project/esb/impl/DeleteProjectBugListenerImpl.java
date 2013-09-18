package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.esb.DeleteProjectBugListener;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class DeleteProjectBugListenerImpl implements DeleteProjectBugListener {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectBugListenerImpl.class);

	@Override
	public void bugRemoved(String username, int accountId, int projectId,
			int bugId) {
		log.debug("Remove bug {} of project {} by user {}", new Object[] {
				bugId, projectId, username });

		removeBugFiles(accountId, projectId, bugId);
		removeBugComments(bugId);
	}

	private void removeBugFiles(int accountId, int projectId, int bugId) {
		log.debug("Delete files of bug {} in project {}", projectId, bugId);

		ResourceService resourceService = ApplicationContextUtil
				.getBean(ResourceService.class);
		String bugAttachmentPath = AttachmentUtils.getProjectBugAttachmentPath(
				accountId, projectId, bugId);
		resourceService.removeResource(bugAttachmentPath, "");
	}

	private void removeBugComments(int bugId) {
		log.debug("Delete related comments of bug {}", bugId);
		CommentMapper commentMapper = ApplicationContextUtil
				.getBean(CommentMapper.class);

		CommentExample ex = new CommentExample();
		ex.createCriteria().andTypeEqualTo(CommentType.PRJ_BUG.toString())
				.andExtratypeidEqualTo(bugId);
		commentMapper.deleteByExample(ex);
	}

}
