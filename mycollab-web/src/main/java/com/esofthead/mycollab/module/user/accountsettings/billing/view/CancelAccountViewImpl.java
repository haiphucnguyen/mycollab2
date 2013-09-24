package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class CancelAccountViewImpl extends AbstractView implements
		CancelAccountView {
	private static final long serialVersionUID = 1L;

	public CancelAccountViewImpl() {
		this.addComponent(new Label("Cancel account"));
	}
}
