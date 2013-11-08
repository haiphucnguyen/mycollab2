/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
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
	private AddViewLayout componentAddLayout;

	public VersionFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		componentAddLayout = new AddViewLayout(this.title,
				MyCollabResource.newResource("icons/24/project/version.png"));

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

	public void addTitleStyleName(String styleName) {
		componentAddLayout.addTitleStyleName(styleName);
	}

	public void removeTitleStyleName(String styleName) {
		componentAddLayout.removeTitleStyleName(styleName);
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
