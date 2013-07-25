package com.esofthead.mycollab.module.crm.domain;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class SimpleNote extends Note {

	private static final long serialVersionUID = 1L;
	private String createdUserAvatarId;
	private String createUserFullName;
	private List<Content> attachments;
	private List<SimpleComment> comments;

	public String getCreatedUserAvatarId() {
		return createdUserAvatarId;
	}

	public void setCreatedUserAvatarId(String createdUserAvatarId) {
		this.createdUserAvatarId = createdUserAvatarId;
	}

	public String getCreateUserFullName() {
		return createUserFullName;
	}

	public void setCreateUserFullName(String createUserFullName) {
		this.createUserFullName = createUserFullName;
	}

	public List<Content> getAttachments() {
		if (attachments == null) {
			ContentJcrDao contentJcr = ApplicationContextUtil
					.getBean(ContentJcrDao.class);
			String notePath = AttachmentUtils.getCrmNotePath(
					this.getSaccountid(), this.getId());
			attachments = contentJcr.getContents(notePath);
		}

		if (attachments == null) {
			attachments = new ArrayList<Content>();
		}
		return attachments;
	}

	public List<SimpleComment> getComments() {
		return comments;
	}

	public void setComments(List<SimpleComment> comments) {
		this.comments = comments;
	}
}
