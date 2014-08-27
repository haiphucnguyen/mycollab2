package com.esofthead.mycollab.mobile.shell;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver;
import com.esofthead.mycollab.mobile.module.project.ProjectUrlResolver;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class ShellUrlResolver extends UrlResolver {

	public ShellUrlResolver() {
		this.addSubResolver("crm", new CrmUrlResolver().build());
		this.addSubResolver("project", new ProjectUrlResolver().build());
	}

	public void navigateByFragement(String fragement) {
		if (fragement != null && fragement.length() > 0) {
			String[] tokens = fragement.split("/");
			this.handle(tokens);
		} else {
			EventBusFactory.getInstance().post(
					new ShellEvent.GotoMainPage(UI.getCurrent(), null));
		}
	}

	@Override
	protected void defaultPageErrorHandler() {
		EventBusFactory.getInstance().post(
				new ShellEvent.GotoMainPage(UI.getCurrent(), null));
	}

}
