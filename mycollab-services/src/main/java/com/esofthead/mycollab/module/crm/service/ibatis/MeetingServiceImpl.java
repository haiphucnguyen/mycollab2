package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.interceptor.aspect.Watchable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.dao.MeetingMapper;
import com.esofthead.mycollab.module.crm.dao.MeetingMapperExt;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.schedule.email.crm.MeetingRelayEmailNotificationAction;

@Service
@Transactional
@Traceable(module = "Crm", type = "Meeting", nameField = "subject")
@Auditable(module = "Crm", type = "Meeting")
@Watchable(type = CrmTypeConstants.MEETING, userFieldName = "createduser", emailHandlerBean = MeetingRelayEmailNotificationAction.class)
public class MeetingServiceImpl extends
		DefaultService<Integer, Meeting, MeetingSearchCriteria> implements
		MeetingService {

	@Autowired
	protected MeetingMapper meetingMapper;
	@Autowired
	protected MeetingMapperExt meetingMapperExt;

	@SuppressWarnings("unchecked")
	@Override
	public ICrudGenericDAO<Integer, Meeting> getCrudMapper() {
		return meetingMapper;
	}

	@Override
	public SimpleMeeting findById(int meetingId, int sAccountId) {
		return meetingMapperExt.findById(meetingId);
	}

	@Override
	public ISearchableDAO<MeetingSearchCriteria> getSearchMapper() {
		return meetingMapperExt;
	}

	@Override
	public int saveWithSession(Meeting record, String username) {
		int result = super.saveWithSession(record, username);
		CacheUtils.cleanCaches(record.getSaccountid(), EventService.class);
		return result;
	}

	@Override
	public int updateWithSession(Meeting record, String username) {
		int result = super.updateWithSession(record, username);
		CacheUtils.cleanCaches(record.getSaccountid(), EventService.class);
		return result;
	}

	@Override
	public int removeWithSession(Integer primaryKey, String username,
			int accountId) {
		int result = super.removeWithSession(primaryKey, username, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
		return result;
	}

	@Override
	public void removeByCriteria(MeetingSearchCriteria criteria, int accountId) {
		super.removeByCriteria(criteria, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void massRemoveWithSession(List<Integer> primaryKeys,
			String username, int accountId) {
		super.massRemoveWithSession(primaryKeys, username, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void massUpdateWithSession(Meeting record,
			List<Integer> primaryKeys, int accountId) {
		super.massUpdateWithSession(record, primaryKeys, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void updateBySearchCriteria(Meeting record,
			MeetingSearchCriteria searchCriteria) {
		super.updateBySearchCriteria(record, searchCriteria);
		CacheUtils.cleanCaches((Integer) searchCriteria.getSaccountid()
				.getValue(), EventService.class);
	}
}
