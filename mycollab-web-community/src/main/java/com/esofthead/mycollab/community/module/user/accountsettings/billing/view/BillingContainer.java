package com.esofthead.mycollab.community.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class BillingContainer extends AbstractView implements IBillingContainer {
	private static final long serialVersionUID = 1L;

	public BillingContainer() {
		this.addComponent(new Label(
				"Module is not presented for community edition"));
	}

}
