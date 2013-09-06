package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class LeadFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private String title;
	private LeadEditInformationLayout leadInformation;

	public LeadFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 leadAddLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/lead.png"));
		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			leadAddLayout.addControlButtons(topPanel);
		}
		leadInformation = new LeadEditInformationLayout();
		leadAddLayout.addBody(leadInformation.getLayout());
		return leadAddLayout;
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public void attachField(Object propertyId, Field field) {
		leadInformation.attachField(propertyId, field);
	}

	private static abstract class LeadGenericInformationLayout implements
			IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		protected VerticalLayout layout;
		protected GridFormLayoutHelper informationLayout;
		protected GridFormLayoutHelper addressLayout;
		protected GridFormLayoutHelper descLayout;

		@Override
		public Layout getLayout() {
			layout = new VerticalLayout();
			layout.setSpacing(false);

			Label organizationHeader = new Label("Contact Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 8, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			informationLayout.getLayout().setWidth("100%");
			informationLayout.getLayout().setMargin(false);
			informationLayout.getLayout().setSpacing(false);

			layout.addComponent(informationLayout.getLayout());

			Component firstnameBox = createFirstNameBox();
			if (firstnameBox != null) {
				informationLayout
						.addComponent(firstnameBox, "First Name", 0, 0);
			}

			Label addressHeader = new Label("Address Information");
			addressHeader.setStyleName("h2");
			layout.addComponent(addressHeader);
			addressLayout = new GridFormLayoutHelper(2, 5, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			addressLayout.getLayout().setWidth("100%");
			addressLayout.getLayout().setMargin(false);
			addressLayout.getLayout().setSpacing(false);
			layout.addComponent(addressLayout.getLayout());

			Label descriptionHeader = new Label("Description");
			descriptionHeader.setStyleName("h2");
			layout.addComponent(descriptionHeader);
			descLayout = new GridFormLayoutHelper(2, 1, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			descLayout.getLayout().setWidth("100%");
			descLayout.getLayout().setMargin(false);
			descLayout.getLayout().setSpacing(false);
			layout.addComponent(descLayout.getLayout());

			return layout;
		}

		protected abstract Component createFirstNameBox();

		@Override
		public void attachField(Object propertyId, Field field) {
			informationLayout.addComponent(propertyId.equals("lastname"),
					field, "Last Name", 0, 1);
			informationLayout.addComponent(propertyId.equals("title"), field,
					"Title", 0, 2);
			informationLayout.addComponent(propertyId.equals("department"),
					field, "Department", 0, 3);
			informationLayout.addComponent(propertyId.equals("accountname"),
					field, "Account Name", 0, 4);
			informationLayout.addComponent(propertyId.equals("source"), field,
					"Lead Source", 0, 5);
			informationLayout.addComponent(propertyId.equals("industry"),
					field, "Industry", 0, 6);
			informationLayout.addComponent(propertyId.equals("noemployees"),
					field, "No of Employees", 0, 7);

			informationLayout.addComponent(propertyId.equals("email"), field,
					"Email", 1, 0);
			informationLayout.addComponent(propertyId.equals("officephone"),
					field, "Office Phone", 1, 1);
			informationLayout.addComponent(propertyId.equals("mobile"), field,
					"Mobile", 1, 2);
			informationLayout.addComponent(propertyId.equals("otherphone"),
					field, "Other Phone", 1, 3);
			informationLayout.addComponent(propertyId.equals("fax"), field,
					"Fax", 1, 4);
			informationLayout.addComponent(propertyId.equals("website"), field,
					"Web Site", 1, 5);
			informationLayout.addComponent(propertyId.equals("status"), field,
					"Status", 1, 6);
			informationLayout.addComponent(propertyId.equals("assignuser"),
					field, LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 1,
					7);

			addressLayout.addComponent(propertyId.equals("primaddress"), field,
					"Address", 0, 0);
			addressLayout.addComponent(propertyId.equals("primcity"), field,
					"City", 0, 1);
			addressLayout.addComponent(propertyId.equals("primstate"), field,
					"State", 0, 2);
			addressLayout.addComponent(propertyId.equals("primpostalcode"),
					field, "Postal Code", 0, 3);
			addressLayout.addComponent(propertyId.equals("primcountry"), field,
					"Country", 0, 4);

			addressLayout.addComponent(propertyId.equals("otheraddress"),
					field, "Other Address", 1, 0);
			addressLayout.addComponent(propertyId.equals("othercity"), field,
					"Other City", 1, 1);
			addressLayout.addComponent(propertyId.equals("otherstate"), field,
					"Other State", 1, 2);
			addressLayout.addComponent(propertyId.equals("otherpostalcode"),
					field, "Other Postal Code", 1, 3);
			addressLayout.addComponent(propertyId.equals("othercountry"),
					field, "Other Country", 1, 4);

			if (propertyId.equals("description")) {
				descLayout.addComponent(field, "Description", 0, 0, 2, "100%",
						Alignment.TOP_LEFT);
			}
		}

	}

	public static class LeadEditInformationLayout extends
			LeadGenericInformationLayout {
		private static final long serialVersionUID = 1L;

		private HorizontalLayout firstNameBox;

		@Override
		protected Component createFirstNameBox() {
			firstNameBox = new HorizontalLayout();
			firstNameBox.setDebugId("Firstnamebox");
			firstNameBox.setSpacing(true);
			firstNameBox.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			return firstNameBox;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("prefixname")) {
				firstNameBox.addComponent(field, 0);
			} else if (propertyId.equals("firstname")) {
				firstNameBox.addComponent(field);
				field.setWidth("100%");
				firstNameBox.setExpandRatio(field, 1.0f);
			} else {
				super.attachField(propertyId, field);
			}
		}

	}

	public static class LeadReadInformationLayout extends
			LeadGenericInformationLayout {
		private static final long serialVersionUID = 1L;

		@Override
		protected Component createFirstNameBox() {
			return null;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("firstname")) {
				informationLayout.addComponent(field, "First Name", 0, 0);
			} else {
				super.attachField(propertyId, field);
			}
		}

	}
}
