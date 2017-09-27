package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object BugEvent {
    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data)

    class SearchRequest(source: Any, data: BugSearchCriteria) : ApplicationEvent(source, data)

    class BugChanged(source: Any, data: Int) : ApplicationEvent(source, data)
}