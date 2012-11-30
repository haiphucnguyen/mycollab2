package com.esofthead.mycollab.vaadin.mvp;

import java.util.EmptyStackException;
import java.util.Stack;

public class HistoryViewManager {
	private static Stack<ViewState> history = new Stack<ViewState>();

	public static void addHistory(ViewState viewState) {
		if (history.size() > 10) {
			history.remove(history.size());
		}

		history.push(viewState);
	}

	public static ViewState getPreviousViewState() {
		try {
			return history.pop();
		} catch (EmptyStackException e) {
			return null;
		}
	}
}
