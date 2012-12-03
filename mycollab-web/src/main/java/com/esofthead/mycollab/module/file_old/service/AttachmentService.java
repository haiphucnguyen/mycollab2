package com.esofthead.mycollab.module.file_old.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.module.file_old.domain.Attachment;

public interface AttachmentService extends ICrudService<Integer, Attachment> {
	List<Attachment> findByAttachmentId(String attachmentid);

	void removeById(String attachmentid);
}
