package com.esofthead.mycollab.module.project.view.time;

import java.util.GregorianCalendar;
import java.util.List;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

public class TimeTrackingTableDisplay
		extends
		PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> {
	private static final long serialVersionUID = 1L;

	public TimeTrackingTableDisplay(List<TableViewField> displayColumns) {
		super(ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class),
				SimpleItemTimeLogging.class, displayColumns);

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

				ButtonLink timeTrackingLink = null;

				if (itemLogging.getType().equals(MonitorTypeConstants.PRJ_BUG)) {

					timeTrackingLink = new ButtonLink(itemLogging.getSummary(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(
										final Button.ClickEvent event) {
									fireTableEvent(new TableClickEvent(
											TimeTrackingTableDisplay.this,
											itemLogging, "summary"));
								}
							});
					timeTrackingLink.setIcon(MyCollabResource
							.newResource("icons/16/project/bug.png"));

					if (BugStatusConstants.VERIFIED.equals(itemLogging.getStatus())) {
						timeTrackingLink.addStyleName(UIConstants.LINK_COMPLETED);
					} else if (itemLogging.getDueDate() != null
							&& (itemLogging.getDueDate()
									.before(new GregorianCalendar().getTime()))) {
						timeTrackingLink.addStyleName(UIConstants.LINK_OVERDUE);
					}
				} else if (itemLogging.getType().equals(
						MonitorTypeConstants.PRJ_TASK)) {

					timeTrackingLink = new ButtonLink(itemLogging.getSummary(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(
										final Button.ClickEvent event) {
									fireTableEvent(new TableClickEvent(
											TimeTrackingTableDisplay.this,
											itemLogging, "summary"));
								}
							});
					timeTrackingLink.setIcon(MyCollabResource
							.newResource("icons/16/project/task.png"));

					if (itemLogging.getPercentageComplete() != null
							&& 100d == itemLogging.getPercentageComplete()) {
						timeTrackingLink.addStyleName(UIConstants.LINK_COMPLETED);
					} else {
						if ("Pending".equals(itemLogging.getStatus())) {
							timeTrackingLink.addStyleName(UIConstants.LINK_PENDING);
						} else if (itemLogging.getDueDate() != null
								&& (itemLogging.getDueDate()
										.before(new GregorianCalendar()
												.getTime()))) {
							timeTrackingLink.addStyleName(UIConstants.LINK_OVERDUE);
						}
					}
				}

				timeTrackingLink.addStyleName("link");
				timeTrackingLink.addStyleName(UIConstants.WORD_WRAP);
				timeTrackingLink.setWidth("100%");

				return timeTrackingLink;

			}
		});

		this.addGeneratedColumn("projectName", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleItemTimeLogging itemLogging = TimeTrackingTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink projectLink = new ButtonLink(itemLogging
						.getProjectName(), new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						fireTableEvent(new TableClickEvent(
								TimeTrackingTableDisplay.this, itemLogging,
								"projectName"));

					}
				});
				projectLink.setIcon(MyCollabResource
						.newResource("icons/16/project/project.png"));
				return projectLink;
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

		this.setWidth("100%");
	}

}
