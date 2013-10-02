package com.esofthead.mycollab.module.crm.view.file;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.events.DocumentEvent;
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver;

public class FileUrlResolver extends CrmUrlResolver {
	public FileUrlResolver() {
		this.addSubResolver("dashboard", new FileDashboardUrlResolver());
	}

	public static class FileDashboardUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new DocumentEvent.GotoDashboard(this, null));
		}
	}
}
