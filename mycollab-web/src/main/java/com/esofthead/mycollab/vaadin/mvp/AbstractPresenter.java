package com.esofthead.mycollab.vaadin.mvp;

import com.vaadin.ui.ComponentContainer;

public abstract class AbstractPresenter<V extends View> implements Presenter {
	private static final long serialVersionUID = 1L;
	
	protected V view;
	
	public AbstractPresenter(Class<V> viewClass) {
		view = ViewManager.getView(viewClass);
	}
	
	public V getView() {
		return view;
	}

	@Override
	public void go(ComponentContainer container) {
		go(container, null);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		HistoryViewManager.addHistory(new ViewState(container, this, data));
		onGo(container, data);
	}

	protected abstract void onGo(ComponentContainer container,
			ScreenData<?> data);
}
