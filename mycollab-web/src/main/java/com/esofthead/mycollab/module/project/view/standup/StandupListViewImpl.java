package com.esofthead.mycollab.module.project.view.standup;

import java.util.Date;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class StandupListViewImpl extends AbstractView implements
		StandupListView {
	private static final long serialVersionUID = 1L;

	private Label titleLbl;
	private final VerticalLayout reportContent;
	private final InlineDateField dateSelection;

	private final BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport> reportInDay;

	public StandupListViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		constructHeader();

		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		reportContent = new VerticalLayout();
		reportContent.setWidth("100%");
		reportContent.setSpacing(true);
		layout.addComponent(reportContent);
		layout.setExpandRatio(reportContent, 1.0f);

		dateSelection = new InlineDateField("");
		dateSelection.setResolution(InlineDateField.RESOLUTION_DAY);
		dateSelection.setImmediate(true);
		dateSelection.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Date value = (Date) event.getProperty().getValue();
				displayReport(value);
			}
		});
		layout.addComponent(dateSelection);
		this.addComponent(layout);

		reportInDay = new BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport>(
				AppContext.getSpringBean(StandupReportService.class),
				StandupReportRowDisplay.class);
		reportContent.addComponent(reportInDay);
	}

	private void displayReport(Date date) {
		StandupReportSearchCriteria searchCriteria = new StandupReportSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		searchCriteria.setOnDate(new DateSearchField(SearchField.AND, date));
		setSearchCriteria(searchCriteria);
	}

	@Override
	public void setSearchCriteria(StandupReportSearchCriteria searchCriteria) {
		reportInDay.setSearchCriteria(searchCriteria);

		if (searchCriteria.getOnDate() != null) {
			titleLbl.setValue("StandUp Report for "
					+ AppContext.formatDate(searchCriteria.getOnDate()
							.getValue()));
		}
	}

	private void constructHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.setMargin(true);
		header.setSpacing(true);
		header.setWidth("100%");

		titleLbl = new Label("StandUp Reports");
		titleLbl.addStyleName("h2");
		header.addComponent(titleLbl);
		header.setComponentAlignment(titleLbl, Alignment.MIDDLE_RIGHT);
		header.setExpandRatio(titleLbl, 1.0f);

		Button addNewReport = new Button("Add New Report",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new StandUpEvent.GotoAdd(
										StandupListViewImpl.class, null));

					}
				});
		addNewReport.setStyleName(UIConstants.THEME_BLUE_LINK);
		addNewReport.setIcon(new ThemeResource("icons/16/addRecord.png"));

		header.addComponent(addNewReport);

		this.addComponent(header);
	}

	public static class StandupReportRowDisplay implements
			RowDisplayHandler<SimpleStandupReport> {

		@Override
		public Component generateRow(SimpleStandupReport obj, int rowIndex) {
			return new StandupReportDepot(obj);
		}

	}

	static class StandupReportDepot extends Depot {
		private static final long serialVersionUID = 1L;

		public StandupReportDepot(SimpleStandupReport report) {
			super(report.getLogByFullName(), new VerticalLayout());

			((VerticalLayout) bodyContent).setSpacing(true);
			Label whatYesterdayLbl = new Label(
					"What I did in the last day/week");
			whatYesterdayLbl.setStyleName("h2");
			bodyContent.addComponent(whatYesterdayLbl);
			Label whatYesterdayField = new Label(report.getWhatlastday(),
					Label.CONTENT_XHTML);
			whatYesterdayField.setSizeUndefined();
			whatYesterdayField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			bodyContent.addComponent(whatYesterdayField);

			Label whatTodayLbl = new Label("What I will do today/week");
			whatTodayLbl.setStyleName("h2");
			bodyContent.addComponent(whatTodayLbl);
			Label whatTodayField = new Label(report.getWhattoday(),
					Label.CONTENT_XHTML);
			whatTodayField.setSizeUndefined();
			whatTodayField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			bodyContent.addComponent(whatTodayField);

			Label roadblockLbl = new Label(
					"Do you have roadblocks? If you have questions or you need help, please write your questions or needs here");
			roadblockLbl.setStyleName("h2");
			bodyContent.addComponent(roadblockLbl);
			Label whatProblemField = new Label(report.getWhatproblem(),
					Label.CONTENT_XHTML);
			whatProblemField.setSizeUndefined();
			whatProblemField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			bodyContent.addComponent(whatProblemField);
		}
	}
}
