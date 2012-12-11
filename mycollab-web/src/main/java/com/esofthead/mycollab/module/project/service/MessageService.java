package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;

public interface MessageService extends
		IDefaultService<Integer, Message, MessageSearchCriteria> {

	/**
	 * 
	 * @param message
	 * @return
	 */
	int saveMessageAndReturnId(Message message, String sessionId);
}
