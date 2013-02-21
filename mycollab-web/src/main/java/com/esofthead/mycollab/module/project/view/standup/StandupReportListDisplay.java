package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

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
			VerticalLayout layout = new VerticalLayout();
			layout.setWidth("100%");
			layout.setSpacing(true);
			layout.setMargin(true, false, true, false);

			VerticalLayout reportHeader = new VerticalLayout();
			Label reportDateLbl = new Label(AppContext.formatDate(obj
					.getForday()));
			reportDateLbl.setWidth("120px");
			reportHeader.addComponent(reportDateLbl);
			reportHeader.addStyleName(UIConstants.REPORT_ROW_HEADER);
			reportHeader.setWidth("100%");
			reportHeader.setHeight("25px");
			reportHeader.setComponentAlignment(reportDateLbl,
					Alignment.MIDDLE_CENTER);

			layout.addComponent(reportHeader);

			HorizontalLayout reportContent = new HorizontalLayout();
			reportContent.setStyleName("report-row-content");

			CssLayout report1 = new CssLayout();
			String prevText = "<b>What I did in the last day/week</b><p>"
					+ obj.getWhatlastday() + "</p>";
			Label prevLbl = new Label(prevText);
			prevLbl.setContentMode(Label.CONTENT_XHTML);
			report1.addComponent(prevLbl);
			report1.setSizeFull();
			report1.setStyleName(UIConstants.REPORT_ROW_BLOCK);
			reportContent.addComponent(report1);
			reportContent.setExpandRatio(report1, 1.0f);

			CssLayout report2 = new CssLayout();
			String todayText = "<b>What I will do today/week</b><p>"
					+ obj.getWhattoday() + "</p>";
			Label todatLbl = new Label(todayText);
			todatLbl.setContentMode(Label.CONTENT_XHTML);
			report2.addComponent(todatLbl);
			report2.setSizeFull();
			report2.setStyleName(UIConstants.REPORT_ROW_BLOCK);
			reportContent.addComponent(report2);
			reportContent.setExpandRatio(report2, 1.0f);

			CssLayout report3 = new CssLayout();
			String issueText = "<b>Do you have roadblocks?</b><p>"
					+ obj.getWhatproblem() + "</p>";
			Label issueLbl = new Label(issueText);
			issueLbl.setContentMode(Label.CONTENT_XHTML);
			report3.addComponent(issueLbl);
			report3.setWidth("100%");
			report3.setHeight("100%");
			report3.setStyleName(UIConstants.REPORT_ROW_BLOCK);
			reportContent.addComponent(report3);
			reportContent.setExpandRatio(report3, 1.0f);

			reportContent.setWidth("100%");
			reportContent.setSpacing(true);

			layout.addComponent(reportContent);
			return layout;
		}

	}

}
