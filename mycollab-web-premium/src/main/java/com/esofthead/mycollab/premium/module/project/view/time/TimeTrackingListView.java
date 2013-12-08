package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;

public interface TimeTrackingListView extends PageView {
	void setSearchCriteria(ItemTimeLoggingSearchCriteria searchCriteria);
}
