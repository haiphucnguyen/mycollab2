package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class TaskGanttChartViewImpl extends AbstractView implements
		TaskGanttChartView {
	private static final long serialVersionUID = 1L;

	private final VerticalLayout bodyContent;
	private GanttChartDisplayWidget ganttChartWidget;

	public TaskGanttChartViewImpl() {
		this.setSpacing(true);
		Label titleLbl = new Label("Gantt View");
		titleLbl.setStyleName("h2");
		this.addComponent(titleLbl);

		HorizontalLayout headerPanel = new HorizontalLayout();
		headerPanel.setWidth("100%");
		this.addComponent(headerPanel);

		Button backToListBtn = new Button("Back to list view",
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
		backToListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		headerPanel.addComponent(backToListBtn);

		Label filterLbl = new Label("Filter:");
		headerPanel.addComponent(filterLbl);
		headerPanel.setComponentAlignment(filterLbl, Alignment.MIDDLE_RIGHT);
		final PopupButton taskGroupSelection = new PopupButton("All Tasks");
		VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("200px");

		Button allTasksBtn = new Button("All Tasks",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						taskGroupSelection.setPopupVisible(false);
						taskGroupSelection.setCaption("All Tasks");
						displayGanttChart();

					}
				});
		allTasksBtn.setStyleName("link");
		filterBtnLayout.addComponent(allTasksBtn);

		Button activeTaskBtn = new Button("Active Tasks",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						taskGroupSelection.setPopupVisible(false);
						taskGroupSelection.setCaption("Active Tasks");
						displayOpenTasks();

					}
				});
		activeTaskBtn.setStyleName("link");
		filterBtnLayout.addComponent(activeTaskBtn);

		Button closeTaskBtn = new Button("Closed Tasks",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						taskGroupSelection.setPopupVisible(false);
						taskGroupSelection.setCaption("Closed Tasks");
						displayClosedTasks();

					}
				});
		closeTaskBtn.setStyleName("link");
		filterBtnLayout.addComponent(closeTaskBtn);

		taskGroupSelection.addStyleName("link");
		taskGroupSelection.addComponent(filterBtnLayout);
		headerPanel.addComponent(taskGroupSelection);

		bodyContent = new VerticalLayout();
		bodyContent.setSizeFull();
		this.addComponent(bodyContent);

		ganttChartWidget = new GanttChartDisplayWidget();
		bodyContent.addComponent(ganttChartWidget);
		bodyContent.setExpandRatio(ganttChartWidget, 1.0f);
	}

	private TaskListSearchCriteria createBaseSearcgCriteria() {
		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		return criteria;
	}

	private void displayOpenTasks() {
		TaskListSearchCriteria criteria = createBaseSearcgCriteria();
		criteria.setStatus(new StringSearchField("Open"));
		ganttChartWidget.setSearchCriteria(criteria);
	}

	private void displayClosedTasks() {
		TaskListSearchCriteria criteria = createBaseSearcgCriteria();
		criteria.setStatus(new StringSearchField("Closed"));
		ganttChartWidget.setSearchCriteria(criteria);
	}

	@Override
	public void displayGanttChart() {
		displayOpenTasks();

	}
}
