package com.esofthead.mycollab.module.project.view.task.components;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.RemoveInlineComponentMarker;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class ToggleTaskSummaryWithParentRelationshipField extends CustomField<SimpleTask> {
    private ToggleTaskSummaryField toggleTaskSummaryField;

    public ToggleTaskSummaryWithParentRelationshipField(final SimpleTask task) {
        toggleTaskSummaryField = new ToggleTaskSummaryField(task);
        Button unlinkBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                task.setParenttaskid(null);
                ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
                taskService.updateWithSession(task, AppContext.getUsername());
                UIUtils.removeChildAssociate(ToggleTaskSummaryWithParentRelationshipField.this, RemoveInlineComponentMarker.class);
            }
        });
        unlinkBtn.setIcon(FontAwesome.UNLINK);
        unlinkBtn.setDescription("Remove parent-child relationship");
        unlinkBtn.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        unlinkBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        toggleTaskSummaryField.addControl(unlinkBtn);
    }

    @Override
    protected Component initContent() {
        return toggleTaskSummaryField;
    }

    @Override
    public Class<? extends SimpleTask> getType() {
        return SimpleTask.class;
    }

    public void updateLabel() {
        toggleTaskSummaryField.updateLabel();
    }

    public void closeTask() {
        toggleTaskSummaryField.closeTask();
    }

    public void overdueTask() {
        toggleTaskSummaryField.overdueTask();
    }

    public void reOpenTask() {
        toggleTaskSummaryField.reOpenTask();
    }
}
