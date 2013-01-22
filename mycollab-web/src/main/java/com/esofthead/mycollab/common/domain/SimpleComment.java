package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.module.file.domain.Attachment;
import java.util.List;

public class SimpleComment extends Comment {

    private static final long serialVersionUID = 1L;
    private String ownerFullName;
    private List<Attachment> attachments;
    

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
