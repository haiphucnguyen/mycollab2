/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskListReadViewImpl extends AbstractView implements TaskListReadView {

    private SimpleTaskList taskList;

    @Override
    public void previewItem(SimpleTaskList item) {
        
    }

    @Override
    public SimpleTaskList getItem() {
        return taskList;
    }
}
