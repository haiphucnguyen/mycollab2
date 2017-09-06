package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.crm.domain.SimpleMeeting;
import com.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface MeetingMapperExt extends ISearchableDAO<MeetingSearchCriteria> {

    SimpleMeeting findById(Integer meetingId);
}
