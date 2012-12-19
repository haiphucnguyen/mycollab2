package com.esofthead.mycollab.vaadin.mvp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.ComponentContainer;

public abstract class AbstractPresenter<V extends View> implements Presenter {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(AbstractPresenter.class);

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
		go(container, data, true);
	}
	
	@Override
	public void go(ComponentContainer container, ScreenData<?> data, boolean isHistoryTrack) {
		if (isHistoryTrack) {
			ViewState state = new ViewState(container, this, data);
			log.debug("Add view state " + state + " to  history");
			HistoryViewManager.addHistory(state);
		} else {
			log.debug("Disable history track for " + this);
		}
		
		onGo(container, data);
	}

	protected abstract void onGo(ComponentContainer container,
			ScreenData<?> data);
}
