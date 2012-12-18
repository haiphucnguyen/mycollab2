package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectTaskViewImpl extends AbstractView implements
		ProjectTaskView {

	public ProjectTaskViewImpl() {
		super();
		this.addComponent(new Label("Tasks"));
	}
}
