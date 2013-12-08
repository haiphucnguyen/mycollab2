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
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComboBox;

public class BugPriorityComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	public BugPriorityComboBox() {

		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

		IndexedContainer ic = new IndexedContainer();
		ic.addItem(BugPriorityStatusConstants.PRIORITY_BLOCKER);
		ic.addItem(BugPriorityStatusConstants.PRIORITY_CRITICAL);
		ic.addItem(BugPriorityStatusConstants.PRIORITY_MAJOR);
		ic.addItem(BugPriorityStatusConstants.PRIORITY_MINOR);
		ic.addItem(BugPriorityStatusConstants.PRIORITY_TRIVIAL);

		this.setContainerDataSource(ic);

		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_BLOCKER,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_BLOCKER_IMG));
		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_CRITICAL,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_CRITICAL_IMG));
		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_MAJOR,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG));
		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_MINOR,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_MINOR_IMG));
		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_TRIVIAL,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_TRIVIAL_IMG));
		this.setNullSelectionAllowed(false);
	}

	public static Resource getIconResourceByPriority(String priority) {
		Resource iconPriority = MyCollabResource
				.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG);
		if (BugPriorityStatusConstants.PRIORITY_BLOCKER.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_BLOCKER_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_CRITICAL
				.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_CRITICAL_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_MAJOR.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_MINOR.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_MINOR_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_TRIVIAL.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_TRIVIAL_IMG);
		}
		return iconPriority;
	}
}
