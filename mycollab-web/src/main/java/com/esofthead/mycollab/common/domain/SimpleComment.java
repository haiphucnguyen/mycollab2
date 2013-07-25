package com.esofthead.mycollab.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

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
			String commentPath = AttachmentUtils.getCommentPath(
					this.getSaccountid(), this.getId());
			attachments = contentJcr.getContents(commentPath);
		}

		if (attachments == null) {
			attachments = new ArrayList<Content>();
		}
		return attachments;
	}
}
