package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ProjectUrlResolver extends UrlResolver {
	public ProjectUrlResolver() {
		this.addSubResolver("dashboard", new ProjectPageUrlResolver());
	}

	public static class ProjectPageUrlResolver extends UrlResolver {

		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoProjectPage(this, null));
		}
	}
}
