/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
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
import com.esofthead.mycollab.reporting.RpParameterBuilder;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.resource.LazyStreamSource;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Sizeable;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class TimeTrackingSummaryViewImpl extends AbstractPageView implements
		TimeTrackingSummaryView {
	private static final long serialVersionUID = 1L;

	private PopupDateField fromDateField;
	private PopupDateField toDateField;

	private TimeTrackingTableDisplay tableItem;

	private Label totalHoursLoggingLabel;
	private SplitButton exportButtonControl;

	private ItemTimeLoggingSearchCriteria searchCriteria;

	public TimeTrackingSummaryViewImpl() {
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
		backBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoProjectModule(
								TimeTrackingSummaryViewImpl.this, null));

			}
		});

		backBtn.addStyleName(UIConstants.THEME_BLUE_LINK);

		HorizontalLayout controlBtns = new HorizontalLayout();
		controlBtns.setMargin(new MarginInfo(true, false, true, false));
		controlBtns.addComponent(backBtn);

		contentWrapper.addComponent(controlBtns);

		final HorizontalLayout dateSelectionLayout = new HorizontalLayout();
		dateSelectionLayout.setSpacing(true);
		dateSelectionLayout
				.setMargin(new MarginInfo(false, false, true, false));
		contentWrapper.addComponent(dateSelectionLayout);

		dateSelectionLayout.addComponent(new Label("From:  "));

		fromDateField = new PopupDateField();
		fromDateField.setResolution(Resolution.DAY);
		dateSelectionLayout.addComponent(fromDateField);

		dateSelectionLayout.addComponent(new Label("  To:  "));
		toDateField = new PopupDateField();
		toDateField.setResolution(Resolution.DAY);
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
		controlsPanel.setHeight("30px");
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
		exportButtonControl = new SplitButton(exportBtn);
		exportButtonControl.setWidth(Sizeable.SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		exportButtonControl.addStyleName(UIConstants.THEME_GRAY_LINK);
		exportButtonControl.setIcon(MyCollabResource
				.newResource("icons/16/export.png"));

		VerticalLayout popupButtonsControl = new VerticalLayout();
		exportButtonControl.setContent(popupButtonsControl);

		Button exportPdfBtn = new Button("Pdf");
		FileDownloader pdfDownloader = new FileDownloader(
				constructStreamResource(ReportExportType.PDF));
		pdfDownloader.extend(exportPdfBtn);
		exportPdfBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/pdf.png"));
		exportPdfBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportPdfBtn);

		Button exportExcelBtn = new Button("Excel");
		FileDownloader excelDownloader = new FileDownloader(
				constructStreamResource(ReportExportType.EXCEL));
		excelDownloader.extend(exportExcelBtn);
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

	private StreamResource constructStreamResource(
			final ReportExportType exportType) {
		LazyStreamSource streamSource = new LazyStreamSource() {
			private static final long serialVersionUID = 1L;

			@Override
			protected StreamSource buildStreamSource() {
				return new SimpleGridExportItemsStreamResource.AllItems<ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
						"Time Tracking Report", new RpParameterBuilder(
								tableItem.getDisplayColumns()), exportType,
						ApplicationContextUtil
								.getSpringBean(ItemTimeLoggingService.class),
						searchCriteria, SimpleItemTimeLogging.class);
			}
		};
		StreamResource res = new StreamResource(streamSource,
				ExportItemsStreamResource.getDefaultExportFileName(exportType));
		return res;
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

		ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil
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
