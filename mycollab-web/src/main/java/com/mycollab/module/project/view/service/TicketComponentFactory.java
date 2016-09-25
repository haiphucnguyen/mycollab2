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
