package com.mycollab.pro.module.project.view;

import com.mycollab.module.project.view.IFavoritePresenter;
import com.mycollab.module.project.view.IFavoriteView;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class FavoritePresenter extends AbstractPresenter<IFavoriteView> implements IFavoritePresenter {
    public FavoritePresenter() {
        super(IFavoriteView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectDashboardContainer projectViewContainer = (ProjectDashboardContainer) container;
        projectViewContainer.removeAllComponents();
        projectViewContainer.addComponent(view);

        ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
        breadcrumb.gotoFavoriteList();
        view.display();
    }
}
