package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@Component
public class ProjectDashboardViewImpl extends AbstractView implements ProjectDashboardView {

	public ProjectDashboardViewImpl() {
		this.addComponent(new Label("AAA"));
	}

}
