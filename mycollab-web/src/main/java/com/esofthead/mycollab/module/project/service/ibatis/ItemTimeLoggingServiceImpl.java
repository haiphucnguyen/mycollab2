package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.dao.ItemTimeLoggingMapper;
import com.esofthead.mycollab.module.project.dao.ItemTimeLoggingMapperExt;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;

@Service
public class ItemTimeLoggingServiceImpl extends
		DefaultService<Integer, ItemTimeLogging, ItemTimeLoggingSearchCriteria>
		implements ItemTimeLoggingService {

	@Autowired
	private ItemTimeLoggingMapper itemTimeLoggingMapper;

	@Autowired
	private ItemTimeLoggingMapperExt itemTimeLoggingMapperExt;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return itemTimeLoggingMapper;
	}

	@Override
	public ISearchableDAO<ItemTimeLoggingSearchCriteria> getSearchMapper() {
		return itemTimeLoggingMapperExt;
	}

	@Override
	public Double getTotalHoursByCriteria(ItemTimeLoggingSearchCriteria criteria) {
		return itemTimeLoggingMapperExt.getTotalHoursByCriteria(criteria);
	}

}
