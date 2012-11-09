package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
public class ProjectContainer extends AbstractView{

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout container = new VerticalLayout();
		UserDashboardViewImpl userDashboard = AppContext.getView(UserDashboardViewImpl.class);
		container.addComponent((com.vaadin.ui.Component)userDashboard.getCompContainer());
		return container;
	}

}
