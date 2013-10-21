package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.events.CrmEvent;
import com.esofthead.mycollab.module.crm.view.account.AccountUrlResolver;
import com.esofthead.mycollab.module.crm.view.activity.ActivityUrlResolver;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignUrlResolver;
import com.esofthead.mycollab.module.crm.view.cases.CaseUrlResolver;
import com.esofthead.mycollab.module.crm.view.contact.ContactUrlResolver;
import com.esofthead.mycollab.module.crm.view.file.FileUrlResolver;
import com.esofthead.mycollab.module.crm.view.lead.LeadUrlResolver;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityUrlResolver;
import com.esofthead.mycollab.module.crm.view.setting.CrmSettingUrlResolver;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.ModuleHelper;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class CrmUrlResolver extends UrlResolver {
	public UrlResolver build() {
		this.addSubResolver("dashboard", new CrmDashboardUrlResolver());
		this.addSubResolver("account", new AccountUrlResolver());
		this.addSubResolver("contact", new ContactUrlResolver());
		this.addSubResolver("campaign", new CampaignUrlResolver());
		this.addSubResolver("lead", new LeadUrlResolver());
		this.addSubResolver("opportunity", new OpportunityUrlResolver());
		this.addSubResolver("cases", new CaseUrlResolver());
		this.addSubResolver("activity", new ActivityUrlResolver());
		this.addSubResolver("file", new FileUrlResolver());
		this.addSubResolver("setting", new CrmSettingUrlResolver());
		return this;
	}

	@Override
	public void handle(String... params) {
		if (!ModuleHelper.isCurrentCrmModule()) {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoCrmModule(this, params));
		} else {
			super.handle(params);
		}
	}

	@Override
	protected void defaultPageErrorHandler() {
		EventBus.getInstance().fireEvent(
				new ShellEvent.GotoCrmModule(this, null));

	}

	public static class CrmDashboardUrlResolver extends CrmUrlResolver {

		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(new CrmEvent.GotoHome(this, null));
		}
	}
}
