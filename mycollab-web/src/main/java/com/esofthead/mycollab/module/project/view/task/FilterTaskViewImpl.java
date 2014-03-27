package com.esofthead.mycollab.module.project.view.task;

import java.util.Arrays;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.view.parameters.TaskFilterParameter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
@ViewComponent
public class FilterTaskViewImpl extends AbstractPageView implements
		FilterTaskView {
	private static final long serialVersionUID = 1L;

	private Label headerText;
	private VerticalLayout rightColumn;
	private VerticalLayout leftColumn;
	private TaskTableDisplay taskTableDisplay;
	private UnresolvedTaskByAssigneeWidget unresolvedTaskByAssigneeWidget;
	private UnresolvedTaskByPriorityWidget unresolvedTaskByPriorityWidget;

	public FilterTaskViewImpl() {
		this.setMargin(new MarginInfo(false, true, true, true));
		final HorizontalLayout header = new HorizontalLayout();
		header.setSpacing(true);
		header.setMargin(new MarginInfo(true, false, true, false));
		header.setStyleName(UIConstants.HEADER_VIEW);
		header.setWidth("100%");
		Image titleIcon = new Image(null,
				MyCollabResource.newResource("icons/24/project/task.png"));

		headerText = new Label();
		headerText.setSizeUndefined();
		headerText.setStyleName(UIConstants.HEADER_TEXT);

		Button backtoTaskListBtn = new Button("Back to task dashboard",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance()
								.fireEvent(
										new TaskListEvent.GotoTaskListScreen(
												this, null));

					}
				});
		backtoTaskListBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

		UiUtils.addComponent(header, titleIcon, Alignment.TOP_LEFT);
		UiUtils.addComponent(header, headerText, Alignment.MIDDLE_LEFT);
		UiUtils.addComponent(header, backtoTaskListBtn, Alignment.MIDDLE_RIGHT);
		header.setExpandRatio(headerText, 1.0f);

		this.addComponent(header);

		HorizontalLayout contentLayout = new HorizontalLayout();
		contentLayout.setWidth("100%");
		contentLayout.setSpacing(true);
		this.addComponent(contentLayout);

		this.taskTableDisplay = new TaskTableDisplay(
				TaskTableFieldDef.taskname, Arrays.asList(
						TaskTableFieldDef.startdate, TaskTableFieldDef.duedate,
						TaskTableFieldDef.percentagecomplete));
		this.taskTableDisplay
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
							EventBus.getInstance().fireEvent(
									new TaskEvent.GotoRead(
											FilterTaskViewImpl.this, task
													.getId()));
						}
					}
				});
		taskTableDisplay.setWidth("100%");
		taskTableDisplay.setStyleName("filter-task-table");

		leftColumn = new VerticalLayout();
		leftColumn.addComponent(taskTableDisplay);
		leftColumn.setStyleName("depotComp");
		leftColumn.setMargin(new MarginInfo(true, true, false, false));

		rightColumn = new VerticalLayout();
		rightColumn.setWidth("300px");
		contentLayout.addComponent(leftColumn);
		contentLayout.addComponent(rightColumn);
		contentLayout.setExpandRatio(leftColumn, 1.0f);
		unresolvedTaskByAssigneeWidget = new UnresolvedTaskByAssigneeWidget();
		rightColumn.addComponent(unresolvedTaskByAssigneeWidget);

		unresolvedTaskByPriorityWidget = new UnresolvedTaskByPriorityWidget();
		rightColumn.addComponent(unresolvedTaskByPriorityWidget);
	}

	@Override
	public void filterTasks(TaskFilterParameter filterParam) {
		headerText.setValue(filterParam.getScreenTitle());
		taskTableDisplay.setSearchCriteria(filterParam.getSearchCriteria());

		TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
		searchCriteria.setProjectid(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		searchCriteria.setStatuses(new SetSearchField<String>(SearchField.AND,
				new String[] { "Open" }));

		unresolvedTaskByAssigneeWidget.setSearchCriteria(searchCriteria);
		unresolvedTaskByPriorityWidget.setSearchCriteria(searchCriteria);
	}

}
