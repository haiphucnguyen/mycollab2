package com.esofthead.mycollab.community.module.project.view.task;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.view.task.TaskTimeLogSheet;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.NotPresentWindow;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
@ViewComponent
public class TaskTimeLogSheetImpl extends TaskTimeLogSheet {
    @Override
    protected Double getTotalBillableHours(SimpleTask bean) {
        return 0d;
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleTask bean) {
        return 0d;
    }

    @Override
    protected Double getRemainedHours(SimpleTask bean) {
        return 0d;
    }

    @Override
    protected boolean hasEditPermission() {
        return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS);
    }

    @Override
    protected void showEditTimeWindow(SimpleTask bean) {
        UI.getCurrent().addWindow(new NotPresentWindow());
    }
}
