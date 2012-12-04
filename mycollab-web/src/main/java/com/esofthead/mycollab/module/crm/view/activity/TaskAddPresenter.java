package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class TaskAddPresenter extends CrmGenericPresenter<TaskAddView> {
	private static final long serialVersionUID = 1L;

	public TaskAddPresenter(TaskAddView view) {
		this.view = view;
	}
	
	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		view.editItem((Task) data.getParams());
	}
}
