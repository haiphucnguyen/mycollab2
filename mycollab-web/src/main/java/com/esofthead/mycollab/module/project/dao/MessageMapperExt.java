package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;

public interface MessageMapperExt extends ISearchableDAO<MessageSearchCriteria> {
    SimpleMessage findMessageById(int messageId);
}
