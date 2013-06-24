package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.module.file.domain.Attachment;
import java.util.List;

public class SimpleNote extends Note {

    private static final long serialVersionUID = 1L;
    private String createdUserAvatarId;
    private String createUserFullName;
    private List<Attachment> attachments;
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

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<SimpleComment> getComments() {
        return comments;
    }

    public void setComments(List<SimpleComment> comments) {
        this.comments = comments;
    }
}
