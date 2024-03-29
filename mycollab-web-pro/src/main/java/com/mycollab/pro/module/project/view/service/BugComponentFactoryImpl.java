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
import com.mycollab.core.utils.HumanTime;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.file.StorageUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.TimeTrackingEvent.TimeLoggingChangedEvent;
import com.mycollab.module.project.i18n.BugI18nEnum;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.CommentDisplay;
import com.mycollab.module.project.ui.components.PriorityComboBox;
import com.mycollab.module.project.view.bug.ApproveInputWindow;
import com.mycollab.module.project.view.bug.ReOpenWindow;
import com.mycollab.module.project.view.bug.ResolvedInputWindow;
import com.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.mycollab.module.project.view.service.BugComponentFactory;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.module.project.domain.BugWithBLOBs;
import com.mycollab.module.project.domain.SimpleBug;
import com.mycollab.module.project.service.BugService;
import com.mycollab.pro.module.project.ui.components.WatchersMultiSelection;
import com.mycollab.pro.vaadin.web.ui.field.PopupBeanFieldBuilder;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.LazyPopupView;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Service;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.LocalDate;
import java.util.List;

import static com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@Service
public class BugComponentFactoryImpl implements BugComponentFactory {

    @Override
    public PopupView createPriorityPopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder<SimpleBug> builder = new PopupBeanFieldBuilder<SimpleBug>() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getPriority(bug.getPriority()).getHtml();
            }

            @Override
            protected String generateDescription() {
                return bug.getPriority();
            }
        };
        builder.withBean(bug).withBindProperty(BugWithBLOBs.Field.priority.name()).withDescription(bug.getPriority())
                .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY_HELP))
                .withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY)).withField(new PriorityComboBox())
                .withService(AppContextUtil.getSpringBean(BugService.class)).withValue(bug.getPriority())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createAssigneePopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder<SimpleBug> builder = new PopupBeanFieldBuilder<SimpleBug>() {
            @Override
            protected String generateSmallContentAsHtml() {
                String avatarLink = StorageUtils.getAvatarPath(bug.getAssignUserAvatarId(), 16);
                Img img = new Img(bug.getAssignuserFullName(), avatarLink).setTitle(bug.getAssignuserFullName())
                        .setCSSClass(WebThemes.CIRCLE_BOX);
                return img.write();
            }

            @Override
            protected String generateSmallAsHtmlAfterUpdate() {
                BugService bugService = AppContextUtil.getSpringBean(BugService.class);
                SimpleBug newBug = bugService.findById(bug.getId(), AppUI.getAccountId());
                String avatarLink = StorageUtils.getAvatarPath(newBug.getAssignUserAvatarId(), 16);
                Img img = new Img(newBug.getAssignuserFullName(), avatarLink).setTitle(newBug.getAssignuserFullName())
                        .setCSSClass(WebThemes.CIRCLE_BOX);
                return img.write();
            }

            @Override
            protected String generateDescription() {
                return bug.getAssignuserFullName();
            }
        };
        builder.withBean(bug).withBindProperty(BugWithBLOBs.Field.assignuser.name()).withDescription(bug.getAssignuserFullName())
                .withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)).withField(new ProjectMemberSelectionField())
                .withService(AppContextUtil.getSpringBean(BugService.class)).withValue(bug.getAssignuser())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createCommentsPopupField(SimpleBug bug) {
        BugCommentsPopupView view = new BugCommentsPopupView(bug);
        view.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_ADD_COMMENT));
        return view;
    }

    @Override
    public PopupView createFollowersPopupField(SimpleBug bug) {
        return new BugFollowersPopupView(bug);
    }

    private static class BugFollowersPopupView extends LazyPopupView {
        private SimpleBug bug;
        private WatchersMultiSelection watchersMultiSelection;

        BugFollowersPopupView(SimpleBug bug) {
            super("");
            this.bug = bug;
            this.setDescription(UserUIContext.getMessage(FollowerI18nEnum.FOLLOWER_EXPLAIN_HELP));
            if (bug.getNumFollowers() == null || bug.getNumFollowers() == 0) {
                this.setMinimizedValueAsHTML(VaadinIcons.EYE.getHtml() + " 0");
            } else {
                this.setMinimizedValueAsHTML(VaadinIcons.EYE.getHtml() + " " + bug.getNumFollowers());
            }
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            watchersMultiSelection = new WatchersMultiSelection(ProjectTypeConstants.BUG, bug.getId(),
                    CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
            layout.with(new ELabel(UserUIContext.getMessage(FollowerI18nEnum.OPT_SUB_INFO_WATCHERS)).withStyleName(ValoTheme.LABEL_H3),
                    watchersMultiSelection);
        }

        @Override
        protected void doHide() {
            MonitorItemService monitorItemService = AppContextUtil.getSpringBean(MonitorItemService.class);

            List<MonitorItem> items = watchersMultiSelection.getUnsavedItems();
            monitorItemService.saveMonitorItems(items);

            MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
            searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.BUG));
            searchCriteria.setTypeId(new NumberSearchField(bug.getId()));
            int numFollowers = monitorItemService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(VaadinIcons.EYE.getHtml() + " " + numFollowers);
        }
    }

    private static class BugCommentsPopupView extends LazyPopupView {
        private SimpleBug bug;

        BugCommentsPopupView(SimpleBug bug) {
            super("");
            this.bug = bug;
            if (bug.getNumComments() == null) {
                this.setMinimizedValueAsHTML(VaadinIcons.COMMENT_O.getHtml() + " 0");
            } else {
                this.setMinimizedValueAsHTML(VaadinIcons.COMMENT_O.getHtml() + " " + bug.getNumComments());
            }
        }

        @Override
        protected String getConstraintWidth() {
            return "900px";
        }

        @Override
        protected void doShow() {
            CommentDisplay commentDisplay = new CommentDisplay(ProjectTypeConstants.BUG, CurrentProjectVariables.getProjectId());
            MVerticalLayout layout = getWrapContent().withStyleName(WebThemes.SCROLLABLE_CONTAINER);
            layout.removeAllComponents();
            layout.with(commentDisplay);
            commentDisplay.loadComments(bug.getId() + "");
        }

        @Override
        protected void doHide() {
            CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
            searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.BUG));
            searchCriteria.setTypeId(StringSearchField.and(bug.getId() + ""));
            CommentService commentService = AppContextUtil.getSpringBean(CommentService.class);
            int commentCount = commentService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(VaadinIcons.COMMENT_O.getHtml() + " " + commentCount);
        }
    }

    @Override
    public PopupView createStatusPopupField(SimpleBug bug) {
        final PopupView view = new BugStatusPopupView(bug);
        view.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_CLICK_TO_EDIT));
        return view;
    }

    private static class BugStatusPopupView extends LazyPopupView {
        private SimpleBug beanItem;

        BugStatusPopupView(SimpleBug bug) {
            super(VaadinIcons.INFO_CIRCLE.getHtml() + " " + UserUIContext.getMessage(StatusI18nEnum.class, bug.getStatus()));
            this.beanItem = bug;
            this.setDescription(UserUIContext.getMessage(BugI18nEnum.FORM_STATUS_HELP));
        }

        @Override
        protected void doShow() {
            MVerticalLayout content = getWrapContent();
            content.removeAllComponents();
            boolean hasPermission = CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
            if (StatusI18nEnum.Open.name().equals(beanItem.getStatus()) ||
                    StatusI18nEnum.ReOpen.name().equals(beanItem.getStatus())) {
                MButton resolveBtn = new MButton(UserUIContext.getMessage(BugI18nEnum.BUTTON_RESOLVED), clickEvent -> {
                    setPopupVisible(false);
                    UI.getCurrent().addWindow(bindCloseWindow(new ResolvedInputWindow(beanItem)));
                }).withStyleName(WebThemes.BUTTON_ACTION);
                resolveBtn.setVisible(hasPermission);
                content.with(resolveBtn);
            } else if (StatusI18nEnum.Verified.name().equals(beanItem.getStatus())) {
                MButton reopenBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN), clickEvent -> {
                    setPopupVisible(false);
                    UI.getCurrent().addWindow(bindCloseWindow(new ReOpenWindow(beanItem)));
                }).withStyleName(WebThemes.BUTTON_ACTION);
                reopenBtn.setVisible(hasPermission);
                content.with(reopenBtn);
            } else if (StatusI18nEnum.Resolved.name().equals(beanItem.getStatus())) {
                MButton reopenBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN), clickEvent -> {
                    setPopupVisible(false);
                    UI.getCurrent().addWindow(bindCloseWindow(new ReOpenWindow(beanItem)));
                }).withStyleName(WebThemes.BUTTON_ACTION);
                reopenBtn.setVisible(hasPermission);

                MButton approveNCloseBtn = new MButton(UserUIContext.getMessage(BugI18nEnum.BUTTON_APPROVE_CLOSE), clickEvent -> {
                    setPopupVisible(false);
                    UI.getCurrent().addWindow(bindCloseWindow(new ApproveInputWindow(beanItem)));
                }).withStyleName(WebThemes.BUTTON_ACTION);
                approveNCloseBtn.setVisible(hasPermission);
                content.with(reopenBtn, approveNCloseBtn);
            }
            if (!hasPermission) {
                content.addComponent(new Label(UserUIContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
            }
        }

        @Override
        protected void doHide() {
            setMinimizedValueAsHTML(VaadinIcons.INFO_CIRCLE.getHtml() + " " +
                    UserUIContext.getMessage(StatusI18nEnum.class, beanItem.getStatus()));
        }

        private Window bindCloseWindow(Window window) {
            window.addCloseListener(closeEvent -> refresh());
            return window;
        }

        private void refresh() {
            setMinimizedValueAsHTML(VaadinIcons.INFO_CIRCLE.getHtml() + " " +
                    UserUIContext.getMessage(StatusI18nEnum.class, beanItem.getStatus()));
            markAsDirty();
        }
    }

    @Override
    public PopupView createMilestonePopupField(SimpleBug bug) {
        PopupBeanFieldBuilder<SimpleBug> builder = new PopupBeanFieldBuilder<SimpleBug>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (bug.getMilestoneid() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    String milestoneName = ((MilestoneComboBox) field).getValue().getName();
                    return ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml() + " " +
                            StringUtils.trim(milestoneName, 20, true);
                }
            }
        };
        MilestoneComboBox milestoneComboBox = new MilestoneComboBox();
        milestoneComboBox.setWidth("300px");
        builder.withBean(bug).withBindProperty("milestoneid").withCaption(UserUIContext.getMessage(MilestoneI18nEnum.SINGLE))
                .withField(milestoneComboBox).withService(AppContextUtil.getSpringBean(BugService.class))
                .withValue(bug.getMilestoneid()).withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createDeadlinePopupField(SimpleBug bug) {
        PopupBeanFieldBuilder<SimpleBug> builder = new PopupBeanFieldBuilder<SimpleBug>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (bug.getDueDateRoundPlusOne() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.CLOCK.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.CLOCK.getHtml(), UserUIContext.formatPrettyTime(bug.getDueDateRoundPlusOne()));
                }
            }
        };
        builder.withBean(bug).withBindProperty("duedate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_DUE_DATE))
                .withField(new DateField()).withService(AppContextUtil.getSpringBean(BugService.class)).withValue(bug.getDuedate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createStartDatePopupField(SimpleBug bug) {
        PopupBeanFieldBuilder<SimpleBug> builder = new PopupBeanFieldBuilder<SimpleBug>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (bug.getStartdate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_FORWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(), UserUIContext.formatPrettyTime(bug.getStartdate()));
                }
            }
        };
        builder.withBean(bug).withBindProperty("startdate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .withField(new DateField()).withService(AppContextUtil.getSpringBean(BugService.class)).withValue(bug.getStartdate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createEndDatePopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder<SimpleBug> builder = new PopupBeanFieldBuilder<SimpleBug>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (bug.getEnddate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_BACKWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT)).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(), UserUIContext.formatPrettyTime(bug.getEnddate()));
                }
            }
        };
        builder.withBean(bug).withBindProperty("enddate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .withField(new DateField()).withService(AppContextUtil.getSpringBean(BugService.class)).withValue(bug.getEnddate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createNonbillableHoursPopupField(SimpleBug bug) {
        return new BugBillableHoursPopupField(bug, false);
    }

    @Override
    public PopupView createBillableHoursPopupField(SimpleBug bug) {
        return new BugBillableHoursPopupField(bug, true);
    }

    private static class BugBillableHoursPopupField extends LazyPopupView {
        private TextField timeInput = new TextField();
        private DateField dateField;
        private SimpleBug bug;
        private boolean isBillable;

        BugBillableHoursPopupField(SimpleBug bug, boolean isBillable) {
            super("");
            this.bug = bug;
            this.isBillable = isBillable;
            if (isBillable) {
                this.setMinimizedValueAsHTML(VaadinIcons.MONEY.getHtml() + " " + bug.getBillableHours());
            } else {
                this.setMinimizedValueAsHTML(VaadinIcons.GIFT.getHtml() + " " + bug.getNonBillableHours());
            }
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.BUGS)) {
                timeInput.setValue("");
                timeInput.setDescription(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_TIME_FORMAT));
                String title = (isBillable) ? UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS) :
                        UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS);
                Label headerLbl = ELabel.h3(title);
                dateField = new DateField();
                dateField.setValue(LocalDate.now());
                layout.with(headerLbl, timeInput);
                Label dateCaption = ELabel.html(UserUIContext.getMessage(DayI18nEnum.OPT_DATE));
                layout.with(dateCaption, dateField);
            } else {
                layout.add(new Label(UserUIContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
            }
        }

        @Override
        protected void doHide() {
            String timeVal = timeInput.getValue();
            if (StringUtils.isNotBlank(timeVal)) {
                long delta = HumanTime.eval(timeVal).getDelta();
                LocalDate date = dateField.getValue();
                if (delta > 0) {
                    ItemTimeLoggingService timeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
                    Double hours = (double) delta / (1000 * 60 * 60);
                    ItemTimeLogging timeLogging = new ItemTimeLogging();
                    timeLogging.setCreateduser(UserUIContext.getUsername());
                    timeLogging.setIsbillable(isBillable);
                    timeLogging.setLoguser(UserUIContext.getUsername());
                    timeLogging.setLogforday(date);
                    timeLogging.setLogvalue(hours);
                    timeLogging.setProjectid(CurrentProjectVariables.getProjectId());
                    timeLogging.setType(ProjectTypeConstants.BUG);
                    timeLogging.setTypeid(bug.getId());
                    timeLogging.setSaccountid(AppUI.getAccountId());
                    timeLoggingService.saveWithSession(timeLogging, UserUIContext.getUsername());
                    EventBusFactory.getInstance().post(new TimeLoggingChangedEvent(BugBillableHoursPopupField.this));

                    // load hours again
                    ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
                    searchCriteria.setBillable(new BooleanSearchField(isBillable));
                    searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                    searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.BUG));
                    searchCriteria.setTypeId(new NumberSearchField(bug.getId()));
                    double calculatedHours = timeLoggingService.getTotalHoursByCriteria(searchCriteria);
                    if (isBillable) {
                        this.setMinimizedValueAsHTML(VaadinIcons.MONEY.getHtml() + " " + calculatedHours);
                    } else {
                        this.setMinimizedValueAsHTML(VaadinIcons.GIFT.getHtml() + " " + calculatedHours);
                    }
                } else {
                    NotificationUtil.showWarningNotification(UserUIContext.getMessage(TimeTrackingI18nEnum.ERROR_TIME_FORMAT));
                }
            }
        }
    }
}
