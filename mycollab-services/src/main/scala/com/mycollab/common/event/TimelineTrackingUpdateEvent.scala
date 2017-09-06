package com.mycollab.common.event

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
class TimelineTrackingUpdateEvent(val typevar: String, val typeId: Integer, val fieldgroup: String, val fieldVal: String,
                                  val extratypeid: Integer, val accountId: Integer) {
    
}
