package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.esb.DeleteProjectMilestoneCommand;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class DeleteProjectMilestoneCommandImpl implements
		DeleteProjectMilestoneCommand {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectMilestoneCommandImpl.class);

	@Override
	public void milestoneRemoved(String username, int accountId, int projectId,
			int milestoneId) {
		log.debug("Remove milestone id {} of project {} by user {}",
				new Object[] { milestoneId, projectId, username });

		removeRelatedFiles(accountId, projectId, milestoneId);
		removeRelatedComments(milestoneId);

	}

	private void removeRelatedFiles(int accountId, int projectId,
			int milestoneId) {
		log.debug("Delete files of bug {} in project {}", milestoneId,
				projectId);

		ResourceService resourceService = ApplicationContextUtil
				.getBean(ResourceService.class);
		String attachmentPath = AttachmentUtils
				.getProjectMilestoneAttachmentPath(accountId, projectId,
						milestoneId);
		resourceService.removeResource(attachmentPath, "");
	}

	private void removeRelatedComments(int milestoneId) {
		log.debug("Delete related comments of milestone id {}", milestoneId);
		CommentMapper commentMapper = ApplicationContextUtil
				.getBean(CommentMapper.class);

		CommentExample ex = new CommentExample();
		ex.createCriteria()
				.andTypeEqualTo(CommentType.PRJ_MILESTONE.toString())
				.andExtratypeidEqualTo(milestoneId);
		commentMapper.deleteByExample(ex);
	}

}
