package com.esofthead.mycollab.mobile.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.SimpleTaskList
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object TaskGroupScreenData {

    class List(param: TaskListSearchCriteria) extends ScreenData[TaskListSearchCriteria](param) {
        def this() = this(null)
    }

    class Add(param: SimpleTaskList) extends ScreenData[SimpleTaskList](param) {}

    class Read(param: Integer) extends ScreenData[Integer](param) {}

    class Edit(param: SimpleTaskList) extends ScreenData[SimpleTaskList](param) {}

}
