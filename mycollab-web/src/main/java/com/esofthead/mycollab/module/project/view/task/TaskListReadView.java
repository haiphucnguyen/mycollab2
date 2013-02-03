/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 *
 * @author haiphucnguyen
 */
public interface TaskListReadView extends IPreviewView<SimpleTaskList> {
    
    HasPreviewFormHandlers<SimpleTaskList> getPreviewFormHandlers();
}
