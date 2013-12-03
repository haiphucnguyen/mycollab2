package com.esofthead.mycollab.pages;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;

public class PrivacyPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public PrivacyPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	public IModel getPageTitle() {
		return new Model<String>("MyCollab - Privacy");
	}

	@Override
	public IModel getDescription() {
		return new Model<String>(
				"MyCollab privacy terms admit to keep clients' information securely and safety");
	}

	@Override
	public IModel getKeywords() {
		return new Model<String>(
				"project management tool, business tools, crm system, online collaboration, cloud office, documents management, online office");
	}

}
