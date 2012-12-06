package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.MeetingMapper;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.service.MeetingService;

@Service
public class MeetingServiceImpl extends DefaultCrudService<Integer, Meeting>
		implements MeetingService {

	@Autowired
	protected MeetingMapper meetingMapper;

	@SuppressWarnings("unchecked")
	@Override
	public ICrudGenericDAO<Integer, Meeting> getCrudMapper() {
		return meetingMapper;
	}

}
