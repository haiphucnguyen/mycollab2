package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.MeetingMapper;
import com.esofthead.mycollab.module.crm.dao.MeetingMapperExt;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.service.MeetingService;

@Service
@Transactional
@Traceable(module = "Crm", type = "Meeting", nameField = "subject")
@Auditable(module = "Crm", type = "Meeting")
public class MeetingServiceImpl extends DefaultService<Integer, Meeting, MeetingSearchCriteria>
        implements MeetingService {

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
    public SimpleMeeting findById(int meetingId) {
        return meetingMapperExt.findMeetingById(meetingId);
    }

    @Override
    public ISearchableDAO<MeetingSearchCriteria> getSearchMapper() {
        return meetingMapperExt;
    }
}
