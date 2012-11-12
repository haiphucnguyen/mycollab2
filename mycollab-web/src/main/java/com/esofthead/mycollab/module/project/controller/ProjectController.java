package com.esofthead.mycollab.module.project.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent.Save;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;
import com.esofthead.mycollab.web.AppContext;

@Component
public class ProjectController {
	@Autowired
	private EventBus eventBus;

	@Autowired
	private ProjectService projectService;

	@SuppressWarnings("serial")
	@PostConstruct
	private void init() {
		eventBus.addListener(new ApplicationEventListener<ProjectEvent.Save>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ProjectEvent.Save.class;
			}

			@Override
			public void handle(Save event) {
				Project project = (Project) event.getData();
				projectService.saveWithSession(project,
						AppContext.getUsername());
				eventBus.fireEvent(new ProjectEvent.GotoMyProjectList(this,
						null));
			}
		});
	}
}
