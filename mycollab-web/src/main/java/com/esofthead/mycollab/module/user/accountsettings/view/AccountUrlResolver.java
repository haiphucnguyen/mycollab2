package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.accountsettings.team.view.RoleUrlResolver;
import com.esofthead.mycollab.module.user.accountsettings.team.view.UserUrlResolver;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.ModuleHelper;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class AccountUrlResolver extends UrlResolver {
	public UrlResolver build() {
		this.addSubResolver("preview", new ReadUrlResolver());
		this.addSubResolver("billing", new BillingUrlResolver());
		this.addSubResolver("user", new UserUrlResolver());
		this.addSubResolver("role", new RoleUrlResolver());
		return this;
	}

	@Override
	public void handle(String... params) {
		if (!ModuleHelper.isCurrentAccountModule()) {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoUserAccountModule(this, params));
		} else {
			super.handle(params);
		}
	}

	@Override
	protected void defaultPageErrorHandler() {
		EventBus.getInstance().fireEvent(
				new ProfileEvent.GotoProfileView(this, null));

	}

	private static class ReadUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ProfileEvent.GotoProfileView(this, null));
		}
	}

	private static class BillingUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new AccountBillingEvent.GotoSummary(this, null));
		}
	}
}
