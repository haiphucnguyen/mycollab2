package com.esofthead.mycollab.community.module.project.view.bug;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.view.bug.BugTimeLogSheet;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.NotPresentWindow;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
@ViewComponent
public class BugTimeLogSheetImpl extends BugTimeLogSheet {
    @Override
    protected Double getTotalBillableHours(SimpleBug bean) {
        return 0d;
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleBug bean) {
        return 0d;
    }

    @Override
    protected Double getRemainedHours(SimpleBug bean) {
        return 0d;
    }

    @Override
    protected boolean hasEditPermission() {
        return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
    }

    @Override
    protected void showEditTimeWindow(SimpleBug bean) {
        UI.getCurrent().addWindow(new NotPresentWindow());
    }
}
