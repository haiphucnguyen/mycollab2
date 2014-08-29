package com.esofthead.mycollab.premium.module.project.view.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
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
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
@ViewComponent(scope = ViewScope.PROTOTYPE)
public class TimeTrackingListViewImpl extends AbstractPageView implements
		TimeTrackingListView {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"EEEE, dd MMMM yyyy");
	private static final List<TableViewField> FIELDS = Arrays.asList(TimeTableFieldDef.summary, TimeTableFieldDef.logUser,
			TimeTableFieldDef.logValue, TimeTableFieldDef.billable, TimeTableFieldDef.id);

	private static final long serialVersionUID = 3742030333599796165L;

	private ItemTimeLoggingSearchPanel itemTimeLoggingPanel;
	private ItemTimeLoggingSearchCriteria itemTimeLogginSearchCriteria;

	private SplitButton exportButtonControl;
	private final ItemTimeLoggingService itemTimeLoggingService;

	private final Label lbTimeRange;

	private VerticalLayout layoutItem;
	private MarginInfo marginInfo = new MarginInfo(true, false, false, false);

	public TimeTrackingListViewImpl() {
		this.setMargin(new MarginInfo(false, true, false, true));
		final HorizontalLayout headerWrapper = new HorizontalLayout();

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
		headerWrapper.addStyleName(UIConstants.LAYOUT_LOG);

		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth("100%");
		headerLayout.setSpacing(true);
		headerWrapper.addComponent(headerLayout);
		this.lbTimeRange = new Label("", ContentMode.HTML);
		this.lbTimeRange.addStyleName(UIConstants.TEXT_LOG_DATE_FULL);
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

		this.layoutItem = new VerticalLayout();
		this.layoutItem.addStyleName(UIConstants.LAYOUT_LOG);
		this.layoutItem.setWidth("100%");
		this.addComponent(this.layoutItem);
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

		this.itemTimeLogginSearchCriteria.setIsBillable(new BooleanSearchField(true));
		Double billableHour = this.itemTimeLoggingService
				.getTotalHoursByCriteria(this.itemTimeLogginSearchCriteria);
		if (billableHour == null || billableHour < 0) {
			billableHour = 0d;
		}

		this.itemTimeLogginSearchCriteria.setIsBillable(new BooleanSearchField(false));
		Double nonbillableHour = this.itemTimeLoggingService
				.getTotalHoursByCriteria(this.itemTimeLogginSearchCriteria);
		if (nonbillableHour == null || nonbillableHour < 0) {
			nonbillableHour = 0d;
		}

		
		this.itemTimeLogginSearchCriteria.setIsBillable(null);
		final Double totalHour = this.itemTimeLoggingService
				.getTotalHoursByCriteria(this.itemTimeLogginSearchCriteria);

		// TODO: check Japanese sentence, remove todo after fix
		if (totalHour != null && totalHour > 0) {
			this.lbTimeRange.setValue(AppContext.getMessage(
					TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
							fromDate, toDate, totalHour, billableHour, nonbillableHour));
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

		this.layoutItem.removeAllComponents();

		@SuppressWarnings("unchecked")
		List<SimpleItemTimeLogging> itemTimeLoggingList = itemTimeLoggingService
				.findPagableListByCriteria(new SearchRequest<ItemTimeLoggingSearchCriteria>(
						itemTimeLogginSearchCriteria));
		Date current = new Date(0);
		double billable = 0, nonbillable = 0;
		List<SimpleItemTimeLogging> list = new ArrayList<SimpleItemTimeLogging>();

		for (SimpleItemTimeLogging itemTimeLogging : itemTimeLoggingList) {
			if (DateTimeUtils.compareByDate(itemTimeLogging.getLogforday(),
					current) > 0) {
				showRecord(current, list, billable, nonbillable);

				current = itemTimeLogging.getLogforday();
				list.clear();
				billable = nonbillable = 0;
			}

			list.add(itemTimeLogging);
			billable += itemTimeLogging.getIsbillable() ? itemTimeLogging
					.getLogvalue() : 0;
			nonbillable += !itemTimeLogging.getIsbillable() ? itemTimeLogging
					.getLogvalue() : 0;
		}
        showRecord(current, list, billable, nonbillable);
	}
	
	private void showRecord(Date date, List<SimpleItemTimeLogging> list,
			Double billable, Double nonbillable) {
		if (list.size() > 0) {
			Label logForDay = new Label(DATE_FORMAT.format(date));
			logForDay.addStyleName(UIConstants.TEXT_LOG_DATE);
			this.layoutItem.addComponent(logForDay);

			TimeTrackingTableDisplay table = new TimeTrackingTableDisplay(FIELDS);
			table.addStyleName(UIConstants.FULL_BORDER_TABLE);
			table.setMargin(marginInfo);
			table.addTableListener(this.tableClickListener);
			table.setCurrentDataList(list);
			this.layoutItem.addComponent(table);

			Label labelTotalHours = new Label(("Total Hours: " + (billable + nonbillable)));
			labelTotalHours.addStyleName(UIConstants.TEXT_LOG_HOURS_TOTAL);
			this.layoutItem.addComponent(labelTotalHours);

			Label labelBillableHours = new Label(("Billable Hours: " + billable));
			labelBillableHours.setStyleName(UIConstants.TEXT_LOG_HOURS);
			this.layoutItem.addComponent(labelBillableHours);

			Label labelNonbillableHours = new Label(("Non Billable Hours: " + nonbillable));
			labelNonbillableHours.setStyleName(UIConstants.TEXT_LOG_HOURS);
			this.layoutItem.addComponent(labelNonbillableHours);
		}
	}

	private TableClickListener tableClickListener = new TableClickListener() {
		private static final long serialVersionUID = 1L;

		@Override
		public void itemClick(final TableClickEvent event) {
			final SimpleItemTimeLogging itemLogging = (SimpleItemTimeLogging) event
					.getData();
			if ("summary".equals(event.getFieldName())) {
				if (ProjectTypeConstants.BUG.equals(itemLogging.getType())) {
					EventBusFactory.getInstance()
							.post(new BugEvent.GotoRead(this, itemLogging
									.getTypeid()));
				} else if (ProjectTypeConstants.TASK.equals(itemLogging
						.getType())) {
					EventBusFactory.getInstance().post(
							new TaskEvent.GotoRead(this, itemLogging
									.getTypeid()));
				} else if (ProjectTypeConstants.RISK.equals(itemLogging
						.getType())) {
					EventBusFactory.getInstance().post(
							new RiskEvent.GotoRead(this, itemLogging
									.getTypeid()));
				} else if (ProjectTypeConstants.PROBLEM.equals(itemLogging
						.getType())) {
					EventBusFactory.getInstance().post(
							new ProblemEvent.GotoRead(this, itemLogging
									.getTypeid()));
				}
			} else if ("delete".equals(event.getFieldName())) {
				ConfirmDialogExt
						.show(UI.getCurrent(),
								AppContext.getMessage(
										GenericI18Enum.DIALOG_DELETE_TITLE,
										SiteConfiguration.getSiteName()),
								AppContext
										.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
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
													AppContext.getUsername(),
													AppContext.getAccountId());

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
	};
}
