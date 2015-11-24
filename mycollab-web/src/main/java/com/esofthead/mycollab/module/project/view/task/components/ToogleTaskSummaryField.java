package com.esofthead.mycollab.module.project.view.task.components;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.LayoutEvents;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import java.util.UUID;

/**
 * @author MyCollab Ltd
 * @since 5.2.3
 */
public class ToogleTaskSummaryField extends CssLayout {
    private boolean isRead = true;
    private Label taskLinkLbl;
    private SimpleTask task;
    private int maxLength;

    public ToogleTaskSummaryField(final SimpleTask task) {
        this(task, Integer.MAX_VALUE);
    }

    public ToogleTaskSummaryField(final SimpleTask task, int maxLength) {
        this.setWidth("100%");
        this.maxLength = maxLength;
        this.task = task;
        taskLinkLbl = new Label(buildTaskLink(), ContentMode.HTML);
        if (task.isCompleted()) {
            taskLinkLbl.addStyleName("completed");
            taskLinkLbl.removeStyleName("overdue pending");
        } else if (task.isOverdue()) {
            taskLinkLbl.addStyleName("overdue");
            taskLinkLbl.removeStyleName("completed pending");
        } else if (task.isPending()) {
            taskLinkLbl.addStyleName("pending");
            taskLinkLbl.removeStyleName("completed overdue");
        }
        taskLinkLbl.addStyleName(UIConstants.LABEL_WORD_WRAP);

        this.addComponent(taskLinkLbl);
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
            this.addStyleName("editable-field");
            this.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                    if (isRead) {
                        ToogleTaskSummaryField.this.removeComponent(taskLinkLbl);
                        final TextField editField = new TextField();
                        editField.setValue(task.getTaskname());
                        editField.setWidth("100%");
                        editField.focus();
                        ToogleTaskSummaryField.this.addComponent(editField);
                        ToogleTaskSummaryField.this.removeStyleName("editable-field");
                        editField.addValueChangeListener(new Property.ValueChangeListener() {
                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {
                                updateFieldValue(editField);
                            }
                        });
                        editField.addBlurListener(new FieldEvents.BlurListener() {
                            @Override
                            public void blur(FieldEvents.BlurEvent event) {
                                updateFieldValue(editField);
                            }
                        });
                        isRead = !isRead;
                    }

                }
            });
        }
    }

    private void updateFieldValue(TextField editField) {
        ToogleTaskSummaryField.this.removeComponent(editField);
        ToogleTaskSummaryField.this.addComponent(taskLinkLbl);
        ToogleTaskSummaryField.this.addStyleName("editable-field");
        String newValue = editField.getValue();
        if (StringUtils.isNotBlank(newValue) && !newValue.equals(task.getTaskname())) {
            task.setTaskname(newValue);
            taskLinkLbl.setValue(buildTaskLink());
            ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
            taskService.updateWithSession(task, AppContext.getUsername());
        }

        isRead = !isRead;
    }

    private String buildTaskLink() {
        String uid = UUID.randomUUID().toString();

        String linkName = String.format("[#%d] - %s", task.getTaskkey(), StringUtils.trim(task.getTaskname(), maxLength, true));
        A taskLink = new A().setId("tag" + uid).setHref(ProjectLinkBuilder.generateTaskPreviewFullLink(task.getTaskkey(),
                CurrentProjectVariables.getShortName())).appendText(linkName).setStyle("display:inline");

        taskLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(uid, ProjectTypeConstants.TASK, task.getId() + ""));
        taskLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction(uid));

        Div resultDiv = new DivLessFormatter().appendChild(taskLink, DivLessFormatter.EMPTY_SPACE(), TooltipHelper.buildDivTooltipEnable(uid));
        return resultDiv.write();
    }

    public void closeTask() {
        taskLinkLbl.removeStyleName("overdue pending");
        taskLinkLbl.addStyleName("completed");
    }

    public void reOpenTask() {
        taskLinkLbl.removeStyleName("overdue pending completed");
    }

    public void pendingTask() {
        taskLinkLbl.removeStyleName("overdue completed");
        taskLinkLbl.addStyleName("pending");
    }
}
