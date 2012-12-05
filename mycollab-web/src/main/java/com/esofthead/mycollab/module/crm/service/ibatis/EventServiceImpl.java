package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultSearchService;
import com.esofthead.mycollab.module.crm.dao.EventMapperExt;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.service.EventService;

@Service
public class EventServiceImpl extends DefaultSearchService<EventSearchCriteria>
		implements EventService {

	@Autowired
	protected EventMapperExt eventMapperExt;

	@Override
	public ISearchableDAO<EventSearchCriteria> getSearchMapper() {
		return eventMapperExt;
	}

}
