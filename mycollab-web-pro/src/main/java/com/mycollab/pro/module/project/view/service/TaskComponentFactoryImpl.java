package com.mycollab.pro.module.project.view.service;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.FollowerI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.service.CommentService;
import com.mycollab.common.service.MonitorItemService;
import com.mycollab.configuration.StorageFactory;
import com.mycollab.core.utils.HumanTime;
import com.mycollab.core.utils.NumberUtils;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.CommentDisplay;
import com.mycollab.module.project.ui.components.PriorityComboBox;
import com.mycollab.module.project.ui.components.TaskSliderField;
import com.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.mycollab.module.project.view.service.TaskComponentFactory;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.module.project.view.task.TaskStatusComboBox;
import com.mycollab.pro.module.project.ui.components.WatchersMultiSelection;
import com.mycollab.pro.vaadin.web.ui.field.PopupBeanFieldBuilder;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.LazyPopupView;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.field.DateTimeOptionField;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Service;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@Service
public class TaskComponentFactoryImpl implements TaskComponentFactory {

    @Override
    public PopupView createAssigneePopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                String avatarLink = StorageFactory.getAvatarPath(task.getAssignUserAvatarId(), 16);
                Img img = new Img(task.getAssignUserFullName(), avatarLink).setTitle(task.getAssignUserFullName())
                        .setCSSClass(UIConstants.CIRCLE_BOX);
                return img.write();
            }

            @Override
            protected String generateSmallAsHtmlAfterUpdate() {
                ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                SimpleTask newTask = taskService.findById(task.getId(), MyCollabUI.getAccountId());
                String avatarLink = StorageFactory.getAvatarPath(newTask.getAssignUserAvatarId(), 16);
                Img img = new Img(newTask.getAssignUserFullName(), avatarLink).setTitle(newTask.getAssignUserFullName())
                        .setCSSClass(UIConstants.CIRCLE_BOX);
                return img.write();
            }

            @Override
            protected String generateDescription() {
                return task.getAssignUserFullName();
            }
        };
        builder.withBean(task).withBindProperty("assignuser").withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE))
                .withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)).withField(new ProjectMemberSelectionField())
                .withService(AppContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getAssignuser())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createPriorityPopupField(final SimpleTask task) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getPriorityHtml(task.getPriority());
            }

            @Override
            protected String generateDescription() {
                return task.getPriority();
            }
        };
        builder.withBean(task).withBindProperty("priority").withDescription(task.getPriority())
                .withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY))
                .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY_HELP))
                .withField(new PriorityComboBox())
                .withService(AppContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getPriority())
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
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return FontAwesome.INFO_CIRCLE.getHtml() + " " + StringUtils.trim(task.getStatus(), 20, true);
                }
            }
        };
        builder.withBean(task).withBindProperty("status").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_STATUS))
                .withDescription(UserUIContext.getMessage(TaskI18nEnum.FORM_STATUS_HELP))
                .withField(new TaskStatusComboBox()).withService(AppContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getStatus())
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
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
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
        builder.withBean(task).withBindProperty("milestoneid").withCaption(UserUIContext.getMessage(MilestoneI18nEnum.SINGLE))
                .withField(milestoneComboBox).withService(AppContextUtil.getSpringBean(ProjectTaskService.class))
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
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                }
            }
        };
        builder.withBean(task).withBindProperty("percentagecomplete")
                .withCaption(UserUIContext.getMessage(TaskI18nEnum.FORM_PERCENTAGE_COMPLETE)).withField(new TaskSliderField())
                .withDescription(UserUIContext.getMessage(TaskI18nEnum.FORM_PERCENTAGE_COMPLETE))
                .withService(AppContextUtil.getSpringBean(ProjectTaskService.class)).withValue(task.getPercentagecomplete())
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
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(), UserUIContext.formatPrettyTime(task.getDeadlineRoundPlusOne()));
                }

            }
        };
        builder.withBean(task).withBindProperty("duedate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_DUE_DATE))
                .withField(new DateTimeOptionField(true)).withService(AppContextUtil.getSpringBean(ProjectTaskService.class))
                .withValue(task.getDuedate()).withHasPermission(CurrentProjectVariables.canWrite
                (ProjectRolePermissionCollections.TASKS));
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
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(), UserUIContext.formatDate(task.getStartdate()));
                }

            }
        };
        builder.withBean(task).withBindProperty("startdate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .withField(new DateTimeOptionField(true)).withService(AppContextUtil.getSpringBean(ProjectTaskService
                .class)).withValue(task.getStartdate())
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
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(), UserUIContext.formatDate(task.getEnddate()));
                }

            }
        };
        builder.withBean(task).withBindProperty("enddate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .withField(new DateTimeOptionField(true)).withService(AppContextUtil.getSpringBean(ProjectTaskService
                .class)).withValue(task.getEnddate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        return builder.build();
    }

    @Override
    public PopupView createCommentsPopupField(SimpleTask task) {
        TaskCommentsPopupView view = new TaskCommentsPopupView(task);
        view.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_ADD_COMMENT));
        return view;
    }

    @Override
    public PopupView createBillableHoursPopupField(SimpleTask task) {
        TaskBillableHoursPopupField view = new TaskBillableHoursPopupField(task, true);
        view.setDescription(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS));
        return view;
    }

    @Override
    public PopupView createNonBillableHoursPopupField(SimpleTask task) {
        TaskBillableHoursPopupField view = new TaskBillableHoursPopupField(task, false);
        view.setDescription(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS));
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
            this.setDescription(UserUIContext.getMessage(FollowerI18nEnum.FOLLOWER_EXPLAIN_HELP));
            this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " " + NumberUtils.zeroIfNull(task.getNumFollowers()));
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            watchersMultiSelection = new WatchersMultiSelection(ProjectTypeConstants.TASK, task.getId(),
                    CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
            layout.with(new ELabel(UserUIContext.getMessage(FollowerI18nEnum.OPT_SUB_INFO_WATCHERS))
                    .withStyleName(ValoTheme.LABEL_H3), watchersMultiSelection);
        }

        @Override
        protected void doHide() {
            MonitorItemService monitorItemService = AppContextUtil.getSpringBean(MonitorItemService.class);

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
            MVerticalLayout layout = getWrapContent().withStyleName(WebThemes.SCROLLABLE_CONTAINER);
            layout.removeAllComponents();
            layout.with(commentDisplay);
            commentDisplay.loadComments(task.getId() + "");
        }

        @Override
        protected void doHide() {
            CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
            searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.TASK));
            searchCriteria.setTypeId(StringSearchField.and(task.getId() + ""));
            CommentService commentService = AppContextUtil.getSpringBean(CommentService.class);
            int commentCount = commentService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + commentCount);
        }

        @Override
        protected String getConstraintWidth() {
            return "900px";
        }
    }

    private static class TaskBillableHoursPopupField extends LazyPopupView {
        private TextField timeInput = new TextField();
        private PopupDateFieldExt dateField;
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
                timeInput.setDescription(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_TIME_FORMAT));
                String title = (isBillable) ? UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS) :
                        UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS);
                Label headerLbl = ELabel.h3(title);
                dateField = new PopupDateFieldExt();
                dateField.setValue(new GregorianCalendar().getTime());
                layout.with(headerLbl, timeInput);
                layout.with(ELabel.h3(UserUIContext.getMessage(DayI18nEnum.OPT_DATE)), dateField);
            } else {
                layout.add(new Label(UserUIContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
            }
        }

        @Override
        protected void doHide() {
            String timeVal = timeInput.getValue();
            if (StringUtils.isNotBlank(timeVal)) {
                Long delta = HumanTime.eval(timeVal).getDelta();
                Date date = dateField.getValue();
                if (delta > 0) {
                    ItemTimeLoggingService timeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
                    Double hours = delta.doubleValue() / (1000 * 60 * 60);
                    ItemTimeLogging timeLogging = new ItemTimeLogging();
                    timeLogging.setCreateduser(UserUIContext.getUsername());
                    timeLogging.setIsbillable(isBillable);
                    timeLogging.setLoguser(UserUIContext.getUsername());
                    timeLogging.setLogforday(date);
                    timeLogging.setLogvalue(hours);
                    timeLogging.setProjectid(CurrentProjectVariables.getProjectId());
                    timeLogging.setType(ProjectTypeConstants.TASK);
                    timeLogging.setTypeid(task.getId());
                    timeLogging.setSaccountid(MyCollabUI.getAccountId());
                    timeLoggingService.saveWithSession(timeLogging, UserUIContext.getUsername());
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
                    NotificationUtil.showWarningNotification(UserUIContext.getMessage(TimeTrackingI18nEnum.ERROR_TIME_FORMAT));
                }
            }
        }
    }
}
