package com.esofthead.mycollab.module.project.view.time;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.addons.lazyquerycontainer.BeanQueryFactory;
import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.LazyQueryDefinition;
import org.vaadin.hene.splitbutton.SplitButtonExt;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ExportTimeLoggingStreamResource;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.localization.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class TimeTrackingListViewImpl extends AbstractView implements
		TimeTrackingListView {
	private static final long serialVersionUID = 1L;

	private TimeTrackingTableDisplay tableItem;
	private final ItemTimeLoggingSearchPanel itemTimeLoggingPanel;
	private ItemTimeLoggingSearchCriteria itemTimeLogginSearchCriteria;

	private SplitButtonExt exportButtonControl;
	private final ItemTimeLoggingService itemTimeLoggingService;

	private final Label lbTimeRange;
	private EntryComponentLayout entryComponentLayout;
	private boolean isNeedConstructLayout = true;

	public TimeTrackingListViewImpl() {
		this.setMargin(false, true, true, true);

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

		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);

		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth("100%");
		headerLayout.setSpacing(true);
		headerWrapper.addComponent(headerLayout);
		this.lbTimeRange = new Label("", Label.CONTENT_XHTML);
		headerLayout.addComponent(this.lbTimeRange);
		headerLayout.setComponentAlignment(this.lbTimeRange,
				Alignment.MIDDLE_LEFT);
		headerLayout.setExpandRatio(this.lbTimeRange, 1.0f);

		Button addNewEntryBtn = new Button("Add entry",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (isNeedConstructLayout) {
							isNeedConstructLayout = false;
							entryComponentLayout = new EntryComponentLayout();
							TimeTrackingListViewImpl.this
									.addComponent(entryComponentLayout);
						}
					}
				});
		addNewEntryBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
		headerLayout.addComponent(addNewEntryBtn);
		headerLayout.setComponentAlignment(addNewEntryBtn,
				Alignment.MIDDLE_RIGHT);

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

		headerLayout.addComponent(exportButtonControl);
		headerLayout.setComponentAlignment(this.exportButtonControl,
				Alignment.MIDDLE_RIGHT);
		this.addComponent(headerWrapper);

		this.tableItem = new TimeTrackingTableDisplay(Arrays.asList(
				new TableViewField("Summary", "summary",
						UIConstants.TABLE_X_LABEL_WIDTH), new TableViewField(
						"User", "logUserFullName",
						UIConstants.TABLE_X_LABEL_WIDTH), new TableViewField(
						"Hours", "logvalue", UIConstants.TABLE_S_LABEL_WIDTH),
				new TableViewField("Created Time", "createdtime",
						UIConstants.TABLE_DATE_TIME_WIDTH)));

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

	private void downloadExportStreamCommand(ReportExportType exportType) {
		final String title = "Time of Project "
				+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
						.getProject().getName() != null) ? CurrentProjectVariables
						.getProject().getName() : "");
		ExportTimeLoggingStreamResource exportStream = new ExportTimeLoggingStreamResource(
				title, exportType,
				ApplicationContextUtil
						.getSpringBean(ItemTimeLoggingService.class),
				TimeTrackingListViewImpl.this.itemTimeLogginSearchCriteria);
		final Resource res = new StreamResource(exportStream,
				exportStream.getDefaultExportFileName(),
				AppContext.getApplication());
		AppContext.getApplication().getMainWindow().open(res, "_blank");
		exportButtonControl.setPopupVisible(false);
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

	public class EntryComponentLayout extends AbstractView {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper gridLayout;

		public EntryComponentLayout() {
			super();

			this.setWidth("100%");
			this.setMargin(true);
			this.setSpacing(true);
			this.addStyleName("timeAdd-popup");
			constructBody();
		}

		private void constructBody() {
			Label headerLbl = new Label("Add new entry");
			headerLbl.addStyleName("h2");

			this.addComponent(headerLbl);
			this.setComponentAlignment(headerLbl, Alignment.MIDDLE_LEFT);

			gridLayout = new GridFormLayoutHelper(2, 2);
			gridLayout.getLayout().setMargin(true);
			gridLayout.getLayout().setWidth("100%");
			gridLayout.getLayout().setStyleName(UIConstants.COLORED_GRIDLAYOUT);

			final BeanQueryFactory<TimeTrackingLazyBeanQuery> queryFactory = new BeanQueryFactory<TimeTrackingLazyBeanQuery>(
					TimeTrackingLazyBeanQuery.class);

			final Map<String, Object> queryConfiguration = new HashMap<String, Object>();
			queryFactory.setQueryConfiguration(queryConfiguration);

			LazyQueryDefinition definition = new LazyQueryDefinition(true, 10);
			LazyQueryContainer container = new LazyQueryContainer(definition,
					queryFactory);

			final ComboBox ticketField = new ComboBox("", container);
			ticketField.setNewItemsAllowed(false);
			ticketField.setItemCaptionMode(ComboBox.ITEM_CAPTION_MODE_PROPERTY);
			ticketField.setItemCaptionPropertyId("summary");
			ticketField.setImmediate(true);

			gridLayout.addComponent(new TextField(), "Date", 0, 0, "300px");
			gridLayout.addComponent(ticketField, "Ticket", 1, 0, "300px");
			gridLayout.addComponent(new TextField(), "Hours", 0, 1, "300px");
			gridLayout.addComponent(new TextField(), "Description", 1, 1,
					"300px");

			this.addComponent(gridLayout.getLayout());

			HorizontalLayout controllGroupBtn = new HorizontalLayout();
			controllGroupBtn.setSpacing(true);
			controllGroupBtn.setMargin(true);

			Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					TimeTrackingListViewImpl.this.isNeedConstructLayout = true;
					TimeTrackingListViewImpl.this
							.removeComponent(entryComponentLayout);
				}
			});
			cancelBtn.addStyleName(UIConstants.THEME_LINK);
			controllGroupBtn.addComponent(cancelBtn);
			controllGroupBtn.setComponentAlignment(cancelBtn,
					Alignment.MIDDLE_LEFT);

			Button saveBtn = new Button("Save", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub

				}
			});
			saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controllGroupBtn.addComponent(saveBtn);
			controllGroupBtn.setComponentAlignment(saveBtn,
					Alignment.MIDDLE_LEFT);

			this.addComponent(controllGroupBtn);
		}
	}
}
