package com.esofthead.mycollab.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.web.AppContext;

public class SimpleComment extends Comment {

	private static final long serialVersionUID = 1L;

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
		if (attachments == null) {
			ContentJcrDao contentJcr = ApplicationContextUtil
					.getBean(ContentJcrDao.class);

			String commentPath = "";
			if (CommentTypeConstants.PRJ_BUG.equals(getType())) {
				commentPath = AttachmentUtils
						.getProjectBugCommentAttachmentPath(
								AppContext.getAccountId(), getExtratypeid(),
								getTypeid(), getId());
			} else if (CommentTypeConstants.PRJ_MESSAGE.equals(getType())) {
				commentPath = AttachmentUtils
						.getProjectMessageCommentAttachmentPath(
								AppContext.getAccountId(), getExtratypeid(),
								getTypeid(), getId());
			} else if (CommentTypeConstants.PRJ_MILESTONE.equals(getType())) {
				commentPath = AttachmentUtils
						.getProjectMilestoneCommentAttachmentPath(
								AppContext.getAccountId(), getExtratypeid(),
								getTypeid(), getId());
			} else if (CommentTypeConstants.PRJ_PROBLEM.equals(getType())) {
				commentPath = AttachmentUtils
						.getProjectProblemCommentAttachmentPath(
								AppContext.getAccountId(), getExtratypeid(),
								getTypeid(), getId());
			} else if (CommentTypeConstants.PRJ_RISK.equals(getType())) {
				commentPath = AttachmentUtils
						.getProjectRiskCommentAttachmentPath(
								AppContext.getAccountId(), getExtratypeid(),
								getTypeid(), getId());
			} else if (CommentTypeConstants.PRJ_TASK.equals(getType())) {
				commentPath = AttachmentUtils
						.getProjectTaskCommentAttachmentPath(
								AppContext.getAccountId(), getExtratypeid(),
								getTypeid(), getId());
			} else if (CommentTypeConstants.PRJ_TASK_LIST.equals(getType())) {
				commentPath = AttachmentUtils
						.getProjectTaskListCommentAttachmentPath(
								AppContext.getAccountId(), getExtratypeid(),
								getTypeid(), getId());
			} else {
				commentPath = AttachmentUtils.getCommentPath(
						this.getSaccountid(), this.getId());
			}

			attachments = contentJcr.getContents(commentPath);
		}

		if (attachments == null) {
			attachments = new ArrayList<Content>();
		}
		return attachments;
	}
}
