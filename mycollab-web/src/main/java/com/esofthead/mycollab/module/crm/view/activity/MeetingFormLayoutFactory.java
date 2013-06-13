package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class MeetingFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private String title;
	private MeetingInformationLayout informationLayout;

	public MeetingFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 meetingLayout = new AddViewLayout2(title,
				new ThemeResource("icons/48/crm/meeting.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			meetingLayout.addControlButtons(topPanel);
		}
		informationLayout = new MeetingInformationLayout();
		meetingLayout.addBody(informationLayout.getLayout());

		return meetingLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@SuppressWarnings("serial")
	public static class MeetingInformationLayout implements IFormLayoutFactory {

		private VerticalLayout layout;
		private GridFormLayoutHelper informationLayout;
		private GridFormLayoutHelper descriptionLayout;

		@Override
		public Layout getLayout() {
			layout = new VerticalLayout();
			Label organizationHeader = new Label("Meeting Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);
			informationLayout = new GridFormLayoutHelper(2, 4,"100%", "167px",
					Alignment.MIDDLE_LEFT);
			informationLayout.getLayout().setWidth("100%");
			informationLayout.getLayout().setMargin(false);
			informationLayout.getLayout().setSpacing(false);
			layout.addComponent(informationLayout.getLayout());
			
			descriptionLayout = new GridFormLayoutHelper(2, 1, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			Label descHeader = new Label("Description");
			descHeader.setStyleName("h2");
			layout.addComponent(descHeader);
			descriptionLayout.getLayout().setWidth("100%");
			descriptionLayout.getLayout().setMargin(false);
			descriptionLayout.getLayout().setSpacing(false);
			
			layout.addComponent(descriptionLayout.getLayout());
			
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("subject")) {
				informationLayout.addComponent(field, "Subject", 0, 0);
			} else if (propertyId.equals("startdate")) {
				informationLayout
						.addComponent(field, "Start Date & Time", 0, 1);
			} else if (propertyId.equals("enddate")) {
				informationLayout.addComponent(field, "End Date & Time", 0, 2);
			} else if (propertyId.equals("status")) {
				informationLayout.addComponent(field, "Status", 1, 0);
			} else if (propertyId.equals("type")) {
				informationLayout.addComponent(field, "Related to", 1, 1,
						UIConstants.DEFAULT_CONTROL_EXT_WIDTH);
			} else if (propertyId.equals("location")) {
				informationLayout.addComponent(field, "Location", 1, 2);
			} else if (propertyId.equals("description")) {
				descriptionLayout.addComponent(field, "Description", 0, 0, 2,"100%", Alignment.TOP_LEFT);
			}
		}

	}
}
