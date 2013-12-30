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

package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class MilestoneFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private GridFormLayoutHelper informationLayout;

	@Override
	public boolean attachField(final Object propertyId, final Field<?> field) {
		if (propertyId.equals("name")) {
			this.informationLayout.addComponent(field, "Name", 0, 0, 2, "100%");
		} else if (propertyId.equals("startdate")) {
			this.informationLayout.addComponent(field, "Start Date", 0, 1);
		} else if (propertyId.equals("enddate")) {
			this.informationLayout.addComponent(field, "End Date", 0, 2);
		} else if (propertyId.equals("owner")) {
			this.informationLayout.addComponent(field, LocalizationHelper
					.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 1, 1);
		} else if (propertyId.equals("status")) {
			this.informationLayout.addComponent(field, "Status", 1, 2);
		} else if (propertyId.equals("numOpenTasks")) {
			this.informationLayout.addComponent(field, "Tasks", 0, 3);
		} else if (propertyId.equals("numOpenBugs")) {
			this.informationLayout.addComponent(field, "Bugs", 1, 3);
		} else if (propertyId.equals("description")) {
			this.informationLayout.addComponent(field, "Description", 0, 4, 2,
					"100%");
		} else {
			return false;
		}

		return true;
	}

	@Override
	public Layout getLayout() {
		final VerticalLayout layout = new VerticalLayout();

		final Label organizationHeader = new Label("Phase Information");
		organizationHeader.setStyleName("h2");
		layout.addComponent(organizationHeader);

		this.informationLayout = new GridFormLayoutHelper(2, 5, "100%",
				"145px", Alignment.MIDDLE_LEFT);
		this.informationLayout.getLayout().setWidth("100%");
		this.informationLayout.getLayout().addStyleName("colored-gridlayout");
		this.informationLayout.getLayout().setMargin(false);
		layout.addComponent(this.informationLayout.getLayout());
		layout.setComponentAlignment(this.informationLayout.getLayout(),
				Alignment.BOTTOM_CENTER);
		return layout;
	}
}
