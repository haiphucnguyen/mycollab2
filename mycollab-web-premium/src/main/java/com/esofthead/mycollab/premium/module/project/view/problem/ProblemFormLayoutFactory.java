package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
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
 * @since 1.0
 * 
 */
public class ProblemFormLayoutFactory implements IFormLayoutFactory {

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
		if (propertyId.equals("issuename")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_NAME), 0, 0, 2,
					"100%");
		} else if (propertyId.equals("description")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_DESCRIPTION), 0,
					1, 2, "100%");
		} else if (propertyId.equals("raisedbyuser")) {
			this.informationLayout
					.addComponent(field, AppContext
							.getMessage(ProblemI18nEnum.FORM_RAISED_BY), 0, 2);
		} else if (propertyId.equals("type")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_RELATED), 1, 2);
		} else if (propertyId.equals("assigntouser")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE),
					0, 3);
		} else if (propertyId.equals("impact")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_IMPACT), 1, 3);
		} else if (propertyId.equals("datedue")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_DATE_DUE), 0, 4);
		} else if (propertyId.equals("priority")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_PRIORITY), 1, 4);
		} else if (propertyId.equals("status")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_STATUS), 0, 5);
		} else if (propertyId.equals("level")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_RATING), 1, 5);
		} else if (propertyId.equals("resolution")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(ProblemI18nEnum.FORM_RESOLUTION), 0,
					6, 2, "100%");
		}
	}
}
