package com.esofthead.mycollab.module.project.view.defect;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;


@SuppressWarnings("serial")
@ViewComponent
public class DefectDashboardViewImpl extends AbstractView
		implements DefectDashboardView {

	public DefectDashboardViewImpl() {
		super();
		this.addComponent(new Label("Defects"));
	}
}
