package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface AssignmentAddView extends IFormAddView<Task> {
	HasEditFormHandlers<Task> getEditFormHandlers();
}
