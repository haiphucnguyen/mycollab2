package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class AccountFormLayoutFactory implements IFormLayoutFactory {

	private GridFormLayoutHelper informationLayout;

	private GridFormLayoutHelper addressLayout;

	private GridFormLayoutHelper descriptionLayout;

	@Override
	public Layout getLayout() {
		AddViewLayout accountAddLayout = new AddViewLayout("Account");

		accountAddLayout.addTopControls(createTopPanel());

		VerticalLayout layout = new VerticalLayout();

		Label organizationHeader = new Label("Account Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = new GridFormLayoutHelper(2, 6);
		informationLayout.getLayout().setWidth("900px");
		layout.addComponent(informationLayout.getLayout());
		layout.setComponentAlignment(informationLayout.getLayout(),
				Alignment.BOTTOM_CENTER);

		addressLayout = new GridFormLayoutHelper(2, 4);
		Label addressHeader = new Label("Address Information");
		addressHeader.setStyleName("h2");
		layout.addComponent(addressHeader);
		addressLayout.getLayout().setWidth("900px");
		layout.addComponent(addressLayout.getLayout());
		layout.setComponentAlignment(addressLayout.getLayout(),
				Alignment.BOTTOM_CENTER);

		descriptionLayout = new GridFormLayoutHelper(1, 1);
		Label descHeader = new Label("Description");
		descHeader.setStyleName("h2");
		layout.addComponent(descHeader);
		descriptionLayout.getLayout().setWidth("900px");
		descriptionLayout.getLayout().setColumnExpandRatio(1, 1.0f);
		layout.addComponent(descriptionLayout.getLayout());

		accountAddLayout.addBottomControls(createBottomPanel());

		accountAddLayout.addBody(layout);

		return accountAddLayout;
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.addComponent(propertyId.equals("accountname"), field,
				"Account Name", 0, 0);
		informationLayout.addComponent(propertyId.equals("phoneoffice"), field,
				"Phone Office", 1, 0);
		informationLayout.addComponent(propertyId.equals("website"), field,
				"Website", 0, 1);
		informationLayout.addComponent(propertyId.equals("fax"), field, "Fax",
				1, 1);
		informationLayout.addComponent(propertyId.equals("numemployees"),
				field, "Employees", 0, 2);
		informationLayout.addComponent(propertyId.equals("alternatephone"),
				field, "Other Phone", 1, 2);
		informationLayout.addComponent(propertyId.equals("industry"), field,
				"Industry", 0, 3);
		informationLayout.addComponent(propertyId.equals("email"), field,
				"Email", 1, 3);
		informationLayout.addComponent(propertyId.equals("type"), field,
				"Type", 0, 4);
		informationLayout.addComponent(propertyId.equals("ownership"), field,
				"Ownership", 1, 4);
		informationLayout.addComponent(propertyId.equals("assignuser"), field,
				"Assign User", 0, 5);
		informationLayout.addComponent(propertyId.equals("annualrevenue"),
				field, "Annual Revenue", 1, 5);

		addressLayout.addComponent(propertyId.equals("billingaddress"), field,
				"Billing Address", 0, 0);
		addressLayout.addComponent(propertyId.equals("shippingaddress"), field,
				"Shipping Address", 1, 0);
		addressLayout.addComponent(propertyId.equals("city"), field,
				"Billing City", 0, 1);
		addressLayout.addComponent(propertyId.equals("shippingcity"), field,
				"Shipping City", 1, 1);
		addressLayout.addComponent(propertyId.equals("state"), field,
				"Billing State", 0, 2);
		addressLayout.addComponent(propertyId.equals("shippingstate"), field,
				"Shipping State", 1, 2);
		addressLayout.addComponent(propertyId.equals("postalcode"), field,
				"Postal Code", 0, 3);
		addressLayout.addComponent(propertyId.equals("shippingpostalcode"),
				field, "Shipping Postal Code", 1, 3);

		if (propertyId.equals("description")) {
			field.setSizeUndefined();
			descriptionLayout.addComponent(field, "Description", 0, 0);
		}

	}

}
