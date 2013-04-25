package com.esofthead.mycollab.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class TimeTrackingListViewImpl extends AbstractView implements
		TimeTrackingListView {
	private static final long serialVersionUID = 1L;

	public TimeTrackingListViewImpl() {
		this.addComponent(new Label("Time tracking"));
	}

	@Override
	public void setSearchCriteria(ItemTimeLoggingSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		
	}

}
