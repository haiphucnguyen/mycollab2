package com.esofthead.mycollab.common.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.ActivityStreamMapper;
import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;

@Service
public class ActivityStreamServiceImpl extends
		DefaultService<Integer, ActivityStream, ActivityStreamSearchCriteria>
		implements ActivityStreamService {

	@Autowired
	protected ActivityStreamMapper activityStreamMapper;

	@Override
	public ICrudGenericDAO<Integer, ActivityStream> getCrudMapper() {
		return activityStreamMapper;
	}

	@Override
	public ISearchableDAO<ActivityStreamSearchCriteria> getSearchMapper() {
		return null;
	}

	@Override
	public void saveActivityStream(int sAccountId, String module, String type,
			int typeid, String action, String createdUser) {
		ActivityStream activity = new ActivityStream();
		activity.setSaccountid(sAccountId);
		activity.setModule(module);
		activity.setType(type);
		activity.setTypeid(typeid);
		activity.setAction(action);
		activity.setCreateduser(createdUser);
		
		this.saveWithSession(activity, createdUser);
	}

}
