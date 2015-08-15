package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.PopupView;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public interface TaskPopupFieldFactory extends CacheableComponent {
    PopupView createTaskStatusPopupField(SimpleTask task);

    PopupView createTaskMilestonePopupField(SimpleTask task);

    PopupView createTaskPercentagePopupField(SimpleTask task);

    PopupView createTaskDeadlinePopupField(SimpleTask task);
}
