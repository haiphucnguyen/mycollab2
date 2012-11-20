package com.esofthead.mycollab.module.user.ui.accountsettings;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@Component
public class AccountSettingViewImpl extends AbstractView implements
		AccountSettingView {

	@Override
	protected void initializeLayout() {
		Label header = new Label("Account Settings");
		header.setStyleName("h1");
		this.addComponent(header);
	}
}
