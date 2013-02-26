package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.MessageMapper;
import com.esofthead.mycollab.module.project.dao.MessageMapperExt;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.service.MessageService;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "title", type = ProjectContants.MESSAGE, extraFieldName = "projectid")
public class MessageServiceImpl extends
		DefaultService<Integer, Message, MessageSearchCriteria> implements
		MessageService {

	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private MessageMapperExt messageMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Message> getCrudMapper() {
		return messageMapper;
	}

	@Override
	public ISearchableDAO<MessageSearchCriteria> getSearchMapper() {
		return messageMapperExt;
	}

	@Override
	public SimpleMessage findMessageById(int messageId) {
		return messageMapperExt.findMessageById(messageId);
	}
}
