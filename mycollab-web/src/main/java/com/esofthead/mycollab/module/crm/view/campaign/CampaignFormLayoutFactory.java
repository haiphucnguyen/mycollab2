package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public abstract class CampaignFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private String title;
	private IFormLayoutFactory informationLayout;

	public CampaignFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 campaignFormLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/campaign.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			campaignFormLayout.addControlButtons(topPanel);
		}

		informationLayout = new DynaFormLayout(CrmTypeConstants.CAMPAIGN,
				CampaignDefaultDynaFormLayoutFactory.getForm());
		campaignFormLayout.addBody(informationLayout.getLayout());

		return campaignFormLayout;
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}
}
