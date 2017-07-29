package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.project.domain.SimpleMessage;
import com.mycollab.module.project.domain.criteria.MessageSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface MessageMapperExt extends ISearchableDAO<MessageSearchCriteria> {
    SimpleMessage findMessageById(int messageId);
}
