package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.ModuleHelper;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class FileUrlResolver extends UrlResolver {

	public UrlResolver build() {
		this.addSubResolver("list", new FileListUrlResolver());
		return this;
	}

	@Override
	public void handle(String... params) {
		if (!ModuleHelper.isCurrentFileModule()) {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoFileModule(this, params));
		} else {
			super.handle(params);
		}
	}

	@Override
	protected void defaultPageErrorHandler() {

	}

	public static class FileListUrlResolver extends CrmUrlResolver {

		@Override
		protected void handlePage(String... params) {
			// EventBus.getInstance().fireEvent(
			// new ShellEvent.GotoFileModule(this, null));
		}
	}

}
