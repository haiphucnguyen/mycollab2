package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProblemPresenter extends AbstractPresenter<ProblemContainer> {

    private static final long serialVersionUID = 1L;

    public ProblemPresenter() {
        super(ProblemContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView("Problems");

        view.removeAllComponents();

        if (data instanceof ScreenData.Search) {
            ProblemListPresenter presenter = PresenterResolver
                    .getPresenter(ProblemListPresenter.class);
            presenter.go(view, data);

        } else if (data instanceof ScreenData.Add
                || data instanceof ScreenData.Edit) {
            ProblemAddPresenter presenter = PresenterResolver
                    .getPresenter(ProblemAddPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ScreenData.Preview) {
            ProblemReadPresenter presenter = PresenterResolver
                    .getPresenter(ProblemReadPresenter.class);
            presenter.go(view, data);
        }
    }
}
