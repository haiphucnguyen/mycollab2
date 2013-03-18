package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ActivityUrlResolver extends UrlResolver {
	public ActivityUrlResolver() {
		this.addSubResolver("calendar", new ActivityCalendartUrlResolver());
		this.addSubResolver("todo", new ActivityTodoAddUrlResolver());
		this.addSubResolver("task", new ActivityTaskUrlResolver());
		this.addSubResolver("meeting", new MeetingUrlResolver());
		this.addSubResolver("call", new CallUrlResolver());
	}

	public static class ActivityCalendartUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ActivityEvent.GotoCalendar(this, null));
		}
	}

	public static class ActivityTodoAddUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ActivityEvent.GotoTodoList(this, null));
		}
	}
}
