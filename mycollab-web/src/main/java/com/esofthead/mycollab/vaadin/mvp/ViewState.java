package com.esofthead.mycollab.vaadin.mvp;

import com.vaadin.ui.ComponentContainer;

public class ViewState {
	private ComponentContainer container;
	private Presenter presenter;
	private ScreenData<?> params;

	public ViewState(ComponentContainer container, Presenter presenter,
			ScreenData<?> data) {
		this.container = container;
		this.presenter = presenter;
		this.params = data;
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public ScreenData<?> getParams() {
		return params;
	}

	public ComponentContainer getContainer() {
		return container;
	}
}
