/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class ProjectFormViewLayoutFactory implements
		IFormLayoutFactory {
	public static class ProjectInformationLayout implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper moreInfoLayout;

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("homepage")) {
				moreInfoLayout.addComponent(field, "Home Page", 0, 0,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("projectstatus")) {
				moreInfoLayout.addComponent(field, "Status", 1, 0,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("planstartdate")) {
				moreInfoLayout.addComponent(field, "Plan Start Date", 0, 1,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("currencyid")) {
				moreInfoLayout.addComponent(field, "Currency", 1, 1,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("planenddate")) {
				moreInfoLayout.addComponent(field, "Plan End Date", 0, 2,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("defaultbillingrate")) {
				moreInfoLayout.addComponent(field, "Rate", 1, 2,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("actualstartdate")) {
				moreInfoLayout.addComponent(field, "Actual Start Date", 0, 3,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("targetbudget")) {
				moreInfoLayout.addComponent(field, "Target Budget", 1, 3,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("actualenddate")) {
				moreInfoLayout.addComponent(field, "Actual End Date", 0, 4,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("actualbudget")) {
				moreInfoLayout.addComponent(field, "Actual Budget", 1, 4,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("description")) {
				moreInfoLayout
						.addComponent(field, "Description", 0, 5, 2,
								UIConstants.DEFAULT_2XCONTROL_WIDTH,
								Alignment.TOP_LEFT);
			}
		}

		@Override
		public Layout getLayout() {
			final VerticalLayout layout = new VerticalLayout();
			layout.addStyleName("colored-gridlayout");

			moreInfoLayout = new GridFormLayoutHelper(2, 6, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			moreInfoLayout.getLayout().setWidth("100%");
			moreInfoLayout.getLayout().setMargin(false);
			layout.addComponent(moreInfoLayout.getLayout());
			layout.setComponentAlignment(moreInfoLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
			return layout;
		}
	}

	private static final long serialVersionUID = 1L;

	private final String title;

	private ProjectInformationLayout projectInformationLayout;

	public ProjectFormViewLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public void attachField(final Object propertyId, final Field field) {
		projectInformationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createBottomPanel();

	protected abstract Layout createTopPanel();

	@Override
	public Layout getLayout() {
		final AddViewLayout projectAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/project/project.png"));

		projectInformationLayout = new ProjectInformationLayout();

		projectAddLayout.addTopControls(createTopPanel());

		projectAddLayout.addBody(projectInformationLayout.getLayout());

		projectAddLayout.addBottomControls(createBottomPanel());

		return projectAddLayout;
	}
}
