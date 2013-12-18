package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProblemFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private GridFormLayoutHelper informationLayout;

	@Override
	public Layout getLayout() {
		final VerticalLayout layout = new VerticalLayout();

		final Label organizationHeader = new Label("Problem Information");
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

		return layout;
	}

	@Override
	public void attachField(final Object propertyId, final Field<?> field) {
		if (propertyId.equals("issuename")) {
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
		} else if (propertyId.equals("impact")) {
			this.informationLayout.addComponent(field, "Impact", 1, 3);
		} else if (propertyId.equals("datedue")) {
			this.informationLayout.addComponent(field, "Date due", 0, 4);
		} else if (propertyId.equals("priority")) {
			this.informationLayout.addComponent(field, "Priority", 1, 4);
		} else if (propertyId.equals("status")) {
			this.informationLayout.addComponent(field, "Status", 0, 5);
		} else if (propertyId.equals("level")) {
			this.informationLayout.addComponent(field, "Rating", 1, 5);
		} else if (propertyId.equals("resolution")) {
			this.informationLayout.addComponent(field, "Resolution", 0, 6, 2,
					"100%");
		}
	}
}
