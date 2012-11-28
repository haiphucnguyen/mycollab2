package com.esofthead.mycollab.core.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface ISearchableDAO<S extends SearchCriteria> {
	int getTotalCount(S criteria);

	/**
	 * 
	 * @param criteria
	 * @param skipNum
	 * @param maxResult
	 * @return
	 */
	List findPagableListByCriteria(S criteria, RowBounds rowBounds);
	
	void removeByCriteria(S criteria);
}
