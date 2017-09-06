package com.mycollab.module.project.view.service;

import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.AbstractComponent;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public interface BugComponentFactory {
    AbstractComponent createPriorityPopupField(SimpleBug bug);

    AbstractComponent createAssigneePopupField(SimpleBug bug);

    AbstractComponent createCommentsPopupField(SimpleBug bug);

    AbstractComponent createStatusPopupField(SimpleBug bug);

    AbstractComponent createMilestonePopupField(SimpleBug bug);

    AbstractComponent createDeadlinePopupField(SimpleBug bug);

    AbstractComponent createStartDatePopupField(SimpleBug bug);

    AbstractComponent createEndDatePopupField(SimpleBug bug);

    AbstractComponent createBillableHoursPopupField(SimpleBug bug);

    AbstractComponent createNonbillableHoursPopupField(SimpleBug bug);

    AbstractComponent createFollowersPopupField(SimpleBug bug);
}
