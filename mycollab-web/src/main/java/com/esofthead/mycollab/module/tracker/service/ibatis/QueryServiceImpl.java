package com.esofthead.mycollab.module.tracker.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.tracker.dao.QueryMapper;
import com.esofthead.mycollab.module.tracker.dao.QueryMapperExt;
import com.esofthead.mycollab.module.tracker.domain.Query;
import com.esofthead.mycollab.module.tracker.domain.criteria.QuerySearchCriteria;
import com.esofthead.mycollab.module.tracker.service.QueryService;

@Service
public class QueryServiceImpl extends DefaultService<Integer, Query, QuerySearchCriteria>
		implements QueryService {

	@Autowired
	private QueryMapper queryMapper;
	
	@Autowired
	private QueryMapperExt queryMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Query> getCrudMapper() {
		return queryMapper;
	}

	@Override
	public ISearchableDAO<QuerySearchCriteria> getSearchMapper() {
		return queryMapperExt;
	}

}
