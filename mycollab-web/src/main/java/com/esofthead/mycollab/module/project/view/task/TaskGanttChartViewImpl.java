package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
		this.addComponent(backToListBtn);

		bodyContent = new VerticalLayout();
		bodyContent.setSizeFull();
		this.addComponent(bodyContent);
	}

	@Override
	public void displayGanttChart(TaskSearchCriteria searchCriteria) {
		bodyContent.removeAllComponents();

		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setStatus(new StringSearchField("Open"));
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));

		ganttChartWidget = new GanttChartDisplayWidget();
		ganttChartWidget.setSearchCriteria(criteria);
		bodyContent.addComponent(ganttChartWidget);
		bodyContent.setExpandRatio(ganttChartWidget, 1.0f);
	}
}
