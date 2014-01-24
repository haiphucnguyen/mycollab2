package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.vaadin.mvp.AbstractMobilePageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class AccountListView extends AbstractMobilePageView {

	private static final long serialVersionUID = -500810154594390148L;
	
	public AccountListView() {
		super();
		
		setCaption("Accounts");
		setToggleButton(true);
		initContent();
	}

	private void initContent() {
		final VerticalLayout mainContent = new VerticalLayout();
		mainContent.setSizeFull();
		
		setContent(mainContent);
	}

}
