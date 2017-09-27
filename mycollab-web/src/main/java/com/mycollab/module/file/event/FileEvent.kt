package com.mycollab.module.file.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.ecm.domain.ExternalDrive
import com.mycollab.module.ecm.domain.Resource

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object FileEvent {
    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data)

    class ResourceRemovedEvent(source: Any, data: Resource) : ApplicationEvent(source, data)

    class ExternalDriveConnectedEvent(source: Any, data: ExternalDrive) : ApplicationEvent(source, data)

    class ExternalDriveDeleteEvent(source: Any, data: ExternalDrive) : ApplicationEvent(source, data)
}