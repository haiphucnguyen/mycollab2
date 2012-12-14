package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.events.ProjectEvent.SaveProjectSucess;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;

public class ProjectController {

	private ProjectMainContainer container;

	public ProjectController(ProjectMainContainer container) {
		this.container = container;
		bindProjectEvents();
		bindRiskEvents();
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
						SimpleProject project = (SimpleProject) event.getData();
						AppContext.putVariable(ProjectContants.PROJECT_NAME,
								project);
						presenter.go(container,
								new ScreenData<Project>(project));
					}
				});
	}

	@SuppressWarnings("serial")
	private void bindRiskEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<RiskEvent.GotoAdd>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RiskEvent.GotoAdd.class;
					}

					@Override
					public void handle(RiskEvent.GotoAdd event) {
						ProjectViewImpl projectView = ViewManager
								.getView(ProjectViewImpl.class);
						ScreenData.Add<Risk> data = new ScreenData.Add<Risk>(
								new Risk());
						projectView.gotoRiskView(data);
					}
				});
	}
}
