package com.mycollab.events

import java.util.*

/**
 * Serves as a parent for all application level event. It holds the source that
 * triggered the event and enforces each event implementation to provide an
 * appropriate description for the event.
 *
 * @author MyCollab Ltd
 * @since 6.0.0
 */
open class ApplicationEvent(source: Any, val data: Any?) : EventObject(source)