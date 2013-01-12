/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class TaskListReadPresenter extends AbstractPresenter<TaskListReadView> {

    public TaskListReadPresenter() {
        super(TaskListReadView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        TaskContainer taskContainer = (TaskContainer) container;
        taskContainer.removeAllComponents();
        
        taskContainer.addComponent(view.getWidget());
    }
}
