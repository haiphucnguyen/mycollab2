package com.esofthead.mycollab.module.file.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent
import com.esofthead.mycollab.module.ecm.domain.{ExternalDrive, Resource}

/**
  * @author MyCollab Ltd
  * @since 5.1.1
  */
object FileEvent {

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class ResourceRemovedEvent(source: AnyRef, data: Resource) extends ApplicationEvent(source, data) {}

  class ExternalDriveConnectedEvent(source: AnyRef, data: ExternalDrive) extends ApplicationEvent(source, data) {}

  class ExternalDriveDeleteEvent(source: AnyRef, data: ExternalDrive) extends ApplicationEvent(source, data) {}

}
