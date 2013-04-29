package com.esofthead.mycollab.module.crm.view.parameters;

import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class ActivityScreenData {
	public static class GotoCalendar extends ScreenData {

		public GotoCalendar() {
			super(null);
		}
	}

	public static class GotoActivityList extends
			ScreenData<EventSearchCriteria> {

		public GotoActivityList(EventSearchCriteria criteria) {
			super(criteria);
		}
	}
}
