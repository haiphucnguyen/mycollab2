package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.core.arguments.SearchCriteria;


public abstract class ScreenData<P> {
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
	
	public static class Edit<P> extends ScreenData<P> {

		public Edit(P params) {
			super(params);
		}

	}
	
	public static class Preview<P> extends ScreenData<P> {

		public Preview(P params) {
			super(params);
		}

	}
	
	public static class Search<S extends SearchCriteria> extends ScreenData<S> {

		public Search(S params) {
			super(params);
		}

	}
}
