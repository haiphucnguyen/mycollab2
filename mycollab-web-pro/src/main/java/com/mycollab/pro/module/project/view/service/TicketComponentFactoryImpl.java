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
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.common.service.CommentService;
import com.mycollab.common.service.MonitorItemService;
import com.mycollab.configuration.StorageFactory;
import com.mycollab.core.MyCollabException;
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
import com.mycollab.module.project.domain.*;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.module.project.i18n.*;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.service.ProjectTicketService;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.CommentDisplay;
import com.mycollab.module.project.ui.components.PriorityComboBox;
import com.mycollab.module.project.view.bug.ApproveInputWindow;
import com.mycollab.module.project.view.bug.BugEditForm;
import com.mycollab.module.project.view.bug.ReOpenWindow;
import com.mycollab.module.project.view.bug.ResolvedInputWindow;
import com.mycollab.module.project.view.service.TicketComponentFactory;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.module.project.view.task.TaskEditForm;
import com.mycollab.module.project.view.task.components.TaskStatusComboBox;
import com.mycollab.module.tracker.domain.BugWithBLOBs;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.pro.module.project.ui.components.WatchersMultiSelection;
import com.mycollab.pro.module.project.view.risk.RiskEditForm;
import com.mycollab.pro.vaadin.web.ui.field.PopupBeanFieldBuilder;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.mycollab.vaadin.web.ui.LazyPopupView;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.stereotype.Service;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@Service
public class TicketComponentFactoryImpl implements TicketComponentFactory {

    private static void save(ProjectTicket bean) {
        AppContextUtil.getSpringBean(ProjectTicketService.class).updateTicket(bean, UserUIContext.getUsername());
    }

    @Override
    public AbstractComponent createStartDatePopupField(ProjectTicket ticket) {
        PopupBeanFieldBuilder<ProjectTicket> builder = new PopupBeanFieldBuilder<ProjectTicket>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (ticket.getStartDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_FORWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(), UserUIContext.formatDate(ticket.getStartDate()));
                }
            }

            @Override
            public boolean isPermission() {
                return CurrentProjectVariables.canWriteTicket(bean);
            }

            @Override
            public void save() {
                TicketComponentFactoryImpl.save(bean);
            }
        };
        builder.withBean(ticket).withBindProperty("startDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .withField(new PopupDateFieldExt()).withValue(ticket.getStartDate());
        return builder.build();
    }

    @Override
    public AbstractComponent createEndDatePopupField(ProjectTicket ticket) {
        PopupBeanFieldBuilder<ProjectTicket> builder = new PopupBeanFieldBuilder<ProjectTicket>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (ticket.getEndDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_BACKWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(), UserUIContext.formatDate(ticket.getEndDate()));
                }
            }

            @Override
            public boolean isPermission() {
                return CurrentProjectVariables.canWriteTicket(bean);
            }

            @Override
            public void save() {
                TicketComponentFactoryImpl.save(bean);
            }
        };
        builder.withBean(ticket).withBindProperty("endDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .withField(new PopupDateFieldExt()).withValue(ticket.getEndDate());
        return builder.build();
    }

    @Override
    public AbstractComponent createDueDatePopupField(ProjectTicket ticket) {
        PopupBeanFieldBuilder<ProjectTicket> builder = new PopupBeanFieldBuilder<ProjectTicket>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (ticket.getDueDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.CLOCK.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.CLOCK.getHtml(), UserUIContext.formatDate(ticket.getDueDate()));
                }
            }

            @Override
            public boolean isPermission() {
                return CurrentProjectVariables.canWriteTicket(bean);
            }

            @Override
            public void save() {
                TicketComponentFactoryImpl.save(bean);
            }
        };
        builder.withBean(ticket).withBindProperty("dueDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_DUE_DATE))
                .withField(new PopupDateFieldExt()).withValue(ticket.getDueDate());
        return builder.build();
    }

    @Override
    public AbstractComponent createPriorityPopupField(ProjectTicket ticket) {
        PopupBeanFieldBuilder<ProjectTicket> builder = new PopupBeanFieldBuilder<ProjectTicket>() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getPriorityHtml(ticket.getPriority()) + " " + UserUIContext.getMessage(Priority.class,
                        ticket.getPriority());
            }

            @Override
            protected String generateDescription() {
                return ticket.getPriority();
            }

            @Override
            public boolean isPermission() {
                return CurrentProjectVariables.canWriteTicket(ticket);
            }

            @Override
            public void save() {
                TicketComponentFactoryImpl.save(ticket);
            }
        };
        builder.withBean(ticket).withBindProperty("priority").withDescription(ticket.getPriority())
                .withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY))
                .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY_HELP))
                .withField(new PriorityComboBox()).withValue(ticket.getPriority());
        return builder.build();
    }

    @Override
    public AbstractComponent createAssigneePopupField(ProjectTicket ticket) {
        PopupBeanFieldBuilder<ProjectTicket> builder = new PopupBeanFieldBuilder<ProjectTicket>() {
            @Override
            protected String generateSmallContentAsHtml() {
                String avatarLink = StorageFactory.getAvatarPath(ticket.getAssignUserAvatarId(), 16);
                Img img = new Img(ticket.getAssignUserFullName(), avatarLink).setTitle(ticket.getAssignUserFullName())
                        .setCSSClass(UIConstants.CIRCLE_BOX);
                return img.write();
            }

            @Override
            public boolean isPermission() {
                return CurrentProjectVariables.canWriteTicket(ticket);
            }

            @Override
            public void save() {
                TicketComponentFactoryImpl.save(ticket);
            }

            @Override
            protected String generateDescription() {
                return ticket.getAssignUserFullName();
            }
        };
        builder.withBean(ticket).withBindProperty("assignUser").withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE))
                .withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)).withField(new ProjectMemberSelectionField())
                .withValue(ticket.getAssignUser());
        return builder.build();
    }

    @Override
    public AbstractComponent createBillableHoursPopupField(ProjectTicket ticket) {
        TicketBillableHoursPopupField view = new TicketBillableHoursPopupField(ticket, true);
        view.setDescription(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS));
        return view;
    }

    @Override
    public AbstractComponent createNonBillableHoursPopupField(ProjectTicket ticket) {
        TicketBillableHoursPopupField view = new TicketBillableHoursPopupField(ticket, false);
        view.setDescription(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS));
        return view;
    }

    @Override
    public AbstractComponent createFollowersPopupField(ProjectTicket ticket) {
        return new TicketFollowersPopupView(ticket);
    }

    @Override
    public AbstractComponent createStatusPopupField(ProjectTicket ticket) {
        if (ticket.isTask()) {
            Task task = ProjectTicket.buildTask(ticket);
            PopupBeanFieldBuilder<Task> builder = new PopupBeanFieldBuilder<Task>() {
                @Override
                protected String generateSmallContentAsHtml() {
                    if (task.getStatus() == null) {
                        Div divHint = new Div().setCSSClass("nonValue");
                        divHint.appendText(FontAwesome.INFO_CIRCLE.getHtml());
                        divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                                .setCSSClass("hide"));
                        return divHint.write();
                    } else {
                        return FontAwesome.INFO_CIRCLE.getHtml() + " " + UserUIContext.getMessage(StatusI18nEnum.class, task.getStatus());
                    }
                }
            };
            builder.withBean(task).withBindProperty("status").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_STATUS))
                    .withDescription(UserUIContext.getMessage(TaskI18nEnum.FORM_STATUS_HELP))
                    .withField(new TaskStatusComboBox()).withService(AppContextUtil.getSpringBean(ProjectTaskService.class)).withValue(ticket.getStatus())
                    .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
            return builder.build();
        } else if (ticket.isBug()) {
            return new BugStatusPopupView(ProjectTicket.buildBug(ticket));
        } else if (ticket.isRisk()) {
            Risk risk = ProjectTicket.buildRisk(ticket);
            PopupBeanFieldBuilder<Risk> builder = new PopupBeanFieldBuilder<Risk>() {
                @Override
                protected String generateSmallContentAsHtml() {
                    if (risk.getStatus() == null) {
                        Div divHint = new Div().setCSSClass("nonValue");
                        divHint.appendText(FontAwesome.INFO_CIRCLE.getHtml());
                        divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                                .setCSSClass("hide"));
                        return divHint.write();
                    } else {
                        return FontAwesome.INFO_CIRCLE.getHtml() + " " + UserUIContext.getMessage(StatusI18nEnum.class, risk.getStatus());
                    }
                }
            };
            builder.withBean(risk).withBindProperty("status").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_STATUS))
                    .withField(new I18nValueComboBox(false, StatusI18nEnum.Open, StatusI18nEnum.Closed))
                    .withService(AppContextUtil.getSpringBean(RiskService.class)).withValue(ticket.getStatus())
                    .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));
            return builder.build();
        } else {
            throw new MyCollabException("Not support");
        }
    }

    @Override
    public AbstractComponent createCommentsPopupField(ProjectTicket task) {
        TicketCommentsPopupView view = new TicketCommentsPopupView(task);
        view.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_ADD_COMMENT));
        return view;
    }

    private static class TicketFollowersPopupView extends LazyPopupView {
        private ProjectTicket ticket;
        private WatchersMultiSelection watchersMultiSelection;

        TicketFollowersPopupView(ProjectTicket ticket) {
            super("");
            this.ticket = ticket;
            this.setDescription(UserUIContext.getMessage(FollowerI18nEnum.FOLLOWER_EXPLAIN_HELP));
            this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " " + NumberUtils.zeroIfNull(ticket.getNumFollowers()));
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            watchersMultiSelection = new WatchersMultiSelection(ticket.getType(), ticket.getTypeId(),
                    CurrentProjectVariables.canWriteTicket(ticket));
            layout.with(new ELabel(UserUIContext.getMessage(FollowerI18nEnum.OPT_SUB_INFO_WATCHERS))
                    .withStyleName(ValoTheme.LABEL_H3), watchersMultiSelection);
        }

        @Override
        protected void doHide() {
            MonitorItemService monitorItemService = AppContextUtil.getSpringBean(MonitorItemService.class);

            List<MonitorItem> items = watchersMultiSelection.getUnsavedItems();
            monitorItemService.saveMonitorItems(items);

            MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
            searchCriteria.setType(StringSearchField.and(ticket.getType()));
            searchCriteria.setTypeId(new NumberSearchField(ticket.getTypeId()));
            int numFollowers = monitorItemService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " " + numFollowers);
        }
    }

    private static class BugStatusPopupView extends LazyPopupView {
        private BugWithBLOBs beanItem;

        BugStatusPopupView(BugWithBLOBs bug) {
            super(FontAwesome.INFO_CIRCLE.getHtml() + " " + UserUIContext.getMessage(BugStatus.class, bug.getStatus()));
            this.beanItem = bug;
            this.setDescription(UserUIContext.getMessage(BugI18nEnum.FORM_STATUS_HELP));
        }

        @Override
        protected void doShow() {
            MVerticalLayout content = getWrapContent();
            content.removeAllComponents();
            boolean hasPermission = CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
            if (hasPermission) {
                BugService bugService = AppContextUtil.getSpringBean(BugService.class);
                SimpleBug bug = bugService.findById(beanItem.getId(), MyCollabUI.getAccountId());
                if (bug != null) {
                    if (BugStatus.Open.name().equals(beanItem.getStatus()) ||
                            BugStatus.ReOpen.name().equals(beanItem.getStatus())) {
                        MButton resolveBtn = new MButton(UserUIContext.getMessage(BugI18nEnum.BUTTON_RESOLVED), clickEvent -> {
                            setPopupVisible(false);
                            UI.getCurrent().addWindow(bindCloseWindow(new ResolvedInputWindow(bug)));
                        }).withStyleName(WebUIConstants.BUTTON_ACTION);
                        content.with(resolveBtn);
                    } else if (BugStatus.Verified.name().equals(beanItem.getStatus())) {
                        MButton reopenBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN), clickEvent -> {
                            setPopupVisible(false);
                            UI.getCurrent().addWindow(bindCloseWindow(new ReOpenWindow(bug)));
                        }).withStyleName(WebUIConstants.BUTTON_ACTION);
                        content.with(reopenBtn);
                    } else if (BugStatus.Resolved.name().equals(beanItem.getStatus())) {
                        MButton reopenBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN), clickEvent -> {
                            setPopupVisible(false);
                            UI.getCurrent().addWindow(bindCloseWindow(new ReOpenWindow(bug)));
                        }).withStyleName(WebUIConstants.BUTTON_ACTION);

                        MButton approveNCloseBtn = new MButton(UserUIContext.getMessage(BugI18nEnum.BUTTON_APPROVE_CLOSE), clickEvent -> {
                            setPopupVisible(false);
                            UI.getCurrent().addWindow(bindCloseWindow(new ApproveInputWindow(bug)));
                        }).withStyleName(WebUIConstants.BUTTON_ACTION);
                        content.with(reopenBtn, approveNCloseBtn);
                    } else if (BugStatus.Resolved.name().equals(beanItem.getStatus())) {
                        MButton reopenBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN),
                                clickEvent -> UI.getCurrent().addWindow(bindCloseWindow(new ReOpenWindow(bug))))
                                .withStyleName(WebUIConstants.BUTTON_ACTION);
                        content.with(reopenBtn);
                    }
                }
            } else {
                content.addComponent(new Label(UserUIContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
            }
        }

        @Override
        protected void doHide() {
            setMinimizedValueAsHTML(FontAwesome.INFO_CIRCLE.getHtml() + " " +
                    UserUIContext.getMessage(BugStatus.class, beanItem.getStatus()));
        }

        private Window bindCloseWindow(Window window) {
            window.addCloseListener(closeEvent -> refresh());
            return window;
        }

        private void refresh() {
            setMinimizedValueAsHTML(FontAwesome.INFO_CIRCLE.getHtml() + " " +
                    UserUIContext.getMessage(BugStatus.class, beanItem.getStatus()));
            markAsDirty();
        }
    }

    private static class TicketCommentsPopupView extends LazyPopupView {
        private ProjectTicket ticket;

        TicketCommentsPopupView(ProjectTicket ticket) {
            super("");
            this.ticket = ticket;
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + NumberUtils.zeroIfNull(ticket.getNumComments()));
        }

        @Override
        protected void doShow() {
            CommentDisplay commentDisplay = new CommentDisplay(ticket.getType(), CurrentProjectVariables.getProjectId());
            MVerticalLayout layout = getWrapContent().withStyleName(WebUIConstants.SCROLLABLE_CONTAINER);
            layout.removeAllComponents();
            layout.with(commentDisplay);
            commentDisplay.loadComments(ticket.getTypeId() + "");
        }

        @Override
        protected void doHide() {
            CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
            searchCriteria.setType(StringSearchField.and(ticket.getType()));
            searchCriteria.setTypeId(StringSearchField.and(ticket.getTypeId() + ""));
            CommentService commentService = AppContextUtil.getSpringBean(CommentService.class);
            int commentCount = commentService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + commentCount);
        }

        @Override
        protected String getConstraintWidth() {
            return "900px";
        }
    }

    @Override
    public MWindow createNewTicketWindow(Date date, Integer prjId, Integer milestoneId, boolean isIncludeMilestone) {
        return new NewTicketWindow(date, prjId, milestoneId, isIncludeMilestone);
    }

    private static class NewTicketWindow extends MWindow {
        private ComboBox typeSelection;
        private CssLayout formLayout;

        NewTicketWindow(Date date, final Integer prjId, final Integer milestoneId, boolean isIncludeMilestone) {
            super(UserUIContext.getMessage(TicketI18nEnum.NEW));
            MVerticalLayout content = new MVerticalLayout();
            withModal(true).withResizable(false).withCenter().withWidth("1200px").withContent(content);

            typeSelection = new ComboBox();
            typeSelection.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
            typeSelection.addItem(UserUIContext.getMessage(TaskI18nEnum.SINGLE));
            typeSelection.setItemIcon(UserUIContext.getMessage(TaskI18nEnum.SINGLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK));
            typeSelection.addItem(UserUIContext.getMessage(BugI18nEnum.SINGLE));
            typeSelection.setItemIcon(UserUIContext.getMessage(BugI18nEnum.SINGLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG));
            if (isIncludeMilestone) {
                typeSelection.addItem(UserUIContext.getMessage(MilestoneI18nEnum.SINGLE));
                typeSelection.setItemIcon(UserUIContext.getMessage(MilestoneI18nEnum.SINGLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE));
            }

            typeSelection.addItem(UserUIContext.getMessage(RiskI18nEnum.SINGLE));
            typeSelection.setItemIcon(UserUIContext.getMessage(RiskI18nEnum.SINGLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK));
            typeSelection.select(UserUIContext.getMessage(TaskI18nEnum.SINGLE));
            typeSelection.setNullSelectionAllowed(false);
            typeSelection.addValueChangeListener(valueChangeEvent -> doChange(date, prjId, milestoneId));

            GridFormLayoutHelper formLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);
            formLayoutHelper.addComponent(typeSelection, UserUIContext.getMessage(GenericI18Enum.FORM_TYPE), 0, 0);
            formLayout = new CssLayout();
            formLayout.setWidth("100%");
            content.with(formLayoutHelper.getLayout(), formLayout);
            doChange(date, prjId, milestoneId);
        }

        private void doChange(Date dateValue, final Integer prjId, final Integer milestoneId) {
            formLayout.removeAllComponents();
            String value = (String) typeSelection.getValue();
            if (UserUIContext.getMessage(TaskI18nEnum.SINGLE).equals(value)) {
                SimpleTask task = new SimpleTask();
                task.setProjectid(prjId);
                task.setMilestoneid(milestoneId);
                task.setSaccountid(MyCollabUI.getAccountId());
                task.setCreateduser(UserUIContext.getUsername());
                task.setStartdate(dateValue);
                TaskEditForm editForm = new TaskEditForm() {
                    @Override
                    protected void postExecution() {
                        close();
                    }
                };
                editForm.setBean(task);
                formLayout.addComponent(editForm);
            } else if (UserUIContext.getMessage(BugI18nEnum.SINGLE).equals(value)) {
                SimpleBug bug = new SimpleBug();
                bug.setProjectid(prjId);
                bug.setSaccountid(MyCollabUI.getAccountId());
                bug.setStartdate(dateValue);
                bug.setMilestoneid(milestoneId);
                bug.setCreateduser(UserUIContext.getUsername());
                BugEditForm editForm = new BugEditForm() {
                    @Override
                    protected void postExecution() {
                        close();
                    }
                };
                editForm.setBean(bug);
                formLayout.addComponent(editForm);
            } else if (UserUIContext.getMessage(RiskI18nEnum.SINGLE).equals(value)) {
                SimpleRisk risk = new SimpleRisk();
                risk.setSaccountid(MyCollabUI.getAccountId());
                risk.setProjectid(prjId);
                risk.setStartdate(dateValue);
                risk.setCreateduser(UserUIContext.getUsername());
                risk.setMilestoneid(milestoneId);
                RiskEditForm editForm = new RiskEditForm() {
                    @Override
                    protected void postExecution() {
                        close();
                    }
                };
                editForm.setBean(risk);
                formLayout.addComponent(editForm);
            }
        }
    }

    private static class TicketBillableHoursPopupField extends LazyPopupView {
        private TextField timeInput = new TextField();
        private PopupDateFieldExt dateField;
        private ProjectTicket ticket;
        private boolean isBillable;

        TicketBillableHoursPopupField(ProjectTicket ticket, boolean isBillable) {
            super("");
            this.ticket = ticket;
            this.isBillable = isBillable;
            if (isBillable) {
                this.setMinimizedValueAsHTML(FontAwesome.MONEY.getHtml() + " " + ticket.getBillableHours());
            } else {
                this.setMinimizedValueAsHTML(FontAwesome.GIFT.getHtml() + " " + ticket.getNonBillableHours());
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
                    timeLogging.setType(ticket.getType());
                    timeLogging.setTypeid(ticket.getTypeId());
                    timeLogging.setSaccountid(MyCollabUI.getAccountId());
                    timeLoggingService.saveWithSession(timeLogging, UserUIContext.getUsername());
                    EventBusFactory.getInstance().post(new ProjectEvent.TimeLoggingChangedEvent(TicketBillableHoursPopupField.this));

                    // load hours again
                    ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
                    searchCriteria.setIsBillable(new BooleanSearchField(isBillable));
                    searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                    searchCriteria.setType(StringSearchField.and(ticket.getType()));
                    searchCriteria.setTypeId(new NumberSearchField(ticket.getTypeId()));
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
