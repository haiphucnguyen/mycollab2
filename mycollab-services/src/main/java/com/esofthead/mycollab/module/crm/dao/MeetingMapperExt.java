package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;

public interface MeetingMapperExt extends ISearchableDAO<MeetingSearchCriteria> {

    SimpleMeeting findById(int meetingId);
}
