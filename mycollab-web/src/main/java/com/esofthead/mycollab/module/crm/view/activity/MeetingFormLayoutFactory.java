package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class MeetingFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private GridFormLayoutHelper informationLayout;

	@Override
	public Layout getLayout() {
		AddViewLayout meetingLayout = new AddViewLayout("Meeting");

		VerticalLayout layout = new VerticalLayout();

		Label organizationHeader = new Label("Meeting Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = new GridFormLayoutHelper(2, 6);
		informationLayout.getLayout().setWidth("900px");
		layout.addComponent(informationLayout.getLayout());

		meetingLayout.addTopControls(createTopPanel());
		meetingLayout.addBody(layout);
		meetingLayout.addBottomControls(createBottomPanel());

		return meetingLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		if (propertyId.equals("subject")) {
			informationLayout.addComponent(field, "Subject", 0, 0);
		} else if (propertyId.equals("startdate")) {
			informationLayout.addComponent(field, "Start Date & Time", 0, 1);
		} else if (propertyId.equals("enddate")) {
			informationLayout.addComponent(field, "End Date & Time", 0, 2);
		} else if (propertyId.equals("status")) {
			informationLayout.addComponent(field, "Status", 1, 0);
		} else if (propertyId.equals("type")) {
			informationLayout.addComponent(field, "Related to", 1, 1);
		} else if (propertyId.equals("location")) {
			informationLayout.addComponent(field, "Location", 1, 2);
		} else if (propertyId.equals("isrecurrence")) {
			informationLayout.addComponent(field, "Recurring Activity", 0, 3,
					2, UIConstants.DEFAULT_2XCONTROL_WIDTH, "30px");
		} else if (propertyId.equals("description")) {
			informationLayout.addComponent(field, "Description", 0, 4, 2,
					UIConstants.DEFAULT_2XCONTROL_WIDTH,
					UIConstants.DEFAULT_2XCONTROL_HEIGHT);
		}
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

}
