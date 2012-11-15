package com.esofthead.mycollab.module.user.ui.accountsettings;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@Component
public class AccountSettingViewImpl extends AbstractView implements
		AccountSettingView {

	@Override
	protected void initializeLayout() {
		this.addComponent(new Label("Account Setting"));
		
	}

}
