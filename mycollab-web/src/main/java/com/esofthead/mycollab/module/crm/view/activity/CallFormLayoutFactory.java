package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class CallFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private GridFormLayoutHelper informationLayout;

	@Override
	public Layout getLayout() {
		AddViewLayout callLayout = new AddViewLayout("Call");

		VerticalLayout layout = new VerticalLayout();

		Label organizationHeader = new Label("Call Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = new GridFormLayoutHelper(2, 6);
		informationLayout.getLayout().setWidth("900px");
		layout.addComponent(informationLayout.getLayout());

		callLayout.addTopControls(createTopPanel());
		callLayout.addBody(layout);
		callLayout.addBottomControls(createBottomPanel());

		return callLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		if (propertyId.equals("subject")) {
			informationLayout.addComponent(field, "Subject", 0, 0);
		} else if (propertyId.equals("startdate")) {
			informationLayout.addComponent(field, "Start Date & Time", 0, 1);
		} else if (propertyId.equals("durationinseconds")) {
			informationLayout.addComponent(field, "Duration", 0, 2);
		} else if (propertyId.equals("status")) {
			informationLayout.addComponent(field, "Status", 1, 0);
		} else if (propertyId.equals("type")) {
			informationLayout.addComponent(field, "Related to", 1, 1,
					UIConstants.DEFAULT_CONTROL_EXT_WIDTH);
		} else if (propertyId.equals("purpose")) {
			informationLayout.addComponent(field, "Purpose", 1, 2);
		} else if (propertyId.equals("assignuser")) {
			informationLayout.addComponent(field, "Assigned to", 0, 3);
		} else if (propertyId.equals("description")) {
			informationLayout.addComponent(field, "Description", 0, 4, 2,
					UIConstants.DEFAULT_2XCONTROL_WIDTH,
					UIConstants.DEFAULT_2XCONTROL_HEIGHT);
		} else if (propertyId.equals("result")) {
			informationLayout.addComponent(field, "Result", 0, 5, 2,
					UIConstants.DEFAULT_2XCONTROL_WIDTH,
					UIConstants.DEFAULT_2XCONTROL_HEIGHT);
		}
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

}
