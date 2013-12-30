package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.vaadin.mvp.MassUpdateCommand;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateRiskWindow extends MassUpdateWindow<Risk> {
	private static final long serialVersionUID = 1L;

	public MassUpdateRiskWindow(String title,
			MassUpdateCommand<Risk> massUpdatePresenter) {
		super(title, MyCollabResource
				.newResource("icons/22/project/menu_risk.png"), new Risk(),
				massUpdatePresenter);
	}

	@Override
	protected IFormLayoutFactory buildFormLayoutFactory() {
		return new MassUpdateRiskFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Risk> initBeanFormFieldFactory() {
		return null;
		// TODO: fix this issue
		// return RiskEditFormFieldFactory(updateForm);
	}

	private class MassUpdateRiskFormLayoutFactory implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			VerticalLayout formLayout = new VerticalLayout();

			Label organizationHeader = new Label("Risk Information");
			organizationHeader.setStyleName("h2");
			formLayout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 6, "100%", "167px",
					Alignment.MIDDLE_LEFT);

			informationLayout.getLayout().setWidth("100%");
			informationLayout.getLayout().setMargin(false);
			informationLayout.getLayout().setSpacing(false);
			informationLayout.getLayout().addStyleName("colored-gridlayout");
			formLayout.addComponent(informationLayout.getLayout());

			formLayout.addComponent(buildButtonControls());
			formLayout
					.addStyleName("v-csslayout v-csslayout-readview-layout-body readview-layout-body");

			return formLayout;
		}

		// Raised By, Assign To, Date Due, Status, Consequence, Probability
		@Override
		public boolean attachField(Object propertyId, Field<?> field) {
			if (propertyId.equals("raisedbyuser")) {
				this.informationLayout.addComponent(field, "Raised by", 0, 0);
			} else if (propertyId.equals("assigntouser")) {
				this.informationLayout.addComponent(field, LocalizationHelper
						.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 1, 0);
			} else if (propertyId.equals("consequence")) {
				this.informationLayout.addComponent(field, "Consequence", 0, 1);
			} else if (propertyId.equals("datedue")) {
				this.informationLayout.addComponent(field, "Date due", 1, 1);
			} else if (propertyId.equals("probalitity")) {
				this.informationLayout.addComponent(field, "Probality", 0, 2);
			} else if (propertyId.equals("status")) {
				this.informationLayout.addComponent(field, "Status", 1, 2);
			} else {
				return false;
			}

			return true;
		}
	}
}
