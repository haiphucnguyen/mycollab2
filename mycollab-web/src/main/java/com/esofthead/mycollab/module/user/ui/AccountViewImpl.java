package com.esofthead.mycollab.module.user.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
public class AccountViewImpl extends AbstractView implements AccountView {

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		return layout;
	}

}
