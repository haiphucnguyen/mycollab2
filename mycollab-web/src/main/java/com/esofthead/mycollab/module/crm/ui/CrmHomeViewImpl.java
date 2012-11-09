package com.esofthead.mycollab.module.crm.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
public class CrmHomeViewImpl extends AbstractView implements CrmHomeView{

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(new Label("Home"));
		return layout;
	}

}
