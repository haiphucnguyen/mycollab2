package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.mobile.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.module.project.i18n.TaskGroupI18nEnum;
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
 * @since 4.5.0
 */
public class TaskGroupFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private GridFormLayoutHelper informationLayout;

	@Override
	public Layout getLayout() {
		final VerticalLayout layout = new VerticalLayout();
		Label header = new Label(
				AppContext.getMessage(TaskGroupI18nEnum.M_FORM_READ_TITLE));
		header.setStyleName("h2");
		layout.addComponent(header);
		this.informationLayout = new GridFormLayoutHelper(1, 6, "100%",
				"150px", Alignment.TOP_LEFT);
		this.informationLayout.getLayout().addStyleName("colored-gridlayout");
		this.informationLayout.getLayout().setMargin(false);
		this.informationLayout.getLayout().setWidth("100%");

		layout.addComponent(this.informationLayout.getLayout());
		layout.setComponentAlignment(this.informationLayout.getLayout(),
				Alignment.BOTTOM_CENTER);
		return layout;
	}

	@Override
	public void attachField(final Object propertyId, final Field<?> field) {
		if (propertyId.equals("name")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(TaskGroupI18nEnum.FORM_NAME_FIELD),
					0, 0);
		} else if (propertyId.equals("description")) {
			this.informationLayout
					.addComponent(
							field,
							AppContext
									.getMessage(TaskGroupI18nEnum.FORM_DESCRIPTION_FIELD),
							0, 1);
		} else if (propertyId.equals("owner")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 0, 2);
		} else if (propertyId.equals("milestoneid")) {
			this.informationLayout.addComponent(field,
					AppContext.getMessage(TaskGroupI18nEnum.FORM_PHASE_FIELD),
					0, 3);
		} else if (propertyId.equals("percentageComplete")) {
			this.informationLayout.addComponent(field, AppContext
					.getMessage(TaskGroupI18nEnum.FORM_PROGRESS_FIELD), 0, 4);
		} else if (propertyId.equals("numOpenTasks")) {
			this.informationLayout.addComponent(field, AppContext
					.getMessage(TaskGroupI18nEnum.FORM_OPEN_TASKS_FIELD), 0, 5);
		}
	}
}
