package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class CampaignUrlResolver extends UrlResolver {
	public CampaignUrlResolver() {
		this.addSubResolver("list", new CampaignListUrlResolver());
		this.addSubResolver("add", new CampaignAddUrlResolver());
		this.addSubResolver("edit", new CampaignEditUrlResolver());
		this.addSubResolver("preview", new CampaignPreviewUrlResolver());
	}

	public static class CampaignListUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new CampaignEvent.GotoList(this, null));
		}
	}

	public static class CampaignAddUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new CampaignEvent.GotoAdd(this, new Account()));
		}
	}

	public static class CampaignEditUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new CampaignEvent.GotoEdit(this, accountId));
		}
	}

	public static class CampaignPreviewUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new CampaignEvent.GotoRead(this, accountId));
		}
	}
}
