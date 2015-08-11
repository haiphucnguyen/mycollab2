package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class BugKanbanPresenter  extends AbstractPresenter<BugKanbanView> {
    public BugKanbanPresenter() {
        super(BugKanbanView.class);
    }

    @Override
    protected void onGo(com.vaadin.ui.ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.BUGS)) {
            BugContainer bugContainer = (BugContainer) container;
            bugContainer.removeAllComponents();
            bugContainer.addComponent(view.getWidget());
            view.display();

            ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadCrumb.gotoBugKanbanView();
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
