package com.esofthead.mycollab.module.project.ui;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class ProjectContainer extends AbstractView {
	
	public ProjectContainer() {
		UserDashboardViewImpl userDashboard = AppContext
				.getView(UserDashboardViewImpl.class);
		this.addComponent((com.vaadin.ui.Component) userDashboard);
	}

	
	private void init() {
//		eventBus.addListener(new ApplicationEventListener<ProjectEvent.GotoMyProjectList>() {
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return ProjectEvent.GotoMyProjectList.class;
//			}
//
//			@Override
//			public void handle(GotoMyProjectList event) {
//				gotoMyProjectList();
//
//			}
//		});
//		
//		eventBus.addListener(new ApplicationEventListener<ProjectEvent.GotoMyProject>() {
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return ProjectEvent.GotoMyProject.class;
//			}
//
//			@Override
//			public void handle(GotoMyProject event) {
//				gotoMyProject();
//			}
//		});
	}

	private void gotoMyProjectList() {
		UserDashboardViewImpl userDashboard = AppContext
				.getView(UserDashboardViewImpl.class);
		this.removeAllComponents();
		this.addComponent(userDashboard);
		userDashboard.gotoMyProjectList();
	}

	private void gotoMyProject() {
		ProjectViewImpl projectDashboard = AppContext
				.getView(ProjectViewImpl.class);
		this.removeAllComponents();
		this.addComponent(projectDashboard);
	}
}
