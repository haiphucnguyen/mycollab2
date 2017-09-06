package com.mycollab.events

import java.util.EventObject

import scala.beans.BeanProperty

/**
  * Serves as a parent for all application level event. It holds the source that
  * triggered the event and enforces each event implementation to provide an
  * appropriate description for the event.
  *
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
class ApplicationEvent(source: AnyRef, @BeanProperty val data: Any) extends EventObject(source) {}
