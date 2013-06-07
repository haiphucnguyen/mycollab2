/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

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
				this.informationLayout.addComponent(field, "Name", 0, 0, 2,
						"100%");
			} else if (propertyId.equals("startdate")) {
				this.informationLayout.addComponent(field, "Start Date", 0, 1);
			} else if (propertyId.equals("enddate")) {
				this.informationLayout.addComponent(field, "End Date", 0, 2);
			} else if (propertyId.equals("owner")) {
				this.informationLayout.addComponent(field, "Responsible User",
						1, 1);
			} else if (propertyId.equals("status")) {
				this.informationLayout.addComponent(field, "Status", 1, 2);
			} else if (propertyId.equals("numOpenTasks")) {
				this.informationLayout.addComponent(field, "Tasks", 0, 3);
			} else if (propertyId.equals("numOpenBugs")) {
				this.informationLayout.addComponent(field, "Bugs", 1, 3);
			} else if (propertyId.equals("description")) {
				this.informationLayout.addComponent(field, "Description", 0, 4,
						2, "100%");
			}
		}

		@Override
		public Layout getLayout() {
			final VerticalLayout layout = new VerticalLayout();

			final Label organizationHeader = new Label("Milestone Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			this.informationLayout = new GridFormLayoutHelper(2, 5, "100%",
					"145px", Alignment.MIDDLE_LEFT);
			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");
			this.informationLayout.getLayout().setMargin(false);
			layout.addComponent(this.informationLayout.getLayout());
			layout.setComponentAlignment(this.informationLayout.getLayout(),
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
		this.milestoneInformationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createBottomPanel();

	protected abstract Layout createTopPanel();

	@Override
	public Layout getLayout() {
		final AddViewLayout milestoneAddLayout = new AddViewLayout(this.title,
				new ThemeResource("icons/48/project/milestone.png"));

		final Layout topLayout = this.createTopPanel();
		if (topLayout != null) {
			milestoneAddLayout.addTopControls(topLayout);
		}

		this.milestoneInformationLayout = new MilestoneInformationLayout();

		final Layout bottomLayout = this.createBottomPanel();
		if (bottomLayout != null) {
			milestoneAddLayout.addBottomControls(bottomLayout);
		}

		milestoneAddLayout.addBody(this.milestoneInformationLayout.getLayout());

		return milestoneAddLayout;
	}
}
