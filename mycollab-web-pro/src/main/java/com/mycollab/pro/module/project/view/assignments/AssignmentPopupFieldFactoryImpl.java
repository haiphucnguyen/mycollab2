package com.mycollab.pro.module.project.view.assignments;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Span;
import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.mycollab.common.i18n.FollowerI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum;
import com.mycollab.common.service.CommentService;
import com.mycollab.common.service.MonitorItemService;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.NumberUtils;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectAssignment;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.i18n.BugI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.CommentDisplay;
import com.mycollab.module.project.ui.components.PriorityComboBox;
import com.mycollab.module.project.view.assignments.AssignmentPopupFieldFactory;
import com.mycollab.module.project.view.bug.ApproveInputWindow;
import com.mycollab.module.project.view.bug.ReOpenWindow;
import com.mycollab.module.project.view.bug.ResolvedInputWindow;
import com.mycollab.module.project.view.task.components.TaskStatusComboBox;
import com.mycollab.module.tracker.domain.BugWithBLOBs;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.pro.module.project.ui.components.WatchersMultiSelection;
import com.mycollab.pro.vaadin.web.ui.field.PopupBeanFieldBuilder;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.mycollab.vaadin.web.ui.LazyPopupView;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@ViewComponent
public class AssignmentPopupFieldFactoryImpl implements AssignmentPopupFieldFactory {

    private static boolean isPermission(ProjectAssignment bean) {
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

    private static void save(ProjectAssignment bean) {
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

    private static Task buildTask(ProjectAssignment bean) {
        Task task = new Task();
        task.setId(bean.getTypeId());
        task.setName(bean.getName());
        task.setStartdate(bean.getEndDate());
        task.setEnddate(bean.getEndDate());
        task.setDeadline(bean.getDueDate());
        task.setStatus(bean.getStatus());
        task.setSaccountid(bean.getsAccountId());
        return task;
    }

    private static SimpleBug buildBug(ProjectAssignment bean) {
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

    private static Risk buildRisk(ProjectAssignment bean) {
        Risk risk = new Risk();
        risk.setId(bean.getTypeId());
        risk.setName(bean.getName());
        risk.setStartdate(bean.getEndDate());
        risk.setEnddate(bean.getEndDate());
        risk.setDatedue(bean.getDueDate());
        risk.setStatus(bean.getStatus());
        risk.setSaccountid(bean.getsAccountId());
        return risk;
    }

    @Override
    public AbstractComponent createStartDatePopupField(ProjectAssignment assignment) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder<ProjectAssignment>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (assignment.getStartDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_FORWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(), UserUIContext.formatDate(assignment.getStartDate()));
                }

            }

            @Override
            public boolean isPermission() {
                return AssignmentPopupFieldFactoryImpl.isPermission(bean);
            }

            @Override
            public void save() {
                AssignmentPopupFieldFactoryImpl.save(bean);
            }
        };
        builder.withBean(assignment).withBindProperty("startDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .withField(new PopupDateFieldExt())
                .withValue(assignment.getStartDate());
        return builder.build();
    }

    @Override
    public AbstractComponent createEndDatePopupField(ProjectAssignment assignment) {
        PopupBeanFieldBuilder<ProjectAssignment> builder = new PopupBeanFieldBuilder<ProjectAssignment>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (assignment.getEndDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_BACKWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(), UserUIContext.formatDate(assignment.getEndDate()));
                }
            }

            @Override
            public boolean isPermission() {
                return AssignmentPopupFieldFactoryImpl.isPermission(bean);
            }

            @Override
            public void save() {
                AssignmentPopupFieldFactoryImpl.save(bean);
            }
        };
        builder.withBean(assignment).withBindProperty("endDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .withField(new PopupDateFieldExt())
                .withValue(assignment.getEndDate());
        return builder.build();
    }

    @Override
    public AbstractComponent createDueDatePopupField(ProjectAssignment assignment) {
        PopupBeanFieldBuilder<ProjectAssignment> builder = new PopupBeanFieldBuilder<ProjectAssignment>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (assignment.getDueDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(FontAwesome.CLOCK_O.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(), UserUIContext.formatDate(assignment
                            .getDueDate()));
                }
            }

            @Override
            public boolean isPermission() {
                return AssignmentPopupFieldFactoryImpl.isPermission(bean);
            }

            @Override
            public void save() {
                AssignmentPopupFieldFactoryImpl.save(bean);
            }
        };
        builder.withBean(assignment).withBindProperty("dueDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_DUE_DATE))
                .withField(new PopupDateFieldExt()).withValue(assignment.getDueDate());
        return builder.build();
    }

    @Override
    public AbstractComponent createPriorityPopupField(ProjectAssignment assignment) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getTaskPriorityHtml(assignment.getPriority()) + " " + UserUIContext.getMessage(Priority.class, assignment.getPriority());
            }

            @Override
            protected String generateDescription() {
                return assignment.getPriority();
            }

            @Override
            public boolean isPermission() {
                return AssignmentPopupFieldFactoryImpl.isPermission(assignment);
            }

            @Override
            public void save() {
                AssignmentPopupFieldFactoryImpl.save(assignment);
            }
        };
        builder.withBean(assignment).withBindProperty("priority").withDescription(assignment.getPriority())
                .withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY))
                .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY_HELP))
                .withField(new PriorityComboBox())
                .withService(AppContextUtil.getSpringBean(ProjectTaskService.class)).withValue(assignment.getPriority());
        return builder.build();
    }

    @Override
    public AbstractComponent createBillableHoursPopupField(ProjectAssignment task) {
        return null;
    }

    @Override
    public AbstractComponent createNonBillableHoursPopupField(ProjectAssignment task) {
        return null;
    }

    @Override
    public AbstractComponent createFollowersPopupField(ProjectAssignment assignment) {
        return new AssignmentFollowersPopupView(assignment);
    }

    @Override
    public AbstractComponent createStatusPopupField(ProjectAssignment assignment) {
        if (assignment.isTask()) {
            PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
                @Override
                protected String generateSmallContentAsHtml() {
                    if (assignment.getStatus() == null) {
                        Div divHint = new Div().setCSSClass("nonValue");
                        divHint.appendText(FontAwesome.INFO_CIRCLE.getHtml());
                        divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                                .setCSSClass("hide"));
                        return divHint.write();
                    } else {
                        return FontAwesome.INFO_CIRCLE.getHtml() + " " + StringUtils.trim(assignment.getStatus(), 20, true);
                    }
                }
            };
            builder.withBean(buildTask(assignment)).withBindProperty("status").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_STATUS))
                    .withDescription(UserUIContext.getMessage(TaskI18nEnum.FORM_STATUS_HELP))
                    .withField(new TaskStatusComboBox()).withService(AppContextUtil.getSpringBean(ProjectTaskService.class)).withValue(assignment.getStatus())
                    .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
            return builder.build();
        } else if (assignment.isBug()) {
            return new BugStatusPopupView(buildBug(assignment));
        } else if (assignment.isRisk()) {
            PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
                @Override
                protected String generateSmallContentAsHtml() {
                    if (assignment.getStatus() == null) {
                        Div divHint = new Div().setCSSClass("nonValue");
                        divHint.appendText(FontAwesome.INFO_CIRCLE.getHtml());
                        divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                                .setCSSClass("hide"));
                        return divHint.write();
                    } else {
                        return FontAwesome.INFO_CIRCLE.getHtml() + " " + StringUtils.trim(assignment.getStatus(), 20, true);
                    }
                }
            };
            builder.withBean(buildRisk(assignment)).withBindProperty("status").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_STATUS))
                    .withField(new I18nValueComboBox(false, OptionI18nEnum.StatusI18nEnum.Open, OptionI18nEnum.StatusI18nEnum.Closed))
                    .withService(AppContextUtil.getSpringBean(RiskService.class)).withValue(assignment.getStatus())
                    .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));
            return builder.build();
        } else {
            throw new MyCollabException("Not support");
        }
    }

    @Override
    public AbstractComponent createCommentsPopupField(ProjectAssignment task) {
        TaskCommentsPopupView view = new TaskCommentsPopupView(task);
        view.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_ADD_COMMENT));
        return view;
    }

    private static class AssignmentFollowersPopupView extends LazyPopupView {
        private ProjectAssignment assignment;
        private WatchersMultiSelection watchersMultiSelection;

        AssignmentFollowersPopupView(ProjectAssignment assignment) {
            super("");
            this.assignment = assignment;
            this.setDescription(UserUIContext.getMessage(FollowerI18nEnum.FOLLOWER_EXPLAIN_HELP));
            this.setMinimizedValueAsHTML(FontAwesome.EYE.getHtml() + " " + NumberUtils.zeroIfNull(assignment.getNumFollowers()));
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            watchersMultiSelection = new WatchersMultiSelection(assignment.getType(), assignment.getTypeId(),
                    isPermission(assignment));
            layout.with(new ELabel(UserUIContext.getMessage(FollowerI18nEnum.OPT_SUB_INFO_WATCHERS))
                    .withStyleName(ValoTheme.LABEL_H3), watchersMultiSelection);
        }

        @Override
        protected void doHide() {
            MonitorItemService monitorItemService = AppContextUtil.getSpringBean(MonitorItemService.class);

            List<MonitorItem> items = watchersMultiSelection.getUnsavedItems();
            monitorItemService.saveMonitorItems(items);

            MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
            searchCriteria.setType(StringSearchField.and(assignment.getType()));
            searchCriteria.setTypeId(new NumberSearchField(assignment.getTypeId()));
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

    private static class TaskCommentsPopupView extends LazyPopupView {
        private ProjectAssignment assignment;

        TaskCommentsPopupView(ProjectAssignment assignment) {
            super("");
            this.assignment = assignment;
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + NumberUtils.zeroIfNull(assignment.getNumComments()));
        }

        @Override
        protected void doShow() {
            CommentDisplay commentDisplay = new CommentDisplay(assignment.getType(), CurrentProjectVariables.getProjectId());
            MVerticalLayout layout = getWrapContent().withStyleName(WebUIConstants.SCROLLABLE_CONTAINER);
            layout.removeAllComponents();
            layout.with(commentDisplay);
            commentDisplay.loadComments(assignment.getTypeId() + "");
        }

        @Override
        protected void doHide() {
            CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
            searchCriteria.setType(StringSearchField.and(assignment.getType()));
            searchCriteria.setTypeId(StringSearchField.and(assignment.getTypeId() + ""));
            CommentService commentService = AppContextUtil.getSpringBean(CommentService.class);
            int commentCount = commentService.getTotalCount(searchCriteria);
            this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + commentCount);
        }

        @Override
        protected String getConstraintWidth() {
            return "900px";
        }
    }
}
