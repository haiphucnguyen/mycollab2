package com.esofthead.mycollab.module.project.view.time;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
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

	private Label lbTimeRange;

	public TimeTrackingListViewImpl() {

		this.setSpacing(true);
		this.setMargin(false, true, true, true);

		Label titleLbl = new Label("Time tracking");
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

		lbTimeRange = new Label("", Label.CONTENT_XHTML);
		this.addComponent(lbTimeRange);

		initUI();
	}

	private void setTimeRange() {
		RangeDateSearchField rangeField = itemTimeLogginSearchCriteria
				.getRangeDate();

		String fromDate = AppContext.formatDate(rangeField.getFrom());
		String toDate = AppContext.formatDate(rangeField.getTo());

		lbTimeRange.setValue("From: <strong>" + fromDate
				+ "</strong> To: <strong>" + toDate + "</strong>");
	}

	private void initUI() {
		tableItem = new PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
				AppContext.getSpringBean(ItemTimeLoggingService.class),
				SimpleItemTimeLogging.class, new String[] { "logUserFullName",
						"type", "createdtime", "logvalue" }, new String[] {
						"User", "Type", "Created Time", "Time" });

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

		tableItem.addGeneratedColumn("type", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleItemTimeLogging itemLogging = tableItem
						.getBeanByIndex(itemId);

				ButtonLink b = null;

				if (itemLogging.getType().equals(MonitorTypeConstants.PRJ_BUG)) {

					BugService bugService = AppContext
							.getSpringBean(BugService.class);
					SimpleBug bug = bugService.findBugById(itemLogging
							.getTypeid());

					b = new ButtonLink(bug.getSummary(),
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

					if (BugStatusConstants.CLOSE.equals(bug.getStatus())) {
						b.addStyleName(UIConstants.LINK_COMPLETED);
					} else if (bug.getDuedate() != null
							&& (bug.getDuedate().before(new GregorianCalendar()
									.getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				} else if (itemLogging.getType().equals(
						MonitorTypeConstants.PRJ_TASK)) {
					ProjectTaskService taskService = AppContext
							.getSpringBean(ProjectTaskService.class);
					SimpleTask task = taskService.findTaskById(itemLogging
							.getTypeid());

					b = new ButtonLink(task.getTaskname(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(Button.ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new TaskEvent.GotoRead(this,
													itemLogging.getTypeid()));
								}
							});
					b.setIcon(new ThemeResource(
							"icons/16/project/task.png"));

					if (task.getPercentagecomplete() != null
							&& 100d == task.getPercentagecomplete()) {
						b.addStyleName(UIConstants.LINK_COMPLETED);
					} else {
						if ("Pending".equals(task.getStatus())) {
							b.addStyleName(UIConstants.LINK_PENDING);
						} else if ((task.getEnddate() != null && (task
								.getEnddate().before(new GregorianCalendar()
								.getTime())))
								|| (task.getActualenddate() != null && (task
										.getActualenddate()
										.before(new GregorianCalendar()
												.getTime())))
								|| (task.getDeadline() != null && (task
										.getDeadline()
										.before(new GregorianCalendar()
												.getTime())))) {
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
		tableItem.setColumnWidth("logUserFullName", UIConstants.TABLE_EX_LABEL_WIDTH);
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
