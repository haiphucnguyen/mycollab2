package com.mycollab.pro.module.project.view.service;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.mycollab.common.i18n.FollowerI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum;
import com.mycollab.common.service.CommentService;
import com.mycollab.common.service.MonitorItemService;
import com.mycollab.configuration.StorageFactory;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.NumberUtils;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.*;
import com.mycollab.module.project.i18n.*;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority;
import com.mycollab.module.project.service.ProjectTaskService;
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
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@Service
public class TicketComponentFactoryImpl implements TicketComponentFactory {

    private static boolean isPermission(ProjectTicket bean) {
        if (bean.isTask()) {
            return CurrentProjectVariables.canWrite(ProjectTypeConstants.TASK);
        } else if (bean.isBug()) {
            return CurrentProjectVariables.canWrite(ProjectTypeConstants.BUG);
        } else if (bean.isRisk()) {
            return CurrentProjectVariables.canWrite(ProjectTypeConstants.RISK);
        } else {
            return false;
        }
    }

    private static void save(ProjectTicket bean) {
        if (bean.isTask()) {
            Task task = buildTask(bean);
            AppContextUtil.getSpringBean(ProjectTaskService.class).updateSelectiveWithSession(task, UserUIContext.getUsername());
        } else if (bean.isBug()) {
            BugWithBLOBs bug = buildBug(bean);
            AppContextUtil.getSpringBean(BugService.class).updateSelectiveWithSession(bug, UserUIContext.getUsername());
        } else if (bean.isRisk()) {
            Risk risk = buildRisk(bean);
            AppContextUtil.getSpringBean(RiskService.class).updateSelectiveWithSession(risk, UserUIContext.getUsername());
        }
    }

    private static Task buildTask(ProjectTicket bean) {
        Task task = new Task();
        task.setId(bean.getTypeId());
        task.setName(bean.getName());
        task.setStartdate(bean.getEndDate());
        task.setEnddate(bean.getEndDate());
        task.setDuedate(bean.getDueDate());
        task.setStatus(bean.getStatus());
        task.setSaccountid(bean.getsAccountId());
        return task;
    }

    private static SimpleBug buildBug(ProjectTicket bean) {
        SimpleBug bug = new SimpleBug();
        bug.setId(bean.getTypeId());
        bug.setName(bean.getName());
        bug.setStartdate(bean.getEndDate());
        bug.setEnddate(bean.getEndDate());
        bug.setDuedate(bean.getDueDate());
        bug.setStatus(bean.getStatus());
        bug.setSaccountid(bean.getsAccountId());
        return bug;
    }

    private static Risk buildRisk(ProjectTicket bean) {
        Risk risk = new Risk();
        risk.setId(bean.getTypeId());
        risk.setName(bean.getName());
        risk.setStartdate(bean.getEndDate());
        risk.setEnddate(bean.getEndDate());
        risk.setDuedate(bean.getDueDate());
        risk.setStatus(bean.getStatus());
        risk.setSaccountid(bean.getsAccountId());
        return risk;
    }

    @Override
    public AbstractComponent createStartDatePopupField(ProjectTicket ticket) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder<ProjectTicket>() {
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
                return TicketComponentFactoryImpl.isPermission(bean);
            }

            @Override
            public void save() {
                TicketComponentFactoryImpl.save(bean);
            }
        };
        builder.withBean(ticket).withBindProperty("startDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .withField(new PopupDateFieldExt())
                .withValue(ticket.getStartDate());
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
                return TicketComponentFactoryImpl.isPermission(bean);
            }

            @Override
            public void save() {
                TicketComponentFactoryImpl.save(bean);
            }
        };
        builder.withBean(ticket).withBindProperty("endDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .withField(new PopupDateFieldExt())
                .withValue(ticket.getEndDate());
        return builder.build();
    }

    @Override
    public AbstractComponent createDueDatePopupField(ProjectTicket ticket) {
        PopupBeanFieldBuilder<ProjectTicket> builder = new PopupBeanFieldBuilder<ProjectTicket>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (ticket.getDueDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(FontAwesome.CLOCK_O.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(), UserUIContext.formatDate(ticket.getDueDate()));
                }
            }

            @Override
            public boolean isPermission() {
                return TicketComponentFactoryImpl.isPermission(bean);
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
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getTaskPriorityHtml(ticket.getPriority()) + " " + UserUIContext.getMessage(Priority.class, ticket.getPriority());
            }

            @Override
            protected String generateDescription() {
                return ticket.getPriority();
            }

            @Override
            public boolean isPermission() {
                return TicketComponentFactoryImpl.isPermission(ticket);
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
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                String avatarLink = StorageFactory.getAvatarPath(ticket.getAssignUserAvatarId(), 16);
                Img img = new Img(ticket.getAssignUserFullName(), avatarLink).setTitle(ticket.getAssignUserFullName())
                        .setCSSClass(UIConstants.CIRCLE_BOX);
                return img.write();
            }

            @Override
            public boolean isPermission() {
                return TicketComponentFactoryImpl.isPermission(ticket);
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
        builder.withBean(ticket).withBindProperty("assignuser").withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE))
                .withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)).withField(new ProjectMemberSelectionField())
                .withValue(ticket.getAssignUser());
        return builder.build();
    }

    @Override
    public AbstractComponent createBillableHoursPopupField(ProjectTicket task) {
        return null;
    }

    @Override
    public AbstractComponent createNonBillableHoursPopupField(ProjectTicket task) {
        return null;
    }

    @Override
    public AbstractComponent createFollowersPopupField(ProjectTicket ticket) {
        return new TicketFollowersPopupView(ticket);
    }

    @Override
    public AbstractComponent createStatusPopupField(ProjectTicket ticket) {
        if (ticket.isTask()) {
            PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
                @Override
                protected String generateSmallContentAsHtml() {
                    if (ticket.getStatus() == null) {
                        Div divHint = new Div().setCSSClass("nonValue");
                        divHint.appendText(FontAwesome.INFO_CIRCLE.getHtml());
                        divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                                .setCSSClass("hide"));
                        return divHint.write();
                    } else {
                        return FontAwesome.INFO_CIRCLE.getHtml() + " " + StringUtils.trim(ticket.getStatus(), 20, true);
                    }
                }
            };
            builder.withBean(buildTask(ticket)).withBindProperty("status").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_STATUS))
                    .withDescription(UserUIContext.getMessage(TaskI18nEnum.FORM_STATUS_HELP))
                    .withField(new TaskStatusComboBox()).withService(AppContextUtil.getSpringBean(ProjectTaskService.class)).withValue(ticket.getStatus())
                    .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
            return builder.build();
        } else if (ticket.isBug()) {
            return new BugStatusPopupView(buildBug(ticket));
        } else if (ticket.isRisk()) {
            PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
                @Override
                protected String generateSmallContentAsHtml() {
                    if (ticket.getStatus() == null) {
                        Div divHint = new Div().setCSSClass("nonValue");
                        divHint.appendText(FontAwesome.INFO_CIRCLE.getHtml());
                        divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                                .setCSSClass("hide"));
                        return divHint.write();
                    } else {
                        return FontAwesome.INFO_CIRCLE.getHtml() + " " + StringUtils.trim(ticket.getStatus(), 20, true);
                    }
                }
            };
            builder.withBean(buildRisk(ticket)).withBindProperty("status").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_STATUS))
                    .withField(new I18nValueComboBox(false, OptionI18nEnum.StatusI18nEnum.Open, OptionI18nEnum.StatusI18nEnum.Closed))
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
                    isPermission(ticket));
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
        private SimpleBug beanItem;

        BugStatusPopupView(SimpleBug bug) {
            super(FontAwesome.INFO_CIRCLE.getHtml() + " " + UserUIContext.getMessage(BugStatus.class, bug.getStatus()));
            this.beanItem = bug;
            this.setDescription(UserUIContext.getMessage(BugI18nEnum.FORM_STATUS_HELP));
        }

        @Override
        protected void doShow() {
            MVerticalLayout content = getWrapContent();
            content.removeAllComponents();
            boolean hasPermission = CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
            if (BugStatus.Open.name().equals(beanItem.getStatus()) ||
                    BugStatus.ReOpen.name().equals(beanItem.getStatus())) {
                MButton resolveBtn = new MButton(UserUIContext.getMessage(BugI18nEnum.BUTTON_RESOLVED), clickEvent -> {
                    setPopupVisible(false);
                    UI.getCurrent().addWindow(bindCloseWindow(new ResolvedInputWindow(beanItem)));
                }).withStyleName(WebUIConstants.BUTTON_ACTION);
                resolveBtn.setVisible(hasPermission);
                content.with(resolveBtn);
            } else if (BugStatus.Verified.name().equals(beanItem.getStatus())) {
                MButton reopenBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN), clickEvent -> {
                    setPopupVisible(false);
                    UI.getCurrent().addWindow(bindCloseWindow(new ReOpenWindow(beanItem)));
                }).withStyleName(WebUIConstants.BUTTON_ACTION);
                reopenBtn.setVisible(hasPermission);
                content.with(reopenBtn);
            } else if (BugStatus.Resolved.name().equals(beanItem.getStatus())) {
                MButton reopenBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN), clickEvent -> {
                    setPopupVisible(false);
                    UI.getCurrent().addWindow(bindCloseWindow(new ReOpenWindow(beanItem)));
                }).withStyleName(WebUIConstants.BUTTON_ACTION);
                reopenBtn.setVisible(hasPermission);

                MButton approveNCloseBtn = new MButton(UserUIContext.getMessage(BugI18nEnum.BUTTON_APPROVE_CLOSE), clickEvent -> {
                    setPopupVisible(false);
                    UI.getCurrent().addWindow(bindCloseWindow(new ApproveInputWindow(beanItem)));
                }).withStyleName(WebUIConstants.BUTTON_ACTION);
                approveNCloseBtn.setVisible(hasPermission);
                content.with(reopenBtn, approveNCloseBtn);
            } else if (BugStatus.Resolved.name().equals(beanItem.getStatus())) {
                MButton reopenBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN),
                        clickEvent -> UI.getCurrent().addWindow(bindCloseWindow(new ReOpenWindow(beanItem))))
                        .withStyleName(WebUIConstants.BUTTON_ACTION);
                reopenBtn.setVisible(hasPermission);
                content.with(reopenBtn);
            }
            if (!hasPermission) {
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
}
