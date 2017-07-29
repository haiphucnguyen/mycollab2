package com.mycollab.module.project.service;

import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.project.domain.Message;
import com.mycollab.module.project.domain.SimpleMessage;
import com.mycollab.module.project.domain.criteria.MessageSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface MessageService extends IDefaultService<Integer, Message, MessageSearchCriteria> {
    @Cacheable
    SimpleMessage findById(Integer messageId, @CacheKey Integer sAccountId);
}
