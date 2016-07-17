package com.mycollab.ondemand.module.file.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.file.CloudDriveInfo

/**
  *
  * @author MyCollab Ltd.
  * @since 1.0
  *
  */
object CloudDriveOAuthCallbackEvent {
  
  @SerialVersionUID(1L)
  class ReceiveCloudDriveInfo(source: AnyRef, data: CloudDriveInfo) extends ApplicationEvent(source, data) {}
  
}