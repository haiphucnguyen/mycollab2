package com.mycollab.module.project.view.service;

import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.AbstractComponent;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public interface TaskComponentFactory {

    AbstractComponent createAssigneePopupField(SimpleTask task);

    AbstractComponent createPriorityPopupField(SimpleTask task);

    AbstractComponent createCommentsPopupField(SimpleTask task);

    AbstractComponent createStatusPopupField(SimpleTask task);

    AbstractComponent createMilestonePopupField(SimpleTask task);

    AbstractComponent createPercentagePopupField(SimpleTask task);

    AbstractComponent createDeadlinePopupField(SimpleTask task);

    AbstractComponent createStartDatePopupField(SimpleTask task);

    AbstractComponent createEndDatePopupField(SimpleTask task);

    AbstractComponent createBillableHoursPopupField(SimpleTask task);

    AbstractComponent createNonBillableHoursPopupField(SimpleTask task);

    AbstractComponent createFollowersPopupField(SimpleTask task);
}
