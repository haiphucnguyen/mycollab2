package com.esofthead.mycollab.module.file.service.impl;

import java.util.List;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.file.ContentConstants;
import com.esofthead.mycollab.module.file.dao.AttachmentMapper;
import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.module.file.domain.AttachmentExample;
import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.service.AccessValidatorFileSystemService;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.file.validator.AccessValidatorFactory;

public class AttachmentServiceImpl extends
		DefaultCrudService<Integer, Attachment> implements AttachmentService {

	private AccessValidatorFileSystemService accessValidatorFileSystemService;

	public void setAccessValidatorFileSystemService(
			AccessValidatorFileSystemService accessValidatorFileSystemService) {
		this.accessValidatorFileSystemService = accessValidatorFileSystemService;
	}

	@Override
	public List<Attachment> findByAttachmentId(String attachmentid) {
		AttachmentExample ex = new AttachmentExample();
		ex.createCriteria().andAttachmentidEqualTo(attachmentid);
		return ((AttachmentMapper) daoObj).selectByExample(ex);
	}

	@Override
	public void removeById(String attachmentid) {
		AttachmentExample ex = new AttachmentExample();
		ex.createCriteria().andAttachmentidEqualTo(attachmentid);
		((AttachmentMapper) daoObj).deleteByExample(ex);

		String path = ContentConstants.ATTACHMENT_PATH + "/" + attachmentid;
		Content content = accessValidatorFileSystemService.findByPath(path);
		if (content != null) {
			accessValidatorFileSystemService.removeWithAccessValidator(
					AccessValidatorFactory.getSimpleAccessValidator(), content,
					false);
		}
	}
}
