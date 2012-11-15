package com.esofthead.mycollab.module.user.ui.accountsettings;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@Component
public class UserInformationViewImpl extends AbstractView implements UserInformationView {

	@Override
	protected void initializeLayout() {
		this.addComponent(new Label("UserInformation"));
		
		
	}

}
