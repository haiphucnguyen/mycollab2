package com.esofthead.mycollab.module.common.esb.impl

import java.util.concurrent.TimeUnit

import com.esofthead.mycollab.common.dao.TimelineTrackingMapper
import com.esofthead.mycollab.common.domain.TimelineTrackingExample
import com.esofthead.mycollab.common.event.TimelineTrackingAdjustIfEntityDeleteEvent
import com.esofthead.mycollab.lock.DistributionLockUtil
import com.esofthead.mycollab.module.GenericCommand
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
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
    val lock = DistributionLockUtil.getLock("timeline-" + event.accountId)
    try {
      if (lock.tryLock(120, TimeUnit.SECONDS)) {
        val ex = new TimelineTrackingExample
        val criteria = ex.createCriteria()
        val now = new LocalDate()
        ex.setOrderByClause("forDay DESC, index DESC")
for (var fieldType <- event.fieldVals) {

}
          criteria.andSaccountidEqualTo(event.accountId).andTypeidEqualTo(event.typeId)
      }
    } finally {
      DistributionLockUtil.removeLock("timeline-" + event.accountId)
      lock.unlock
    }
  }
}
