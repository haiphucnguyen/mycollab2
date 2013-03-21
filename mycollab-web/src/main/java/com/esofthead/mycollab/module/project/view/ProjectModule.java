package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class ProjectModule extends AbstractView implements IModule {
	private static final long serialVersionUID = 1L;

	public ProjectModule() {
		ControllerRegistry.getInstance().addController(
				new ProjectController(this));
	}

	public void gotoProjectPage() {
		UserDashboardPresenter presenter = PresenterResolver
				.getPresenter(UserDashboardPresenter.class);
		presenter.go(this, null);
	}
}
