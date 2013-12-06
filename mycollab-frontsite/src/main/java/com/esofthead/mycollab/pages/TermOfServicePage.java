package com.esofthead.mycollab.pages;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.esofthead.mycollab.base.BasePage;

public class TermOfServicePage extends BasePage {
	private static final long serialVersionUID = 1L;

	public TermOfServicePage() {
	}

	@Override
	public IModel getPageTitle() {
		return new Model<String>("MyCollab - Terms of Service");
	}

	@Override
	public IModel getDescription() {
		return new Model<String>(
				"MyCollab is a secure cloud office that enables you to manage documents, projects, bug and issue tracking, milestones, time tracking, team and customer relations and more in one place.");
	}

	@Override
	public IModel getKeywords() {
		return new Model<String>(
				"project management tool, business tools, crm system, online collaboration, cloud office, documents management, online office");
	}
}
