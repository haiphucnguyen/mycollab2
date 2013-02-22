/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.ui.components.ProjectUiUtils;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public abstract class VersionFormLayoutFactory implements IFormLayoutFactory {

	private final String title;
	private VersionInformationLayout informationLayout;

	public VersionFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout componentAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/project/version.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			componentAddLayout.addTopControls(topPanel);
		}

		VerticalLayout layout = new VerticalLayout();

		Label organizationHeader = new Label("Version Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		informationLayout = new VersionInformationLayout();
		informationLayout.getLayout().setWidth("100%");
		layout.addComponent(informationLayout.getLayout());

		componentAddLayout.addBody(layout);

		Layout bottomPanel = createBottomPanel();
		if (bottomPanel != null) {
			componentAddLayout.addBottomControls(bottomPanel);
		}

		return componentAddLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	public static class VersionInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			informationLayout = ProjectUiUtils.getGridFormLayoutHelper(2, 3);
			informationLayout.getLayout().setWidth("100%");
			VerticalLayout layout = new VerticalLayout();
			layout.addComponent(informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("versionname")) {
				informationLayout.addComponent(field, "Version Name", 0, 0, 2,
						"100%");
			} else if (propertyId.equals("description")) {
				informationLayout.addComponent(field, "Description", 0, 1, 2,
						"100%");
			} else if (propertyId.equals("duedate")) {
				informationLayout.addComponent(field, "Due Date", 0, 2);
			}
		}
	}
}
