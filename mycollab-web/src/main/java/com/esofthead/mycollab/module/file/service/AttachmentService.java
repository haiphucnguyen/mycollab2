package com.esofthead.mycollab.module.file.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.module.file.domain.Attachment;

public interface AttachmentService extends ICrudService<Attachment, Integer> {
	List<Attachment> findByAttachmentId(String attachmentid);

	void removeById(String attachmentid);
}
