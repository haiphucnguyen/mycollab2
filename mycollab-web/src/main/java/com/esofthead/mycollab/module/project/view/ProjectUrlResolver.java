package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.view.bug.BugUrlResolver;
import com.esofthead.mycollab.module.project.view.message.MessageUrlResolver;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneUrlResolver;
import com.esofthead.mycollab.module.project.view.task.ScheduleUrlResolver;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ProjectUrlResolver extends UrlResolver {
	public ProjectUrlResolver() {
		this.addSubResolver("dashboard", new ProjectPageUrlResolver());
		this.addSubResolver("message", new MessageUrlResolver());
		this.addSubResolver("milestone", new MilestoneUrlResolver());
		this.addSubResolver("task", new ScheduleUrlResolver());
		this.addSubResolver("bug", new BugUrlResolver());
	}

	public static class ProjectPageUrlResolver extends UrlResolver {

		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoProjectPage(this, null));
		}
	}
}
