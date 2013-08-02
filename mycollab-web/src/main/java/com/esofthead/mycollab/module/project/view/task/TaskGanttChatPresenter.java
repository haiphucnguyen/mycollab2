package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class TaskGanttChatPresenter extends
		AbstractPresenter<TaskGanttChartView> {
	private static final long serialVersionUID = 1L;

	public TaskGanttChatPresenter() {
		super(TaskGanttChartView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		TaskContainer taskContainer = (TaskContainer) container;
		taskContainer.removeAllComponents();

		taskContainer.addComponent(view.getWidget());
		view.displayGanttChart();
	}

}
