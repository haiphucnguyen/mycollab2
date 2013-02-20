package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class BugListDisplayPresenter extends
		AbstractPresenter<BugListDisplayView> {
	private static final long serialVersionUID = 1L;

	public BugListDisplayPresenter() {
		super(BugListDisplayView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
	}

}
