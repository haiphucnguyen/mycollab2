package com.esofthead.mycollab.module.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.file.dao.AttachmentMapper;
import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.module.file.service.AttachmentService;

@Service
public class AttachmentServiceImpl extends
		DefaultCrudService<Integer, Attachment> implements AttachmentService {
	
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	public ICrudGenericDAO<Integer, Attachment> getCrudMapper() {
		return attachmentMapper;
	}
//
//	@Override
//	public List<Attachment> findByAttachmentId(String attachmentid) {
//		AttachmentExample ex = new AttachmentExample();
//		ex.createCriteria().andAttachmentidEqualTo(attachmentid);
//		return attachmentMapper.selectByExample(ex);
//	}
//
//	@Override
//	public void removeById(String attachmentid) {
//		AttachmentExample ex = new AttachmentExample();
//		ex.createCriteria().andAttachmentidEqualTo(attachmentid);
//		attachmentMapper.deleteByExample(ex);
//	}
}
