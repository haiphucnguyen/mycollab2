package com.mycollab.module.project.view.service;

import com.mycollab.module.project.domain.SimpleMilestone;
import com.vaadin.ui.AbstractComponent;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public interface MilestoneComponentFactory {
    AbstractComponent createMilestoneAssigneePopupField(SimpleMilestone milestone, boolean isDisplayName);

    AbstractComponent createStartDatePopupField(SimpleMilestone milestone);

    AbstractComponent createEndDatePopupField(SimpleMilestone milestone);

    AbstractComponent createBillableHoursPopupField(SimpleMilestone milestone);

    AbstractComponent createNonBillableHoursPopupField(SimpleMilestone milestone);
}
