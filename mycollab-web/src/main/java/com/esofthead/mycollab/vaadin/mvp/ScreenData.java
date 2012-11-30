package com.esofthead.mycollab.vaadin.mvp;

public class ScreenData<P> {
	protected P params;

	public ScreenData(P params) {
		this.params = params;
	}

	public P getParams() {
		return params;
	}

	public void setParams(P params) {
		this.params = params;
	}
}
