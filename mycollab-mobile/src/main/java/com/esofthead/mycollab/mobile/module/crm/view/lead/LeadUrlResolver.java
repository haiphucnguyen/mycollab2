package com.esofthead.mycollab.mobile.module.crm.view.lead;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver;
import com.esofthead.mycollab.mobile.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.domain.Lead;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class LeadUrlResolver extends CrmUrlResolver {
	public LeadUrlResolver() {
		this.addSubResolver("list", new LeadListUrlResolver());
		this.addSubResolver("preview", new LeadPreviewUrlResolver());
		this.addSubResolver("add", new LeadAddUrlResolver());
		this.addSubResolver("edit", new LeadEditUrlResolver());
	}

	public static class LeadListUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new LeadEvent.GotoList(this, null));
		}
	}

	public static class LeadAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new LeadEvent.GotoAdd(this, new Lead()));
		}
	}

	public static class LeadEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int leadId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new LeadEvent.GotoEdit(this, leadId));
		}
	}

	public static class LeadPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int leadId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new LeadEvent.GotoRead(this, leadId));
		}
	}

}
