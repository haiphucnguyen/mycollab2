package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.accountsettings.team.view.RoleUrlResolver;
import com.esofthead.mycollab.module.user.accountsettings.team.view.UserUrlResolver;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ModuleHelper;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class AccountUrlResolver extends UrlResolver {
	public AccountUrlResolver() {
		this.addSubResolver("preview", new ReadUrlResolver());
		this.addSubResolver("billing", new BillingUrlResolver());
		this.addSubResolver("user", new UserUrlResolver());
		this.addSubResolver("role", new RoleUrlResolver());
	}

	@Override
	public void handle(String... params) {
		if (!ModuleHelper.isCurrentAccountModule()) {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoUserAccountModule(this, params));
		} else {
			try {
				super.handle(params);
			} catch (Exception e) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoUserAccountModule(this, params));
				if (e instanceof MyCollabException) {
					throw (MyCollabException) e;
				} else {
					throw new MyCollabException(e);
				}
			}
		}
	}

	private static class ReadUrlResolver extends UrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ProfileEvent.GotoProfileView(this, null));
		}
	}

	private static class BillingUrlResolver extends UrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new AccountBillingEvent.GotoSummary(this, null));
		}
	}
}
