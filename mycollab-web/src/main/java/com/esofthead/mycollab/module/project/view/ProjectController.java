package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent.SaveProjectSucess;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;

public class ProjectController {

	private ProjectMainContainer container;

	private ProjectController(ProjectMainContainer container) {
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
	}
}
