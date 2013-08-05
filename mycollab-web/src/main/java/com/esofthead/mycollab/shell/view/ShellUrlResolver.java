package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.module.crm.view.CrmUrlResolver;
import com.esofthead.mycollab.module.file.view.FileUrlResolver;
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountUrlResolver;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ShellUrlResolver extends UrlResolver {
	public ShellUrlResolver() {
		super();
		this.addSubResolver("crm", new CrmUrlResolver().build());
		this.addSubResolver("project", new ProjectUrlResolver().build());
		this.addSubResolver("account", new AccountUrlResolver().build());
		this.addSubResolver("document", new FileUrlResolver().build());
	}

	@Override
	protected void defaultPageErrorHandler() {
	}
}
