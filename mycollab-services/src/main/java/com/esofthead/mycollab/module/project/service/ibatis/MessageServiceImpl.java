package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.MessageMapper;
import com.esofthead.mycollab.module.project.dao.MessageMapperExt;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.schedule.email.project.MessageRelayEmailNotificationAction;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "title", type = ProjectContants.MESSAGE, extraFieldName = "projectid")
public class MessageServiceImpl extends
		DefaultService<Integer, Message, MessageSearchCriteria> implements
		MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private MessageMapperExt messageMapperExt;

	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Override
	public ICrudGenericDAO<Integer, Message> getCrudMapper() {
		return messageMapper;
	}

	@Override
	public int saveWithSession(Message record, String username) {
		int recordId = super.saveWithSession(record, username);
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, recordId), username);
		return recordId;
	}

	private RelayEmailNotification createNotification(Message record,
			String username, int recordId) {
		RelayEmailNotification relayNotification = new RelayEmailNotification();
		relayNotification.setChangeby(username);
		relayNotification.setChangecomment("");
		int sAccountId = record.getSaccountid();
		relayNotification.setSaccountid(sAccountId);
		relayNotification.setType(MonitorTypeConstants.PRJ_MESSAGE);
		relayNotification.setAction(MonitorTypeConstants.CREATE_ACTION);
		relayNotification
				.setEmailhandlerbean(MessageRelayEmailNotificationAction.class
						.getName());
		relayNotification.setTypeid(recordId);
		relayNotification.setExtratypeid(record.getProjectid());
		return relayNotification;
	}

	@Override
	public int updateWithSession(Message record, String username) {
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, record.getId()), username);
		return super.updateWithSession(record, username);
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
