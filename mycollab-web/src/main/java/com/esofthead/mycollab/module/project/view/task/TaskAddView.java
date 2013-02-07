/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.AttachmentUploadField;

/**
 *
 * @author haiphucnguyen
 */
public interface TaskAddView extends IFormAddView<Task> {

    HasEditFormHandlers<Task> getEditFormHandlers();
    
    AttachmentUploadField getAttachUploadField();
}
