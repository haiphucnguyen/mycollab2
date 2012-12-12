package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.project.ChangeLogAction;
import com.esofthead.mycollab.module.project.ChangeLogSource;
import com.esofthead.mycollab.module.project.dao.MessageMapper;
import com.esofthead.mycollab.module.project.dao.MessageMapperExt;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;
import com.esofthead.mycollab.module.project.service.MessageService;

@Service
public class MessageServiceImpl extends DefaultService<Integer, Message, MessageSearchCriteria>
		implements MessageService {

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private MessageMapperExt messageMapperExt;

	@Autowired
	private ChangeLogService changeLogService;

	@Override
	public ICrudGenericDAO<Integer, Message> getCrudMapper() {
		return messageMapper;
	}

	@Override
	public ISearchableDAO<MessageSearchCriteria> getSearchMapper() {
		return messageMapperExt;
	}

	@Override
	protected int internalRemoveWithSession(Integer primaryKey, String username) {
		Message message = this.findByPrimaryKey(primaryKey);
		changeLogService.saveChangeLog(message.getProjectid(), username,
				ChangeLogSource.MESSAGE, message.getId(),
				ChangeLogAction.DELETE, message.getTitle());

		String attachmentid = "project-message-" + primaryKey;
		attachmentService.removeById(attachmentid);
		return super.internalRemoveWithSession(primaryKey, username);
	}

	@Override
	protected int internalUpdateWithSession(Message record, String username) {
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.MESSAGE, record.getId(),
				ChangeLogAction.UPDATE, record.getTitle());
		return super.internalUpdateWithSession(record, username);
	}

	@Override
	public int saveMessageAndReturnId(Message message, String username) {
		messageMapperExt.saveMessageAndReturnId(message);
		int messageid = message.getId();
		changeLogService.saveChangeLog(message.getProjectid(), username,
				ChangeLogSource.MESSAGE, messageid, ChangeLogAction.CREATE,
				message.getTitle());
		return messageid;
	}

}
