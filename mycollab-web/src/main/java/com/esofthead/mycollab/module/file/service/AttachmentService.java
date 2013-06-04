package com.esofthead.mycollab.module.file.service;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.file.domain.Attachment;
import java.util.List;

public interface AttachmentService extends ICrudService<Integer, Attachment> {

    List<Attachment> findByAttachmentId(String type, int typeid);
    
    void removeAttachment(int accountid, Attachment attachment);
}
