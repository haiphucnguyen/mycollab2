package com.mycollab.module.project.view.service;

import com.mycollab.module.project.domain.ProjectTicket;
import com.vaadin.ui.AbstractComponent;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
public interface TicketComponentFactory {

    AbstractComponent createStartDatePopupField(ProjectTicket ticket);

    AbstractComponent createEndDatePopupField(ProjectTicket ticket);

    AbstractComponent createDueDatePopupField(ProjectTicket ticket);

    AbstractComponent createPriorityPopupField(ProjectTicket ticket);

    AbstractComponent createAssigneePopupField(ProjectTicket ticket);

    AbstractComponent createBillableHoursPopupField(ProjectTicket ticket);

    AbstractComponent createNonBillableHoursPopupField(ProjectTicket ticket);

    AbstractComponent createFollowersPopupField(ProjectTicket ticket);

    AbstractComponent createCommentsPopupField(ProjectTicket ticket);

    AbstractComponent createStatusPopupField(ProjectTicket ticket);

    MWindow createNewTicketWindow(Date date, final Integer prjId, final Integer milestoneId, boolean isIncludeMilestone);
}
