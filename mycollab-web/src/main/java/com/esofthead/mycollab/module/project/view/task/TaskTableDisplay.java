/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.BeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskTableDisplay extends
		BeanTable<ProjectTaskService, TaskSearchCriteria, SimpleTask> {

	public TaskTableDisplay(String[] visibleColumns, String[] columnHeaders) {
		super(AppContext.getSpringBean(ProjectTaskService.class),
				SimpleTask.class, visibleColumns, columnHeaders);

		this.addGeneratedColumn("taskname", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleTask task = TaskTableDisplay.this
						.getBeanByIndex(itemId);

				ButtonLink b = new ButtonLink(task.getTaskname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										TaskTableDisplay.this, task, "taskname"));
							}
						});
				b.addStyleName("medium-text");

				if (StringUtil.isNotNullOrEmpty(task.getPriority())) {
					ThemeResource iconPriority = TaskPriorityComboBox
							.getIconResourceByPriority(task.getPriority());

					b.setIcon(iconPriority);
				}

				if (task.getPercentagecomplete() != null
						&& 100d == task.getPercentagecomplete()) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if ((task.getEnddate() != null && (task.getEnddate()
							.before(new GregorianCalendar().getTime())))
							|| (task.getActualenddate() != null && (task
									.getActualenddate()
									.before(new GregorianCalendar().getTime())))
							|| (task.getDeadline() != null && (task
									.getDeadline()
									.before(new GregorianCalendar().getTime())))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				}
				return b;

			}
		});

		this.addGeneratedColumn("percentagecomplete",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleTask task = TaskTableDisplay.this
								.getBeanByIndex(itemId);
						Double percomp = (task.getPercentagecomplete() == null) ? new Double(
								0) : task.getPercentagecomplete() / 100;
						ProgressIndicator progress = new ProgressIndicator(
								new Float(percomp));
						progress.setPollingInterval(1000 * 60 * 60 * 24);
						progress.setWidth("100px");
						return progress;
					}
				});

		this.addGeneratedColumn("startdate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleTask task = TaskTableDisplay.this
						.getBeanByIndex(itemId);
				return new Label(AppContext.formatDate(task.getStartdate()));

			}
		});

		this.addGeneratedColumn("deadline", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleTask task = TaskTableDisplay.this
						.getBeanByIndex(itemId);
				return new Label(AppContext.formatDate(task.getDeadline()));

			}
		});

		this.addGeneratedColumn("id", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleTask task = TaskTableDisplay.this
						.getBeanByIndex(itemId);
				if ((task.getPercentagecomplete() != null && task
						.getPercentagecomplete() != 100)
						|| task.getPercentagecomplete() == null) {
					Button b = new Button(null, new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							fireTableEvent(new TableClickEvent(
									TaskTableDisplay.this, task, "id"));
						}
					});
					b.setIcon(new ThemeResource("icons/16/close.png"));
					b.setStyleName("link");
					b.setDescription("Close this task");
					return b;
				} else {
					return new Label();
				}

			}
		});

		this.setColumnExpandRatio("taskname", 1);
		this.setColumnWidth("assignUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);
		this.setColumnWidth("startdate", UIConstants.TABLE_DATE_WIDTH);
		this.setColumnWidth("deadline", UIConstants.TABLE_DATE_WIDTH);
		this.setColumnWidth("percentagecomplete",
				UIConstants.TABLE_M_LABEL_WIDTH);
		this.setWidth("100%");
	}
}
