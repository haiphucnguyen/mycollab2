package com.esofthead.mycollab.premium.module.project.view.time;

import java.util.Arrays;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.localization.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.reporting.ExportTimeLoggingStreamResource;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.time.TimeTableFieldDef;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd
 * @since 2.0
 * 
 */
@ViewComponent
public class TimeTrackingListViewImpl extends AbstractPageView implements
		TimeTrackingListView {
	private static final long serialVersionUID = 3742030333599796165L;

	private TimeTrackingTableDisplay tableItem;
	private ItemTimeLoggingSearchPanel itemTimeLoggingPanel;
	private ItemTimeLoggingSearchCriteria itemTimeLogginSearchCriteria;

	private SplitButton exportButtonControl;
	private final ItemTimeLoggingService itemTimeLoggingService;

	private final Label lbTimeRange;

	public TimeTrackingListViewImpl() {
		this.setMargin(new MarginInfo(false, true, false, true));
		final CssLayout headerWrapper = new CssLayout();

		this.itemTimeLoggingService = ApplicationContextUtil
				.getSpringBean(ItemTimeLoggingService.class);

		this.itemTimeLoggingPanel = new ItemTimeLoggingSearchPanel();
		this.itemTimeLoggingPanel
				.addSearchHandler(new SearchHandler<ItemTimeLoggingSearchCriteria>() {
					@Override
					public void onSearch(
							final ItemTimeLoggingSearchCriteria criteria) {
						TimeTrackingListViewImpl.this
								.setSearchCriteria(criteria);
					}
				});

		this.addComponent(this.itemTimeLoggingPanel);
		this.itemTimeLoggingPanel.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				AddTimeEntryWindow addTimeEntry = new AddTimeEntryWindow(
						TimeTrackingListViewImpl.this);
				UI.getCurrent().addWindow(addTimeEntry);
			}
		});

		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);

		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth("100%");
		headerLayout.setSpacing(true);
		headerWrapper.addComponent(headerLayout);
		this.lbTimeRange = new Label("", ContentMode.HTML);
		headerLayout.addComponent(this.lbTimeRange);
		headerLayout.setComponentAlignment(this.lbTimeRange,
				Alignment.MIDDLE_LEFT);
		headerLayout.setExpandRatio(this.lbTimeRange, 1.0f);

		Button exportBtn = new Button("Export", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				exportButtonControl.setPopupVisible(true);
			}
		});
		exportButtonControl = new SplitButton(exportBtn);
		exportButtonControl.setStyleName(UIConstants.THEME_GRAY_LINK);
		exportButtonControl.addStyleName(UIConstants.SPLIT_BUTTON);
		exportButtonControl.setIcon(MyCollabResource
				.newResource("icons/16/export.png"));

		VerticalLayout popupButtonsControl = new VerticalLayout();
		exportButtonControl.setContent(popupButtonsControl);

		Button exportPdfBtn = new Button("Pdf");
		FileDownloader exportPdfDownloader = new FileDownloader(
				constructStreamResource(ReportExportType.PDF));
		exportPdfDownloader.extend(exportPdfBtn);
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

		headerLayout.addComponent(exportButtonControl);
		headerLayout.setComponentAlignment(this.exportButtonControl,
				Alignment.MIDDLE_RIGHT);
		this.addComponent(headerWrapper);

		this.tableItem = new TimeTrackingTableDisplay(Arrays.asList(
				TimeTableFieldDef.summary, TimeTableFieldDef.logUser,
				TimeTableFieldDef.logValue, TimeTableFieldDef.billable,
				TimeTableFieldDef.logForDate, TimeTableFieldDef.id));

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
							if (ProjectTypeConstants.BUG.equals(itemLogging
									.getType())) {
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoRead(this, itemLogging
												.getTypeid()));
							} else if (ProjectTypeConstants.TASK
									.equals(itemLogging.getType())) {
								EventBus.getInstance().fireEvent(
										new TaskEvent.GotoRead(this,
												itemLogging.getTypeid()));
							}
							 else if (ProjectTypeConstants.RISK
										.equals(itemLogging.getType())) {
									EventBus.getInstance().fireEvent(
											new RiskEvent.GotoRead(this,
													itemLogging.getTypeid()));
							}
							 else if (ProjectTypeConstants.PROBLEM
										.equals(itemLogging.getType())) {
									EventBus.getInstance().fireEvent(
											new ProblemEvent.GotoRead(this,
													itemLogging.getTypeid()));
							}
						} else if ("delete".equals(event.getFieldName())) {
							ConfirmDialogExt.show(
									UI.getCurrent(),
									AppContext.getMessage(
											GenericI18Enum.DELETE_DIALOG_TITLE,
											SiteConfiguration.getSiteName()),
									AppContext
											.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
									AppContext
											.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
									AppContext
											.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
									new ConfirmDialog.Listener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void onClose(ConfirmDialog dialog) {
											if (dialog.isConfirmed()) {
												ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil
														.getSpringBean(ItemTimeLoggingService.class);
												itemTimeLoggingService.removeWithSession(
														itemLogging.getId(),
														AppContext
																.getUsername(),
														AppContext
																.getAccountId());

												refresh();
											}
										}
									});

						} else if ("edit".equals(event.getFieldName())) {
							TimeTrackingEditViewWindow timeTrackingEdit = new TimeTrackingEditViewWindow(
									TimeTrackingListViewImpl.this, itemLogging);
							UI.getCurrent().addWindow(timeTrackingEdit);
						}
					}
				});

		this.addComponent(this.tableItem);
	}

	private StreamResource constructStreamResource(ReportExportType exportType) {
		final String title = "Time of Project "
				+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
						.getProject().getName() != null) ? CurrentProjectVariables
						.getProject().getName() : "");
		ExportTimeLoggingStreamResource exportStream = new ExportTimeLoggingStreamResource(
				title, exportType,
				ApplicationContextUtil
						.getSpringBean(ItemTimeLoggingService.class),
				TimeTrackingListViewImpl.this.itemTimeLogginSearchCriteria);
		final StreamResource res = new StreamResource(exportStream,
				ExportTimeLoggingStreamResource
						.getDefaultExportFileName(exportType));
		return res;
	}

	private void setTimeRange() {
		final RangeDateSearchField rangeField = this.itemTimeLogginSearchCriteria
				.getRangeDate();

		final String fromDate = AppContext.formatDate(rangeField.getFrom());
		final String toDate = AppContext.formatDate(rangeField.getTo());

		final Double totalHour = this.itemTimeLoggingService
				.getTotalHoursByCriteria(this.itemTimeLogginSearchCriteria);

		if (totalHour != null && totalHour > 0) {
			this.lbTimeRange.setValue(AppContext.getMessage(
					TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
					fromDate, toDate, totalHour));
		} else {
			this.lbTimeRange.setValue(AppContext.getMessage(
					TimeTrackingI18nEnum.TASK_LIST_RANGE, fromDate, toDate));
		}
	}

	@Override
	public void setSearchCriteria(
			final ItemTimeLoggingSearchCriteria searchCriteria) {
		this.itemTimeLogginSearchCriteria = searchCriteria;
		refresh();
	}

	@Override
	public void refresh() {
		this.setTimeRange();
		this.tableItem.setSearchCriteria(itemTimeLogginSearchCriteria);

	}
}
