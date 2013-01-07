package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.module.file.domain.Attachment;
import java.util.List;

public class SimpleNote extends Note {

    private static final long serialVersionUID = 1L;
    private String createUserFullName;
    private List<Attachment> attachments;

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
}
