package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class CrmHomeViewImpl extends AbstractView implements CrmHomeView{

	public CrmHomeViewImpl() {
		this.addComponent(new Label("Home"));
	}
}
