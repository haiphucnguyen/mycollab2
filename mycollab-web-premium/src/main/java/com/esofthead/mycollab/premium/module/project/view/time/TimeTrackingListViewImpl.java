package com.esofthead.mycollab.premium.module.project.view.time;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ExportTimeLoggingStreamResource;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.localization.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class TimeTrackingListViewImpl extends AbstractPageView implements
TimeTrackingListView {
	private static final long serialVersionUID = 3742030333599796165L;

	private TimeTrackingTableDisplay tableItem;
	private final ItemTimeLoggingSearchPanel itemTimeLoggingPanel;
	private ItemTimeLoggingSearchCriteria itemTimeLogginSearchCriteria;

	private SplitButton exportButtonControl;
	private final ItemTimeLoggingService itemTimeLoggingService;

	private final Label lbTimeRange;
	private EntryComponentLayout entryComponentLayout;
	private boolean isNeedConstructLayout;

	public TimeTrackingListViewImpl() {
		this.setMargin(new MarginInfo(false, true, false, true));
		final CssLayout headerWrapper = new CssLayout();
		isNeedConstructLayout = true;

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
				if (isNeedConstructLayout) {
					isNeedConstructLayout = false;
					entryComponentLayout = new EntryComponentLayout();
					//int index = TimeTrackingListViewImpl.this.getComponentIndex(headerWrapper) + 1;
					int index=0;
					TimeTrackingListViewImpl.this.itemTimeLoggingPanel.addComponent(entryComponentLayout, index);
				}
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

	public class EntryComponentLayout extends AbstractPageView {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper gridLayout;

		public EntryComponentLayout() {
			super();

			this.setWidth("100%");
			this.addStyleName("timeAdd-popup");
			constructBody();
		}

		private void constructBody() {
			Label headerLbl = new Label("Add New Entry");
			headerLbl.setStyleName("popup-header");
			headerLbl.setHeight("34px");

			this.addComponent(headerLbl);
			this.setComponentAlignment(headerLbl, Alignment.MIDDLE_LEFT);

			gridLayout = new GridFormLayoutHelper(3, 1);
			gridLayout.getLayout().setWidth("100%");
			gridLayout.getLayout().setStyleName(UIConstants.COLORED_GRIDLAYOUT);
			gridLayout.getLayout().setMargin(false);
			gridLayout.getLayout().setSpacing(true);

			final AssignmentSelectionComboBox ticketComboBox = new AssignmentSelectionComboBox();
			ticketComboBox.setImmediate(true);
			ticketComboBox.setNullSelectionAllowed(false);

			final TextField descriptionField = new TextField();
			final NumbericTextField hourField = new NumbericTextField();

			final DateField dateField = new DateField();
			dateField.setResolution(Resolution.DAY);
			gridLayout.addComponent(dateField, "Date", 0, 0, "190px");
			gridLayout.addComponent(hourField, "Hours", 1, 0, "190px");
			gridLayout.addComponent(ticketComboBox, "Ticket", 2, 0, "190px");
			gridLayout.getLayout().setColumnExpandRatio(3, 1.0f);

			this.addComponent(gridLayout.getLayout());

			HorizontalLayout bottomWapper = new HorizontalLayout();
			bottomWapper.setWidth("100%");
			HorizontalLayout controllGroupBtn = new HorizontalLayout();
			controllGroupBtn.setSpacing(true);
			controllGroupBtn.setMargin(true);
			UiUtils.addComponent(bottomWapper, controllGroupBtn, Alignment.MIDDLE_CENTER);
			bottomWapper.setStyleName("popup-groupBtn");

			

			Button saveBtn = new Button("Save", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					ProjectGenericTask projectGenericTask = ticketComboBox
							.getCurrentItem();
					if (projectGenericTask == null) {
						NotificationUtil
						.showErrorNotification("Please choose assignment");
						return;
					}
					try {
						Double hourValue = Double
								.parseDouble(hourField.getValue());
						ItemTimeLogging item = new ItemTimeLogging();
						item.setNote(descriptionField.getValue());
						if (projectGenericTask.getType().equals("Bug")) {
							item.setType("Project-Bug");
						} else if (projectGenericTask.getType().equals("Task")) {
							item.setType("Project-Task");
						} else {
							throw new MyCollabException(
									"Does not support any assignments except task and bug");
						}

						item.setTypeid(projectGenericTask.getTypeId());
						item.setProjectid(projectGenericTask.getProjectId());
						item.setSaccountid(AppContext.getAccountId());
						item.setCreatedtime(dateField.getValue());
						item.setLogvalue(hourValue);
						item.setLoguser(AppContext.getUsername());
						TimeTrackingListViewImpl.this.itemTimeLoggingService
						.saveWithSession(item, AppContext.getUsername());

						TimeTrackingListViewImpl.this.isNeedConstructLayout = true;
						TimeTrackingListViewImpl.this.itemTimeLoggingPanel.removeComponent(entryComponentLayout);
						setSearchCriteria(itemTimeLogginSearchCriteria);
					} catch (Exception e) {
						throw new MyCollabException(e);
					}
				}
			});
			saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
			UiUtils.addComponent(controllGroupBtn, saveBtn,
					Alignment.MIDDLE_LEFT);
			Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					TimeTrackingListViewImpl.this.isNeedConstructLayout = true;
					TimeTrackingListViewImpl.this.itemTimeLoggingPanel.removeComponent(entryComponentLayout);
				}
			});
			cancelBtn.addStyleName(UIConstants.THEME_BLANK_LINK);
			UiUtils.addComponent(controllGroupBtn, cancelBtn,
					Alignment.MIDDLE_LEFT);

			this.addComponent(bottomWapper);
		}

		public class AssignmentSelectionComboBox extends ComboBox {
			private static final long serialVersionUID = 1L;

			private String oldText;
			private ProjectGenericTask projectGenericTask;

			public ProjectGenericTask getCurrentItem() {
				return projectGenericTask;
			}

			public AssignmentSelectionComboBox() {
				super();
				this.setFilteringMode(FilteringMode.STARTSWITH);
				this.setTextInputAllowed(true);
				this.setImmediate(true);
				this.setNullSelectionAllowed(true);
				this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
				this.addValueChangeListener(new Property.ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(Property.ValueChangeEvent event) {
						AssignmentSelectionComboBox.this.projectGenericTask = (ProjectGenericTask) AssignmentSelectionComboBox.this
								.getValue();
					}
				});
			}

			@Override
			public void changeVariables(Object source, Map variables) {
				String filterString = (String) variables.get("filter");
				if (filterString != null && filterString.trim().length() > 0) {
					if (filterString.equals(oldText)) {
						return;
					} else {
						oldText = filterString;
					}

					items.removeAllItems();

					ProjectGenericTaskService service = ApplicationContextUtil
							.getSpringBean(ProjectGenericTaskService.class);
					ProjectGenericTaskSearchCriteria criteria = new ProjectGenericTaskSearchCriteria();
					criteria.setName(new StringSearchField(oldText));
					SearchRequest<ProjectGenericTaskSearchCriteria> request = new SearchRequest<ProjectGenericTaskSearchCriteria>(
							criteria, 0, Integer.MAX_VALUE);
					List<ProjectGenericTask> assignments = service
							.findPagableBugAndTaskByCriteria(request);
					for (ProjectGenericTask projectGenericTask : assignments) {
						items.addItem(projectGenericTask);
						AssignmentSelectionComboBox.this.setItemCaption(
								projectGenericTask,
								projectGenericTask.getName());
						if (projectGenericTask.getType().equals("Bug")) {
							AssignmentSelectionComboBox.this
							.setItemIcon(
									projectGenericTask,
									MyCollabResource
									.newResource("icons/16/project/bug.png"));
						} else if (projectGenericTask.getType().equals("Task")) {
							AssignmentSelectionComboBox.this
							.setItemIcon(
									projectGenericTask,
									MyCollabResource
									.newResource("icons/16/project/task.png"));
						}
					}
				}
				super.changeVariables(source, variables);
			}

			@Override
			protected List<?> getOptionsWithFilter(boolean needNullSelectOption) {
				@SuppressWarnings("unchecked")
				List<Object> options = (List<Object>) super
				.getOptionsWithFilter(needNullSelectOption);

				if (getValue() != null && options != null && options.size() > 0) {
					options.add(0, getValue());
				}

				return options;
			}
		}

		public class NumbericTextField extends TextField {
			private static final long serialVersionUID = 1L;
			private static final String regex = "^[0-9]*.?[0-9]*$";
			private String oldText;

			public NumbericTextField() {
				this.addTextChangeListener(new TextChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void textChange(TextChangeEvent event) {
						String inputText = event.getText();
						if (oldText != null
								&& inputText.length() < oldText.length()) {
							NumbericTextField.this.setValue(inputText);
							return;
						}
						try {
							Double.parseDouble(inputText);
							NumbericTextField.this.setValue(inputText);
							oldText = inputText;
						} catch (Exception e) {
							if (oldText == null)
								NumbericTextField.this.setValue("");
							else
								NumbericTextField.this.setValue(oldText);
						}
					}
				});
			}
		}
	}
}
