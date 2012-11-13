package com.esofthead.mycollab.module.project.ui;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent.GetMyProjects;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter.ViewInterface;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
@Component
@ViewInterface(MyProjectsView.class)
public class MyProjectsPresenter extends AbstractPresenter<MyProjectsView> {
	@Autowired
	private ProjectService projectService;

	@PostConstruct
	private void init() {
		eventBus.addListener(new ApplicationEventListener<ProjectEvent.GetMyProjects>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ProjectEvent.GetMyProjects.class;
			}

			@Override
			public void handle(GetMyProjects event) {
				List<SimpleProject> projects = projectService
						.getInvolvedProjectOfUser(AppContext.getUsername());
				MyProjectsPresenter.this.view.displayProjects(projects);
			}
		});
	}
}
