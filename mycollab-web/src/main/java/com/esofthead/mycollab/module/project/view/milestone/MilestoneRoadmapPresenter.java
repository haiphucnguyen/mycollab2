package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class MilestoneRoadmapPresenter extends AbstractPresenter<MilestoneRoadmapView> {
    public MilestoneRoadmapPresenter() {
        super(MilestoneRoadmapView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.MILESTONES)) {
            MilestoneContainer milestoneContainer = (MilestoneContainer) container;
            milestoneContainer.navigateToContainer(ProjectTypeConstants.MILESTONE);
            milestoneContainer.removeAllComponents();
            milestoneContainer.addComponent(view.getWidget());

            view.lazyLoadView();
            ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadcrumb.gotoRoadmap();
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
