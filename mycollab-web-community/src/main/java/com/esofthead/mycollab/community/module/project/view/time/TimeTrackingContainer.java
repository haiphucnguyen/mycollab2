package com.esofthead.mycollab.community.module.project.view.time;

import com.esofthead.mycollab.module.project.view.time.ITimeTrackingContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class TimeTrackingContainer extends AbstractView implements
		ITimeTrackingContainer {
	private static final long serialVersionUID = 1L;

	public TimeTrackingContainer() {
		this.addComponent(new Label(
				"Module is not presented for community edition"));
	}
}
