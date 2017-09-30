package com.mycollab.mobile.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object ProjectEvent {
    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoProjectList(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoMyProject(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class MyProjectActivities(source: Any, data: Int) : ApplicationEvent(source, data) 

    class GotoAllActivitiesView(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoDashboard(source: Any, data: Any?) : ApplicationEvent(source, data) 
}