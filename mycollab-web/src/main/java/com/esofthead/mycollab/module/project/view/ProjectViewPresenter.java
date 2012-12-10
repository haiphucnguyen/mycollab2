package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

public class ProjectViewPresenter implements Presenter {
	private static final long serialVersionUID = 1L;

	private ProjectView view;

	public ProjectViewPresenter(ProjectView view) {
		this.view = view;
	}

	@Override
	public void go(ComponentContainer container) {
		// TODO Auto-generated method stub

	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		ProjectMainContainer prjContainer = (ProjectMainContainer) container;
		prjContainer.removeAllComponents();
		prjContainer.addComponent((Component)view);
	}

}
