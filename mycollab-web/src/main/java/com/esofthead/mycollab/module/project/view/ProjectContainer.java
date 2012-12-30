package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectContainer extends AbstractView {

    private ProjectController controller;

    public ProjectContainer() {
        controller = new ProjectController(this);
    }

    public void gotoProjectPage() {
       UserDashboardPresenter presenter = PresenterResolver.getPresenter(UserDashboardPresenter.class);
       presenter.go(this);
    }
}
