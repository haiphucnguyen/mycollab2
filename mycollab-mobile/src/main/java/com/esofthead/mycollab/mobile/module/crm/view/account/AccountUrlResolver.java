package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver;
import com.esofthead.mycollab.mobile.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.domain.Account;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class AccountUrlResolver extends CrmUrlResolver {
	public AccountUrlResolver() {
		this.addSubResolver("list", new AccountListUrlResolver());
		this.addSubResolver("preview", new AccountPreviewUrlResolver());
		this.addSubResolver("add", new AccountAddUrlResolver());
		this.addSubResolver("edit", new AccountEditUrlResolver());
	}

	public static class AccountListUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new AccountEvent.GotoList(this, null));
		}
	}

	public static class AccountAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new AccountEvent.GotoAdd(this, new Account()));
		}
	}

	public static class AccountEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new AccountEvent.GotoEdit(this, accountId));
		}
	}

	public static class AccountPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new AccountEvent.GotoRead(this, accountId));
		}
	}
}
