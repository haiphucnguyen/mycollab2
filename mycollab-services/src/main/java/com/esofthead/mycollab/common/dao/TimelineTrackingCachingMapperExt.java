package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public interface TimelineTrackingCachingMapperExt {
    List<GroupItem> findTimelineItems(@Param("groupVal") String groupVal, @Param("dates") List<Date> dates,
                                      @Param("searchCriteria") TimelineTrackingSearchCriteria criteria);
}
