package com.esofthead.mycollab.common.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class SimpleComment extends Comment {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(SimpleComment.class);

	private String ownerAvatarId;
	private String ownerFullName;
	private List<Content> attachments;

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getOwnerAvatarId() {
		return ownerAvatarId;
	}

	public void setOwnerAvatarId(String ownerAvatarId) {
		this.ownerAvatarId = ownerAvatarId;
	}

	public List<Content> getAttachments() {
		try {
			if (attachments == null) {
				ContentJcrDao contentJcr = ApplicationContextUtil
						.getBean(ContentJcrDao.class);

				String commentPath = "";
				if (CommentTypeConstants.PRJ_BUG.equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectBugCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentTypeConstants.PRJ_MESSAGE.equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectMessageCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentTypeConstants.PRJ_MILESTONE.equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectMilestoneCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentTypeConstants.PRJ_PROBLEM.equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectProblemCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentTypeConstants.PRJ_RISK.equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectRiskCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentTypeConstants.PRJ_TASK.equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectTaskCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentTypeConstants.PRJ_TASK_LIST.equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectTaskListCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentTypeConstants.CRM_NOTE.equals(getType())) {
					commentPath = AttachmentUtils
							.getCrmNoteCommentAttachmentPath(getSaccountid(),
									getTypeid(), getId());
				} else {
					throw new MyCollabException("Do not support comment type "
							+ getType());
				}

				attachments = contentJcr.getContents(commentPath);
			}
		} catch (Exception e) {
			log.error("Error while get attachments of comment {}",
					new Object[] { getId() });
		}

		if (attachments == null) {
			attachments = new ArrayList<Content>();
		}
		return attachments;
	}
}
