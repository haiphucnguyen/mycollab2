package com.esofthead.mycollab.module.project.ui;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent.GotoMyProject;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent.GotoMyProjectList;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
@Component
public class ProjectContainer extends AbstractView {

	@PostConstruct
	private void init() {
		eventBus.addListener(new ApplicationEventListener<ProjectEvent.GotoMyProjectList>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ProjectEvent.GotoMyProjectList.class;
			}

			@Override
			public void handle(GotoMyProjectList event) {
				gotoMyProjectList();

			}
		});
		
		eventBus.addListener(new ApplicationEventListener<ProjectEvent.GotoMyProject>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ProjectEvent.GotoMyProject.class;
			}

			@Override
			public void handle(GotoMyProject event) {
				gotoMyProject();
			}
		});
	}

	@Override
	protected void initializeLayout() {
		UserDashboardViewImpl userDashboard = AppContext
				.getView(UserDashboardViewImpl.class);
		this.setSizeFull();
		this.addComponent((com.vaadin.ui.Component) userDashboard);
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
