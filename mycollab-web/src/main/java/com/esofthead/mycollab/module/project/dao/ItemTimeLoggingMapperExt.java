package com.esofthead.mycollab.module.project.dao;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;

public interface ItemTimeLoggingMapperExt extends
		ISearchableDAO<ItemTimeLoggingSearchCriteria> {

	Double getTotalHoursByCriteria(
			@Param("searchCriteria") ItemTimeLoggingSearchCriteria criteria);
}
