package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class TaskPresenter extends AbstractPresenter<TaskView> {

    private static final long serialVersionUID = 1L;

    public TaskPresenter() {
        super(TaskView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        view = (TaskView) projectViewContainer.gotoSubView("Tasks");
        view.displayTakLists();
    }
}
