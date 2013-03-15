package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class CallUrlResolver extends UrlResolver {
	public CallUrlResolver() {
		this.addSubResolver("add", new CallAddUrlResolver());
		this.addSubResolver("edit", new CallEditUrlResolver());
		this.addSubResolver("preview", new CallPreviewUrlResolver());
	}

	public static class CallAddUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ActivityEvent.CallAdd(this, new Meeting()));
		}
	}

	public static class CallEditUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int meetingId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new ActivityEvent.CallEdit(this, meetingId));
		}
	}

	public static class CallPreviewUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new ActivityEvent.CallRead(this, accountId));
		}
	}
}
