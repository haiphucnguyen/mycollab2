/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.ui.components.ProjectUiUtils;
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

/**
 * 
 * @author haiphucnguyen
 */
public abstract class ProjectFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private final String title;

	private ProjectInformationLayout projectInformationLayout;

	public ProjectFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout projectAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/project/project.png"));

		projectInformationLayout = new ProjectInformationLayout();

		projectAddLayout.addTopControls(createTopPanel());

		projectAddLayout.addBody(projectInformationLayout.getLayout());

		projectAddLayout.addBottomControls(createBottomPanel());

		return projectAddLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		projectInformationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	public static class ProjectInformationLayout implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper informationLayout;
		private GridFormLayoutHelper financialLayout;
		private GridFormLayoutHelper descriptionLayout;

		@Override
		public Layout getLayout() {
			VerticalLayout layout = new VerticalLayout();

			Label organizationHeader = new Label("Project Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = ProjectUiUtils.getGridFormLayoutHelper(2, 2);
			informationLayout.getLayout().setWidth("100%");
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);

			Label financialHeader = new Label(
					"Schedule & Financial Information");
			financialHeader.setStyleName("h2");
			layout.addComponent(financialHeader);

			financialLayout = ProjectUiUtils.getGridFormLayoutHelper(2, 4);
			financialLayout.getLayout().setWidth("100%");
			layout.addComponent(financialLayout.getLayout());
			layout.setComponentAlignment(financialLayout.getLayout(),
					Alignment.BOTTOM_CENTER);

			Label descHeader = new Label("Description");
			descHeader.setStyleName("h2");
			layout.addComponent(descHeader);

			descriptionLayout = ProjectUiUtils.getGridFormLayoutHelper(2, 1);
			descriptionLayout.getLayout().setWidth("100%");
			layout.addComponent(descriptionLayout.getLayout());
			layout.setComponentAlignment(descriptionLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("name")) {
				informationLayout.addComponent(field, "Project Name", 0, 0);
			} else if (propertyId.equals("homepage")) {
				informationLayout.addComponent(field, "Home Page", 1, 0);
			} else if (propertyId.equals("shortname")) {
				informationLayout.addComponent(field, "Short Name", 0, 1);
			} else if (propertyId.equals("projectstatus")) {
				informationLayout.addComponent(field, "Status", 1, 1);
			} else if (propertyId.equals("planstartdate")) {
				financialLayout.addComponent(field, "Plan Start Date", 0, 0);
			} else if (propertyId.equals("currencyid")) {
				financialLayout.addComponent(field, "Currency", 1, 0);
			} else if (propertyId.equals("planenddate")) {
				financialLayout.addComponent(field, "Plan End Date", 0, 1);
			} else if (propertyId.equals("defaultbillingrate")) {
				financialLayout.addComponent(field, "Rate", 1, 1);
			} else if (propertyId.equals("actualstartdate")) {
				financialLayout.addComponent(field, "Actual Start Date", 0, 2);
			} else if (propertyId.equals("targetbudget")) {
				financialLayout.addComponent(field, "Target Budget", 1, 2);
			} else if (propertyId.equals("actualenddate")) {
				financialLayout.addComponent(field, "Actual End Date", 0, 3);
			} else if (propertyId.equals("actualbudget")) {
				financialLayout.addComponent(field, "Actual Budget", 1, 3);
			} else if (propertyId.equals("description")) {
				descriptionLayout.addComponent(field, "Description", 0, 0, 2,
						UIConstants.DEFAULT_2XCONTROL_WIDTH);
			}
		}
	}
}
