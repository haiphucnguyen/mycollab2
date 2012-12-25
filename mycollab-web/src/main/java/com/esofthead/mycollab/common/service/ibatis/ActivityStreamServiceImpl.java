package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.dao.ActivityStreamMapper;
import com.esofthead.mycollab.common.dao.ActivityStreamMapperExt;
import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityStreamServiceImpl extends
		DefaultService<Integer, ActivityStream, ActivityStreamSearchCriteria>
		implements ActivityStreamService {

	@Autowired
	protected ActivityStreamMapper activityStreamMapper;
        
        @Autowired
        protected ActivityStreamMapperExt activityStreamMapperExt;

	@Override
	public ICrudGenericDAO<Integer, ActivityStream> getCrudMapper() {
		return activityStreamMapper;
	}

	@Override
	public ISearchableDAO<ActivityStreamSearchCriteria> getSearchMapper() {
		return activityStreamMapperExt;
	}

	@Override
	public void save(ActivityStream activityStream) {
		activityStreamMapper.insert(activityStream);
	}
}
