package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class RiskFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private GridFormLayoutHelper informationLayout;
	private final String title;

	public RiskFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		final AddViewLayout riskAddLayout = new AddViewLayout(this.title,
				MyCollabResource.newResource("icons/24/project/risk.png"));

		riskAddLayout.addTopControls(this.createTopPanel());

		final VerticalLayout layout = new VerticalLayout();

		final Label organizationHeader = new Label("Risk Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		this.informationLayout = new GridFormLayoutHelper(2, 7, "100%",
				"167px", Alignment.MIDDLE_LEFT);
		this.informationLayout.getLayout().setWidth("100%");
		this.informationLayout.getLayout().setMargin(false);
		this.informationLayout.getLayout().addStyleName("colored-gridlayout");
		layout.addComponent(this.informationLayout.getLayout());
		layout.setComponentAlignment(this.informationLayout.getLayout(),
				Alignment.BOTTOM_CENTER);

		riskAddLayout.addBottomControls(this.createBottomPanel());

		riskAddLayout.addBody(layout);

		return riskAddLayout;
	}

	@Override
	public void attachField(final Object propertyId, final Field field) {
		if (propertyId.equals("riskname")) {
			this.informationLayout.addComponent(field, "Name", 0, 0, 2, "100%");
		} else if (propertyId.equals("description")) {
			this.informationLayout.addComponent(field, "Description", 0, 1, 2,
					"100%");
		} else if (propertyId.equals("raisedbyuser")) {
			this.informationLayout.addComponent(field, "Raised by", 0, 2);
		} else if (propertyId.equals("type")) {
			this.informationLayout.addComponent(field, "Related to", 1, 2);
		} else if (propertyId.equals("assigntouser")) {
			this.informationLayout.addComponent(field, LocalizationHelper
					.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 0, 3);
		} else if (propertyId.equals("consequence")) {
			this.informationLayout.addComponent(field, "Consequence", 1, 3);
		} else if (propertyId.equals("datedue")) {
			this.informationLayout.addComponent(field, "Date due", 0, 4);
		} else if (propertyId.equals("probalitity")) {
			this.informationLayout.addComponent(field, "Probability", 1, 4);
		} else if (propertyId.equals("status")) {
			this.informationLayout.addComponent(field, "Status", 0, 5);
		} else if (propertyId.equals("level")) {
			this.informationLayout.addComponent(field, "Rating", 1, 5);
		} else if (propertyId.equals("response")) {
			this.informationLayout.addComponent(field, "Response", 0, 6, 2,
					"100%");
		}
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();
}
