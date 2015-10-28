package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.dao.TimelineTrackingMapper;
import com.esofthead.mycollab.common.domain.TimelineTracking;
import com.esofthead.mycollab.common.service.TimelineTrackingService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class TimelineTrackingServiceImpl extends DefaultCrudService<Integer, TimelineTracking> implements TimelineTrackingService {

    @Autowired
    private TimelineTrackingMapper timelineTrackingMapper;

    @Override
    public ICrudGenericDAO<Integer, TimelineTracking> getCrudMapper() {
        return timelineTrackingMapper;
    }
}
