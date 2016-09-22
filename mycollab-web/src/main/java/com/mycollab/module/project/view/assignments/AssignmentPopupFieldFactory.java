package com.mycollab.module.project.view.assignments;

import com.mycollab.module.project.domain.ProjectAssignment;
import com.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.AbstractComponent;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
public interface AssignmentPopupFieldFactory extends CacheableComponent {

    AbstractComponent createStartDatePopupField(ProjectAssignment assignment);

    AbstractComponent createEndDatePopupField(ProjectAssignment assignment);

    AbstractComponent createDueDatePopupField(ProjectAssignment assignment);

    AbstractComponent createPriorityPopupField(ProjectAssignment task);

    AbstractComponent createBillableHoursPopupField(ProjectAssignment task);

    AbstractComponent createNonBillableHoursPopupField(ProjectAssignment task);

    AbstractComponent createFollowersPopupField(ProjectAssignment task);

    AbstractComponent createCommentsPopupField(ProjectAssignment task);

    AbstractComponent createStatusPopupField(ProjectAssignment task);
}
