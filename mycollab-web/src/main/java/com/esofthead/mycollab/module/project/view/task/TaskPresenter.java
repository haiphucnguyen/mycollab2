/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class TaskPresenter extends AbstractPresenter<TaskContainer> {

    public TaskPresenter() {
        super(TaskContainer.class);
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
            presenter = PresenterResolver.getPresenter(TaskListReadPresenter.class);
        } else {
            presenter = PresenterResolver.getPresenter(TaskListDisplayPresenter.class);
        }

        presenter.go(view, data);
    }
}
