package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class TimeTrackingScreenData {
	public static class Search extends
			ScreenData<ItemTimeLoggingSearchCriteria> {

		public Search(ItemTimeLoggingSearchCriteria criteria) {
			super(criteria);
		}
	}
}
