package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.ui.components.ProjectUiUtils;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class BugAddFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private GridFormLayoutHelper informationLayout;
	private final String title;

	public BugAddFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout bugAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/project/bug.png"));

		bugAddLayout.addTopControls(createTopPanel());

		VerticalLayout layout = new VerticalLayout();

		Label organizationHeader = new Label("Bug Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = ProjectUiUtils.getGridFormLayoutHelper(2, 8);
		informationLayout.getLayout().setWidth("100%");
		layout.addComponent(informationLayout.getLayout());
		layout.setComponentAlignment(informationLayout.getLayout(),
				Alignment.BOTTOM_CENTER);

		bugAddLayout.addBottomControls(createBottomPanel());

		bugAddLayout.addBody(layout);

		return bugAddLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		if (propertyId.equals("summary")) {
			informationLayout.addComponent(field, "Summary", 0, 0, 2, "100%");
		} else if (propertyId.equals("priority")) {
			informationLayout.addComponent(field, "Priority", 0, 1);
		} else if (propertyId.equals("severity")) {
			informationLayout.addComponent(field, "Severity", 0, 2);
		} else if (propertyId.equals("duedate")) {
			informationLayout.addComponent(field, "Due Date", 0, 3);
		} else if (propertyId.equals("assignuser")) {
			informationLayout.addComponent(field, "Assignee", 0, 4);
		} else if (propertyId.equals("components")) {
			informationLayout.addComponent(field, "Components", 1, 1);
		} else if (propertyId.equals("affectedVersions")) {
			informationLayout.addComponent(field, "Affected Versions", 1, 2);
		} else if (propertyId.equals("fixedVersions")) {
			informationLayout.addComponent(field, "Fixed Versions", 1, 3);
		} else if (propertyId.equals("environment")) {
			informationLayout.addComponent(field, "Environment", 0, 5, 2,
					"100%");
		} else if (propertyId.equals("description")) {
			informationLayout.addComponent(field, "Description", 0, 6, 2,
					"100%");
		} else if (propertyId.equals("id")) {// add attachment box
			informationLayout
					.addComponent(field, "Attachment", 0, 7, 2, "100%");
		} else if (propertyId.equals("milestoneid")) {
			informationLayout.addComponent(field, "Milestone", 1, 4);
		}

	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();
}
