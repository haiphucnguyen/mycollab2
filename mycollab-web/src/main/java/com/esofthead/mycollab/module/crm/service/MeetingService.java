package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;

public interface MeetingService extends IDefaultService<Integer, Meeting, MeetingSearchCriteria> {

    SimpleMeeting findById(int meetingId);
}
