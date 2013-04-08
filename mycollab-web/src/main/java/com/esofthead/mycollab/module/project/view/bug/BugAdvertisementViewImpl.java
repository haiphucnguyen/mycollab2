package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class BugAdvertisementViewImpl extends AbstractView implements
		BugAdvertisementView {
	private static final long serialVersionUID = 1L;

	public BugAdvertisementViewImpl() {
		this.addComponent(new Label("Please upgrade"));
	}
}
