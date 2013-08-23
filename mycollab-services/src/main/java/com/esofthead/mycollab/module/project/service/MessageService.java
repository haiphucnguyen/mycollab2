package com.esofthead.mycollab.module.project.service;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;

@RemotingDestination
public interface MessageService extends
		IDefaultService<Integer, Message, MessageSearchCriteria> {
	@Cacheable
	SimpleMessage findMessageById(int messageId, @CacheKey Integer sAccountId);
}
