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
public class TaskAddPresenter extends AbstractPresenter<TaskAddView> {
    public TaskAddPresenter() {
        super(TaskAddView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
