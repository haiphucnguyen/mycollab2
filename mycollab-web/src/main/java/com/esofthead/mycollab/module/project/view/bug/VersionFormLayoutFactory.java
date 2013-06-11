/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

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
@SuppressWarnings("serial")
public abstract class VersionFormLayoutFactory implements IFormLayoutFactory {

	private final String title;
	private VersionInformationLayout informationLayout;

	public VersionFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		final AddViewLayout componentAddLayout = new AddViewLayout(this.title,
				new ThemeResource("icons/48/project/version.png"));

		final Layout topPanel = this.createTopPanel();
		if (topPanel != null) {
			componentAddLayout.addTopControls(topPanel);
		}

		final VerticalLayout layout = new VerticalLayout();

		final Label organizationHeader = new Label("Version Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		this.informationLayout = new VersionInformationLayout();
		this.informationLayout.getLayout().setWidth("100%");
		layout.addComponent(this.informationLayout.getLayout());

		componentAddLayout.addBody(layout);

		final Layout bottomPanel = this.createBottomPanel();
		if (bottomPanel != null) {
			componentAddLayout.addBottomControls(bottomPanel);
		}

		return componentAddLayout;
	}

	@Override
	public void attachField(final Object propertyId, final Field field) {
		this.informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	public static class VersionInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			this.informationLayout = new GridFormLayoutHelper(2, 3, "100%",
					"167px", Alignment.MIDDLE_LEFT);
			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");
			final VerticalLayout layout = new VerticalLayout();
			layout.addComponent(this.informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("versionname")) {
				this.informationLayout.addComponent(field, "Version Name", 0,
						0, 2, "100%");
			} else if (propertyId.equals("description")) {
				this.informationLayout.addComponent(field, "Description", 0, 1,
						2, "100%");
			} else if (propertyId.equals("duedate")) {
				this.informationLayout.addComponent(field, "Due Date", 0, 2);
			}
		}
	}
}
