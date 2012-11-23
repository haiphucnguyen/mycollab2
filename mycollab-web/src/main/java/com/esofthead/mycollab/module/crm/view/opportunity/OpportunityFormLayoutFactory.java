package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class OpportunityFormLayoutFactory implements
		IFormLayoutFactory {

	protected GridFormLayoutHelper informationLayout;

	protected GridFormLayoutHelper descriptionLayout;

	@Override
	public Layout getLayout() {
		AddViewLayout opportunityAddLayout = new AddViewLayout("Opportunity");
		opportunityAddLayout.addTopControls(createButtonControls());

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		Label organizationHeader = new Label("Account Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = new GridFormLayoutHelper(2, 6);
		informationLayout.getLayout().setWidth("900px");
		layout.addComponent(informationLayout.getLayout());

		descriptionLayout = new GridFormLayoutHelper(2, 1);
		descriptionLayout.getLayout().setWidth("900px");
		Label descHeader = new Label("Description");
		descHeader.setStyleName("h2");
		layout.addComponent(descHeader);
		layout.addComponent(descriptionLayout.getLayout());

		opportunityAddLayout.addBody(layout);
		opportunityAddLayout.addBottomControls(createButtonControls());
		return opportunityAddLayout;
	}

	protected abstract Layout createButtonControls();

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.addComponent(propertyId.equals("opportunityname"),
				field, "Opportunity Name", 0, 0);
		informationLayout.addComponent(propertyId.equals("currencyid"), field,
				"Currency", 0, 1);
		informationLayout.addComponent(propertyId.equals("amount"), field,
				"Amount", 0, 2);
		informationLayout.addComponent(propertyId.equals("salesstage"), field,
				"Sales Stage", 0, 3);
		informationLayout.addComponent(propertyId.equals("probability"), field,
				"Probability", 0, 4);
		informationLayout.addComponent(propertyId.equals("nextstep"), field,
				"Next Step", 0, 5);

		informationLayout.addComponent(propertyId.equals("accountid"), field,
				"Account Name", 1, 0);
		informationLayout.addComponent(propertyId.equals("expectedcloseddate"),
				field, "Expected Close Date", 1, 1);
		informationLayout.addComponent(propertyId.equals("opportunitytype"),
				field, "Type", 1, 2);
		informationLayout.addComponent(propertyId.equals("source"), field,
				"Source", 1, 3);
		informationLayout.addComponent(propertyId.equals("campaignid"), field,
				"Campaign", 1, 4);

		if (propertyId.equals("description")) {
			descriptionLayout.addComponent(field, "Description", 0, 0);
		}
	}

}
