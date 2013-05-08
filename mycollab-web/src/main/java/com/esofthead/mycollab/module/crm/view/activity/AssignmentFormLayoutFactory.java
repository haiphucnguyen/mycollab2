package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class AssignmentFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private AssignmentInformationLayout informationLayout;
	private String title;

	public AssignmentFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout taskAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/crm/task.png"));

		Layout topPanel = createTopPanel();

		if (topPanel != null) {
			taskAddLayout.addTopControls(topPanel);
		}

		informationLayout = new AssignmentInformationLayout();
		taskAddLayout.addBody(informationLayout.getLayout());

		Layout bottomPanel = createBottomPanel();
		if (bottomPanel != null) {
			taskAddLayout.addBottomControls(bottomPanel);
		}

		return taskAddLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	public static class AssignmentInformationLayout implements
			IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			VerticalLayout layout = new VerticalLayout();

			Label organizationHeader = new Label("Task Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 5);
			informationLayout.getLayout().setWidth("900px");
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);

			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("subject")) {
				informationLayout.addComponent(field, "Subject", 0, 0);
			} else if (propertyId.equals("startdate")) {
				informationLayout.addComponent(field, "Start Date", 0, 1);
			} else if (propertyId.equals("duedate")) {
				informationLayout.addComponent(field, "Due Date", 0, 2);
			} else if (propertyId.equals("priority")) {
				informationLayout.addComponent(field, "Priority", 0, 3);
			} else if (propertyId.equals("description")) {
				informationLayout.addComponent(field, "Description", 0, 4, 2,
						UIConstants.DEFAULT_2XCONTROL_WIDTH,
						UIConstants.DEFAULT_2XCONTROL_HEIGHT);
			} else if (propertyId.equals("status")) {
				informationLayout.addComponent(field, "Status", 1, 0);
			} else if (propertyId.equals("type")) {
				informationLayout.addComponent(field, "Related To", 1, 1,
						UIConstants.DEFAULT_CONTROL_EXT_WIDTH);
			} else if (propertyId.equals("contactid")) {
				informationLayout.addComponent(field, "Contact", 1, 2);
			} else if (propertyId.equals("assignuser")) {
				informationLayout.addComponent(field, "Assigned to", 1, 3);
			}

		}

	}
}
