package com.esofthead.mycollab.community.module.project.view.task;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.task.TaskPopupFieldFactory;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.form.field.PopupBeanField;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.PopupView;
import org.vaadin.teemu.VaadinIcons;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@ViewComponent
public class TaskPopupFieldFactoryImpl implements TaskPopupFieldFactory {
    @Override
    public PopupView createTaskStatusPopupField(SimpleTask task) {
        return new PopupBeanField(FontAwesome.INFO_CIRCLE.getHtml() + " " + task.getStatus());
    }

    @Override
    public PopupView createTaskPercentagePopupField(SimpleTask task) {
        return new PopupBeanField(VaadinIcons.CALENDAR_CLOCK.getHtml() + " " + String.format(" %s%%",
                task.getPercentagecomplete()));
    }

    @Override
    public PopupView createTaskMilestonePopupField(SimpleTask task) {
        return new PopupBeanField(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml() +
                " " + task.getMilestoneName());
    }

    @Override
    public PopupView createTaskDeadlinePopupField(SimpleTask task) {
        return new PopupBeanField(String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(),
                AppContext.formatPrettyTime(task.getDeadlineRoundPlusOne())));
    }
}
