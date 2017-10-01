package com.mycollab.ondemand.module.file.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.file.CloudDriveInfo

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CloudDriveOAuthCallbackEvent {
    class ReceiveCloudDriveInfo(source: Any, data: CloudDriveInfo) : ApplicationEvent(source)
}