/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.dao.TimelineTrackingMapper;
import com.esofthead.mycollab.common.dao.TimelineTrackingMapperExt;
import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.TimelineTracking;
import com.esofthead.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.esofthead.mycollab.common.service.TimelineTrackingService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Service
public class TimelineTrackingServiceImpl extends DefaultCrudService<Integer, TimelineTracking> implements TimelineTrackingService {

    @Autowired
    private TimelineTrackingMapper timelineTrackingMapper;

    @Autowired
    private TimelineTrackingMapperExt timelineTrackingMapperExt;

    @Override
    public ICrudGenericDAO<Integer, TimelineTracking> getCrudMapper() {
        return timelineTrackingMapper;
    }

    @Override
    public List<GroupItem> findTimelineItems(List<String> groupVals, TimelineTrackingSearchCriteria criteria) {
        return timelineTrackingMapperExt.findTimelineItems(groupVals, criteria);
    }
}
