package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.dao.ItemTimeLoggingMapper;
import com.esofthead.mycollab.module.project.dao.ItemTimeLoggingMapperExt;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;

public class ItemTimeLoggingServiceImpl extends
		DefaultService<Integer, ItemTimeLogging, ItemTimeLoggingSearchCriteria>
		implements ItemTimeLoggingService {

	@Autowired
	private ItemTimeLoggingMapper itemTimeMapper;

	@Autowired
	private ItemTimeLoggingMapperExt itemTimeMapperExt;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return itemTimeMapper;
	}

	@Override
	public ISearchableDAO<ItemTimeLoggingSearchCriteria> getSearchMapper() {
		return itemTimeMapperExt;
	}

}
