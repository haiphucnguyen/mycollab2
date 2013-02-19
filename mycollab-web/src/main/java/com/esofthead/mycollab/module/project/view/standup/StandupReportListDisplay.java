package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
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
			GridLayout layout = new GridLayout(3, 2);
			layout.setWidth("100%");
			Label reportDateLbl = new Label("Reported at "
					+ AppContext.formatDate(obj.getForday()));
			layout.addComponent(reportDateLbl, 0, 0, 2, 0);
			String prevText = "<b>What I did in the last day/week</b><p>"
					+ obj.getWhatlastday() + "</p>";
			Label prevLbl = new Label(prevText);
			prevLbl.setContentMode(Label.CONTENT_XHTML);
			layout.addComponent(prevLbl, 0, 1);

			String todayText = "<b>What I will do today/week</b><p>"
					+ obj.getWhattoday() + "</p>";
			Label todatLbl = new Label(todayText);
			todatLbl.setContentMode(Label.CONTENT_XHTML);
			layout.addComponent(todatLbl, 1, 1);

			String issueText = "<b>Do you have roadblocks?</b><p>"
					+ obj.getWhatproblem() + "</p>";
			Label issueLbl = new Label(issueText);
			issueLbl.setContentMode(Label.CONTENT_XHTML);
			layout.addComponent(issueLbl, 2, 1);

			return layout;
		}

	}

}
