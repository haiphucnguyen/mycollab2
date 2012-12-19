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

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("View State:").append("\n");
		result.append("   Presenter: " + presenter.getClass().getName()).append("\n");
		result.append("   Params: " + ((params != null) ? params : "null"));
		return result.toString();
	}
}
