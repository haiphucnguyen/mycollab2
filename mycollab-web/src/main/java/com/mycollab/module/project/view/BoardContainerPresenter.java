package com.mycollab.module.project.view;

import com.mycollab.module.project.view.parameters.ProjectScreenData;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

public class BoardContainerPresenter extends AbstractPresenter<BoardContainer> {

    public BoardContainerPresenter() {
        super(BoardContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectModule module = (ProjectModule) container;
        module.setContent(getView());

        AbstractPresenter<?> presenter;
        if (data instanceof ProjectScreenData.GotoList) {
            presenter = PresenterResolver.getPresenter(ProjectListPresenter.class);

        } else {
            presenter = PresenterResolver.getPresenter(BoardContainerPresenter.class);
        }
        presenter.go(view, data);
    }
}
