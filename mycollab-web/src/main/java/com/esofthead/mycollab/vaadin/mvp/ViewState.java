package com.esofthead.mycollab.vaadin.mvp;

public class ViewState {
	private Presenter presenter;
	private ScreenData<?> params;
	
	public ViewState(Presenter presenter, ScreenData<?> data) {
		this.presenter = presenter;
		this.params = data;
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public ScreenData<?> getParams() {
		return params;
	}
}
