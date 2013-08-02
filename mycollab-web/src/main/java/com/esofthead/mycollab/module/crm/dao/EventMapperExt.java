package com.esofthead.mycollab.module.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;

public interface EventMapperExt extends ISearchableDAO<EventSearchCriteria> {
	int getTotalCountFromTask(
			@Param("searchCriteria") EventSearchCriteria criteria);

	int getTotalCountFromCall(
			@Param("searchCriteria") EventSearchCriteria criteria);

	int getTotalCountFromMeeting(
			@Param("searchCriteria") EventSearchCriteria criteria);
}
