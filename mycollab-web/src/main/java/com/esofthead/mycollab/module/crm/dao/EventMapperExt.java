package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;

public interface EventMapperExt extends ISearchableDAO<EventSearchCriteria> {
	int getTotalCountFromTask(EventSearchCriteria criteria);

	int getTotalCountFromCall(EventSearchCriteria criteria);

	int getTotalCountFromMeeting(EventSearchCriteria criteria);
}
