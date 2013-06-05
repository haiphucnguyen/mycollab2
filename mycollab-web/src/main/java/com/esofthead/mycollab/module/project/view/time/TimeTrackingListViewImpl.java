package com.esofthead.mycollab.module.project.view.time;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.module.file.ExportTimeLoggingStreamResource;
import com.esofthead.mycollab.module.file.FieldExportColumn;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.localization.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@ViewComponent
public class TimeTrackingListViewImpl extends AbstractView implements
		TimeTrackingListView {
	private static final long serialVersionUID = 1L;

	private PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> tableItem;
	private ItemTimeLoggingSearchPanel itemTimeLoggingPanel;
	private ItemTimeLoggingSearchCriteria itemTimeLogginSearchCriteria;
	private Button exportBtn;
	private ItemTimeLoggingService itemTimeLoggingService;
	private static final FieldExportColumn[] EXPORT_COLUMNS = new FieldExportColumn[] {
			new FieldExportColumn("logUserFullName", "User"),
			new FieldExportColumn("type", "Summary", 70),
			new FieldExportColumn("createdtime", "Created Time"),
			new FieldExportColumn("logvalue", "Hours"), };

	private Label lbTimeRange;

	public TimeTrackingListViewImpl() {

		this.setSpacing(true);
		this.setMargin(false, true, true, true);

		itemTimeLoggingService = AppContext
				.getSpringBean(ItemTimeLoggingService.class);

		Label titleLbl = new Label(
				LocalizationHelper
						.getMessage(TimeTrackingI18nEnum.TIME_RECORD_HEADER));
		titleLbl.setStyleName("h2");
		this.addComponent(titleLbl);

		itemTimeLoggingPanel = new ItemTimeLoggingSearchPanel();
		itemTimeLoggingPanel
				.addSearchHandler(new SearchHandler<ItemTimeLoggingSearchCriteria>() {
					@Override
					public void onSearch(ItemTimeLoggingSearchCriteria criteria) {
						setSearchCriteria(criteria);
					}
				});
		this.addComponent(itemTimeLoggingPanel);

		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth("100%");
		lbTimeRange = new Label("", Label.CONTENT_XHTML);
		headerLayout.addComponent(lbTimeRange);
		headerLayout.setComponentAlignment(lbTimeRange, Alignment.MIDDLE_LEFT);
		headerLayout.setExpandRatio(lbTimeRange, 1.0f);

		exportBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.TABLE_EXPORT_BUTTON));
		exportBtn.setIcon(new ThemeResource("icons/16/export_excel.png"));
		exportBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		headerLayout.addComponent(exportBtn);
		exportBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String title = "Time of Project "
						+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
								.getProject().getName() != null) ? CurrentProjectVariables
								.getProject().getName() : "");
				Resource res = new StreamResource(
						new ExportTimeLoggingStreamResource(
								title,
								EXPORT_COLUMNS,
								AppContext
										.getSpringBean(ItemTimeLoggingService.class),
								itemTimeLogginSearchCriteria),
						"timeLogging_list.xls", AppContext.getApplication());
				AppContext.getApplication().getMainWindow().open(res, "_blank");
			}
		});
		headerLayout.setComponentAlignment(exportBtn, Alignment.MIDDLE_RIGHT);
		this.addComponent(headerLayout);

		initUI();
	}

	private void setTimeRange() {
		RangeDateSearchField rangeField = itemTimeLogginSearchCriteria
				.getRangeDate();

		String fromDate = AppContext.formatDate(rangeField.getFrom());
		String toDate = AppContext.formatDate(rangeField.getTo());

		Double totalHour = itemTimeLoggingService
				.getTotalHoursByCriteria(itemTimeLogginSearchCriteria);

		if (totalHour != null && totalHour > 0) {
			lbTimeRange.setValue(LocalizationHelper.getMessage(
					TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
					fromDate, toDate, totalHour));
		} else {
			lbTimeRange.setValue(LocalizationHelper.getMessage(
					TimeTrackingI18nEnum.TASK_LIST_RANGE, fromDate, toDate));
		}
	}

	private void initUI() {
		tableItem = new PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
				AppContext.getSpringBean(ItemTimeLoggingService.class),
				SimpleItemTimeLogging.class, new String[] { "logUserFullName",
						"summary", "createdtime", "logvalue" }, new String[] {
						"User", "Summary", "Created Time", "Hours" });

		tableItem.addGeneratedColumn("logUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleItemTimeLogging monitorItem = tableItem
								.getBeanByIndex(itemId);

						return new ProjectUserLink(monitorItem.getLoguser(),
								monitorItem.getLogUserFullName(), true, true);

					}
				});

		tableItem.addGeneratedColumn("summary", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleItemTimeLogging itemLogging = tableItem
						.getBeanByIndex(itemId);

				ButtonLink b = null;

				if (itemLogging.getType().equals(MonitorTypeConstants.PRJ_BUG)) {

					b = new ButtonLink(itemLogging.getSummary(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(Button.ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new BugEvent.GotoRead(this,
													itemLogging.getTypeid()));
								}
							});
					b.setIcon(new ThemeResource("icons/16/project/bug.png"));

					if (BugStatusConstants.CLOSE.equals(itemLogging.getStatus())) {
						b.addStyleName(UIConstants.LINK_COMPLETED);
					} else if (itemLogging.getDueDate() != null
							&& (itemLogging.getDueDate()
									.before(new GregorianCalendar().getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				} else if (itemLogging.getType().equals(
						MonitorTypeConstants.PRJ_TASK)) {

					b = new ButtonLink(itemLogging.getSummary(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(Button.ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new TaskEvent.GotoRead(this,
													itemLogging.getTypeid()));
								}
							});
					b.setIcon(new ThemeResource("icons/16/project/task.png"));

					if (itemLogging.getPercentageComplete() != null
							&& 100d == itemLogging.getPercentageComplete()) {
						b.addStyleName(UIConstants.LINK_COMPLETED);
					} else {
						if ("Pending".equals(itemLogging.getStatus())) {
							b.addStyleName(UIConstants.LINK_PENDING);
						} else if (itemLogging.getDueDate() != null
								&& (itemLogging.getDueDate()
										.before(new GregorianCalendar()
												.getTime()))) {
							b.addStyleName(UIConstants.LINK_OVERDUE);
						}
					}
				}

				b.addStyleName("link");
				b.addStyleName(UIConstants.WORD_WRAP);
				b.setWidth("100%");

				return b;

			}
		});

		tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleItemTimeLogging monitorItem = tableItem
						.getBeanByIndex(itemId);
				Label l = new Label();
				l.setValue(AppContext.formatDateTime(monitorItem
						.getCreatedtime()));
				return l;
			}
		});

		tableItem.addGeneratedColumn("logvalue", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleItemTimeLogging itemTimeLogging = tableItem
						.getBeanByIndex(itemId);
				Label l = new Label();
				l.setValue(itemTimeLogging.getLogvalue());
				return l;
			}
		});

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("type", 1.0f);
		tableItem.setColumnWidth("logUserFullName",
				UIConstants.TABLE_EX_LABEL_WIDTH);
		tableItem
				.setColumnWidth("createdtime", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("logvalue", UIConstants.TABLE_S_LABEL_WIDTH);

		this.addComponent(tableItem);
	}

	@Override
	public void setSearchCriteria(ItemTimeLoggingSearchCriteria searchCriteria) {
		this.itemTimeLogginSearchCriteria = searchCriteria;
		tableItem.setSearchCriteria(searchCriteria);
		setTimeRange();
	}

}
