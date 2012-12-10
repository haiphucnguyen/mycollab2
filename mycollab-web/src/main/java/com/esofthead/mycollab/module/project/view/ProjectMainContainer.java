package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.Alignment;

@SuppressWarnings("serial")
public class ProjectMainContainer extends AbstractView {
	
	private ProjectController controller;

	public ProjectMainContainer() {
		controller = new ProjectController(this);
		UserDashboardViewImpl userDashboard = ViewManager
				.getView(UserDashboardViewImpl.class);
		this.addComponent(userDashboard);
		this.setComponentAlignment(userDashboard, Alignment.MIDDLE_CENTER);
	}

	private void gotoMyProjectList() {
		UserDashboardViewImpl userDashboard = ViewManager
				.getView(UserDashboardViewImpl.class);
		this.removeAllComponents();
		this.addComponent(userDashboard);
		userDashboard.gotoMyProjectList();
	}

	private void gotoMyProject() {
		ProjectViewImpl projectDashboard = ViewManager
				.getView(ProjectViewImpl.class);
		this.removeAllComponents();
		this.addComponent(projectDashboard);
	}
}
