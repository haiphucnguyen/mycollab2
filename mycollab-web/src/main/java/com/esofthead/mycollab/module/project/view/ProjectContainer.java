package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class ProjectContainer extends AbstractView {
	private static final long serialVersionUID = 1L;

	public ProjectContainer() {
		ControllerRegistry.getInstance().addController(
				new ProjectController(this));
	}

	public void gotoProjectPage() {
		UserDashboardPresenter presenter = PresenterResolver
				.getPresenter(UserDashboardPresenter.class);
		presenter.go(this, null);
	}
}
