package com.esofthead.mycollab.module.project.view.task;

import java.util.Arrays;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class TaskListViewImpl extends AbstractPageView implements TaskListView {

	private static final long serialVersionUID = 1L;
	private TaskSearchPanel taskSearchPanel;
	private TaskSearchCriteria searchCriteria;
	private TaskSearchTableDisplay tableItem;
	private final VerticalLayout taskListLayout;

	public TaskListViewImpl() {

		// this.setMargin(new MarginInfo(false, true, false, true));

		this.taskSearchPanel = new TaskSearchPanel();

		this.taskListLayout = new VerticalLayout();
		this.generateDisplayTable();

		addComponent(this.taskSearchPanel);

		taskSearchPanel
				.addSearchHandler(new SearchHandler<TaskSearchCriteria>() {

					@Override
					public void onSearch(TaskSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});
		addComponent(this.taskListLayout);
	}

	private void generateDisplayTable() {

		this.tableItem = new TaskSearchTableDisplay(TaskTableFieldDef.id,
				Arrays.asList(TaskTableFieldDef.taskname,
						TaskTableFieldDef.startdate, TaskTableFieldDef.duedate,
						TaskTableFieldDef.assignee,
						TaskTableFieldDef.percentagecomplete));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleTask task = (SimpleTask) event.getData();
						if ("taskname".equals(event.getFieldName())) {
							EventBus.getInstance()
									.fireEvent(
											new TaskEvent.GotoRead(
													TaskListViewImpl.this, task
															.getId()));
						}
					}
				});

		searchCriteria = new TaskSearchCriteria();
		searchCriteria.setProjectid(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		tableItem.setSearchCriteria(searchCriteria);

		this.taskListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<TaskSearchCriteria> getSearchHandlers() {
		return this.taskSearchPanel;
	}

	@Override
	public IPagedBeanTable<TaskSearchCriteria, SimpleTask> getPagedBeanTable() {
		return this.tableItem;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

}
