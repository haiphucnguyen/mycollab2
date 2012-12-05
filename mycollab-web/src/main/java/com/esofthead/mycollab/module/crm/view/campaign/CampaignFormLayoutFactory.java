package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class CampaignFormLayoutFactory implements IFormLayoutFactory {

	private GridFormLayoutHelper informationLayout;

	private GridFormLayoutHelper campaignGoal;

	private GridFormLayoutHelper descriptionLayout;

	@Override
	public Layout getLayout() {
		AddViewLayout campaignFormLayout = new AddViewLayout("Campaign");
		campaignFormLayout.addTopControls(createButtonControls());

		VerticalLayout layout = new VerticalLayout();
		Label organizationHeader = new Label("Account Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = new GridFormLayoutHelper(2, 6);
		informationLayout.getLayout().setWidth("900px");
		layout.addComponent(informationLayout.getLayout());

		campaignGoal = new GridFormLayoutHelper(2, 4);
		Label addressHeader = new Label("Address Information");
		addressHeader.setStyleName("h2");
		layout.addComponent(addressHeader);
		campaignGoal.getLayout().setWidth("900px");
		layout.addComponent(campaignGoal.getLayout());

		descriptionLayout = new GridFormLayoutHelper(2, 1);
		Label descHeader = new Label("Description");
		descHeader.setStyleName("h2");

		layout.addComponent(descHeader);
		layout.addComponent(descriptionLayout.getLayout());
		descriptionLayout.getLayout().setWidth("900px");

		campaignFormLayout.addBody(layout);
		campaignFormLayout.addBottomControls(createButtonControls());

		return campaignFormLayout;
	}

	abstract protected HorizontalLayout createButtonControls();

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.addComponent(propertyId.equals("campaignname"),
				field, "Name", 0, 0);
		informationLayout.addComponent(propertyId.equals("startdate"), field,
				"Start Date", 0, 1);
		informationLayout.addComponent(propertyId.equals("enddate"), field,
				"End Date", 0, 2);
		informationLayout.addComponent(propertyId.equals("status"), field,
				"Status", 1, 0);
		informationLayout.addComponent(propertyId.equals("type"), field,
				"Type", 1, 1);

		campaignGoal.addComponent(propertyId.equals("currencyid"), field,
				"Currency", 0, 0);
		campaignGoal.addComponent(propertyId.equals("budget"), field, "Budget",
				1, 0);
		campaignGoal.addComponent(propertyId.equals("expectedcost"), field,
				"Expected Cost", 0, 1);
		campaignGoal.addComponent(propertyId.equals("actualcost"), field,
				"Actual Cost", 1, 1);
		campaignGoal.addComponent(propertyId.equals("expectedrevenue"), field,
				"Expected Revenue", 0, 2);

		if (propertyId.equals("description")) {
			descriptionLayout.addComponent(field,
					"Description", 0, 0, 2, UIConstants.DEFAULT_2XCONTROL_WIDTH,
					UIConstants.DEFAULT_2XCONTROL_HEIGHT);
		}
	}

}
