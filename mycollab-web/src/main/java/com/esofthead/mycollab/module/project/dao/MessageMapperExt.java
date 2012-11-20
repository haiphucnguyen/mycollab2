package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;

public interface MessageMapperExt extends ISearchableDAO<MessageSearchCriteria>{

	void saveMessageAndReturnId(Message message);

	List<GroupItem> getMessageCategoryGroup(int projectid);
}
