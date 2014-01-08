package com.esofthead.mycollab.premium.module.project.view.standup;

import java.util.Date;
import java.util.List;

import com.vaadin.shared.ui.MarginInfo;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.StandupStyleCalendarExp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UrlDetectableLabel;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class StandupListViewImpl extends AbstractPageView implements
		StandupListView {
	private static final long serialVersionUID = 1L;

	private Label titleLbl;
	private PopupButton dateChooser;
	private final StandupStyleCalendarExp standupCalendar = new StandupStyleCalendarExp();

	private final BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport> reportInDay;

	public StandupListViewImpl() {
		super();
		
		this.constructHeader();

		this.addCalendarEvent();
		this.getListReport();

		this.reportInDay = new BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport>(
				ApplicationContextUtil
						.getSpringBean(StandupReportService.class),
				StandupReportRowDisplay.class);
		this.reportInDay.addStyleName("standupreport-list-content");
		this.addComponent(this.reportInDay);
	}

	private RangeDateSearchField getRangeDateSearchField(final Date date1,
			final Date date2) {
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

		this.standupCalendar.getStyleCalendar().addValueChangeListener(
				new ValueChangeListener() {

					@Override
					public void valueChange(final ValueChangeEvent event) {
						final Date selectedDate = (Date) event.getProperty()
								.getValue();
						StandupListViewImpl.this.displayReport(selectedDate);
						StandupListViewImpl.this.standupCalendar
								.setLabelTime(AppContext
										.formatDate(selectedDate));
						StandupListViewImpl.this.dateChooser
								.setCaption(AppContext.formatDate(selectedDate));
						StandupListViewImpl.this.dateChooser
								.setPopupVisible(false);
					}
				});

		this.standupCalendar.getBtnShowNextYear().addClickListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						StandupListViewImpl.this.standupCalendar
								.getStyleCalendar().showNextYear();
						StandupListViewImpl.this.standupCalendar.setLabelTime(AppContext
								.formatDate(StandupListViewImpl.this.standupCalendar
										.getStyleCalendar().getShowingDate()));
						StandupListViewImpl.this.getListReport();
					}
				});

		this.standupCalendar.getBtnShowNextMonth().addClickListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						StandupListViewImpl.this.standupCalendar
								.getStyleCalendar().showNextMonth();
						StandupListViewImpl.this.standupCalendar.setLabelTime(AppContext
								.formatDate(StandupListViewImpl.this.standupCalendar
										.getStyleCalendar().getShowingDate()));
						StandupListViewImpl.this.getListReport();
					}
				});

		this.standupCalendar.getBtnShowPreviousMonth().addClickListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						StandupListViewImpl.this.standupCalendar
								.getStyleCalendar().showPreviousMonth();
						StandupListViewImpl.this.standupCalendar.setLabelTime(AppContext
								.formatDate(StandupListViewImpl.this.standupCalendar
										.getStyleCalendar().getShowingDate()));
						StandupListViewImpl.this.getListReport();
					}
				});

		this.standupCalendar.getBtnShowPreviousYear().addClickListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						StandupListViewImpl.this.standupCalendar
								.getStyleCalendar().showPreviousYear();
						StandupListViewImpl.this.standupCalendar.setLabelTime(AppContext
								.formatDate(StandupListViewImpl.this.standupCalendar
										.getStyleCalendar().getShowingDate()));
						StandupListViewImpl.this.getListReport();
					}
				});
	}

	private void getListReport() {
		final StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setReportDateRange(this.getRangeDateSearchField(new Date(),
				this.standupCalendar.getStyleCalendar().getShowingDate()));
		final StandupReportService reportService = ApplicationContextUtil
				.getSpringBean(StandupReportService.class);
		final List<GroupItem> reportsCount = reportService
				.getReportsCount(criteria);

		for (final GroupItem groupItem : reportsCount) {
			final Date date = DateTimeUtils.convertDateByString(
					groupItem.getGroupname(), AppContext.getDateFormat());
			this.standupCalendar.addSelectedDate(date);
		}
	}

	private void displayReport(final Date date) {
		final StandupReportSearchCriteria searchCriteria = new StandupReportSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		searchCriteria.setOnDate(new DateSearchField(SearchField.AND, date));
		this.setSearchCriteria(searchCriteria);
	}

	@Override
	public void setSearchCriteria(
			final StandupReportSearchCriteria searchCriteria) {
		this.reportInDay.setSearchCriteria(searchCriteria);

		if (searchCriteria.getOnDate() != null) {
			this.dateChooser.setCaption(AppContext.formatDate(searchCriteria
					.getOnDate().getValue()));
			this.standupCalendar.getStyleCalendar().setShowingDate(
					searchCriteria.getOnDate().getValue());
			this.standupCalendar.setLabelTime(AppContext
					.formatDate(searchCriteria.getOnDate().getValue()));
		}
	}

	private void constructHeader() {
		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.addStyleName("standupreport-list-header");
		headerWrapper.setWidth("100%");
		final HorizontalLayout header = new HorizontalLayout();
		header.setMargin(false);
		header.setSpacing(true);
		header.setWidth("100%");
		headerWrapper.addComponent(header);

		final HorizontalLayout headerLeft = new HorizontalLayout();
		headerLeft.setSpacing(true);

		this.titleLbl = new Label("StandUp Reports for: ");
		this.titleLbl.addStyleName("h2");

		headerLeft.addComponent(this.titleLbl);

		this.dateChooser = new PopupButton("Choose date to view reports");
		this.dateChooser.setContent(this.standupCalendar);
		headerLeft.addComponent(this.dateChooser);
		headerLeft.setComponentAlignment(this.dateChooser,
				Alignment.BOTTOM_RIGHT);

		header.addComponent(headerLeft);
		header.setComponentAlignment(headerLeft, Alignment.MIDDLE_LEFT);
		header.setExpandRatio(headerLeft, 1.0f);

		final Button addNewReport = new Button("Add/Edit Report",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new StandUpEvent.GotoAdd(
										StandupListViewImpl.class, null));

					}
				});
		addNewReport.setStyleName(UIConstants.THEME_BLUE_LINK);
		addNewReport.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));

		header.addComponent(addNewReport);

		this.addComponent(headerWrapper);
	}

	public static class StandupReportRowDisplay implements
			RowDisplayHandler<SimpleStandupReport> {
		private static final long serialVersionUID = 1L;

		@Override
		public Component generateRow(final SimpleStandupReport obj,
				final int rowIndex) {
			final StandupReportDepot singleReport = new StandupReportDepot(obj);
			if (rowIndex == 0) {
				singleReport.addStyleName("first-report");
			}
			return singleReport;
		}

	}

	static class StandupReportDepot extends Depot {
		private static final long serialVersionUID = 1L;

		public StandupReportDepot(final SimpleStandupReport report) {
			super("", new VerticalLayout());

            this.setMargin(false);

			String userLbl = "<img src=\"%s\" alt=\" \"/>&nbsp; %s";
			this.getHeaderLbl().setValue(
					String.format(
							userLbl,
							UserAvatarControlFactory.getAvatarLink(
									report.getLogByAvatarId(), 16),
							report.getLogByFullName()));
			this.getHeaderLbl().setContentMode(ContentMode.HTML);
			((VerticalLayout) this.bodyContent).setSpacing(true);
            ((VerticalLayout) this.bodyContent).setMargin(new MarginInfo(true, false, false, true));
			final Label whatYesterdayLbl = new Label(
					"What I did in the last day/week");
			whatYesterdayLbl.setStyleName("h2");
			this.bodyContent.addComponent(whatYesterdayLbl);
			final Label whatYesterdayField = new UrlDetectableLabel(
					report.getWhatlastday());
			whatYesterdayField.setSizeUndefined();
			whatYesterdayField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			this.bodyContent.addComponent(whatYesterdayField);

			final Label whatTodayLbl = new Label("What I will do today/week");
			whatTodayLbl.setStyleName("h2");
			this.bodyContent.addComponent(whatTodayLbl);
			final Label whatTodayField = new UrlDetectableLabel(
					report.getWhattoday());
			whatTodayField.setSizeUndefined();
			whatTodayField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			this.bodyContent.addComponent(whatTodayField);

			final Label roadblockLbl = new Label(
					"Do you have roadblocks? If you have questions or you need help, please write your questions or needs here");
			roadblockLbl.setStyleName("h2");
			this.bodyContent.addComponent(roadblockLbl);
			final Label whatProblemField = new UrlDetectableLabel(
					report.getWhatproblem());
			whatProblemField.setSizeUndefined();
			whatProblemField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			this.bodyContent.addComponent(whatProblemField);
		}
	}
}
