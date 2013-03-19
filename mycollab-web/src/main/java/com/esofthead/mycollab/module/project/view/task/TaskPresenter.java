/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskPresenter extends AbstractPresenter<TaskContainer> {
	private static final long serialVersionUID = 1L;

	public TaskPresenter() {
		super(TaskContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Tasks");

		view.removeAllComponents();

		AbstractPresenter presenter = null;

		if (data instanceof TaskContainer.PreviewTaskData) {
			presenter = PresenterResolver.getPresenter(TaskReadPresenter.class);
		} else if (data instanceof TaskContainer.PreviewTaskListData) {
			presenter = PresenterResolver
					.getPresenter(TaskGroupReadPresenter.class);
		} else if (data instanceof TaskContainer.EditTaskData) {
			presenter = PresenterResolver.getPresenter(TaskAddPresenter.class);
		} else if (data instanceof TaskContainer.AddTaskData) {
			presenter = PresenterResolver.getPresenter(TaskAddPresenter.class);
		} else if (data instanceof TaskContainer.EditTaskListData) {
			presenter = PresenterResolver
					.getPresenter(TaskGroupAddPresenter.class);
		} else if (data instanceof TaskContainer.ReorderTaskListRequest) {
			presenter = PresenterResolver
					.getPresenter(TaskGroupReorderPresenter.class);
		} else if (data instanceof TaskContainer.DisplayGanttChartRequest) {
			presenter = PresenterResolver
					.getPresenter(TaskGanttChatPresenter.class);
		} else {
			presenter = PresenterResolver
					.getPresenter(TaskGroupDisplayPresenter.class);
		}

		presenter.go(view, data);
	}

	@Override
	public void handleChain(ComponentContainer container,
			PageActionChain pageActionChain) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Tasks");

		view.removeAllComponents();

		AbstractPresenter presenter;

		ScreenData pageAction = pageActionChain.peek();
		if (pageAction instanceof TaskScreenData.Read) {
			presenter = PresenterResolver.getPresenter(TaskReadPresenter.class);
		} else if (pageAction instanceof TaskGroupScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(TaskGroupReadPresenter.class);
		} else {
			throw new UnsupportedOperationException(
					"Do not support page action " + pageAction);
		}

		presenter.go(view, pageAction);
	}
}
