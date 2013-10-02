package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver;

public class ActivityTaskUrlResolver extends CrmUrlResolver {
	public ActivityTaskUrlResolver() {
		this.addSubResolver("add", new TaskAddUrlResolver());
		this.addSubResolver("edit", new TaskEditUrlResolver());
		this.addSubResolver("preview", new TaskPreviewUrlResolver());
	}

	public static class TaskAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ActivityEvent.TaskAdd(this, new Meeting()));
		}
	}

	public static class TaskEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int meetingId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new ActivityEvent.TaskEdit(this, meetingId));
		}
	}

	public static class TaskPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new ActivityEvent.TaskRead(this, accountId));
		}
	}
}
