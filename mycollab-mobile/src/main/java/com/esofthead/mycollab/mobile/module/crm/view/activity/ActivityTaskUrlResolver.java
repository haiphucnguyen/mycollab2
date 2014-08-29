package com.esofthead.mycollab.mobile.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver;
import com.esofthead.mycollab.mobile.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.domain.Task;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 * 
 */
public class ActivityTaskUrlResolver extends CrmUrlResolver {
	public ActivityTaskUrlResolver() {
		this.addSubResolver("add", new TaskAddUrlResolver());
		this.addSubResolver("edit", new TaskEditUrlResolver());
		this.addSubResolver("preview", new TaskPreviewUrlResolver());
	}

	public static class TaskAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new ActivityEvent.TaskAdd(this, new Task()));
		}
	}

	public static class TaskEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int meetingId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new ActivityEvent.TaskEdit(this, meetingId));
		}
	}

	public static class TaskPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int accountId = Integer.parseInt(decodeUrl);
			EventBusFactory.getInstance().post(
					new ActivityEvent.TaskRead(this, accountId));
		}
	}
}
