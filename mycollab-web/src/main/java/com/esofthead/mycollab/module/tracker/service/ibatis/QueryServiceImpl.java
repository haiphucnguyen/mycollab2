package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.tracker.dao.QueryMapperExt;
import com.esofthead.mycollab.module.tracker.domain.Query;
import com.esofthead.mycollab.module.tracker.domain.criteria.QuerySearchCriteria;
import com.esofthead.mycollab.module.tracker.service.QueryService;

public class QueryServiceImpl extends DefaultCrudService<Integer, Query>
		implements QueryService {

	private QueryMapperExt queryExtDAO;

	public void setQueryExtDAO(QueryMapperExt queryExtDAO) {
		this.queryExtDAO = queryExtDAO;
	}

	@Override
	public List findPagableListByCriteria(QuerySearchCriteria criteria,
			int skipNum, int maxResult) {
		return queryExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(QuerySearchCriteria criteria) {
		return queryExtDAO.getTotalCount(criteria);
	}

}
