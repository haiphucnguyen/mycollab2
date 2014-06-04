package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.project.localization.RiskI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public class RiskFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private GridFormLayoutHelper informationLayout;

	@Override
	public Layout getLayout() {
		final VerticalLayout layout = new VerticalLayout();

		this.informationLayout = new GridFormLayoutHelper(2, 7, "100%",
				"167px", Alignment.TOP_LEFT);
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
		if (propertyId.equals("riskname")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_NAME), 0, 0, 2,
					"100%");
		} else if (propertyId.equals("description")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_DESCRIPTION), 0, 1,
					2, "100%");
		} else if (propertyId.equals("raisedbyuser")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_RAISED_BY), 0, 2);
		} else if (propertyId.equals("type")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_RELATED), 1, 2);
		} else if (propertyId.equals("assigntouser")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
					0, 3);
		} else if (propertyId.equals("consequence")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE), 1, 3);
		} else if (propertyId.equals("datedue")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_DATE_DUE), 0, 4);
		} else if (propertyId.equals("probalitity")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY), 1, 4);
		} else if (propertyId.equals("status")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_STATUS), 0, 5);
		} else if (propertyId.equals("level")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_RATING), 1, 5);
		} else if (propertyId.equals("response")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(RiskI18nEnum.FORM_RESPONSE), 0, 6, 2,
					"100%");
		}
	}
}
