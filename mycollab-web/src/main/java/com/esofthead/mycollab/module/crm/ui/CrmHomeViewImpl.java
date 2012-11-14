package com.esofthead.mycollab.module.crm.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@Component
public class CrmHomeViewImpl extends AbstractView implements CrmHomeView{

	@Override
	protected void initializeLayout() {
		this.addComponent(new Label("Home"));
	}

}
