package com.esofthead.mycollab.module.project.ui;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent.GotoMyProjectList;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

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
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout container = new VerticalLayout();
		UserDashboardViewImpl userDashboard = AppContext
				.getView(UserDashboardViewImpl.class);
		container.addComponent((com.vaadin.ui.Component) userDashboard
				.getCompContainer());
		return container;
	}

}
