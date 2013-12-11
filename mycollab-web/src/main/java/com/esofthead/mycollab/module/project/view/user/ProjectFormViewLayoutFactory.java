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
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 */
public abstract class ProjectFormViewLayoutFactory implements
		IFormLayoutFactory {
	public static class ProjectInformationLayout implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper moreInfoLayout;

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("homepage")) {
				this.moreInfoLayout.addComponent(field, "Home Page", 0, 0,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("projectstatus")) {
				this.moreInfoLayout.addComponent(field, "Status", 1, 0,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("planstartdate")) {
				this.moreInfoLayout.addComponent(field, "Plan Start Date", 0,
						1, Alignment.TOP_LEFT);
			} else if (propertyId.equals("currencyid")) {
				this.moreInfoLayout.addComponent(field, "Currency", 1, 1,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("planenddate")) {
				this.moreInfoLayout.addComponent(field, "Plan End Date", 0, 2,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("defaultbillingrate")) {
				this.moreInfoLayout.addComponent(field, "Rate", 1, 2,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("actualstartdate")) {
				this.moreInfoLayout.addComponent(field, "Actual Start Date", 0,
						3, Alignment.TOP_LEFT);
			} else if (propertyId.equals("targetbudget")) {
				this.moreInfoLayout.addComponent(field, "Target Budget", 1, 3,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("actualenddate")) {
				this.moreInfoLayout.addComponent(field, "Actual End Date", 0,
						4, Alignment.TOP_LEFT);
			} else if (propertyId.equals("actualbudget")) {
				this.moreInfoLayout.addComponent(field, "Actual Budget", 1, 4,
						Alignment.TOP_LEFT);
			} else if (propertyId.equals("description")) {
				this.moreInfoLayout
						.addComponent(field, "Description", 0, 5, 2,
								UIConstants.DEFAULT_2XCONTROL_WIDTH,
								Alignment.TOP_LEFT);
			}
		}

		@Override
		public Layout getLayout() {
			final VerticalLayout layout = new VerticalLayout();
			layout.addStyleName("colored-gridlayout");

			this.moreInfoLayout = new GridFormLayoutHelper(2, 6, "100%",
					"167px", Alignment.MIDDLE_LEFT);
			this.moreInfoLayout.getLayout().setWidth("100%");
			this.moreInfoLayout.getLayout().setMargin(false);
			layout.addComponent(this.moreInfoLayout.getLayout());
			layout.setComponentAlignment(this.moreInfoLayout.getLayout(),
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
		this.projectInformationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createBottomPanel();

	protected abstract Layout createTopPanel();

	@Override
	public Layout getLayout() {
		final AddViewLayout projectAddLayout = new AddViewLayout(this.title,
				MyCollabResource.newResource("icons/24/project/project.png"));

		this.projectInformationLayout = new ProjectInformationLayout();

		projectAddLayout.addTopControls(this.createTopPanel());

		projectAddLayout.addBody(this.projectInformationLayout.getLayout());

		projectAddLayout.addBottomControls(this.createBottomPanel());

		return projectAddLayout;
	}
}
