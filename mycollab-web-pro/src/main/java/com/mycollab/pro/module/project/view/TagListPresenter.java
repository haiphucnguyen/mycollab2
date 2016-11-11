package com.mycollab.pro.module.project.view;

import com.mycollab.common.domain.Tag;
import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.view.ITagListPresenter;
import com.mycollab.module.project.view.ITagListView;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

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
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectDashboardContainer projectViewContainer = (ProjectDashboardContainer) container;
        projectViewContainer.removeAllComponents();
        projectViewContainer.addComponent(view);
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
