package com.esofthead.mycollab.pro.module.project.view.task;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.HumanTime;
import com.esofthead.mycollab.core.utils.NumberUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.ui.components.TaskCompleteStatusSelection;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.module.project.view.task.TaskPopupFieldFactory;
import com.esofthead.mycollab.module.project.view.task.components.TaskPriorityComboBox;
import com.esofthead.mycollab.module.project.view.task.components.TaskStatusComboBox;
import com.esofthead.mycollab.pro.module.project.ui.components.WatchersMultiSelection;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.LazyPopupView;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.pro.vaadin.web.ui.field.PopupBeanFieldBuilder;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@ViewComponent
public class TaskPopupFieldFactoryImpl implements TaskPopupFieldFactory {

    @Override
    public PopupView createAssigneePopupField(final SimpleTask task) {
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
    public PopupView createPriorityPopupField(final SimpleTask task) {
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
    public PopupView createStatusPopupField(final SimpleTask task) {
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
        builder.withBean(task).withBindProperty("status").withCaption(AppContext.getMessage(GenericI18Enum.FORM_STATUS))
                .withField(new TaskStatusComboBox()).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getStatus())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createMilestonePopupField(final SimpleTask task) {
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
        builder.withBean(task).withBindProperty("milestoneid").withCaption(AppContext.getMessage(TaskI18nEnum.FORM_PHASE))
                .withField(milestoneComboBox).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class))
                .withValue(task.getMilestoneid()).withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createPercentagePopupField(final SimpleTask task) {
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
    public PopupView createDeadlinePopupField(final SimpleTask task) {
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
    public PopupView createStartDatePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (task.getStartdate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_FORWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(), AppContext.formatDate(task.getStartdate()));
                }

            }
        };
        builder.withBean(task).withBindProperty("startdate").withCaption(AppContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .withField(new DateField()).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getStartdate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createEndDatePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (task.getEnddate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_BACKWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(), AppContext.formatDate(task.getEnddate()));
                }

            }
        };
        builder.withBean(task).withBindProperty("enddate").withCaption(AppContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .withField(new DateField()).withService(ApplicationContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getEnddate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createCommentsPopupField(SimpleTask task) {
        TaskCommentsPopupView view = new TaskCommentsPopupView(task);
        view.setDescription("Click to edit");
        return view;
    }

    @Override
    public PopupView createBillableHoursPopupField(SimpleTask task) {
        TaskBillableHoursPopupField view = new TaskBillableHoursPopupField(task, true);
        view.setDescription("Billable hours");
        return view;
    }

    @Override
    public PopupView createNonBillableHoursPopupField(SimpleTask task) {
        TaskBillableHoursPopupField view = new TaskBillableHoursPopupField(task, false);
        view.setDescription("Non billable hours");
        return view;
    }

    @Override
    public PopupView createFollowersPopupField(SimpleTask task) {
        return new TaskFollowersPopupView(task);
    }

    private static class TaskFollowersPopupView extends LazyPopupView {
        private SimpleTask task;
        private WatchersMultiSelection watchersMultiSelection;

        TaskFollowersPopupView(SimpleTask task) {
            super("");
            this.task = task;
            this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " " + NumberUtils.zeroIfNull(task.getNumFollowers()));
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            watchersMultiSelection = new WatchersMultiSelection(ProjectTypeConstants.TASK, task.getId());
            layout.with(new ELabel("Modify watchers").withStyleName(ValoTheme.LABEL_H3), watchersMultiSelection);
        }

        @Override
        protected void doHide() {
            MonitorItemService monitorItemService = ApplicationContextUtil.getSpringBean(MonitorItemService.class);

            List<MonitorItem> items = watchersMultiSelection.getUnsavedItems();
            monitorItemService.saveMonitorItems(items);

            MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
            searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.TASK));
            searchCriteria.setTypeId(new NumberSearchField(task.getId()));
            int numFollowers = monitorItemService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " " + numFollowers);
        }
    }

    private static class TaskCommentsPopupView extends LazyPopupView {
        private SimpleTask task;

        TaskCommentsPopupView(SimpleTask task) {
            super("");
            this.task = task;
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + NumberUtils.zeroIfNull(task.getNumComments()));
        }

        @Override
        protected void doShow() {
            CommentDisplay commentDisplay = new CommentDisplay(ProjectTypeConstants.TASK, CurrentProjectVariables.getProjectId());
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            layout.with(commentDisplay);
            commentDisplay.loadComments(task.getId() + "");
        }

        @Override
        protected void doHide() {
            CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
            searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.TASK));
            searchCriteria.setTypeId(StringSearchField.and(task.getId() + ""));
            CommentService commentService = ApplicationContextUtil.getSpringBean(CommentService.class);
            int commentCount = commentService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + commentCount);
        }
    }

    private static class TaskBillableHoursPopupField extends LazyPopupView {
        private TextField timeInput = new TextField();
        private DateField dateField;
        private SimpleTask task;
        private boolean isBillable;

        TaskBillableHoursPopupField(SimpleTask task, boolean isBillable) {
            super("");
            this.task = task;
            this.isBillable = isBillable;
            if (isBillable) {
                this.setMinimizedValueAsHTML(FontAwesome.MONEY.getHtml() + " " + task.getBillableHours());
            } else {
                this.setMinimizedValueAsHTML(FontAwesome.GIFT.getHtml() + " " + task.getNonBillableHours());
            }
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.TASKS)) {
                timeInput.setValue("");
                timeInput.setDescription("The format of duration must be [number] d [number] h [number] m [number] s");
                String title = (isBillable) ? "Add billable hours" : "Add non billable hours";
                Label headerLbl = new Label(title, ContentMode.HTML);
                headerLbl.addStyleName(ValoTheme.LABEL_H3);
                dateField = new DateField();
                dateField.setValue(new GregorianCalendar().getTime());
                layout.with(headerLbl, timeInput);
                Label dateCaption = new Label("For date");
                dateCaption.addStyleName(ValoTheme.LABEL_H3);
                layout.with(dateCaption, dateField);
            } else {
                layout.add(new Label(AppContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
            }
        }

        @Override
        protected void doHide() {
            String timeVal = timeInput.getValue();
            if (StringUtils.isNotBlank(timeVal)) {
                Long delta = HumanTime.eval(timeVal).getDelta();
                Date date = DateTimeUtils.trimHMSOfDate(dateField.getValue());
                if (delta > 0) {
                    ItemTimeLoggingService timeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);
                    Double hours = delta.doubleValue() / (1000 * 60 * 60);
                    ItemTimeLogging timeLogging = new ItemTimeLogging();
                    timeLogging.setCreateduser(AppContext.getUsername());
                    timeLogging.setIsbillable(isBillable);
                    timeLogging.setLoguser(AppContext.getUsername());
                    timeLogging.setLogforday(date);
                    timeLogging.setLogvalue(hours);
                    timeLogging.setProjectid(CurrentProjectVariables.getProjectId());
                    timeLogging.setType(ProjectTypeConstants.TASK);
                    timeLogging.setTypeid(task.getId());
                    timeLogging.setSaccountid(AppContext.getAccountId());
                    timeLoggingService.saveWithSession(timeLogging, AppContext.getUsername());
                    EventBusFactory.getInstance().post(new ProjectEvent.TimeLoggingChangedEvent(TaskBillableHoursPopupField.this));

                    // load hours again
                    ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
                    searchCriteria.setIsBillable(new BooleanSearchField(isBillable));
                    searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                    searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.TASK));
                    searchCriteria.setTypeId(new NumberSearchField(task.getId()));
                    Double calculatedHours = timeLoggingService.getTotalHoursByCriteria(searchCriteria);
                    if (isBillable) {
                        this.setMinimizedValueAsHTML(FontAwesome.MONEY.getHtml() + " " + calculatedHours);
                    } else {
                        this.setMinimizedValueAsHTML(FontAwesome.GIFT.getHtml() + " " + calculatedHours);
                    }
                } else {
                    NotificationUtil.showWarningNotification("Invalid value. The format of duration must be [number] d [number] h [number] m [number] s");
                }
            }
        }
    }
}
