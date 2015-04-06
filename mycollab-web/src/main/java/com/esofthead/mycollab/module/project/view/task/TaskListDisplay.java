package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.configuration.StorageManager;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.OptionPopupContent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.maddon.layouts.MHorizontalLayout;

import java.util.UUID;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class TaskListDisplay extends DefaultBeanPagedList<ProjectTaskService, TaskSearchCriteria, SimpleTask> {
    public TaskListDisplay() {
        super(ApplicationContextUtil.getSpringBean(ProjectTaskService.class), new TaskRowDisplayHandler(), Integer.MAX_VALUE);
        this.setStyleName("tasklist");
    }

    public static class TaskRowDisplayHandler implements RowDisplayHandler<SimpleTask> {
        private Label taskLinkLbl;

        @Override
        public Component generateRow(SimpleTask task, int rowIndex) {
            MHorizontalLayout layout = new MHorizontalLayout().withSpacing(false).withMargin(true).withWidth("100%").withStyleName("taskrow");
            layout.with(createTaskActionControl(task));

            taskLinkLbl = new Label(buildTaskLink(task), ContentMode.HTML);
            layout.with(taskLinkLbl).expand(taskLinkLbl);
            if (task.isCompleted()) {
                taskLinkLbl.addStyleName("completed");
            } else if (task.isOverdue()) {
                taskLinkLbl.addStyleName("overdue");
            } else if (task.isPending()) {
                taskLinkLbl.addStyleName("pending");
            }
            taskLinkLbl.addStyleName("wordWrap");
            return layout;
        }

        private String buildTaskLink(SimpleTask task) {
            String linkName = String.format("[%s-%d] %s", CurrentProjectVariables.getShortName(), task.getTaskkey(), task
                    .getTaskname());
            A taskLink = new A().setHref(ProjectLinkBuilder.generateTaskPreviewFullLink(task.getTaskkey(),
                    CurrentProjectVariables.getShortName())).appendText(linkName).setStyle("display:inline");

            String uid = UUID.randomUUID().toString();
            taskLink.setAttribute("onmouseover", TooltipHelper.buildItemHtmlTooltip(uid, ProjectTypeConstants.TASK, task.getId() + ""));

            String avatarLink = StorageManager.getAvatarLink(task.getAssignUserAvatarId(), 16);
            Img avatarImg = new Img(task.getAssignUserFullName(), avatarLink).setTitle(task.getAssignUserFullName());

            if (task.getDeadline() != null) {
                Div deadline = new Div().appendChild(new Text(String.format(" - %s: %s", AppContext.getMessage
                        (TaskI18nEnum.FORM_DEADLINE), AppContext.formatPrettyTime(task.getDeadline()))))
                        .setStyle("color:gray; display:inline").setTitle(AppContext.formatDate(task.getDeadline()));

                return new DivLessFormatter().appendChild(avatarImg, DivLessFormatter.EMPTY_SPACE(), taskLink, deadline,
                        DivLessFormatter.EMPTY_SPACE(),
                        TooltipHelper.buildDivTooltipEnable(uid)).write();
            } else {
                return new DivLessFormatter().appendChild(avatarImg, DivLessFormatter.EMPTY_SPACE(), taskLink, DivLessFormatter
                                .EMPTY_SPACE(),
                        TooltipHelper.buildDivTooltipEnable(uid)).write();
            }
        }

        private void closeTask() {

        }

        private void reOpenTask() {

        }

        private void pendingTask() {

        }

        private void deleteTask() {

        }

        private PopupButton createTaskActionControl(final SimpleTask task) {
            PopupButton taskSettingPopupBtn = new PopupButton();
            taskSettingPopupBtn.setIcon(FontAwesome.COGS);

            taskSettingPopupBtn.addStyleName("noDefaultIcon");
            taskSettingPopupBtn.addStyleName("button-icon-only");

            OptionPopupContent filterBtnLayout = new OptionPopupContent().withWidth("100px");

            Button editButton = new Button(AppContext
                    .getMessage(GenericI18Enum.BUTTON_EDIT),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            EventBusFactory.getInstance().post(
                                    new TaskEvent.GotoEdit(
                                            TaskRowDisplayHandler.this, task));
                        }
                    });
            editButton.setEnabled(CurrentProjectVariables
                    .canWrite(ProjectRolePermissionCollections.TASKS));
            editButton.setIcon(FontAwesome.EDIT);
            filterBtnLayout.addOption(editButton);

            if ((task.getPercentagecomplete() != null && task
                    .getPercentagecomplete() != 100)
                    || task.getPercentagecomplete() == null) {
                Button closeBtn = new Button(AppContext
                        .getMessage(GenericI18Enum.BUTTON_CLOSE),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                task.setStatus(OptionI18nEnum.StatusI18nEnum.Closed.name());
                                task.setPercentagecomplete(100d);

                                ProjectTaskService projectTaskService = ApplicationContextUtil
                                        .getSpringBean(ProjectTaskService.class);
                                projectTaskService
                                        .updateSelectiveWithSession(task,
                                                AppContext.getUsername());

                                closeTask();
                            }
                        });
                closeBtn.setIcon(FontAwesome.CHECK_CIRCLE_O);
                closeBtn.setEnabled(CurrentProjectVariables
                        .canWrite(ProjectRolePermissionCollections.TASKS));
                filterBtnLayout.addOption(closeBtn);
            } else {
                Button reOpenBtn = new Button("ReOpen",
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                task.setStatus(OptionI18nEnum.StatusI18nEnum.Open.name());
                                task.setPercentagecomplete(0d);

                                ProjectTaskService projectTaskService = ApplicationContextUtil
                                        .getSpringBean(ProjectTaskService.class);
                                projectTaskService
                                        .updateSelectiveWithSession(task,
                                                AppContext.getUsername());
                                reOpenTask();
                            }
                        });
                reOpenBtn.setIcon(FontAwesome.UNLOCK);
                reOpenBtn.setEnabled(CurrentProjectVariables
                        .canWrite(ProjectRolePermissionCollections.TASKS));
                filterBtnLayout.addOption(reOpenBtn);
            }

            if (!"Pending".equals(task.getStatus())) {
                if (!"Closed".equals(task.getStatus())) {
                    Button pendingBtn = new Button("Pending",
                            new Button.ClickListener() {
                                private static final long serialVersionUID = 1L;

                                @Override
                                public void buttonClick(Button.ClickEvent event) {
                                    task.setStatus("Pending");
                                    task.setPercentagecomplete(0d);

                                    ProjectTaskService projectTaskService = ApplicationContextUtil
                                            .getSpringBean(ProjectTaskService.class);
                                    projectTaskService
                                            .updateSelectiveWithSession(
                                                    task, AppContext
                                                            .getUsername());
                                    pendingTask();
                                }
                            });
                    pendingBtn.setIcon(FontAwesome.HDD_O);
                    pendingBtn.setEnabled(CurrentProjectVariables
                            .canWrite(ProjectRolePermissionCollections.TASKS));
                    filterBtnLayout.addOption(pendingBtn);
                }
            } else {
                Button reOpenBtn = new Button("ReOpen",
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                task.setStatus("Open");
                                task.setPercentagecomplete(0d);

                                ProjectTaskService projectTaskService = ApplicationContextUtil
                                        .getSpringBean(ProjectTaskService.class);
                                projectTaskService
                                        .updateSelectiveWithSession(task,
                                                AppContext.getUsername());

                                reOpenTask();
                            }
                        });
                reOpenBtn.setIcon(FontAwesome.UNLOCK);
                reOpenBtn.setEnabled(CurrentProjectVariables
                        .canWrite(ProjectRolePermissionCollections.TASKS));
                filterBtnLayout.addOption(reOpenBtn);
            }

            Button deleteBtn = new Button(AppContext
                    .getMessage(GenericI18Enum.BUTTON_DELETE),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            ConfirmDialogExt.show(
                                    UI.getCurrent(),
                                    AppContext
                                            .getMessage(
                                                    GenericI18Enum.DIALOG_DELETE_TITLE,
                                                    SiteConfiguration
                                                            .getSiteName()),
                                    AppContext
                                            .getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                                    AppContext
                                            .getMessage(GenericI18Enum.BUTTON_YES),
                                    AppContext
                                            .getMessage(GenericI18Enum.BUTTON_NO),
                                    new ConfirmDialog.Listener() {
                                        private static final long serialVersionUID = 1L;

                                        @Override
                                        public void onClose(
                                                ConfirmDialog dialog) {
                                            if (dialog.isConfirmed()) {
                                                ProjectTaskService projectTaskService = ApplicationContextUtil
                                                        .getSpringBean(ProjectTaskService.class);
                                                projectTaskService.removeWithSession(
                                                        task.getId(),
                                                        AppContext.getUsername(),
                                                        AppContext.getAccountId());
                                                deleteTask();
                                            }
                                        }
                                    });
                        }
                    });
            deleteBtn.setIcon(FontAwesome.TRASH_O);
            deleteBtn.setEnabled(CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.TASKS));
            filterBtnLayout.addOption(deleteBtn);

            taskSettingPopupBtn.setContent(filterBtnLayout);
            return taskSettingPopupBtn;
        }
    }
}
