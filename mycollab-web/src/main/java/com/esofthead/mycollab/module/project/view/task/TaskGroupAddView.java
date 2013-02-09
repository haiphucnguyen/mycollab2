/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 *
 * @author haiphucnguyen
 */
public interface TaskGroupAddView extends IFormAddView<TaskList> {

    HasEditFormHandlers<TaskList> getEditFormHandlers();
    
}
