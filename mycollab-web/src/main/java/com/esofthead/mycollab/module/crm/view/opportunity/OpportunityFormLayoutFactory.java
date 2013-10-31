package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public abstract class OpportunityFormLayoutFactory implements
		IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private String title;
	private IFormLayoutFactory opportunityInformation;

	public OpportunityFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 opportunityAddLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/opportunity.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			opportunityAddLayout.addControlButtons(topPanel);
		}

		opportunityInformation = new DynaFormLayout(
				CrmTypeConstants.OPPORTUNITY,
				OpportunityDefaultDynaFormLayoutFactory.getForm());
		opportunityAddLayout.addBody(opportunityInformation.getLayout());

		return opportunityAddLayout;
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public void attachField(Object propertyId, Field field) {
		opportunityInformation.attachField(propertyId, field);
	}
}
