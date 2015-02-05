package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.MassUpdateCommand;
import com.esofthead.mycollab.vaadin.ui.*;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
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
	protected AbstractBeanFieldGroupEditFieldFactory<Risk> buildBeanFormFieldFactory() {
		return new RiskEditFormFieldFactory(updateForm);
	}

	private class MassUpdateRiskFormLayoutFactory implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;

		private GridFormLayoutHelper informationLayout;

		@Override
		public ComponentContainer getLayout() {
			VerticalLayout formLayout = new VerticalLayout();

			Label organizationHeader = new Label("Risk Information");
			organizationHeader.setStyleName(UIConstants.H2_STYLE2);
			formLayout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 6, "100%", "167px",
					Alignment.TOP_LEFT);

			informationLayout.getLayout().setWidth("100%");
			informationLayout.getLayout().setMargin(false);
			informationLayout.getLayout().setSpacing(false);
			informationLayout.getLayout().addStyleName("colored-gridlayout");
			formLayout.addComponent(informationLayout.getLayout());

			formLayout.addComponent(buildButtonControls());

			return formLayout;
		}

		// Raised By, Assign To, Date Due, Status, Consequence, Probability
		@Override
		public void attachField(Object propertyId, Field<?> field) {
			if (propertyId.equals("raisedbyuser")) {
				this.informationLayout.addComponent(field,
						AppContext.getMessage(RiskI18nEnum.FORM_RAISED_BY), 0,
						0);
			} else if (propertyId.equals("assigntouser")) {
				this.informationLayout.addComponent(field, AppContext
						.getMessage(GenericI18Enum.FORM_ASSIGNEE), 1, 0);
			} else if (propertyId.equals("consequence")) {
				this.informationLayout.addComponent(field,
						AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE),
						0, 1);
			} else if (propertyId.equals("datedue")) {
				this.informationLayout
						.addComponent(field, AppContext
								.getMessage(RiskI18nEnum.FORM_DATE_DUE), 1, 1);
			} else if (propertyId.equals("probalitity")) {
				this.informationLayout.addComponent(field,
						AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY),
						0, 2);
			} else if (propertyId.equals("status")) {
				this.informationLayout.addComponent(field,
						AppContext.getMessage(RiskI18nEnum.FORM_STATUS), 1, 2);
			}
		}
	}
}
