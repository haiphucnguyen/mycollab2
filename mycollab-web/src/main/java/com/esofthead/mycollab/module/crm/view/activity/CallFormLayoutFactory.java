package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class CallFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private String title;

	private CallInformationLayout informationLayout;

	public CallFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 callAddLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/call.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {

			callAddLayout.addControlButtons(topPanel);
		}

		informationLayout = new CallInformationLayout();
		callAddLayout.addBody(informationLayout.getLayout());

		return callAddLayout;
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	@SuppressWarnings("serial")
	public static class CallInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			VerticalLayout layout = new VerticalLayout();

			Label organizationHeader = new Label("Call Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 6, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			informationLayout.getLayout().setWidth("100%");
			informationLayout.getLayout().setMargin(false);
			informationLayout.getLayout().setSpacing(false);
			layout.addComponent(informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("subject")) {
				informationLayout.addComponent(field, "Subject", 0, 0);
			} else if (propertyId.equals("startdate")) {
				informationLayout
						.addComponent(field, "Start Date & Time", 0, 1);
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
				informationLayout.addComponent(field, LocalizationHelper
						.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 0, 3,
						2, "100%", Alignment.TOP_LEFT);
			} else if (propertyId.equals("description")) {
				informationLayout.addComponent(field, "Description", 0, 4, 2,
						"100%", Alignment.TOP_LEFT);
			} else if (propertyId.equals("result")) {
				informationLayout.addComponent(field, "Result", 0, 5, 2,
						"100%", Alignment.TOP_LEFT);
			}
		}

	}
}
