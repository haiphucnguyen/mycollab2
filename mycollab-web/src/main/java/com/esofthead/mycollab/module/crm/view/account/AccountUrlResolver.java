package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class AccountUrlResolver extends UrlResolver {
	public AccountUrlResolver() {
		this.addSubResolver("list", new AccountListUrlResolver());
		this.addSubResolver("preview", new AccountPreviewUrlResolver());
	}

	public static class AccountListUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new AccountEvent.GotoList(this, null));
		}
	}

	public static class AccountPreviewUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new AccountEvent.GotoRead(this, accountId));
		}
	}
}
