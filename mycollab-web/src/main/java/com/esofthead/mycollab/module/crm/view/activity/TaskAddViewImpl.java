package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

public class TaskAddViewImpl extends AbstractView implements TaskAddView{
	private static final long serialVersionUID = 1L;

	public TaskAddViewImpl() {
		super();
		this.addComponent(new Label("AAA"));
	}
}
