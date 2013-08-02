package com.esofthead.mycollab.core.persistence;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface IMassUpdateDAO<R, S extends SearchCriteria> {
	void updateBySearchCriteria(@Param("record") R record,
			@Param("searchCriteria") S searchCriteria);
}
