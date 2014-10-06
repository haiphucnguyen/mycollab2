package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.mobile.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class MilestoneFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private GridFormLayoutHelper informationLayout;

	@Override
	public void attachField(final Object propertyId, final Field<?> field) {
		if (propertyId.equals("status")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(MilestoneI18nEnum.FORM_STATUS_FIELD),
					0, 0);
		} else if (propertyId.equals("owner")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 0, 1);
		} else if (propertyId.equals("startdate")) {
			this.informationLayout.addComponent(field, AppContext
					.getMessage(MilestoneI18nEnum.FORM_START_DATE_FIELD), 0, 2);
		} else if (propertyId.equals("enddate")) {
			this.informationLayout.addComponent(field, AppContext
					.getMessage(MilestoneI18nEnum.FORM_END_DATE_FIELD), 0, 3);
		} else if (propertyId.equals("numOpenTasks")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(MilestoneI18nEnum.FORM_TASK_FIELD),
					0, 4);
		} else if (propertyId.equals("numOpenBugs")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(MilestoneI18nEnum.FORM_BUG_FIELD), 0,
					5);
		} else if (propertyId.equals("description")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0,
					6);
		}
	}

	@Override
	public Layout getLayout() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(false);
		Label header = new Label(
				AppContext.getMessage(MilestoneI18nEnum.M_FORM_READ_TITLE));
		header.setStyleName("h2");
		layout.addComponent(header);

		this.informationLayout = new GridFormLayoutHelper(1, 7, "100%",
				"150px", Alignment.TOP_LEFT);
		this.informationLayout.getLayout().setWidth("100%");
		this.informationLayout.getLayout().addStyleName("colored-gridlayout");
		this.informationLayout.getLayout().setMargin(false);
		layout.addComponent(this.informationLayout.getLayout());
		layout.setComponentAlignment(this.informationLayout.getLayout(),
				Alignment.BOTTOM_CENTER);
		return layout;
	}
}
