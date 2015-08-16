package com.esofthead.mycollab.premium.module.project.view.task;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.TaskCompleteStatusSelection;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.task.TaskPopupFieldFactory;
import com.esofthead.mycollab.module.project.view.task.TaskStatusComboBox;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.form.field.PopupBeanFieldBuilder;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PopupView;
import org.vaadin.teemu.VaadinIcons;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@ViewComponent
public class TaskPopupFieldFactoryImpl implements TaskPopupFieldFactory {
    @Override
    public PopupView createTaskStatusPopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return FontAwesome.INFO_CIRCLE.getHtml() + " " + task.getStatus();
            }
        };
        builder.withBean(task).withBindProperty("status").withCaption(AppContext.getMessage(TaskI18nEnum.FORM_STATUS))
                .withField(new TaskStatusComboBox()).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class))
                .withValue(task.getStatus());
        return builder.build();
    }

    @Override
    public PopupView createTaskMilestonePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml() + " " + task.getMilestoneName();
            }
        };
        builder.withBean(task).withBindProperty("milestoneid").withCaption(AppContext.getMessage(TaskI18nEnum.FORM_MILESTONE))
                .withField(new MilestoneComboBox()).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class))
                .withValue(task.getMilestoneid());
        return builder.build();
    }

    @Override
    public PopupView createTaskPercentagePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return VaadinIcons.CALENDAR_CLOCK.getHtml() + " " + String.format(" %s%%", value);
            }
        };
        builder.withBean(task).withBindProperty("percentagecomplete")
                .withCaption(AppContext.getMessage(TaskI18nEnum.FORM_PERCENTAGE_COMPLETE)).withField(new TaskCompleteStatusSelection())
                .withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getPercentagecomplete());
        return builder.build();
    }

    @Override
    public PopupView createTaskDeadlinePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(), AppContext.formatPrettyTime((Date) value));
            }
        };
        builder.withBean(task).withBindProperty("deadline").withCaption(AppContext.getMessage(TaskI18nEnum.FORM_DEADLINE))
                .withField(new DateField()).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class))
                .withValue(task.getDeadline());
        return builder.build();
    }
}
