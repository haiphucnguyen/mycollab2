package com.esofthead.mycollab.vaadin.mvp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HistoryViewManager {
	private static Logger log = LoggerFactory
			.getLogger(HistoryViewManager.class);

	private static List<ViewState> history = new ArrayList<ViewState>();

	public static void addHistory(ViewState viewState) {
		if (history.size() > 10) {
			history.remove(0);
		}

		history.add(viewState);
		log.debug("Add view state to history. The history list is " + history);
	}

	public static ViewState getPreviousViewState() {
		if (history.size() >= 2) {
			ViewState viewState = history.get(history.size() - 2);
			return viewState;
		} else {
			return new NullViewState();
		}
	}

	public static ViewState back() {
		if (history.size() >= 2) {
			ViewState viewState = history.get(history.size() - 2);
			history.remove(history.size() - 1);
			history.remove(history.size() - 1);
			
			viewState.getPresenter().go(viewState.getContainer(),
					viewState.getParams());
			return viewState;
		} else {
			return new NullViewState();
		}
	}
}
