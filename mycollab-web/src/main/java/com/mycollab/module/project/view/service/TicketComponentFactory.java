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
package com.mycollab.module.project.view.service;

import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.AbstractComponent;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
public interface TicketComponentFactory {

    AbstractComponent createStartDatePopupField(ProjectTicket assignment);

    AbstractComponent createEndDatePopupField(ProjectTicket assignment);

    AbstractComponent createDueDatePopupField(ProjectTicket assignment);

    AbstractComponent createPriorityPopupField(ProjectTicket task);

    AbstractComponent createBillableHoursPopupField(ProjectTicket task);

    AbstractComponent createNonBillableHoursPopupField(ProjectTicket task);

    AbstractComponent createFollowersPopupField(ProjectTicket task);

    AbstractComponent createCommentsPopupField(ProjectTicket task);

    AbstractComponent createStatusPopupField(ProjectTicket task);

    MWindow createNewTicketWindow(Date date, final Integer prjId, final Integer milestoneId, boolean isIncludeMilestone);
}
