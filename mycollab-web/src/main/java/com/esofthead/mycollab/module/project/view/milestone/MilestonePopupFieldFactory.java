package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.PopupView;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public interface MilestonePopupFieldFactory extends CacheableComponent {
    PopupView createMilestoneAssigneePopupField(SimpleMilestone milestone);

    PopupView createStartDatePopupField(SimpleMilestone milestone);

    PopupView createEndDatePopupField(SimpleMilestone milestone);

    PopupView createBillableHoursPopupField(SimpleMilestone milestone);

    PopupView createNonBillableHoursPopupField(SimpleMilestone milestone);
}
