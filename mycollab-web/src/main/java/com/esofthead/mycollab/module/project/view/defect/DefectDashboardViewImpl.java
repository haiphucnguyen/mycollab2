package com.esofthead.mycollab.module.project.view.defect;

import com.esofthead.mycollab.module.project.view.ProjectAbstractView;
import com.vaadin.ui.Label;


@SuppressWarnings("serial")
public class DefectDashboardViewImpl extends ProjectAbstractView
		implements DefectDashboardView {

	public DefectDashboardViewImpl() {
		super();
		this.addComponent(new Label("Defects"));
	}
}
