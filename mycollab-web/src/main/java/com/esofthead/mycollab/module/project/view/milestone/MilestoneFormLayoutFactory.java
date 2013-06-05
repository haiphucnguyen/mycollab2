/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

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

/**
 * 
 * @author haiphucnguyen
 */
public abstract class MilestoneFormLayoutFactory implements IFormLayoutFactory {
	public static class MilestoneInformationLayout implements
			IFormLayoutFactory {
		private static final long serialVersionUID = 1L;

		private GridFormLayoutHelper informationLayout;

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("name")) {
				informationLayout.addComponent(field, "Name", 0, 0, 2, "100%");
			} else if (propertyId.equals("startdate")) {
				informationLayout.addComponent(field, "Start Date", 0, 1);
			} else if (propertyId.equals("enddate")) {
				informationLayout.addComponent(field, "End Date", 0, 2);
			} else if (propertyId.equals("owner")) {
				informationLayout.addComponent(field, "Responsible User", 1, 1);
			} else if (propertyId.equals("status")) {
				informationLayout.addComponent(field, "Status", 1, 2);
			} else if (propertyId.equals("numOpenTasks")) {
				informationLayout.addComponent(field, "Tasks", 0, 3);
			} else if (propertyId.equals("numOpenBugs")) {
				informationLayout.addComponent(field, "Bugs", 1, 3);
			} else if (propertyId.equals("description")) {
				informationLayout.addComponent(field, "Description", 0, 4, 2,
						"100%");
			}
		}

		@Override
		public Layout getLayout() {
			final VerticalLayout layout = new VerticalLayout();

			final Label organizationHeader = new Label("Milestone Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = ProjectUiUtils.getGridFormLayoutHelper(2, 5);
			informationLayout.getLayout().setWidth("100%");
			informationLayout.getLayout().addStyleName("colored-gridlayout");
			informationLayout.getLayout().setMargin(false);
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
			return layout;
		}

	}

	private static final long serialVersionUID = 1L;
	private final String title;

	private MilestoneInformationLayout milestoneInformationLayout;

	public MilestoneFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public void attachField(final Object propertyId, final Field field) {
		milestoneInformationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createBottomPanel();

	protected abstract Layout createTopPanel();

	@Override
	public Layout getLayout() {
		final AddViewLayout milestoneAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/project/milestone.png"));

		final Layout topLayout = createTopPanel();
		if (topLayout != null) {
			milestoneAddLayout.addTopControls(topLayout);
		}

		milestoneInformationLayout = new MilestoneInformationLayout();

		final Layout bottomLayout = createBottomPanel();
		if (bottomLayout != null) {
			milestoneAddLayout.addBottomControls(bottomLayout);
		}

		milestoneAddLayout.addBody(milestoneInformationLayout.getLayout());

		return milestoneAddLayout;
	}
}
