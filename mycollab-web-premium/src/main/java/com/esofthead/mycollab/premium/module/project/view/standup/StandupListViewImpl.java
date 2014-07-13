package com.esofthead.mycollab.premium.module.project.view.standup;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.StyleCalendarExp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UrlDetectableLabel;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
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
	private final StyleCalendarExp standupCalendar = new StyleCalendarExp();

	private final BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport> reportInDay;
	private StandupMissingComp standupMissingComp;

	public StandupListViewImpl() {
		super();
		this.setMargin(new MarginInfo(false, true, false, true));

		this.constructHeader();

		this.addCalendarEvent();
		this.getListReport();

		this.reportInDay = new BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport>(
				ApplicationContextUtil
						.getSpringBean(StandupReportService.class),
				StandupReportRowDisplay.class);
		this.reportInDay.addStyleName("standupreport-list-content");
		HorizontalLayout contentWrap = new HorizontalLayout();
		contentWrap.setWidth("100%");
		contentWrap.setSpacing(true);
		contentWrap.addComponent(reportInDay);
		contentWrap.setExpandRatio(reportInDay, 1.0f);

		standupCalendar.addStyleName("standup-calendar");

		standupMissingComp = new StandupMissingComp();
		standupMissingComp.setWidth("250px");
		contentWrap.addComponent(standupMissingComp);

		this.addComponent(contentWrap);
	}

	private RangeDateSearchField getRangeDateSearchField(final Date date) {
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(date);
		cal1.set(Calendar.DAY_OF_MONTH, 1);

		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(date);
		cal2.set(Calendar.DAY_OF_MONTH,
				cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new RangeDateSearchField(cal1.getTime(), cal2.getTime());
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

						ProjectBreadcrumb breadCrumb = ViewManager
								.getView(ProjectBreadcrumb.class);
						breadCrumb.gotoStandupList(selectedDate);
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
		criteria.setReportDateRange(this
				.getRangeDateSearchField(this.standupCalendar
						.getStyleCalendar().getShowingDate()));
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
		searchCriteria.setOnDate(new DateSearchField(SearchField.AND,
				DateSearchField.EQUAL, date));
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
			this.standupMissingComp.search(searchCriteria.getOnDate()
					.getValue());
		}
	}

	private void constructHeader() {
		final HorizontalLayout header = new HorizontalLayout();
		header.setMargin(new MarginInfo(true, false, true, false));
		header.setSpacing(true);
		header.setWidth("100%");
		header.setStyleName("hdr-view");
		header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		final HorizontalLayout headerLeft = new HorizontalLayout();
		headerLeft.setSpacing(true);
		headerLeft.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		headerLeft.addComponent(new Image(null, MyCollabResource
				.newResource("icons/22/project/standup_selected.png")));

		this.titleLbl = new Label(
				AppContext.getMessage(StandupI18nEnum.VIEW_LIST_TITLE));
		this.titleLbl.addStyleName("hdr-text");

		headerLeft.addComponent(this.titleLbl);

		this.dateChooser = new PopupButton(
				AppContext.getMessage(StandupI18nEnum.CHOOSE_REPORT_DATE));
		this.dateChooser.setContent(this.standupCalendar);
		this.dateChooser.setStyleName(UIConstants.THEME_BLANK_LINK);
		headerLeft.addComponent(this.dateChooser);
		headerLeft.setComponentAlignment(this.dateChooser,
				Alignment.BOTTOM_RIGHT);

		header.addComponent(headerLeft);
		header.setComponentAlignment(headerLeft, Alignment.MIDDLE_LEFT);
		header.setExpandRatio(headerLeft, 1.0f);

		final Button addNewReport = new Button(
				AppContext.getMessage(StandupI18nEnum.BUTTON_ADD_REPORT_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBusFactory.getInstance().post(
								new StandUpEvent.GotoAdd(
										StandupListViewImpl.class, null));

					}
				});
		addNewReport.setStyleName(UIConstants.THEME_GREEN_LINK);
		addNewReport.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));

		header.addComponent(addNewReport);

		this.addComponent(header);
	}

	public static class StandupReportRowDisplay implements
			RowDisplayHandler<SimpleStandupReport> {
		private static final long serialVersionUID = 1L;

		@Override
		public Component generateRow(final SimpleStandupReport obj,
				final int rowIndex) {
			final StandupReportBlock singleReport = new StandupReportBlock(obj);
			if (rowIndex == 0) {
				singleReport.addStyleName("first-report");
			}
			return singleReport;
		}

	}

	static class StandupReportBlock extends HorizontalLayout {
		private static final long serialVersionUID = 1L;

		public StandupReportBlock(final SimpleStandupReport report) {
			super();
			this.setStyleName("standup-block");

			VerticalLayout userInfo = new VerticalLayout();
			userInfo.setWidth("200px");
			userInfo.setStyleName("user-info");
			userInfo.setSpacing(true);
			userInfo.setDefaultComponentAlignment(Alignment.TOP_CENTER);
			userInfo.setMargin(true);

			userInfo.addComponent(UserAvatarControlFactory
					.createUserAvatarEmbeddedComponent(
							report.getLogByAvatarId(), 100));
			ButtonLink userBtn = new ButtonLink(report.getLogByFullName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBusFactory.getInstance().post(
									new ProjectMemberEvent.GotoRead(this,
											report.getLogby()));
						}
					});
			userBtn.addStyleName("user-name");
			userInfo.addComponent(userBtn);
			this.addComponent(userInfo);

			VerticalLayout reportContent = new VerticalLayout();
			reportContent.setMargin(true);
			reportContent.setStyleName("report-content");
			reportContent.setSpacing(true);

			final Label whatYesterdayLbl = new Label(
					AppContext.getMessage(StandupI18nEnum.STANDUP_LASTDAY));
			whatYesterdayLbl.setStyleName("h2");
			reportContent.addComponent(whatYesterdayLbl);
			final Label whatYesterdayField = new UrlDetectableLabel(
					report.getWhatlastday());
			whatYesterdayField.setSizeUndefined();
			whatYesterdayField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			reportContent.addComponent(whatYesterdayField);

			final Label whatTodayLbl = new Label(
					AppContext.getMessage(StandupI18nEnum.STANDUP_TODAY));
			whatTodayLbl.setStyleName("h2");
			reportContent.addComponent(whatTodayLbl);
			final Label whatTodayField = new UrlDetectableLabel(
					report.getWhattoday());
			whatTodayField.setSizeUndefined();
			whatTodayField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			reportContent.addComponent(whatTodayField);

			final Label roadblockLbl = new Label(
					AppContext.getMessage(StandupI18nEnum.STANDUP_ISSUE));
			roadblockLbl.setStyleName("h2");
			reportContent.addComponent(roadblockLbl);
			final Label whatProblemField = new UrlDetectableLabel(
					report.getWhatproblem());
			whatProblemField.setSizeUndefined();
			whatProblemField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
			reportContent.addComponent(whatProblemField);

			this.addComponent(reportContent);
			this.setExpandRatio(reportContent, 1.0f);
		}
	}
}
