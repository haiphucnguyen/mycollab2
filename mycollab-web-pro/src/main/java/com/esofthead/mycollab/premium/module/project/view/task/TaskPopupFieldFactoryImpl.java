package com.esofthead.mycollab.premium.module.project.view.task;

import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.HumanTime;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.ui.components.TaskCompleteStatusSelection;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.module.project.view.task.TaskPopupFieldFactory;
import com.esofthead.mycollab.module.project.view.task.TaskPriorityComboBox;
import com.esofthead.mycollab.module.project.view.task.TaskStatusComboBox;
import com.esofthead.mycollab.schedule.email.project.ProjectTaskRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.LazyPopupView;
import com.esofthead.mycollab.vaadin.ui.form.field.PopupBeanFieldBuilder;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@ViewComponent
public class TaskPopupFieldFactoryImpl implements TaskPopupFieldFactory {

    @Override
    public PopupView createTaskAssigneePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                String avatarLink = StorageFactory.getInstance().getAvatarPath(task.getAssignUserAvatarId(), 16);
                Img img = new Img(task.getAssignUserFullName(), avatarLink).setTitle(task.getAssignUserFullName());
                return img.write();
            }

            @Override
            protected String generateSmallAsHtmlAfterUpdate() {
                ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
                SimpleTask newTask = taskService.findById(task.getId(), AppContext.getAccountId());
                String avatarLink = StorageFactory.getInstance().getAvatarPath(newTask.getAssignUserAvatarId(), 16);
                Img img = new Img(newTask.getAssignUserFullName(), avatarLink).setTitle(newTask.getAssignUserFullName());
                return img.write();
            }

            @Override
            protected String generateDescription() {
                return task.getAssignUserFullName();
            }
        };
        builder.withBean(task).withBindProperty("assignuser").withDescription(task.getAssignUserFullName())
                .withCaption(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)).withField(new ProjectMemberSelectionField())
                .withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getAssignuser())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createTaskPriorityPopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getTaskPriorityHtml(task.getPriority());
            }

            @Override
            protected String generateDescription() {
                return task.getPriority();
            }
        };
        builder.withBean(task).withBindProperty("priority").withDescription(task.getPriority())
                .withCaption(AppContext.getMessage(TaskI18nEnum.FORM_PRIORITY)).withField(new TaskPriorityComboBox())
                .withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getPriority())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createTaskStatusPopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (task.getStatus() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(FontAwesome.INFO_CIRCLE.getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return FontAwesome.INFO_CIRCLE.getHtml() + " " + StringUtils.trim(task.getStatus(), 20, true);
                }
            }
        };
        builder.withBean(task).withBindProperty("status").withCaption(AppContext.getMessage(TaskI18nEnum.FORM_STATUS))
                .withField(new TaskStatusComboBox()).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getStatus())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createTaskMilestonePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (task.getMilestoneid() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    String milestoneName = ((MilestoneComboBox) field).getItemCaption(task.getMilestoneid());
                    return ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml() + " " +
                            StringUtils.trim(milestoneName, 20, true);
                }
            }
        };
        MilestoneComboBox milestoneComboBox = new MilestoneComboBox();
        milestoneComboBox.setWidth("300px");
        builder.withBean(task).withBindProperty("milestoneid").withCaption(AppContext.getMessage(TaskI18nEnum.FORM_MILESTONE))
                .withField(milestoneComboBox).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class))
                .withValue(task.getMilestoneid()).withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createTaskPercentagePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (task.getPercentagecomplete() != null && task.getPercentagecomplete() > 0) {
                    return VaadinIcons.CALENDAR_CLOCK.getHtml() + " " + String.format(" %s%%", task.getPercentagecomplete());
                } else {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.CALENDAR_CLOCK.getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                }
            }
        };
        builder.withBean(task).withBindProperty("percentagecomplete")
                .withCaption(AppContext.getMessage(TaskI18nEnum.FORM_PERCENTAGE_COMPLETE)).withField(new TaskCompleteStatusSelection())
                .withDescription(AppContext.getMessage(TaskI18nEnum.FORM_PERCENTAGE_COMPLETE))
                .withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getPercentagecomplete())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createTaskDeadlinePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (task.getDeadlineRoundPlusOne() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(FontAwesome.CLOCK_O.getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(), AppContext.formatPrettyTime(task.getDeadlineRoundPlusOne()));
                }

            }
        };
        builder.withBean(task).withBindProperty("deadline").withCaption(AppContext.getMessage(TaskI18nEnum.FORM_DEADLINE))
                .withField(new DateField()).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getDeadline())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createTaskCommentsPopupField(SimpleTask task) {
        TaskCommentsPopupView view = new TaskCommentsPopupView(task);
        view.setDescription("Click to edit");
        return view;
    }

    @Override
    public PopupView createTaskBillableHoursPopupField(SimpleTask task) {
        TaskBillableHoursPopupField view = new TaskBillableHoursPopupField(task, true);
        view.setDescription("Billable hours");
        return view;
    }

    @Override
    public PopupView createTaskNonBillableHoursPopupField(SimpleTask task) {
        TaskBillableHoursPopupField view = new TaskBillableHoursPopupField(task, false);
        view.setDescription("Non billable hours");
        return view;
    }

    private static class TaskCommentsPopupView extends LazyPopupView {
        private SimpleTask task;

        TaskCommentsPopupView(SimpleTask task) {
            super("");
            this.task = task;
            if (task.getNumComments() == null || task.getNumComments() == 0) {
                this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " 0");
            } else {
                this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + task.getNumComments());
            }
        }

        @Override
        protected void doShow() {
            CommentDisplay commentDisplay = new CommentDisplay(ProjectTypeConstants.TASK, CurrentProjectVariables.getProjectId(),
                    ProjectTaskRelayEmailNotificationAction.class);
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            layout.with(commentDisplay);
            commentDisplay.loadComments(task.getId() + "");
        }

        @Override
        protected void doHide() {
            CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
            searchCriteria.setType(new StringSearchField(ProjectTypeConstants.TASK));
            searchCriteria.setTypeid(new StringSearchField(task.getId() + ""));
            CommentService commentService = ApplicationContextUtil.getSpringBean(CommentService.class);
            int commentCount = commentService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + commentCount);
        }
    }

    private static class TaskBillableHoursPopupField extends LazyPopupView {
        private TextField timeInput = new TextField();
        private SimpleTask task;
        private boolean isBillable;

        TaskBillableHoursPopupField(SimpleTask task, boolean isBillable) {
            super("");
            this.task = task;
            this.isBillable = isBillable;
            this.setMinimizedValueAsHTML(FontAwesome.MONEY.getHtml() + " " + task.getBillableHours());
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            timeInput.setValue("");
            timeInput.setDescription("The format of duration must be [number] y " +
                    "[number] d [number] h [number] m [number] s");
            String title = (isBillable) ? "Add billable hours" : "Add non billable hours";
            Label headerLbl = new Label(title, ContentMode.HTML);
            headerLbl.addStyleName("h2");
            layout.with(headerLbl, timeInput);
        }

        @Override
        protected void doHide() {
            String timeVal = timeInput.getValue();
            Long delta = HumanTime.eval(timeVal).getDelta();
            if (delta > 0) {
                ItemTimeLoggingService timeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);
                Double hours = delta.doubleValue() / (1000 * 60 * 60);
                ItemTimeLogging timeLogging = new ItemTimeLogging();
                timeLogging.setCreateduser(AppContext.getUsername());
                timeLogging.setIsbillable(isBillable);
                timeLogging.setLoguser(AppContext.getUsername());
                timeLogging.setLogvalue(hours);
                timeLogging.setProjectid(CurrentProjectVariables.getProjectId());
                timeLogging.setType(ProjectTypeConstants.TASK);
                timeLogging.setTypeid(task.getId());
                timeLogging.setSaccountid(AppContext.getAccountId());
                timeLoggingService.saveWithSession(timeLogging, AppContext.getUsername());

                // load hours again
                ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
                searchCriteria.setIsBillable(new BooleanSearchField(isBillable));
                searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                searchCriteria.setType(new StringSearchField(ProjectTypeConstants.TASK));
                searchCriteria.setTypeId(new NumberSearchField(task.getId()));
                Double calculatedHours = timeLoggingService.getTotalHoursByCriteria(searchCriteria);
                this.setMinimizedValueAsHTML(FontAwesome.MONEY.getHtml() + " " + calculatedHours);
            }
        }
    }
}
