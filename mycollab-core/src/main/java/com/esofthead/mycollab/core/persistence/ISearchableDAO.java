package com.esofthead.mycollab.core.persistence;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ISearchableDAO<S extends SearchCriteria> {

	int getTotalCount(@Param("searchCriteria") S criteria);

	List findPagableListByCriteria(@Param("searchCriteria") S criteria,
			RowBounds rowBounds);

	Integer getNextItemKey(@Param("searchCriteria") S criteria);

	Integer getPreviousItemKey(@Param("searchCriteria") S criteria);

	void removeByCriteria(@Param("searchCriteria") S criteria);
}
