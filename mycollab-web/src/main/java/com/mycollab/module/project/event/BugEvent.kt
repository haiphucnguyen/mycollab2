package com.mycollab.module.project.event

import com.mycollab.vaadin.event.ApplicationEvent
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object BugEvent {
    class GotoAdd(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoEdit(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoRead(source: Any, val data: Any?) : ApplicationEvent(source)

    class SearchRequest(source: Any, data: BugSearchCriteria) : ApplicationEvent(source)

    class BugChanged(source: Any, data: Int) : ApplicationEvent(source)
}