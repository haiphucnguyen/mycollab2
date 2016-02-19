package com.esofthead.mycollab.common.event

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
class TimelineTrackingAdjustIfEntityDeleteEvent(val typevar: String, val typeId: Integer, val fieldVals: Array[String],
                                                val extratypeid: Integer, val accountId: Integer) {
  
}
