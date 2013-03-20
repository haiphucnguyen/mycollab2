package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class TaskUrlResolver extends UrlResolver {
	public TaskUrlResolver() {
		this.addSubResolver("dashboard", new DashboardUrlResolver());
	}

	private static class DashboardUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);
			
		}
	}
}
