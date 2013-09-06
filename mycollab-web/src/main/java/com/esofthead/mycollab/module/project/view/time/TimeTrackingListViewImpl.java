package com.esofthead.mycollab.module.project.view.time;

import java.util.Arrays;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.file.resource.FieldExportColumn;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ExportTimeLoggingStreamResource;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.localization.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@ViewComponent
public class TimeTrackingListViewImpl extends AbstractView implements
		TimeTrackingListView {
	private static final long serialVersionUID = 1L;

	private TimeTrackingTableDisplay tableItem;
	private final ItemTimeLoggingSearchPanel itemTimeLoggingPanel;
	private ItemTimeLoggingSearchCriteria itemTimeLogginSearchCriteria;
	private final Button exportBtn;
	private final ItemTimeLoggingService itemTimeLoggingService;
	private static final FieldExportColumn[] EXPORT_COLUMNS = new FieldExportColumn[] {
			new FieldExportColumn("logUserFullName", "User"),
			new FieldExportColumn("type", "Summary", 70),
			new FieldExportColumn("createdtime", "Created Time"),
			new FieldExportColumn("logvalue", "Hours"), };

	private final Label lbTimeRange;

	public TimeTrackingListViewImpl() {
		this.setMargin(false, true, true, true);

		this.itemTimeLoggingService = AppContext
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

		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);

		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth("100%");
		headerWrapper.addComponent(headerLayout);
		this.lbTimeRange = new Label("", Label.CONTENT_XHTML);
		headerLayout.addComponent(this.lbTimeRange);
		headerLayout.setComponentAlignment(this.lbTimeRange,
				Alignment.MIDDLE_LEFT);
		headerLayout.setExpandRatio(this.lbTimeRange, 1.0f);

		this.exportBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.TABLE_EXPORT_BUTTON));
		this.exportBtn.setIcon(MyCollabResource
				.newResource("icons/16/export_excel.png"));
		this.exportBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		headerLayout.addComponent(this.exportBtn);
		this.exportBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final String title = "Time of Project "
						+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
								.getProject().getName() != null) ? CurrentProjectVariables
								.getProject().getName() : "");
				// final Resource res = new StreamResource(
				// new ExportTimeLoggingStreamResource(
				// title,
				// TimeTrackingListViewImpl.EXPORT_COLUMNS,
				// AppContext
				// .getSpringBean(ItemTimeLoggingService.class),
				// TimeTrackingListViewImpl.this.itemTimeLogginSearchCriteria),
				// "timeLogging_list.xls", AppContext.getApplication());
				// AppContext.getApplication().getMainWindow().open(res,
				// "_blank");
			}
		});
		headerLayout.setComponentAlignment(this.exportBtn,
				Alignment.MIDDLE_RIGHT);
		this.addComponent(headerWrapper);

		this.tableItem = new TimeTrackingTableDisplay(Arrays.asList(
				new TableViewField("Summary", "summary",
						UIConstants.TABLE_X_LABEL_WIDTH), new TableViewField(
						"User", "logUserFullName",
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
							if (MonitorTypeConstants.PRJ_BUG.equals(itemLogging
									.getType())) {
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoRead(this, itemLogging
												.getTypeid()));
							} else if (MonitorTypeConstants.PRJ_TASK
									.equals(itemLogging.getType())) {
								EventBus.getInstance().fireEvent(
										new TaskEvent.GotoRead(this,
												itemLogging.getTypeid()));
							}
						}
					}
				});

		this.addComponent(this.tableItem);
	}

	private void setTimeRange() {
		final RangeDateSearchField rangeField = this.itemTimeLogginSearchCriteria
				.getRangeDate();

		final String fromDate = AppContext.formatDate(rangeField.getFrom());
		final String toDate = AppContext.formatDate(rangeField.getTo());

		final Double totalHour = this.itemTimeLoggingService
				.getTotalHoursByCriteria(this.itemTimeLogginSearchCriteria);

		if (totalHour != null && totalHour > 0) {
			this.lbTimeRange.setValue(LocalizationHelper.getMessage(
					TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
					fromDate, toDate, totalHour));
		} else {
			this.lbTimeRange.setValue(LocalizationHelper.getMessage(
					TimeTrackingI18nEnum.TASK_LIST_RANGE, fromDate, toDate));
		}
	}

	@Override
	public void setSearchCriteria(
			final ItemTimeLoggingSearchCriteria searchCriteria) {
		this.itemTimeLogginSearchCriteria = searchCriteria;
		this.tableItem.setSearchCriteria(searchCriteria);
		this.setTimeRange();
	}

}
