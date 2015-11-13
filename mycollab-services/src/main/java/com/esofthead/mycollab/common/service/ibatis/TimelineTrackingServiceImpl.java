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
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
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

    @Autowired
    private DataSource dataSource;

    @Override
    public ICrudGenericDAO<Integer, TimelineTracking> getCrudMapper() {
        return timelineTrackingMapper;
    }

    @Override
    public Map<String, List<GroupItem>> findTimelineItems(List<String> groupVals, Date start, Date end,
                                                          TimelineTrackingSearchCriteria criteria) {
        DateTime startDate = new DateTime(start);
        DateTime endDate = new DateTime(end);
        if (startDate.isAfter(endDate)) {
            throw new UserInvalidInputException("Start date must be greater than end date");
        }
        List<Date> dates = boundDays(startDate, endDate);

        Map<String, List<GroupItem>> items = new HashMap<>();
//        List<Map> cacheTimelineItems = timelineTrackingCachingMapperExt.findTimelineItems(groupVals, dates, criteria);

//        LocalDate calculatedDate = new LocalDate(startDate);
//        if (cacheTimelineItems.size() > 0) {
//            Map value = cacheTimelineItems.get(cacheTimelineItems.size() - 1);
//            String dateValue = (String) value.get("groupname");
//
//        }

//        dates = boundDays(calculatedDate, endDate);
        if (dates.size() > 0) {
            final List<Map> timelineItems = timelineTrackingMapperExt.findTimelineItems(groupVals, dates, criteria);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//            jdbcTemplate.batchUpdate("INSERT INTO `s_timeline_tracking_cache`(type, fieldval,extratypeid,sAccountId," +
//                    "forDay, fieldgroup,count) VALUES(?,?,?,?,?,?,?)", new BatchPreparedStatementSetter() {
//                @Override
//                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//                    Map item = timelineItems.get(i);
//
//                }
//
//                @Override
//                public int getBatchSize() {
//                    return timelineItems.size();
//                }
//            });
            for (Map map : timelineItems) {
                String groupVal = (String) map.get("groupid");
                GroupItem item = new GroupItem();
                item.setValue(((BigDecimal) map.get("value")).intValue());
                item.setGroupid((String) map.get("groupid"));
                item.setGroupname((String) map.get("groupname"));
                Object obj = items.get(groupVal);
                if (obj == null) {
                    List<GroupItem> itemLst = new ArrayList<>();
                    itemLst.add(item);
                    items.put(groupVal, itemLst);
                } else {
                    List<GroupItem> itemLst = (List<GroupItem>) obj;
                    itemLst.add(item);
                }
            }
        }

        return items;
    }

    private List<Date> boundDays(DateTime start, DateTime end) {
        Duration duration = new Duration(start, end);
        long days = duration.getStandardDays();
        List<Date> dates = new ArrayList<>();
        //Will try to get from cache values from the end date to (startdate - 1)
        for (int i = 0; i <= days; i++) {
            dates.add(start.plusDays(i).toDate());
        }
        return dates;
    }
}
