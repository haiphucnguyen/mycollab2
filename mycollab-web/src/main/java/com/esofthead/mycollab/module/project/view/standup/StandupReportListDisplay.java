package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class StandupReportListDisplay
		extends
		PagedBeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport> {
	private static final long serialVersionUID = 1L;

	public StandupReportListDisplay() {
		super(AppContext.getSpringBean(StandupReportService.class),
				new StandupReportRowDisplay());
	}

	public static class StandupReportRowDisplay implements
			RowDisplayHandler<SimpleStandupReport> {

		@Override
		public Component generateRow(SimpleStandupReport obj, int rowIndex) {
			return new Label("AAA");
		}

	}

}
