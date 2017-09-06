package com.mycollab.module.common.esb

import java.util.concurrent.TimeUnit

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.dao.TimelineTrackingMapper
import com.mycollab.common.domain.{TimelineTracking, TimelineTrackingExample}
import com.mycollab.common.event.TimelineTrackingAdjustIfEntityDeleteEvent
import com.mycollab.lock.DistributionLockUtil
import com.mycollab.module.esb.GenericCommand
import org.joda.time.LocalDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
@Component class TimelineAdjustWithDeleteEntityCommand extends GenericCommand {
  @Autowired var timelineMapper: TimelineTrackingMapper = _

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: TimelineTrackingAdjustIfEntityDeleteEvent): Unit = {
//    val lock = DistributionLockUtil.getLock("timeline-" + event.accountId)
//    try {
//      if (lock.tryLock(120, TimeUnit.SECONDS)) {
//        for (groupVal <- event.groupVals) {
//          val ex = new TimelineTrackingExample
//          ex.setOrderByClause("forDay DESC, id DESC")
//          val criteria = ex.createCriteria()
//          criteria.andSaccountidEqualTo(event.accountId).andTypeidEqualTo(event.typeId).
//            andFieldgroupEqualTo(groupVal).andExtratypeidEqualTo(event.extratypeid).andTypeEqualTo(event.typevar)
//          val items = timelineMapper.selectByExample(ex)
//          if (items.size() > 0) {
//            val item = items.get(0)
//            val minusTimeline = new TimelineTracking
//            minusTimeline.setType(event.typevar)
//            minusTimeline.setTypeid(event.typeId)
//            minusTimeline.setFieldgroup(groupVal)
//            minusTimeline.setFieldval(item.getFieldval)
//            minusTimeline.setExtratypeid(event.extratypeid)
//            minusTimeline.setSaccountid(event.accountId)
//            minusTimeline.setForday(new LocalDate().toDate)
//            minusTimeline.setFlag(Byte.box(-1))
//            timelineMapper.insert(minusTimeline)
//          }
//        }
//      }
//    } finally {
//      DistributionLockUtil.removeLock("timeline-" + event.accountId)
//      lock.unlock()
//    }
  }
}
