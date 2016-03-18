package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.common.domain.Tag;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.view.ITagListPresenter;
import com.esofthead.mycollab.module.project.view.ITagListView;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class TagListPresenter extends AbstractPresenter<ITagListView> implements ITagListPresenter {
    private static final long serialVersionUID = 1L;

    public TagListPresenter() {
        super(ITagListView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectDashboardContainer projectViewContainer = (ProjectDashboardContainer) container;
        projectViewContainer.removeAllComponents();
        projectViewContainer.addComponent(view.getWidget());
        Object params = data.getParams();
        if (params instanceof Tag || params == null) {
            ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadcrumb.gotoTagList();
            view.displayTags((Tag) params);
        } else {
            throw new MyCollabException("Do not support param type " + params);
        }
    }
}
