package com.esofthead.mycollab.mobile.module.crm.view.opportunity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver;
import com.esofthead.mycollab.mobile.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.domain.Account;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class OpportunityUrlResolver extends CrmUrlResolver {
	public OpportunityUrlResolver() {
		this.addSubResolver("list", new OpportunityListUrlResolver());
		this.addSubResolver("add", new OpportunityAddUrlResolver());
		this.addSubResolver("edit", new OpportunityEditUrlResolver());
		this.addSubResolver("preview", new OpportunityPreviewUrlResolver());
	}

	public static class OpportunityListUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new OpportunityEvent.GotoList(this, null));
		}
	}

	public static class OpportunityAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new OpportunityEvent.GotoAdd(this, new Account()));
		}
	}

	public static class OpportunityEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int opportunintyId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new OpportunityEvent.GotoEdit(this, opportunintyId));
		}
	}

	public static class OpportunityPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int opportunintyId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new OpportunityEvent.GotoRead(this, opportunintyId));
		}
	}
}
