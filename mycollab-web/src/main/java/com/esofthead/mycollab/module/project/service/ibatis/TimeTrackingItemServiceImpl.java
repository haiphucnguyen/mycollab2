package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.domain.TimeTrackingItem;
import com.esofthead.mycollab.module.project.domain.criteria.TimeTrackingItemSearchCriteria;
import com.esofthead.mycollab.module.project.service.TimeTrackingItemService;

@Service
public class TimeTrackingItemServiceImpl
		extends
		DefaultService<Integer, TimeTrackingItem, TimeTrackingItemSearchCriteria>
		implements TimeTrackingItemService {

	@Override
	public ICrudGenericDAO getCrudMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISearchableDAO<TimeTrackingItemSearchCriteria> getSearchMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
