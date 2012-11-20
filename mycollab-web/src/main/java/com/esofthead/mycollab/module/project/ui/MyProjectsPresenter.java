package com.esofthead.mycollab.module.project.ui;

import javax.annotation.PostConstruct;

import com.esofthead.mycollab.module.project.service.ProjectService;

@SuppressWarnings("serial")

public class MyProjectsPresenter {
	private ProjectService projectService;

	@PostConstruct
	private void init() {
//		eventBus.addListener(new ApplicationEventListener<ProjectEvent.GetMyProjects>() {
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return ProjectEvent.GetMyProjects.class;
//			}
//
//			@Override
//			public void handle(GetMyProjects event) {
//				List<SimpleProject> projects = projectService
//						.getInvolvedProjectOfUser(AppContext.getUsername());
//				MyProjectsPresenter.this.view.displayProjects(projects);
//			}
//		});
	}
}
