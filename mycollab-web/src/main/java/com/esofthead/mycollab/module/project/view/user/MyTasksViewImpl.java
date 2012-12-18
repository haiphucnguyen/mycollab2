package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class MyTasksViewImpl extends AbstractView implements MyTasksView {

	public MyTasksViewImpl() {
		this.addComponent(new Label("My Tasks"));
	}

}
