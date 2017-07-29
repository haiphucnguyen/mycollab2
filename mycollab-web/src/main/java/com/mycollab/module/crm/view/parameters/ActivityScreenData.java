package com.mycollab.module.crm.view.parameters;

import com.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.mycollab.vaadin.mvp.ScreenData;

public class ActivityScreenData {
	public static class GotoCalendar extends ScreenData {

		public GotoCalendar() {
			super(null);
		}
	}

	public static class GotoActivityList extends
			ScreenData<ActivitySearchCriteria> {

		public GotoActivityList(ActivitySearchCriteria criteria) {
			super(criteria);
		}
	}
}
