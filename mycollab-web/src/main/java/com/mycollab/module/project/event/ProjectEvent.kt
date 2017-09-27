package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.ProjectGenericItem

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ProjectEvent {
    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoMyProject(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoTagListView(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoFavoriteView(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class SelectFavoriteItem(source: Any, data: ProjectGenericItem) : ApplicationEvent(source, data)

    class TimeLoggingChangedEvent(source: Any) : ApplicationEvent(source, null) 

    class GotoCalendarView(source: Any) : ApplicationEvent(source, null) 

    class GotoGanttChart(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoDashboard(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoUserDashboard(source: Any, data: Any?) : ApplicationEvent(source, data) 
}