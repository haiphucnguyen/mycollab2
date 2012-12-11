package com.esofthead.mycollab.module.project.view;

import com.vaadin.ui.Label;


@SuppressWarnings("serial")
public class ProjectDefectDashboardViewImpl extends ProjectAbstractView
		implements ProjectDefectDashboardView {

	public ProjectDefectDashboardViewImpl() {
		super();
		this.addComponent(new Label("Defects"));
	}
}
