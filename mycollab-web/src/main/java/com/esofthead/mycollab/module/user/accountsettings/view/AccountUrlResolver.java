package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.accountsettings.team.view.RoleUrlResolver;
import com.esofthead.mycollab.module.user.accountsettings.team.view.UserUrlResolver;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ModuleHelper;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class AccountUrlResolver extends UrlResolver {
	public AccountUrlResolver() {
		this.addSubResolver("preview", new ReadUrlResolver());
		this.addSubResolver("user", new UserUrlResolver());
		this.addSubResolver("role", new RoleUrlResolver());
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

	private static class ReadUrlResolver extends UrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ProfileEvent.GotoProfileView(this, null));
		}
	}
}
