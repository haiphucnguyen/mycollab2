package com.mycollab.pro.module.project.view.milestone;

import com.mycollab.core.SecureAccessException;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.milestone.IMilestoneKanbanPresenter;
import com.mycollab.module.project.view.milestone.IMilestoneKanbanView;
import com.mycollab.module.project.view.milestone.MilestoneContainer;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
public class MilestoneKanbanPresenter extends AbstractPresenter<IMilestoneKanbanView> implements IMilestoneKanbanPresenter {
    public MilestoneKanbanPresenter() {
        super(IMilestoneKanbanView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.MILESTONES)) {
            MilestoneContainer milestoneContainer = (MilestoneContainer) container;
            milestoneContainer.navigateToContainer(ProjectTypeConstants.MILESTONE);
            milestoneContainer.removeAllComponents();
            milestoneContainer.addComponent(view);

            view.lazyLoadView();
            ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadcrumb.gotoMilestoneKanban();
        }else {
            throw new SecureAccessException();
        }
    }
}
