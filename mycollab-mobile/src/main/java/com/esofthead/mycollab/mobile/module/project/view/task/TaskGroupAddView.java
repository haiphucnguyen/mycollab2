package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 */
public interface TaskGroupAddView extends IFormAddView<SimpleTaskList> {

	HasEditFormHandlers<SimpleTaskList> getEditFormHandlers();

}
