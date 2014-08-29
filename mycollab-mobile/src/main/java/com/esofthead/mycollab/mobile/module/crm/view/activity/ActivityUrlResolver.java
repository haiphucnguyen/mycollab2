package com.esofthead.mycollab.mobile.module.crm.view.activity;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver;
import com.esofthead.mycollab.mobile.module.crm.events.ActivityEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 * 
 */
public class ActivityUrlResolver extends CrmUrlResolver {
	public ActivityUrlResolver() {
		this.addSubResolver("list", new ActivityListUrlResolver());
		this.addSubResolver("task", new ActivityTaskUrlResolver());
		this.addSubResolver("meeting", new MeetingUrlResolver());
		this.addSubResolver("call", new CallUrlResolver());
	}

	public static class ActivityListUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBusFactory.getInstance().post(
					new ActivityEvent.GotoList(this, null));
		}
	}
}
