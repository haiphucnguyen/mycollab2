package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageAction;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
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

        Presenter presenter;

        if (data instanceof ScreenData.Search) {
            presenter = PresenterResolver
                    .getPresenter(BugListPresenter.class);
        } else if (data instanceof ScreenData.Add
                || data instanceof ScreenData.Edit) {
            presenter = PresenterResolver
                    .getPresenter(BugAddPresenter.class);
        } else if (data instanceof ScreenData.Preview) {
            presenter = PresenterResolver
                    .getPresenter(BugReadPresenter.class);
        } else if (data instanceof BugContainer.AddComponentData) {
            presenter = PresenterResolver.getPresenter(ComponentAddPresenter.class);
        } else if (data instanceof BugContainer.EditComponentData) {
            presenter = PresenterResolver.getPresenter(ComponentAddPresenter.class);
        } else if (data instanceof BugContainer.SearchComponentData) {
            presenter = PresenterResolver.getPresenter(ComponentListPresenter.class);
        } else if (data instanceof BugContainer.ReadComponentData) {
            presenter = PresenterResolver.getPresenter(ComponentReadPresenter.class);
        } else if (data instanceof BugContainer.AddVersionData) {
            presenter = PresenterResolver.getPresenter(VersionAddPresenter.class);
        } else if (data instanceof BugContainer.EditVersionData) {
            presenter = PresenterResolver.getPresenter(VersionAddPresenter.class);
        } else if (data instanceof BugContainer.SearchVersionData) {
            presenter = PresenterResolver.getPresenter(VersionListPresenter.class);
        } else if (data instanceof BugContainer.ReadVersionData) {
            presenter = PresenterResolver.getPresenter(VersionReadPresenter.class);
        } else {
            presenter = PresenterResolver
                    .getPresenter(BugDashboardPresenter.class);
        }

        presenter.go(view, data);
    }

    @Override
    public void handleChain(ComponentContainer container, PageActionChain pageActionChain) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView("Bugs");

        view.removeAllComponents();
        
        PageAction pageAction = pageActionChain.pop();
        
        AbstractPresenter presenter;
        if (pageAction instanceof BugReadPageAction) {
            presenter = PresenterResolver.getPresenter(BugReadPresenter.class);
        } else if (pageAction instanceof ComponentReadPageAction) {
            presenter = PresenterResolver.getPresenter(ComponentReadPresenter.class);
        } else if (pageAction instanceof VersionReadPageAction) {
            presenter = PresenterResolver.getPresenter(VersionReadPresenter.class);
        } 
        else {
            throw new UnsupportedOperationException("Do not support handle page action " + pageAction);
        }
        
        presenter.go(view, pageAction.getScreenData());
    }
    
    
}
