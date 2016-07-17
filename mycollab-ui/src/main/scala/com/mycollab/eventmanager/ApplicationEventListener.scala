package com.mycollab.eventmanager

import java.util.EventListener

import com.mycollab.events.ApplicationEvent

/**
  * A listener that listens and is able to handle {@link ApplicationEvent
 * application events}.
  *
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
trait ApplicationEventListener[E <: ApplicationEvent] extends EventListener with Serializable {
  /**
    * Handles the given application event.
    *
    * @param event
    * The event to handle.
    */
  def handle(event: E)
}
