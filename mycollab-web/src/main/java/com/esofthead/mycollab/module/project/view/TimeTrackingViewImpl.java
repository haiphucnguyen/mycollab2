package com.esofthead.mycollab.module.project.view;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class TimeTrackingViewImpl extends AbstractView implements
		TimeTrackingView {
	private static final long serialVersionUID = 1L;

	private PopupDateField fromDateField;
	private PopupDateField toDateField;

	private TimeTrackingTableDisplay tableItem;

	private Label totalHoursLoggingLabel;

	public TimeTrackingViewImpl() {
		this.setSpacing(true);
		this.setWidth("100%");

		final CssLayout contentWrapper = new CssLayout();
		contentWrapper.setWidth("100%");
		contentWrapper.addStyleName("main-content-wrapper");
		this.addComponent(contentWrapper);

		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.setStyleName("projectfeed-hdr-wrapper");

		final HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		header.setSpacing(true);

		final Embedded timeIcon = new Embedded();
		timeIcon.setSource(MyCollabResource
				.newResource("icons/24/time_tracking.png"));
		header.addComponent(timeIcon);

		final Label layoutHeader = new Label("Your Time Reporting");
		layoutHeader.addStyleName("h2");
		header.addComponent(layoutHeader);
		header.setComponentAlignment(layoutHeader, Alignment.MIDDLE_LEFT);
		header.setExpandRatio(layoutHeader, 1.0f);

		headerWrapper.addComponent(header);
		contentWrapper.addComponent(headerWrapper);

		final Button backBtn = new Button("Back to Work Board");
		backBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoProjectModule(
								TimeTrackingViewImpl.this, null));

			}
		});

		backBtn.addStyleName(UIConstants.THEME_BLUE_LINK);

		VerticalLayout controlBtns = new VerticalLayout();
		controlBtns.setMargin(true, false, true, false);
		controlBtns.addComponent(backBtn);

		contentWrapper.addComponent(controlBtns);

		final HorizontalLayout dateSelectionLayout = new HorizontalLayout();
		dateSelectionLayout.setSpacing(true);
		dateSelectionLayout.setMargin(false, false, true, false);
		contentWrapper.addComponent(dateSelectionLayout);

		dateSelectionLayout.addComponent(new Label("From:  "));

		fromDateField = new PopupDateField();
		fromDateField.setResolution(DateField.RESOLUTION_DAY);
		dateSelectionLayout.addComponent(fromDateField);

		dateSelectionLayout.addComponent(new Label("  To:  "));
		toDateField = new PopupDateField();
		toDateField.setResolution(DateField.RESOLUTION_DAY);
		dateSelectionLayout.addComponent(toDateField);

		final Button queryBtn = new Button("Submit",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						Date from = (Date) fromDateField.getValue();
						Date to = (Date) toDateField.getValue();
						searchTimeReporting(from, to);
					}
				});
		queryBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		dateSelectionLayout.addComponent(queryBtn);

		HorizontalLayout hoursLoggingPanel = new HorizontalLayout();
		hoursLoggingPanel.setSpacing(true);
		hoursLoggingPanel.addComponent(new Label("Total Hours Logging:"));
		totalHoursLoggingLabel = new Label("0 Hrs");
		hoursLoggingPanel.addComponent(totalHoursLoggingLabel);
		contentWrapper.addComponent(hoursLoggingPanel);

		this.tableItem = new TimeTrackingTableDisplay(Arrays.asList(
				new TableViewField("Summary", "summary",
						UIConstants.TABLE_EX_LABEL_WIDTH), new TableViewField(
						"User", "logUserFullName",
						UIConstants.TABLE_X_LABEL_WIDTH), new TableViewField(
						"Project", "projectName",
						UIConstants.TABLE_X_LABEL_WIDTH), new TableViewField(
						"Created Time", "createdtime",
						UIConstants.TABLE_DATE_TIME_WIDTH), new TableViewField(
						"Hours", "logvalue", UIConstants.TABLE_S_LABEL_WIDTH)));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleItemTimeLogging itemLogging = (SimpleItemTimeLogging) event
								.getData();
						if ("summary".equals(event.getFieldName())) {
							final int typeId = itemLogging.getTypeid();
							final int projectId = itemLogging.getProjectid();

							if (MonitorTypeConstants.PRJ_BUG.equals(itemLogging
									.getType())) {
								final PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectId),
										new BugScreenData.Read(typeId));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							} else if (MonitorTypeConstants.PRJ_TASK
									.equals(itemLogging.getType())) {
								final PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectId),
										new TaskScreenData.Read(typeId));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							}
						} else if ("projectName".equals(event.getFieldName())) {
							final PageActionChain chain = new PageActionChain(
									new ProjectScreenData.Goto(itemLogging
											.getProjectid()));
							EventBus.getInstance()
									.fireEvent(
											new ProjectEvent.GotoMyProject(
													this, chain));
						}
					}
				});
		contentWrapper.addComponent(this.tableItem);
	}

	@Override
	public void display() {
		Calendar date = new GregorianCalendar();
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		Date from = date.getTime();
		date.add(Calendar.DATE, 6);
		Date to = date.getTime();

		fromDateField.setValue(from);
		toDateField.setValue(to);
		this.searchTimeReporting(from, to);
	}

	private void searchTimeReporting(Date from, Date to) {
		final ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
		searchCriteria.setLogUsers(new SetSearchField<String>(SearchField.AND,
				new String[] { AppContext.getUsername() }));
		searchCriteria.setRangeDate(new RangeDateSearchField(from, to));
		this.tableItem.setSearchCriteria(searchCriteria);

		ItemTimeLoggingService itemTimeLoggingService = AppContext
				.getSpringBean(ItemTimeLoggingService.class);
		Double totalHoursLogging = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);
		if (totalHoursLogging == null) {
			totalHoursLoggingLabel.setValue("0 Hrs");
		} else {
			totalHoursLoggingLabel.setValue(totalHoursLogging + " Hrs"); 
		}
	}

}
