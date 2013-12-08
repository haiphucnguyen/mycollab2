package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;

public interface StandupListView extends PageView {
	void setSearchCriteria(StandupReportSearchCriteria searchCriteria);
}
