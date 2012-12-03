package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class CaseFormLayoutFactory implements IFormLayoutFactory {

	private GridFormLayoutHelper informationLayout;

	private GridFormLayoutHelper descriptionLayout;

	@Override
	public Layout getLayout() {
		AddViewLayout accountAddLayout = new AddViewLayout("Case");

		accountAddLayout.addTopControls(createTopPanel());

		VerticalLayout layout = new VerticalLayout();

		Label organizationHeader = new Label("Case Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = new GridFormLayoutHelper(2, 6);
		informationLayout.getLayout().setWidth("900px");
		layout.addComponent(informationLayout.getLayout());
		layout.setComponentAlignment(informationLayout.getLayout(),
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

	@Override
	public void attachField(Object propertyId, Field field) {
		if (propertyId.equals("priority")) {
			informationLayout.addComponent(field, "Priority", 0, 0);
		} else if (propertyId.equals("status")) {
			informationLayout.addComponent(field, "Status", 0, 1);
		} else if (propertyId.equals("accountid")) {
			informationLayout.addComponent(field, "Account Name", 0, 2);
		}
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

}
