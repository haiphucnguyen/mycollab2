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

import com.esofthead.mycollab.common.dao.TimelineTrackingCachingMapperExt;
import com.esofthead.mycollab.common.dao.TimelineTrackingMapper;
import com.esofthead.mycollab.common.dao.TimelineTrackingMapperExt;
import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.TimelineTracking;
import com.esofthead.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.esofthead.mycollab.common.service.TimelineTrackingService;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private TimelineTrackingCachingMapperExt timelineTrackingCachingMapperExt;

    @Override
    public ICrudGenericDAO<Integer, TimelineTracking> getCrudMapper() {
        return timelineTrackingMapper;
    }

    @Override
    public Map<String, List<GroupItem>> findTimelineItems(List<String> groupVals, Date start, Date end, TimelineTrackingSearchCriteria criteria) {
        DateTime startDate = new DateTime(start);
        DateTime endDate = new DateTime(end);
        if (startDate.isAfter(endDate)) {
            throw new UserInvalidInputException("Start date must be greater than end date");
        }
        Duration period = new Duration(startDate, endDate);
        List<Date> dates = new ArrayList<>();
        long days = period.getStandardDays();

        //Will try to get from cache values from the end date to (startdate - 1)
        for (int i = 0; i <= days-1; i++) {
            dates.add(startDate.plusDays(i).toDate());
        }


        Map<String, List<GroupItem>> items = new HashMap<>();
        for (String groupVal : groupVals) {
            List<GroupItem> timelineItems = timelineTrackingMapperExt.findTimelineItems(groupVal, dates, criteria);
            items.put(groupVal, timelineItems);
        }

        return items;
    }
}
