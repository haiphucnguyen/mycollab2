package com.esofthead.mycollab.mobile.module.crm;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.shell.ModuleHelper;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class CrmUrlResolver extends UrlResolver {

	public UrlResolver build() {
		return this;
	}

	@Override
	public void handle(String... params) {
		if (!ModuleHelper.isCurrentCrmModule()) {
			EventBusFactory.getInstance().post(
					new ShellEvent.GotoCrmModule(this, params));
		} else {
			super.handle(params);
		}
	}

	@Override
	protected void defaultPageErrorHandler() {
		EventBusFactory.getInstance().post(
				new ShellEvent.GotoCrmModule(this, null));

	}

}
