package com.esofthead.mycollab.module.common.esb.impl

import com.esofthead.mycollab.common.dao.TimelineTrackingMapper
import com.esofthead.mycollab.common.domain.{TimelineTracking, TimelineTrackingExample}
import com.esofthead.mycollab.common.event.TimelineTrackingUpdateEvent
import com.esofthead.mycollab.module.GenericCommand
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.joda.time.LocalDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Component class TimelineTrackingCommand extends GenericCommand {
    @Autowired var timelineMapper: TimelineTrackingMapper = _

    @AllowConcurrentEvents
    @Subscribe
    def execute(event: TimelineTrackingUpdateEvent): Unit = {
        val ex = new TimelineTrackingExample
        val criteria = ex.createCriteria();
        val now = new LocalDate()
        criteria.andFordayEqualTo(now.toDate).andTypeEqualTo(event.typevar).andTypeidEqualTo(event.typeId).
            andFieldgroupEqualTo(event.fieldgroup).andSaccountidEqualTo(event.accountId)
        if (event.extratypeid != null) {
            criteria.andExtratypeidEqualTo(event.extratypeid)
        }
        val items = timelineMapper.selectByExample(ex)
        if (items == null || items.size() == 0) {
            val timeline = new TimelineTracking
            timeline.setType(event.typevar)
            timeline.setTypeid(event.typeId)
            timeline.setFieldgroup(event.fieldgroup)
            timeline.setFieldval(event.fieldVal)
            timeline.setExtratypeid(event.extratypeid)
            timeline.setSaccountid(event.accountId)
            timeline.setForday(now.toDate)
            timelineMapper.insert(timeline)
        } else {
            val timeline = items.get(0);
            if (event.fieldVal != timeline.getFieldval) {
                timeline.setFieldval(event.fieldVal)
                timelineMapper.updateByPrimaryKey(timeline)
            }
        }
    }
}
