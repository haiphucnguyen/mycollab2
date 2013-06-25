package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class StandupReportListDisplay
		extends
		DefaultBeanPagedList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport> {
	private static final long serialVersionUID = 1L;

	public StandupReportListDisplay() {
		super(AppContext.getSpringBean(StandupReportService.class),
				new StandupReportRowDisplay());
	}

	public static class StandupReportRowDisplay implements
			RowDisplayHandler<SimpleStandupReport> {

		@Override
		public Component generateRow(final SimpleStandupReport obj,
				final int rowIndex) {
			final VerticalLayout layout = new VerticalLayout();
			layout.setWidth("100%");
			layout.setMargin(true, false, true, false);

			final VerticalLayout reportHeader = new VerticalLayout();
			final Label reportDateLbl = new Label(AppContext.formatDate(obj
					.getForday()));
			reportDateLbl.setWidth("120px");
			reportHeader.addComponent(reportDateLbl);
			reportHeader.addStyleName(UIConstants.REPORT_ROW_HEADER);
			reportHeader.setWidth(Sizeable.SIZE_UNDEFINED, 0);
			reportHeader.setHeight("25px");
			reportHeader.setComponentAlignment(reportDateLbl,
					Alignment.MIDDLE_LEFT);

			layout.addComponent(reportHeader);

			final HorizontalLayout reportContent = new HorizontalLayout();
			reportContent.setStyleName("report-row-content");

			final CssLayout report1 = new CssLayout();
			final String prevText = "<b>What I did in the last day/week</b><p>"
					+ obj.getWhatlastday() + "</p>";
			final Label prevLbl = new Label(prevText);
			prevLbl.setContentMode(Label.CONTENT_XHTML);
			report1.addComponent(prevLbl);
			report1.setSizeFull();
			report1.setStyleName(UIConstants.REPORT_ROW_BLOCK);
			reportContent.addComponent(report1);
			reportContent.setExpandRatio(report1, 1.0f);

			final CssLayout report2 = new CssLayout();
			final String todayText = "<b>What I will do today/week</b><p>"
					+ obj.getWhattoday() + "</p>";
			final Label todatLbl = new Label(todayText);
			todatLbl.setContentMode(Label.CONTENT_XHTML);
			report2.addComponent(todatLbl);
			report2.setSizeFull();
			report2.setStyleName(UIConstants.REPORT_ROW_BLOCK);
			report2.addStyleName("special-col");
			reportContent.addComponent(report2);
			reportContent.setExpandRatio(report2, 1.0f);

			final CssLayout report3 = new CssLayout();
			final String issueText = "<b>Do you have roadblocks?</b><p>"
					+ obj.getWhatproblem() + "</p>";
			final Label issueLbl = new Label(issueText);
			issueLbl.setContentMode(Label.CONTENT_XHTML);
			report3.addComponent(issueLbl);
			report3.setWidth("100%");
			report3.setHeight("100%");
			report3.setStyleName(UIConstants.REPORT_ROW_BLOCK);
			reportContent.addComponent(report3);
			reportContent.setExpandRatio(report3, 1.0f);

			reportContent.setWidth("100%");
			// reportContent.setSpacing(true);

			layout.addComponent(reportContent);
			return layout;
		}
	}

}
