package com.esofthead.mycollab.community.module.project.view;

import com.esofthead.mycollab.module.project.view.ITagListPresenter;
import com.esofthead.mycollab.module.project.view.ITagListView;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class TagListPresenter extends AbstractPresenter<ITagListView> implements ITagListPresenter {
    public TagListPresenter() {
        super(ITagListView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectDashboardContainer projectViewContainer = (ProjectDashboardContainer) container;
        projectViewContainer.removeAllComponents();
        projectViewContainer.addComponent(view.getWidget());
    }
}
