package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.events.CrmEvent;
import com.esofthead.mycollab.module.crm.view.account.AccountUrlResolver;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class CrmUrlResolver extends UrlResolver {
	public CrmUrlResolver() {
		this.addSubResolver("dashboard", new CrmDashboardUrlResolver());
		this.addSubResolver("account", new AccountUrlResolver());
	}
	
	public static class CrmDashboardUrlResolver extends UrlResolver {

		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(new CrmEvent.GotoHome(this, null));
		}
	}
}
