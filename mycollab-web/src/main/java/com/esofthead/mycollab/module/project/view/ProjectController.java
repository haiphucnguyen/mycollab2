package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent.SaveProjectSucess;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;

public class ProjectController {

	private ProjectMainContainer container;

	public ProjectController(ProjectMainContainer container) {
		this.container = container;
		bindProjectEvents();
	}

	@SuppressWarnings("serial")
	private void bindProjectEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectEvent.SaveProjectSucess>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectEvent.SaveProjectSucess.class;
					}

					@Override
					public void handle(SaveProjectSucess event) {
						// TODO Auto-generated method stub

					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectEvent.GotoMyProject>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectEvent.GotoMyProject.class;
					}

					@Override
					public void handle(ProjectEvent.GotoMyProject event) {
						ProjectViewImpl projectView = ViewManager
								.getView(ProjectViewImpl.class);
						ProjectViewPresenter presenter = new ProjectViewPresenter(
								projectView);
						presenter.go(container, new ScreenData<Project>(
								(Project) event.getData()));
					}
				});
	}
}
