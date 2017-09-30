package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.Project
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object ProjectScreenData {
    class GotoList : ScreenData<*>(null)

    class Goto(params: Int) : ScreenData<Int>(params)

    class Edit(params: Project) : ScreenData<Project>(params)

    class GotoTagList(params: Any) : ScreenData<*>(params)

    class GotoFavorite() : ScreenData<*>(null)

    class SearchItem(params: String) : ScreenData<String>(params)

    class GotoGanttChart : ScreenData<*>(null)

    class GotoReportConsole : ScreenData<*>(null)

    class GotoCalendarView : ScreenData<*>(null)
}