package com.esofthead.mycollab.module.project.view;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.vaadin.hene.splitbutton.SplitButtonExt;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.file.resource.ExportItemsStreamResource;
import com.esofthead.mycollab.module.file.resource.SimpleGridExportItemsStreamResource;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.StreamResource;
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
	private SplitButtonExt exportButtonControl;

	private ItemTimeLoggingSearchCriteria searchCriteria;

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

		HorizontalLayout controlBtns = new HorizontalLayout();
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

		HorizontalLayout controlsPanel = new HorizontalLayout();
		controlsPanel.setWidth("100%");
		controlsPanel.setSpacing(true);
		totalHoursLoggingLabel = new Label("Total Hours Logging: 0 Hrs");
		controlsPanel.addComponent(totalHoursLoggingLabel);
		controlsPanel.setExpandRatio(totalHoursLoggingLabel, 1.0f);

		Button exportBtn = new Button("Export", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				exportButtonControl.setPopupVisible(true);

			}
		});
		exportButtonControl = new SplitButtonExt(exportBtn);
		exportButtonControl.setStyleName(UIConstants.THEME_GRAY_LINK);
		exportButtonControl.addStyleName(UIConstants.SPLIT_BUTTON);
		exportButtonControl.setIcon(MyCollabResource
				.newResource("icons/16/export.png"));

		VerticalLayout popupButtonsControl = new VerticalLayout();
		popupButtonsControl.setWidth("150px");
		exportButtonControl.addComponent(popupButtonsControl);

		Button exportPdfBtn = new Button("Pdf", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				downloadExportStreamCommand(ReportExportType.PDF);

			}
		});
		exportPdfBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/pdf.png"));
		exportPdfBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportPdfBtn);

		Button exportExcelBtn = new Button("Excel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				downloadExportStreamCommand(ReportExportType.EXCEL);
			}
		});
		exportExcelBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/excel.png"));
		exportExcelBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportExcelBtn);

		controlsPanel.addComponent(exportButtonControl);
		contentWrapper.addComponent(controlsPanel);

		this.tableItem = new TimeTrackingTableDisplay(Arrays.asList(
				TimeTrackingFieldDef.summary, TimeTrackingFieldDef.logUser,
				TimeTrackingFieldDef.project, TimeTrackingFieldDef.createdTime,
				TimeTrackingFieldDef.timeLogValue));

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

	private void downloadExportStreamCommand(ReportExportType exportType) {
		ExportItemsStreamResource<SimpleItemTimeLogging> stream = new SimpleGridExportItemsStreamResource.AllItems<ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
				"Time Tracking Report", tableItem.getDisplayColumns(),
				exportType,
				AppContext.getSpringBean(ItemTimeLoggingService.class),
				searchCriteria, SimpleItemTimeLogging.class);
		StreamResource res = new StreamResource(stream,
				stream.getDefaultExportFileName(),
				TimeTrackingViewImpl.this.getApplication());
		TimeTrackingViewImpl.this.getWindow().open(res, "_blank");
		exportButtonControl.setPopupVisible(false);
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
		searchCriteria = new ItemTimeLoggingSearchCriteria();
		searchCriteria.setLogUsers(new SetSearchField<String>(SearchField.AND,
				new String[] { AppContext.getUsername() }));
		searchCriteria.setRangeDate(new RangeDateSearchField(from, to));
		this.tableItem.setSearchCriteria(searchCriteria);

		ItemTimeLoggingService itemTimeLoggingService = AppContext
				.getSpringBean(ItemTimeLoggingService.class);
		Double totalHoursLogging = itemTimeLoggingService
				.getTotalHoursByCriteria(searchCriteria);
		if (totalHoursLogging == null) {
			totalHoursLoggingLabel.setValue("Total hours logging: 0 Hrs");
		} else {
			totalHoursLoggingLabel.setValue("Total hours logging: "
					+ totalHoursLogging + " Hrs");
		}
	}

}
