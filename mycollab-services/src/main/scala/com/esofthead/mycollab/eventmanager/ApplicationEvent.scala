package com.esofthead.mycollab.eventmanager

import java.util.EventObject

/**
 * Serves as a parent for all application level events. It holds the source that
 * triggered the event and enforces each event implementation to provide an
 * appropriate description for the event.
 *
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
class ApplicationEvent(source: AnyRef, data: AnyRef) extends EventObject(source) {

  def getData: AnyRef = {
    return data
  }
}
