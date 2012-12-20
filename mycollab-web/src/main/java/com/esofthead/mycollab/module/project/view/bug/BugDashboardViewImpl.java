package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;


@SuppressWarnings("serial")
@ViewComponent
public class BugDashboardViewImpl extends AbstractView
		implements BugDashboardView {

	public BugDashboardViewImpl() {
		super();
		this.addComponent(new Label("Defects"));
	}
}
