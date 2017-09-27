package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ReportEvent {
    class GotoConsole(source: Any) : ApplicationEvent(source, null) 

    class GotoTimesheetReport(source: Any) : ApplicationEvent(source, null) 

    class GotoWeeklyTimingReport(source: Any) : ApplicationEvent(source, null) 

    class GotoUserWorkloadReport(source: Any) : ApplicationEvent(source, null) 
}