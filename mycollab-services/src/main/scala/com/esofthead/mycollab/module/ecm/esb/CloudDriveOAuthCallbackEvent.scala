package com.esofthead.mycollab.module.ecm.esb

import com.esofthead.mycollab.eventmanager.ApplicationEvent
import com.esofthead.mycollab.module.file.CloudDriveInfo

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