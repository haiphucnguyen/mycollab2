package com.esofthead.mycollab.module.project.ui;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ProjectDashboardViewImpl extends AbstractView implements ProjectDashboardView {

	public ProjectDashboardViewImpl() {
		this.addComponent(new Label("AAA"));
	}

}
