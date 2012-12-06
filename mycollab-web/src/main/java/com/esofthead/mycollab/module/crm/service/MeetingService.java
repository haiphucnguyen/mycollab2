package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;

public interface MeetingService extends ICrudService<Integer, Meeting> {
	SimpleMeeting findMeetingById(int meetingId);
}
