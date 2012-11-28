package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent.SaveProjectSucess;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;

public class ProjectController {

	private ProjectContainer container;

	private ProjectController(ProjectContainer container) {
		this.container = container;
	}

	private void bindProjectEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectEvent.SaveProjectSucess>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void handle(SaveProjectSucess event) {
						// TODO Auto-generated method stub
						
					}
				});
	}
}
