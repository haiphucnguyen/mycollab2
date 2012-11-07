package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;

public interface MessageMapperExt {
	int getTotalCount(MessageSearchCriteria criteria);

	List<Message> findPagableList(MessageSearchCriteria criteria,
			RowBounds rowBounds);

	void saveMessageAndReturnId(Message message);

	List<GroupItem> getMessageCategoryGroup(int projectid);
}
