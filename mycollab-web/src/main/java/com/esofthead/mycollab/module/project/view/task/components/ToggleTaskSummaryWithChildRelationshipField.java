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
public class ToggleTaskSummaryWithChildRelationshipField extends CustomField<SimpleTask> {
    private ToggleTaskSummaryField toggleTaskSummaryField;

    public ToggleTaskSummaryWithChildRelationshipField(final SimpleTask parentTask, final SimpleTask childTask) {
        toggleTaskSummaryField = new ToggleTaskSummaryField(parentTask);
        Button unlinkBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                childTask.setParenttaskid(null);
                ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
                taskService.updateWithSession(childTask, AppContext.getUsername());
                UIUtils.removeChildAssociate(ToggleTaskSummaryWithChildRelationshipField.this, RemoveInlineComponentMarker.class);
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
}
