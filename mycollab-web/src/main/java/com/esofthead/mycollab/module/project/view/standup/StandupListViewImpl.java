package com.esofthead.mycollab.module.project.view.standup;

import java.util.Date;
import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
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
import com.esofthead.mycollab.vaadin.ui.StandupStyleCalendarExp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class StandupListViewImpl extends AbstractView implements
		StandupListView {
	private static final long serialVersionUID = 1L;

	private Label titleLbl;
	private final VerticalLayout reportContent;
	private StandupStyleCalendarExp standupCalendar = new StandupStyleCalendarExp();

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

		layout.addComponent(standupCalendar);
		this.addComponent(layout);
		addCalendarEvent();
		getListReport();

		reportInDay = new BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport>(
				AppContext.getSpringBean(StandupReportService.class),
				StandupReportRowDisplay.class);
		reportContent.addComponent(reportInDay);
	}

	private RangeDateSearchField getRangeDateSearchField(Date date1, Date date2) {
		if (date1.before(date2)) {
			return new RangeDateSearchField(date1, date2);
		} else if (date1.after(date2)) {
			return new RangeDateSearchField(date2, date1);
		} else {
			return new RangeDateSearchField(date1, date2);
		}
	}

	@SuppressWarnings("serial")
	private void addCalendarEvent() {

		standupCalendar.getStyleCalendar().addListener(
				new ValueChangeListener() {

					@Override
					public void valueChange(ValueChangeEvent event) {
						Date selectedDate = (Date) event.getProperty()
								.getValue();
						displayReport(selectedDate);
						standupCalendar.setLabelTime(AppContext
								.formatDate(selectedDate));
					}
				});

		standupCalendar.getBtnShowNextYear().addListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						standupCalendar.getStyleCalendar().showNextYear();
						standupCalendar.setLabelTime(AppContext
								.formatDate(standupCalendar.getStyleCalendar()
										.getShowingDate()));
						getListReport();
					}
				});

		standupCalendar.getBtnShowNextMonth().addListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						standupCalendar.getStyleCalendar().showNextMonth();
						standupCalendar.setLabelTime(AppContext
								.formatDate(standupCalendar.getStyleCalendar()
										.getShowingDate()));
						getListReport();
					}
				});

		standupCalendar.getBtnShowPreviousMonth().addListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						standupCalendar.getStyleCalendar().showPreviousMonth();
						standupCalendar.setLabelTime(AppContext
								.formatDate(standupCalendar.getStyleCalendar()
										.getShowingDate()));
						getListReport();
					}
				});

		standupCalendar.getBtnShowPreviousYear().addListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						standupCalendar.getStyleCalendar().showPreviousYear();
						standupCalendar.setLabelTime(AppContext
								.formatDate(standupCalendar.getStyleCalendar()
										.getShowingDate()));
						getListReport();
					}
				});
	}

	private void getListReport() {
		StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setReportDateRange(getRangeDateSearchField(new Date(),
				standupCalendar.getStyleCalendar().getShowingDate()));
		StandupReportService reportService = AppContext
				.getSpringBean(StandupReportService.class);
		List<GroupItem> reportsCount = reportService.getReportsCount(criteria);

		for (GroupItem groupItem : reportsCount) {
			Date date = DateTimeUtils.getDateByStringWithFormat(
					groupItem.getGroupname(), AppContext.getDateFormat());
			standupCalendar.addSelectedDate(date);
		}
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

		Button addNewReport = new Button("Add/Edit Report",
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
