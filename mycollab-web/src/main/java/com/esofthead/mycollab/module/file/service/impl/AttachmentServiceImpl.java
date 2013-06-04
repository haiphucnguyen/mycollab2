package com.esofthead.mycollab.module.file.service.impl;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.file.dao.AttachmentMapper;
import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.module.file.domain.AttachmentExample;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.file.service.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends DefaultCrudService<Integer, Attachment> implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;
    
    @Autowired
    private ContentService contentService;

    @Override
    public ICrudGenericDAO<Integer, Attachment> getCrudMapper() {
        return attachmentMapper;
    }

    @Override
    public List<Attachment> findByAttachmentId(String type, int typeid) {
        AttachmentExample ex = new AttachmentExample();
        ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeid);
        return attachmentMapper.selectByExample(ex);
    }

    @Override
    public void removeAttachment(int accountid, Attachment attachment) {
        contentService.removeContent(accountid, attachment.getDocumentpath());
        attachmentMapper.deleteByPrimaryKey(attachment.getId());
    }
    
    
}
