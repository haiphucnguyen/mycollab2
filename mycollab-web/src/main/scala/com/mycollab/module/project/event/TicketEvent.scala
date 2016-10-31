/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
  * This file is part of mycollab-web.
  *
  * mycollab-web is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * mycollab-web is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
  */
package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.4.3
  */
object TicketEvent {
  
  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
  
  class NewTicketAdded(source: AnyRef, @BeanProperty val typeVal: String, @BeanProperty val typeIdVal: Integer) extends
    ApplicationEvent(source, null) {}
  
  class SearchRequest(source: AnyRef, data: ProjectTicketSearchCriteria) extends ApplicationEvent(source, data) {}
  
  class HasTicketPropertyChanged(source: AnyRef, data: String) extends ApplicationEvent(source, data) {}
  
}
