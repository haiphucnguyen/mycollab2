package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class ComponentPresenter extends AbstractPresenter<ComponentContainer> {
	private static final long serialVersionUID = 1L;

	public ComponentPresenter() {
		super(ComponentContainer.class);
	}

	@Override
	protected void onGo(com.vaadin.ui.ComponentContainer container,
			ScreenData<?> data) {
	}

}
