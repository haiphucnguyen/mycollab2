package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public abstract class AccountFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	private final String title;

	private IFormLayoutFactory informationLayout;

	public AccountFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public Layout getLayout() {
		AddViewLayout2 accountAddLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/account.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			accountAddLayout.addControlButtons(topPanel);
		}

		informationLayout = new DynaFormLayout(CrmTypeConstants.ACCOUNT,
				AccountDefaultDynaFormFactory.getForm());

		accountAddLayout.addBody(informationLayout.getLayout());

		return accountAddLayout;
	}
}
