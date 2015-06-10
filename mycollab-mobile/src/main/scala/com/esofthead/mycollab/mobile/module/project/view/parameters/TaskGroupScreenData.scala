/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
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
