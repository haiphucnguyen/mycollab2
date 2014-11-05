package com.esofthead.mycollab.mobile.ui;

import static com.esofthead.mycollab.common.MyCollabSession.HISTORY_VAL;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.common.MyCollabSession;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ViewState;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class MobileHistoryViewManager extends HistoryViewManager {

	@SuppressWarnings("unchecked")
	public static ViewState pop(boolean firstTime) {
		List<ViewState> history = (List<ViewState>) MyCollabSession
				.getVariable(HISTORY_VAL);
		if (history == null) {
			history = new ArrayList<ViewState>();
			MyCollabSession.putVariable(HISTORY_VAL, history);
		}
		if (firstTime) {
			if (history.size() >= 2) {
				ViewState viewState = history.get(history.size() - 2);
				history.remove(history.size() - 1);
				history.remove(history.size() - 1);
				MyCollabSession.putVariable(HISTORY_VAL, history);
				return viewState;
			}
		} else {
			if (history.size() >= 1) {
				ViewState viewState = history.get(history.size() - 1);
				history.remove(history.size() - 1);
				MyCollabSession.putVariable(HISTORY_VAL, history);
				return viewState;
			}
		}
		return new NullViewState();
	}

	public static ViewState pop() {
		return MobileHistoryViewManager.pop(true);
	}

	@SuppressWarnings("unchecked")
	public static ViewState peak() {
		List<ViewState> history = (List<ViewState>) MyCollabSession
				.getVariable(HISTORY_VAL);
		if (history == null) {
			history = new ArrayList<ViewState>();
			MyCollabSession.putVariable(HISTORY_VAL, history);
		}
		if (history.size() >= 1) {
			ViewState viewState = history.get(history.size() - 1);
			return viewState;
		}
		return new NullViewState();
	}

}
