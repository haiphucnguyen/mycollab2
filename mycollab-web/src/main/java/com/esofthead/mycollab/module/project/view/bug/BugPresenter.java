package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class BugPresenter extends AbstractPresenter<BugContainer> {

    private static final long serialVersionUID = 1L;

    public BugPresenter() {
        super(BugContainer.class);
    }

    @Override
    public void go(ComponentContainer container, ScreenData<?> data) {
        super.go(container, data, false);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView("Bugs");

        view.removeAllComponents();

        if (data instanceof ScreenData.Search) {
            BugListPresenter presenter = PresenterResolver
                    .getPresenter(BugListPresenter.class);
            presenter.go(view, data);

        } else if (data instanceof ScreenData.Add
                || data instanceof ScreenData.Edit) {
            BugAddPresenter presenter = PresenterResolver
                    .getPresenter(BugAddPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ScreenData.Preview) {
            BugReadPresenter presenter = PresenterResolver
                    .getPresenter(BugReadPresenter.class);
            presenter.go(view, data);
        } else {
            BugDashboardPresenter presenter = PresenterResolver
                    .getPresenter(BugDashboardPresenter.class);
            presenter.go(view, data);
        }
    }
}
