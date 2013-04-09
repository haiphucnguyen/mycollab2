package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class StandupAdvertisementViewImpl extends AbstractView implements
		StandupAdvertisementView {
	private static final long serialVersionUID = 1L;
	
	public StandupAdvertisementViewImpl() {
		this.addComponent(new Label("Please upgrade"));
	}

}
