package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ScheduleUrlResolver extends UrlResolver {
	public ScheduleUrlResolver() {
		this.addSubResolver("dashboard", new DashboardUrlResolver());
		this.addSubResolver("task", new TaskUrlResolver());
		this.addSubResolver("taskgroup", new TaskGroupUrlResolver());
	}

	private static class DashboardUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);
			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new TaskGroupScreenData.GotoDashboard());
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
