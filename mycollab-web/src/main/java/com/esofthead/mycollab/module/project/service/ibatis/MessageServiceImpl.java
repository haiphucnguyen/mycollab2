package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.project.ChangeLogAction;
import com.esofthead.mycollab.module.project.ChangeLogSource;
import com.esofthead.mycollab.module.project.dao.MessageMapperExt;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;
import com.esofthead.mycollab.module.project.service.MessageService;

public class MessageServiceImpl extends DefaultCrudService<Integer, Message>
		implements MessageService {

	private AttachmentService attachmentService;

	private MessageMapperExt messageExtDAO;

	private ChangeLogService changeLogService;

	public void setMessageExtDAO(MessageMapperExt messageExtDAO) {
		this.messageExtDAO = messageExtDAO;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public void setChangeLogService(ChangeLogService changeLogService) {
		this.changeLogService = changeLogService;
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
	public List findPagableListByCriteria(MessageSearchCriteria criteria,
			int skipNum, int maxResult) {
		return messageExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(MessageSearchCriteria criteria) {
		return messageExtDAO.getTotalCount(criteria);
	}

	@Override
	public int saveMessageAndReturnId(Message message, String username) {
		messageExtDAO.saveMessageAndReturnId(message);
		int messageid = message.getId();
		changeLogService.saveChangeLog(message.getProjectid(), username,
				ChangeLogSource.MESSAGE, messageid, ChangeLogAction.CREATE,
				message.getTitle());
		return messageid;
	}

	@Override
	public Message findByPrimaryKey(Integer primaryKey) {
		MessageSearchCriteria criteria = new MessageSearchCriteria();
		criteria.setId(new NumberSearchField(SearchField.AND, primaryKey));
		List<Message> messages = messageExtDAO.findPagableList(criteria,
				new RowBounds(0, 1));
		if (messages.size() > 0) {
			return messages.get(0);
		}

		return null;
	}

	@Override
	public List<GroupItem> getMessageCategoryGroup(int projectid) {
		return messageExtDAO.getMessageCategoryGroup(projectid);
	}

}
