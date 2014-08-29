package com.esofthead.mycollab.mobile.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver;
import com.esofthead.mycollab.mobile.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.domain.MeetingWithBLOBs;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 * 
 */
public class MeetingUrlResolver extends CrmUrlResolver {
	public MeetingUrlResolver() {
		this.addSubResolver("add", new MeetingAddUrlResolver());
		this.addSubResolver("edit", new MeetingEditUrlResolver());
		this.addSubResolver("preview", new MeetingPreviewUrlResolver());
	}

	public static class MeetingAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new ActivityEvent.MeetingAdd(this, new MeetingWithBLOBs()));
		}
	}

	public static class MeetingEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int meetingId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new ActivityEvent.MeetingEdit(this, meetingId));
		}
	}

	public static class MeetingPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new ActivityEvent.MeetingRead(this, accountId));
		}
	}
}
