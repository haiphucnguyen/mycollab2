package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver;
import com.esofthead.mycollab.vaadin.events.EventBus;

public class MeetingUrlResolver extends CrmUrlResolver {
	public MeetingUrlResolver() {
		this.addSubResolver("add", new MeetingAddUrlResolver());
		this.addSubResolver("edit", new MeetingEditUrlResolver());
		this.addSubResolver("preview", new MeetingPreviewUrlResolver());
	}

	public static class MeetingAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ActivityEvent.MeetingAdd(this, new Meeting()));
		}
	}

	public static class MeetingEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int meetingId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new ActivityEvent.MeetingEdit(this, meetingId));
		}
	}

	public static class MeetingPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new ActivityEvent.MeetingRead(this, accountId));
		}
	}
}
