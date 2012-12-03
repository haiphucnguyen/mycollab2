package com.esofthead.mycollab.module.file_old.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.file_old.ContentConstants;
import com.esofthead.mycollab.module.file_old.dao.AttachmentMapper;
import com.esofthead.mycollab.module.file_old.domain.Attachment;
import com.esofthead.mycollab.module.file_old.domain.AttachmentExample;
import com.esofthead.mycollab.module.file_old.domain.Content;
import com.esofthead.mycollab.module.file_old.service.AccessValidatorFileSystemService;
import com.esofthead.mycollab.module.file_old.service.AttachmentService;
import com.esofthead.mycollab.module.file_old.validator.AccessValidatorFactory;

@Service
public class AttachmentServiceImpl extends
		DefaultCrudService<Integer, Attachment> implements AttachmentService {

	@Autowired
	private AccessValidatorFileSystemService accessValidatorFileSystemService;
	
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	public ICrudGenericDAO<Integer, Attachment> getCrudMapper() {
		return attachmentMapper;
	}

	@Override
	public List<Attachment> findByAttachmentId(String attachmentid) {
		AttachmentExample ex = new AttachmentExample();
		ex.createCriteria().andAttachmentidEqualTo(attachmentid);
		return attachmentMapper.selectByExample(ex);
	}

	@Override
	public void removeById(String attachmentid) {
		AttachmentExample ex = new AttachmentExample();
		ex.createCriteria().andAttachmentidEqualTo(attachmentid);
		attachmentMapper.deleteByExample(ex);

		String path = ContentConstants.ATTACHMENT_PATH + "/" + attachmentid;
		Content content = accessValidatorFileSystemService.findByPath(path);
		if (content != null) {
			accessValidatorFileSystemService.removeWithAccessValidator(
					AccessValidatorFactory.getSimpleAccessValidator(), content,
					false);
		}
	}
}
