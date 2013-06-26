package com.esofthead.mycollab.module.project.view.time;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

public class TimeTrackingTableDisplay
		extends
		PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> {
	private static final long serialVersionUID = 1L;

	public TimeTrackingTableDisplay(String[] visibleColumns,
			String[] columnHeaders) {
		super(AppContext.getSpringBean(ItemTimeLoggingService.class),
				SimpleItemTimeLogging.class, visibleColumns, columnHeaders);

		this.addGeneratedColumn("logUserFullName", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleItemTimeLogging timeItem = TimeTrackingTableDisplay.this
						.getBeanByIndex(itemId);

				return new ProjectUserLink(timeItem.getLoguser(), timeItem
						.getLogUserAvatarId(), timeItem.getLogUserFullName(),
						true, true);

			}
		});

		this.addGeneratedColumn("summary", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleItemTimeLogging itemLogging = TimeTrackingTableDisplay.this
						.getBeanByIndex(itemId);

				ButtonLink b = null;

				if (itemLogging.getType().equals(MonitorTypeConstants.PRJ_BUG)) {

					b = new ButtonLink(itemLogging.getSummary(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(
										final Button.ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new BugEvent.GotoRead(this,
													itemLogging.getTypeid()));
								}
							});
					b.setIcon(MyCollabResource
							.newResource("icons/16/project/bug.png"));

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
								public void buttonClick(
										final Button.ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new TaskEvent.GotoRead(this,
													itemLogging.getTypeid()));
								}
							});
					b.setIcon(MyCollabResource
							.newResource("icons/16/project/task.png"));

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

		this.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleItemTimeLogging monitorItem = TimeTrackingTableDisplay.this
						.getBeanByIndex(itemId);
				final Label l = new Label();
				l.setValue(AppContext.formatDateTime(monitorItem
						.getCreatedtime()));
				return l;
			}
		});

		this.addGeneratedColumn("logvalue", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleItemTimeLogging itemTimeLogging = TimeTrackingTableDisplay.this
						.getBeanByIndex(itemId);
				final Label l = new Label();
				l.setValue(itemTimeLogging.getLogvalue());
				return l;
			}
		});

		this.setWidth("100%");

		this.setColumnExpandRatio("type", 1.0f);
		this.setColumnWidth("logUserFullName", UIConstants.TABLE_EX_LABEL_WIDTH);
		this.setColumnWidth("createdtime", UIConstants.TABLE_X_LABEL_WIDTH);
		this.setColumnWidth("logvalue", UIConstants.TABLE_S_LABEL_WIDTH);
	}

}
