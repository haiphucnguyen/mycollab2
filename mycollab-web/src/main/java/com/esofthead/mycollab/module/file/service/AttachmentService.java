package com.esofthead.mycollab.module.file.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.file.domain.Attachment;

public interface AttachmentService extends ICrudService<Integer, Attachment> {

    List<Attachment> findByAttachmentId(String type, int typeid);
    
    void removeAttachment(int accountid, Attachment attachment);
}
