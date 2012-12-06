package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class LeadFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	protected GridFormLayoutHelper informationLayout;

	protected GridFormLayoutHelper addressLayout;

	protected GridFormLayoutHelper descLayout;

	private HorizontalLayout prefixFirstNameBox;

	@Override
	public Layout getLayout() {
		AddViewLayout leadAddLayout = new AddViewLayout("Lead");
		leadAddLayout.addTopControls(createButtonControls());

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		Label organizationHeader = new Label("Contact Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = new GridFormLayoutHelper(2, 8);
		informationLayout.getLayout().setWidth("900px");
		layout.addComponent(informationLayout.getLayout());

		prefixFirstNameBox = new HorizontalLayout();
		prefixFirstNameBox.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
		prefixFirstNameBox.setSpacing(true);
		informationLayout.addComponent(prefixFirstNameBox, "First Name", 0, 0);

		Label addressHeader = new Label("Address Information");
		addressHeader.setStyleName("h2");
		layout.addComponent(addressHeader);
		addressLayout = new GridFormLayoutHelper(2, 5);
		addressLayout.getLayout().setWidth("900px");
		layout.addComponent(addressLayout.getLayout());

		Label descriptionHeader = new Label("Description");
		descriptionHeader.setStyleName("h2");
		layout.addComponent(descriptionHeader);
		descLayout = new GridFormLayoutHelper(2, 1);
		descLayout.getLayout().setWidth("900px");
		layout.addComponent(descLayout.getLayout());

		leadAddLayout.addBody(layout);
		leadAddLayout.addBottomControls(createButtonControls());
		return leadAddLayout;
	}

	protected abstract Layout createButtonControls();

	@Override
	public void attachField(Object propertyId, Field field) {
		if (propertyId.equals("prefixname")) {
			prefixFirstNameBox.addComponent(field, 0);
			field.setWidth("45px");
		} else if (propertyId.equals("firstname")) {
			prefixFirstNameBox.addComponent(field);
			field.setWidth("145px");
		}

		informationLayout.addComponent(propertyId.equals("lastname"), field,
				"Last Name", 0, 1);
		informationLayout.addComponent(propertyId.equals("title"), field,
				"Title", 0, 2);
		informationLayout.addComponent(propertyId.equals("department"), field,
				"Department", 0, 3);
		informationLayout.addComponent(propertyId.equals("accountname"), field,
				"Account Name", 0, 4);
		informationLayout.addComponent(propertyId.equals("source"), field,
				"Lead Source", 0, 5);
		informationLayout.addComponent(propertyId.equals("industry"), field,
				"Industry", 0, 6);
		informationLayout.addComponent(propertyId.equals("noemployees"), field,
				"No of Employees", 0, 7);

		informationLayout.addComponent(propertyId.equals("email"), field,
				"Email", 1, 0);
		informationLayout.addComponent(propertyId.equals("officephone"), field,
				"Office Phone", 1, 1);
		informationLayout.addComponent(propertyId.equals("mobile"), field,
				"Mobile", 1, 2);
		informationLayout.addComponent(propertyId.equals("otherphone"), field,
				"Other Phone", 1, 3);
		informationLayout.addComponent(propertyId.equals("fax"), field, "Fax",
				1, 4);
		informationLayout.addComponent(propertyId.equals("website"), field,
				"Web Site", 1, 5);
		informationLayout.addComponent(propertyId.equals("status"), field,
				"Status", 1, 6);
		informationLayout.addComponent(propertyId.equals("assignuser"), field,
				"Assigned User", 1, 7);

		addressLayout.addComponent(propertyId.equals("primaddress"), field,
				"Address", 0, 0);
		addressLayout.addComponent(propertyId.equals("primcity"), field,
				"City", 0, 1);
		addressLayout.addComponent(propertyId.equals("primstate"), field,
				"State", 0, 2);
		addressLayout.addComponent(propertyId.equals("primpostalcode"), field,
				"Postal Code", 0, 3);
		addressLayout.addComponent(propertyId.equals("primcountry"), field,
				"Country", 0, 4);

		addressLayout.addComponent(propertyId.equals("otheraddress"), field,
				"Other Address", 1, 0);
		addressLayout.addComponent(propertyId.equals("othercity"), field,
				"Other City", 1, 1);
		addressLayout.addComponent(propertyId.equals("otherstate"), field,
				"Other State", 1, 2);
		addressLayout.addComponent(propertyId.equals("otherpostalcode"), field,
				"Other Postal Code", 1, 3);
		addressLayout.addComponent(propertyId.equals("othercountry"), field,
				"Other Country", 1, 4);

		if (propertyId.equals("description")) {
			descLayout.addComponent(field, "Description", 0, 0, 2,
					UIConstants.DEFAULT_2XCONTROL_WIDTH,
					UIConstants.DEFAULT_2XCONTROL_HEIGHT);
		}
	}

}
