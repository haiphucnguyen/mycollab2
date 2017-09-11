package com.mycollab.module.project.view.page;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.parameters.PageScreenData;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public class PagePresenter extends AbstractPresenter<PageContainer> {
    private static final long serialVersionUID = 1L;

    public PagePresenter() {
        super(PageContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView(ProjectTypeConstants.INSTANCE.getPAGE());

        AbstractPresenter presenter;
        if (data instanceof PageScreenData.Search) {
            presenter = PresenterResolver.getPresenter(PageListPresenter.class);
        } else if (data instanceof PageScreenData.Add || data instanceof PageScreenData.Edit) {
            presenter = PresenterResolver.getPresenter(PageAddPresenter.class);
        } else if (data instanceof PageScreenData.Read) {
            presenter = PresenterResolver.getPresenter(PageReadPresenter.class);
        } else {
            throw new MyCollabException("Do not support screen data " + data);
        }

        presenter.go(view, data);
    }

}
