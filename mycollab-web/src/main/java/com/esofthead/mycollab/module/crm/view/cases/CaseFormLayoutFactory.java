package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class CaseFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private String title;
	private CaseInformationLayout caseInformationLayout;

	public CaseFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 caseAddLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/case.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			caseAddLayout.addControlButtons(topPanel);
		}

		caseInformationLayout = new CaseInformationLayout();
		caseAddLayout.addBody(caseInformationLayout.getLayout());

		return caseAddLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		caseInformationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	public static class CaseInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;
		private GridFormLayoutHelper descriptionLayout;

		@Override
		public Layout getLayout() {
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(false);

			Label organizationHeader = new Label("Case Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 5, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			informationLayout.getLayout().setWidth("100%");
			informationLayout.getLayout().setMargin(false);
			informationLayout.getLayout().setSpacing(false);
			layout.addComponent(informationLayout.getLayout());

			descriptionLayout = new GridFormLayoutHelper(2, 2, "100%", "167px",
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
			if (propertyId.equals("priority")) {
				informationLayout.addComponent(field, "Priority", 0, 0);
			} else if (propertyId.equals("status")) {
				informationLayout.addComponent(field, "Status", 0, 1);
			} else if (propertyId.equals("accountid")) {
				informationLayout.addComponent(field, "Account Name", 0, 2);
			} else if (propertyId.equals("phonenumber")) {
				informationLayout.addComponent(field, "Phone Number", 0, 3);
			} else if (propertyId.equals("origin")) {
				informationLayout.addComponent(field, "Origin", 0, 4);
			} else if (propertyId.equals("type")) {
				informationLayout.addComponent(field, "Type", 1, 0);
			} else if (propertyId.equals("reason")) {
				informationLayout.addComponent(field, "Reason", 1, 1);
			} else if (propertyId.equals("subject")) {
				informationLayout.addComponent(field, "Subject", 1, 2);
			} else if (propertyId.equals("email")) {
				informationLayout.addComponent(field, "Email", 1, 3);
			} else if (propertyId.equals("assignuser")) {
				informationLayout.addComponent(field, "Assigned User", 1, 4);
			} else if (propertyId.equals("description")) {
				descriptionLayout.addComponent(field, "Description", 0, 0, 2,
						"100%", Alignment.TOP_LEFT);
			} else if (propertyId.equals("resolution")) {
				descriptionLayout.addComponent(field, "Resolution", 0, 1, 2,
						"100%", Alignment.TOP_LEFT);
			}
		}
	}
}
