package com.esofthead.mycollab.mobile.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver;
import com.esofthead.mycollab.mobile.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 * 
 */
public class CallUrlResolver extends CrmUrlResolver {
	public CallUrlResolver() {
		this.addSubResolver("add", new CallAddUrlResolver());
		this.addSubResolver("edit", new CallEditUrlResolver());
		this.addSubResolver("preview", new CallPreviewUrlResolver());
	}

	public static class CallAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new ActivityEvent.CallAdd(this, new CallWithBLOBs()));
		}
	}

	public static class CallEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int meetingId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new ActivityEvent.CallEdit(this, meetingId));
		}
	}

	public static class CallPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new ActivityEvent.CallRead(this, accountId));
		}
	}
}
