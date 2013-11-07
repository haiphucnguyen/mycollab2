package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface TimeTrackingListView extends View {
	void setSearchCriteria(ItemTimeLoggingSearchCriteria searchCriteria);
}
