package com.mycollab.module.project.view.task;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Span;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.event.TaskEvent;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.ui.components.BlockRowRender;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.AbstractToggleSummaryField;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addons.CssCheckBox;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import static com.mycollab.vaadin.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd
 * @since 5.2.3
 */
public class ToggleTaskSummaryField extends AbstractToggleSummaryField {
    private boolean isRead = true;
    private SimpleTask task;
    private int maxLength;
    private CssCheckBox toggleStatusSelect;

    public ToggleTaskSummaryField(final SimpleTask task, boolean toggleStatusSupport) {
        this(task, Integer.MAX_VALUE, toggleStatusSupport, false);
    }

    public ToggleTaskSummaryField(final SimpleTask task, int maxLength, boolean toggleStatusSupport, boolean canRemove) {
        this.setWidth("100%");
        this.maxLength = maxLength;
        this.task = task;
        titleLinkLbl = ELabel.html(buildTaskLink()).withWidthUndefined().withStyleName(UIConstants.LABEL_WORD_WRAP);

        if (toggleStatusSupport && CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INSTANCE.getTASKS())) {
            toggleStatusSelect = new CssCheckBox();
            toggleStatusSelect.setSimpleMode(true);
            toggleStatusSelect.setValue(task.isCompleted());
            displayTooltip();
            toggleStatusSelect.addValueChangeListener(valueChangeEvent -> {
                if (task.isCompleted()) {
                    task.setStatus(StatusI18nEnum.Open.name());
                    task.setPercentagecomplete(0d);
                    titleLinkLbl.removeStyleName(WebThemes.LINK_COMPLETED);
                } else {
                    task.setStatus(StatusI18nEnum.Closed.name());
                    task.setPercentagecomplete(100d);
                    titleLinkLbl.addStyleName(WebThemes.LINK_COMPLETED);
                }
                displayTooltip();
                ProjectTaskService projectTaskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                projectTaskService.updateWithSession(task, UserUIContext.getUsername());

                if (StatusI18nEnum.Closed.name().equals(task.getStatus())) {
                    Integer countOfOpenSubTasks = projectTaskService.getCountOfOpenSubTasks(task.getId());
                    if (countOfOpenSubTasks > 0) {
                        ConfirmDialogExt.show(UI.getCurrent(),
                                UserUIContext.getMessage(GenericI18Enum.OPT_QUESTION, AppUI.getSiteName()),
                                UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_CLOSE_SUB_ASSIGNMENTS),
                                UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                                UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                                confirmDialog -> {
                                    if (confirmDialog.isConfirmed()) {
                                        projectTaskService.massUpdateTaskStatuses(task.getId(), StatusI18nEnum.Closed.name(),
                                                AppUI.getAccountId());
                                    }
                                });
                    }
                }
            });
            this.addComponent(toggleStatusSelect);
            this.addComponent(ELabel.EMPTY_SPACE());
        }

        this.addComponent(titleLinkLbl);
        buttonControls = new MHorizontalLayout().withMargin(new MarginInfo(false, false, false, true)).withStyleName("toggle");
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INSTANCE.getTASKS())) {
            this.addStyleName("editable-field");

            MButton instantEditBtn = new MButton("", clickEvent -> {
                if (isRead) {
                    ToggleTaskSummaryField.this.removeComponent(titleLinkLbl);
                    ToggleTaskSummaryField.this.removeComponent(buttonControls);
                    final TextField editField = new TextField();
                    editField.setValue(task.getName());
                    editField.setWidth("100%");
                    editField.focus();
                    ToggleTaskSummaryField.this.addComponent(editField);
                    ToggleTaskSummaryField.this.removeStyleName("editable-field");
                    editField.addValueChangeListener(valueChangeEvent -> updateFieldValue(editField));
                    editField.addBlurListener(blurEvent -> updateFieldValue(editField));
                    isRead = !isRead;
                }
            }).withIcon(FontAwesome.EDIT).withStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
            instantEditBtn.setDescription(UserUIContext.getMessage(TaskI18nEnum.OPT_EDIT_TASK_NAME));
            buttonControls.with(instantEditBtn);
        }

        if (canRemove && CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.INSTANCE.getTASKS())) {
            MButton removeBtn = new MButton("", clickEvent -> {
                ConfirmDialogExt.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                AppContextUtil.getSpringBean(ProjectTaskService.class).removeWithSession(task,
                                        UserUIContext.getUsername(), AppUI.getAccountId());
                                BlockRowRender rowRenderer = UIUtils.getRoot(ToggleTaskSummaryField.this, BlockRowRender.class);
                                if (rowRenderer != null) {
                                    rowRenderer.selfRemoved();
                                }
                                EventBusFactory.getInstance().post(new TaskEvent.TaskDeleted(this, task.getId()));
                            }
                        });
            }).withIcon(FontAwesome.TRASH).withStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
            buttonControls.with(removeBtn);
        }
        if (buttonControls.getComponentCount() > 0) {
            this.addComponent(buttonControls);
        }
    }

    private void displayTooltip() {
        if (task.isCompleted()) {
            toggleStatusSelect.setDescription(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_MARK_INCOMPLETE));
        } else {
            toggleStatusSelect.setDescription(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_MARK_COMPLETE));
        }
    }

    private void updateFieldValue(TextField editField) {
        removeComponent(editField);
        addComponent(titleLinkLbl);
        addComponent(buttonControls);
        addStyleName("editable-field");
        String newValue = editField.getValue();
        if (StringUtils.isNotBlank(newValue) && !newValue.equals(task.getName())) {
            task.setName(newValue);
            titleLinkLbl.setValue(buildTaskLink());
            ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
            taskService.updateSelectiveWithSession(BeanUtility.deepClone(task), UserUIContext.getUsername());
        }

        isRead = !isRead;
    }

    private String buildTaskLink() {
        String linkName = StringUtils.trim(task.getName(), maxLength, true);
        A taskLink = new A().setId("tag" + TOOLTIP_ID).setHref(ProjectLinkBuilder.generateTaskPreviewFullLink(task.getTaskkey(),
                CurrentProjectVariables.getShortName())).appendText(linkName).setStyle("display:inline");
        Div resultDiv = new DivLessFormatter().appendChild(taskLink);
        if (task.isOverdue()) {
            taskLink.setCSSClass("overdue");
            resultDiv.appendChild(new Span().setCSSClass(UIConstants.META_INFO).appendText(" - " + UserUIContext
                    .getMessage(ProjectCommonI18nEnum.OPT_DUE_IN, UserUIContext.formatDuration(task.getDuedate()))));
        } else if (task.isCompleted()) {
            taskLink.setCSSClass("completed");
        }

        taskLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(ProjectTypeConstants.INSTANCE.getTASK(), task.getId() + ""));
        taskLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());

        return resultDiv.write();
    }

    public void updateLabel() {
        titleLinkLbl.setValue(buildTaskLink());
    }

    public void closeTask() {
        titleLinkLbl.removeStyleName("overdue");
        titleLinkLbl.addStyleName("completed");
    }

    public void overdueTask() {
        titleLinkLbl.removeStyleName("completed");
        titleLinkLbl.addStyleName("overdue");
    }

    public void reOpenTask() {
        titleLinkLbl.removeStyleName("overdue completed");
    }
}
