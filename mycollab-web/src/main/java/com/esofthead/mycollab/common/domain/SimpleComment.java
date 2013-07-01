package com.esofthead.mycollab.common.domain;

import java.util.List;

import com.esofthead.mycollab.module.file.domain.Attachment;

public class SimpleComment extends Comment {

    private static final long serialVersionUID = 1L;
    
    private String ownerAvatarId;
    private String ownerFullName;
    private List<Attachment> attachments;
    

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

	public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
