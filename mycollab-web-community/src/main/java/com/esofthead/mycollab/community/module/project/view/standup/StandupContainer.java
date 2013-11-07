package com.esofthead.mycollab.community.module.project.view.standup;

import com.esofthead.mycollab.module.project.view.standup.IStandupContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class StandupContainer extends AbstractView implements IStandupContainer {
	private static final long serialVersionUID = 1L;

	public StandupContainer() {
		this.addComponent(new Label(
				"Module is not presented for community edition"));
	}

}
