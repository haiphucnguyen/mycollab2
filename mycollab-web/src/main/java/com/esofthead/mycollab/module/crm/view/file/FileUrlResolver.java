package com.esofthead.mycollab.module.crm.view.file;

import com.esofthead.mycollab.module.crm.events.DocumentEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class FileUrlResolver extends UrlResolver {
	public FileUrlResolver() {
		this.addSubResolver("dashboard", new FileDashboardUrlResolver());
	}

	public static class FileDashboardUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new DocumentEvent.GotoDashboard(this, null));
		}
	}
}
