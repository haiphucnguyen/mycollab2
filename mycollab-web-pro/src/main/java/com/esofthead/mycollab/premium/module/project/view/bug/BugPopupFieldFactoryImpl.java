package com.esofthead.mycollab.premium.module.project.view.bug;

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
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.view.bug.*;
import com.esofthead.mycollab.module.project.view.bug.components.BugPriorityComboBox;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.premium.module.project.ui.components.WatchersMultiSelection;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.LazyPopupView;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.form.field.PopupBeanFieldBuilder;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@ViewComponent
public class BugPopupFieldFactoryImpl implements BugPopupFieldFactory {

    @Override
    public PopupView createBugPriorityPopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getBugPriorityHtml(bug.getPriority());
            }

            @Override
            protected String generateDescription() {
                return bug.getPriority();
            }
        };
        builder.withBean(bug).withBindProperty(BugWithBLOBs.Field.priority.name()).withDescription(bug.getPriority())
                .withCaption(AppContext.getMessage(BugI18nEnum.FORM_PRIORITY)).withField(new BugPriorityComboBox())
                .withService(ApplicationContextUtil.getSpringBean(BugService.class)).withValue(bug.getPriority())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createBugAssigneePopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                String avatarLink = StorageFactory.getInstance().getAvatarPath(bug.getAssignUserAvatarId(), 16);
                Img img = new Img(bug.getAssignuserFullName(), avatarLink).setTitle(bug.getAssignuserFullName());
                return img.write();
            }

            @Override
            protected String generateSmallAsHtmlAfterUpdate() {
                BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
                SimpleBug newBug = bugService.findById(bug.getId(), AppContext.getAccountId());
                String avatarLink = StorageFactory.getInstance().getAvatarPath(newBug.getAssignUserAvatarId(), 16);
                Img img = new Img(newBug.getAssignuserFullName(), avatarLink).setTitle(newBug.getAssignuserFullName());
                return img.write();
            }

            @Override
            protected String generateDescription() {
                return bug.getAssignuserFullName();
            }
        };
        builder.withBean(bug).withBindProperty(BugWithBLOBs.Field.assignuser.name()).withDescription(bug.getAssignuserFullName())
                .withCaption(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)).withField(new ProjectMemberSelectionField())
                .withService(ApplicationContextUtil.getSpringBean(BugService.class)).withValue(bug.getAssignuser())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createBugCommentsPopupField(SimpleBug bug) {
        BugCommentsPopupView view = new BugCommentsPopupView(bug);
        view.setDescription("Click to edit");
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
            if (bug.getNumFollowers() == null || bug.getNumFollowers() == 0) {
                this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " 0");
            } else {
                this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " " + bug.getNumFollowers());
            }
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            watchersMultiSelection = new WatchersMultiSelection(ProjectTypeConstants.BUG, bug.getId());
            layout.with(new ELabel("Modify watchers").withStyleName(ValoTheme.LABEL_H3), watchersMultiSelection);
        }

        @Override
        protected void doHide() {
            MonitorItemService monitorItemService = ApplicationContextUtil.getSpringBean(MonitorItemService.class);

            List<MonitorItem> items = watchersMultiSelection.getUnsavedItems();
            monitorItemService.saveMonitorItems(items);

            MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
            searchCriteria.setType(new StringSearchField(ProjectTypeConstants.BUG));
            searchCriteria.setTypeId(new NumberSearchField(bug.getId()));
            int numFollowers = monitorItemService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " " + numFollowers);
        }
    }

    private static class BugCommentsPopupView extends LazyPopupView {
        private SimpleBug bug;

        public BugCommentsPopupView(SimpleBug bug) {
            super("");
            this.bug = bug;
            if (bug.getNumComments() == null) {
                this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " 0");
            } else {
                this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + bug.getNumComments());
            }
        }

        @Override
        protected void doShow() {
            CommentDisplay commentDisplay = new CommentDisplay(ProjectTypeConstants.BUG, CurrentProjectVariables.getProjectId());
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            layout.with(commentDisplay);
            commentDisplay.loadComments(bug.getId() + "");
        }

        @Override
        protected void doHide() {
            CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
            searchCriteria.setType(new StringSearchField(ProjectTypeConstants.BUG));
            searchCriteria.setTypeid(new StringSearchField(bug.getId() + ""));
            CommentService commentService = ApplicationContextUtil.getSpringBean(CommentService.class);
            int commentCount = commentService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + commentCount);
        }
    }

    @Override
    public PopupView createBugStatusPopupField(final SimpleBug bug) {
        final PopupView view = new BugStatusPopupView(bug);
        view.setDescription("Click to edit");
        return view;
    }

    private static class BugStatusPopupView extends LazyPopupView implements IBugCallbackStatusComp {
        private SimpleBug beanItem;

        BugStatusPopupView(SimpleBug bug) {
            super(FontAwesome.INFO_CIRCLE.getHtml() + " " + AppContext.getMessage(OptionI18nEnum.BugStatus.class, bug.getStatus()));
            this.beanItem = bug;
        }

        @Override
        protected void doShow() {
            MVerticalLayout content = getWrapContent();
            content.removeAllComponents();
            boolean hasPermission = CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
            if (OptionI18nEnum.BugStatus.Open.name().equals(beanItem.getStatus()) ||
                    OptionI18nEnum.BugStatus.ReOpened.name().equals(beanItem.getStatus())) {
                Button startProgressBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_START_PROGRESS), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        setPopupVisible(false);
                        beanItem.setStatus(OptionI18nEnum.BugStatus.InProgress.name());
                        BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
                        bugService.updateSelectiveWithSession(beanItem, AppContext.getUsername());
                        refreshBugItem();
                    }
                });
                startProgressBtn.addStyleName(UIConstants.BUTTON_ACTION);
                startProgressBtn.setEnabled(hasPermission);

                Button resolveBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_RESOLVED), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        setPopupVisible(false);
                        UI.getCurrent().addWindow(new ResolvedInputWindow(BugStatusPopupView.this, beanItem));
                    }
                });
                resolveBtn.addStyleName(UIConstants.BUTTON_ACTION);
                resolveBtn.setEnabled(hasPermission);

                Button wontFixBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_WONTFIX), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        setPopupVisible(false);
                        UI.getCurrent().addWindow(new WontFixExplainWindow(BugStatusPopupView.this, beanItem));
                    }
                });
                wontFixBtn.addStyleName(UIConstants.BUTTON_ACTION);
                wontFixBtn.setEnabled(hasPermission);
                content.with(startProgressBtn, resolveBtn, wontFixBtn);
            } else if (OptionI18nEnum.BugStatus.InProgress.name().equals(beanItem.getStatus())) {
                Button stopProgressBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_STOP_PROGRESS), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        setPopupVisible(false);
                        beanItem.setStatus(OptionI18nEnum.BugStatus.Open.name());
                        BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
                        bugService.updateSelectiveWithSession(beanItem, AppContext.getUsername());
                        refreshBugItem();
                    }
                });
                stopProgressBtn.addStyleName(UIConstants.BUTTON_ACTION);
                stopProgressBtn.setEnabled(hasPermission);

                Button resolveBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_RESOLVED), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        setPopupVisible(false);
                        UI.getCurrent().addWindow(new ResolvedInputWindow(BugStatusPopupView.this, beanItem));
                    }
                });
                resolveBtn.addStyleName(UIConstants.BUTTON_ACTION);
                resolveBtn.setEnabled(hasPermission);
                content.with(stopProgressBtn, resolveBtn);
            } else if (OptionI18nEnum.BugStatus.Verified.name().equals(beanItem.getStatus())) {
                Button reopenBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_REOPEN), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        setPopupVisible(false);
                        UI.getCurrent().addWindow(new ReOpenWindow(BugStatusPopupView.this, beanItem));
                    }
                });
                reopenBtn.addStyleName(UIConstants.BUTTON_ACTION);
                reopenBtn.setEnabled(hasPermission);
                content.with(reopenBtn);
            } else if (OptionI18nEnum.BugStatus.Resolved.name().equals(beanItem.getStatus())) {
                Button reopenBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_REOPEN), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        setPopupVisible(false);
                        UI.getCurrent().addWindow(new ReOpenWindow(BugStatusPopupView.this, beanItem));
                    }
                });
                reopenBtn.addStyleName(UIConstants.BUTTON_ACTION);
                reopenBtn.setEnabled(hasPermission);

                Button approveNCloseBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_APPROVE_CLOSE), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        setPopupVisible(false);
                        UI.getCurrent().addWindow(new ApproveInputWindow(BugStatusPopupView.this, beanItem));
                    }
                });
                approveNCloseBtn.addStyleName(UIConstants.BUTTON_ACTION);
                approveNCloseBtn.setEnabled(hasPermission);
                content.with(reopenBtn, approveNCloseBtn);
            } else if (OptionI18nEnum.BugStatus.Resolved.name().equals(beanItem.getStatus())) {
                Button reopenBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_REOPEN), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI.getCurrent().addWindow(new ReOpenWindow(BugStatusPopupView.this, beanItem));
                    }
                });
                reopenBtn.addStyleName(UIConstants.BUTTON_ACTION);
                reopenBtn.setEnabled(hasPermission);
                content.with(reopenBtn);
            }
            if (!hasPermission) {
                content.addComponent(new Label(AppContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
            }
        }

        @Override
        public void refreshBugItem() {
            setMinimizedValueAsHTML(FontAwesome.INFO_CIRCLE.getHtml() + " " +
                    AppContext.getMessage(OptionI18nEnum.BugStatus.class, beanItem.getStatus()));
            markAsDirty();
        }
    }

    @Override
    public PopupView createBugMilestonePopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (bug.getMilestoneid() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    String milestoneName = ((MilestoneComboBox) field).getItemCaption(bug.getMilestoneid());
                    return ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml() + " " +
                            StringUtils.trim(milestoneName, 20, true);
                }
            }
        };
        MilestoneComboBox milestoneComboBox = new MilestoneComboBox();
        milestoneComboBox.setWidth("300px");
        builder.withBean(bug).withBindProperty("milestoneid").withCaption(AppContext.getMessage(BugI18nEnum.FORM_PHASE))
                .withField(milestoneComboBox).withService(ApplicationContextUtil.getSpringBean(BugService.class))
                .withValue(bug.getMilestoneid()).withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createBugDeadlinePopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (bug.getDueDateRoundPlusOne() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(FontAwesome.CLOCK_O.getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(), AppContext.formatPrettyTime(bug.getDueDateRoundPlusOne()));
                }
            }
        };
        builder.withBean(bug).withBindProperty("duedate").withCaption(AppContext.getMessage(BugI18nEnum.FORM_DUE_DATE))
                .withField(new DateField()).withService(ApplicationContextUtil.getSpringBean(BugService.class)).withValue(bug.getDuedate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));
        return builder.build();
    }

    @Override
    public PopupView createBugNonbillableHoursPopupField(SimpleBug bug) {
        return new BugBillableHoursPopupField(bug, false);
    }

    @Override
    public PopupView createBugBillableHoursPopupField(SimpleBug bug) {
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
                this.setMinimizedValueAsHTML(FontAwesome.MONEY.getHtml() + " " + bug.getBillableHours());
            } else {
                this.setMinimizedValueAsHTML(FontAwesome.GIFT.getHtml() + " " + bug.getNonBillableHours());
            }
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.BUGS)) {
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
                    timeLogging.setType(ProjectTypeConstants.BUG);
                    timeLogging.setTypeid(bug.getId());
                    timeLogging.setSaccountid(AppContext.getAccountId());
                    timeLoggingService.saveWithSession(timeLogging, AppContext.getUsername());
                    EventBusFactory.getInstance().post(new ProjectEvent.TimeLoggingChangedEvent(BugBillableHoursPopupField.this));

                    // load hours again
                    ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
                    searchCriteria.setIsBillable(new BooleanSearchField(isBillable));
                    searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                    searchCriteria.setType(new StringSearchField(ProjectTypeConstants.BUG));
                    searchCriteria.setTypeId(new NumberSearchField(bug.getId()));
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
