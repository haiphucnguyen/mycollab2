package com.esofthead.mycollab.module.project.controller;

import javax.annotation.PostConstruct;


public class ProjectController {

	

	@SuppressWarnings("serial")
	@PostConstruct
	private void init() {
//		eventBus.addListener(new ApplicationEventListener<ProjectEvent.Save>() {
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return ProjectEvent.Save.class;
//			}
//
//			@Override
//			public void handle(Save event) {
//				Project project = (Project) event.getData();
//				projectService.saveWithSession(project,
//						AppContext.getUsername());
//				eventBus.fireEvent(new ProjectEvent.GotoMyProjectList(this,
//						null));
//			}
//		});
	}
}
